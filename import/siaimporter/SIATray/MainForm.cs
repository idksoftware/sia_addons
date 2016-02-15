using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using IADetectDrive;
using System.Threading;
using System.IO;
using System.Diagnostics;
using IDK.Gui;


namespace SIATray
{
   
    public partial class MainForm : Form
    {
        DriveDetector driveDetector = new DriveDetector();
        static DriveBackup driveBackup = null;
        private BackupManager backupManager;
        private static List<Form> processList = new List<Form>();

        enum EDriveOperation
        {
            eDeviceArrived,
            eDriveRemoved,
            eQueryRemove,
            eNone
        }

        enum Operation
        {
            eAccessingDrive,
            eInitSearch,
            eSearchFiles,
            eSearchingFiles,
            eFileFindComplete,
            eNone
        }

        private static bool hasBackupStarted = false;
        
        private static bool IsCancel = false;
        private static bool toggle = false;
        private static String path = "";
        private static int filesDone = 0;
        private static int filesCurr = 0;
        private static int noFilesFound = 0;
        private static bool hasFlashDriveInstalled = false;
        
        Thread thread = null;
        
        private static String curSearchPath = null;

        private static Operation operation = Operation.eNone;
        private EDriveOperation driveOperation = EDriveOperation.eNone;

        public static List<Form> ProcessList { get { return processList; } }
        
        public MainForm()
        {
            InitializeComponent();
            notifyIcon.Visible = true;

            

   //         Jounal Jounal = new Jounal("F:\\", "C:\\test.xml", "TestID", "Full", "Test discription");
            //JounalContentsForm form = new JounalContentsForm("C:\\Temp\\FMM\\Nikon80-4\\full-2010-3-29-12-44");
            //form.ShowDialog();
            if (BackupManager.Init() == false)
            {
                if (RegSetting.IsInstalled == false)
                {
                    MessageBox.Show(
                        "SIA Importer 1.0 application not installed correctly? Please re-install. If this do'es not fix the problem, please contact IDK Software Ltd",
                        "SIA Importer 1.0", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    Environment.Exit(-1);
                }
                if (RegSetting.IsSetup == false)
                {
                    Setup();
                }
            }

            Drives();
            if (BackupManager.HasDrives2Scan())
            {
                ScanDrives();
                timer.Start();
            }
           
            if (HasFlashDrives() == false)
            {
                quickBackupToolStripMenuItem.Enabled = false;
                quickRestoreToolStripMenuItem.Enabled = false;
            }
            else
            {
                hasFlashDriveInstalled = true;
            }

            driveDetector.DeviceArrived += new DriveDetectorEventHandler(OnDriveArrived);
            driveDetector.DeviceRemoved += new DriveDetectorEventHandler(OnDriveRemoved);
            driveDetector.QueryRemove += new DriveDetectorEventHandler(OnQueryRemove);
           
        }

        // Called by DriveDetector when removable device in inserted 
        private void OnDriveArrived(object sender, DriveDetectorEventArgs e)
        {
            String msg = "New volume " + e.Drive + "Click on button to search the drive.";
            driveOperation = EDriveOperation.eDeviceArrived;
            notifyIcon.ShowBalloonTip(1000, "Flash Memory Manager", msg, ToolTipIcon.Info);
            path = e.Drive;

            quickBackupToolStripMenuItem.Enabled = true;
            quickRestoreToolStripMenuItem.Enabled = true;
            Drives();
            if (BackupManager.HasDrives2Scan())
            {
                ScanDrives();
            }
        }
        

        // Called by DriveDetector after removable device has been unpluged 
        private void OnDriveRemoved(object sender, DriveDetectorEventArgs e)
        {
            String msg = "Drive removed " + e.Drive;
            driveOperation = EDriveOperation.eDriveRemoved;
            notifyIcon.ShowBalloonTip(500, "Flash Memory Manager", msg, ToolTipIcon.Info);
            path = e.Drive;
            if (HasFlashDrives() == false)
            {
                quickBackupToolStripMenuItem.Enabled = false;
                quickRestoreToolStripMenuItem.Enabled = false;
            }
            else
            {
                hasFlashDriveInstalled = true;
            }
            RemoveDrive(e.Drive);
        }

        private void OnQueryRemove(object sender, DriveDetectorEventArgs e)
        {
            String msg = "Query Remove " + e.Drive;
            driveOperation = EDriveOperation.eQueryRemove;
            notifyIcon.ShowBalloonTip(500, "Flash Memory Manager", msg, ToolTipIcon.Info);
            path = e.Drive;
            notifyIcon.Icon = new Icon("redicon.ico");


        }

        private void notifyIcon_BalloonTipClicked(object sender, EventArgs e)
        {
            if (driveOperation == EDriveOperation.eDeviceArrived)
            {
                
                labelImagesFound.Text = "0";
                //driveBackup.TargetPath = path;

                DirectoryInfo directoryInfo = new DirectoryInfo(path);
                FileInfo[] files = directoryInfo.GetFiles();
                int noOfFiles = files.Length;
                this.Text = "Flash Memory Manager - New volume " + path;
                this.WindowState = FormWindowState.Normal;
                this.Visible = true;
                this.TopMost = true;
                timer.Start();
               // Init cotrols
                this.labelPath.Text = "";
                progressBar.Value = 0;
                progressBar.Visible = true;
                timer.Start();
                labelPath.Visible = true;
                labelSearchingFolder.Visible = true;
                buttonBackup.Visible = false;
                BackupManager.DoDrives2Scan();
                hasBackupStarted = true;
                backupTimer.Start();
                //filesFound.RemoveAll();
                noFilesFound = 0;
                IsCancel = false;
                 
            }
            notifyIcon.Icon =  SIATray.Properties.Resources.siagreen;

        }

        /**
         *
         */
        private void ScanDrives()
        {
            BackupManager.DoDrives2Scan();
            hasBackupStarted = true;
            backupTimer.Start();
        }

        static void ReadingFiles(string sDir)
        {

            try
            {
                foreach (string d in Directory.GetDirectories(sDir))
                {
                    foreach (string f in Directory.GetFiles(d))
                    {
                        filesDone++;
                    }
                    ReadingFiles(d);
                }
            }
            catch (System.Exception excpt)
            {
                //MessageBox.Show(excpt.Message);
            }
        }

        
        

        private void timer_Tick(object sender, EventArgs e)
        {
            /*
            if (toggle == false)
            {
                toggle = true;
                notifyIcon.Icon = SIATray.Properties.Resources.sia;
            }
            else
            {
                toggle = false;
                notifyIcon.Icon = SIATray.Properties.Resources.siagreen;
            }
            */
            /*
            if (operation == Operation.eAccessingDrive)
            {
                this.labelCurrentFile.Text = "Accessing Drive";
            }
            if (operation == Operation.eInitSearch)
            {
                progressBar.Maximum = filesDone;
                progressBar.Minimum = 0;
                this.labelCurrentFile.Text = "Searching file(s)";
                operation = Operation.eSearchFiles;
            }
            if (operation == Operation.eSearchingFiles)
            {
               
                int step = filesDone - filesCurr;
                filesCurr = filesDone;
                this.labelPath.Text = curSearchPath;
                progressBar.Increment(step);
                labelImagesFound.Text = noFilesFound.ToString();
            }
            if (operation == Operation.eFileFindComplete)
            {
                this.labelPath.Text = "";
                progressBar.Visible = false;
                timer.Stop();
                labelPath.Visible = false;
                buttonBackup.Visible = true;
                labelSearchingFolder.Visible = false;
                this.labelCurrentFile.Text = "Searching complete";
            }
             */
        }

        private void MainForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            e.Cancel = true;
            timer.Stop();
            //this.WindowState = FormWindowState.Minimized;
            this.Visible = false;
            this.TopMost = false;
        }

        private void buttonCancel_Click(object sender, EventArgs e)
        {
            //e.Cancel = true;
            timer.Stop();
            //this.WindowState = FormWindowState.Minimized;
            this.Visible = false;
            this.TopMost = false;
            IsCancel = true;
        }

        private void buttonViewImages_Click(object sender, EventArgs e)
        {
            this.TopMost = false;
            
            
           
        }

        private void buttonTest_Click(object sender, EventArgs e)
        {
            //ExplorerLike form = new ExplorerLike();
            //form.FileListView.ShowFiles("D:\\Users\\501726576\\My Documents\\pics");
            //form.ShowDialog();
        }

        private void aboutToolStripMenuItem_Click(object sender, EventArgs e)
        {
            AboutBox form = new AboutBox();
            form.ShowDialog();
        }

        private void propertiesToolStripMenuItem_Click(object sender, EventArgs e)
        {
            PropertiesForm fmmForm = new PropertiesForm();
            fmmForm.ShowDialog();
        }

        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Environment.Exit(0);
        }

        private void importImagesToolStripMenuItem_Click(object sender, EventArgs e)
        {
            //ExplorerLike form = new ExplorerLike();
            //form.FileListView.ShowFiles("D:\\Users\\501726576\\My Documents\\pics");
            //form.ShowDialog();
        }

        private void buttonBackup_Click(object sender, EventArgs e)
        {
            this.TopMost = false;
            //BackupForm form = new BackupForm(driveBackup);
            //form.ShowDialog();

            /*
            if (driveBackup.IsDriveIdentified())
            {

            }
            if (driveBackup.DoesNeedBackup() == true)
            {
                if (driveBackup.IsDriveIdentified())
                {
                    form.DriveLabel = driveBackup.DriveID;
                    form.DriveLabelReadOnly = true;
                }
                else
                {

                }
                form.ShowDialog();
                //driveBackup.FullBackup();
            }

            */


        }

        private void quickBackupToolStripMenuItem_Click(object sender, EventArgs e)
        {

        }

        private void backupTimer_Tick(object sender, EventArgs e)
        {

            if (hasBackupStarted == true)
            {
                if (DriveBackup.TaskPending <= 0)
                {
                    long found = BackupManager.GetImagesFound();
                    String msg = "Scan completed";
                    if (found > 0)
                    {
                        msg = String.Format("Scan completed found {0} images", found);
                    }
                    notifyIcon.ShowBalloonTip(500, "SIA Importer", msg, ToolTipIcon.Info);
                    backupTimer.Stop();
                    hasBackupStarted = false;
                    notifyIcon.Icon = SIATray.Properties.Resources.sia;

                }
            }
            else
            {
                if (DriveBackup.TaskPending > 0)
                {
                    hasBackupStarted = true;
                }
            }
            if (toggle == false)
            {
                toggle = true;
                notifyIcon.Icon = SIATray.Properties.Resources.sia;
            }
            else
            {
                toggle = false;
                notifyIcon.Icon = SIATray.Properties.Resources.siagreen;
            }
        }

        private void quickRestoreToolStripMenuItem_Click(object sender, EventArgs e)
        {
            //RestoreForm form = new RestoreForm();
            //form.ShowDialog();
        }

        private bool HasFlashDrives()
        {
            DriveInfo[] allDrives = DriveInfo.GetDrives();
            foreach (DriveInfo d in allDrives)
            {
                Debug.Print("Drive {0}", d.Name);
                Debug.Print("  File type: {0}", d.DriveType);
                if (d.DriveType == DriveType.Removable)
                {
                    if (d.IsReady == true)
                    {
                        return true;
                    }
                }
            }
            return false;
        }

        

        public void Drives()
        {
            DriveInfo[] allDrives = DriveInfo.GetDrives();
           
            foreach (DriveInfo d in allDrives)
            {

                Debug.Print("Drive {0}", d.Name);
                Debug.Print("  File type: {0}", d.DriveType);
                if (d.DriveType == DriveType.Removable)
                {
                    if (d.IsReady == true)
                    {
                        if (BackupManager.DriveList.IsDup(d.Name)) {
                            continue;
                        }
                        DriveBackup db = new DriveBackup(d.Name);
                        BackupManager.DriveList.Push(db);
                        BackupManager.CurrentDB = db;
                        db.Init();

                        db.VolumeLabel = d.VolumeLabel;
                        db.TotalSize = d.TotalSize;
                        db.TotalFreeSpace = d.TotalFreeSpace;
                        db.AvailableFreeSpace = d.AvailableFreeSpace;

                        Debug.Print("  Volume label: {0}", d.VolumeLabel);
                        Debug.Print("  File system: {0}", d.DriveFormat);
                        Debug.Print(
                            "  Available space to current user:{0, 15} bytes",
                            d.AvailableFreeSpace);

                        Debug.Print(
                            "  Total available space:          {0, 15} bytes",
                            d.TotalFreeSpace);

                        Debug.Print(
                            "  Total size of drive:            {0, 15} bytes ",
                            d.TotalSize);

                        Debug.Print(
                           "  Drive letter:            {0, 15} ",
                           db.DriveLetter);

                        Debug.Print(
                           "  Volume label:            {0, 15} ",
                           db.VolumeLabel);

                        Debug.Print(
                           "       Drive ID:            {0, 15} ",
                           db.DriveID);
                    }

                }
            }
           
        }

        public void RemoveDrive(String DriveLetter)
        {
            BackupManager.DriveList.Pop(DriveLetter);
        }

        private void Completed(object sender)
        {
            this.Invoke((MethodInvoker)delegate
            {
                if (BackupManager.HasDrives2Scan())
                {
                    ScanDrives();
                }
                else
                {
                    if (BackupManager.HasDrives2Backup())
                    {
                        String msg = "One or more drives to backup";
                        notifyIcon.ShowBalloonTip(500, "Flash Memory Manager", msg, ToolTipIcon.Info);
                    }
                    else
                    {
                        String msg = "Scan completed";
                        notifyIcon.ShowBalloonTip(500, "Flash Memory Manager", msg, ToolTipIcon.Info);
                    }
                    timer.Stop();
                    notifyIcon.Icon = SIATray.Properties.Resources.sia;
                }
            });
        }

        #region Setup

        void Setup()
        {
            while (true)
            {
                if (SettupWizard() == true)
                {
                    break;
                }
                else
                {
                    if ((MessageBox.Show("Flash Memory Manager settup Wizard did not complete setting up Flash Memory Manager 1.0.\r Do you wish to re-start the setup Wizard?",
                                        "Flash Memory Manager 1.0", MessageBoxButtons.YesNo, MessageBoxIcon.Hand)) == DialogResult.Yes)
                    {
                        continue;
                    }
                    else
                    {
                        MessageBox.Show("Flash Memory Manager connot continue, exiting Bedtime 1.0\rRe-starting Flash Memory Manager will also re-start the setup Wizard, thus enabling you to complete Flash Memory Manager setup at another time",
                                        "Flash Memory Manager 1.0", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                        Environment.Exit(-1);
                    }
                }
            }
        }

        private bool SettupWizard()
        {
            WizardForm form = new WizardForm();
            
            if (form.ShowDialog() == DialogResult.OK)
            {
                //password.Create(form.Password);

                /*
                Shareware shareware = new Shareware();
                if (form.UsingShareware == true)
                {
                    shareware.WriteInstallDate();
                }
                else
                {
                    shareware.WriteLicence();
                }
                shareware.WriteUserName();

                shutdown.Init();
                shutdown.SetOption(form.ShutdownOption);
                shutdown.WriteRegistory();
                bedTimes.WriteRegistory();
                password.WriteRegistory();
                */
                BackupManager.LibraryLocationPath = form.LibraryLocation;
                BackupManager.WriteSettings();
                return true;
            }
            return false;
        }

        #endregion

        private void contextMenuStrip_Opening(object sender, CancelEventArgs e)
        {

        }

        private void FolderImportToolStripMenuItem_Click(object sender, EventArgs e)
        {
            FolderImportForm form = new FolderImportForm();
            DialogResult res = form.ShowDialog();
            if (res == DialogResult.Cancel)
            {
                return;
            }
        }

        private void flashImportToolStripMenuItem_Click(object sender, EventArgs e)
        {
            DriveForm form = new DriveForm();
            DialogResult res = form.ShowDialog();
            if (res == DialogResult.Cancel)
            {
                return;
            }
        }
    }
}