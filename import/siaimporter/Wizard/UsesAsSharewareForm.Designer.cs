namespace IDK.Gui
{
    partial class UsesAsSharewareForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(UsesAsSharewareForm));
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.logoPictureBox = new System.Windows.Forms.PictureBox();
            this.buttoOk = new System.Windows.Forms.Button();
            this.labelYouHave = new System.Windows.Forms.Label();
            this.labelEvaluale = new System.Windows.Forms.Label();
            this.labelDays = new System.Windows.Forms.Label();
            this.labelDaysRemaining = new System.Windows.Forms.Label();
            this.labelElapsed = new System.Windows.Forms.Label();
            this.buttonLicence = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.logoPictureBox)).BeginInit();
            this.SuspendLayout();
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(102, 224);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(100, 13);
            this.label3.TabIndex = 26;
            this.label3.Text = "All Rights Reserved";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(102, 211);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(192, 13);
            this.label2.TabIndex = 25;
            this.label2.Text = "Copyright 1997-1998 IDK Software Ltd.";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(102, 22);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(231, 16);
            this.label1.TabIndex = 24;
            this.label1.Text = "Flash Memory Manager Shareware - version 1.0";
            // 
            // logoPictureBox
            // 
            this.logoPictureBox.Image = ((System.Drawing.Image)(resources.GetObject("logoPictureBox.Image")));
            this.logoPictureBox.Location = new System.Drawing.Point(12, 12);
            this.logoPictureBox.Name = "logoPictureBox";
            this.logoPictureBox.Size = new System.Drawing.Size(84, 259);
            this.logoPictureBox.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.logoPictureBox.TabIndex = 14;
            this.logoPictureBox.TabStop = false;
            // 
            // buttoOk
            // 
            this.buttoOk.DialogResult = System.Windows.Forms.DialogResult.OK;
            this.buttoOk.Location = new System.Drawing.Point(294, 249);
            this.buttoOk.Name = "buttoOk";
            this.buttoOk.Size = new System.Drawing.Size(75, 23);
            this.buttoOk.TabIndex = 28;
            this.buttoOk.Text = "&Ok";
            this.buttoOk.UseVisualStyleBackColor = true;
            // 
            // labelYouHave
            // 
            this.labelYouHave.Location = new System.Drawing.Point(102, 55);
            this.labelYouHave.Name = "labelYouHave";
            this.labelYouHave.Size = new System.Drawing.Size(59, 17);
            this.labelYouHave.TabIndex = 29;
            this.labelYouHave.Text = "You have ";
            // 
            // labelEvaluale
            // 
            this.labelEvaluale.Location = new System.Drawing.Point(102, 72);
            this.labelEvaluale.Name = "labelEvaluale";
            this.labelEvaluale.Size = new System.Drawing.Size(231, 35);
            this.labelEvaluale.TabIndex = 30;
            this.labelEvaluale.Text = "to evaluate the software. After that time please purchase a licence for IDK Softw" +
                "are Ltd.";
            // 
            // labelDays
            // 
            this.labelDays.Location = new System.Drawing.Point(179, 55);
            this.labelDays.Name = "labelDays";
            this.labelDays.Size = new System.Drawing.Size(38, 17);
            this.labelDays.TabIndex = 31;
            this.labelDays.Text = "days";
            // 
            // labelDaysRemaining
            // 
            this.labelDaysRemaining.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelDaysRemaining.Location = new System.Drawing.Point(156, 53);
            this.labelDaysRemaining.Name = "labelDaysRemaining";
            this.labelDaysRemaining.Size = new System.Drawing.Size(26, 16);
            this.labelDaysRemaining.TabIndex = 32;
            this.labelDaysRemaining.Text = "30";
            // 
            // labelElapsed
            // 
            this.labelElapsed.Location = new System.Drawing.Point(102, 107);
            this.labelElapsed.Name = "labelElapsed";
            this.labelElapsed.Size = new System.Drawing.Size(231, 35);
            this.labelElapsed.TabIndex = 33;
            this.labelElapsed.Text = "Evaluation time has elapsed, Please purchase a licence for IDK Software Ltd.";
            // 
            // buttonLicence
            // 
            this.buttonLicence.DialogResult = System.Windows.Forms.DialogResult.OK;
            this.buttonLicence.Location = new System.Drawing.Point(182, 249);
            this.buttonLicence.Name = "buttonLicence";
            this.buttonLicence.Size = new System.Drawing.Size(94, 23);
            this.buttonLicence.TabIndex = 34;
            this.buttonLicence.Text = "&Licence";
            this.buttonLicence.UseVisualStyleBackColor = true;
            this.buttonLicence.Click += new System.EventHandler(this.buttonLicence_Click);
            // 
            // UsesAsSharewareForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(375, 279);
            this.Controls.Add(this.buttonLicence);
            this.Controls.Add(this.labelElapsed);
            this.Controls.Add(this.labelDaysRemaining);
            this.Controls.Add(this.labelDays);
            this.Controls.Add(this.labelEvaluale);
            this.Controls.Add(this.labelYouHave);
            this.Controls.Add(this.buttoOk);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.logoPictureBox);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "UsesAsSharewareForm";
            this.SizeGripStyle = System.Windows.Forms.SizeGripStyle.Hide;
            this.Text = "Flash Memory Manager 1.0 - Shareware";
            ((System.ComponentModel.ISupportInitialize)(this.logoPictureBox)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.PictureBox logoPictureBox;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button buttoOk;
        private System.Windows.Forms.Label labelYouHave;
        private System.Windows.Forms.Label labelEvaluale;
        private System.Windows.Forms.Label labelDays;
        private System.Windows.Forms.Label labelDaysRemaining;
        private System.Windows.Forms.Label labelElapsed;
        private System.Windows.Forms.Button buttonLicence;
    }
}