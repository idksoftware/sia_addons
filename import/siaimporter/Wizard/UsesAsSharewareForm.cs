using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace IDK.Gui
{
    public partial class UsesAsSharewareForm : Form
    {
        
        public UsesAsSharewareForm()
        {
            InitializeComponent();
            DateTime today = DateTime.Now;
            Shareware shareware = new Shareware();
            if (shareware.Init() == false)
            {
                MessageBox.Show(
                    "Flash Memory Manager 1.0 application not installed correctly? Please re-install. If this do'es not fix the problem, please contact IDK Software Ltd",
                    "Flash Memory Manager 1.0", MessageBoxButtons.OK, MessageBoxIcon.Error);
                Environment.Exit(-1);
            }
            
            if (shareware.IsNewInstall == true)
            {
                shareware.WriteInstallDate();
            }
            DateTime id = shareware.InstalledDate;
            TimeSpan ts30 = new TimeSpan(30, 0, 0, 0);
            TimeSpan idTS = new TimeSpan((id.Ticks + ts30.Ticks) - today.Ticks);
            int days = (int)idTS.TotalDays + 1;
            //days = -1;
            if (days < 0)
            {
                labelElapsed.Visible = true;
                labelDaysRemaining.Visible = false;
                labelDays.Visible = false;
                labelEvaluale.Visible = false;
                labelYouHave.Visible = false;
            }
            else
            {
                labelElapsed.Visible = false;
                labelDaysRemaining.Visible = true;
                labelDays.Visible = true;
                labelEvaluale.Visible = true;
                labelDaysRemaining.Text = days.ToString();
                labelYouHave.Visible = true;
            }
        }

        private void buttonLicence_Click(object sender, EventArgs e)
        {
            SharewareForm form = new SharewareForm();
            Shareware shareware = new Shareware();
            if (shareware.Init() == false)
            {
                MessageBox.Show(
                    "Flash Memory Manager 1.0 application not installed correctly? Please re-install. If this do'es not fix the problem, please contact IDK Software Ltd",
                    "Flash Memory Manager 1.0", MessageBoxButtons.OK, MessageBoxIcon.Error);
                Environment.Exit(-1);
            }
            form.UserName = shareware.UserName;
            form.ShowDialog();
        }
    }
}