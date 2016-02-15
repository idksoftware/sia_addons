using System;
using System.IO;
using System.Diagnostics;
using System.Collections.Generic;

namespace SIATray
{
	/// <summary>
	/// Defines the action on a directory which triggered the event
	/// </summary>
	public enum ScanDirectoryAction
	{
		/// <summary>
		/// Enter a directory
		/// </summary>
		Enter,

		/// <summary>
		/// Leave a directory
		/// </summary>
		Leave
	}

 	
	/// <summary>
	/// Scan directory trees
	/// </summary>
	public class ScanDirectory
	{

		private const string _patternAllFiles = "*.*";
        private bool isError = false;
        private String lastError = null;
        private long totalSize = 0;
        private long progressedSize;
        private List<String> fileExtentions = new List<String>();

        public long TotalSize
        {
            set { totalSize = value; }
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

		#region Handling of the DirectoryEvent

		/// <summary>
		/// Definition for the DirectoryEvent.
		/// </summary>
		public delegate void DirectoryEventHandler(object sender, DirectoryEventArgs e); 

		/// <summary>
		/// Event is raised for each directory.
		/// </summary>
		public event DirectoryEventHandler DirectoryEvent;

		/// <summary>
		/// Raises the directory event.
		/// </summary>
		/// <param name="directory"><see cref="DirectoryInfo"/> object for the current path.</param>
		/// <param name="action">The <see cref="ScanDirectoryAction"/> action value.</param>
		/// <returns><see langword="true"/> when the scan is allowed to continue. <see langword="false"/> if otherwise;</returns>
		private bool RaiseDirectoryEvent(DirectoryInfo directory, ScanDirectoryAction action)
		{
			bool continueScan = true;

			// Only do something when the event has been declared.
			if (FileEvent != null)
			{
				// Create a new argument object for the file event.
				DirectoryEventArgs args = new DirectoryEventArgs(directory, action);

				// Now raise the event.
				DirectoryEvent(this, args);

				continueScan = !args.Cancel;
			}
			return continueScan;
		}

		#endregion

		#region Public methods

       

		/// <summary>
		/// Walks the specified path.
		/// </summary>
		/// <param name="path">The path.</param>
		/// <returns><see langword="true"/> when the scan finished without being interupted. <see langword="false"/> if otherwise;</returns>
		public bool WalkDirectory(string path)
		{
			// Validate path argument.
			if (path == null || path.Length == 0) throw new ArgumentNullException("path");

			return this.WalkDirectory(new DirectoryInfo(path));
		}

		/// <summary>
		/// Walks the specified directory.
		/// </summary>
		/// <param name="directory"><see cref="DirectoryInfo"/> object for the current path.</param>
		/// <returns><see langword="true"/> when the scan finished without being interupted. <see langword="false"/> if otherwise;</returns>
		public bool WalkDirectory(DirectoryInfo directory)
		{
			if (directory == null) 
			{
				throw new ArgumentNullException("directory");
			}

			return this.WalkDirectories(directory);
		}

        public bool IsError()
        {
            return isError;
        }

        public String GetLastError()
        {
            return lastError;
        }

		#endregion

		#region Overridable methods 

		/// <summary>
		/// Processes the directory.
		/// </summary>
		/// <param name="directoryInfo">The directory info.</param>
		/// <param name="action">The action.</param>
		/// <returns><see langword="true"/> when the scan is allowed to continue. <see langword="false"/> if otherwise;</returns>
		public virtual bool ProcessDirectory(DirectoryInfo directoryInfo, ScanDirectoryAction action)
		{
			if (DirectoryEvent != null)
			{
				return RaiseDirectoryEvent(directoryInfo, action);
			}
			return true;
		}

		/// <summary>
		/// Processes the file.
		/// </summary>
		/// <param name="fileInfo">The file info.</param>
		/// <returns><see langword="true"/> when the scan is allowed to continue. <see langword="false"/> if otherwise;</returns>
		public virtual bool ProcessFile(FileInfo fileInfo)
		{
            if (fileInfo.Extension.Length == 0) {
                return true; // not a problem just will not be processed
            }
            String ext = fileInfo.Extension.Substring(1,fileInfo.Extension.Length - 1);
            if (fileExtentions.FindIndex(x => x.Equals(ext, StringComparison.OrdinalIgnoreCase)) == -1)
            {
                
                return true; // not a problem just will not be processed
            }

            Debug.Print(
                   "       File:            {0, 15} ",
                   fileInfo.FullName);
			// Only do something when the event has been declared.
            progressedSize += fileInfo.Length;
            //int percent = 600 / 1000;
			if (FileEvent != null)
			{
				RaiseFileEvent(fileInfo);
			}
            /*
            if (ProgressEvent != null)
            {
                RaiseProgressEvent(percent);
            }
             */
			return true;
		}

		#endregion

		#region Private methods

		/// <summary>
		/// Walks the directory tree starting at the specified directory.
		/// </summary>
		/// <param name="directory"><see cref="DirectoryInfo"/> object for the current directory.</param>
		/// <returns><see langword="true"/> when the scan is allowed to continue. <see langword="false"/> if otherwise;</returns>
		private bool WalkDirectories(DirectoryInfo directory)
		{
			bool continueScan = true;

			if (continueScan = this.ProcessDirectory(directory, ScanDirectoryAction.Enter))
			{
				// Only scan the files in this path when a file event was specified 
				if (this.FileEvent != null)
				{
					continueScan = WalkFilesInDirectory(directory);
				}

				if (continueScan)
				{
                    try
                    {
                        DirectoryInfo[] subDirectories = directory.GetDirectories();
                   
					    foreach (DirectoryInfo subDirectory in subDirectories)
					    {
						    // It is possible that users create a recursive directory by mounting a drive
						    // into an existing directory on that same drive. If so, the attributes
						    // will have the ReparsePoint flag active. The directory is then skipped.
						    // See: http://blogs.msdn.com/oldnewthing/archive/2004/12/27/332704.aspx
						    if ((subDirectory.Attributes & FileAttributes.ReparsePoint) != 0)
						    {
							    continue;
						    }

						    if (!(continueScan = this.WalkDirectory(subDirectory)))
						    {
							    break;
						    }
					    }
                    }
                    catch (Exception ex)
                    {
                        isError = true;
                        lastError = ex.Message;
                    }
				}

				if (continueScan)
				{
					continueScan = this.ProcessDirectory(directory, ScanDirectoryAction.Leave);
				}
			}
			return continueScan;
		}

		/// <summary>
		/// Walks the directory tree starting at the specified path.
		/// </summary>
		/// <param name="directory"><see cref="DirectoryInfo"/> object for the current path.</param>
		/// <returns><see langword="true"/> when the scan was cancelled. <see langword="false"/> if otherwise;</returns>
		private bool WalkFilesInDirectory(DirectoryInfo directory)
		{
			bool continueScan = true;
            try
            {
                // Break up the search pattern in separate patterns
                string[] searchPatterns = _searchPattern.Split(';');

                // Try to find files for each search pattern
                foreach (string searchPattern in searchPatterns)
                {
                    if (!continueScan)
                    {
                        break;
                    }
                    // Scan all files in the current path
                    foreach (FileInfo file in directory.GetFiles(searchPattern))
                    {
                        Debug.Print("File: {0}", file.FullName);
                        if (!(continueScan = this.ProcessFile(file)))
                        {
                            break;
                        }
                    }
                }
                
            }
            catch (Exception ex)
            {
                isError = true;
                lastError = ex.Message;
            }
            return continueScan;
		}

		#endregion

		#region Properties

		private string _searchPattern;

		/// <summary>
		/// Gets or sets the search pattern.
		/// </summary>
		/// <example>
		/// You can specify more than one seach pattern
		/// </example>
		/// <value>The search pattern.</value>
		public string SearchPattern
		{
			get { return _searchPattern;  }
			set 
			{
				// When an empty value is specified, the search pattern will be the default (= *.*)
				if (value == null || value.Trim().Length == 0)
				{
					_searchPattern = _patternAllFiles;
				}
				else
				{
					_searchPattern = value; 
					// make sure the pattern does not end with a semi-colon
					_searchPattern = _searchPattern.TrimEnd(new char [] {';'});
				}
			}
		}

		#endregion

        public bool readImageExtFile(string path)
        {
            
            string line;

            // Read the file and display it line by line.
            System.IO.StreamReader file = new System.IO.StreamReader(path);
            while ((line = file.ReadLine()) != null)
            {
                Console.WriteLine(line);
                String[] items = line.Split(':');
                fileExtentions.Add(items[0]);
            }

            file.Close();

            // Suspend the screen.
            Console.ReadLine();

            return true;
        }
	}

   
}
