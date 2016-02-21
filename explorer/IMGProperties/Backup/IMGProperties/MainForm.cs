using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.IO;
using IWshRuntimeLibrary;
using System.Diagnostics;
using System.Xml;
using System.Xml.XPath;

namespace IMGProperties
{
    public partial class MainForm : Form
    {
        public XMLPropertiesReader xmlPropertiesReader = null;
        public ImageProperties imageProperties = null;
        public XmlDocument document = null;

        public MainForm(string filename)
        {
            InitializeComponent();
            FileInfo fileInfo = new FileInfo(filename);
            if (fileInfo.Exists == false)
            {
                MessageBox.Show("File \"" + fileInfo.Name + "\" not found", "ImgAchive Propertes");
                return;
            }    
            String ext = fileInfo.Extension;
            if (ext.Equals(".lnk"))
            {
                //FileStream fileStream = fileInfo.Open(FileMode.Open);
                //ileStream.
                WshShell shell = new WshShell();
                String path = fileInfo.ToString();
                IWshShortcut link = (IWshShortcut)shell.CreateShortcut(path);
               
                FileInfo doesItExist = new FileInfo(link.TargetPath);
                if (doesItExist.Exists == false)
                {
                    MessageBox.Show("File \"" + fileInfo.Name + "\" not found", "ImgAchive Propertes");
                    return;
                }    
                xmlPropertiesReader = new XMLPropertiesReader(link.TargetPath);
                xmlPropertiesReader.Process();
                imageProperties = xmlPropertiesReader.ImageProperties;
                document = xmlPropertiesReader.XMLDocument;
               
            }
            else if (ext.Equals(".xml"))
            {
                XMLPropertiesReader xmlPropertiesReader = new XMLPropertiesReader(fileInfo.FullName);
                xmlPropertiesReader.Process();
                imageProperties = xmlPropertiesReader.ImageProperties;
                document = xmlPropertiesReader.XMLDocument;
            }
            else
            {
                return;
            }
            
        }

        private void MainForm_Load(object sender, EventArgs e)
        {
            ExifContainer exifContainer = new ExifContainer();
            exifPropertyGrid.SelectedObject = exifContainer;

            LoadTagList();
            LoadKeywordList();

            labelNumber.Text = imageProperties.number;
            labelRevision.Text = imageProperties.revision;
        // Jounal
            //labelTimesBackedUp.Text = imageProperties.timesBackedUp;
            //labelInPrimaryStorage.Text = imageProperties.inPrimaryStorage;
            //labelCheckedStatus.Text = imageProperties.checkedStatus;
        // File
            labelOriginalFileName.Text = imageProperties.originalFile;
            labelLastModified.Text = imageProperties.lastModified;
            labelSize.Text = imageProperties.size;
            labelCRC.Text = imageProperties.crc;
            labelMD5.Text = imageProperties.md5;
            labelArchiveName.Text = imageProperties.archiveName;
            labelUUID.Text = imageProperties.uUID;
             
        // Details
            labelTitle.Text = imageProperties.title;

            labelSubject.Text = imageProperties.subject;
            labelImageDate.Text = imageProperties.imageDate;
            labelLatestRevision.Text = imageProperties.latestRevision;
            labelTitle.Text = imageProperties.author;
            labelHardcopyLocation.Text = imageProperties.hardCopyLocation;
            labelComments.Text = imageProperties.comments;
            labelEditor.Text = imageProperties.editor;
            labelLanguage.Text = imageProperties.language;
            //labelTitle.Text = imageProperties.keywords;
            labeType.Text = imageProperties.type;
            //labelTitle.Text = imageProperties.tags;
            labelCategory.Text = imageProperties.category;
        // Camera Details
            labelCamera.Text = imageProperties.camera;
            labelMetering.Text = imageProperties.metering;
            labelShutter.Text = imageProperties.shutter;
            labelAperture.Text = imageProperties.aperture;
            labelExposureMode.Text = imageProperties.modeExp;
            labelFocalLength.Text = imageProperties.focalLength;
            labelFlashMode.Text = imageProperties.flashMode;
             
        }

        private void buttonTagChange_Click(object sender, EventArgs e)
        {
            XmlDocument newDocument = (XmlDocument)document.Clone();
            ChangeTagForm form = new ChangeTagForm(newDocument);
            form.ShowDialog();
            if (form.DialogResult == DialogResult.OK)
            {
                document = newDocument;
                tagListBox.Items.Clear();
                LoadTagList();
            }
            else
            {
                return;
            }
        }

        private void buttonKeywordsChange_Click(object sender, EventArgs e)
        {
            XmlDocument newDocument = (XmlDocument)document.Clone();
            ChangeKeywordForm form = new ChangeKeywordForm(newDocument);
            form.ShowDialog();
            if (form.DialogResult == DialogResult.OK)
            {
                document = newDocument;
                keywordListBox.Items.Clear();
                LoadKeywordList();
            }
            else
            {
                return;
            }
        }

            

        private void LoadTagList()
        {
            XmlNodeList nodeList = document.GetElementsByTagName("Tags");
            for (int i = 0; i < nodeList.Count; ++i)
            {
                //Debug.Write(nodeList.Item(i).Name);
                XmlNode node = nodeList.Item(i);
                if (node.HasChildNodes)
                {
                    XmlNodeList childNodeList = node.ChildNodes;
                    for (int j = 0; j < childNodeList.Count; j++)
                    {
                        XmlNode childNode = childNodeList.Item(j);
                        tagListBox.Items.Add(childNode.InnerText);
                    }
                }

            }
        }

        private void LoadKeywordList()
        {
            XmlNodeList nodeList = document.GetElementsByTagName("Keywords");
            for (int i = 0; i < nodeList.Count; ++i)
            {
                //Debug.Write(nodeList.Item(i).Name);
                XmlNode node = nodeList.Item(i);
                if (node.HasChildNodes)
                {
                    XmlNodeList childNodeList = node.ChildNodes;
                    for (int j = 0; j < childNodeList.Count; j++)
                    {
                        XmlNode childNode = childNodeList.Item(j);
                        keywordListBox.Items.Add(childNode.InnerText);
                    }
                }

            }
        }

        private void button5_Click(object sender, EventArgs e)
        {

        }

        private void buttonChangeDetails_Click(object sender, EventArgs e)
        {
            XmlDocument newDocument = (XmlDocument)document.Clone();
            ChangeDetailsForm form = new ChangeDetailsForm(newDocument);
            form.ShowDialog();
            if (form.DialogResult == DialogResult.OK)
            {
                document = newDocument;
                labelImageDate.Text = form.ImageDate;
                labelImageDate.Text = form.Author;
                labelLatestRevision.Text = form.LatestRevision;
                labelHardcopyLocation.Text = form.HardCopyLocation;
                labelCategory.Text = form.Category;
                labelComments.Text = form.Comments;
                labelEditor.Text = form.Editor;
                labelLanguage.Text = form.Language;
                labeType.Text = form.Type;
                
                //LoadKeywordList();
            }
            else
            {
                return;
            }
        }

        private void buttonChangeTitle_Click(object sender, EventArgs e)
        {
            XmlDocument newDocument = (XmlDocument)document.Clone();
            ChangeTitleForm form = new ChangeTitleForm(newDocument);
            form.ShowDialog();
            if (form.DialogResult == DialogResult.OK)
            {
                document = newDocument;
                labelTitle.Text = form.Title;
                labelSubject.Text = form.Subject;
            }
            else
            {
                return;
            }
        }

        private void ChangeButton_Click(object sender, EventArgs e)
        {
            XmlDocument newDocument = (XmlDocument)document.Clone();
            CameraChangeForm form = new CameraChangeForm(newDocument);
            form.ShowDialog();
            if (form.DialogResult == DialogResult.OK)
            {
                document = newDocument;
            }
            else
            {
                return;
            }
        }
    }
}