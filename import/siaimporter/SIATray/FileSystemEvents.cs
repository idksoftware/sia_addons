using System;
using System.Collections.Generic;
using System.Text;
using System.IO;
using System.Diagnostics;

namespace SIATray
{
    #region Event argument definition for ScanDirectory.FileEvent

	/// <summary>
	/// Information about the file in the current directory.
	/// </summary>
	public class FileEventArgs : EventArgs
	{
		#region Constructors

		/// <summary>
		/// Block the default constructor.
		/// </summary>
		private FileEventArgs() {	}

		/// <summary>
		/// Initializes a new instance of the <see cref="DirectoryEventArgs"/> class.
		/// </summary>
		/// <param name="fileInfo"><see cref="FileInfo"/> object for the current file.</param>
		internal FileEventArgs(FileInfo fileInfo)
		{
			if (fileInfo == null) throw new ArgumentNullException("fileInfo");
			
			// Get File information 
			_fileInfo = fileInfo;
		}

		#endregion

		#region Properties

		private bool			_cancel;
		private FileInfo		_fileInfo;

		/// <summary>
		/// Gets the current file information.
		/// </summary>
		/// <value>The <see cref="FileInfo"/> object for the current file.</value>
		public FileInfo Info
		{
			get { return _fileInfo; }
		}

		/// <summary>
		/// Gets or sets a value indicating whether to cancel the directory scan.
		/// </summary>
		/// <value>
		/// <see langword="true"/> if the scan must be cancelled; otherwise, <see langword="false"/>.
		/// </value>
		public bool Cancel
		{
			get { return _cancel; }
			set { _cancel = value; }
		}

		#endregion
	}

	#endregion

	#region Event argument definition for ScanDirectory.DirectoryEvent

	/// <summary>
	/// Event arguments for the DirectoryEvent
	/// </summary>
	public class DirectoryEventArgs : EventArgs
	{
		#region Constructors

		/// <summary>
		/// Block the default constructor.
		/// </summary>
		private DirectoryEventArgs() {	}

		/// <summary>
		/// Initializes a new instance of the <see cref="DirectoryEventArgs"/> class.
		/// </summary>
		/// <param name="directory"><see cref="DirectoryInfo"/> object for the current path.</param>
		/// <param name="action">The action.</param>
		internal DirectoryEventArgs(DirectoryInfo directory, ScanDirectoryAction action)
		{
			if (directory == null) throw new ArgumentNullException("directory");
			
			// Get File information 
			_directoryInfo = directory;
			_action = action;
		}

        #endregion
        #region Properties

        private DirectoryInfo _directoryInfo;
        private ScanDirectoryAction _action;
        private bool _cancel;

        /// <summary>
        /// Gets the current directory information.
        /// </summary>
        /// <value>The <see cref="DirectoryInfo"/> object for the current directory.</value>
        public DirectoryInfo Info
        {
            get { return _directoryInfo; }
        }

        /// <summary>
        /// Gets the current directory action.
        /// </summary>
        /// <value>The <see cref="ScanDirectoryAction"/> action value.</value>
        public ScanDirectoryAction Action
        {
            get { return _action; }
        }

        /// <summary>
        /// Gets or sets a value indicating whether to cancel the directory scan.
        /// </summary>
        /// <value>
        /// <see langword="true"/> if the scan must be cancelled; otherwise, <see langword="false"/>.
        /// </value>
        public bool Cancel
        {
            get { return _cancel; }
            set { _cancel = value; }
        }

        #endregion
    }

    #endregion
    #region Event argument definition for ProgressEventArgs
    /// <summary>
    /// Information about the file in the current directory.
    /// </summary>
    public class ProgressEventArgs : EventArgs
    {
        #region Constructors

        /// <summary>
        /// Block the default constructor.
        /// </summary>
        private ProgressEventArgs() { }

        /// <summary>
        /// Initializes a new instance of the <see cref="DirectoryEventArgs"/> class.
        /// </summary>
        /// <param name="fileInfo"><see cref="FileInfo"/> object for the current file.</param>
        internal ProgressEventArgs(int percent)
        {
            if (percent > 100) throw new ArgumentNullException("PercentOutOfRange");

            // Get File information 
            _percent = percent;
        }

        #endregion

        #region Properties


        private int _percent;

        /// <summary>
        /// Gets the current file information.
        /// </summary>
        /// <value>The <see cref="FileInfo"/> object for the current file.</value>
        public int Percent
        {
            get { return _percent; }
        }

        #endregion
    }

    #endregion
}
