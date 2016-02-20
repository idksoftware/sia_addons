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
        int lastReadItems = 0;
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
            timerUpdate.Start();
        }


        int GetStatusID(ImportStatus status)
        {
            switch(status) {
                case  ImportStatus.Pending:
                    return 0;
                case  ImportStatus.InProgress:
                    return 1;
                case  ImportStatus.Completed:
                    return 2;
                default:
                    return 2;
            }
       
        }
        public int readQueue()
        {
            listView.Items.Clear();

            foreach (ImportJob ij in ImportQueue.Instance)
            {
                ListViewItem lvi = new ListViewItem(ij.TrackingNo, GetStatusID(ij.ImportStatus));
                lvi.SubItems.Add(ij.DateTime.ToShortDateString());
                lvi.SubItems.Add(ij.Location);
                lvi.SubItems.Add(ij.SubFolders.ToString());
                lvi.SubItems.Add(ij.ImportStatus.ToString());
                listView.Items.Add(lvi);
            }
            return listView.Items.Count;
        }

        public int updateStatus()
        {
            ImportQueue importQueue = ImportQueue.Instance;
            foreach (ListViewItem lvi in listView.Items)
            {
                ImportJob job = importQueue.FindJob(lvi.Text);
                if (job != null)
                {
                    lvi.ImageIndex = GetStatusID(job.ImportStatus);
                }
            }
            return listView.Items.Count;
        }
        private void ImportQueueForm_FormClosing(object sender, FormClosingEventArgs e)
        {
           
        }
        
        void OnStatusChanged(ImportStatus param) {
            updateStatus();
        }

        private void listView_MouseDoubleClick(object sender, MouseEventArgs e)
        {
            /*
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
             */
        }

        /*
        void OnProgressDialogStatusChanged(ProgressDialog.ProgressDialog.Action action)
        {
            if (action == ProgressDialog.ProgressDialog.Action.Complete) 
            {
                progressDialogOpen = false;
            }
        }
        */
        private void buttonImportFromFolder_Click(object sender, EventArgs e)
        {
            FolderImportForm form = new FolderImportForm();
            DialogResult res = form.ShowDialog();
            if (res == DialogResult.Cancel)
            {
                return;
            }
        }

        private void buttonImportFromDrive_Click(object sender, EventArgs e)
        {
            DriveForm form = new DriveForm();
            DialogResult res = form.ShowDialog();
            if (res == DialogResult.Cancel)
            {
                return;
            }
        }

        private void buttonClose_Click(object sender, EventArgs e)
        {
            this.Close();
            MainForm.importQueueForm = null;
        }

        private void timerUpdate_Tick(object sender, EventArgs e)
        {
            ImportQueue importQueue = ImportQueue.Instance;
            if (importQueue.Count != lastReadItems)
            {
                lastReadItems = readQueue();
            }
            updateStatus();
        }

        
    }
}
