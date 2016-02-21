namespace IMGProperties
{
    partial class KeywordForm
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
            this.textBoxAddKeyword = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.buttonOk = new System.Windows.Forms.Button();
            this.ButtonCancel = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // textBoxAddKeyword
            // 
            this.textBoxAddKeyword.Location = new System.Drawing.Point(78, 15);
            this.textBoxAddKeyword.Name = "textBoxAddKeyword";
            this.textBoxAddKeyword.Size = new System.Drawing.Size(100, 20);
            this.textBoxAddKeyword.TabIndex = 7;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(18, 18);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(51, 13);
            this.label1.TabIndex = 6;
            this.label1.Text = "Keyword:";
            // 
            // buttonOk
            // 
            this.buttonOk.DialogResult = System.Windows.Forms.DialogResult.OK;
            this.buttonOk.Location = new System.Drawing.Point(120, 64);
            this.buttonOk.Name = "buttonOk";
            this.buttonOk.Size = new System.Drawing.Size(75, 23);
            this.buttonOk.TabIndex = 5;
            this.buttonOk.Text = "&Ok";
            this.buttonOk.UseVisualStyleBackColor = true;
            // 
            // ButtonCancel
            // 
            this.ButtonCancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.ButtonCancel.Location = new System.Drawing.Point(39, 64);
            this.ButtonCancel.Name = "ButtonCancel";
            this.ButtonCancel.Size = new System.Drawing.Size(75, 23);
            this.ButtonCancel.TabIndex = 4;
            this.ButtonCancel.Text = "&Cancel";
            this.ButtonCancel.UseVisualStyleBackColor = true;
            // 
            // KeywordForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(204, 98);
            this.Controls.Add(this.textBoxAddKeyword);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.buttonOk);
            this.Controls.Add(this.ButtonCancel);
            this.Name = "KeywordForm";
            this.Text = "New Keyword";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox textBoxAddKeyword;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button buttonOk;
        private System.Windows.Forms.Button ButtonCancel;
    }
}