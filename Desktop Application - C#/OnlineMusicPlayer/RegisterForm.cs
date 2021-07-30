using MySql.Data.MySqlClient;
using SkyBeats;
using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace SkyBeats
{
    public partial class RegisterForm : Form
    {
  

        WebClient wc = new WebClient();
        NameValueCollection dataToSend = new NameValueCollection();
        public RegisterForm()
        {
            InitializeComponent();
        }

        private void btnCreate_Click(object sender, EventArgs e)
        {

            dataToSend["firstname"] = txtFirstname.Text;
            dataToSend["lastname"] = txtLastname.Text;
            dataToSend["email"] = txtEmail.Text;
            dataToSend["password"] = txtPassword.Text;
            dataToSend["gender"] = txtGender.Text;
            dataToSend["contact"] = txtContact.Text;

            string GetData = Encoding.UTF8.GetString(wc.UploadValues(@"https://skybeats.ga/desktop_app/csharpregister.php", dataToSend));
            Console.WriteLine(GetData);

            if (string.IsNullOrEmpty(txtEmail.Text) || string.IsNullOrEmpty(txtPassword.Text) || string.IsNullOrEmpty(txtFirstname.Text) || string.IsNullOrEmpty(txtLastname.Text) || string.IsNullOrEmpty(txtGender.Text) || string.IsNullOrEmpty(txtContact.Text))
            {
                MessageBox.Show("Please fill out all the input boxes", "Error");

            }
            else if (GetData == "nodata")
            {
                MessageBox.Show("No data found!", "#nodata", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            else if (GetData == "emailexists")
            {
                MessageBox.Show("Email already exists!", "#emailexists", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            else if (GetData == "success") { 
                    
                    MessageBox.Show("Account Successfully Created!");
                    this.Hide();
                    SkyBeats frm3 = new SkyBeats();
                    frm3.ShowDialog();
                }
            else
            {
                MessageBox.Show("Something went be wrong!", "#dataerror", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }


        }
    }
}
