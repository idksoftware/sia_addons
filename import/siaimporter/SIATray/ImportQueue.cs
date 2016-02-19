using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using ProgressDialog;

namespace SIATray
{
    enum ImportStatus {
        Pending,
        InProgress,
        Completed,
        Error
    };
    class ImportJob {
        String trackingNo;
        DateTime dateTime;
        String location;
        bool subFolders = true;
        ImportStatus importStatus;
        public String TrackingNo { get { return trackingNo; } }
        public DateTime DateTime { get { return dateTime; } }
        public String Location { get { return location; } }
        public bool SubFolders { get { return subFolders; } }
        public ImportStatus ImportStatus { get { return importStatus; } set { importStatus = value; } }
        public ImportJob(String location, bool subFolders = true)
        {
           this.dateTime = DateTime.Now;
           this.trackingNo = makeTrackingNo(this.dateTime);
           this.location = location;
           this.subFolders = subFolders;
           importStatus = ImportStatus.Pending;

        }

        String makeTrackingNo(DateTime dateTime)
        {
            String year = dateTime.Year.ToString("00").Substring(2);
            String tn = year + dateTime.Month.ToString("00") + dateTime.Day.ToString("00")
                + dateTime.Hour.ToString("00") + dateTime.Minute.ToString("00") + dateTime.Second.ToString("00");
            return tn;
        }
    }
    class ImportQueue : List<ImportJob> {
        Thread _processThread;
        bool _running = false;
        static readonly ImportQueue instance = new ImportQueue();
        public delegate void EventHandler(ImportStatus param);
        public EventHandler StatusChanged;
        private ImportJob currentJob = null;
        
        static ImportQueue()
        {
            
        }

       
        public void Start()
        {

            LaunchCommandLine.StatusChanged += OnProcessStatusChanged;
            if (!_running)
            {
                _running = true;
                _processThread = new Thread(Process);
                _processThread.Start();
            }
        }

        public static ImportQueue Instance
        {
            get
            {
                return instance;
            }
        }

        private void Process()
        {
            const int SLEEP_AMOUNT = 100;
            try
            {
                while (_running)
                {
                    if (this.Count != 0)
                    {
                        foreach (ImportJob job in this)
                        {
                            if (job.ImportStatus == ImportStatus.Pending)
                            {
                                if (currentJob == null)
                                {
                                    currentJob = job;
                                    if (StatusChanged != null)
                                    {
                                        StatusChanged(ImportStatus.InProgress);
                                    }
                                    job.ImportStatus = ImportStatus.Pending;
                                    
                                }
                            }
                        }
                        
                    }
                    Thread.Sleep(SLEEP_AMOUNT);
                }
            }
            catch (Exception e)
            {
                
            }         
            return;
        }


        public ImportJob CurrentJob { get { return currentJob; } }
        public void Add(String location, bool subFolders = true) {
            ImportJob job = new ImportJob(location, subFolders);
            Add(job);
            /*
            if (StatusChanged != null)
            {
                
                StatusChanged(ImportStatus.Pending);
            }
            
            if (currentJob == null)
            {
                currentJob = job;
                if (StatusChanged != null) {
                    StatusChanged(ImportStatus.InProgress);
                }
            }
             */
        }

        void OnProcessStatusChanged(LaunchCommandLine.Status param)
        {
            switch(param) {
            case LaunchCommandLine.Status.Completed:
                currentJob.ImportStatus = ImportStatus.Completed;
                break;
            default:
                currentJob.ImportStatus = ImportStatus.Error;
                break;
            }
            currentJob = null;
        }
       
    }
}
