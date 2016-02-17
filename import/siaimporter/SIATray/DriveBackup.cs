using System;
using System.Collections.Generic;
using System.Text;
using System.IO;

namespace SIATray
{
    public enum BackupType
    {
        Full,
        Inc,
        None

    }

    
    public class DriveBackup
    {
        //private List<FileObject> fileList = new List<FileObject>();
        private String driveID = null;
        private DriveType driveType;
        private String LibraryLocationPath = null;
        private Jounal currentJounal = null; // = new Jounal(this);
        private String curSearchPath = null;
        private long noFilesFound = 0;
        private long filesDone = 0;
        private String driveLetter = null;
        private String volumeLabel;
        private long availableFreeSpace;
        private long totalFreeSpace;
        private long totalSize;
        private DateTime lastBackedUp;
        private bool needsBackingUp = true;
        private bool canceled = false;
        private static int taskPending = 0;
        private int currentTaskPending = 0;
        
        private String errorMsg = null;

        List<BackupItem> list = new List<BackupItem>();

        public Jounal Jounal { get { return currentJounal; } }

        public DriveBackup(String dl)
        {
            currentJounal = new Jounal(this);
            LibraryLocationPath = BackupManager.LibraryLocationPath;
            driveLetter = dl;
        }

        

        public void Init()
        {
            foreach (string item in Directory.GetFiles(driveLetter))
            {
                if (item.EndsWith(".drvid"))
                {
                    ReadDriveID(item);
                    return;
                }
            }
            return;
           
        }


        public bool IsDriveIdentified()
        {
            if (driveID == null)
            {
                return false;
            }
            return true;
        }

        #region Handling of the FileEvent

        /// <summary>
        /// Definition for the FileEvent.
        ///	</summary>
        public delegate void FileEventHandler(object sender, FileEventArgs e);

        /// <summary>
        /// Event is raised for each file in a directory.
        /// </summary>
        public event FileEventHandler FileEvent;

        /// <summary>
        /// Raises the file event.
        /// </summary>
        /// <param name="fileInfo"><see cref="FileInfo"/> object for the current file.</param>
        private bool RaiseFileEvent(FileInfo fileInfo)
        {
            bool continueScan = true;

            // Create a new argument object for the file event.
            FileEventArgs args = new FileEventArgs(fileInfo);

            // Now raise the event.
            FileEvent(this, args);

            continueScan = !args.Cancel;

            return continueScan;
        }

        #endregion

        public delegate void CompletedEventHandler(object sender);
        public event CompletedEventHandler CompletedEvent;

        public void scanDrive()
        {
            taskPending++;
            currentTaskPending++;
            Jounal.AnaliseDrive();
            taskPending--;
            currentTaskPending--;
        }
        public void FullBackup()
        {
            currentTaskPending++;
            TextWriter textWriter = null;
            DateTime now = DateTime.Now;
            String dateString = now.Year.ToString() + "-" + now.Month.ToString() + "-" + now.Day.ToString()
                            + "-" + now.Hour.ToString() + "-" + now.Minute.ToString();
            String DestPath = LibraryLocationPath + "\\" + driveID + "\\" + "full-" + dateString;
            String DestDataPath = DestPath + "\\" + "data";

            taskPending++;
            try
            {
                CreatDestPath(DestDataPath);
          
                this.Jounal.WriteXML(driveLetter, DestPath + "\\jounal.xml", driveID, "Full", "Test discription");
                List<FileObject> fileList = currentJounal.FileList;
                foreach (FileObject item in fileList)
                {
                    if (canceled == true)
                    {
                        break;
                    }
                    if (item.path.EndsWith(".drvid"))
                    {
                        continue;    // skip
                    }
                    else if (item.path.Equals(DriveLetter))
                    {
                        continue;
                    }
                    FileInfo fi = new FileInfo(item.path);
                    RaiseFileEvent(fi);
                    String dirPath = item.path.Substring(1);
                    String fullDestPath = DestDataPath + "\\" + dirPath;
                    String fullSourcePath = driveLetter + item.path;
                    FileInfo file = new FileInfo(fullDestPath);
                    CreatDestPath(file.DirectoryName);
                    try
                    {
                        File.Copy(fullSourcePath, fullDestPath, true);
                        FileInfo dfi = new FileInfo(fullDestPath);
                        dfi.Attributes |= FileAttributes.Compressed;
                        RaiseFileEvent(dfi);
                    }
                    catch (Exception ex)
                    {
                        if (textWriter == null)
                        {
                            textWriter = new StreamWriter(DestPath + "\\Journal.log");
                            textWriter.Write(item + " Error: " + ex.Message);
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                errorMsg = ex.Message;
            }
            finally
            {
                if (textWriter != null)
                {
                    textWriter.Close();
                }
                taskPending--;
                currentTaskPending--;
                if (canceled == true)
                {
                    // cleanup
                    Directory.Delete(DestPath, true);
                }
                canceled = false;
            }
            //CompletedEvent(this);
        }
        public void Canceled()
        {
            canceled = true;
        }
        void CreatDestPath(String DestPath)
        {
            String[] splitPath = DestPath.Split('\\');
            String path = null;
            bool first = true;
            foreach (String item in splitPath)
            {
                if (first == true)
                {
                    path = item;
                    first = false;
                }
                else
                {
                    path = path + "\\" + item; 
                }
                DirectoryInfo dir = new DirectoryInfo(path);
                if (dir.Exists == false)
                {
                    dir.Create();
                }
                
            }
            
        }

        private void ReadDriveID(String path)
        {
            TextReader textReader = new StreamReader(path);
            driveID = textReader.ReadLine();
            textReader.Close();
        }

        public static bool WriteDriveID(String path, String Id)
        {
            try
            {
                TextWriter textWriter = new StreamWriter(path + ".drvid");
                textWriter.WriteLine(Id);
                textWriter.Close();
                return true;
            }
            catch (Exception ex)
            {
                return false;
            }
            
        }

        public void ReadBackups()
        {
            String path = null;
            if (driveID == null || driveID.Length == 0)
            {
                return;
            }
            String destPath = LibraryLocationPath + "\\" + driveID;
            DirectoryInfo destPathInfo = new DirectoryInfo(destPath);
            bool first = true;

            if (!destPathInfo.Exists)
            {
                return;
            }
            foreach (DirectoryInfo d in destPathInfo.GetDirectories())
            {
                String dateString = d.Name.Substring(5);
                path = d.Name;
                DateTime dt = GetDateTime(dateString);
                BackupItem bi = new BackupItem();
                bi.DriveID = driveID;
                bi.timeStamp = dt;
                String typeStr = d.Name.Substring(0,4);

                if (typeStr.Equals("full"))
                {
                    bi.type = BackupType.Full;
                }
                else
                {
                    bi.type = BackupType.Inc;
                }
                bi.Description = "Test Only";
                bi.FolderName = d.Name;
                list.Add(bi);

            }
            
        }
        public String GetLastBackupFullpath()
        {
            String path = GetLastBackup();
            if (path == null)
            {
                return null;
            }
            return LibraryLocationPath + "\\" + driveID + "\\" + path + "\\Jounal.xml";
        }
        public String GetLastBackup()
        {
            String path = null;
            String lastPath = null;
            DateTime lastDateTime = new DateTime(0);
            String destPath = LibraryLocationPath + "\\" + driveID;
            DirectoryInfo destPathInfo = new DirectoryInfo(destPath);
            bool first = true;

            if (!destPathInfo.Exists)
            {
                return null;
            }
            foreach (DirectoryInfo d in destPathInfo.GetDirectories())
            {
                String dateString = d.Name.Substring(5);
                path = d.Name;
                DateTime dt = GetDateTime(dateString);
                if (first)
                {
                    lastDateTime = dt;
                    lastPath = d.Name;
                    first = false;
                }
                else
                {
                    if (dt.CompareTo(lastDateTime) > 0)
                    {
                        lastDateTime = dt;
                        lastPath = d.Name;
                    }
                }
            }
            return lastPath;
        }

        static DateTime GetDateTime(String dateString)
        {
            String[] items = dateString.Split('-');
            DateTime dateTime = new DateTime(0);
            try
            {
                int year = int.Parse(items[0]);
                int month = int.Parse(items[1]);
                int day = int.Parse(items[2]);
                int hour = int.Parse(items[3]);
                int min = int.Parse(items[4]);
                dateTime = new DateTime(year, month, day, hour, min, 00);
                return dateTime;
            }
            catch (Exception e)
            {

            }
            return dateTime;
        }

        public bool DoesNeedBackup()
        {

            String path = GetLastBackup();
            if (path == null)
            {
                return false;
            }
            String destPath = LibraryLocationPath + "\\" + driveID + "\\" + path + "\\Jounal.xml";
            
            if (!File.Exists(destPath)) {
                return false;
            }
            Jounal.ReadXML(destPath);
            List<FileObject> fileList = currentJounal.FileList;
            foreach (FileObject f in fileList)
            {
                JounalItem ji = Jounal.GetItem(f.path);
                if (ji != null)
                {
                    DateTime dt = ji.lastWriteTime;
                    FileInfo fi = new FileInfo(f.path);
                    if (fi.LastWriteTime.CompareTo(dt) > 0)
                    {
                        return true;
                    }
                }
                else
                {
                    return true;
                }
            }
            return false;
            
        }
        public long TotalSize {
            get { return totalSize; }
            set { totalSize = value; currentJounal.TotalSize = value; }
        }
        public long AvailableFreeSpace {
            get { return availableFreeSpace; }
            set { availableFreeSpace = value; currentJounal.AvailableFreeSpace = value; }
        }
        public bool NeedsScanning { get { return currentJounal.NeedsScanning; } set { currentJounal.NeedsScanning = value; } }
        
        public String DriveLetter { get { return driveLetter; } set { driveLetter = value; } }
        public String DriveID { get { return driveID; } set { driveID = value; } }
        public String VolumeLabel { get { return volumeLabel; } set { volumeLabel = value; } }
        public DriveType DriveType { get { return driveType; } set { driveType = value; } }
       
        public long TotalFreeSpace { get { return totalFreeSpace; } set { totalFreeSpace = value; } }
       
        public DateTime LastBackedUp { get { return lastBackedUp; } set { lastBackedUp = value; } }
        public bool NeedsBackingUp { get { return needsBackingUp; } set { needsBackingUp = value; } }
        public List<BackupItem> BackupList { get { return list; } }
        public static int TaskPending { get { return taskPending; } }
        public int CurrentTaskPending { get { return currentTaskPending; } }
    }
    /*
    enum DriveItemType
    {
        File,
        Directory,
        Other
    }

    public class DriveItemInfo
    {
        public String path;
        public DateTime dateLastWritten;
        public DriveItemType driveItemType;
    }
     */
}
