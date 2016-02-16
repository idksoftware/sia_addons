<<<<<<< HEAD
namespace SIATray
{
    partial class JounalContentsForm
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
            this.listView1 = new System.Windows.Forms.ListView();
            this.chFile = new System.Windows.Forms.ColumnHeader();
            this.chDate = new System.Windows.Forms.ColumnHeader();
            this.chAttrib = new System.Windows.Forms.ColumnHeader();
            this.imageListStatus = new System.Windows.Forms.ImageList(this.components);
            this.buttonCancel = new System.Windows.Forms.Button();
            this.contextMenuStrip = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.invertSelectionToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.fileToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.restoreAllToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.restoreSelectedToolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
            this.closeToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.editToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.selectAllToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.invertSelectionToolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.labelDriveID = new System.Windows.Forms.Label();
            this.labelDate = new System.Windows.Forms.Label();
            this.labelType = new System.Windows.Forms.Label();
            this.labelDescription = new System.Windows.Forms.Label();
            this.selectToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.unselectToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.contextMenuStrip.SuspendLayout();
            this.menuStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // listView1
            // 
            this.listView1.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.listView1.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.chFile,
            this.chDate,
            this.chAttrib});
            this.listView1.ContextMenuStrip = this.contextMenuStrip;
            this.listView1.FullRowSelect = true;
            this.listView1.Location = new System.Drawing.Point(12, 106);
            this.listView1.Name = "listView1";
            this.listView1.Size = new System.Drawing.Size(385, 257);
            this.listView1.StateImageList = this.imageListStatus;
            this.listView1.TabIndex = 0;
            this.listView1.UseCompatibleStateImageBehavior = false;
            this.listView1.View = System.Windows.Forms.View.Details;
            this.listView1.DoubleClick += new System.EventHandler(this.listView1_DoubleClick);
            this.listView1.Click += new System.EventHandler(this.listView1_Click);
            // 
            // chFile
            // 
            this.chFile.Text = "File";
            this.chFile.Width = 193;
            // 
            // chDate
            // 
            this.chDate.Text = "Date last modified";
            this.chDate.Width = 144;
            // 
            // chAttrib
            // 
            this.chAttrib.Text = "Attrib";
            // 
            // imageListStatus
            // 
            this.imageListStatus.ColorDepth = System.Windows.Forms.ColorDepth.Depth8Bit;
            this.imageListStatus.ImageSize = new System.Drawing.Size(8, 8);
            this.imageListStatus.TransparentColor = System.Drawing.Color.Transparent;
            // 
            // buttonCancel
            // 
            this.buttonCancel.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.buttonCancel.DialogResult = System.Windows.Forms.DialogResult.OK;
            this.buttonCancel.Location = new System.Drawing.Point(314, 369);
            this.buttonCancel.Name = "buttonCancel";
            this.buttonCancel.Size = new System.Drawing.Size(83, 23);
            this.buttonCancel.TabIndex = 40;
            this.buttonCancel.Text = "&Close";
            this.buttonCancel.UseVisualStyleBackColor = true;
            // 
            // contextMenuStrip
            // 
            this.contextMenuStrip.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.selectToolStripMenuItem,
            this.unselectToolStripMenuItem,
            this.toolStripSeparator2,
            this.invertSelectionToolStripMenuItem});
            this.contextMenuStrip.Name = "contextMenuStrip";
            this.contextMenuStrip.Size = new System.Drawing.Size(135, 76);
            // 
            // invertSelectionToolStripMenuItem
            // 
            this.invertSelectionToolStripMenuItem.Name = "invertSelectionToolStripMenuItem";
            this.invertSelectionToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.invertSelectionToolStripMenuItem.Text = "Properties";
            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.fileToolStripMenuItem,
            this.editToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(416, 24);
            this.menuStrip1.TabIndex = 43;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // fileToolStripMenuItem
            // 
            this.fileToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.restoreAllToolStripMenuItem,
            this.restoreSelectedToolStripMenuItem1,
            this.toolStripSeparator1,
            this.closeToolStripMenuItem});
            this.fileToolStripMenuItem.Name = "fileToolStripMenuItem";
            this.fileToolStripMenuItem.Size = new System.Drawing.Size(35, 20);
            this.fileToolStripMenuItem.Text = "File";
            // 
            // restoreAllToolStripMenuItem
            // 
            this.restoreAllToolStripMenuItem.Name = "restoreAllToolStripMenuItem";
            this.restoreAllToolStripMenuItem.Size = new System.Drawing.Size(167, 22);
            this.restoreAllToolStripMenuItem.Text = "Restore &All";
            // 
            // restoreSelectedToolStripMenuItem1
            // 
            this.restoreSelectedToolStripMenuItem1.Name = "restoreSelectedToolStripMenuItem1";
            this.restoreSelectedToolStripMenuItem1.Size = new System.Drawing.Size(167, 22);
            this.restoreSelectedToolStripMenuItem1.Text = "Restore &Selected";
            // 
            // closeToolStripMenuItem
            // 
            this.closeToolStripMenuItem.Name = "closeToolStripMenuItem";
            this.closeToolStripMenuItem.Size = new System.Drawing.Size(167, 22);
            this.closeToolStripMenuItem.Text = "&Close";
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(164, 6);
            // 
            // editToolStripMenuItem
            // 
            this.editToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.selectAllToolStripMenuItem,
            this.invertSelectionToolStripMenuItem1});
            this.editToolStripMenuItem.Name = "editToolStripMenuItem";
            this.editToolStripMenuItem.Size = new System.Drawing.Size(37, 20);
            this.editToolStripMenuItem.Text = "Edit";
            // 
            // selectAllToolStripMenuItem
            // 
            this.selectAllToolStripMenuItem.Name = "selectAllToolStripMenuItem";
            this.selectAllToolStripMenuItem.Size = new System.Drawing.Size(161, 22);
            this.selectAllToolStripMenuItem.Text = "Select &All";
            // 
            // invertSelectionToolStripMenuItem1
            // 
            this.invertSelectionToolStripMenuItem1.Name = "invertSelectionToolStripMenuItem1";
            this.invertSelectionToolStripMenuItem1.Size = new System.Drawing.Size(161, 22);
            this.invertSelectionToolStripMenuItem1.Text = "&Invert Selection";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(30, 38);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(49, 13);
            this.label1.TabIndex = 45;
            this.label1.Text = "Drive ID:";
            this.label1.TextAlign = System.Drawing.ContentAlignment.TopRight;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(46, 51);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(33, 13);
            this.label2.TabIndex = 46;
            this.label2.Text = "Date:";
            this.label2.TextAlign = System.Drawing.ContentAlignment.TopRight;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(46, 64);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(34, 13);
            this.label3.TabIndex = 47;
            this.label3.Text = "Type:";
            this.label3.TextAlign = System.Drawing.ContentAlignment.TopRight;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(20, 77);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(59, 13);
            this.label4.TabIndex = 48;
            this.label4.Text = "Discription:";
            this.label4.TextAlign = System.Drawing.ContentAlignment.TopRight;
            // 
            // labelDriveID
            // 
            this.labelDriveID.Location = new System.Drawing.Point(89, 38);
            this.labelDriveID.Name = "labelDriveID";
            this.labelDriveID.Size = new System.Drawing.Size(308, 13);
            this.labelDriveID.TabIndex = 49;
            this.labelDriveID.Text = "label5";
            // 
            // labelDate
            // 
            this.labelDate.Location = new System.Drawing.Point(89, 51);
            this.labelDate.Name = "labelDate";
            this.labelDate.Size = new System.Drawing.Size(308, 13);
            this.labelDate.TabIndex = 50;
            this.labelDate.Text = "label6";
            // 
            // labelType
            // 
            this.labelType.Location = new System.Drawing.Point(89, 64);
            this.labelType.Name = "labelType";
            this.labelType.Size = new System.Drawing.Size(308, 13);
            this.labelType.TabIndex = 51;
            this.labelType.Text = "label7";
            // 
            // labelDescription
            // 
            this.labelDescription.Location = new System.Drawing.Point(89, 77);
            this.labelDescription.Name = "labelDescription";
            this.labelDescription.Size = new System.Drawing.Size(308, 13);
            this.labelDescription.TabIndex = 52;
            this.labelDescription.Text = "label8";
            // 
            // selectToolStripMenuItem
            // 
            this.selectToolStripMenuItem.Name = "selectToolStripMenuItem";
            this.selectToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.selectToolStripMenuItem.Text = "Select";
            // 
            // unselectToolStripMenuItem
            // 
            this.unselectToolStripMenuItem.Name = "unselectToolStripMenuItem";
            this.unselectToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.unselectToolStripMenuItem.Text = "Unselect";
            // 
            // toolStripSeparator2
            // 
            this.toolStripSeparator2.Name = "toolStripSeparator2";
            this.toolStripSeparator2.Size = new System.Drawing.Size(149, 6);
            // 
            // JounalContentsForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(416, 395);
            this.Controls.Add(this.labelDescription);
            this.Controls.Add(this.labelType);
            this.Controls.Add(this.labelDate);
            this.Controls.Add(this.labelDriveID);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.menuStrip1);
            this.Controls.Add(this.buttonCancel);
            this.Controls.Add(this.listView1);
            this.MainMenuStrip = this.menuStrip1;
            this.MinimumSize = new System.Drawing.Size(424, 422);
            this.Name = "JounalContentsForm";
            this.Text = "Flash Memory Manager - Contents";
            this.Load += new System.EventHandler(this.JounalContentsForm_Load);
            this.DoubleClick += new System.EventHandler(this.JounalContentsForm_DoubleClick);
            this.Activated += new System.EventHandler(this.JounalContentsForm_Activated);
            this.contextMenuStrip.ResumeLayout(false);
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListView listView1;
        private System.Windows.Forms.Button buttonCancel;
        private System.Windows.Forms.ColumnHeader chFile;
        private System.Windows.Forms.ImageList imageListStatus;
        private System.Windows.Forms.ColumnHeader chDate;
        private System.Windows.Forms.ColumnHeader chAttrib;
        private System.Windows.Forms.ContextMenuStrip contextMenuStrip;
        private System.Windows.Forms.ToolStripMenuItem invertSelectionToolStripMenuItem;
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem fileToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem restoreAllToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem restoreSelectedToolStripMenuItem1;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.ToolStripMenuItem closeToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem editToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem selectAllToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem invertSelectionToolStripMenuItem1;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label labelDriveID;
        private System.Windows.Forms.Label labelDate;
        private System.Windows.Forms.Label labelType;
        private System.Windows.Forms.Label labelDescription;
        private System.Windows.Forms.ToolStripMenuItem selectToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem unselectToolStripMenuItem;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
    }
=======
namespace SIATray
{
    partial class JounalContentsForm
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
            this.listView1 = new System.Windows.Forms.ListView();
            this.chFile = new System.Windows.Forms.ColumnHeader();
            this.chDate = new System.Windows.Forms.ColumnHeader();
            this.chAttrib = new System.Windows.Forms.ColumnHeader();
            this.imageListStatus = new System.Windows.Forms.ImageList(this.components);
            this.buttonCancel = new System.Windows.Forms.Button();
            this.contextMenuStrip = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.invertSelectionToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.fileToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.restoreAllToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.restoreSelectedToolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
            this.closeToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.editToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.selectAllToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.invertSelectionToolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.labelDriveID = new System.Windows.Forms.Label();
            this.labelDate = new System.Windows.Forms.Label();
            this.labelType = new System.Windows.Forms.Label();
            this.labelDescription = new System.Windows.Forms.Label();
            this.selectToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.unselectToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.contextMenuStrip.SuspendLayout();
            this.menuStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // listView1
            // 
            this.listView1.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.listView1.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.chFile,
            this.chDate,
            this.chAttrib});
            this.listView1.ContextMenuStrip = this.contextMenuStrip;
            this.listView1.FullRowSelect = true;
            this.listView1.Location = new System.Drawing.Point(12, 106);
            this.listView1.Name = "listView1";
            this.listView1.Size = new System.Drawing.Size(385, 257);
            this.listView1.StateImageList = this.imageListStatus;
            this.listView1.TabIndex = 0;
            this.listView1.UseCompatibleStateImageBehavior = false;
            this.listView1.View = System.Windows.Forms.View.Details;
            this.listView1.DoubleClick += new System.EventHandler(this.listView1_DoubleClick);
            this.listView1.Click += new System.EventHandler(this.listView1_Click);
            // 
            // chFile
            // 
            this.chFile.Text = "File";
            this.chFile.Width = 193;
            // 
            // chDate
            // 
            this.chDate.Text = "Date last modified";
            this.chDate.Width = 144;
            // 
            // chAttrib
            // 
            this.chAttrib.Text = "Attrib";
            // 
            // imageListStatus
            // 
            this.imageListStatus.ColorDepth = System.Windows.Forms.ColorDepth.Depth8Bit;
            this.imageListStatus.ImageSize = new System.Drawing.Size(8, 8);
            this.imageListStatus.TransparentColor = System.Drawing.Color.Transparent;
            // 
            // buttonCancel
            // 
            this.buttonCancel.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.buttonCancel.DialogResult = System.Windows.Forms.DialogResult.OK;
            this.buttonCancel.Location = new System.Drawing.Point(314, 369);
            this.buttonCancel.Name = "buttonCancel";
            this.buttonCancel.Size = new System.Drawing.Size(83, 23);
            this.buttonCancel.TabIndex = 40;
            this.buttonCancel.Text = "&Close";
            this.buttonCancel.UseVisualStyleBackColor = true;
            // 
            // contextMenuStrip
            // 
            this.contextMenuStrip.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.selectToolStripMenuItem,
            this.unselectToolStripMenuItem,
            this.toolStripSeparator2,
            this.invertSelectionToolStripMenuItem});
            this.contextMenuStrip.Name = "contextMenuStrip";
            this.contextMenuStrip.Size = new System.Drawing.Size(135, 76);
            // 
            // invertSelectionToolStripMenuItem
            // 
            this.invertSelectionToolStripMenuItem.Name = "invertSelectionToolStripMenuItem";
            this.invertSelectionToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.invertSelectionToolStripMenuItem.Text = "Properties";
            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.fileToolStripMenuItem,
            this.editToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(416, 24);
            this.menuStrip1.TabIndex = 43;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // fileToolStripMenuItem
            // 
            this.fileToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.restoreAllToolStripMenuItem,
            this.restoreSelectedToolStripMenuItem1,
            this.toolStripSeparator1,
            this.closeToolStripMenuItem});
            this.fileToolStripMenuItem.Name = "fileToolStripMenuItem";
            this.fileToolStripMenuItem.Size = new System.Drawing.Size(35, 20);
            this.fileToolStripMenuItem.Text = "File";
            // 
            // restoreAllToolStripMenuItem
            // 
            this.restoreAllToolStripMenuItem.Name = "restoreAllToolStripMenuItem";
            this.restoreAllToolStripMenuItem.Size = new System.Drawing.Size(167, 22);
            this.restoreAllToolStripMenuItem.Text = "Restore &All";
            // 
            // restoreSelectedToolStripMenuItem1
            // 
            this.restoreSelectedToolStripMenuItem1.Name = "restoreSelectedToolStripMenuItem1";
            this.restoreSelectedToolStripMenuItem1.Size = new System.Drawing.Size(167, 22);
            this.restoreSelectedToolStripMenuItem1.Text = "Restore &Selected";
            // 
            // closeToolStripMenuItem
            // 
            this.closeToolStripMenuItem.Name = "closeToolStripMenuItem";
            this.closeToolStripMenuItem.Size = new System.Drawing.Size(167, 22);
            this.closeToolStripMenuItem.Text = "&Close";
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(164, 6);
            // 
            // editToolStripMenuItem
            // 
            this.editToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.selectAllToolStripMenuItem,
            this.invertSelectionToolStripMenuItem1});
            this.editToolStripMenuItem.Name = "editToolStripMenuItem";
            this.editToolStripMenuItem.Size = new System.Drawing.Size(37, 20);
            this.editToolStripMenuItem.Text = "Edit";
            // 
            // selectAllToolStripMenuItem
            // 
            this.selectAllToolStripMenuItem.Name = "selectAllToolStripMenuItem";
            this.selectAllToolStripMenuItem.Size = new System.Drawing.Size(161, 22);
            this.selectAllToolStripMenuItem.Text = "Select &All";
            // 
            // invertSelectionToolStripMenuItem1
            // 
            this.invertSelectionToolStripMenuItem1.Name = "invertSelectionToolStripMenuItem1";
            this.invertSelectionToolStripMenuItem1.Size = new System.Drawing.Size(161, 22);
            this.invertSelectionToolStripMenuItem1.Text = "&Invert Selection";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(30, 38);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(49, 13);
            this.label1.TabIndex = 45;
            this.label1.Text = "Drive ID:";
            this.label1.TextAlign = System.Drawing.ContentAlignment.TopRight;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(46, 51);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(33, 13);
            this.label2.TabIndex = 46;
            this.label2.Text = "Date:";
            this.label2.TextAlign = System.Drawing.ContentAlignment.TopRight;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(46, 64);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(34, 13);
            this.label3.TabIndex = 47;
            this.label3.Text = "Type:";
            this.label3.TextAlign = System.Drawing.ContentAlignment.TopRight;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(20, 77);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(59, 13);
            this.label4.TabIndex = 48;
            this.label4.Text = "Discription:";
            this.label4.TextAlign = System.Drawing.ContentAlignment.TopRight;
            // 
            // labelDriveID
            // 
            this.labelDriveID.Location = new System.Drawing.Point(89, 38);
            this.labelDriveID.Name = "labelDriveID";
            this.labelDriveID.Size = new System.Drawing.Size(308, 13);
            this.labelDriveID.TabIndex = 49;
            this.labelDriveID.Text = "label5";
            // 
            // labelDate
            // 
            this.labelDate.Location = new System.Drawing.Point(89, 51);
            this.labelDate.Name = "labelDate";
            this.labelDate.Size = new System.Drawing.Size(308, 13);
            this.labelDate.TabIndex = 50;
            this.labelDate.Text = "label6";
            // 
            // labelType
            // 
            this.labelType.Location = new System.Drawing.Point(89, 64);
            this.labelType.Name = "labelType";
            this.labelType.Size = new System.Drawing.Size(308, 13);
            this.labelType.TabIndex = 51;
            this.labelType.Text = "label7";
            // 
            // labelDescription
            // 
            this.labelDescription.Location = new System.Drawing.Point(89, 77);
            this.labelDescription.Name = "labelDescription";
            this.labelDescription.Size = new System.Drawing.Size(308, 13);
            this.labelDescription.TabIndex = 52;
            this.labelDescription.Text = "label8";
            // 
            // selectToolStripMenuItem
            // 
            this.selectToolStripMenuItem.Name = "selectToolStripMenuItem";
            this.selectToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.selectToolStripMenuItem.Text = "Select";
            // 
            // unselectToolStripMenuItem
            // 
            this.unselectToolStripMenuItem.Name = "unselectToolStripMenuItem";
            this.unselectToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.unselectToolStripMenuItem.Text = "Unselect";
            // 
            // toolStripSeparator2
            // 
            this.toolStripSeparator2.Name = "toolStripSeparator2";
            this.toolStripSeparator2.Size = new System.Drawing.Size(149, 6);
            // 
            // JounalContentsForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(416, 395);
            this.Controls.Add(this.labelDescription);
            this.Controls.Add(this.labelType);
            this.Controls.Add(this.labelDate);
            this.Controls.Add(this.labelDriveID);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.menuStrip1);
            this.Controls.Add(this.buttonCancel);
            this.Controls.Add(this.listView1);
            this.MainMenuStrip = this.menuStrip1;
            this.MinimumSize = new System.Drawing.Size(424, 422);
            this.Name = "JounalContentsForm";
            this.Text = "Flash Memory Manager - Contents";
            this.Load += new System.EventHandler(this.JounalContentsForm_Load);
            this.DoubleClick += new System.EventHandler(this.JounalContentsForm_DoubleClick);
            this.Activated += new System.EventHandler(this.JounalContentsForm_Activated);
            this.contextMenuStrip.ResumeLayout(false);
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListView listView1;
        private System.Windows.Forms.Button buttonCancel;
        private System.Windows.Forms.ColumnHeader chFile;
        private System.Windows.Forms.ImageList imageListStatus;
        private System.Windows.Forms.ColumnHeader chDate;
        private System.Windows.Forms.ColumnHeader chAttrib;
        private System.Windows.Forms.ContextMenuStrip contextMenuStrip;
        private System.Windows.Forms.ToolStripMenuItem invertSelectionToolStripMenuItem;
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem fileToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem restoreAllToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem restoreSelectedToolStripMenuItem1;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.ToolStripMenuItem closeToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem editToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem selectAllToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem invertSelectionToolStripMenuItem1;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label labelDriveID;
        private System.Windows.Forms.Label labelDate;
        private System.Windows.Forms.Label labelType;
        private System.Windows.Forms.Label labelDescription;
        private System.Windows.Forms.ToolStripMenuItem selectToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem unselectToolStripMenuItem;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
    }
>>>>>>> 291c508aa47ea3e34a225d431ef34192e909c4ee
}