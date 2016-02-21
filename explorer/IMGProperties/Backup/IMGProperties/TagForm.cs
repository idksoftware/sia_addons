using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace IMGProperties
{
    public partial class TagForm : Form
    {
        public String editTag = null;

        public TagForm(String edit)
        {
            InitializeComponent();
            editTag = edit;
            textBoxAddTag.Text = editTag;
        }

        public String tag { get { return textBoxAddTag.Text; } }
    }
}