using System;
using System.Collections.Generic;
using System.Text;
using Microsoft.Win32;

namespace IDK.Gui
{
    public class RegInitalise : IDisposable
    {
        static protected RegistryKey regKey;
        static protected RegistryKey regSubKey;
        static protected String errorString = null;
        static protected bool isError = false;
        static protected bool isInstalled = false;
        static protected bool isSetup = false;

        protected bool InitReg()
        {
            try
            {
                //regKey = Registry.LocalMachine;
                regKey = Registry.CurrentUser;
                String baseRegisterStr = RegistryPath.Path;
                regSubKey = regKey.CreateSubKey(baseRegisterStr.ToString());
                if (regSubKey.ValueCount == 0)
                {
                    return false;
                }
                return true;
            }
            catch (Exception e)
            {
                errorString = e.Message;
                return false;
            }
            
            
        }

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected virtual void Dispose(bool disposing)
        {
            if (disposing && (regSubKey != null))
            {
                regSubKey.Close();
            }
            //base.Dispose(disposing);
        }

        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }

        static String ErrSring { get { return errorString; }}
        static bool IsError { get { return IsError; } }

        static public bool IsInstalled { get { return isInstalled; } }
        static public bool IsSetup { get { return isSetup; } }
    }
}
