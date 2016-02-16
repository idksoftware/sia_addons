<<<<<<< HEAD
namespace SIATray
{
    partial class DriveForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(DriveForm));
            this.label1 = new System.Windows.Forms.Label();
            this.listView = new System.Windows.Forms.ListView();
            this.chDriveLetter = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chImagesFound = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chScanned = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.imageLstT = new System.Windows.Forms.ImageList(this.components);
            this.labelLastBackedUp = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.buttonImport = new System.Windows.Forms.Button();
            this.button1 = new System.Windows.Forms.Button();
            this.labelDateLastBackedUp = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.labelVolumeLabel = new System.Windows.Forms.Label();
            this.labelTotalAvailableSpace = new System.Windows.Forms.Label();
            this.labelTotalSizeOfDrive = new System.Windows.Forms.Label();
            this.labelImagesFound = new System.Windows.Forms.Label();
            this.groupBoxDriveID = new System.Windows.Forms.GroupBox();
            this.buttonRefresh = new System.Windows.Forms.Button();
            this.groupBoxDriveID.SuspendLayout();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(18, 31);
            this.label1.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(124, 20);
            this.label1.TabIndex = 1;
            this.label1.Text = "Available Drives:";
            this.label1.Click += new System.EventHandler(this.label1_Click);
            // 
            // listView
            // 
            this.listView.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.chDriveLetter,
            this.chScanned,
            this.chImagesFound});
            this.listView.FullRowSelect = true;
            this.listView.Location = new System.Drawing.Point(22, 55);
            this.listView.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.listView.Name = "listView";
            this.listView.Size = new System.Drawing.Size(628, 189);
            this.listView.StateImageList = this.imageLstT;
            this.listView.TabIndex = 2;
            this.listView.UseCompatibleStateImageBehavior = false;
            this.listView.View = System.Windows.Forms.View.Details;
            this.listView.SelectedIndexChanged += new System.EventHandler(this.listView_SelectedIndexChanged);
            this.listView.DoubleClick += new System.EventHandler(this.listView_DoubleClick);
            // 
            // chDriveLetter
            // 
            this.chDriveLetter.Text = "Drive";
            this.chDriveLetter.Width = 54;
            // 
            // chImagesFound
            // 
            this.chImagesFound.Text = "Images Found";
            this.chImagesFound.Width = 108;
            // 
            // chScanned
            // 
            this.chScanned.DisplayIndex = 2;
            this.chScanned.Text = "Scanned";
            this.chScanned.Width = 104;
            // 
            // imageLstT
            // 
            this.imageLstT.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("imageLstT.ImageStream")));
            this.imageLstT.TransparentColor = System.Drawing.Color.Transparent;
            this.imageLstT.Images.SetKeyName(0, "Backedup.bmp");
            this.imageLstT.Images.SetKeyName(1, "NeedsBackup.bmp");
            // 
            // labelLastBackedUp
            // 
            this.labelLastBackedUp.AutoSize = true;
            this.labelLastBackedUp.Location = new System.Drawing.Point(18, 32);
            this.labelLastBackedUp.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.labelLastBackedUp.Name = "labelLastBackedUp";
            this.labelLastBackedUp.Size = new System.Drawing.Size(163, 20);
            this.labelLastBackedUp.TabIndex = 3;
            this.labelLastBackedUp.Text = "Date Last Backed up:";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(74, 52);
            this.label4.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(110, 20);
            this.label4.TabIndex = 5;
            this.label4.Text = "Volume Label:";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(18, 72);
            this.label5.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(160, 20);
            this.label5.TabIndex = 6;
            this.label5.Text = "Total available space:";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(40, 92);
            this.label6.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(139, 20);
            this.label6.TabIndex = 7;
            this.label6.Text = "Total size of drive::";
            // 
            // buttonImport
            // 
            this.buttonImport.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.buttonImport.Location = new System.Drawing.Point(528, 283);
            this.buttonImport.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.buttonImport.Name = "buttonImport";
            this.buttonImport.Size = new System.Drawing.Size(124, 35);
            this.buttonImport.TabIndex = 41;
            this.buttonImport.Text = "&Import";
            this.buttonImport.UseVisualStyleBackColor = true;
            this.buttonImport.Click += new System.EventHandler(this.buttonBackup_Click);
            // 
            // button1
            // 
            this.button1.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.button1.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.button1.Location = new System.Drawing.Point(528, 438);
            this.button1.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(124, 35);
            this.button1.TabIndex = 42;
            this.button1.Text = "&Close";
            this.button1.UseVisualStyleBackColor = true;
            // 
            // labelDateLastBackedUp
            // 
            this.labelDateLastBackedUp.AutoSize = true;
            this.labelDateLastBackedUp.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.labelDateLastBackedUp.Location = new System.Drawing.Point(194, 32);
            this.labelDateLastBackedUp.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.labelDateLastBackedUp.Name = "labelDateLastBackedUp";
            this.labelDateLastBackedUp.Size = new System.Drawing.Size(133, 20);
            this.labelDateLastBackedUp.TabIndex = 43;
            this.labelDateLastBackedUp.Text = "2010/03/10 12:23";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(34, 112);
            this.label3.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(116, 20);
            this.label3.TabIndex = 44;
            this.label3.Text = "Images Found:";
            // 
            // labelVolumeLabel
            // 
            this.labelVolumeLabel.AutoSize = true;
            this.labelVolumeLabel.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.labelVolumeLabel.Location = new System.Drawing.Point(194, 52);
            this.labelVolumeLabel.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.labelVolumeLabel.Name = "labelVolumeLabel";
            this.labelVolumeLabel.Size = new System.Drawing.Size(81, 20);
            this.labelVolumeLabel.TabIndex = 45;
            this.labelVolumeLabel.Text = "Nikon80-1";
            // 
            // labelTotalAvailableSpace
            // 
            this.labelTotalAvailableSpace.AutoSize = true;
            this.labelTotalAvailableSpace.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.labelTotalAvailableSpace.Location = new System.Drawing.Point(194, 72);
            this.labelTotalAvailableSpace.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.labelTotalAvailableSpace.Name = "labelTotalAvailableSpace";
            this.labelTotalAvailableSpace.Size = new System.Drawing.Size(31, 20);
            this.labelTotalAvailableSpace.TabIndex = 46;
            this.labelTotalAvailableSpace.Text = "8G";
            // 
            // labelTotalSizeOfDrive
            // 
            this.labelTotalSizeOfDrive.AutoSize = true;
            this.labelTotalSizeOfDrive.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.labelTotalSizeOfDrive.Location = new System.Drawing.Point(194, 92);
            this.labelTotalSizeOfDrive.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.labelTotalSizeOfDrive.Name = "labelTotalSizeOfDrive";
            this.labelTotalSizeOfDrive.Size = new System.Drawing.Size(31, 20);
            this.labelTotalSizeOfDrive.TabIndex = 47;
            this.labelTotalSizeOfDrive.Text = "8G";
            // 
            // labelImagesFound
            // 
            this.labelImagesFound.AutoSize = true;
            this.labelImagesFound.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelImagesFound.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.labelImagesFound.Location = new System.Drawing.Point(194, 112);
            this.labelImagesFound.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.labelImagesFound.Name = "labelImagesFound";
            this.labelImagesFound.Size = new System.Drawing.Size(58, 20);
            this.labelImagesFound.TabIndex = 48;
            this.labelImagesFound.Text = "TRUE";
            // 
            // groupBoxDriveID
            // 
            this.groupBoxDriveID.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.groupBoxDriveID.Controls.Add(this.labelImagesFound);
            this.groupBoxDriveID.Controls.Add(this.labelTotalSizeOfDrive);
            this.groupBoxDriveID.Controls.Add(this.labelTotalAvailableSpace);
            this.groupBoxDriveID.Controls.Add(this.labelVolumeLabel);
            this.groupBoxDriveID.Controls.Add(this.label3);
            this.groupBoxDriveID.Controls.Add(this.labelDateLastBackedUp);
            this.groupBoxDriveID.Controls.Add(this.label6);
            this.groupBoxDriveID.Controls.Add(this.label5);
            this.groupBoxDriveID.Controls.Add(this.label4);
            this.groupBoxDriveID.Controls.Add(this.labelLastBackedUp);
            this.groupBoxDriveID.Location = new System.Drawing.Point(22, 283);
            this.groupBoxDriveID.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.groupBoxDriveID.Name = "groupBoxDriveID";
            this.groupBoxDriveID.Padding = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.groupBoxDriveID.Size = new System.Drawing.Size(472, 191);
            this.groupBoxDriveID.TabIndex = 50;
            this.groupBoxDriveID.TabStop = false;
            this.groupBoxDriveID.Text = "Drive:";
            // 
            // buttonRefresh
            // 
            this.buttonRefresh.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.buttonRefresh.Location = new System.Drawing.Point(528, 335);
            this.buttonRefresh.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.buttonRefresh.Name = "buttonRefresh";
            this.buttonRefresh.Size = new System.Drawing.Size(124, 35);
            this.buttonRefresh.TabIndex = 54;
            this.buttonRefresh.Text = "&Refresh";
            this.buttonRefresh.UseVisualStyleBackColor = true;
            this.buttonRefresh.Click += new System.EventHandler(this.buttonRefresh_Click);
            // 
            // DriveForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(670, 492);
            this.Controls.Add(this.buttonRefresh);
            this.Controls.Add(this.groupBoxDriveID);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.buttonImport);
            this.Controls.Add(this.listView);
            this.Controls.Add(this.label1);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.Name = "DriveForm";
            this.SizeGripStyle = System.Windows.Forms.SizeGripStyle.Hide;
            this.Text = "SIA Importer - Available Drives";
            this.groupBoxDriveID.ResumeLayout(false);
            this.groupBoxDriveID.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ListView listView;
        private System.Windows.Forms.Label labelLastBackedUp;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.ColumnHeader chDriveLetter;
        private System.Windows.Forms.ColumnHeader chImagesFound;
        private System.Windows.Forms.Button buttonImport;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.ImageList imageLstT;
        private System.Windows.Forms.Label labelDateLastBackedUp;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label labelVolumeLabel;
        private System.Windows.Forms.Label labelTotalAvailableSpace;
        private System.Windows.Forms.Label labelTotalSizeOfDrive;
        private System.Windows.Forms.Label labelImagesFound;
        private System.Windows.Forms.GroupBox groupBoxDriveID;
        private System.Windows.Forms.Button buttonRefresh;
        private System.Windows.Forms.ColumnHeader chScanned;
    }
=======
namespace SIATray
{
    partial class DriveForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(DriveForm));
            this.label1 = new System.Windows.Forms.Label();
            this.listView = new System.Windows.Forms.ListView();
            this.chDriveLetter = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chImagesFound = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chScanned = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.imageLstT = new System.Windows.Forms.ImageList(this.components);
            this.labelLastBackedUp = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.buttonImport = new System.Windows.Forms.Button();
            this.button1 = new System.Windows.Forms.Button();
            this.labelDateLastBackedUp = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.labelVolumeLabel = new System.Windows.Forms.Label();
            this.labelTotalAvailableSpace = new System.Windows.Forms.Label();
            this.labelTotalSizeOfDrive = new System.Windows.Forms.Label();
            this.labelImagesFound = new System.Windows.Forms.Label();
            this.groupBoxDriveID = new System.Windows.Forms.GroupBox();
            this.buttonRefresh = new System.Windows.Forms.Button();
            this.groupBoxDriveID.SuspendLayout();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(18, 31);
            this.label1.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(124, 20);
            this.label1.TabIndex = 1;
            this.label1.Text = "Available Drives:";
            this.label1.Click += new System.EventHandler(this.label1_Click);
            // 
            // listView
            // 
            this.listView.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.chDriveLetter,
            this.chScanned,
            this.chImagesFound});
            this.listView.FullRowSelect = true;
            this.listView.Location = new System.Drawing.Point(22, 55);
            this.listView.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.listView.Name = "listView";
            this.listView.Size = new System.Drawing.Size(628, 189);
            this.listView.StateImageList = this.imageLstT;
            this.listView.TabIndex = 2;
            this.listView.UseCompatibleStateImageBehavior = false;
            this.listView.View = System.Windows.Forms.View.Details;
            this.listView.SelectedIndexChanged += new System.EventHandler(this.listView_SelectedIndexChanged);
            this.listView.DoubleClick += new System.EventHandler(this.listView_DoubleClick);
            // 
            // chDriveLetter
            // 
            this.chDriveLetter.Text = "Drive";
            this.chDriveLetter.Width = 54;
            // 
            // chImagesFound
            // 
            this.chImagesFound.Text = "Images Found";
            this.chImagesFound.Width = 108;
            // 
            // chScanned
            // 
            this.chScanned.DisplayIndex = 2;
            this.chScanned.Text = "Scanned";
            this.chScanned.Width = 104;
            // 
            // imageLstT
            // 
            this.imageLstT.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("imageLstT.ImageStream")));
            this.imageLstT.TransparentColor = System.Drawing.Color.Transparent;
            this.imageLstT.Images.SetKeyName(0, "Backedup.bmp");
            this.imageLstT.Images.SetKeyName(1, "NeedsBackup.bmp");
            // 
            // labelLastBackedUp
            // 
            this.labelLastBackedUp.AutoSize = true;
            this.labelLastBackedUp.Location = new System.Drawing.Point(18, 32);
            this.labelLastBackedUp.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.labelLastBackedUp.Name = "labelLastBackedUp";
            this.labelLastBackedUp.Size = new System.Drawing.Size(163, 20);
            this.labelLastBackedUp.TabIndex = 3;
            this.labelLastBackedUp.Text = "Date Last Backed up:";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(74, 52);
            this.label4.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(110, 20);
            this.label4.TabIndex = 5;
            this.label4.Text = "Volume Label:";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(18, 72);
            this.label5.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(160, 20);
            this.label5.TabIndex = 6;
            this.label5.Text = "Total available space:";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(40, 92);
            this.label6.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(139, 20);
            this.label6.TabIndex = 7;
            this.label6.Text = "Total size of drive::";
            // 
            // buttonImport
            // 
            this.buttonImport.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.buttonImport.Location = new System.Drawing.Point(528, 283);
            this.buttonImport.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.buttonImport.Name = "buttonImport";
            this.buttonImport.Size = new System.Drawing.Size(124, 35);
            this.buttonImport.TabIndex = 41;
            this.buttonImport.Text = "&Import";
            this.buttonImport.UseVisualStyleBackColor = true;
            this.buttonImport.Click += new System.EventHandler(this.buttonBackup_Click);
            // 
            // button1
            // 
            this.button1.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.button1.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.button1.Location = new System.Drawing.Point(528, 438);
            this.button1.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(124, 35);
            this.button1.TabIndex = 42;
            this.button1.Text = "&Close";
            this.button1.UseVisualStyleBackColor = true;
            // 
            // labelDateLastBackedUp
            // 
            this.labelDateLastBackedUp.AutoSize = true;
            this.labelDateLastBackedUp.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.labelDateLastBackedUp.Location = new System.Drawing.Point(194, 32);
            this.labelDateLastBackedUp.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.labelDateLastBackedUp.Name = "labelDateLastBackedUp";
            this.labelDateLastBackedUp.Size = new System.Drawing.Size(133, 20);
            this.labelDateLastBackedUp.TabIndex = 43;
            this.labelDateLastBackedUp.Text = "2010/03/10 12:23";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(34, 112);
            this.label3.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(116, 20);
            this.label3.TabIndex = 44;
            this.label3.Text = "Images Found:";
            // 
            // labelVolumeLabel
            // 
            this.labelVolumeLabel.AutoSize = true;
            this.labelVolumeLabel.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.labelVolumeLabel.Location = new System.Drawing.Point(194, 52);
            this.labelVolumeLabel.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.labelVolumeLabel.Name = "labelVolumeLabel";
            this.labelVolumeLabel.Size = new System.Drawing.Size(81, 20);
            this.labelVolumeLabel.TabIndex = 45;
            this.labelVolumeLabel.Text = "Nikon80-1";
            // 
            // labelTotalAvailableSpace
            // 
            this.labelTotalAvailableSpace.AutoSize = true;
            this.labelTotalAvailableSpace.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.labelTotalAvailableSpace.Location = new System.Drawing.Point(194, 72);
            this.labelTotalAvailableSpace.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.labelTotalAvailableSpace.Name = "labelTotalAvailableSpace";
            this.labelTotalAvailableSpace.Size = new System.Drawing.Size(31, 20);
            this.labelTotalAvailableSpace.TabIndex = 46;
            this.labelTotalAvailableSpace.Text = "8G";
            // 
            // labelTotalSizeOfDrive
            // 
            this.labelTotalSizeOfDrive.AutoSize = true;
            this.labelTotalSizeOfDrive.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.labelTotalSizeOfDrive.Location = new System.Drawing.Point(194, 92);
            this.labelTotalSizeOfDrive.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.labelTotalSizeOfDrive.Name = "labelTotalSizeOfDrive";
            this.labelTotalSizeOfDrive.Size = new System.Drawing.Size(31, 20);
            this.labelTotalSizeOfDrive.TabIndex = 47;
            this.labelTotalSizeOfDrive.Text = "8G";
            // 
            // labelImagesFound
            // 
            this.labelImagesFound.AutoSize = true;
            this.labelImagesFound.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelImagesFound.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.labelImagesFound.Location = new System.Drawing.Point(194, 112);
            this.labelImagesFound.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.labelImagesFound.Name = "labelImagesFound";
            this.labelImagesFound.Size = new System.Drawing.Size(58, 20);
            this.labelImagesFound.TabIndex = 48;
            this.labelImagesFound.Text = "TRUE";
            // 
            // groupBoxDriveID
            // 
            this.groupBoxDriveID.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.groupBoxDriveID.Controls.Add(this.labelImagesFound);
            this.groupBoxDriveID.Controls.Add(this.labelTotalSizeOfDrive);
            this.groupBoxDriveID.Controls.Add(this.labelTotalAvailableSpace);
            this.groupBoxDriveID.Controls.Add(this.labelVolumeLabel);
            this.groupBoxDriveID.Controls.Add(this.label3);
            this.groupBoxDriveID.Controls.Add(this.labelDateLastBackedUp);
            this.groupBoxDriveID.Controls.Add(this.label6);
            this.groupBoxDriveID.Controls.Add(this.label5);
            this.groupBoxDriveID.Controls.Add(this.label4);
            this.groupBoxDriveID.Controls.Add(this.labelLastBackedUp);
            this.groupBoxDriveID.Location = new System.Drawing.Point(22, 283);
            this.groupBoxDriveID.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.groupBoxDriveID.Name = "groupBoxDriveID";
            this.groupBoxDriveID.Padding = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.groupBoxDriveID.Size = new System.Drawing.Size(472, 191);
            this.groupBoxDriveID.TabIndex = 50;
            this.groupBoxDriveID.TabStop = false;
            this.groupBoxDriveID.Text = "Drive:";
            // 
            // buttonRefresh
            // 
            this.buttonRefresh.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.buttonRefresh.Location = new System.Drawing.Point(528, 335);
            this.buttonRefresh.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.buttonRefresh.Name = "buttonRefresh";
            this.buttonRefresh.Size = new System.Drawing.Size(124, 35);
            this.buttonRefresh.TabIndex = 54;
            this.buttonRefresh.Text = "&Refresh";
            this.buttonRefresh.UseVisualStyleBackColor = true;
            this.buttonRefresh.Click += new System.EventHandler(this.buttonRefresh_Click);
            // 
            // DriveForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(670, 492);
            this.Controls.Add(this.buttonRefresh);
            this.Controls.Add(this.groupBoxDriveID);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.buttonImport);
            this.Controls.Add(this.listView);
            this.Controls.Add(this.label1);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.Name = "DriveForm";
            this.SizeGripStyle = System.Windows.Forms.SizeGripStyle.Hide;
            this.Text = "SIA Importer - Available Drives";
            this.groupBoxDriveID.ResumeLayout(false);
            this.groupBoxDriveID.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ListView listView;
        private System.Windows.Forms.Label labelLastBackedUp;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.ColumnHeader chDriveLetter;
        private System.Windows.Forms.ColumnHeader chImagesFound;
        private System.Windows.Forms.Button buttonImport;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.ImageList imageLstT;
        private System.Windows.Forms.Label labelDateLastBackedUp;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label labelVolumeLabel;
        private System.Windows.Forms.Label labelTotalAvailableSpace;
        private System.Windows.Forms.Label labelTotalSizeOfDrive;
        private System.Windows.Forms.Label labelImagesFound;
        private System.Windows.Forms.GroupBox groupBoxDriveID;
        private System.Windows.Forms.Button buttonRefresh;
        private System.Windows.Forms.ColumnHeader chScanned;
    }
>>>>>>> 291c508aa47ea3e34a225d431ef34192e909c4ee
}