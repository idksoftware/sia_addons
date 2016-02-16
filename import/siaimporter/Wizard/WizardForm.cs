<<<<<<< HEAD
using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;

namespace IDK.Gui
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
    public class WizardForm : System.Windows.Forms.Form
	{
		private IDK.Gui.Wizard.Wizard FMMWizard;
        private IDK.Gui.Wizard.WizardPage WelcomePage;
        private IDK.Gui.Wizard.InfoContainer infoContainer1;
        private System.Windows.Forms.Label label7;
		private IDK.Gui.Wizard.WizardPage wpAlternateFinish;
		private IDK.Gui.Wizard.InfoContainer infoContainerOptionsPage;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Label label11;
        private IDK.Gui.Wizard.WizardPage wizardPagePassword;
        private IDK.Gui.Wizard.Header header7;
        private TextBox textBoxPasswordConfirm;
        private Label label21;
        private TextBox textBoxPassword;
        private Label label22;
        private IDK.Gui.Wizard.WizardPage FMMRegistration;
        private Button buttonShareware;
        private Label label26;
        private TextBox textBox19;
        private TextBox textBox18;
        private TextBox textBox17;
        private TextBox textBox16;
        private TextBox textBox15;
        private Label label25;
        private IDK.Gui.Wizard.Header header9;
        private IDK.Gui.Wizard.WizardPage wpLicenceKey;
        private IDK.Gui.Wizard.Header header4;
        private Label label32;
        private Label label33;
        private TextBox textBoxLic2;
        private TextBox textBoxLic1;
        private TextBox textBoxName;
        private Button buttonUseAsShareware;
		private System.ComponentModel.IContainer components;
        private IDK.Gui.Wizard.WizardPage wpLocationOptions;
        private IDK.Gui.Wizard.Header header1;
        private Label label12;
        private bool continueOn = false;
        private TextBox textBoxLibraryLocalion;
        private Button buttonBrowse;
        private FolderBrowserDialog folderBrowserDialogLibraryLocation;
        private String shutdownOption = null;
        private String libraryLocation = null;
        
		public WizardForm()
		{
			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();
            this.TopMost = true;
			//
			// TODO: Add any constructor code after InitializeComponent call
			//
		}

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		protected override void Dispose( bool disposing )
		{
			if( disposing )
			{
				if (components != null) 
				{
					components.Dispose();
				}
			}
			base.Dispose( disposing );
		}

		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(WizardForm));
            this.FMMWizard = new IDK.Gui.Wizard.Wizard();
            this.WelcomePage = new IDK.Gui.Wizard.WizardPage();
            this.infoContainer1 = new IDK.Gui.Wizard.InfoContainer();
            this.label11 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.wpAlternateFinish = new IDK.Gui.Wizard.WizardPage();
            this.infoContainerOptionsPage = new IDK.Gui.Wizard.InfoContainer();
            this.label9 = new System.Windows.Forms.Label();
            this.wpLocationOptions = new IDK.Gui.Wizard.WizardPage();
            this.buttonBrowse = new System.Windows.Forms.Button();
            this.textBoxLibraryLocalion = new System.Windows.Forms.TextBox();
            this.label12 = new System.Windows.Forms.Label();
            this.header1 = new IDK.Gui.Wizard.Header();
            this.wizardPagePassword = new IDK.Gui.Wizard.WizardPage();
            this.textBoxPasswordConfirm = new System.Windows.Forms.TextBox();
            this.label21 = new System.Windows.Forms.Label();
            this.textBoxPassword = new System.Windows.Forms.TextBox();
            this.label22 = new System.Windows.Forms.Label();
            this.header7 = new IDK.Gui.Wizard.Header();
            this.wpLicenceKey = new IDK.Gui.Wizard.WizardPage();
            this.buttonUseAsShareware = new System.Windows.Forms.Button();
            this.label33 = new System.Windows.Forms.Label();
            this.textBoxLic2 = new System.Windows.Forms.TextBox();
            this.textBoxLic1 = new System.Windows.Forms.TextBox();
            this.textBoxName = new System.Windows.Forms.TextBox();
            this.label32 = new System.Windows.Forms.Label();
            this.header4 = new IDK.Gui.Wizard.Header();
            this.FMMRegistration = new IDK.Gui.Wizard.WizardPage();
            this.buttonShareware = new System.Windows.Forms.Button();
            this.label26 = new System.Windows.Forms.Label();
            this.textBox19 = new System.Windows.Forms.TextBox();
            this.textBox18 = new System.Windows.Forms.TextBox();
            this.textBox17 = new System.Windows.Forms.TextBox();
            this.textBox16 = new System.Windows.Forms.TextBox();
            this.textBox15 = new System.Windows.Forms.TextBox();
            this.label25 = new System.Windows.Forms.Label();
            this.header9 = new IDK.Gui.Wizard.Header();
            this.folderBrowserDialogLibraryLocation = new System.Windows.Forms.FolderBrowserDialog();
            this.FMMWizard.SuspendLayout();
            this.WelcomePage.SuspendLayout();
            this.infoContainer1.SuspendLayout();
            this.wpAlternateFinish.SuspendLayout();
            this.infoContainerOptionsPage.SuspendLayout();
            this.wpLocationOptions.SuspendLayout();
            this.wizardPagePassword.SuspendLayout();
            this.wpLicenceKey.SuspendLayout();
            this.FMMRegistration.SuspendLayout();
            this.SuspendLayout();
            // 
            // FMMWizard
            // 
            this.FMMWizard.Controls.Add(this.WelcomePage);
            this.FMMWizard.Controls.Add(this.wpAlternateFinish);
            this.FMMWizard.Controls.Add(this.wpLocationOptions);
            this.FMMWizard.Controls.Add(this.wizardPagePassword);
            this.FMMWizard.Controls.Add(this.wpLicenceKey);
            this.FMMWizard.Controls.Add(this.FMMRegistration);
            this.FMMWizard.Dock = System.Windows.Forms.DockStyle.Fill;
            this.FMMWizard.Font = new System.Drawing.Font("Tahoma", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.FMMWizard.Location = new System.Drawing.Point(0, 0);
            this.FMMWizard.Name = "FMMWizard";
            this.FMMWizard.Pages.AddRange(new IDK.Gui.Wizard.WizardPage[] {
            this.WelcomePage,
            this.wpLicenceKey,
            this.wizardPagePassword,
            this.wpLocationOptions,
            this.wpAlternateFinish});
            this.FMMWizard.Size = new System.Drawing.Size(676, 429);
            this.FMMWizard.TabIndex = 0;
            this.FMMWizard.CloseFromCancel += new System.ComponentModel.CancelEventHandler(this.wizard1_CloseFromCancel);
            // 
            // WelcomePage
            // 
            this.WelcomePage.Controls.Add(this.infoContainer1);
            this.WelcomePage.Dock = System.Windows.Forms.DockStyle.Fill;
            this.WelcomePage.IsFinishPage = false;
            this.WelcomePage.Location = new System.Drawing.Point(0, 0);
            this.WelcomePage.Name = "WelcomePage";
            this.WelcomePage.Size = new System.Drawing.Size(676, 358);
            this.WelcomePage.TabIndex = 1;
            // 
            // infoContainer1
            // 
            this.infoContainer1.BackColor = System.Drawing.Color.White;
            this.infoContainer1.Controls.Add(this.label11);
            this.infoContainer1.Controls.Add(this.label7);
            this.infoContainer1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.infoContainer1.Image = global::IDK.Gui.Properties.Resources.disks;
            this.infoContainer1.Location = new System.Drawing.Point(0, 0);
            this.infoContainer1.Name = "infoContainer1";
            this.infoContainer1.PageTitle = "Welcome to the Flash Memory Manager 1.0 Setup Wizard";
            this.infoContainer1.Size = new System.Drawing.Size(676, 358);
            this.infoContainer1.TabIndex = 0;
            // 
            // label11
            // 
            this.label11.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.label11.FlatStyle = System.Windows.Forms.FlatStyle.System;
            this.label11.Location = new System.Drawing.Point(275, 82);
            this.label11.Name = "label11";
            this.label11.Size = new System.Drawing.Size(394, 45);
            this.label11.TabIndex = 8;
            this.label11.Text = "This wizard helps you setup Flash Memory Manager on your system. ";
            // 
            // label7
            // 
            this.label7.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.label7.FlatStyle = System.Windows.Forms.FlatStyle.System;
            this.label7.Location = new System.Drawing.Point(291, 399);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(362, 41);
            this.label7.TabIndex = 10;
            this.label7.Text = "To Continue click next.";
            // 
            // wpAlternateFinish
            // 
            this.wpAlternateFinish.Controls.Add(this.infoContainerOptionsPage);
            this.wpAlternateFinish.Dock = System.Windows.Forms.DockStyle.Fill;
            this.wpAlternateFinish.IsFinishPage = true;
            this.wpAlternateFinish.Location = new System.Drawing.Point(0, 0);
            this.wpAlternateFinish.Name = "wpAlternateFinish";
            this.wpAlternateFinish.Size = new System.Drawing.Size(685, 377);
            this.wpAlternateFinish.TabIndex = 8;
            // 
            // infoContainerOptionsPage
            // 
            this.infoContainerOptionsPage.BackColor = System.Drawing.Color.White;
            this.infoContainerOptionsPage.Controls.Add(this.label9);
            this.infoContainerOptionsPage.Dock = System.Windows.Forms.DockStyle.Fill;
            this.infoContainerOptionsPage.Image = ((System.Drawing.Image)(resources.GetObject("infoContainerOptionsPage.Image")));
            this.infoContainerOptionsPage.Location = new System.Drawing.Point(0, 0);
            this.infoContainerOptionsPage.Name = "infoContainerOptionsPage";
            this.infoContainerOptionsPage.PageTitle = "Final Setup Completed Successfully!";
            this.infoContainerOptionsPage.Size = new System.Drawing.Size(685, 377);
            this.infoContainerOptionsPage.TabIndex = 0;
            // 
            // label9
            // 
            this.label9.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.label9.FlatStyle = System.Windows.Forms.FlatStyle.System;
            this.label9.Location = new System.Drawing.Point(267, 76);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(411, 107);
            this.label9.TabIndex = 12;
            this.label9.Text = resources.GetString("label9.Text");
            // 
            // wpLocationOptions
            // 
            this.wpLocationOptions.Controls.Add(this.buttonBrowse);
            this.wpLocationOptions.Controls.Add(this.textBoxLibraryLocalion);
            this.wpLocationOptions.Controls.Add(this.label12);
            this.wpLocationOptions.Controls.Add(this.header1);
            this.wpLocationOptions.Dock = System.Windows.Forms.DockStyle.Fill;
            this.wpLocationOptions.IsFinishPage = false;
            this.wpLocationOptions.Location = new System.Drawing.Point(0, 0);
            this.wpLocationOptions.Name = "wpLocationOptions";
            this.wpLocationOptions.Size = new System.Drawing.Size(685, 377);
            this.wpLocationOptions.TabIndex = 18;
            // 
            // buttonBrowse
            // 
            this.buttonBrowse.Location = new System.Drawing.Point(542, 265);
            this.buttonBrowse.Name = "buttonBrowse";
            this.buttonBrowse.Size = new System.Drawing.Size(120, 33);
            this.buttonBrowse.TabIndex = 39;
            this.buttonBrowse.Text = "Browse";
            this.buttonBrowse.UseVisualStyleBackColor = true;
            this.buttonBrowse.Click += new System.EventHandler(this.buttonBrowse_Click);
            // 
            // textBoxLibraryLocalion
            // 
            this.textBoxLibraryLocalion.Location = new System.Drawing.Point(26, 225);
            this.textBoxLibraryLocalion.Name = "textBoxLibraryLocalion";
            this.textBoxLibraryLocalion.Size = new System.Drawing.Size(636, 27);
            this.textBoxLibraryLocalion.TabIndex = 15;
            this.textBoxLibraryLocalion.TextChanged += new System.EventHandler(this.textBoxLibraryLocalion_TextChanged);
            // 
            // label12
            // 
            this.label12.AutoSize = true;
            this.label12.Location = new System.Drawing.Point(21, 202);
            this.label12.Name = "label12";
            this.label12.Size = new System.Drawing.Size(133, 21);
            this.label12.TabIndex = 14;
            this.label12.Text = "Library Location:";
            // 
            // header1
            // 
            this.header1.BackColor = System.Drawing.SystemColors.Control;
            this.header1.CausesValidation = false;
            this.header1.Description = "This page allows you to set the action FMM carry out when FMM is reached.";
            this.header1.Dock = System.Windows.Forms.DockStyle.Top;
            this.header1.Image = ((System.Drawing.Image)(resources.GetObject("header1.Image")));
            this.header1.Location = new System.Drawing.Point(0, 0);
            this.header1.Name = "header1";
            this.header1.Size = new System.Drawing.Size(685, 94);
            this.header1.TabIndex = 5;
            this.header1.Title = "Library Location";
            // 
            // wizardPagePassword
            // 
            this.wizardPagePassword.Controls.Add(this.textBoxPasswordConfirm);
            this.wizardPagePassword.Controls.Add(this.label21);
            this.wizardPagePassword.Controls.Add(this.textBoxPassword);
            this.wizardPagePassword.Controls.Add(this.label22);
            this.wizardPagePassword.Controls.Add(this.header7);
            this.wizardPagePassword.Dock = System.Windows.Forms.DockStyle.Fill;
            this.wizardPagePassword.IsFinishPage = false;
            this.wizardPagePassword.Location = new System.Drawing.Point(0, 0);
            this.wizardPagePassword.Name = "wizardPagePassword";
            this.wizardPagePassword.Size = new System.Drawing.Size(685, 377);
            this.wizardPagePassword.TabIndex = 12;
            this.wizardPagePassword.CloseFromNext += new IDK.Gui.Wizard.PageEventHandler(this.wizardPagePassword_CloseFromNext);
            // 
            // textBoxPasswordConfirm
            // 
            this.textBoxPasswordConfirm.Location = new System.Drawing.Point(442, 174);
            this.textBoxPasswordConfirm.Name = "textBoxPasswordConfirm";
            this.textBoxPasswordConfirm.PasswordChar = '*';
            this.textBoxPasswordConfirm.Size = new System.Drawing.Size(188, 27);
            this.textBoxPasswordConfirm.TabIndex = 29;
            // 
            // label21
            // 
            this.label21.AutoSize = true;
            this.label21.Location = new System.Drawing.Point(355, 178);
            this.label21.Name = "label21";
            this.label21.Size = new System.Drawing.Size(73, 21);
            this.label21.TabIndex = 28;
            this.label21.Text = "Confirm:";
            // 
            // textBoxPassword
            // 
            this.textBoxPassword.Location = new System.Drawing.Point(157, 174);
            this.textBoxPassword.Name = "textBoxPassword";
            this.textBoxPassword.PasswordChar = '*';
            this.textBoxPassword.Size = new System.Drawing.Size(189, 27);
            this.textBoxPassword.TabIndex = 27;
            this.textBoxPassword.UseSystemPasswordChar = true;
            // 
            // label22
            // 
            this.label22.AutoSize = true;
            this.label22.Location = new System.Drawing.Point(56, 178);
            this.label22.Name = "label22";
            this.label22.Size = new System.Drawing.Size(87, 21);
            this.label22.TabIndex = 26;
            this.label22.Text = "Password:";
            // 
            // header7
            // 
            this.header7.BackColor = System.Drawing.SystemColors.Control;
            this.header7.CausesValidation = false;
            this.header7.Description = "Use this page to setup a password so only you can change the FMMs and other setti" +
                "ng in the FMM application. ";
            this.header7.Dock = System.Windows.Forms.DockStyle.Top;
            this.header7.Image = ((System.Drawing.Image)(resources.GetObject("header7.Image")));
            this.header7.Location = new System.Drawing.Point(0, 0);
            this.header7.Name = "header7";
            this.header7.Size = new System.Drawing.Size(685, 94);
            this.header7.TabIndex = 2;
            this.header7.Title = "Flash Memory Manager password";
            // 
            // wpLicenceKey
            // 
            this.wpLicenceKey.Controls.Add(this.buttonUseAsShareware);
            this.wpLicenceKey.Controls.Add(this.label33);
            this.wpLicenceKey.Controls.Add(this.textBoxLic2);
            this.wpLicenceKey.Controls.Add(this.textBoxLic1);
            this.wpLicenceKey.Controls.Add(this.textBoxName);
            this.wpLicenceKey.Controls.Add(this.label32);
            this.wpLicenceKey.Controls.Add(this.header4);
            this.wpLicenceKey.Dock = System.Windows.Forms.DockStyle.Fill;
            this.wpLicenceKey.IsFinishPage = false;
            this.wpLicenceKey.Location = new System.Drawing.Point(0, 0);
            this.wpLicenceKey.Name = "wpLicenceKey";
            this.wpLicenceKey.Size = new System.Drawing.Size(685, 377);
            this.wpLicenceKey.TabIndex = 17;
            this.wpLicenceKey.CloseFromNext += new IDK.Gui.Wizard.PageEventHandler(this.wpLicenceKey_CloseFromNext);
            this.wpLicenceKey.ShowFromNext += new System.EventHandler(this.wpLicenceKey_ShowFromNext);
            // 
            // buttonUseAsShareware
            // 
            this.buttonUseAsShareware.Location = new System.Drawing.Point(486, 330);
            this.buttonUseAsShareware.Name = "buttonUseAsShareware";
            this.buttonUseAsShareware.Size = new System.Drawing.Size(157, 34);
            this.buttonUseAsShareware.TabIndex = 14;
            this.buttonUseAsShareware.Text = "Shareware";
            this.buttonUseAsShareware.UseVisualStyleBackColor = true;
            this.buttonUseAsShareware.Click += new System.EventHandler(this.buttonUseAsShareware_Click);
            // 
            // label33
            // 
            this.label33.AutoSize = true;
            this.label33.Location = new System.Drawing.Point(56, 284);
            this.label33.Name = "label33";
            this.label33.Size = new System.Drawing.Size(103, 21);
            this.label33.TabIndex = 13;
            this.label33.Text = "Licence Key:";
            // 
            // textBoxLic2
            // 
            this.textBoxLic2.Location = new System.Drawing.Point(282, 279);
            this.textBoxLic2.Name = "textBoxLic2";
            this.textBoxLic2.Size = new System.Drawing.Size(129, 27);
            this.textBoxLic2.TabIndex = 9;
            this.textBoxLic2.TextChanged += new System.EventHandler(this.textBoxLic2_TextChanged);
            // 
            // textBoxLic1
            // 
            this.textBoxLic1.Location = new System.Drawing.Point(173, 279);
            this.textBoxLic1.Name = "textBoxLic1";
            this.textBoxLic1.Size = new System.Drawing.Size(83, 27);
            this.textBoxLic1.TabIndex = 8;
            this.textBoxLic1.TextChanged += new System.EventHandler(this.textBoxLic1_TextChanged);
            // 
            // textBoxName
            // 
            this.textBoxName.Location = new System.Drawing.Point(173, 133);
            this.textBoxName.Name = "textBoxName";
            this.textBoxName.Size = new System.Drawing.Size(195, 27);
            this.textBoxName.TabIndex = 7;
            // 
            // label32
            // 
            this.label32.AutoSize = true;
            this.label32.Location = new System.Drawing.Point(64, 137);
            this.label32.Name = "label32";
            this.label32.Size = new System.Drawing.Size(96, 21);
            this.label32.TabIndex = 6;
            this.label32.Text = "Your name:";
            // 
            // header4
            // 
            this.header4.BackColor = System.Drawing.SystemColors.Control;
            this.header4.CausesValidation = false;
            this.header4.Description = "If you have purchased the softwate please enter your licence key, otherwise you h" +
                "ave 30 days trial period in which you can use the software.";
            this.header4.Dock = System.Windows.Forms.DockStyle.Top;
            this.header4.Image = ((System.Drawing.Image)(resources.GetObject("header4.Image")));
            this.header4.Location = new System.Drawing.Point(0, 0);
            this.header4.Name = "header4";
            this.header4.Size = new System.Drawing.Size(685, 94);
            this.header4.TabIndex = 5;
            this.header4.Title = "Licence Key";
            // 
            // FMMRegistration
            // 
            this.FMMRegistration.Controls.Add(this.buttonShareware);
            this.FMMRegistration.Controls.Add(this.label26);
            this.FMMRegistration.Controls.Add(this.textBox19);
            this.FMMRegistration.Controls.Add(this.textBox18);
            this.FMMRegistration.Controls.Add(this.textBox17);
            this.FMMRegistration.Controls.Add(this.textBox16);
            this.FMMRegistration.Controls.Add(this.textBox15);
            this.FMMRegistration.Controls.Add(this.label25);
            this.FMMRegistration.Controls.Add(this.header9);
            this.FMMRegistration.Dock = System.Windows.Forms.DockStyle.Fill;
            this.FMMRegistration.IsFinishPage = false;
            this.FMMRegistration.Location = new System.Drawing.Point(0, 0);
            this.FMMRegistration.Name = "FMMRegistration";
            this.FMMRegistration.Size = new System.Drawing.Size(676, 429);
            this.FMMRegistration.TabIndex = 14;
            // 
            // buttonShareware
            // 
            this.buttonShareware.Location = new System.Drawing.Point(542, 330);
            this.buttonShareware.Name = "buttonShareware";
            this.buttonShareware.Size = new System.Drawing.Size(120, 34);
            this.buttonShareware.TabIndex = 31;
            this.buttonShareware.Text = "Shareware";
            this.buttonShareware.UseVisualStyleBackColor = true;
            this.buttonShareware.Click += new System.EventHandler(this.buttonShareware_Click);
            // 
            // label26
            // 
            this.label26.AutoSize = true;
            this.label26.Location = new System.Drawing.Point(75, 241);
            this.label26.Name = "label26";
            this.label26.Size = new System.Drawing.Size(106, 21);
            this.label26.TabIndex = 30;
            this.label26.Text = "Registration:";
            // 
            // textBox19
            // 
            this.textBox19.Location = new System.Drawing.Point(392, 265);
            this.textBox19.Name = "textBox19";
            this.textBox19.Size = new System.Drawing.Size(94, 27);
            this.textBox19.TabIndex = 29;
            // 
            // textBox18
            // 
            this.textBox18.Location = new System.Drawing.Point(288, 265);
            this.textBox18.Name = "textBox18";
            this.textBox18.Size = new System.Drawing.Size(94, 27);
            this.textBox18.TabIndex = 28;
            // 
            // textBox17
            // 
            this.textBox17.Location = new System.Drawing.Point(184, 265);
            this.textBox17.Name = "textBox17";
            this.textBox17.Size = new System.Drawing.Size(94, 27);
            this.textBox17.TabIndex = 27;
            // 
            // textBox16
            // 
            this.textBox16.Location = new System.Drawing.Point(80, 265);
            this.textBox16.Name = "textBox16";
            this.textBox16.Size = new System.Drawing.Size(94, 27);
            this.textBox16.TabIndex = 26;
            // 
            // textBox15
            // 
            this.textBox15.Location = new System.Drawing.Point(184, 152);
            this.textBox15.Name = "textBox15";
            this.textBox15.Size = new System.Drawing.Size(189, 27);
            this.textBox15.TabIndex = 23;
            // 
            // label25
            // 
            this.label25.AutoSize = true;
            this.label25.Location = new System.Drawing.Point(75, 156);
            this.label25.Name = "label25";
            this.label25.Size = new System.Drawing.Size(96, 21);
            this.label25.TabIndex = 22;
            this.label25.Text = "User name:";
            // 
            // header9
            // 
            this.header9.BackColor = System.Drawing.SystemColors.Control;
            this.header9.CausesValidation = false;
            this.header9.Description = "This wizard page is used to setup the FMM License. This License code may be found" +
                " inside the FMM CD case. If you are trying out the product as shareware click th" +
                "e Shareware button.";
            this.header9.Dock = System.Windows.Forms.DockStyle.Top;
            this.header9.Image = ((System.Drawing.Image)(resources.GetObject("header9.Image")));
            this.header9.Location = new System.Drawing.Point(0, 0);
            this.header9.Name = "header9";
            this.header9.Size = new System.Drawing.Size(676, 94);
            this.header9.TabIndex = 4;
            this.header9.Title = "FMM Registration";
            // 
            // WizardForm
            // 
            this.AutoScaleBaseSize = new System.Drawing.Size(8, 19);
            this.ClientSize = new System.Drawing.Size(676, 429);
            this.Controls.Add(this.FMMWizard);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MinimumSize = new System.Drawing.Size(698, 485);
            this.Name = "WizardForm";
            this.Text = "Flash Memory Manager 1.0 - Setup Wizard";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.FMMWizard.ResumeLayout(false);
            this.WelcomePage.ResumeLayout(false);
            this.infoContainer1.ResumeLayout(false);
            this.wpAlternateFinish.ResumeLayout(false);
            this.infoContainerOptionsPage.ResumeLayout(false);
            this.wpLocationOptions.ResumeLayout(false);
            this.wpLocationOptions.PerformLayout();
            this.wizardPagePassword.ResumeLayout(false);
            this.wizardPagePassword.PerformLayout();
            this.wpLicenceKey.ResumeLayout(false);
            this.wpLicenceKey.PerformLayout();
            this.FMMRegistration.ResumeLayout(false);
            this.FMMRegistration.PerformLayout();
            this.ResumeLayout(false);

		}
		#endregion

		/// <summary>
		/// The main entry point for the application.
		/// </summary>
		[STAThread]
		static void Main() 
		{
			Application.EnableVisualStyles();
			Application.DoEvents();
            Application.Run(new WizardForm());
		}

        public String LibraryLocation { get { return libraryLocation; } }
        public String Password { get { return textBoxPassword.Text; } }
             
		private void wizard1_CloseFromCancel(object sender, System.ComponentModel.CancelEventArgs e)
		{
			if (MessageBox.Show(this, 
				"Are you sure you want to close this?",
				"Wizard Cancelled", MessageBoxButtons.YesNo
				) == DialogResult.No)
			{
				e.Cancel = true;
			}
		}

       
		private void wpLicenceValidate_CloseFromBack(object sender, IDK.Gui.Wizard.PageEventArgs e)
		{
			//e.Page = OptionsPage;
		}

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void buttonShareware_Click(object sender, EventArgs e)
        {
            //SharewareForm form = new SharewareForm();
            //form.
        }

        private void infoContainer1_Load(object sender, EventArgs e)
        {

        }

        private void wizardPagePassword_Enter(object sender, EventArgs e)
        {

        }

        private void wizardPagePassword_Leave(object sender, EventArgs e)
        {
            

        }

        private void wizardPagePassword_CloseFromNext(object sender, IDK.Gui.Wizard.PageEventArgs e)
        {
            if (textBoxPassword.Text.Length == 0
                        && textBoxPasswordConfirm.Text.Length == 0)
            {
                if (MessageBox.Show("You have left the password blank. Anyone can change the FMM setting.\rDo you want to continue?",
                    "Flash Memory Manager 1.0", MessageBoxButtons.YesNo) == DialogResult.No)
                {
                    //Then ensure we DONT goto the NEXT PAGE
                    e.Page = wizardPagePassword;
                }
            }
            if (textBoxPassword.Text.Equals(textBoxPasswordConfirm.Text) == false)
            {
                MessageBox.Show("The passwords you entered do not match Please try again",
                    "Flash Memory Manager 1.0", MessageBoxButtons.OK, MessageBoxIcon.Question);
                //Then ensure we DONT goto the NEXT PAGE
                e.Page = wizardPagePassword;
            }

                        
        }

        
        private void buttonUseAsShareware_Click(object sender, EventArgs e)
        {
            //UsesAsSharewareForm form = new UsesAsSharewareForm();
            //form.ShowDialog();
            continueOn = true;
            buttonUseAsShareware.Enabled = false;
            FMMWizard.NextEnabled = true;
        }

        private void wpLicenceKey_ShowFromNext(object sender, EventArgs e)
        {
            if (continueOn)
            {
                FMMWizard.NextEnabled = true;
            }
            else
            {
                FMMWizard.NextEnabled = false;
            }
        }

        private void textBoxLic1_TextChanged(object sender, EventArgs e)
        {
            buttonUseAsShareware.Enabled = true;
            FMMWizard.NextEnabled = true;
        }

        private void textBoxLic2_TextChanged(object sender, EventArgs e)
        {
            buttonUseAsShareware.Enabled = true;
            FMMWizard.NextEnabled = true;
        }

        private void wpLicenceKey_CloseFromNext(object sender, IDK.Gui.Wizard.PageEventArgs e)
        {
            continueOn = true;
            if (continueOn == false)
            {
                MessageBox.Show("The passwords you entered do not match Please try again",
                        "Flash Memory Manager 1.0", MessageBoxButtons.OK, MessageBoxIcon.Question);
                //Then ensure we DONT goto the NEXT PAGE
                e.Page = wpLicenceKey;
            }
        }

        
        private void BedtimeWizard_Load(object sender, EventArgs e)
        {

        }

        private void buttonBrowse_Click(object sender, EventArgs e)
        {
            if (folderBrowserDialogLibraryLocation.ShowDialog() == DialogResult.OK)
            {
                if (libraryLocation != null)
                {
                    folderBrowserDialogLibraryLocation.SelectedPath = libraryLocation;
                }
                textBoxLibraryLocalion.Text = folderBrowserDialogLibraryLocation.SelectedPath;
                libraryLocation = folderBrowserDialogLibraryLocation.SelectedPath;
                
            }
        }

        private void textBoxLibraryLocalion_TextChanged(object sender, EventArgs e)
        {
            libraryLocation = textBoxLibraryLocalion.Text;
        }
	}
}
=======
using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;

namespace IDK.Gui
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
    public class WizardForm : System.Windows.Forms.Form
	{
		private IDK.Gui.Wizard.Wizard FMMWizard;
        private IDK.Gui.Wizard.WizardPage WelcomePage;
        private IDK.Gui.Wizard.InfoContainer infoContainer1;
        private System.Windows.Forms.Label label7;
		private IDK.Gui.Wizard.WizardPage wpAlternateFinish;
		private IDK.Gui.Wizard.InfoContainer infoContainerOptionsPage;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Label label11;
        private IDK.Gui.Wizard.WizardPage wizardPagePassword;
        private IDK.Gui.Wizard.Header header7;
        private TextBox textBoxPasswordConfirm;
        private Label label21;
        private TextBox textBoxPassword;
        private Label label22;
        private IDK.Gui.Wizard.WizardPage FMMRegistration;
        private Button buttonShareware;
        private Label label26;
        private TextBox textBox19;
        private TextBox textBox18;
        private TextBox textBox17;
        private TextBox textBox16;
        private TextBox textBox15;
        private Label label25;
        private IDK.Gui.Wizard.Header header9;
        private IDK.Gui.Wizard.WizardPage wpLicenceKey;
        private IDK.Gui.Wizard.Header header4;
        private Label label32;
        private Label label33;
        private TextBox textBoxLic2;
        private TextBox textBoxLic1;
        private TextBox textBoxName;
        private Button buttonUseAsShareware;
		private System.ComponentModel.IContainer components;
        private IDK.Gui.Wizard.WizardPage wpLocationOptions;
        private IDK.Gui.Wizard.Header header1;
        private Label label12;
        private bool continueOn = false;
        private TextBox textBoxLibraryLocalion;
        private Button buttonBrowse;
        private FolderBrowserDialog folderBrowserDialogLibraryLocation;
        private String shutdownOption = null;
        private String libraryLocation = null;
        
		public WizardForm()
		{
			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();
            this.TopMost = true;
			//
			// TODO: Add any constructor code after InitializeComponent call
			//
		}

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		protected override void Dispose( bool disposing )
		{
			if( disposing )
			{
				if (components != null) 
				{
					components.Dispose();
				}
			}
			base.Dispose( disposing );
		}

		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(WizardForm));
            this.FMMWizard = new IDK.Gui.Wizard.Wizard();
            this.WelcomePage = new IDK.Gui.Wizard.WizardPage();
            this.infoContainer1 = new IDK.Gui.Wizard.InfoContainer();
            this.label11 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.wpAlternateFinish = new IDK.Gui.Wizard.WizardPage();
            this.infoContainerOptionsPage = new IDK.Gui.Wizard.InfoContainer();
            this.label9 = new System.Windows.Forms.Label();
            this.wpLocationOptions = new IDK.Gui.Wizard.WizardPage();
            this.buttonBrowse = new System.Windows.Forms.Button();
            this.textBoxLibraryLocalion = new System.Windows.Forms.TextBox();
            this.label12 = new System.Windows.Forms.Label();
            this.header1 = new IDK.Gui.Wizard.Header();
            this.wizardPagePassword = new IDK.Gui.Wizard.WizardPage();
            this.textBoxPasswordConfirm = new System.Windows.Forms.TextBox();
            this.label21 = new System.Windows.Forms.Label();
            this.textBoxPassword = new System.Windows.Forms.TextBox();
            this.label22 = new System.Windows.Forms.Label();
            this.header7 = new IDK.Gui.Wizard.Header();
            this.wpLicenceKey = new IDK.Gui.Wizard.WizardPage();
            this.buttonUseAsShareware = new System.Windows.Forms.Button();
            this.label33 = new System.Windows.Forms.Label();
            this.textBoxLic2 = new System.Windows.Forms.TextBox();
            this.textBoxLic1 = new System.Windows.Forms.TextBox();
            this.textBoxName = new System.Windows.Forms.TextBox();
            this.label32 = new System.Windows.Forms.Label();
            this.header4 = new IDK.Gui.Wizard.Header();
            this.FMMRegistration = new IDK.Gui.Wizard.WizardPage();
            this.buttonShareware = new System.Windows.Forms.Button();
            this.label26 = new System.Windows.Forms.Label();
            this.textBox19 = new System.Windows.Forms.TextBox();
            this.textBox18 = new System.Windows.Forms.TextBox();
            this.textBox17 = new System.Windows.Forms.TextBox();
            this.textBox16 = new System.Windows.Forms.TextBox();
            this.textBox15 = new System.Windows.Forms.TextBox();
            this.label25 = new System.Windows.Forms.Label();
            this.header9 = new IDK.Gui.Wizard.Header();
            this.folderBrowserDialogLibraryLocation = new System.Windows.Forms.FolderBrowserDialog();
            this.FMMWizard.SuspendLayout();
            this.WelcomePage.SuspendLayout();
            this.infoContainer1.SuspendLayout();
            this.wpAlternateFinish.SuspendLayout();
            this.infoContainerOptionsPage.SuspendLayout();
            this.wpLocationOptions.SuspendLayout();
            this.wizardPagePassword.SuspendLayout();
            this.wpLicenceKey.SuspendLayout();
            this.FMMRegistration.SuspendLayout();
            this.SuspendLayout();
            // 
            // FMMWizard
            // 
            this.FMMWizard.Controls.Add(this.WelcomePage);
            this.FMMWizard.Controls.Add(this.wpAlternateFinish);
            this.FMMWizard.Controls.Add(this.wpLocationOptions);
            this.FMMWizard.Controls.Add(this.wizardPagePassword);
            this.FMMWizard.Controls.Add(this.wpLicenceKey);
            this.FMMWizard.Controls.Add(this.FMMRegistration);
            this.FMMWizard.Dock = System.Windows.Forms.DockStyle.Fill;
            this.FMMWizard.Font = new System.Drawing.Font("Tahoma", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.FMMWizard.Location = new System.Drawing.Point(0, 0);
            this.FMMWizard.Name = "FMMWizard";
            this.FMMWizard.Pages.AddRange(new IDK.Gui.Wizard.WizardPage[] {
            this.WelcomePage,
            this.wpLicenceKey,
            this.wizardPagePassword,
            this.wpLocationOptions,
            this.wpAlternateFinish});
            this.FMMWizard.Size = new System.Drawing.Size(676, 429);
            this.FMMWizard.TabIndex = 0;
            this.FMMWizard.CloseFromCancel += new System.ComponentModel.CancelEventHandler(this.wizard1_CloseFromCancel);
            // 
            // WelcomePage
            // 
            this.WelcomePage.Controls.Add(this.infoContainer1);
            this.WelcomePage.Dock = System.Windows.Forms.DockStyle.Fill;
            this.WelcomePage.IsFinishPage = false;
            this.WelcomePage.Location = new System.Drawing.Point(0, 0);
            this.WelcomePage.Name = "WelcomePage";
            this.WelcomePage.Size = new System.Drawing.Size(676, 358);
            this.WelcomePage.TabIndex = 1;
            // 
            // infoContainer1
            // 
            this.infoContainer1.BackColor = System.Drawing.Color.White;
            this.infoContainer1.Controls.Add(this.label11);
            this.infoContainer1.Controls.Add(this.label7);
            this.infoContainer1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.infoContainer1.Image = global::IDK.Gui.Properties.Resources.disks;
            this.infoContainer1.Location = new System.Drawing.Point(0, 0);
            this.infoContainer1.Name = "infoContainer1";
            this.infoContainer1.PageTitle = "Welcome to the Flash Memory Manager 1.0 Setup Wizard";
            this.infoContainer1.Size = new System.Drawing.Size(676, 358);
            this.infoContainer1.TabIndex = 0;
            // 
            // label11
            // 
            this.label11.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.label11.FlatStyle = System.Windows.Forms.FlatStyle.System;
            this.label11.Location = new System.Drawing.Point(275, 82);
            this.label11.Name = "label11";
            this.label11.Size = new System.Drawing.Size(394, 45);
            this.label11.TabIndex = 8;
            this.label11.Text = "This wizard helps you setup Flash Memory Manager on your system. ";
            // 
            // label7
            // 
            this.label7.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.label7.FlatStyle = System.Windows.Forms.FlatStyle.System;
            this.label7.Location = new System.Drawing.Point(291, 399);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(362, 41);
            this.label7.TabIndex = 10;
            this.label7.Text = "To Continue click next.";
            // 
            // wpAlternateFinish
            // 
            this.wpAlternateFinish.Controls.Add(this.infoContainerOptionsPage);
            this.wpAlternateFinish.Dock = System.Windows.Forms.DockStyle.Fill;
            this.wpAlternateFinish.IsFinishPage = true;
            this.wpAlternateFinish.Location = new System.Drawing.Point(0, 0);
            this.wpAlternateFinish.Name = "wpAlternateFinish";
            this.wpAlternateFinish.Size = new System.Drawing.Size(685, 377);
            this.wpAlternateFinish.TabIndex = 8;
            // 
            // infoContainerOptionsPage
            // 
            this.infoContainerOptionsPage.BackColor = System.Drawing.Color.White;
            this.infoContainerOptionsPage.Controls.Add(this.label9);
            this.infoContainerOptionsPage.Dock = System.Windows.Forms.DockStyle.Fill;
            this.infoContainerOptionsPage.Image = ((System.Drawing.Image)(resources.GetObject("infoContainerOptionsPage.Image")));
            this.infoContainerOptionsPage.Location = new System.Drawing.Point(0, 0);
            this.infoContainerOptionsPage.Name = "infoContainerOptionsPage";
            this.infoContainerOptionsPage.PageTitle = "Final Setup Completed Successfully!";
            this.infoContainerOptionsPage.Size = new System.Drawing.Size(685, 377);
            this.infoContainerOptionsPage.TabIndex = 0;
            // 
            // label9
            // 
            this.label9.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.label9.FlatStyle = System.Windows.Forms.FlatStyle.System;
            this.label9.Location = new System.Drawing.Point(267, 76);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(411, 107);
            this.label9.TabIndex = 12;
            this.label9.Text = resources.GetString("label9.Text");
            // 
            // wpLocationOptions
            // 
            this.wpLocationOptions.Controls.Add(this.buttonBrowse);
            this.wpLocationOptions.Controls.Add(this.textBoxLibraryLocalion);
            this.wpLocationOptions.Controls.Add(this.label12);
            this.wpLocationOptions.Controls.Add(this.header1);
            this.wpLocationOptions.Dock = System.Windows.Forms.DockStyle.Fill;
            this.wpLocationOptions.IsFinishPage = false;
            this.wpLocationOptions.Location = new System.Drawing.Point(0, 0);
            this.wpLocationOptions.Name = "wpLocationOptions";
            this.wpLocationOptions.Size = new System.Drawing.Size(685, 377);
            this.wpLocationOptions.TabIndex = 18;
            // 
            // buttonBrowse
            // 
            this.buttonBrowse.Location = new System.Drawing.Point(542, 265);
            this.buttonBrowse.Name = "buttonBrowse";
            this.buttonBrowse.Size = new System.Drawing.Size(120, 33);
            this.buttonBrowse.TabIndex = 39;
            this.buttonBrowse.Text = "Browse";
            this.buttonBrowse.UseVisualStyleBackColor = true;
            this.buttonBrowse.Click += new System.EventHandler(this.buttonBrowse_Click);
            // 
            // textBoxLibraryLocalion
            // 
            this.textBoxLibraryLocalion.Location = new System.Drawing.Point(26, 225);
            this.textBoxLibraryLocalion.Name = "textBoxLibraryLocalion";
            this.textBoxLibraryLocalion.Size = new System.Drawing.Size(636, 27);
            this.textBoxLibraryLocalion.TabIndex = 15;
            this.textBoxLibraryLocalion.TextChanged += new System.EventHandler(this.textBoxLibraryLocalion_TextChanged);
            // 
            // label12
            // 
            this.label12.AutoSize = true;
            this.label12.Location = new System.Drawing.Point(21, 202);
            this.label12.Name = "label12";
            this.label12.Size = new System.Drawing.Size(133, 21);
            this.label12.TabIndex = 14;
            this.label12.Text = "Library Location:";
            // 
            // header1
            // 
            this.header1.BackColor = System.Drawing.SystemColors.Control;
            this.header1.CausesValidation = false;
            this.header1.Description = "This page allows you to set the action FMM carry out when FMM is reached.";
            this.header1.Dock = System.Windows.Forms.DockStyle.Top;
            this.header1.Image = ((System.Drawing.Image)(resources.GetObject("header1.Image")));
            this.header1.Location = new System.Drawing.Point(0, 0);
            this.header1.Name = "header1";
            this.header1.Size = new System.Drawing.Size(685, 94);
            this.header1.TabIndex = 5;
            this.header1.Title = "Library Location";
            // 
            // wizardPagePassword
            // 
            this.wizardPagePassword.Controls.Add(this.textBoxPasswordConfirm);
            this.wizardPagePassword.Controls.Add(this.label21);
            this.wizardPagePassword.Controls.Add(this.textBoxPassword);
            this.wizardPagePassword.Controls.Add(this.label22);
            this.wizardPagePassword.Controls.Add(this.header7);
            this.wizardPagePassword.Dock = System.Windows.Forms.DockStyle.Fill;
            this.wizardPagePassword.IsFinishPage = false;
            this.wizardPagePassword.Location = new System.Drawing.Point(0, 0);
            this.wizardPagePassword.Name = "wizardPagePassword";
            this.wizardPagePassword.Size = new System.Drawing.Size(685, 377);
            this.wizardPagePassword.TabIndex = 12;
            this.wizardPagePassword.CloseFromNext += new IDK.Gui.Wizard.PageEventHandler(this.wizardPagePassword_CloseFromNext);
            // 
            // textBoxPasswordConfirm
            // 
            this.textBoxPasswordConfirm.Location = new System.Drawing.Point(442, 174);
            this.textBoxPasswordConfirm.Name = "textBoxPasswordConfirm";
            this.textBoxPasswordConfirm.PasswordChar = '*';
            this.textBoxPasswordConfirm.Size = new System.Drawing.Size(188, 27);
            this.textBoxPasswordConfirm.TabIndex = 29;
            // 
            // label21
            // 
            this.label21.AutoSize = true;
            this.label21.Location = new System.Drawing.Point(355, 178);
            this.label21.Name = "label21";
            this.label21.Size = new System.Drawing.Size(73, 21);
            this.label21.TabIndex = 28;
            this.label21.Text = "Confirm:";
            // 
            // textBoxPassword
            // 
            this.textBoxPassword.Location = new System.Drawing.Point(157, 174);
            this.textBoxPassword.Name = "textBoxPassword";
            this.textBoxPassword.PasswordChar = '*';
            this.textBoxPassword.Size = new System.Drawing.Size(189, 27);
            this.textBoxPassword.TabIndex = 27;
            this.textBoxPassword.UseSystemPasswordChar = true;
            // 
            // label22
            // 
            this.label22.AutoSize = true;
            this.label22.Location = new System.Drawing.Point(56, 178);
            this.label22.Name = "label22";
            this.label22.Size = new System.Drawing.Size(87, 21);
            this.label22.TabIndex = 26;
            this.label22.Text = "Password:";
            // 
            // header7
            // 
            this.header7.BackColor = System.Drawing.SystemColors.Control;
            this.header7.CausesValidation = false;
            this.header7.Description = "Use this page to setup a password so only you can change the FMMs and other setti" +
                "ng in the FMM application. ";
            this.header7.Dock = System.Windows.Forms.DockStyle.Top;
            this.header7.Image = ((System.Drawing.Image)(resources.GetObject("header7.Image")));
            this.header7.Location = new System.Drawing.Point(0, 0);
            this.header7.Name = "header7";
            this.header7.Size = new System.Drawing.Size(685, 94);
            this.header7.TabIndex = 2;
            this.header7.Title = "Flash Memory Manager password";
            // 
            // wpLicenceKey
            // 
            this.wpLicenceKey.Controls.Add(this.buttonUseAsShareware);
            this.wpLicenceKey.Controls.Add(this.label33);
            this.wpLicenceKey.Controls.Add(this.textBoxLic2);
            this.wpLicenceKey.Controls.Add(this.textBoxLic1);
            this.wpLicenceKey.Controls.Add(this.textBoxName);
            this.wpLicenceKey.Controls.Add(this.label32);
            this.wpLicenceKey.Controls.Add(this.header4);
            this.wpLicenceKey.Dock = System.Windows.Forms.DockStyle.Fill;
            this.wpLicenceKey.IsFinishPage = false;
            this.wpLicenceKey.Location = new System.Drawing.Point(0, 0);
            this.wpLicenceKey.Name = "wpLicenceKey";
            this.wpLicenceKey.Size = new System.Drawing.Size(685, 377);
            this.wpLicenceKey.TabIndex = 17;
            this.wpLicenceKey.CloseFromNext += new IDK.Gui.Wizard.PageEventHandler(this.wpLicenceKey_CloseFromNext);
            this.wpLicenceKey.ShowFromNext += new System.EventHandler(this.wpLicenceKey_ShowFromNext);
            // 
            // buttonUseAsShareware
            // 
            this.buttonUseAsShareware.Location = new System.Drawing.Point(486, 330);
            this.buttonUseAsShareware.Name = "buttonUseAsShareware";
            this.buttonUseAsShareware.Size = new System.Drawing.Size(157, 34);
            this.buttonUseAsShareware.TabIndex = 14;
            this.buttonUseAsShareware.Text = "Shareware";
            this.buttonUseAsShareware.UseVisualStyleBackColor = true;
            this.buttonUseAsShareware.Click += new System.EventHandler(this.buttonUseAsShareware_Click);
            // 
            // label33
            // 
            this.label33.AutoSize = true;
            this.label33.Location = new System.Drawing.Point(56, 284);
            this.label33.Name = "label33";
            this.label33.Size = new System.Drawing.Size(103, 21);
            this.label33.TabIndex = 13;
            this.label33.Text = "Licence Key:";
            // 
            // textBoxLic2
            // 
            this.textBoxLic2.Location = new System.Drawing.Point(282, 279);
            this.textBoxLic2.Name = "textBoxLic2";
            this.textBoxLic2.Size = new System.Drawing.Size(129, 27);
            this.textBoxLic2.TabIndex = 9;
            this.textBoxLic2.TextChanged += new System.EventHandler(this.textBoxLic2_TextChanged);
            // 
            // textBoxLic1
            // 
            this.textBoxLic1.Location = new System.Drawing.Point(173, 279);
            this.textBoxLic1.Name = "textBoxLic1";
            this.textBoxLic1.Size = new System.Drawing.Size(83, 27);
            this.textBoxLic1.TabIndex = 8;
            this.textBoxLic1.TextChanged += new System.EventHandler(this.textBoxLic1_TextChanged);
            // 
            // textBoxName
            // 
            this.textBoxName.Location = new System.Drawing.Point(173, 133);
            this.textBoxName.Name = "textBoxName";
            this.textBoxName.Size = new System.Drawing.Size(195, 27);
            this.textBoxName.TabIndex = 7;
            // 
            // label32
            // 
            this.label32.AutoSize = true;
            this.label32.Location = new System.Drawing.Point(64, 137);
            this.label32.Name = "label32";
            this.label32.Size = new System.Drawing.Size(96, 21);
            this.label32.TabIndex = 6;
            this.label32.Text = "Your name:";
            // 
            // header4
            // 
            this.header4.BackColor = System.Drawing.SystemColors.Control;
            this.header4.CausesValidation = false;
            this.header4.Description = "If you have purchased the softwate please enter your licence key, otherwise you h" +
                "ave 30 days trial period in which you can use the software.";
            this.header4.Dock = System.Windows.Forms.DockStyle.Top;
            this.header4.Image = ((System.Drawing.Image)(resources.GetObject("header4.Image")));
            this.header4.Location = new System.Drawing.Point(0, 0);
            this.header4.Name = "header4";
            this.header4.Size = new System.Drawing.Size(685, 94);
            this.header4.TabIndex = 5;
            this.header4.Title = "Licence Key";
            // 
            // FMMRegistration
            // 
            this.FMMRegistration.Controls.Add(this.buttonShareware);
            this.FMMRegistration.Controls.Add(this.label26);
            this.FMMRegistration.Controls.Add(this.textBox19);
            this.FMMRegistration.Controls.Add(this.textBox18);
            this.FMMRegistration.Controls.Add(this.textBox17);
            this.FMMRegistration.Controls.Add(this.textBox16);
            this.FMMRegistration.Controls.Add(this.textBox15);
            this.FMMRegistration.Controls.Add(this.label25);
            this.FMMRegistration.Controls.Add(this.header9);
            this.FMMRegistration.Dock = System.Windows.Forms.DockStyle.Fill;
            this.FMMRegistration.IsFinishPage = false;
            this.FMMRegistration.Location = new System.Drawing.Point(0, 0);
            this.FMMRegistration.Name = "FMMRegistration";
            this.FMMRegistration.Size = new System.Drawing.Size(676, 429);
            this.FMMRegistration.TabIndex = 14;
            // 
            // buttonShareware
            // 
            this.buttonShareware.Location = new System.Drawing.Point(542, 330);
            this.buttonShareware.Name = "buttonShareware";
            this.buttonShareware.Size = new System.Drawing.Size(120, 34);
            this.buttonShareware.TabIndex = 31;
            this.buttonShareware.Text = "Shareware";
            this.buttonShareware.UseVisualStyleBackColor = true;
            this.buttonShareware.Click += new System.EventHandler(this.buttonShareware_Click);
            // 
            // label26
            // 
            this.label26.AutoSize = true;
            this.label26.Location = new System.Drawing.Point(75, 241);
            this.label26.Name = "label26";
            this.label26.Size = new System.Drawing.Size(106, 21);
            this.label26.TabIndex = 30;
            this.label26.Text = "Registration:";
            // 
            // textBox19
            // 
            this.textBox19.Location = new System.Drawing.Point(392, 265);
            this.textBox19.Name = "textBox19";
            this.textBox19.Size = new System.Drawing.Size(94, 27);
            this.textBox19.TabIndex = 29;
            // 
            // textBox18
            // 
            this.textBox18.Location = new System.Drawing.Point(288, 265);
            this.textBox18.Name = "textBox18";
            this.textBox18.Size = new System.Drawing.Size(94, 27);
            this.textBox18.TabIndex = 28;
            // 
            // textBox17
            // 
            this.textBox17.Location = new System.Drawing.Point(184, 265);
            this.textBox17.Name = "textBox17";
            this.textBox17.Size = new System.Drawing.Size(94, 27);
            this.textBox17.TabIndex = 27;
            // 
            // textBox16
            // 
            this.textBox16.Location = new System.Drawing.Point(80, 265);
            this.textBox16.Name = "textBox16";
            this.textBox16.Size = new System.Drawing.Size(94, 27);
            this.textBox16.TabIndex = 26;
            // 
            // textBox15
            // 
            this.textBox15.Location = new System.Drawing.Point(184, 152);
            this.textBox15.Name = "textBox15";
            this.textBox15.Size = new System.Drawing.Size(189, 27);
            this.textBox15.TabIndex = 23;
            // 
            // label25
            // 
            this.label25.AutoSize = true;
            this.label25.Location = new System.Drawing.Point(75, 156);
            this.label25.Name = "label25";
            this.label25.Size = new System.Drawing.Size(96, 21);
            this.label25.TabIndex = 22;
            this.label25.Text = "User name:";
            // 
            // header9
            // 
            this.header9.BackColor = System.Drawing.SystemColors.Control;
            this.header9.CausesValidation = false;
            this.header9.Description = "This wizard page is used to setup the FMM License. This License code may be found" +
                " inside the FMM CD case. If you are trying out the product as shareware click th" +
                "e Shareware button.";
            this.header9.Dock = System.Windows.Forms.DockStyle.Top;
            this.header9.Image = ((System.Drawing.Image)(resources.GetObject("header9.Image")));
            this.header9.Location = new System.Drawing.Point(0, 0);
            this.header9.Name = "header9";
            this.header9.Size = new System.Drawing.Size(676, 94);
            this.header9.TabIndex = 4;
            this.header9.Title = "FMM Registration";
            // 
            // WizardForm
            // 
            this.AutoScaleBaseSize = new System.Drawing.Size(8, 19);
            this.ClientSize = new System.Drawing.Size(676, 429);
            this.Controls.Add(this.FMMWizard);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MinimumSize = new System.Drawing.Size(698, 485);
            this.Name = "WizardForm";
            this.Text = "Flash Memory Manager 1.0 - Setup Wizard";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.FMMWizard.ResumeLayout(false);
            this.WelcomePage.ResumeLayout(false);
            this.infoContainer1.ResumeLayout(false);
            this.wpAlternateFinish.ResumeLayout(false);
            this.infoContainerOptionsPage.ResumeLayout(false);
            this.wpLocationOptions.ResumeLayout(false);
            this.wpLocationOptions.PerformLayout();
            this.wizardPagePassword.ResumeLayout(false);
            this.wizardPagePassword.PerformLayout();
            this.wpLicenceKey.ResumeLayout(false);
            this.wpLicenceKey.PerformLayout();
            this.FMMRegistration.ResumeLayout(false);
            this.FMMRegistration.PerformLayout();
            this.ResumeLayout(false);

		}
		#endregion

		/// <summary>
		/// The main entry point for the application.
		/// </summary>
		[STAThread]
		static void Main() 
		{
			Application.EnableVisualStyles();
			Application.DoEvents();
            Application.Run(new WizardForm());
		}

        public String LibraryLocation { get { return libraryLocation; } }
        public String Password { get { return textBoxPassword.Text; } }
             
		private void wizard1_CloseFromCancel(object sender, System.ComponentModel.CancelEventArgs e)
		{
			if (MessageBox.Show(this, 
				"Are you sure you want to close this?",
				"Wizard Cancelled", MessageBoxButtons.YesNo
				) == DialogResult.No)
			{
				e.Cancel = true;
			}
		}

       
		private void wpLicenceValidate_CloseFromBack(object sender, IDK.Gui.Wizard.PageEventArgs e)
		{
			//e.Page = OptionsPage;
		}

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void buttonShareware_Click(object sender, EventArgs e)
        {
            //SharewareForm form = new SharewareForm();
            //form.
        }

        private void infoContainer1_Load(object sender, EventArgs e)
        {

        }

        private void wizardPagePassword_Enter(object sender, EventArgs e)
        {

        }

        private void wizardPagePassword_Leave(object sender, EventArgs e)
        {
            

        }

        private void wizardPagePassword_CloseFromNext(object sender, IDK.Gui.Wizard.PageEventArgs e)
        {
            if (textBoxPassword.Text.Length == 0
                        && textBoxPasswordConfirm.Text.Length == 0)
            {
                if (MessageBox.Show("You have left the password blank. Anyone can change the FMM setting.\rDo you want to continue?",
                    "Flash Memory Manager 1.0", MessageBoxButtons.YesNo) == DialogResult.No)
                {
                    //Then ensure we DONT goto the NEXT PAGE
                    e.Page = wizardPagePassword;
                }
            }
            if (textBoxPassword.Text.Equals(textBoxPasswordConfirm.Text) == false)
            {
                MessageBox.Show("The passwords you entered do not match Please try again",
                    "Flash Memory Manager 1.0", MessageBoxButtons.OK, MessageBoxIcon.Question);
                //Then ensure we DONT goto the NEXT PAGE
                e.Page = wizardPagePassword;
            }

                        
        }

        
        private void buttonUseAsShareware_Click(object sender, EventArgs e)
        {
            //UsesAsSharewareForm form = new UsesAsSharewareForm();
            //form.ShowDialog();
            continueOn = true;
            buttonUseAsShareware.Enabled = false;
            FMMWizard.NextEnabled = true;
        }

        private void wpLicenceKey_ShowFromNext(object sender, EventArgs e)
        {
            if (continueOn)
            {
                FMMWizard.NextEnabled = true;
            }
            else
            {
                FMMWizard.NextEnabled = false;
            }
        }

        private void textBoxLic1_TextChanged(object sender, EventArgs e)
        {
            buttonUseAsShareware.Enabled = true;
            FMMWizard.NextEnabled = true;
        }

        private void textBoxLic2_TextChanged(object sender, EventArgs e)
        {
            buttonUseAsShareware.Enabled = true;
            FMMWizard.NextEnabled = true;
        }

        private void wpLicenceKey_CloseFromNext(object sender, IDK.Gui.Wizard.PageEventArgs e)
        {
            continueOn = true;
            if (continueOn == false)
            {
                MessageBox.Show("The passwords you entered do not match Please try again",
                        "Flash Memory Manager 1.0", MessageBoxButtons.OK, MessageBoxIcon.Question);
                //Then ensure we DONT goto the NEXT PAGE
                e.Page = wpLicenceKey;
            }
        }

        
        private void BedtimeWizard_Load(object sender, EventArgs e)
        {

        }

        private void buttonBrowse_Click(object sender, EventArgs e)
        {
            if (folderBrowserDialogLibraryLocation.ShowDialog() == DialogResult.OK)
            {
                if (libraryLocation != null)
                {
                    folderBrowserDialogLibraryLocation.SelectedPath = libraryLocation;
                }
                textBoxLibraryLocalion.Text = folderBrowserDialogLibraryLocation.SelectedPath;
                libraryLocation = folderBrowserDialogLibraryLocation.SelectedPath;
                
            }
        }

        private void textBoxLibraryLocalion_TextChanged(object sender, EventArgs e)
        {
            libraryLocation = textBoxLibraryLocalion.Text;
        }
	}
}
>>>>>>> 291c508aa47ea3e34a225d431ef34192e909c4ee
