using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using MySql.Data.MySqlClient;
using System.Collections;
using System.Diagnostics;
using System.Net;
using System.IO;
using Newtonsoft.Json;

namespace SkyBeats
{
    public partial class SkyBeats : Form
    {

        List<String> listName = new List<String>();
        List<String> listURL = new List<String>();
        List<String> albumART = new List<String>();
        List<String> Artist = new List<String>();
        String url;
        WebRequest request;
        HttpWebResponse response;

        public SkyBeats()
        {
            InitializeComponent();
            
            try
            {
                request = WebRequest.Create("http://localhost/music/api.php");
                url = "http://localhost/music/assets/uploads/";
                response = (HttpWebResponse)request.GetResponse();
            }
            catch (WebException ex)
            {
                Console.WriteLine(ex.Message);
            }

            finally
            {
                if (response == null)
                {
                    request = WebRequest.Create("https://skybeats.ga/api.php");
                    url = "https://skybeats.ga/assets/uploads/";
                    response = (HttpWebResponse)request.GetResponse();
                }
            }

            // Get the response.
            

            // Get the stream containing content returned by the server.
            Stream dataStream = response.GetResponseStream();
            // Open the stream using a StreamReader for easy access.
            StreamReader reader = new StreamReader(dataStream);
            // Read the content.
            string responseFromServer = reader.ReadToEnd();

            
            //Deserialize responseFromServer
            List<Posts> post = JsonConvert.DeserializeObject<List<Posts>>(responseFromServer) as List<Posts>;
            // Display the content.

            
            foreach (Posts obj in post)
            {
               
                Songs.Items.Add(obj.title);
                listName.Add(obj.title);
                listURL.Add(url+obj.upath);
                albumART.Add(url+obj.cover_image);
                Artist.Add(obj.artist);
            }


            // Cleanup the streams and the response.
            reader.Close();
            dataStream.Close();
            response.Close();


        }
       

        private void songList_SelectedIndexChanged(object sender, EventArgs e)
        {
            axWindowsMediaPlayer1.URL = listURL[Songs.SelectedIndex];
            pictureBox1.ImageLocation = albumART[Songs.SelectedIndex];
            pictureBox1.SizeMode = PictureBoxSizeMode.StretchImage;
            artistLabel.Text = "Artist: "+Artist[Songs.SelectedIndex];
        }

        

        public void updateToNext(int index)
        {
            int next_index = (index + 1)%Songs.Items.Count;
            Songs.SelectedIndex = next_index;
            axWindowsMediaPlayer1.URL = listURL[next_index];
        }
        
       

        private void button1_Click_1(object sender, EventArgs e)
        {

            Application.Restart();
        }


        
    }
}
