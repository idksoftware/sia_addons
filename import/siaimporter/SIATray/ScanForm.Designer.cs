<<<<<<< HEAD
namespace SIATray
{
    partial class ScanForm
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
            this.labelInfoOnNewDrive = new System.Windows.Forms.Label();
            this.buttonCancel = new System.Windows.Forms.Button();
            this.label3 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.progressBar = new System.Windows.Forms.ProgressBar();
            this.label2 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // labelInfoOnNewDrive
            // 
            this.labelInfoOnNewDrive.AutoSize = true;
            this.labelInfoOnNewDrive.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelInfoOnNewDrive.Location = new System.Drawing.Point(18, 14);
            this.labelInfoOnNewDrive.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.labelInfoOnNewDrive.Name = "labelInfoOnNewDrive";
            this.labelInfoOnNewDrive.Size = new System.Drawing.Size(226, 25);
            this.labelInfoOnNewDrive.TabIndex = 0;
            this.labelInfoOnNewDrive.Text = "Scan drive infomation ";
            // 
            // buttonCancel
            // 
            this.buttonCancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.buttonCancel.Location = new System.Drawing.Point(273, 212);
            this.buttonCancel.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.buttonCancel.Name = "buttonCancel";
            this.buttonCancel.Size = new System.Drawing.Size(124, 35);
            this.buttonCancel.TabIndex = 36;
            this.buttonCancel.Text = "&Cancel";
            this.buttonCancel.UseVisualStyleBackColor = true;
            // 
            // label3
            // 
            this.label3.Location = new System.Drawing.Point(18, 55);
            this.label3.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(380, 48);
            this.label3.TabIndex = 38;
            this.label3.Text = "Use this to scan the drive so SIA can establish if a images are found";
            // 
            // label1
            // 
            this.label1.Location = new System.Drawing.Point(18, 252);
            this.label1.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(380, 48);
            this.label1.TabIndex = 39;
            this.label1.Text = "Note: This can take a few minutes to scan a large drive.";
            // 
            // progressBar
            // 
            this.progressBar.Location = new System.Drawing.Point(18, 108);
            this.progressBar.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.progressBar.Name = "progressBar";
            this.progressBar.Size = new System.Drawing.Size(375, 35);
            this.progressBar.TabIndex = 40;
            // 
            // label2
            // 
            this.label2.Location = new System.Drawing.Point(18, 148);
            this.label2.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(152, 23);
            this.label2.TabIndex = 41;
            this.label2.Text = "Scanning ...";
            // 
            // ScanForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(416, 302);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.progressBar);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.buttonCancel);
            this.Controls.Add(this.labelInfoOnNewDrive);
            this.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.Name = "ScanForm";
            this.Text = "Flash Memory Manager - Backup";
            this.Load += new System.EventHandler(this.ScanForm_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label labelInfoOnNewDrive;
        private System.Windows.Forms.Button buttonCancel;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ProgressBar progressBar;
        private System.Windows.Forms.Label label2;
    }
=======
namespace SIATray
{
    partial class ScanForm
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
            this.labelInfoOnNewDrive = new System.Windows.Forms.Label();
            this.buttonCancel = new System.Windows.Forms.Button();
            this.label3 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.progressBar = new System.Windows.Forms.ProgressBar();
            this.label2 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // labelInfoOnNewDrive
            // 
            this.labelInfoOnNewDrive.AutoSize = true;
            this.labelInfoOnNewDrive.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelInfoOnNewDrive.Location = new System.Drawing.Point(18, 14);
            this.labelInfoOnNewDrive.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.labelInfoOnNewDrive.Name = "labelInfoOnNewDrive";
            this.labelInfoOnNewDrive.Size = new System.Drawing.Size(226, 25);
            this.labelInfoOnNewDrive.TabIndex = 0;
            this.labelInfoOnNewDrive.Text = "Scan drive infomation ";
            // 
            // buttonCancel
            // 
            this.buttonCancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.buttonCancel.Location = new System.Drawing.Point(273, 212);
            this.buttonCancel.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.buttonCancel.Name = "buttonCancel";
            this.buttonCancel.Size = new System.Drawing.Size(124, 35);
            this.buttonCancel.TabIndex = 36;
            this.buttonCancel.Text = "&Cancel";
            this.buttonCancel.UseVisualStyleBackColor = true;
            // 
            // label3
            // 
            this.label3.Location = new System.Drawing.Point(18, 55);
            this.label3.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(380, 48);
            this.label3.TabIndex = 38;
            this.label3.Text = "Use this to scan the drive so SIA can establish if a images are found";
            // 
            // label1
            // 
            this.label1.Location = new System.Drawing.Point(18, 252);
            this.label1.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(380, 48);
            this.label1.TabIndex = 39;
            this.label1.Text = "Note: This can take a few minutes to scan a large drive.";
            // 
            // progressBar
            // 
            this.progressBar.Location = new System.Drawing.Point(18, 108);
            this.progressBar.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.progressBar.Name = "progressBar";
            this.progressBar.Size = new System.Drawing.Size(375, 35);
            this.progressBar.TabIndex = 40;
            // 
            // label2
            // 
            this.label2.Location = new System.Drawing.Point(18, 148);
            this.label2.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(152, 23);
            this.label2.TabIndex = 41;
            this.label2.Text = "Scanning ...";
            // 
            // ScanForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(416, 302);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.progressBar);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.buttonCancel);
            this.Controls.Add(this.labelInfoOnNewDrive);
            this.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.Name = "ScanForm";
            this.Text = "Flash Memory Manager - Backup";
            this.Load += new System.EventHandler(this.ScanForm_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label labelInfoOnNewDrive;
        private System.Windows.Forms.Button buttonCancel;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ProgressBar progressBar;
        private System.Windows.Forms.Label label2;
    }
>>>>>>> 291c508aa47ea3e34a225d431ef34192e909c4ee
}