using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace IMGProperties
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main(string[] args)
        {
            String filename = null;
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            foreach (string arg in args)
            {
                filename = arg.ToString();
            }
            Application.Run(new MainForm(filename));
        }
    }
}