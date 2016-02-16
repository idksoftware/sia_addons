<<<<<<< HEAD
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.IO;
using System.Xml;

namespace SIATray
{
    public partial class JounalContentsForm : Form
    {
        ImageList imageList1;
        String path = null;
        XmlDocument xmlDocument = null;
        
        public JounalContentsForm(String p)
        {
            path = p;
            InitializeComponent();

            xmlDocument = new XmlDocument();
            xmlDocument.Load(path + "\\jounal.xml");
           
        }

        public void ExtractAssociatedIconEx()
        {
            // Initialize the ListView, ImageList and Form. 
            imageList1 = new ImageList();
            
            listView1.SmallImageList = imageList1;
         

            imageListStatus.Images.Add(new Bitmap(GetType(), "NeedsBackup.bmp"));
            imageListStatus.Images.Add(new Bitmap(GetType(), "Backedup.bmp"));


            // Get the c:\ directory.
            System.IO.DirectoryInfo dir = new System.IO.DirectoryInfo(@"c:\");

            ListViewItem item;
            listView1.BeginUpdate();

            XmlNodeList nodeList = xmlDocument.GetElementsByTagName("Jounal");
            XmlNode node = nodeList.Item(0);
            if (node.HasChildNodes)
            {
                XmlNodeList fileList = node.ChildNodes;
                foreach (XmlNode fileItem in fileList)
                {
                    if (fileItem.Name == "File")
                    {
                        if (fileItem.HasChildNodes)
                        {
                            String path = null;
                            DateTime lastMod = new DateTime();


                            XmlNodeList fileItemList = fileItem.ChildNodes;
                            foreach (XmlNode fileContainsItem in fileItemList)
                            {
                                if (fileContainsItem.Name == "Path")
                                {
                                    path = fileContainsItem.InnerText;
                                }
                                if (fileContainsItem.Name == "LastWriteTime")
                                {
                                    lastMod = DateTime.Parse(fileContainsItem.InnerText);
                                }
                            }
                        
                            FileInfo file = new FileInfo(path);
                            // Set a default icon for the file.
                            Icon iconForFile = SystemIcons.WinLogo;

                            item = new ListViewItem(file.FullName, 1);
                            try
                            {
                                iconForFile = Icon.ExtractAssociatedIcon(file.FullName);
                            

                                // Check to see if the image collection contains an image
                                // for this extension, using the extension as a key.
                                if (!imageList1.Images.ContainsKey(file.Extension))
                                {
                                    // If not, add the image to the image list.
                                    iconForFile = System.Drawing.Icon.ExtractAssociatedIcon(file.FullName);
                                    imageList1.Images.Add(file.Extension, iconForFile);
                                }
                            }
                            catch (Exception ex)
                            {

                            }
                            item.ImageKey = file.Extension;
                            item.StateImageIndex = 0;

                            item.SubItems.Add(lastMod.ToString());
                            listView1.Items.Add(item);
                        }
                    }
               
                }
            }
        
            listView1.EndUpdate();
        }

        private void listView1_Click(object sender, EventArgs e)
        {
            ListView.SelectedListViewItemCollection list =
                                        this.listView1.SelectedItems;

            foreach (ListViewItem item in list)
            {
                item.StateImageIndex = 0;
            }
        }

        private void JounalContentsForm_Load(object sender, EventArgs e)
        {
            ExtractAssociatedIconEx();
        }

        private void JounalContentsForm_Activated(object sender, EventArgs e)
        {
            //ExtractAssociatedIconEx();
        }

        private void JounalContentsForm_DoubleClick(object sender, EventArgs e)
        {
            ListView.SelectedListViewItemCollection list =
                                        this.listView1.SelectedItems;
            foreach (ListViewItem item in list)
            {
                item.StateImageIndex = 1;
            }

        }

        private void listView1_DoubleClick(object sender, EventArgs e)
        {
            ListView.SelectedListViewItemCollection list =
                                        this.listView1.SelectedItems;

            foreach (ListViewItem item in list)
            {
                item.StateImageIndex = 1;
            }
        }

        private void restoreToolStripMenuItem_Click(object sender, EventArgs e)
        {

        }

    }


=======
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.IO;
using System.Xml;

namespace SIATray
{
    public partial class JounalContentsForm : Form
    {
        ImageList imageList1;
        String path = null;
        XmlDocument xmlDocument = null;
        
        public JounalContentsForm(String p)
        {
            path = p;
            InitializeComponent();

            xmlDocument = new XmlDocument();
            xmlDocument.Load(path + "\\jounal.xml");
           
        }

        public void ExtractAssociatedIconEx()
        {
            // Initialize the ListView, ImageList and Form. 
            imageList1 = new ImageList();
            
            listView1.SmallImageList = imageList1;
         

            imageListStatus.Images.Add(new Bitmap(GetType(), "NeedsBackup.bmp"));
            imageListStatus.Images.Add(new Bitmap(GetType(), "Backedup.bmp"));


            // Get the c:\ directory.
            System.IO.DirectoryInfo dir = new System.IO.DirectoryInfo(@"c:\");

            ListViewItem item;
            listView1.BeginUpdate();

            XmlNodeList nodeList = xmlDocument.GetElementsByTagName("Jounal");
            XmlNode node = nodeList.Item(0);
            if (node.HasChildNodes)
            {
                XmlNodeList fileList = node.ChildNodes;
                foreach (XmlNode fileItem in fileList)
                {
                    if (fileItem.Name == "File")
                    {
                        if (fileItem.HasChildNodes)
                        {
                            String path = null;
                            DateTime lastMod = new DateTime();


                            XmlNodeList fileItemList = fileItem.ChildNodes;
                            foreach (XmlNode fileContainsItem in fileItemList)
                            {
                                if (fileContainsItem.Name == "Path")
                                {
                                    path = fileContainsItem.InnerText;
                                }
                                if (fileContainsItem.Name == "LastWriteTime")
                                {
                                    lastMod = DateTime.Parse(fileContainsItem.InnerText);
                                }
                            }
                        
                            FileInfo file = new FileInfo(path);
                            // Set a default icon for the file.
                            Icon iconForFile = SystemIcons.WinLogo;

                            item = new ListViewItem(file.FullName, 1);
                            try
                            {
                                iconForFile = Icon.ExtractAssociatedIcon(file.FullName);
                            

                                // Check to see if the image collection contains an image
                                // for this extension, using the extension as a key.
                                if (!imageList1.Images.ContainsKey(file.Extension))
                                {
                                    // If not, add the image to the image list.
                                    iconForFile = System.Drawing.Icon.ExtractAssociatedIcon(file.FullName);
                                    imageList1.Images.Add(file.Extension, iconForFile);
                                }
                            }
                            catch (Exception ex)
                            {

                            }
                            item.ImageKey = file.Extension;
                            item.StateImageIndex = 0;

                            item.SubItems.Add(lastMod.ToString());
                            listView1.Items.Add(item);
                        }
                    }
               
                }
            }
        
            listView1.EndUpdate();
        }

        private void listView1_Click(object sender, EventArgs e)
        {
            ListView.SelectedListViewItemCollection list =
                                        this.listView1.SelectedItems;

            foreach (ListViewItem item in list)
            {
                item.StateImageIndex = 0;
            }
        }

        private void JounalContentsForm_Load(object sender, EventArgs e)
        {
            ExtractAssociatedIconEx();
        }

        private void JounalContentsForm_Activated(object sender, EventArgs e)
        {
            //ExtractAssociatedIconEx();
        }

        private void JounalContentsForm_DoubleClick(object sender, EventArgs e)
        {
            ListView.SelectedListViewItemCollection list =
                                        this.listView1.SelectedItems;
            foreach (ListViewItem item in list)
            {
                item.StateImageIndex = 1;
            }

        }

        private void listView1_DoubleClick(object sender, EventArgs e)
        {
            ListView.SelectedListViewItemCollection list =
                                        this.listView1.SelectedItems;

            foreach (ListViewItem item in list)
            {
                item.StateImageIndex = 1;
            }
        }

        private void restoreToolStripMenuItem_Click(object sender, EventArgs e)
        {

        }

    }


>>>>>>> 291c508aa47ea3e34a225d431ef34192e909c4ee
}