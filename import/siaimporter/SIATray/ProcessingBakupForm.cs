using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.Diagnostics;

namespace SIATray
{
    public partial class ProcessingBakupForm : Form
    {
        private String driveLetter;
        private String driveLabel;
        private DriveTask driveTask;

        public ProcessingBakupForm(String driveLetter, String driveLabel)
        {
            InitializeComponent();
            this.driveLetter = driveLetter;
            this.driveLabel = driveLabel;
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            labelDriveLetter.Text = driveLetter;
            labelDriveId.Text = driveLabel;
            DriveBackup driveBackup = BackupManager.GetDriveBackupObject(driveLabel);
            long UsedSpace = driveBackup.TotalSize - driveBackup.TotalFreeSpace;
            this.labelBackingUpItems.Text = String.Format("Analysing drive ({0, 15})", SizeLabel(UsedSpace));
            driveTask = BackupManager.FullBackup(driveBackup);
            if (driveTask == null)
            {
                return;
            }
            DriveBackup driveBackup1 = driveTask.DriveBackup;
            driveBackup1.Jounal.OnFileEvent += new Jounal.OnFileEventHandler(JounalFileEvent);
            driveBackup1.Jounal.CompletedEvent += new Jounal.CompletedEventHandler(JounalCompleted);
            driveBackup.FileEvent += new DriveBackup.FileEventHandler(FileEvent);
            //driveBackup.CompletedEvent += new DriveBackup.CompletedEventHandler(BackupCompleted);
            driveTask.DoTask();
            timer.Start();
        }

        private void timer_Tick(object sender, EventArgs e)
        {
           if (driveTask.DriveBackup.CurrentTaskPending == 0)
           {
               BackupManager.EndTask(driveTask);
               Close();
               MainForm.ProcessList.Remove(this);
               driveTask.DriveBackup.CompletedEvent -= BackupCompleted;
               timer.Stop();
               driveTask = null;
           }
        }


        private void JounalFileEvent(object sender, FileEventArgs e)
        {
            if (this.IsHandleCreated) 
            {
                this.Invoke((MethodInvoker)delegate
                {
                    // close the form on the forms thread
                    this.labelFilename.Text = e.Info.Name;
                });
            }
        }

        private void FileEvent(object sender, FileEventArgs e)
        {
            if (this.IsHandleCreated)
            {
                this.Invoke((MethodInvoker)delegate
                {
                    // close the form on the forms thread
                    this.labelFilename.Text = e.Info.Name;
                });
            }
        }


        private void JounalCompleted(object sender)
        {
            this.Invoke((MethodInvoker)delegate
            {
                // close the form on the forms thread
                this.labelBackingUpItems.Text = String.Format("Backing up drive");
            });
        }


        
        private void BackupCompleted(object sender)
        {
            BackupManager.EndTask(driveTask);

            driveTask.DriveBackup.CompletedEvent -= BackupCompleted;
        }
        
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

        private void buttonCancel_Click(object sender, EventArgs e)
        {
            driveTask.Canceled();
            BackupManager.EndTask(driveTask);
            Close();
            MainForm.ProcessList.Remove(this);
            driveTask.DriveBackup.CompletedEvent -= BackupCompleted;
            timer.Stop();
            driveTask = null;
        }
    }
}