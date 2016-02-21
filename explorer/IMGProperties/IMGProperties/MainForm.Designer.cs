namespace IMGProperties
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
            this.tabControl1 = new System.Windows.Forms.TabControl();
            this.generalPage = new System.Windows.Forms.TabPage();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.labelUUID = new System.Windows.Forms.Label();
            this.labelArchiveName = new System.Windows.Forms.Label();
            this.labelMD5 = new System.Windows.Forms.Label();
            this.labelCRC = new System.Windows.Forms.Label();
            this.labelSize = new System.Windows.Forms.Label();
            this.label35 = new System.Windows.Forms.Label();
            this.label28 = new System.Windows.Forms.Label();
            this.label27 = new System.Windows.Forms.Label();
            this.label26 = new System.Windows.Forms.Label();
            this.label24 = new System.Windows.Forms.Label();
            this.labelLastModified = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.labelOriginalFileName = new System.Windows.Forms.Label();
            this.label9 = new System.Windows.Forms.Label();
            this.labelSubject = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.buttonChangeTitle = new System.Windows.Forms.Button();
            this.labelTitle = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.groupBoxIdentity = new System.Windows.Forms.GroupBox();
            this.labelRevision = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.labelNumber = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.detailsPage = new System.Windows.Forms.TabPage();
            this.buttonChangeDetails = new System.Windows.Forms.Button();
            this.labeType = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.labelCategory = new System.Windows.Forms.Label();
            this.label31 = new System.Windows.Forms.Label();
            this.labelLanguage = new System.Windows.Forms.Label();
            this.label23 = new System.Windows.Forms.Label();
            this.labelComments = new System.Windows.Forms.Label();
            this.label21 = new System.Windows.Forms.Label();
            this.labelEditor = new System.Windows.Forms.Label();
            this.label19 = new System.Windows.Forms.Label();
            this.labelHardcopyLocation = new System.Windows.Forms.Label();
            this.label17 = new System.Windows.Forms.Label();
            this.labelAuthor = new System.Windows.Forms.Label();
            this.label15 = new System.Windows.Forms.Label();
            this.labelLatestRevision = new System.Windows.Forms.Label();
            this.label13 = new System.Windows.Forms.Label();
            this.labelImageDate = new System.Windows.Forms.Label();
            this.label11 = new System.Windows.Forms.Label();
            this.exifPage = new System.Windows.Forms.TabPage();
            this.exifPropertyGrid = new System.Windows.Forms.PropertyGrid();
            this.tagPage = new System.Windows.Forms.TabPage();
            this.groupBox4 = new System.Windows.Forms.GroupBox();
            this.tagListBox = new System.Windows.Forms.ListBox();
            this.buttonTagChange = new System.Windows.Forms.Button();
            this.groupBox3 = new System.Windows.Forms.GroupBox();
            this.keywordListBox = new System.Windows.Forms.ListBox();
            this.buttonKeywordsChange = new System.Windows.Forms.Button();
            this.cameraPage = new System.Windows.Forms.TabPage();
            this.labelExposureMode = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.labelFlashMode = new System.Windows.Forms.Label();
            this.label12 = new System.Windows.Forms.Label();
            this.labelFocalLength = new System.Windows.Forms.Label();
            this.label20 = new System.Windows.Forms.Label();
            this.labelAperture = new System.Windows.Forms.Label();
            this.label29 = new System.Windows.Forms.Label();
            this.labelMetering = new System.Windows.Forms.Label();
            this.label32 = new System.Windows.Forms.Label();
            this.label33 = new System.Windows.Forms.Label();
            this.labelShutter = new System.Windows.Forms.Label();
            this.labelCamera = new System.Windows.Forms.Label();
            this.label37 = new System.Windows.Forms.Label();
            this.button5 = new System.Windows.Forms.Button();
            this.ChangeButton = new System.Windows.Forms.Button();
            this.tabControl1.SuspendLayout();
            this.generalPage.SuspendLayout();
            this.groupBox1.SuspendLayout();
            this.groupBoxIdentity.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.detailsPage.SuspendLayout();
            this.exifPage.SuspendLayout();
            this.tagPage.SuspendLayout();
            this.groupBox4.SuspendLayout();
            this.groupBox3.SuspendLayout();
            this.cameraPage.SuspendLayout();
            this.SuspendLayout();
            // 
            // tabControl1
            // 
            this.tabControl1.Controls.Add(this.generalPage);
            this.tabControl1.Controls.Add(this.detailsPage);
            this.tabControl1.Controls.Add(this.exifPage);
            this.tabControl1.Controls.Add(this.tagPage);
            this.tabControl1.Controls.Add(this.cameraPage);
            this.tabControl1.Location = new System.Drawing.Point(5, 12);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedIndex = 0;
            this.tabControl1.Size = new System.Drawing.Size(358, 424);
            this.tabControl1.TabIndex = 0;
            // 
            // generalPage
            // 
            this.generalPage.Controls.Add(this.groupBox1);
            this.generalPage.Controls.Add(this.labelSubject);
            this.generalPage.Controls.Add(this.label3);
            this.generalPage.Controls.Add(this.buttonChangeTitle);
            this.generalPage.Controls.Add(this.labelTitle);
            this.generalPage.Controls.Add(this.label1);
            this.generalPage.Controls.Add(this.groupBoxIdentity);
            this.generalPage.Controls.Add(this.pictureBox1);
            this.generalPage.Location = new System.Drawing.Point(4, 22);
            this.generalPage.Name = "generalPage";
            this.generalPage.Padding = new System.Windows.Forms.Padding(3);
            this.generalPage.Size = new System.Drawing.Size(350, 398);
            this.generalPage.TabIndex = 0;
            this.generalPage.Text = "General";
            this.generalPage.UseVisualStyleBackColor = true;
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.labelUUID);
            this.groupBox1.Controls.Add(this.labelArchiveName);
            this.groupBox1.Controls.Add(this.labelMD5);
            this.groupBox1.Controls.Add(this.labelCRC);
            this.groupBox1.Controls.Add(this.labelSize);
            this.groupBox1.Controls.Add(this.label35);
            this.groupBox1.Controls.Add(this.label28);
            this.groupBox1.Controls.Add(this.label27);
            this.groupBox1.Controls.Add(this.label26);
            this.groupBox1.Controls.Add(this.label24);
            this.groupBox1.Controls.Add(this.labelLastModified);
            this.groupBox1.Controls.Add(this.label6);
            this.groupBox1.Controls.Add(this.labelOriginalFileName);
            this.groupBox1.Controls.Add(this.label9);
            this.groupBox1.Location = new System.Drawing.Point(6, 132);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(338, 223);
            this.groupBox1.TabIndex = 7;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "File";
            // 
            // labelUUID
            // 
            this.labelUUID.AutoSize = true;
            this.labelUUID.Location = new System.Drawing.Point(91, 195);
            this.labelUUID.Name = "labelUUID";
            this.labelUUID.Size = new System.Drawing.Size(31, 13);
            this.labelUUID.TabIndex = 20;
            this.labelUUID.Text = "0001";
            this.labelUUID.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // labelArchiveName
            // 
            this.labelArchiveName.AutoSize = true;
            this.labelArchiveName.Location = new System.Drawing.Point(134, 57);
            this.labelArchiveName.Name = "labelArchiveName";
            this.labelArchiveName.Size = new System.Drawing.Size(31, 13);
            this.labelArchiveName.TabIndex = 19;
            this.labelArchiveName.Text = "0001";
            this.labelArchiveName.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // labelMD5
            // 
            this.labelMD5.AutoSize = true;
            this.labelMD5.Location = new System.Drawing.Point(88, 156);
            this.labelMD5.Name = "labelMD5";
            this.labelMD5.Size = new System.Drawing.Size(31, 13);
            this.labelMD5.TabIndex = 18;
            this.labelMD5.Text = "0001";
            this.labelMD5.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // labelCRC
            // 
            this.labelCRC.AutoSize = true;
            this.labelCRC.Location = new System.Drawing.Point(134, 113);
            this.labelCRC.Name = "labelCRC";
            this.labelCRC.Size = new System.Drawing.Size(31, 13);
            this.labelCRC.TabIndex = 17;
            this.labelCRC.Text = "0001";
            this.labelCRC.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // labelSize
            // 
            this.labelSize.AutoSize = true;
            this.labelSize.Location = new System.Drawing.Point(134, 84);
            this.labelSize.Name = "labelSize";
            this.labelSize.Size = new System.Drawing.Size(31, 13);
            this.labelSize.TabIndex = 16;
            this.labelSize.Text = "0001";
            this.labelSize.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label35
            // 
            this.label35.AutoSize = true;
            this.label35.Location = new System.Drawing.Point(51, 57);
            this.label35.Name = "label35";
            this.label35.Size = new System.Drawing.Size(77, 13);
            this.label35.TabIndex = 15;
            this.label35.Text = "Archive Name:";
            this.label35.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // label28
            // 
            this.label28.AutoSize = true;
            this.label28.Location = new System.Drawing.Point(85, 182);
            this.label28.Name = "label28";
            this.label28.Size = new System.Drawing.Size(37, 13);
            this.label28.TabIndex = 14;
            this.label28.Text = "UUID:";
            this.label28.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // label27
            // 
            this.label27.AutoSize = true;
            this.label27.Location = new System.Drawing.Point(85, 143);
            this.label27.Name = "label27";
            this.label27.Size = new System.Drawing.Size(33, 13);
            this.label27.TabIndex = 13;
            this.label27.Text = "MD5:";
            this.label27.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // label26
            // 
            this.label26.AutoSize = true;
            this.label26.Location = new System.Drawing.Point(85, 113);
            this.label26.Name = "label26";
            this.label26.Size = new System.Drawing.Size(32, 13);
            this.label26.TabIndex = 12;
            this.label26.Text = "CRC:";
            this.label26.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // label24
            // 
            this.label24.AutoSize = true;
            this.label24.Location = new System.Drawing.Point(87, 84);
            this.label24.Name = "label24";
            this.label24.Size = new System.Drawing.Size(30, 13);
            this.label24.TabIndex = 11;
            this.label24.Text = "Size:";
            this.label24.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // labelLastModified
            // 
            this.labelLastModified.AutoSize = true;
            this.labelLastModified.Location = new System.Drawing.Point(134, 35);
            this.labelLastModified.Name = "labelLastModified";
            this.labelLastModified.Size = new System.Drawing.Size(31, 13);
            this.labelLastModified.TabIndex = 10;
            this.labelLastModified.Text = "0001";
            this.labelLastModified.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(46, 35);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(73, 13);
            this.label6.TabIndex = 9;
            this.label6.Text = "Last Modified:";
            this.label6.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // labelOriginalFileName
            // 
            this.labelOriginalFileName.AutoSize = true;
            this.labelOriginalFileName.Location = new System.Drawing.Point(134, 16);
            this.labelOriginalFileName.Name = "labelOriginalFileName";
            this.labelOriginalFileName.Size = new System.Drawing.Size(55, 13);
            this.labelOriginalFileName.TabIndex = 8;
            this.labelOriginalFileName.Text = "00000001";
            this.labelOriginalFileName.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(24, 16);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(95, 13);
            this.label9.TabIndex = 7;
            this.label9.Text = "Original File Name:";
            this.label9.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // labelSubject
            // 
            this.labelSubject.AutoSize = true;
            this.labelSubject.Location = new System.Drawing.Point(88, 34);
            this.labelSubject.Name = "labelSubject";
            this.labelSubject.Size = new System.Drawing.Size(72, 13);
            this.labelSubject.TabIndex = 6;
            this.labelSubject.Text = "John Climbing";
            this.labelSubject.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(46, 34);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(46, 13);
            this.label3.TabIndex = 5;
            this.label3.Text = "Subject:";
            this.label3.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // buttonChangeTitle
            // 
            this.buttonChangeTitle.Location = new System.Drawing.Point(263, 34);
            this.buttonChangeTitle.Name = "buttonChangeTitle";
            this.buttonChangeTitle.Size = new System.Drawing.Size(75, 23);
            this.buttonChangeTitle.TabIndex = 4;
            this.buttonChangeTitle.Text = "Change";
            this.buttonChangeTitle.UseVisualStyleBackColor = true;
            this.buttonChangeTitle.Click += new System.EventHandler(this.buttonChangeTitle_Click);
            // 
            // labelTitle
            // 
            this.labelTitle.AutoSize = true;
            this.labelTitle.Location = new System.Drawing.Point(88, 15);
            this.labelTitle.Name = "labelTitle";
            this.labelTitle.Size = new System.Drawing.Size(113, 13);
            this.labelTitle.TabIndex = 3;
            this.labelTitle.Text = "Climbing in Wye Valley";
            this.labelTitle.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(61, 15);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(30, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "Title:";
            this.label1.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // groupBoxIdentity
            // 
            this.groupBoxIdentity.Controls.Add(this.labelRevision);
            this.groupBoxIdentity.Controls.Add(this.label5);
            this.groupBoxIdentity.Controls.Add(this.labelNumber);
            this.groupBoxIdentity.Controls.Add(this.label7);
            this.groupBoxIdentity.Location = new System.Drawing.Point(6, 63);
            this.groupBoxIdentity.Name = "groupBoxIdentity";
            this.groupBoxIdentity.Size = new System.Drawing.Size(338, 63);
            this.groupBoxIdentity.TabIndex = 1;
            this.groupBoxIdentity.TabStop = false;
            this.groupBoxIdentity.Text = "Identity";
            // 
            // labelRevision
            // 
            this.labelRevision.AutoSize = true;
            this.labelRevision.Location = new System.Drawing.Point(134, 35);
            this.labelRevision.Name = "labelRevision";
            this.labelRevision.Size = new System.Drawing.Size(31, 13);
            this.labelRevision.TabIndex = 10;
            this.labelRevision.Text = "0001";
            this.labelRevision.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(40, 35);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(51, 13);
            this.label5.TabIndex = 9;
            this.label5.Text = "Revision:";
            this.label5.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // labelNumber
            // 
            this.labelNumber.AutoSize = true;
            this.labelNumber.Location = new System.Drawing.Point(134, 16);
            this.labelNumber.Name = "labelNumber";
            this.labelNumber.Size = new System.Drawing.Size(55, 13);
            this.labelNumber.TabIndex = 8;
            this.labelNumber.Text = "00000001";
            this.labelNumber.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(40, 16);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(79, 13);
            this.label7.TabIndex = 7;
            this.label7.Text = "Image Number:";
            this.label7.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // pictureBox1
            // 
            this.pictureBox1.Image = global::IMGProperties.Properties.Resources.imgarchive;
            this.pictureBox1.Location = new System.Drawing.Point(6, 6);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(34, 35);
            this.pictureBox1.TabIndex = 0;
            this.pictureBox1.TabStop = false;
            // 
            // detailsPage
            // 
            this.detailsPage.Controls.Add(this.buttonChangeDetails);
            this.detailsPage.Controls.Add(this.labeType);
            this.detailsPage.Controls.Add(this.label4);
            this.detailsPage.Controls.Add(this.labelCategory);
            this.detailsPage.Controls.Add(this.label31);
            this.detailsPage.Controls.Add(this.labelLanguage);
            this.detailsPage.Controls.Add(this.label23);
            this.detailsPage.Controls.Add(this.labelComments);
            this.detailsPage.Controls.Add(this.label21);
            this.detailsPage.Controls.Add(this.labelEditor);
            this.detailsPage.Controls.Add(this.label19);
            this.detailsPage.Controls.Add(this.labelHardcopyLocation);
            this.detailsPage.Controls.Add(this.label17);
            this.detailsPage.Controls.Add(this.labelAuthor);
            this.detailsPage.Controls.Add(this.label15);
            this.detailsPage.Controls.Add(this.labelLatestRevision);
            this.detailsPage.Controls.Add(this.label13);
            this.detailsPage.Controls.Add(this.labelImageDate);
            this.detailsPage.Controls.Add(this.label11);
            this.detailsPage.Location = new System.Drawing.Point(4, 22);
            this.detailsPage.Name = "detailsPage";
            this.detailsPage.Padding = new System.Windows.Forms.Padding(3);
            this.detailsPage.Size = new System.Drawing.Size(350, 398);
            this.detailsPage.TabIndex = 1;
            this.detailsPage.Text = "Details";
            this.detailsPage.UseVisualStyleBackColor = true;
            // 
            // buttonChangeDetails
            // 
            this.buttonChangeDetails.Location = new System.Drawing.Point(258, 360);
            this.buttonChangeDetails.Name = "buttonChangeDetails";
            this.buttonChangeDetails.Size = new System.Drawing.Size(75, 23);
            this.buttonChangeDetails.TabIndex = 33;
            this.buttonChangeDetails.Text = "Change";
            this.buttonChangeDetails.UseVisualStyleBackColor = true;
            this.buttonChangeDetails.Click += new System.EventHandler(this.buttonChangeDetails_Click);
            // 
            // labeType
            // 
            this.labeType.AutoSize = true;
            this.labeType.Location = new System.Drawing.Point(130, 233);
            this.labeType.Name = "labeType";
            this.labeType.Size = new System.Drawing.Size(41, 13);
            this.labeType.TabIndex = 32;
            this.labeType.Text = "English";
            this.labeType.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(59, 233);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(37, 13);
            this.label4.TabIndex = 31;
            this.label4.Text = "Type: ";
            this.label4.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // labelCategory
            // 
            this.labelCategory.AutoSize = true;
            this.labelCategory.Location = new System.Drawing.Point(130, 127);
            this.labelCategory.Name = "labelCategory";
            this.labelCategory.Size = new System.Drawing.Size(60, 13);
            this.labelCategory.TabIndex = 30;
            this.labelCategory.Text = "Landscape";
            this.labelCategory.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label31
            // 
            this.label31.AutoSize = true;
            this.label31.Location = new System.Drawing.Point(65, 127);
            this.label31.Name = "label31";
            this.label31.Size = new System.Drawing.Size(52, 13);
            this.label31.TabIndex = 29;
            this.label31.Text = "Category:";
            this.label31.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // labelLanguage
            // 
            this.labelLanguage.AutoSize = true;
            this.labelLanguage.Location = new System.Drawing.Point(130, 206);
            this.labelLanguage.Name = "labelLanguage";
            this.labelLanguage.Size = new System.Drawing.Size(41, 13);
            this.labelLanguage.TabIndex = 22;
            this.labelLanguage.Text = "English";
            this.labelLanguage.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label23
            // 
            this.label23.AutoSize = true;
            this.label23.Location = new System.Drawing.Point(59, 206);
            this.label23.Name = "label23";
            this.label23.Size = new System.Drawing.Size(61, 13);
            this.label23.TabIndex = 21;
            this.label23.Text = "Language: ";
            this.label23.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // labelComments
            // 
            this.labelComments.AutoSize = true;
            this.labelComments.Location = new System.Drawing.Point(131, 154);
            this.labelComments.Name = "labelComments";
            this.labelComments.Size = new System.Drawing.Size(33, 13);
            this.labelComments.TabIndex = 20;
            this.labelComments.Text = "None";
            this.labelComments.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label21
            // 
            this.label21.AutoSize = true;
            this.label21.Location = new System.Drawing.Point(83, 181);
            this.label21.Name = "label21";
            this.label21.Size = new System.Drawing.Size(37, 13);
            this.label21.TabIndex = 19;
            this.label21.Text = "Editor:";
            this.label21.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // labelEditor
            // 
            this.labelEditor.AutoSize = true;
            this.labelEditor.Location = new System.Drawing.Point(131, 181);
            this.labelEditor.Name = "labelEditor";
            this.labelEditor.Size = new System.Drawing.Size(33, 13);
            this.labelEditor.TabIndex = 18;
            this.labelEditor.Text = "None";
            this.labelEditor.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label19
            // 
            this.label19.AutoSize = true;
            this.label19.Location = new System.Drawing.Point(66, 154);
            this.label19.Name = "label19";
            this.label19.Size = new System.Drawing.Size(59, 13);
            this.label19.TabIndex = 17;
            this.label19.Text = "Comments:";
            this.label19.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // labelHardcopyLocation
            // 
            this.labelHardcopyLocation.AutoSize = true;
            this.labelHardcopyLocation.Location = new System.Drawing.Point(130, 101);
            this.labelHardcopyLocation.Name = "labelHardcopyLocation";
            this.labelHardcopyLocation.Size = new System.Drawing.Size(84, 13);
            this.labelHardcopyLocation.TabIndex = 16;
            this.labelHardcopyLocation.Text = "Cabinet-11/789 ";
            this.labelHardcopyLocation.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label17
            // 
            this.label17.AutoSize = true;
            this.label17.Location = new System.Drawing.Point(25, 101);
            this.label17.Name = "label17";
            this.label17.Size = new System.Drawing.Size(99, 13);
            this.label17.TabIndex = 15;
            this.label17.Text = "Hard copy location:";
            this.label17.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // labelAuthor
            // 
            this.labelAuthor.AutoSize = true;
            this.labelAuthor.Location = new System.Drawing.Point(130, 49);
            this.labelAuthor.Name = "labelAuthor";
            this.labelAuthor.Size = new System.Drawing.Size(71, 13);
            this.labelAuthor.TabIndex = 14;
            this.labelAuthor.Text = "Iain Ferguson";
            this.labelAuthor.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label15
            // 
            this.label15.AutoSize = true;
            this.label15.Location = new System.Drawing.Point(83, 49);
            this.label15.Name = "label15";
            this.label15.Size = new System.Drawing.Size(41, 13);
            this.label15.TabIndex = 13;
            this.label15.Text = "Author:";
            this.label15.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // labelLatestRevision
            // 
            this.labelLatestRevision.AutoSize = true;
            this.labelLatestRevision.Location = new System.Drawing.Point(130, 75);
            this.labelLatestRevision.Name = "labelLatestRevision";
            this.labelLatestRevision.Size = new System.Drawing.Size(34, 13);
            this.labelLatestRevision.TabIndex = 12;
            this.labelLatestRevision.Text = "0001 ";
            this.labelLatestRevision.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label13
            // 
            this.label13.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.label13.AutoSize = true;
            this.label13.Location = new System.Drawing.Point(35, 75);
            this.label13.Name = "label13";
            this.label13.Size = new System.Drawing.Size(89, 13);
            this.label13.TabIndex = 11;
            this.label13.Text = "Latest Revision:  ";
            this.label13.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // labelImageDate
            // 
            this.labelImageDate.AutoSize = true;
            this.labelImageDate.Location = new System.Drawing.Point(130, 23);
            this.labelImageDate.Name = "labelImageDate";
            this.labelImageDate.Size = new System.Drawing.Size(56, 13);
            this.labelImageDate.TabIndex = 10;
            this.labelImageDate.Text = "Unknown ";
            this.labelImageDate.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label11
            // 
            this.label11.AutoSize = true;
            this.label11.Location = new System.Drawing.Point(59, 23);
            this.label11.Name = "label11";
            this.label11.Size = new System.Drawing.Size(65, 13);
            this.label11.TabIndex = 9;
            this.label11.Text = "Image Date:";
            this.label11.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // exifPage
            // 
            this.exifPage.Controls.Add(this.exifPropertyGrid);
            this.exifPage.Location = new System.Drawing.Point(4, 22);
            this.exifPage.Name = "exifPage";
            this.exifPage.Size = new System.Drawing.Size(350, 398);
            this.exifPage.TabIndex = 2;
            this.exifPage.Text = "Exif";
            this.exifPage.UseVisualStyleBackColor = true;
            // 
            // exifPropertyGrid
            // 
            this.exifPropertyGrid.Location = new System.Drawing.Point(3, 0);
            this.exifPropertyGrid.Name = "exifPropertyGrid";
            this.exifPropertyGrid.Size = new System.Drawing.Size(343, 395);
            this.exifPropertyGrid.TabIndex = 0;
            // 
            // tagPage
            // 
            this.tagPage.Controls.Add(this.groupBox4);
            this.tagPage.Controls.Add(this.groupBox3);
            this.tagPage.Location = new System.Drawing.Point(4, 22);
            this.tagPage.Name = "tagPage";
            this.tagPage.Size = new System.Drawing.Size(350, 398);
            this.tagPage.TabIndex = 3;
            this.tagPage.Text = "Tags & Keywords";
            this.tagPage.UseVisualStyleBackColor = true;
            // 
            // groupBox4
            // 
            this.groupBox4.Controls.Add(this.tagListBox);
            this.groupBox4.Controls.Add(this.buttonTagChange);
            this.groupBox4.Location = new System.Drawing.Point(6, 13);
            this.groupBox4.Name = "groupBox4";
            this.groupBox4.Size = new System.Drawing.Size(338, 146);
            this.groupBox4.TabIndex = 33;
            this.groupBox4.TabStop = false;
            this.groupBox4.Text = "Tags";
            // 
            // tagListBox
            // 
            this.tagListBox.FormattingEnabled = true;
            this.tagListBox.Location = new System.Drawing.Point(16, 27);
            this.tagListBox.Name = "tagListBox";
            this.tagListBox.Size = new System.Drawing.Size(226, 108);
            this.tagListBox.TabIndex = 26;
            // 
            // buttonTagChange
            // 
            this.buttonTagChange.Location = new System.Drawing.Point(257, 112);
            this.buttonTagChange.Name = "buttonTagChange";
            this.buttonTagChange.Size = new System.Drawing.Size(75, 23);
            this.buttonTagChange.TabIndex = 25;
            this.buttonTagChange.Text = "Change";
            this.buttonTagChange.UseVisualStyleBackColor = true;
            this.buttonTagChange.Click += new System.EventHandler(this.buttonTagChange_Click);
            // 
            // groupBox3
            // 
            this.groupBox3.Controls.Add(this.keywordListBox);
            this.groupBox3.Controls.Add(this.buttonKeywordsChange);
            this.groupBox3.Location = new System.Drawing.Point(6, 165);
            this.groupBox3.Name = "groupBox3";
            this.groupBox3.Size = new System.Drawing.Size(338, 134);
            this.groupBox3.TabIndex = 32;
            this.groupBox3.TabStop = false;
            this.groupBox3.Text = "Keywords";
            // 
            // keywordListBox
            // 
            this.keywordListBox.FormattingEnabled = true;
            this.keywordListBox.Location = new System.Drawing.Point(16, 16);
            this.keywordListBox.Name = "keywordListBox";
            this.keywordListBox.Size = new System.Drawing.Size(226, 108);
            this.keywordListBox.TabIndex = 27;
            // 
            // buttonKeywordsChange
            // 
            this.buttonKeywordsChange.Location = new System.Drawing.Point(257, 101);
            this.buttonKeywordsChange.Name = "buttonKeywordsChange";
            this.buttonKeywordsChange.Size = new System.Drawing.Size(75, 23);
            this.buttonKeywordsChange.TabIndex = 25;
            this.buttonKeywordsChange.Text = "Change";
            this.buttonKeywordsChange.UseVisualStyleBackColor = true;
            this.buttonKeywordsChange.Click += new System.EventHandler(this.buttonKeywordsChange_Click);
            // 
            // cameraPage
            // 
            this.cameraPage.Controls.Add(this.ChangeButton);
            this.cameraPage.Controls.Add(this.labelExposureMode);
            this.cameraPage.Controls.Add(this.label8);
            this.cameraPage.Controls.Add(this.labelFlashMode);
            this.cameraPage.Controls.Add(this.label12);
            this.cameraPage.Controls.Add(this.labelFocalLength);
            this.cameraPage.Controls.Add(this.label20);
            this.cameraPage.Controls.Add(this.labelAperture);
            this.cameraPage.Controls.Add(this.label29);
            this.cameraPage.Controls.Add(this.labelMetering);
            this.cameraPage.Controls.Add(this.label32);
            this.cameraPage.Controls.Add(this.label33);
            this.cameraPage.Controls.Add(this.labelShutter);
            this.cameraPage.Controls.Add(this.labelCamera);
            this.cameraPage.Controls.Add(this.label37);
            this.cameraPage.Location = new System.Drawing.Point(4, 22);
            this.cameraPage.Name = "cameraPage";
            this.cameraPage.Size = new System.Drawing.Size(350, 398);
            this.cameraPage.TabIndex = 4;
            this.cameraPage.Text = "Camera";
            this.cameraPage.UseVisualStyleBackColor = true;
            // 
            // labelExposureMode
            // 
            this.labelExposureMode.AutoSize = true;
            this.labelExposureMode.Location = new System.Drawing.Point(164, 143);
            this.labelExposureMode.Name = "labelExposureMode";
            this.labelExposureMode.Size = new System.Drawing.Size(60, 13);
            this.labelExposureMode.TabIndex = 46;
            this.labelExposureMode.Text = "Landscape";
            this.labelExposureMode.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(55, 143);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(84, 13);
            this.label8.TabIndex = 45;
            this.label8.Text = "Exposure Mode:";
            this.label8.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // labelFlashMode
            // 
            this.labelFlashMode.AutoSize = true;
            this.labelFlashMode.Location = new System.Drawing.Point(165, 201);
            this.labelFlashMode.Name = "labelFlashMode";
            this.labelFlashMode.Size = new System.Drawing.Size(41, 13);
            this.labelFlashMode.TabIndex = 44;
            this.labelFlashMode.Text = "English";
            this.labelFlashMode.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label12
            // 
            this.label12.AutoSize = true;
            this.label12.Location = new System.Drawing.Point(71, 201);
            this.label12.Name = "label12";
            this.label12.Size = new System.Drawing.Size(68, 13);
            this.label12.TabIndex = 43;
            this.label12.Text = "Flash Mode: ";
            this.label12.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // labelFocalLength
            // 
            this.labelFocalLength.AutoSize = true;
            this.labelFocalLength.Location = new System.Drawing.Point(165, 170);
            this.labelFocalLength.Name = "labelFocalLength";
            this.labelFocalLength.Size = new System.Drawing.Size(33, 13);
            this.labelFocalLength.TabIndex = 42;
            this.labelFocalLength.Text = "None";
            this.labelFocalLength.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label20
            // 
            this.label20.AutoSize = true;
            this.label20.Location = new System.Drawing.Point(67, 170);
            this.label20.Name = "label20";
            this.label20.Size = new System.Drawing.Size(72, 13);
            this.label20.TabIndex = 39;
            this.label20.Text = "Focal Length:";
            this.label20.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // labelAperture
            // 
            this.labelAperture.AutoSize = true;
            this.labelAperture.Location = new System.Drawing.Point(164, 117);
            this.labelAperture.Name = "labelAperture";
            this.labelAperture.Size = new System.Drawing.Size(84, 13);
            this.labelAperture.TabIndex = 38;
            this.labelAperture.Text = "Cabinet-11/789 ";
            this.labelAperture.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label29
            // 
            this.label29.AutoSize = true;
            this.label29.Location = new System.Drawing.Point(85, 117);
            this.label29.Name = "label29";
            this.label29.Size = new System.Drawing.Size(50, 13);
            this.label29.TabIndex = 37;
            this.label29.Text = "Aperture:";
            this.label29.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // labelMetering
            // 
            this.labelMetering.AutoSize = true;
            this.labelMetering.Location = new System.Drawing.Point(164, 65);
            this.labelMetering.Name = "labelMetering";
            this.labelMetering.Size = new System.Drawing.Size(71, 13);
            this.labelMetering.TabIndex = 36;
            this.labelMetering.Text = "Iain Ferguson";
            this.labelMetering.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label32
            // 
            this.label32.AutoSize = true;
            this.label32.Location = new System.Drawing.Point(85, 65);
            this.label32.Name = "label32";
            this.label32.Size = new System.Drawing.Size(54, 13);
            this.label32.TabIndex = 35;
            this.label32.Text = "Metering: ";
            this.label32.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // label33
            // 
            this.label33.AutoSize = true;
            this.label33.Location = new System.Drawing.Point(164, 91);
            this.label33.Name = "label33";
            this.label33.Size = new System.Drawing.Size(34, 13);
            this.label33.TabIndex = 34;
            this.label33.Text = "0001 ";
            this.label33.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // labelShutter
            // 
            this.labelShutter.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.labelShutter.AutoSize = true;
            this.labelShutter.Location = new System.Drawing.Point(89, 91);
            this.labelShutter.Name = "labelShutter";
            this.labelShutter.Size = new System.Drawing.Size(50, 13);
            this.labelShutter.TabIndex = 33;
            this.labelShutter.Text = "Shutter:  ";
            this.labelShutter.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // labelCamera
            // 
            this.labelCamera.AutoSize = true;
            this.labelCamera.Location = new System.Drawing.Point(164, 39);
            this.labelCamera.Name = "labelCamera";
            this.labelCamera.Size = new System.Drawing.Size(56, 13);
            this.labelCamera.TabIndex = 32;
            this.labelCamera.Text = "Unknown ";
            this.labelCamera.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label37
            // 
            this.label37.AutoSize = true;
            this.label37.Location = new System.Drawing.Point(93, 39);
            this.label37.Name = "label37";
            this.label37.Size = new System.Drawing.Size(46, 13);
            this.label37.TabIndex = 31;
            this.label37.Text = "Camera:";
            this.label37.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // button5
            // 
            this.button5.Location = new System.Drawing.Point(284, 442);
            this.button5.Name = "button5";
            this.button5.Size = new System.Drawing.Size(75, 23);
            this.button5.TabIndex = 1;
            this.button5.Text = "&Done";
            this.button5.UseVisualStyleBackColor = true;
            this.button5.Click += new System.EventHandler(this.button5_Click);
            // 
            // ChangeButton
            // 
            this.ChangeButton.Location = new System.Drawing.Point(260, 363);
            this.ChangeButton.Name = "ChangeButton";
            this.ChangeButton.Size = new System.Drawing.Size(75, 23);
            this.ChangeButton.TabIndex = 47;
            this.ChangeButton.Text = "Change";
            this.ChangeButton.UseVisualStyleBackColor = true;
            this.ChangeButton.Click += new System.EventHandler(this.ChangeButton_Click);
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(367, 473);
            this.Controls.Add(this.button5);
            this.Controls.Add(this.tabControl1);
            this.Name = "MainForm";
            this.Text = "Image Details";
            this.Load += new System.EventHandler(this.MainForm_Load);
            this.tabControl1.ResumeLayout(false);
            this.generalPage.ResumeLayout(false);
            this.generalPage.PerformLayout();
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.groupBoxIdentity.ResumeLayout(false);
            this.groupBoxIdentity.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.detailsPage.ResumeLayout(false);
            this.detailsPage.PerformLayout();
            this.exifPage.ResumeLayout(false);
            this.tagPage.ResumeLayout(false);
            this.groupBox4.ResumeLayout(false);
            this.groupBox3.ResumeLayout(false);
            this.cameraPage.ResumeLayout(false);
            this.cameraPage.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl tabControl1;
        private System.Windows.Forms.TabPage generalPage;
        private System.Windows.Forms.TabPage detailsPage;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.GroupBox groupBoxIdentity;
        private System.Windows.Forms.Button buttonChangeTitle;
        private System.Windows.Forms.Label labelTitle;
        private System.Windows.Forms.Label labelSubject;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label labelRevision;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label labelNumber;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Label labelLastModified;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label labelOriginalFileName;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Label labelCategory;
        private System.Windows.Forms.Label label31;
        private System.Windows.Forms.Label labelLanguage;
        private System.Windows.Forms.Label label23;
        private System.Windows.Forms.Label labelComments;
        private System.Windows.Forms.Label label21;
        private System.Windows.Forms.Label labelEditor;
        private System.Windows.Forms.Label label19;
        private System.Windows.Forms.Label labelHardcopyLocation;
        private System.Windows.Forms.Label label17;
        private System.Windows.Forms.Label labelAuthor;
        private System.Windows.Forms.Label label15;
        private System.Windows.Forms.Label labelLatestRevision;
        private System.Windows.Forms.Label label13;
        private System.Windows.Forms.Label labelImageDate;
        private System.Windows.Forms.Label label11;
        private System.Windows.Forms.TabPage exifPage;
        private System.Windows.Forms.PropertyGrid exifPropertyGrid;
        private System.Windows.Forms.TabPage tagPage;
        private System.Windows.Forms.GroupBox groupBox4;
        private System.Windows.Forms.Button buttonTagChange;
        private System.Windows.Forms.GroupBox groupBox3;
        private System.Windows.Forms.Button buttonKeywordsChange;
        private System.Windows.Forms.ListBox tagListBox;
        private System.Windows.Forms.ListBox keywordListBox;
        private System.Windows.Forms.Button button5;
        private System.Windows.Forms.TabPage cameraPage;
        private System.Windows.Forms.Label label26;
        private System.Windows.Forms.Label label24;
        private System.Windows.Forms.Label labelUUID;
        private System.Windows.Forms.Label labelArchiveName;
        private System.Windows.Forms.Label labelMD5;
        private System.Windows.Forms.Label labelCRC;
        private System.Windows.Forms.Label labelSize;
        private System.Windows.Forms.Label label35;
        private System.Windows.Forms.Label label28;
        private System.Windows.Forms.Label label27;
        private System.Windows.Forms.Label labelExposureMode;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Label labelFlashMode;
        private System.Windows.Forms.Label label12;
        private System.Windows.Forms.Label labelFocalLength;
        private System.Windows.Forms.Label label20;
        private System.Windows.Forms.Label labelAperture;
        private System.Windows.Forms.Label label29;
        private System.Windows.Forms.Label labelMetering;
        private System.Windows.Forms.Label label32;
        private System.Windows.Forms.Label label33;
        private System.Windows.Forms.Label labelShutter;
        private System.Windows.Forms.Label labelCamera;
        private System.Windows.Forms.Label label37;
        private System.Windows.Forms.Label labeType;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Button buttonChangeDetails;
        private System.Windows.Forms.Button ChangeButton;


    }
}

