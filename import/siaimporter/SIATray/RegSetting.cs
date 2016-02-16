<<<<<<< HEAD
using System;
using System.Collections.Generic;
using System.Text;
using IDK.Gui;

namespace SIATray
{
    class RegSetting : RegInitalise
    {
        String libraryLocationPath = null;

        public void ReadRegister()
        {
            isSetup = false;
            if (InitReg() == true)
            {
                libraryLocationPath = (String)regSubKey.GetValue("LibraryLocationPath");
                isInstalled = true;
                if (libraryLocationPath == null)
                {
                    return;
                }
                isSetup = true;
                
            }   
        }

        public void WriteRegistory()
        {
            if (libraryLocationPath != null)
            {
                regSubKey.SetValue("LibraryLocationPath", libraryLocationPath);
            }
        }

       

        public String LibraryLocationPath
        {
            get { return libraryLocationPath; }
            set { libraryLocationPath = value; }
        }
    }
}
=======
using System;
using System.Collections.Generic;
using System.Text;
using IDK.Gui;

namespace SIATray
{
    class RegSetting : RegInitalise
    {
        String libraryLocationPath = null;

        public void ReadRegister()
        {
            isSetup = false;
            if (InitReg() == true)
            {
                libraryLocationPath = (String)regSubKey.GetValue("LibraryLocationPath");
                isInstalled = true;
                if (libraryLocationPath == null)
                {
                    return;
                }
                isSetup = true;
                
            }   
        }

        public void WriteRegistory()
        {
            if (libraryLocationPath != null)
            {
                regSubKey.SetValue("LibraryLocationPath", libraryLocationPath);
            }
        }

       

        public String LibraryLocationPath
        {
            get { return libraryLocationPath; }
            set { libraryLocationPath = value; }
        }
    }
}
>>>>>>> 291c508aa47ea3e34a225d431ef34192e909c4ee
