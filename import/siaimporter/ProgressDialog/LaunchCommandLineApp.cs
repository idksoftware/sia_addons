using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Diagnostics;

namespace ProgressDialog
{
    // add --source-path="Z:\Pictures/Photos/LightRoom backup/temp/DCIM/100D3200" --events
    // C:\development\sia\SimpleArchive\Release
    class LaunchCommandLine
    {
        public enum Status
        {
            Running,
            Completed,
            Error
        };
        private static int elapsedTime;
        static bool eventHandled = false;

        Thread _runThread;
        
        static readonly LaunchCommandLine instance = new LaunchCommandLine();

        public delegate void StatusEventHandler(Status param);
        public static StatusEventHandler StatusChanged;

        const string path = "C:\\development\\sia\\SimpleArchive\\Release\\sia.exe";

        // Explicit static constructor to tell C# compiler
        // not to mark type as beforefieldinit
        static LaunchCommandLine()
        {
        }

        LaunchCommandLine()
        {
            _runThread = null;
            
        }

        public static LaunchCommandLine Instance
        {
            get
            {
                return instance;
            }
        }

        public int ElapsedTime
        {
            get
            {
                return elapsedTime;
            }
        }
        public void Run()
        {
            
            StartRunning();
        }
        private void StartRunning()
        {

            _runThread = new Thread(LaunchCommand);
            _runThread.Start();
            if (StatusChanged != null)
            {
                StatusChanged(Status.Running);
            }
        }
         /// <summary>
    /// Launch the legacy application with some options set.
    /// </summary>
        void LaunchCommand()
        {
            //Process process = new Process();
            try
            {
 
                
	            // Use ProcessStartInfo class.
	            ProcessStartInfo startInfo = new ProcessStartInfo();
                //startInfo.RedirectStandardOutput = true;
	            //startInfo.CreateNoWindow = true;
                startInfo.CreateNoWindow = false;
	            startInfo.UseShellExecute = false;
                startInfo.WorkingDirectory = "C:\\development\\sia\\SimpleArchive\\Release";
	            startInfo.FileName = path;
	            startInfo.WindowStyle = ProcessWindowStyle.Hidden;
	            //startInfo.Arguments = "add --source-path=\"Z:\\Pictures/Photos/LightRoom backup/temp/DCIM/100D3200\" --events";
                startInfo.Arguments = "add --source-path=\"C:\\test\" --events";
                
                Process process = Process.Start(startInfo);
                process.EnableRaisingEvents = true;
                process.Exited += new EventHandler(OnProcessExited);

                const int SLEEP_AMOUNT = 100;
                while (!eventHandled)
                {
                    elapsedTime += SLEEP_AMOUNT;
                   
                    Thread.Sleep(SLEEP_AMOUNT);
               
                }

                /*
                process.WaitForExit();
                if (StatusChanged != null)
                {
                    StatusChanged(Status.Completed);
                }
                 */

            }
            catch (Exception ex)
            {
                if (StatusChanged != null)
                {
                    StatusChanged(Status.Error);
                }
                return;
            }

            
        }

/*
	        try
	        {
	            // Start the process with the info we specified.
	            // Call WaitForExit and then the using-statement will close.
                Process.EnableRaisingEvents = true;
                startInfo.Exited += new EventHandler(myProcess_Exited);
	            using (Process exeProcess = Process.Start(startInfo))
	            {
		            exeProcess.WaitForExit();
                    if (StatusChanged != null)
                    {
                        StatusChanged(Status.Completed);
                    }
	            }
	        }
	        catch
	        {
                // Log error.
                if (StatusChanged != null)
                {
                    StatusChanged(Status.Error);
                }

            }
 */
        
        // Handle Exited event and display process information.
        private void OnProcessExited(object sender, System.EventArgs e)
        {
            eventHandled = true;
            if (StatusChanged != null)
            {
                StatusChanged(Status.Completed);
            }
            //eventHandled = true;
            //Console.WriteLine("Exit time:    {0}\r\n" +
            //    "Exit code:    {1}\r\nElapsed time: {2}", myProcess.ExitTime, myProcess.ExitCode, elapsedTime);
        }


    }
}
