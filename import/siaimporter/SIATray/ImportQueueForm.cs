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
        ImageList imageListSmall = new ImageList();
        public static ProgressDialog.ProgressDialog progressDialog = null;
        bool progressDialogOpen = false;
        public ImportQueueForm()
        {
            InitializeComponent();
            ImportQueue iq = ImportQueue.Instance;
            //iq.Add(new ImportJob("Z:\\Pictures\\Photos\\scotland rock\\pics"));
            listView.AllowColumnReorder = true;
            listView.FullRowSelect = true;
            
            // Initialize the ImageList objects with bitmaps.
            imageListSmall.Images.Add(SIATray.Properties.Resources.pending);
            imageListSmall.Images.Add(SIATray.Properties.Resources.inprogress);
            imageListSmall.Images.Add(SIATray.Properties.Resources.completed);

            listView.SmallImageList = imageListSmall;
            readQueue();
            iq.StatusChanged += OnStatusChanged;
        }

        public void readQueue()
        {
            listView.Items.Clear();

            foreach (ImportJob ij in ImportQueue.Instance)
            {
                ListViewItem lvi = new ListViewItem(ij.TrackingNo, 0);
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
        
        void OnStatusChanged(ImportStatus param) {
            if (param == ImportStatus.Pending)
            {
                readQueue();
                return;
            }
            if (param == ImportStatus.InProgress)
            {
                //readQueue();
                ImportQueue iq = ImportQueue.Instance;
                ListViewItem item = listView.FindItemWithText(iq.CurrentJob.TrackingNo);
                item.ImageIndex = 1;

            }
        }

        private void listView_MouseDoubleClick(object sender, MouseEventArgs e)
        {
            if (progressDialogOpen == false)
            {
                if (progressDialog != null)
                {
                    progressDialog = null;
                }
            }
            if (progressDialog == null)
            {
                progressDialog = new ProgressDialog.ProgressDialog();
                progressDialog.StatusChanged += OnProgressDialogStatusChanged; 
            }
            progressDialog.Show();
            progressDialog.WindowState = FormWindowState.Normal;
            progressDialogOpen = true;
        }

        void OnProgressDialogStatusChanged(ProgressDialog.ProgressDialog.Action action)
        {
            if (action == ProgressDialog.ProgressDialog.Action.Complete) 
            {
                progressDialogOpen = false;
            }
        }
    }
}
