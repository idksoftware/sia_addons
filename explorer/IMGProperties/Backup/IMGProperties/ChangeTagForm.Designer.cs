namespace IMGProperties
{
    partial class ChangeTagForm
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
            this.tagListBox = new System.Windows.Forms.ListBox();
            this.buttonAdd = new System.Windows.Forms.Button();
            this.buttonEdit = new System.Windows.Forms.Button();
            this.buttonDelete = new System.Windows.Forms.Button();
            this.buttonOk = new System.Windows.Forms.Button();
            this.buttonCancel = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // tagListBox
            // 
            this.tagListBox.FormattingEnabled = true;
            this.tagListBox.Location = new System.Drawing.Point(12, 24);
            this.tagListBox.Name = "tagListBox";
            this.tagListBox.Size = new System.Drawing.Size(114, 199);
            this.tagListBox.TabIndex = 27;
            // 
            // buttonAdd
            // 
            this.buttonAdd.Location = new System.Drawing.Point(149, 24);
            this.buttonAdd.Name = "buttonAdd";
            this.buttonAdd.Size = new System.Drawing.Size(75, 23);
            this.buttonAdd.TabIndex = 28;
            this.buttonAdd.Text = "Add";
            this.buttonAdd.UseVisualStyleBackColor = true;
            this.buttonAdd.Click += new System.EventHandler(this.buttonAdd_Click);
            // 
            // buttonEdit
            // 
            this.buttonEdit.Location = new System.Drawing.Point(149, 53);
            this.buttonEdit.Name = "buttonEdit";
            this.buttonEdit.Size = new System.Drawing.Size(75, 23);
            this.buttonEdit.TabIndex = 29;
            this.buttonEdit.Text = "Edit";
            this.buttonEdit.UseVisualStyleBackColor = true;
            this.buttonEdit.Click += new System.EventHandler(this.buttonEdit_Click);
            // 
            // buttonDelete
            // 
            this.buttonDelete.Location = new System.Drawing.Point(149, 82);
            this.buttonDelete.Name = "buttonDelete";
            this.buttonDelete.Size = new System.Drawing.Size(75, 23);
            this.buttonDelete.TabIndex = 30;
            this.buttonDelete.Text = "Delete";
            this.buttonDelete.UseVisualStyleBackColor = true;
            this.buttonDelete.Click += new System.EventHandler(this.buttonDelete_Click);
            // 
            // buttonOk
            // 
            this.buttonOk.DialogResult = System.Windows.Forms.DialogResult.OK;
            this.buttonOk.Location = new System.Drawing.Point(149, 238);
            this.buttonOk.Name = "buttonOk";
            this.buttonOk.Size = new System.Drawing.Size(75, 23);
            this.buttonOk.TabIndex = 31;
            this.buttonOk.Text = "&Ok";
            this.buttonOk.UseVisualStyleBackColor = true;
            // 
            // buttonCancel
            // 
            this.buttonCancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.buttonCancel.Location = new System.Drawing.Point(68, 238);
            this.buttonCancel.Name = "buttonCancel";
            this.buttonCancel.Size = new System.Drawing.Size(75, 23);
            this.buttonCancel.TabIndex = 32;
            this.buttonCancel.Text = "&Cancel";
            this.buttonCancel.UseVisualStyleBackColor = true;
            // 
            // ChangeTagForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(242, 273);
            this.Controls.Add(this.buttonCancel);
            this.Controls.Add(this.buttonOk);
            this.Controls.Add(this.buttonDelete);
            this.Controls.Add(this.buttonEdit);
            this.Controls.Add(this.buttonAdd);
            this.Controls.Add(this.tagListBox);
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "ChangeTagForm";
            this.SizeGripStyle = System.Windows.Forms.SizeGripStyle.Hide;
            this.Text = "Change Tag Form";
            this.Load += new System.EventHandler(this.Form_Load);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ListBox tagListBox;
        private System.Windows.Forms.Button buttonAdd;
        private System.Windows.Forms.Button buttonEdit;
        private System.Windows.Forms.Button buttonDelete;
        private System.Windows.Forms.Button buttonOk;
        private System.Windows.Forms.Button buttonCancel;
    }
}