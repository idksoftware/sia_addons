<<<<<<< HEAD
using System;
using System.Collections.Generic;
using System.Text;
using System.Xml;
using System.IO;
using System.Collections;
using System.Diagnostics;

namespace SIATray
{

    public class BackupItem
    {
        public DateTime timeStamp;
        public BackupType type = BackupType.None;
        public String DriveID = null;
        public String Description = null;
        public String FolderName = null;
    }

    public class FileObject
    {
        public DateTime lastWriteTime;
        public String path = null;
        public FileAttributes attributes;
    }

    public class FileItem : FileObject
    {
        
        public long size = 0;
        public uint crc32 = 0;
    }

    public class FolderItem : FileObject
    {

    }


    public class JounalItem
    {
        public String path = null;
        public bool IsDirectory = false;
        public DateTime lastWriteTime;



    }

    

    public class Jounal
    {
        Dictionary<String, JounalItem> fileLookup = new Dictionary<string, JounalItem>(1000);
        private List<FileObject> fileList = new List<FileObject>();
        private static bool isError = false;
        private static String lastErrorString = null;
        private static String curSearchPath = null;
        private static int filesDone = 0;
        private static long _totalSize;
        private static int _noFilesFound = 0;
        private static int _noFoldersFound = 0;
        private String driveID = null;
        private String type = null;
        private String discription = null;
        private DriveBackup driveBackup;
        //private XmlDocument xmlDoc = new XmlDocument();
        private long totalSize;
        private long availableFreeSpace;
        private long processedSize;
        private int noFilesFound = 0;
        private int noFoldersFound = 0;
        private bool needsScanning = true;
        private bool quick = true;

        public long TotalSize { get { return totalSize; } set { totalSize = value; } }
        public long AvailableFreeSpace { get { return availableFreeSpace; } set { availableFreeSpace = value; } }
        public bool NeedsScanning { get { return needsScanning; } set { needsScanning = value; } }
        public DriveBackup DriveBackup { get { return driveBackup; } }

        public long NoFiles { get { return noFilesFound; } }
        public long NoFolders { get { return noFoldersFound; } }

        public delegate void CompletedEventHandler(object sender);
        public event CompletedEventHandler CompletedEvent;

        public delegate void OnFileEventHandler(object sender, FileEventArgs e);
        public event OnFileEventHandler OnFileEvent;

        /// <summary>
        /// Raises the file event.
        /// </summary>
        /// <param name="fileInfo"><see cref="FileInfo"/> object for the current file.</param>
        private bool RaiseFileEvent(FileInfo fileInfo)
        {
            bool continueScan = true;

            // Create a new argument object for the file event.
            FileEventArgs args = new FileEventArgs(fileInfo);
            if (OnFileEvent == null)
            {
                return continueScan;
            }
            // Now raise the event.
            OnFileEvent(this, args);
            continueScan = !args.Cancel;
            return continueScan;
        }

        #region Handling of the ProgressEvent

        /// <summary>
        /// Definition for the FileEvent.
        ///	</summary>
        public delegate void ProgressEventHandler(object sender, ProgressEventArgs e);

        /// <summary>
        /// Event is raised for each file in a directory.
        /// </summary>
        public event ProgressEventHandler ProgressEvent;

        /// <summary>
        /// Raises the file event.
        /// </summary>
        /// <param name="fileInfo"><see cref="FileInfo"/> object for the current file.</param>
        private bool RaiseProgressEvent(int progress)
        {

            // Create a new argument object for the file event.
            ProgressEventArgs args = new ProgressEventArgs(progress);

            // Now raise the event.
            if (ProgressEvent != null) {
            ProgressEvent(this, args);
                }
            return true;
        }

        #endregion

        public Jounal(DriveBackup db)
        {
            driveBackup = db;
        }

        public List<FileObject> FileList { get { return fileList; } }

        public void AnaliseDrive()
        {
            /*
            String lastBackupPath = driveBackup.GetLastBackupFullpath();
            if (lastBackupPath == null)
            {
                driveBackup.NeedsScanning = false;
                driveBackup.NeedsBackingUp = true;
                return;
            }

            */
            //driveBackup.Jounal.ReadXML(lastBackupPath);
            driveBackup.Jounal.ScanDriveDirectories(driveBackup.DriveLetter);
        }

        public void ReadXML(String fileName)
        {
            //XmlReader xmlReader = new XmlReader();
            XmlDocument xmlDoc = new XmlDocument();
            xmlDoc.Load(fileName);

            XmlNodeList jounalNodeList = xmlDoc.GetElementsByTagName("Jounal");
            for (int i = 0; i < jounalNodeList.Count; ++i)
            {
                //Debug.Write(nodeList.Item(i).Name);
                XmlNode jounalNode = jounalNodeList.Item(i);
                if (jounalNode.HasChildNodes)
                {

                    XmlNodeList childNodeList = jounalNode.ChildNodes;
                    for (int j = 0; j < childNodeList.Count; j++)
                    {
                        // File and Directory nodes

                        XmlNode childNode = childNodeList.Item(j);
                        if (childNode.HasChildNodes)
                        {
                            //XmlNodeList fileAndDirectoryNodeList = childNode.ChildNodes;
                            //for (int r = 0; r < fileAndDirectoryNodeList.Count; r++)
                            //{
                            //    XmlNode fileAndDirectoryNode = fileAndDirectoryNodeList.Item(r);
                                if (childNode.Name == "File")
                                {
                                    if (childNode.HasChildNodes)
                                    {
                                        JounalItem ji = new JounalItem();
                                        ji.IsDirectory = false;
                                        XmlNodeList fileNodeList = childNode.ChildNodes;
                                        for (int k = 0; k < fileNodeList.Count; k++)
                                        {
                                            
                                            XmlNode fileNode = fileNodeList.Item(k);
                                            if (fileNode.Name == "Path")
                                            {
                                                ji.path = fileNode.InnerText;
                                            }
                                            if (fileNode.Name == "LastWriteTime")
                                            {
                                                ji.lastWriteTime = DateTime.Parse(fileNode.InnerText);
                                            }
                                        }
                                        fileLookup.Add(ji.path, ji);
                                    }

                                }
                                else if (childNode.Name == "Directory")
                                {
                                    if (childNode.HasChildNodes)
                                    {
                                        JounalItem ji = new JounalItem();
                                        ji.IsDirectory = true;
                                        XmlNodeList dirNodeList = childNode.ChildNodes;
                                        for (int k = 0; k < dirNodeList.Count; k++)
                                        {
                                            XmlNode dirNode = dirNodeList.Item(k);                     
                                            if (dirNode.Name == "Path")
                                            {
                                                ji.path = dirNode.InnerText;
                                            }
                                            if (dirNode.Name == "LastWriteTime")
                                            {
                                                ji.lastWriteTime = DateTime.Parse(dirNode.InnerText);
                                            }
                                             
                                        }
                                        fileLookup.Add(ji.path, ji);
                                    }
                                }
                            }
                           
                        }

                    }
                }
               
                /*
                // Display all key/value pairs in the Dictionary.
                foreach (KeyValuePair<String, JounalItem> kvp in fileLookup)
                {
                    Debug.Print("Key: " + kvp.Key + "\tValue: " + kvp.Value.path);
                }
          */

        
        }

        public void WriteXML(String sDir, String fileName, String driveID, String type, String discription)
        {
            _noFilesFound = 0;
            XmlDocument xmlDoc = new XmlDocument();
            // Write down the XML declaration
            XmlDeclaration xmlDeclaration = xmlDoc.CreateXmlDeclaration("1.0", "utf-8", null);

            // Create the root element
            XmlElement rootNode = xmlDoc.CreateElement("Backup");
            xmlDoc.InsertBefore(xmlDeclaration, xmlDoc.DocumentElement);
            xmlDoc.AppendChild(rootNode);

            XmlElement headerNode = xmlDoc.CreateElement("Header");
            rootNode.AppendChild(headerNode);

            XmlElement driveIDNode = xmlDoc.CreateElement("DriveID");
            XmlText driveIDText = xmlDoc.CreateTextNode(driveID);
            headerNode.AppendChild(driveIDNode);
            driveIDNode.AppendChild(driveIDText);

            XmlElement createdNode = xmlDoc.CreateElement("Created");
            XmlText createdText = xmlDoc.CreateTextNode(DateTime.Now.ToString());
            headerNode.AppendChild(createdNode);
            createdNode.AppendChild(createdText);

            XmlElement typeNode = xmlDoc.CreateElement("Type");
            XmlText typeText = xmlDoc.CreateTextNode(type);
            headerNode.AppendChild(typeNode);
            typeNode.AppendChild(typeText);

            XmlElement discriptionNode = xmlDoc.CreateElement("Discription");
            XmlText discriptionText = xmlDoc.CreateTextNode(discription);
            headerNode.AppendChild(discriptionNode);
            discriptionNode.AppendChild(discriptionText);

            XmlElement totalSizeNode = xmlDoc.CreateElement("TotalSize");
            headerNode.AppendChild(totalSizeNode);
            XmlElement numberFilesNode = xmlDoc.CreateElement("NumberFiles");
            headerNode.AppendChild(numberFilesNode);
            XmlElement numberFoldersNode = xmlDoc.CreateElement("NumberFolders");
            headerNode.AppendChild(numberFoldersNode);



            XmlElement jounalNode = xmlDoc.CreateElement("Jounal");
            xmlDoc.DocumentElement.AppendChild(jounalNode);

            DirSearch(sDir, xmlDoc, jounalNode);

            XmlText totalSizeText = xmlDoc.CreateTextNode(_totalSize.ToString());
            totalSizeNode.AppendChild(totalSizeText);
            XmlText numberFilesText = xmlDoc.CreateTextNode(_noFilesFound.ToString());
            numberFilesNode.AppendChild(numberFilesText);
            XmlText numberFoldersText = xmlDoc.CreateTextNode(_noFoldersFound.ToString());
            numberFoldersNode.AppendChild(numberFoldersText);
            noFilesFound = _noFilesFound;
            noFoldersFound = _noFoldersFound;
            totalSize = _totalSize;
            xmlDoc.Save(fileName);
        }

        public void WriteXML1(String sDir, String fileName, String driveID, String type, String discription)
        {
            _noFilesFound = 0;
            XmlDocument xmlDoc = new XmlDocument();
            // Write down the XML declaration
            XmlDeclaration xmlDeclaration = xmlDoc.CreateXmlDeclaration("1.0", "utf-8", null);

            // Create the root element
            XmlElement rootNode = xmlDoc.CreateElement("Backup");
            xmlDoc.InsertBefore(xmlDeclaration, xmlDoc.DocumentElement);
            xmlDoc.AppendChild(rootNode);

            XmlElement headerNode = xmlDoc.CreateElement("Header");
            rootNode.AppendChild(headerNode);

            XmlElement driveIDNode = xmlDoc.CreateElement("DriveID");
            XmlText driveIDText = xmlDoc.CreateTextNode(driveID);
            headerNode.AppendChild(driveIDNode);
            driveIDNode.AppendChild(driveIDText);

            XmlElement createdNode = xmlDoc.CreateElement("Created");
            XmlText createdText = xmlDoc.CreateTextNode(DateTime.Now.ToString());
            headerNode.AppendChild(createdNode);
            createdNode.AppendChild(createdText);

            XmlElement typeNode = xmlDoc.CreateElement("Type");
            XmlText typeText = xmlDoc.CreateTextNode(type);
            headerNode.AppendChild(typeNode);
            typeNode.AppendChild(typeText);

            XmlElement discriptionNode = xmlDoc.CreateElement("Discription");
            XmlText discriptionText = xmlDoc.CreateTextNode(discription);
            headerNode.AppendChild(discriptionNode);
            discriptionNode.AppendChild(discriptionText);

            XmlElement totalSizeNode = xmlDoc.CreateElement("TotalSize");
            headerNode.AppendChild(totalSizeNode);
            XmlElement numberFilesNode = xmlDoc.CreateElement("NumberFiles");
            headerNode.AppendChild(numberFilesNode);
            XmlElement numberFoldersNode = xmlDoc.CreateElement("NumberFolders");
            headerNode.AppendChild(numberFoldersNode);

            

            XmlElement jounalNode = xmlDoc.CreateElement("Jounal");
            xmlDoc.DocumentElement.AppendChild(jounalNode);

            DirSearch(sDir, xmlDoc, jounalNode);

            XmlText totalSizeText = xmlDoc.CreateTextNode(_totalSize.ToString());
            totalSizeNode.AppendChild(totalSizeText);
            XmlText numberFilesText = xmlDoc.CreateTextNode(_noFilesFound.ToString());
            numberFilesNode.AppendChild(numberFilesText);
            XmlText numberFoldersText = xmlDoc.CreateTextNode(_noFoldersFound.ToString());
            numberFoldersNode.AppendChild(numberFoldersText);
            noFilesFound = _noFilesFound;
            noFoldersFound = _noFoldersFound;
            totalSize = _totalSize;
            xmlDoc.Save(fileName);
        }
        public JounalItem GetItem(String path)
        {
            try
            {
                return fileLookup[path];
            }
            catch (Exception e)
            {
                return null;
            }
        }
        
        void DirSearch(String sDir, XmlDocument xmlDoc, XmlElement node)
        {
            
            try
            {

                foreach (string f in Directory.GetFiles(sDir))
                {
                    
                    _noFilesFound++;
                    filesDone++;
                    FileInfo fi = new FileInfo(f);
                    RaiseFileEvent(fi);
                    CRC32 crc32 = new CRC32();
                    String hash = String.Empty;
                    if (!quick)
                    {
                        using (FileStream fs = File.Open(f, FileMode.Open)) //here you pass the file name 
                        {
                            foreach (byte b in crc32.ComputeHash(fs))
                            {
                                hash += b.ToString("x2").ToLower();
                            }

                        }
                    }
                    _totalSize += fi.Length;
                    XmlElement parentNode = xmlDoc.CreateElement("File");
                    node.AppendChild(parentNode);

                    // Create the required nodes
                    XmlElement pathNode = xmlDoc.CreateElement("Path");
                    XmlElement dateModifiedNode = xmlDoc.CreateElement("LastWriteTime");
                    XmlElement attribNode = xmlDoc.CreateElement("Attributes");
                    XmlElement sizeNode = xmlDoc.CreateElement("Size");
                   
                    // retrieve the text 
                    XmlText pathText = xmlDoc.CreateTextNode(fi.FullName);
                    XmlText lastWriteTimeText = xmlDoc.CreateTextNode(fi.LastWriteTime.ToString());
                    XmlText attribText = xmlDoc.CreateTextNode(fi.Attributes.ToString());
                    XmlText sizeText = xmlDoc.CreateTextNode(fi.Length.ToString());
                    

                    // append the nodes to the parentNode without the value
                    parentNode.AppendChild(pathNode);
                    parentNode.AppendChild(dateModifiedNode);
                    parentNode.AppendChild(attribNode);
                    parentNode.AppendChild(sizeNode);
                    

                    // save the value of the fields into the nodes
                    pathNode.AppendChild(pathText);
                    dateModifiedNode.AppendChild(lastWriteTimeText);
                    attribNode.AppendChild(attribText);
                    sizeNode.AppendChild(sizeText);
                    if (!quick)
                    {
                        XmlElement crc32Node = xmlDoc.CreateElement("Crc32");
                        XmlText crc32Text = xmlDoc.CreateTextNode(hash);
                        parentNode.AppendChild(crc32Node);
                        crc32Node.AppendChild(crc32Text);
                    }

                }
                foreach (string d in Directory.GetDirectories(sDir))
                {
                    DirectoryInfo di = new DirectoryInfo(d);
                    _noFoldersFound++;

                    XmlElement parentNode = xmlDoc.CreateElement("Directory");
                    node.AppendChild(parentNode);

                    // Create the required nodes
                    XmlElement pathNode = xmlDoc.CreateElement("Path");
                    XmlElement dateModifiedNode = xmlDoc.CreateElement("LastWriteTime");
                    XmlElement attribNode = xmlDoc.CreateElement("Attributes");

                    // retrieve the text 
                    XmlText pathText = xmlDoc.CreateTextNode(di.FullName);
                    XmlText lastWriteTimeText = xmlDoc.CreateTextNode(di.LastWriteTime.ToString());
                    XmlText attribText = xmlDoc.CreateTextNode(di.Attributes.ToString());

                    // append the nodes to the parentNode without the value
                    parentNode.AppendChild(pathNode);
                    parentNode.AppendChild(dateModifiedNode);
                    parentNode.AppendChild(attribNode);

                    // save the value of the fields into the nodes
                    pathNode.AppendChild(pathText);
                    dateModifiedNode.AppendChild(lastWriteTimeText);
                    attribNode.AppendChild(attribText);

                    curSearchPath = d;
                    DirSearch(d, xmlDoc, node);
                }
            }
            catch (System.Exception excpt)
            {
                isError = true;
                lastErrorString = excpt.Message;
            } 
        }
        public bool IsError
        {
            get
            {
                bool err = isError;
                isError = false;
                return err;
            }
        }
        public String IastErrorString { get { return lastErrorString; } }

        public void ScanDriveDirectories(String path)
        {
            ScanDirectory scanDirectory = new ScanDirectory();
            scanDirectory.TotalSize = totalSize;
           
            // Add a DirectoryEvent to the class
            scanDirectory.DirectoryEvent += new ScanDirectory.DirectoryEventHandler(scanDirectory_DirectoryEvent);

            // Add a FileEvent to the class
            scanDirectory.FileEvent += new ScanDirectory.FileEventHandler(scanDirectory_FileEvent);

            scanDirectory.SearchPattern = "*.*";
            scanDirectory.readImageExtFile("C:\\ProgramData\\IDK Software\\ImageArchive1.0\\config\\ext.dat");
            scanDirectory.WalkDirectory(path);
            needsScanning = false;
            if (CompletedEvent != null)
            {
                CompletedEvent(this);
            }
        }

        /// <summary>
        /// Handles the DirectoryEvent event of the scanDirectory control.
        /// </summary>
        /// <param name="sender">The source of the event.</param>
        /// <param name="e">The <see cref="DirectoryEventArgs"/> instance containing the event data.</param>
        private void scanDirectory_ProgressEvent(object sender, ProgressEventArgs e)
        {
            Debug.Print("  Volume label:            {0, 15} ", e.Percent); 
        }

        /// <summary>
        /// Handles the DirectoryEvent event of the scanDirectory control.
        /// </summary>
        /// <param name="sender">The source of the event.</param>
        /// <param name="e">The <see cref="DirectoryEventArgs"/> instance containing the event data.</param>
        private void scanDirectory_DirectoryEvent(object sender, DirectoryEventArgs e)
        {

            switch (e.Action)
            {
                case ScanDirectoryAction.Enter:
                    if (e.Info.FullName.EndsWith(":\\") == true)
                    {
                        break; // skip drive letters
                    }
                    FolderItem fi = new FolderItem();
                    fi.path = e.Info.FullName.Substring(2); // do not include drive letter (X:)
                    
                    fi.attributes = e.Info.Attributes;
                    fi.lastWriteTime = e.Info.LastWriteTime;
                    fileList.Add(fi);
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
            noFilesFound++;
            filesDone++;
            
            FileItem fi = new FileItem();
            //fi.path = e.Info.FullName;
            fi.path = e.Info.FullName.Substring(2);
            fi.attributes = e.Info.Attributes;
            fi.crc32 = 0;
            fi.size = e.Info.Length;
            fi.lastWriteTime = e.Info.LastWriteTime;

            fileList.Add(fi);
            //OnFileEvent(this);
            processedSize += e.Info.Length;
            double processed = processedSize;
            double total = totalSize - availableFreeSpace;
            int progress = 0;
            double fraction = processed / total;
            fraction *= 100;
            progress = (int)fraction;
            Debug.Print("  processedSize:            {0, 15} ", fraction);
            Debug.Print("  Percent:            {0, 15} ", progress);
            RaiseProgressEvent((int)progress);
            //e.Cancel = !buttonCancel.Enabled;       
        }

      

    }
}
=======
using System;
using System.Collections.Generic;
using System.Text;
using System.Xml;
using System.IO;
using System.Collections;
using System.Diagnostics;

namespace SIATray
{

    public class BackupItem
    {
        public DateTime timeStamp;
        public BackupType type = BackupType.None;
        public String DriveID = null;
        public String Description = null;
        public String FolderName = null;
    }

    public class FileObject
    {
        public DateTime lastWriteTime;
        public String path = null;
        public FileAttributes attributes;
    }

    public class FileItem : FileObject
    {
        
        public long size = 0;
        public uint crc32 = 0;
    }

    public class FolderItem : FileObject
    {

    }


    public class JounalItem
    {
        public String path = null;
        public bool IsDirectory = false;
        public DateTime lastWriteTime;



    }

    

    public class Jounal
    {
        Dictionary<String, JounalItem> fileLookup = new Dictionary<string, JounalItem>(1000);
        private List<FileObject> fileList = new List<FileObject>();
        private static bool isError = false;
        private static String lastErrorString = null;
        private static String curSearchPath = null;
        private static int filesDone = 0;
        private static long _totalSize;
        private static int _noFilesFound = 0;
        private static int _noFoldersFound = 0;
        private String driveID = null;
        private String type = null;
        private String discription = null;
        private DriveBackup driveBackup;
        //private XmlDocument xmlDoc = new XmlDocument();
        private long totalSize;
        private long availableFreeSpace;
        private long processedSize;
        private int noFilesFound = 0;
        private int noFoldersFound = 0;
        private bool needsScanning = true;
        private bool quick = true;

        public long TotalSize { get { return totalSize; } set { totalSize = value; } }
        public long AvailableFreeSpace { get { return availableFreeSpace; } set { availableFreeSpace = value; } }
        public bool NeedsScanning { get { return needsScanning; } set { needsScanning = value; } }
        public DriveBackup DriveBackup { get { return driveBackup; } }

        public long NoFiles { get { return noFilesFound; } }
        public long NoFolders { get { return noFoldersFound; } }

        public delegate void CompletedEventHandler(object sender);
        public event CompletedEventHandler CompletedEvent;

        public delegate void OnFileEventHandler(object sender, FileEventArgs e);
        public event OnFileEventHandler OnFileEvent;

        /// <summary>
        /// Raises the file event.
        /// </summary>
        /// <param name="fileInfo"><see cref="FileInfo"/> object for the current file.</param>
        private bool RaiseFileEvent(FileInfo fileInfo)
        {
            bool continueScan = true;

            // Create a new argument object for the file event.
            FileEventArgs args = new FileEventArgs(fileInfo);
            if (OnFileEvent == null)
            {
                return continueScan;
            }
            // Now raise the event.
            OnFileEvent(this, args);
            continueScan = !args.Cancel;
            return continueScan;
        }

        #region Handling of the ProgressEvent

        /// <summary>
        /// Definition for the FileEvent.
        ///	</summary>
        public delegate void ProgressEventHandler(object sender, ProgressEventArgs e);

        /// <summary>
        /// Event is raised for each file in a directory.
        /// </summary>
        public event ProgressEventHandler ProgressEvent;

        /// <summary>
        /// Raises the file event.
        /// </summary>
        /// <param name="fileInfo"><see cref="FileInfo"/> object for the current file.</param>
        private bool RaiseProgressEvent(int progress)
        {

            // Create a new argument object for the file event.
            ProgressEventArgs args = new ProgressEventArgs(progress);

            // Now raise the event.
            if (ProgressEvent != null) {
            ProgressEvent(this, args);
                }
            return true;
        }

        #endregion

        public Jounal(DriveBackup db)
        {
            driveBackup = db;
        }

        public List<FileObject> FileList { get { return fileList; } }

        public void AnaliseDrive()
        {
            /*
            String lastBackupPath = driveBackup.GetLastBackupFullpath();
            if (lastBackupPath == null)
            {
                driveBackup.NeedsScanning = false;
                driveBackup.NeedsBackingUp = true;
                return;
            }

            */
            //driveBackup.Jounal.ReadXML(lastBackupPath);
            driveBackup.Jounal.ScanDriveDirectories(driveBackup.DriveLetter);
        }

        public void ReadXML(String fileName)
        {
            //XmlReader xmlReader = new XmlReader();
            XmlDocument xmlDoc = new XmlDocument();
            xmlDoc.Load(fileName);

            XmlNodeList jounalNodeList = xmlDoc.GetElementsByTagName("Jounal");
            for (int i = 0; i < jounalNodeList.Count; ++i)
            {
                //Debug.Write(nodeList.Item(i).Name);
                XmlNode jounalNode = jounalNodeList.Item(i);
                if (jounalNode.HasChildNodes)
                {

                    XmlNodeList childNodeList = jounalNode.ChildNodes;
                    for (int j = 0; j < childNodeList.Count; j++)
                    {
                        // File and Directory nodes

                        XmlNode childNode = childNodeList.Item(j);
                        if (childNode.HasChildNodes)
                        {
                            //XmlNodeList fileAndDirectoryNodeList = childNode.ChildNodes;
                            //for (int r = 0; r < fileAndDirectoryNodeList.Count; r++)
                            //{
                            //    XmlNode fileAndDirectoryNode = fileAndDirectoryNodeList.Item(r);
                                if (childNode.Name == "File")
                                {
                                    if (childNode.HasChildNodes)
                                    {
                                        JounalItem ji = new JounalItem();
                                        ji.IsDirectory = false;
                                        XmlNodeList fileNodeList = childNode.ChildNodes;
                                        for (int k = 0; k < fileNodeList.Count; k++)
                                        {
                                            
                                            XmlNode fileNode = fileNodeList.Item(k);
                                            if (fileNode.Name == "Path")
                                            {
                                                ji.path = fileNode.InnerText;
                                            }
                                            if (fileNode.Name == "LastWriteTime")
                                            {
                                                ji.lastWriteTime = DateTime.Parse(fileNode.InnerText);
                                            }
                                        }
                                        fileLookup.Add(ji.path, ji);
                                    }

                                }
                                else if (childNode.Name == "Directory")
                                {
                                    if (childNode.HasChildNodes)
                                    {
                                        JounalItem ji = new JounalItem();
                                        ji.IsDirectory = true;
                                        XmlNodeList dirNodeList = childNode.ChildNodes;
                                        for (int k = 0; k < dirNodeList.Count; k++)
                                        {
                                            XmlNode dirNode = dirNodeList.Item(k);                     
                                            if (dirNode.Name == "Path")
                                            {
                                                ji.path = dirNode.InnerText;
                                            }
                                            if (dirNode.Name == "LastWriteTime")
                                            {
                                                ji.lastWriteTime = DateTime.Parse(dirNode.InnerText);
                                            }
                                             
                                        }
                                        fileLookup.Add(ji.path, ji);
                                    }
                                }
                            }
                           
                        }

                    }
                }
               
                /*
                // Display all key/value pairs in the Dictionary.
                foreach (KeyValuePair<String, JounalItem> kvp in fileLookup)
                {
                    Debug.Print("Key: " + kvp.Key + "\tValue: " + kvp.Value.path);
                }
          */

        
        }

        public void WriteXML(String sDir, String fileName, String driveID, String type, String discription)
        {
            _noFilesFound = 0;
            XmlDocument xmlDoc = new XmlDocument();
            // Write down the XML declaration
            XmlDeclaration xmlDeclaration = xmlDoc.CreateXmlDeclaration("1.0", "utf-8", null);

            // Create the root element
            XmlElement rootNode = xmlDoc.CreateElement("Backup");
            xmlDoc.InsertBefore(xmlDeclaration, xmlDoc.DocumentElement);
            xmlDoc.AppendChild(rootNode);

            XmlElement headerNode = xmlDoc.CreateElement("Header");
            rootNode.AppendChild(headerNode);

            XmlElement driveIDNode = xmlDoc.CreateElement("DriveID");
            XmlText driveIDText = xmlDoc.CreateTextNode(driveID);
            headerNode.AppendChild(driveIDNode);
            driveIDNode.AppendChild(driveIDText);

            XmlElement createdNode = xmlDoc.CreateElement("Created");
            XmlText createdText = xmlDoc.CreateTextNode(DateTime.Now.ToString());
            headerNode.AppendChild(createdNode);
            createdNode.AppendChild(createdText);

            XmlElement typeNode = xmlDoc.CreateElement("Type");
            XmlText typeText = xmlDoc.CreateTextNode(type);
            headerNode.AppendChild(typeNode);
            typeNode.AppendChild(typeText);

            XmlElement discriptionNode = xmlDoc.CreateElement("Discription");
            XmlText discriptionText = xmlDoc.CreateTextNode(discription);
            headerNode.AppendChild(discriptionNode);
            discriptionNode.AppendChild(discriptionText);

            XmlElement totalSizeNode = xmlDoc.CreateElement("TotalSize");
            headerNode.AppendChild(totalSizeNode);
            XmlElement numberFilesNode = xmlDoc.CreateElement("NumberFiles");
            headerNode.AppendChild(numberFilesNode);
            XmlElement numberFoldersNode = xmlDoc.CreateElement("NumberFolders");
            headerNode.AppendChild(numberFoldersNode);



            XmlElement jounalNode = xmlDoc.CreateElement("Jounal");
            xmlDoc.DocumentElement.AppendChild(jounalNode);

            DirSearch(sDir, xmlDoc, jounalNode);

            XmlText totalSizeText = xmlDoc.CreateTextNode(_totalSize.ToString());
            totalSizeNode.AppendChild(totalSizeText);
            XmlText numberFilesText = xmlDoc.CreateTextNode(_noFilesFound.ToString());
            numberFilesNode.AppendChild(numberFilesText);
            XmlText numberFoldersText = xmlDoc.CreateTextNode(_noFoldersFound.ToString());
            numberFoldersNode.AppendChild(numberFoldersText);
            noFilesFound = _noFilesFound;
            noFoldersFound = _noFoldersFound;
            totalSize = _totalSize;
            xmlDoc.Save(fileName);
        }

        public void WriteXML1(String sDir, String fileName, String driveID, String type, String discription)
        {
            _noFilesFound = 0;
            XmlDocument xmlDoc = new XmlDocument();
            // Write down the XML declaration
            XmlDeclaration xmlDeclaration = xmlDoc.CreateXmlDeclaration("1.0", "utf-8", null);

            // Create the root element
            XmlElement rootNode = xmlDoc.CreateElement("Backup");
            xmlDoc.InsertBefore(xmlDeclaration, xmlDoc.DocumentElement);
            xmlDoc.AppendChild(rootNode);

            XmlElement headerNode = xmlDoc.CreateElement("Header");
            rootNode.AppendChild(headerNode);

            XmlElement driveIDNode = xmlDoc.CreateElement("DriveID");
            XmlText driveIDText = xmlDoc.CreateTextNode(driveID);
            headerNode.AppendChild(driveIDNode);
            driveIDNode.AppendChild(driveIDText);

            XmlElement createdNode = xmlDoc.CreateElement("Created");
            XmlText createdText = xmlDoc.CreateTextNode(DateTime.Now.ToString());
            headerNode.AppendChild(createdNode);
            createdNode.AppendChild(createdText);

            XmlElement typeNode = xmlDoc.CreateElement("Type");
            XmlText typeText = xmlDoc.CreateTextNode(type);
            headerNode.AppendChild(typeNode);
            typeNode.AppendChild(typeText);

            XmlElement discriptionNode = xmlDoc.CreateElement("Discription");
            XmlText discriptionText = xmlDoc.CreateTextNode(discription);
            headerNode.AppendChild(discriptionNode);
            discriptionNode.AppendChild(discriptionText);

            XmlElement totalSizeNode = xmlDoc.CreateElement("TotalSize");
            headerNode.AppendChild(totalSizeNode);
            XmlElement numberFilesNode = xmlDoc.CreateElement("NumberFiles");
            headerNode.AppendChild(numberFilesNode);
            XmlElement numberFoldersNode = xmlDoc.CreateElement("NumberFolders");
            headerNode.AppendChild(numberFoldersNode);

            

            XmlElement jounalNode = xmlDoc.CreateElement("Jounal");
            xmlDoc.DocumentElement.AppendChild(jounalNode);

            DirSearch(sDir, xmlDoc, jounalNode);

            XmlText totalSizeText = xmlDoc.CreateTextNode(_totalSize.ToString());
            totalSizeNode.AppendChild(totalSizeText);
            XmlText numberFilesText = xmlDoc.CreateTextNode(_noFilesFound.ToString());
            numberFilesNode.AppendChild(numberFilesText);
            XmlText numberFoldersText = xmlDoc.CreateTextNode(_noFoldersFound.ToString());
            numberFoldersNode.AppendChild(numberFoldersText);
            noFilesFound = _noFilesFound;
            noFoldersFound = _noFoldersFound;
            totalSize = _totalSize;
            xmlDoc.Save(fileName);
        }
        public JounalItem GetItem(String path)
        {
            try
            {
                return fileLookup[path];
            }
            catch (Exception e)
            {
                return null;
            }
        }
        
        void DirSearch(String sDir, XmlDocument xmlDoc, XmlElement node)
        {
            
            try
            {

                foreach (string f in Directory.GetFiles(sDir))
                {
                    
                    _noFilesFound++;
                    filesDone++;
                    FileInfo fi = new FileInfo(f);
                    RaiseFileEvent(fi);
                    CRC32 crc32 = new CRC32();
                    String hash = String.Empty;
                    if (!quick)
                    {
                        using (FileStream fs = File.Open(f, FileMode.Open)) //here you pass the file name 
                        {
                            foreach (byte b in crc32.ComputeHash(fs))
                            {
                                hash += b.ToString("x2").ToLower();
                            }

                        }
                    }
                    _totalSize += fi.Length;
                    XmlElement parentNode = xmlDoc.CreateElement("File");
                    node.AppendChild(parentNode);

                    // Create the required nodes
                    XmlElement pathNode = xmlDoc.CreateElement("Path");
                    XmlElement dateModifiedNode = xmlDoc.CreateElement("LastWriteTime");
                    XmlElement attribNode = xmlDoc.CreateElement("Attributes");
                    XmlElement sizeNode = xmlDoc.CreateElement("Size");
                   
                    // retrieve the text 
                    XmlText pathText = xmlDoc.CreateTextNode(fi.FullName);
                    XmlText lastWriteTimeText = xmlDoc.CreateTextNode(fi.LastWriteTime.ToString());
                    XmlText attribText = xmlDoc.CreateTextNode(fi.Attributes.ToString());
                    XmlText sizeText = xmlDoc.CreateTextNode(fi.Length.ToString());
                    

                    // append the nodes to the parentNode without the value
                    parentNode.AppendChild(pathNode);
                    parentNode.AppendChild(dateModifiedNode);
                    parentNode.AppendChild(attribNode);
                    parentNode.AppendChild(sizeNode);
                    

                    // save the value of the fields into the nodes
                    pathNode.AppendChild(pathText);
                    dateModifiedNode.AppendChild(lastWriteTimeText);
                    attribNode.AppendChild(attribText);
                    sizeNode.AppendChild(sizeText);
                    if (!quick)
                    {
                        XmlElement crc32Node = xmlDoc.CreateElement("Crc32");
                        XmlText crc32Text = xmlDoc.CreateTextNode(hash);
                        parentNode.AppendChild(crc32Node);
                        crc32Node.AppendChild(crc32Text);
                    }

                }
                foreach (string d in Directory.GetDirectories(sDir))
                {
                    DirectoryInfo di = new DirectoryInfo(d);
                    _noFoldersFound++;

                    XmlElement parentNode = xmlDoc.CreateElement("Directory");
                    node.AppendChild(parentNode);

                    // Create the required nodes
                    XmlElement pathNode = xmlDoc.CreateElement("Path");
                    XmlElement dateModifiedNode = xmlDoc.CreateElement("LastWriteTime");
                    XmlElement attribNode = xmlDoc.CreateElement("Attributes");

                    // retrieve the text 
                    XmlText pathText = xmlDoc.CreateTextNode(di.FullName);
                    XmlText lastWriteTimeText = xmlDoc.CreateTextNode(di.LastWriteTime.ToString());
                    XmlText attribText = xmlDoc.CreateTextNode(di.Attributes.ToString());

                    // append the nodes to the parentNode without the value
                    parentNode.AppendChild(pathNode);
                    parentNode.AppendChild(dateModifiedNode);
                    parentNode.AppendChild(attribNode);

                    // save the value of the fields into the nodes
                    pathNode.AppendChild(pathText);
                    dateModifiedNode.AppendChild(lastWriteTimeText);
                    attribNode.AppendChild(attribText);

                    curSearchPath = d;
                    DirSearch(d, xmlDoc, node);
                }
            }
            catch (System.Exception excpt)
            {
                isError = true;
                lastErrorString = excpt.Message;
            } 
        }
        public bool IsError
        {
            get
            {
                bool err = isError;
                isError = false;
                return err;
            }
        }
        public String IastErrorString { get { return lastErrorString; } }

        public void ScanDriveDirectories(String path)
        {
            ScanDirectory scanDirectory = new ScanDirectory();
            scanDirectory.TotalSize = totalSize;
           
            // Add a DirectoryEvent to the class
            scanDirectory.DirectoryEvent += new ScanDirectory.DirectoryEventHandler(scanDirectory_DirectoryEvent);

            // Add a FileEvent to the class
            scanDirectory.FileEvent += new ScanDirectory.FileEventHandler(scanDirectory_FileEvent);

            scanDirectory.SearchPattern = "*.*";
            scanDirectory.readImageExtFile("C:\\ProgramData\\IDK Software\\ImageArchive1.0\\config\\ext.dat");
            scanDirectory.WalkDirectory(path);
            needsScanning = false;
            if (CompletedEvent != null)
            {
                CompletedEvent(this);
            }
        }

        /// <summary>
        /// Handles the DirectoryEvent event of the scanDirectory control.
        /// </summary>
        /// <param name="sender">The source of the event.</param>
        /// <param name="e">The <see cref="DirectoryEventArgs"/> instance containing the event data.</param>
        private void scanDirectory_ProgressEvent(object sender, ProgressEventArgs e)
        {
            Debug.Print("  Volume label:            {0, 15} ", e.Percent); 
        }

        /// <summary>
        /// Handles the DirectoryEvent event of the scanDirectory control.
        /// </summary>
        /// <param name="sender">The source of the event.</param>
        /// <param name="e">The <see cref="DirectoryEventArgs"/> instance containing the event data.</param>
        private void scanDirectory_DirectoryEvent(object sender, DirectoryEventArgs e)
        {

            switch (e.Action)
            {
                case ScanDirectoryAction.Enter:
                    if (e.Info.FullName.EndsWith(":\\") == true)
                    {
                        break; // skip drive letters
                    }
                    FolderItem fi = new FolderItem();
                    fi.path = e.Info.FullName.Substring(2); // do not include drive letter (X:)
                    
                    fi.attributes = e.Info.Attributes;
                    fi.lastWriteTime = e.Info.LastWriteTime;
                    fileList.Add(fi);
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
            noFilesFound++;
            filesDone++;
            
            FileItem fi = new FileItem();
            //fi.path = e.Info.FullName;
            fi.path = e.Info.FullName.Substring(2);
            fi.attributes = e.Info.Attributes;
            fi.crc32 = 0;
            fi.size = e.Info.Length;
            fi.lastWriteTime = e.Info.LastWriteTime;

            fileList.Add(fi);
            //OnFileEvent(this);
            processedSize += e.Info.Length;
            double processed = processedSize;
            double total = totalSize - availableFreeSpace;
            int progress = 0;
            double fraction = processed / total;
            fraction *= 100;
            progress = (int)fraction;
            Debug.Print("  processedSize:            {0, 15} ", fraction);
            Debug.Print("  Percent:            {0, 15} ", progress);
            RaiseProgressEvent((int)progress);
            //e.Cancel = !buttonCancel.Enabled;       
        }

      

    }
}
>>>>>>> 291c508aa47ea3e34a225d431ef34192e909c4ee
