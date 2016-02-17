using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace SIATray
{
    public partial class ImportQueueForm : Form
    {
        public ImportQueueForm()
        {
            InitializeComponent();
            ImportQueue iq = ImportQueue.Instance;
            iq.Add(new ImportJob("Z:\\Pictures\\Photos\\scotland rock\\pics"));
            readQueue();
        }

        void readQueue()
        {
            listView.Items.Clear();

            foreach (ImportJob ij in ImportQueue.Instance)
            {
                ListViewItem lvi = new ListViewItem(ij.TrackingNo);
                lvi.SubItems.Add(ij.DateTime.ToShortDateString());
                lvi.SubItems.Add(ij.Location);
                lvi.SubItems.Add(ij.SubFolders.ToString());
                lvi.SubItems.Add(ij.ImportStatus.ToString());
                listView.Items.Add(lvi);
            }
        }

        private void ImportQueueForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            MainForm.importQueueForm = null;
        }
        
    }
}
