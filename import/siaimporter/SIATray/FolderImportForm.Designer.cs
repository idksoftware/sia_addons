namespace SIATray
{
    partial class FolderImportForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(FolderImportForm));
            this.label1 = new System.Windows.Forms.Label();
            this.importFolder = new System.Windows.Forms.TextBox();
            this.buttonBrowse = new System.Windows.Forms.Button();
            this.checkBoxSubFolders = new System.Windows.Forms.CheckBox();
            this.buttonOk = new System.Windows.Forms.Button();
            this.buttonCancal = new System.Windows.Forms.Button();
            this.importSourceBrowserDialog = new System.Windows.Forms.FolderBrowserDialog();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(23, 27);
            this.label1.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(104, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "Source import folder:";
            // 
            // importFolder
            // 
            this.importFolder.Location = new System.Drawing.Point(26, 47);
            this.importFolder.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.importFolder.Name = "importFolder";
            this.importFolder.Size = new System.Drawing.Size(349, 20);
            this.importFolder.TabIndex = 1;
            // 
            // buttonBrowse
            // 
            this.buttonBrowse.Location = new System.Drawing.Point(377, 47);
            this.buttonBrowse.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.buttonBrowse.Name = "buttonBrowse";
            this.buttonBrowse.Size = new System.Drawing.Size(61, 26);
            this.buttonBrowse.TabIndex = 2;
            this.buttonBrowse.Text = "Browse";
            this.buttonBrowse.UseVisualStyleBackColor = true;
            this.buttonBrowse.Click += new System.EventHandler(this.buttonBrowse_Click);
            // 
            // checkBoxSubFolders
            // 
            this.checkBoxSubFolders.AutoSize = true;
            this.checkBoxSubFolders.Checked = true;
            this.checkBoxSubFolders.CheckState = System.Windows.Forms.CheckState.Checked;
            this.checkBoxSubFolders.Location = new System.Drawing.Point(26, 73);
            this.checkBoxSubFolders.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.checkBoxSubFolders.Name = "checkBoxSubFolders";
            this.checkBoxSubFolders.Size = new System.Drawing.Size(109, 17);
            this.checkBoxSubFolders.TabIndex = 3;
            this.checkBoxSubFolders.Text = "Import sub-folders";
            this.checkBoxSubFolders.UseVisualStyleBackColor = true;
            // 
            // buttonOk
            // 
            this.buttonOk.DialogResult = System.Windows.Forms.DialogResult.OK;
            this.buttonOk.Location = new System.Drawing.Point(307, 115);
            this.buttonOk.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.buttonOk.Name = "buttonOk";
            this.buttonOk.Size = new System.Drawing.Size(50, 25);
            this.buttonOk.TabIndex = 4;
            this.buttonOk.Text = "&Ok";
            this.buttonOk.UseVisualStyleBackColor = true;
            this.buttonOk.Click += new System.EventHandler(this.buttonOk_Click);
            // 
            // buttonCancal
            // 
            this.buttonCancal.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.buttonCancal.Location = new System.Drawing.Point(377, 115);
            this.buttonCancal.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.buttonCancal.Name = "buttonCancal";
            this.buttonCancal.Size = new System.Drawing.Size(50, 25);
            this.buttonCancal.TabIndex = 5;
            this.buttonCancal.Text = "&Cancel";
            this.buttonCancal.UseVisualStyleBackColor = true;
            // 
            // FolderImportForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(463, 155);
            this.Controls.Add(this.buttonCancal);
            this.Controls.Add(this.buttonOk);
            this.Controls.Add(this.checkBoxSubFolders);
            this.Controls.Add(this.buttonBrowse);
            this.Controls.Add(this.importFolder);
            this.Controls.Add(this.label1);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.Name = "FolderImportForm";
            this.Text = "Folder Import";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox importFolder;
        private System.Windows.Forms.Button buttonBrowse;
        private System.Windows.Forms.CheckBox checkBoxSubFolders;
        private System.Windows.Forms.Button buttonOk;
        private System.Windows.Forms.Button buttonCancal;
        private System.Windows.Forms.FolderBrowserDialog importSourceBrowserDialog;
    }
}