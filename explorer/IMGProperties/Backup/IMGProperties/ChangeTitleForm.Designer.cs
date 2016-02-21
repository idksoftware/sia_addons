namespace IMGProperties
{
    partial class ChangeTitleForm
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
            this.textBoxTitle = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.textBoxSubject = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.buttonOk = new System.Windows.Forms.Button();
            this.ButtonCancel = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // textBoxTitle
            // 
            this.textBoxTitle.Location = new System.Drawing.Point(12, 25);
            this.textBoxTitle.Name = "textBoxTitle";
            this.textBoxTitle.Size = new System.Drawing.Size(278, 20);
            this.textBoxTitle.TabIndex = 5;
            this.textBoxTitle.TextChanged += new System.EventHandler(this.textBoxTitle_TextChanged);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 9);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(30, 13);
            this.label1.TabIndex = 4;
            this.label1.Text = "Title:";
            // 
            // textBoxSubject
            // 
            this.textBoxSubject.Location = new System.Drawing.Point(12, 70);
            this.textBoxSubject.Name = "textBoxSubject";
            this.textBoxSubject.Size = new System.Drawing.Size(278, 20);
            this.textBoxSubject.TabIndex = 7;
            this.textBoxSubject.TextChanged += new System.EventHandler(this.textBoxSubject_TextChanged);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(12, 54);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(46, 13);
            this.label2.TabIndex = 6;
            this.label2.Text = "Subject:";
            // 
            // buttonOk
            // 
            this.buttonOk.DialogResult = System.Windows.Forms.DialogResult.OK;
            this.buttonOk.Location = new System.Drawing.Point(217, 121);
            this.buttonOk.Name = "buttonOk";
            this.buttonOk.Size = new System.Drawing.Size(75, 23);
            this.buttonOk.TabIndex = 9;
            this.buttonOk.Text = "&Ok";
            this.buttonOk.UseVisualStyleBackColor = true;
            this.buttonOk.Click += new System.EventHandler(this.buttonOk_Click);
            // 
            // ButtonCancel
            // 
            this.ButtonCancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.ButtonCancel.Location = new System.Drawing.Point(136, 121);
            this.ButtonCancel.Name = "ButtonCancel";
            this.ButtonCancel.Size = new System.Drawing.Size(75, 23);
            this.ButtonCancel.TabIndex = 8;
            this.ButtonCancel.Text = "&Cancel";
            this.ButtonCancel.UseVisualStyleBackColor = true;
            // 
            // ChangeTitleForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(302, 156);
            this.Controls.Add(this.buttonOk);
            this.Controls.Add(this.ButtonCancel);
            this.Controls.Add(this.textBoxSubject);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.textBoxTitle);
            this.Controls.Add(this.label1);
            this.Name = "ChangeTitleForm";
            this.SizeGripStyle = System.Windows.Forms.SizeGripStyle.Hide;
            this.Text = "TitleForm";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox textBoxTitle;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox textBoxSubject;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button buttonOk;
        private System.Windows.Forms.Button ButtonCancel;

        public string Title { get { return textBoxTitle.Text; } }
        public string Subject { get { return textBoxSubject.Text; } }
    }
}