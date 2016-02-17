using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.Diagnostics;
using System.IO;

namespace SIATray
{
    

    public partial class DriveForm : Form
    {
        ImageList imglstT = null;
        String driveID = null;
       // Jounal jounal = new Jounal();
      
        public DriveForm()
        {
            InitializeComponent();

            imglstT = new ImageList();
            imglstT.ImageSize = new Size(8, 8);

            imglstT.Images.Add(new Bitmap(GetType(), "NeedsBackup.bmp"));
            imglstT.Images.Add(new Bitmap(GetType(), "Backedup.bmp"));

            Drives();
        }
       

        public void SetLabels(DriveBackup db)
        {
            //this.groupBoxDriveID.Text = db.IsDriveIdentified
            labelDateLastBackedUp.Text = db.LastBackedUp.ToString();
            labelTotalAvailableSpace.Text = SizeLabel(db.AvailableFreeSpace);
            labelTotalSizeOfDrive.Text = SizeLabel(db.TotalSize);
            labelImagesFound.Text = db.NeedsBackingUp.ToString();
            labelTotalAvailableSpace.Text = SizeLabel(db.TotalFreeSpace);
            labelVolumeLabel.Text = db.VolumeLabel;
           
        }

        public void Drives()
        {
            DriveInfo[] allDrives = DriveInfo.GetDrives();
            listView.Items.Clear();

            foreach (DriveBackup db in BackupManager.DriveList)
            {
                Debug.Print("Drive {0}", db.DriveLetter);
                Debug.Print("  File type: {0}", db.DriveType);
                         
                BackupManager.CurrentDB = db;
               
                Debug.Print("  Volume label: {0}", db.VolumeLabel);
                Debug.Print(
                    "  Available space to current user:{0, 15} bytes",
                    db.AvailableFreeSpace);

                Debug.Print(
                    "  Total available space:          {0, 15} bytes",
                    db.TotalFreeSpace);

                Debug.Print(
                    "  Total size of drive:            {0, 15} bytes ",
                    db.TotalSize);

                Debug.Print(
                    "  Drive letter:            {0, 15} ",
                    db.DriveLetter);

                ListViewItem lvi = new ListViewItem(db.DriveLetter);
                       
                lvi.StateImageIndex = 0;
                if (db.VolumeLabel.Length == 0)
                {
                    labelVolumeLabel.Text = "Not Set";
                }
                else
                {
                    labelVolumeLabel.Text = db.VolumeLabel;
                }

                Debug.Print(
                    "  Volume label:            {0, 15} ",
                    db.VolumeLabel);

                
                if (db.NeedsScanning == true)
                {
                    lvi.SubItems.Add("Yes");
                }
                else
                {
                    lvi.SubItems.Add("No");
                }
                Debug.Print(
                    "       Drive ID:            {0, 15} ",
                    db.DriveID);

                listView.Items.Add(lvi);
                if (listView.Items.Count > 0)
                {
                    SetLabels(BackupManager.GetDriveBackupObject(0));
                    this.listView.Items[0].Focused = true;
                    this.listView.Items[0].Selected = true;
                }
                      
                    
               
            }
            if (listView.Items.Count == 0)
            {
                // List is empty? disable the backup button
                buttonImport.Enabled = false;
                labelDateLastBackedUp.Text = "";
                labelTotalAvailableSpace.Text = "";
                labelTotalSizeOfDrive.Text = "";
                labelImagesFound.Text = "";
                labelTotalAvailableSpace.Text = "";

            }
        }

        public bool DoesNeedBackup(DriveBackup db)
        {
            if (db != null && db.DriveID != null)
            {
                db.Jounal.ScanDriveDirectories(db.DriveLetter);
                if (db.DoesNeedBackup() == true)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            // As the drive has no Drive ID the system will have no record of backups
            // so it needs backing up
            return true;
        }
        /*
        private void ScanDriveDirectories()
        {
            ScanDirectory scanDirectory = new ScanDirectory();
            // Add a DirectoryEvent to the class
            scanDirectory.DirectoryEvent += new ScanDirectory.DirectoryEventHandler(scanDirectory_DirectoryEvent);

            // Add a FileEvent to the class
            scanDirectory.FileEvent += new ScanDirectory.FileEventHandler(scanDirectory_FileEvent);
  
            scanDirectory.SearchPattern = "*.*";
            scanDirectory.WalkDirectory(BackupManager.CurrentDB.DriveLetter);
        }
        */
        public String SizeLabel(long size)
        {
            if (size < 999)
            {
                return size.ToString() + " bytes (" + size.ToString() + " bytes)";
            }
            if (size < 999999)
            {

                String sizeStr = size.ToString().Substring(0, size.ToString().Length - 3);
                return sizeStr + " Kb (" + size.ToString() + " bytes)";
            }
            if (size < 999999999)
            {

                String sizeStr = size.ToString().Substring(0, size.ToString().Length - 6);
                return sizeStr + " Mb (" + size.ToString() + " bytes)";
            }
            if (size > 999999999)
            {

                String sizeStr = size.ToString().Substring(0, size.ToString().Length - 9);
                return sizeStr + " Gb (" + size.ToString() + " bytes)";
            }
            return "";
        }

        private void listView_DoubleClick(object sender, EventArgs e)
        {
            ListViewItem lvi = listView.SelectedItems[0];
            ListViewItem.ListViewSubItem lviSub = lvi.SubItems[1];
            String DriverID = lviSub.Text;
            if (DriverID.Equals("Not Set") == true)
            {
                // Messagebox Drive ID not set
                return;
            }
            DriveBackup db = BackupManager.GetDriveBackupObject(DriverID);
            //BackupHistoryForm form = new BackupHistoryForm(db);
            
            //form.ShowDialog();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void buttonBackup_Click(object sender, EventArgs e)
        {
            if (BackupManager.CurrentDB == null)
            {
                return;
            }
            if (BackupManager.CurrentDB.DriveID == null || BackupManager.CurrentDB.DriveID.Length == 0) {
                //NeedsDriveIDForm form = new NeedsDriveIDForm();
                //DialogResult res = form.ShowDialog();
                //if (res == DialogResult.Cancel)
                //{
                //    return;
                //}
                DriveBackup.WriteDriveID(BackupManager.CurrentDB.DriveLetter, BackupManager.CurrentDB.DriveID);
            }
            //BackupForm backupForm = new BackupForm(BackupManager.CurrentDB);
            //backupForm.ShowDialog();
            //backupForm.Dispose();
            /*
          
            if (BackupManager.CurrentDB.DriveID == null)
            {
                NeedsDriveIDForm form = new NeedsDriveIDForm();

                if (form.ShowDialog() == DialogResult.OK)
                {
                    BackupManager.CurrentDB.DriveID = form.DriveID;
                }
                DriveBackup.WriteDriveID(BackupManager.CurrentDB.DriveLetter, BackupManager.CurrentDB.DriveID);
            }
            BackupManager.CurrentDB.Jounal.ScanDriveDirectories(BackupManager.CurrentDB.DriveLetter);
            BackupManager.FullBackup(BackupManager.CurrentDB);
             */
        }

        /// <summary>
        /// Handles the DirectoryEvent event of the scanDirectory control.
        /// </summary>
        /// <param name="sender">The source of the event.</param>
        /// <param name="e">The <see cref="DirectoryEventArgs"/> instance containing the event data.</param>
        /*
        private void scanDirectory_DirectoryEvent(object sender, DirectoryEventArgs e)
        {

            switch (e.Action)
            {
                case ScanDirectoryAction.Enter:
                    FolderItem fi = new FolderItem();
                    fi.path = e.Info.FullName;
                    BackupManager.CurrentDB.FileList.Add(fi);
                    break;
                case ScanDirectoryAction.Leave:
                    break;
            }
            //e.Cancel = !buttonCancel.Enabled;

        }

        /// <summary>
        /// Handles the FileEvent event of the scanDirectory control.
        /// </summary>
        /// <param name="sender">The source of the event.</param>
        /// <param name="e">The <see cref="FileEventArgs"/> instance containing the event data.</param>
        private void scanDirectory_FileEvent(object sender, FileEventArgs e)
        {
            FileItem fi = new FileItem();
            fi.path = e.Info.FullName;

            BackupManager.CurrentDB.FileList.Add(fi);
            //e.Cancel = !buttonCancel.Enabled;       
        }
      */
        private void listView_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (listView.SelectedItems.Count == 0)
            {
                return;
            }
            //buttonDriveID.Enabled = true;
            ListViewItem lvi = listView.SelectedItems[0];
            DriveBackup db = BackupManager.GetDriveBackupObject(lvi.Index);
            BackupManager.CurrentDB = db;
            SetLabels(db);
            
           
        }

        private void buttonDriveID_Click(object sender, EventArgs e)
        {
            if (listView.SelectedIndices.Count == 0)
            {
                return;
            }
            //buttonDriveID.Enabled = true;
            /*NeedsDriveIDForm form = new NeedsDriveIDForm();
            if (driveID != null)
            {
                form.DriveID = driveID;
            }
            if (DialogResult.OK == form.ShowDialog())
            {
                driveID = form.DriveID;
                
                ListViewItem lvi = listView.SelectedItems[0];
                lvi.SubItems[1].Text = driveID;
                String DriveLetter = lvi.SubItems[0].Text;
                if (DriveLetter == null)
                {
                    return;
                }
                if (DriveBackup.WriteDriveID(DriveLetter, driveID))
                {
                    return;
                }
            }
             */
        }

        private void buttonRefresh_Click(object sender, EventArgs e)
        {
            ListViewItem lvi = listView.SelectedItems[0];
            DriveBackup db = BackupManager.GetDriveBackupObject(lvi.Index);
            BackupManager.CurrentDB = db;
            ScanForm form = new ScanForm(db);
            form.ShowDialog();
            form.Dispose();
            /*
            //labelAction.Text = "Scanning drive";
            if (DoesNeedBackup(db) == true)
            {

            }
            //labelAction.Text = "Complete";
             */
        }
    }
}