using System;
using System.Collections.Generic;
using System.Text;
using Microsoft.Win32;

namespace IDK.Gui
{
    public class Shareware : RegInitalise 
    {
        static String[] validStringList = {
                "DADSNB",
                "ODNGHH",
                "UUFGSS",
                "HHJIXW",
                "YYDRDF", 
                "QWERTY"};

       
        //static protected String installerKey = @"SOFTWARE\Microsoft\Windows\CurrentVersion\Installer\UserData\S-1-5-21-2731444950-3940108436-931277745-61379\Products\A70EF446F74978E4F9E5E7AA10CE912C\InstallProperties";
        static protected String installerKey = RegistryPath.Path;
        static protected String licenceKeyString = null;
        static protected DateTime installedDate;
        static protected bool isNewInstall = false;
        static protected String userName = null;
        
        static protected String newInstall;
        public Shareware()
        {
            
        }

        public bool Init()
        {
            if (InitReg() == false)
            {
                return false;
            }
            ReadRegister();
            return true;
        }

      
        public bool HasLicenceKey()
        {
            if (licenceKeyString == null)
            {
                return false;
            }
            return true;
        }

        public bool IsValidLicenceKey()
        {
            if (HasLicenceKey() == false)
            {
                return false;
            }
            String lic1 = licenceKeyString.Substring(0, 5);
            String lic2 = licenceKeyString.Substring(6, 6);
            return true;
        }

        public bool IsNewInstall { get { return isNewInstall; }}
        public DateTime InstalledDate { get { return installedDate; }}
        public String UserName
        {
            get { ReadRegister(); return userName; }
            set { userName = value; WriteUserName(); }
        }

        

        public bool Verify(String lic1, String lic2)
        {
            int licInt = 0;
            if (lic1.Length != 5)
            {
                return false;
            }
            if (int.TryParse(lic1,out licInt) == false)
            {
                return false;
            }
            if (licInt%3 != 0)
            {
                return false;
            }
           
            foreach (String i in validStringList)
            {
                if (i.Equals(lic2) == true)
                {
                    licenceKeyString = lic1 + "-" + lic2;
                    WriteLicence();
                    return true;
                }
            }
            return false;
        }

        void ReadRegister()
        {
            licenceKeyString = (String)regSubKey.GetValue("ProductID");
            String dateTicksStr = (String)regSubKey.GetValue("Key");
            if (dateTicksStr == null)
            {
                return;
            }
            if (dateTicksStr.Equals("NewInstall"))
            {
                isNewInstall = true;
                return;
            }
            if (dateTicksStr == null)
            {
                return;
            }
            long ticks = long.Parse(dateTicksStr);
            if (ticks == 0)
            {
                return;
            }
            installedDate = new DateTime(ticks);
            newInstall = (string)regSubKey.GetValue("Key");
            userName = (string)regSubKey.GetValue("UserName");
            return;
        }

        public void WriteLicence()
        {
            if (licenceKeyString != null)
            {
                regSubKey.SetValue("ProductID", licenceKeyString);
            }
        }

        public void WriteInstallDate()
        {
            installedDate = new DateTime();
            installedDate = DateTime.Now;
            regSubKey.SetValue("Key", installedDate.Ticks);
        }

        public void WriteUserName()
        {
            if (userName != null)
            {
                regSubKey.SetValue("UserName", userName);
            }
        }

        
    }
}
/*
[HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Installer\UserData\S-1-5-21-2731444950-3940108436-931277745-61379\Products\A70EF446F74978E4F9E5E7AA10CE912C\InstallProperties]
*/