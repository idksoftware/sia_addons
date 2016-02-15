using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.Threading;
using System.Diagnostics;

namespace SIATray
{
    public partial class ScanForm : Form
    {
        //static ScanForm thisInstance = null;
        private DriveBackup currentDrive;
        public delegate void DoWorkDelegate();
        public ScanForm(DriveBackup db)
        {
            currentDrive = db;
            InitializeComponent();
            progressBar.Value = 100;
            //thisInstance = this;
        }

        

        public void DoWorkMethod()
        {
            currentDrive.Jounal.CompletedEvent += new Jounal.CompletedEventHandler(Completed);
            currentDrive.Jounal.OnFileEvent += new Jounal.OnFileEventHandler(OnFile);
            currentDrive.Jounal.ProgressEvent += new Jounal.ProgressEventHandler(OnProgressEvent);
            if (DoesNeedBackup(currentDrive) == true)
            {

            }
        }

        
        public bool DoesNeedBackup(DriveBackup db)
        {
            if (db != null && db.DriveID != null)
            {
               
                Thread thread = new Thread(() => db.Jounal.ScanDriveDirectories(db.DriveLetter));

                thread.Start();
            }  
            return true;
        }
         
        /*
        static public bool DoesNeedBackup(DriveBackup db)
        {
            if (db != null && db.DriveID != null)
            {

                Task task = new Task(new Action(db.Jounal.ScanDriveDirectories(db.DriveLetter));

                task.Start();
            }
            return true;
        }
        */
        private void OnFile(object sender, FileEventArgs e)
        {
            Debug.Print("ScanForm OnFile");
        }

        private void OnProgressEvent(object sender, ProgressEventArgs e)
        {
            //Debug.Print("  Percent:            {0, 15} ", e.Percent);
            this.Invoke((MethodInvoker)delegate
            {
                // close the form on the forms thread
                this.progressBar.Value = e.Percent;
            });
            
        }

        private void Completed(object sender)
        {
            this.Invoke((MethodInvoker) delegate
            {
                // close the form on the forms thread
                this.Close();
            });
            /*
            if (thisInstance != null)
            {
                //thisInstance.Close();
                thisInstance.Invoke((MethodInvoker)delegate
                {
                    // close the form on the forms thread
                    thisInstance.Close();
                });

            }
             */
        }

        private void ScanForm_Load(object sender, EventArgs e)
        {
            BeginInvoke(new DoWorkDelegate(DoWorkMethod));
        }
    }
}