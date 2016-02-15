using System;
using System.Collections.Generic;
using System.Text;
using System.Threading;
using System.Diagnostics;

namespace SIATray 
{
    public class DriveList : List<DriveBackup>
    {
        public bool IsDup(String DriveLetter)
        {
            if (this.Count == 0)
            {
                return false;
            }
            var index = FindIndex(i => (String.Compare(i.DriveLetter, DriveLetter) == 0)); // like Where/Single
            return (index >= 0);
        }

        public void Push(DriveBackup db)
        {
            foreach (DriveBackup dbItem in this)
            {
                if (String.Compare(dbItem.DriveLetter, db.DriveLetter, true) == 0) 
                {
                    // Already in the list
                    return;
                }   
            }
            base.Add(db);
        }

        public void Pop(String DriveLetter)
        {
            int index = FindIndex(i => (String.Compare(i.DriveLetter, DriveLetter) == 0)); // like Where/Single
            if (index >= 0)
            {   // ensure item found
                RemoveAt(index);
            }

        }

    }


    public abstract class DriveTask
    {
        protected Thread thread = null;
        protected DriveBackup driveBackup = null;

        public DriveTask(DriveBackup driveBackup)
        {
            this.driveBackup = driveBackup;
        }

        ~DriveTask()
        {
            Debug.Print("DriveTask destructor");
        }
        public void Canceled()
        {
            driveBackup.Canceled();
        }
        public abstract void DoTask();
        public DriveBackup DriveBackup { get { return driveBackup; } set { driveBackup = value; } }
    }

    public class FullBackupTask : DriveTask
    {
        public FullBackupTask(DriveBackup driveBackup) : base(driveBackup)
        {}
        //
        public override void DoTask()
        {
            thread = new Thread(new ThreadStart(driveBackup.FullBackup));
            thread.Start();
        }
    }

    public class ScanTask : DriveTask
    {
        public ScanTask(DriveBackup driveBackup)
            : base(driveBackup)
        { }
        //
        public override void DoTask()
        {
            thread = new Thread(new ThreadStart(driveBackup.scanDrive));
            thread.Start();
        }
    }



    public class BackupManager
    {
        private static DriveList driveList = new DriveList();
        private static FavoritesList favoritesList = new FavoritesList();
        private static DriveBackup currentDB = null;
        private static List<DriveTask> DriveTasks = new List<DriveTask>();
        private static String libraryLocationPath = null;

        public static bool Init()
        {
            RegSetting regSetting = new RegSetting();
            regSetting.ReadRegister();
           
            libraryLocationPath = regSetting.LibraryLocationPath;
            if (libraryLocationPath == null)
            {
                return false;
            }
            return true;
        }

        public static DriveTask ScanDrive(DriveBackup driveBackup)
        {
            foreach (DriveTask dt in DriveTasks)
            {
                DriveBackup DriveBackup = dt.DriveBackup;
                if (DriveBackup.DriveLetter == driveBackup.DriveLetter)
                {
                    // Task in progress
                    return null;
                }
            }
            ScanTask scanTask = new ScanTask(driveBackup);
            DriveTasks.Add(scanTask);
            return scanTask;
        }

        public static DriveTask FullBackup(DriveBackup driveBackup)
        {
            foreach (DriveTask dt in DriveTasks)
            {
                DriveBackup DriveBackup = dt.DriveBackup;
                if (DriveBackup.DriveLetter == driveBackup.DriveLetter)
                {
                    // Task in progress
                    return null;
                }
            }
            FullBackupTask fullBackupTask = new FullBackupTask(driveBackup);
            DriveTasks.Add(fullBackupTask);
            return fullBackupTask;
        }

        public static bool EndTask(DriveTask driveTask)
        {
            EndTask(driveTask.DriveBackup.DriveLetter);
            return false;
        }

        public static bool EndTask(String driveLetter)
        {
            foreach (DriveTask dt in DriveTasks)
            {
                DriveBackup DriveBackup = dt.DriveBackup;
                if (driveLetter == dt.DriveBackup.DriveLetter)
                {
                    DriveTasks.Remove(dt);
                  
                    return true;
                }
            }
            return false;
        }


        static public DriveBackup GetDriveBackupObject(int idx)
        {
            if (driveList.Count < idx)
            {
                return null;
            }
            return driveList[idx];
        }
        
        static public DriveBackup GetDriveBackupObject(String DriveID)
        {
            foreach (DriveBackup db in driveList)
            {
                if (db.DriveID == null)
                {
                    return null;
                }
                if (db.DriveID.Equals(DriveID))
                {
                    return db;
                }
            }
            return null;
        }

        static public long GetImagesFound()
        {
            long filesFound = 0;
            foreach (DriveBackup db in driveList)
            {
                filesFound += db.Jounal.NoFiles;
            }
            return filesFound;
        }

        static public DriveBackup GetDrive2Scan()
        {
            foreach (DriveBackup db in driveList)
            {
                if (db.IsDriveIdentified())
                {
                    if (db.NeedsScanning == true)
                    {
                        return db;
                    }
                }
            }
            return null;
        }

        static public DriveBackup DoDrives2Scan()
        {
            foreach (DriveBackup db in driveList)
            {
                // For later release
                //if (db.IsDriveIdentified())
                //{

                    if (db.NeedsScanning == true)
                    {
                        db.Jounal.CompletedEvent += new Jounal.CompletedEventHandler(ScanCompleted);
                        DriveTask driveTask = ScanDrive(db);
                        if (driveTask == null)
                        {
                            continue;
                        }
                        driveTask.DoTask();
                    }
                //}
            }
            return null;
        }

        static public bool HasDrives2Scan()
        {
            foreach (DriveBackup db in driveList)
            {
                //if (db.IsDriveIdentified())
                //{
                    if (db.NeedsScanning == true)
                    {
                        return true;
                    }
                //}
            }
            return false;
        }

        static public bool HasDrives2Backup()
        {
            foreach (DriveBackup db in driveList)
            {
                if (db.IsDriveIdentified())
                {
                    if (db.NeedsBackingUp == true)
                    {
                        return true;
                    }
                }
            }
            return false;
        }

        static public void WriteSettings()
        {
            
            RegSetting regSetting = new RegSetting();
            regSetting.LibraryLocationPath = libraryLocationPath;
            regSetting.WriteRegistory();
        }

        private static void ScanCompleted(object sender)
        {
            Jounal jounal = (Jounal)sender;
            EndTask(jounal.DriveBackup.DriveLetter);
            Debug.Print("Got here");
        }

        public static DriveList DriveList { get {
            return driveList;
        } }
        public static DriveBackup CurrentDB { get { return currentDB; } set { currentDB = value; } }
        public static String LibraryLocationPath
        {
            get { return libraryLocationPath; }
            set { libraryLocationPath = value; }
        }

        public static FavoritesList FavoritesList
        {
            get { return favoritesList; }
        }
    }

    

    
}
