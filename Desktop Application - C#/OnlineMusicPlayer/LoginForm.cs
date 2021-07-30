using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace SkyBeats
{ 
   public partial class LoginForm : Form { 
   
    WebClient wc = new WebClient();
    NameValueCollection dataToSend = new NameValueCollection();
        string GetData;


        public LoginForm()
        {
            InitializeComponent();
        }

        private void LoginForm_Load(object sender, EventArgs e)
        {

        }

        

        private void btnLogin_Click(object sender, EventArgs e)
        {

            dataToSend["user_username"] = textEmail.Text;
            dataToSend["user_password"] = textPassword.Text;

            try
            {
                GetData = Encoding.UTF8.GetString(wc.UploadValues(@"http://localhost/music/csharplogin.php", dataToSend));
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }

            finally
            {
                GetData = Encoding.UTF8.GetString(wc.UploadValues(@"https://skybeats.ga/desktop_app/csharplogin.php", dataToSend));
            }


            Console.WriteLine(GetData);
            if (string.IsNullOrEmpty(textEmail.Text) || string.IsNullOrEmpty(textPassword.Text))
            {
                MessageBox.Show("Please input EMail and Password", "Error");
            }
            else if (GetData == "nodata")
            {
                MessageBox.Show("No data found!", "#nodata", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            else if (GetData == "dataerror")
            {
                MessageBox.Show("Something went be wrong!", "#dataerror", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            else if (GetData == "usernotfound")
            {
                MessageBox.Show("Email not found!", "#usernotfound", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            else if (GetData == "userwrongpassword")
            {
                MessageBox.Show("Email/Password incorrect", "#userwrongpassword", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            else if (GetData == "userbanned")
            {
                MessageBox.Show("Your account has been banned!", "#userbanned", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            else if (GetData == "success")
            {
                MessageBox.Show("Login Successful!");
                this.Hide();
                SkyBeats frm2 = new SkyBeats();
                frm2.ShowDialog();

            }

            else
            {
                MessageBox.Show("Something went be wrong!", "#dataerror", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
        }

        private void btnCreate_Click(object sender, EventArgs e)
        {
            this.Hide();
            RegisterForm frm3 = new RegisterForm();
            frm3.ShowDialog();
        }
    }
}
