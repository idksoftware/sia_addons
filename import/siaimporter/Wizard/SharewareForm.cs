using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace IDK.Gui
{
    public partial class SharewareForm : Form
    {
        Shareware shareware = null;
        public SharewareForm()
        {
            InitializeComponent();
            shareware = new Shareware();
            if (shareware.Init() == false)
            {
                MessageBox.Show(
                    "Flash Memory Manager 1.0 application not installed correctly? Please re-install. If this do'es not fix the problem, please contact IDK Software Ltd",
                    "Flash Memory Manager 1.0", MessageBoxButtons.OK, MessageBoxIcon.Error);
                Environment.Exit(-1);
            }
            textBoxName.Text = shareware.UserName;

        }

        private void buttoOk_Click(object sender, EventArgs e)
        {

            if (shareware.Verify(textBoxLic1.Text,textBoxLic2.Text) == true)
            {
                shareware.UserName = textBoxName.Text;
                shareware.WriteUserName();
                return;
            }
            this.DialogResult = DialogResult.None;
            if (MessageBox.Show("Licence key incorrect?", "Flash Memory Manager 1.0",
                            MessageBoxButtons.RetryCancel,
                            MessageBoxIcon.Question) == DialogResult.Cancel)
            {
                this.DialogResult = DialogResult.Cancel;
            }
            
        }
        public String UserName
        {
            get { return textBoxName.Text; }
            set { textBoxName.Text = value; }
        } 
    }
}