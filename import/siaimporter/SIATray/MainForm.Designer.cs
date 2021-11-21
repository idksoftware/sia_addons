namespace SIATray
{
    partial class MainForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(MainForm));
            this.notifyIcon = new System.Windows.Forms.NotifyIcon(this.components);
            this.contextMenuStrip = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.aboutToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.importQueueToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.quickBackupToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.quickRestoreToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.propertiesToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.exitToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.buttonBackup = new System.Windows.Forms.Button();
            this.labelImagesFound = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.labelCurrentFile = new System.Windows.Forms.Label();
            this.labelPath = new System.Windows.Forms.Label();
            this.buttonCancel = new System.Windows.Forms.Button();
            this.labelSearchingFolder = new System.Windows.Forms.Label();
            this.progressBar = new System.Windows.Forms.ProgressBar();
            this.timer = new System.Windows.Forms.Timer(this.components);
            this.backupTimer = new System.Windows.Forms.Timer(this.components);
            this.contextMenuStrip.SuspendLayout();
            this.SuspendLayout();
            // 
            // notifyIcon
            // 
            this.notifyIcon.ContextMenuStrip = this.contextMenuStrip;
            this.notifyIcon.Icon = ((System.Drawing.Icon)(resources.GetObject("notifyIcon.Icon")));
            this.notifyIcon.Text = "Flash Memory Manager Client 1.0";
            this.notifyIcon.Visible = true;
            this.notifyIcon.BalloonTipClicked += new System.EventHandler(this.notifyIcon_BalloonTipClicked);
            // 
            // contextMenuStrip
            // 
            this.contextMenuStrip.ImageScalingSize = new System.Drawing.Size(24, 24);
            this.contextMenuStrip.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.aboutToolStripMenuItem,
            this.importQueueToolStripMenuItem,
            this.quickBackupToolStripMenuItem,
            this.quickRestoreToolStripMenuItem,
            this.propertiesToolStripMenuItem,
            this.toolStripSeparator1,
            this.exitToolStripMenuItem});
            this.contextMenuStrip.Name = "contextMenuStrip";
            this.contextMenuStrip.Size = new System.Drawing.Size(241, 235);
            this.contextMenuStrip.Opening += new System.ComponentModel.CancelEventHandler(this.contextMenuStrip_Opening);
            // 
            // aboutToolStripMenuItem
            // 
            this.aboutToolStripMenuItem.Name = "aboutToolStripMenuItem";
            this.aboutToolStripMenuItem.Size = new System.Drawing.Size(240, 32);
            this.aboutToolStripMenuItem.Text = "About";
            this.aboutToolStripMenuItem.Click += new System.EventHandler(this.aboutToolStripMenuItem_Click);
            // 
            // importQueueToolStripMenuItem
            // 
            this.importQueueToolStripMenuItem.Name = "importQueueToolStripMenuItem";
            this.importQueueToolStripMenuItem.Size = new System.Drawing.Size(240, 32);
            this.importQueueToolStripMenuItem.Text = "Import Queue";
            this.importQueueToolStripMenuItem.Click += new System.EventHandler(this.importQueueToolStripMenuItem_Click);
            // 
            // quickBackupToolStripMenuItem
            // 
            this.quickBackupToolStripMenuItem.Name = "quickBackupToolStripMenuItem";
            this.quickBackupToolStripMenuItem.Size = new System.Drawing.Size(240, 32);
            this.quickBackupToolStripMenuItem.Text = "Flash Import";
            this.quickBackupToolStripMenuItem.Click += new System.EventHandler(this.flashImportToolStripMenuItem_Click);
            // 
            // quickRestoreToolStripMenuItem
            // 
            this.quickRestoreToolStripMenuItem.Name = "quickRestoreToolStripMenuItem";
            this.quickRestoreToolStripMenuItem.Size = new System.Drawing.Size(240, 32);
            this.quickRestoreToolStripMenuItem.Text = "Folder Import";
            this.quickRestoreToolStripMenuItem.Click += new System.EventHandler(this.FolderImportToolStripMenuItem_Click);
            // 
            // propertiesToolStripMenuItem
            // 
            this.propertiesToolStripMenuItem.Name = "propertiesToolStripMenuItem";
            this.propertiesToolStripMenuItem.Size = new System.Drawing.Size(240, 32);
            this.propertiesToolStripMenuItem.Text = "Properties";
            this.propertiesToolStripMenuItem.Click += new System.EventHandler(this.propertiesToolStripMenuItem_Click);
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(237, 6);
            // 
            // exitToolStripMenuItem
            // 
            this.exitToolStripMenuItem.Name = "exitToolStripMenuItem";
            this.exitToolStripMenuItem.Size = new System.Drawing.Size(240, 32);
            this.exitToolStripMenuItem.Text = "Exit";
            this.exitToolStripMenuItem.Click += new System.EventHandler(this.exitToolStripMenuItem_Click);
            // 
            // buttonBackup
            // 
            this.buttonBackup.Location = new System.Drawing.Point(550, 302);
            this.buttonBackup.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.buttonBackup.Name = "buttonBackup";
            this.buttonBackup.Size = new System.Drawing.Size(124, 35);
            this.buttonBackup.TabIndex = 40;
            this.buttonBackup.Text = "&Backup";
            this.buttonBackup.UseVisualStyleBackColor = true;
            this.buttonBackup.Visible = false;
            this.buttonBackup.Click += new System.EventHandler(this.buttonBackup_Click);
            // 
            // labelImagesFound
            // 
            this.labelImagesFound.AutoSize = true;
            this.labelImagesFound.Location = new System.Drawing.Point(204, 18);
            this.labelImagesFound.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.labelImagesFound.Name = "labelImagesFound";
            this.labelImagesFound.Size = new System.Drawing.Size(27, 20);
            this.labelImagesFound.TabIndex = 39;
            this.labelImagesFound.Text = "21";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(84, 18);
            this.label1.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(111, 20);
            this.label1.TabIndex = 38;
            this.label1.Text = "Images found:";
            // 
            // labelCurrentFile
            // 
            this.labelCurrentFile.AutoSize = true;
            this.labelCurrentFile.Location = new System.Drawing.Point(18, 122);
            this.labelCurrentFile.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.labelCurrentFile.Name = "labelCurrentFile";
            this.labelCurrentFile.Size = new System.Drawing.Size(124, 20);
            this.labelCurrentFile.TabIndex = 37;
            this.labelCurrentFile.Text = "DSC_9897.NEF";
            // 
            // labelPath
            // 
            this.labelPath.AutoSize = true;
            this.labelPath.Location = new System.Drawing.Point(18, 72);
            this.labelPath.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.labelPath.Name = "labelPath";
            this.labelPath.Size = new System.Drawing.Size(472, 20);
            this.labelPath.TabIndex = 36;
            this.labelPath.Text = "D:\\Users\\501726576\\My Documents\\My Pictures\\DCIM\\00000002";
            // 
            // buttonCancel
            // 
            this.buttonCancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.buttonCancel.Location = new System.Drawing.Point(458, 357);
            this.buttonCancel.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.buttonCancel.Name = "buttonCancel";
            this.buttonCancel.Size = new System.Drawing.Size(124, 35);
            this.buttonCancel.TabIndex = 35;
            this.buttonCancel.Text = "&Cancel";
            this.buttonCancel.UseVisualStyleBackColor = true;
            this.buttonCancel.Click += new System.EventHandler(this.buttonCancel_Click);
            // 
            // labelSearchingFolder
            // 
            this.labelSearchingFolder.AutoSize = true;
            this.labelSearchingFolder.Location = new System.Drawing.Point(76, 48);
            this.labelSearchingFolder.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.labelSearchingFolder.Name = "labelSearchingFolder";
            this.labelSearchingFolder.Size = new System.Drawing.Size(129, 20);
            this.labelSearchingFolder.TabIndex = 33;
            this.labelSearchingFolder.Text = "Searching folder:";
            // 
            // progressBar
            // 
            this.progressBar.Location = new System.Drawing.Point(22, 97);
            this.progressBar.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.progressBar.Name = "progressBar";
            this.progressBar.Size = new System.Drawing.Size(492, 20);
            this.progressBar.TabIndex = 32;
            // 
            // timer
            // 
            this.timer.Tick += new System.EventHandler(this.timer_Tick);
            // 
            // backupTimer
            // 
            this.backupTimer.Tick += new System.EventHandler(this.backupTimer_Tick);
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(723, 512);
            this.Controls.Add(this.buttonBackup);
            this.Controls.Add(this.labelImagesFound);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.labelCurrentFile);
            this.Controls.Add(this.labelPath);
            this.Controls.Add(this.buttonCancel);
            this.Controls.Add(this.labelSearchingFolder);
            this.Controls.Add(this.progressBar);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.Name = "MainForm";
            this.ShowInTaskbar = false;
            this.Text = "Flash Memory Manager - New volume";
            this.WindowState = System.Windows.Forms.FormWindowState.Minimized;
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.MainForm_FormClosing);
            this.contextMenuStrip.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.NotifyIcon notifyIcon;
        private System.Windows.Forms.ContextMenuStrip contextMenuStrip;
        private System.Windows.Forms.Button buttonBackup;
        private System.Windows.Forms.Label labelImagesFound;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label labelCurrentFile;
        private System.Windows.Forms.Label labelPath;
        private System.Windows.Forms.Button buttonCancel;
        private System.Windows.Forms.Label labelSearchingFolder;
        private System.Windows.Forms.ProgressBar progressBar;
        private System.Windows.Forms.Timer timer;
        private System.Windows.Forms.ToolStripMenuItem aboutToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem propertiesToolStripMenuItem;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.ToolStripMenuItem exitToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem quickBackupToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem quickRestoreToolStripMenuItem;
        private System.Windows.Forms.Timer backupTimer;
        private System.Windows.Forms.ToolStripMenuItem importQueueToolStripMenuItem;
    }
}

