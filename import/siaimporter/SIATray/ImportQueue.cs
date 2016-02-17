using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SIATray
{
    enum ImportStatus {
        Pending,
        InProgress,
        Completed,
        Error
    }
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
        public ImportStatus ImportStatus { get { return importStatus; } }
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

        static readonly ImportQueue instance = new ImportQueue();

        static ImportQueue()
        {
            
        }
        public static ImportQueue Instance
        {
            get
            {
                return instance;
            }
        }
    }
}
