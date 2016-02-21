using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.Xml;

namespace IMGProperties
{
    public partial class ChangeDetailsForm : Form
    {
        private XmlDocument document = null;
        bool IsChanged = true;
        public ChangeDetailsForm(XmlDocument doc)
        {
            InitializeComponent();
            document = doc;
            LoadDetails();
        }

        private void buttonOk_Click(object sender, EventArgs e)
        {
            if (IsChanged != true)
            {
                return;
            }
            XmlNodeList nodeList = document.GetElementsByTagName("Details");
            for (int i = 0; i < nodeList.Count; ++i)
            {
                XmlNode node = nodeList.Item(i);
                if (node.HasChildNodes)
                {
                    XmlNodeList childNodeList = node.ChildNodes;
                    for (int j = 0; j < childNodeList.Count; j++)
                    {
                        XmlNode childNode = childNodeList.Item(j);
                        if (childNode.Name == "ImageDate")
                        {
                            XmlElement element = document.CreateElement("ImageDate");
                            element.InnerText = textBoxImageDate.Text;
                            node.ReplaceChild(element, childNode);
                            continue;
                        }
                        if (childNode.Name == "LatestRevision")
                        {
                            XmlElement element = document.CreateElement("LatestRevision");
                            element.InnerText = this.textBoxLatestRevision.Text;
                            node.ReplaceChild(element, childNode);
                            continue;
                        }
                        if (childNode.Name == "Author")
                        {
                            XmlElement element = document.CreateElement("Author");
                            element.InnerText = this.textBoxAuthor.Text;
                            node.ReplaceChild(element, childNode);
                            continue;
                            
                        }
                        if (childNode.Name == "HardCopyLocation")
                        {
                            XmlElement element = document.CreateElement("HardCopyLocation");
                            element.InnerText = this.textBoxHardCopyLocation.Text;
                            node.ReplaceChild(element, childNode);
                            continue;
                            
                        }
                        if (childNode.Name == "Comments")
                        {
                            XmlElement element = document.CreateElement("Comments");
                            element.InnerText = this.textBoxComments.Text;
                            node.ReplaceChild(element, childNode);
                            continue;
                            
                        }
                        if (childNode.Name == "Editor")
                        {
                            XmlElement element = document.CreateElement("Editor");
                            element.InnerText = this.textBoxeditor.Text;
                            node.ReplaceChild(element, childNode);
                            continue;
                      
                        }
                        if (childNode.Name == "Language")
                        {
                            XmlElement element = document.CreateElement("Language");
                            element.InnerText = this.textBoxLanguage.Text;
                            node.ReplaceChild(element, childNode);
                            continue;
                            
                        }
                        if (childNode.Name == "Category")
                        {
                            XmlElement element = document.CreateElement("Category");
                            element.InnerText = this.textBoxCategory.Text;
                            node.ReplaceChild(element, childNode);
                            continue;
                        
                        }
                        if (childNode.Name == "Type")
                        {
                            XmlElement element = document.CreateElement("Type");
                            element.InnerText = this.textBoxType.Text;
                            node.ReplaceChild(element, childNode);
                            continue;
                          
                        }
                       
                    }
                }
            }
            //document.Save("c:\\test1.xml");
        }
        private void LoadDetails()
        {
            XmlNodeList nodeList = document.GetElementsByTagName("Details");
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
                        if (childNode.Name == "ImageDate")
                        {
                            this.textBoxImageDate.Text = childNode.InnerText;
                            continue;
                        }
                        if (childNode.Name == "LatestRevision")
                        {
                            this.textBoxLatestRevision.Text = childNode.InnerText;
                            continue;
                        }
                        if (childNode.Name == "Author")
                        {
                            this.textBoxAuthor.Text = childNode.InnerText;
                            continue;
                        }
                        if (childNode.Name == "HardCopyLocation")
                        {
                            this.textBoxHardCopyLocation.Text = childNode.InnerText;
                            continue;
                        }
                         if (childNode.Name == "Comments")
                        {
                            this.textBoxComments.Text = childNode.InnerText;
                            continue;
                        }
                        if (childNode.Name == "Editor")
                        {
                            this.textBoxeditor.Text = childNode.InnerText;
                            continue;
                        }
                         if (childNode.Name == "Language")
                        {
                            this.textBoxLanguage.Text = childNode.InnerText;
                            continue;
                        }
                        if (childNode.Name == "Category")
                        {
                            this.textBoxCategory.Text = childNode.InnerText;
                            continue;
                        }
                        if (childNode.Name == "Type")
                        {
                            this.textBoxType.Text = childNode.InnerText;
                            continue;
                        }
                    }
                }

            }
        }

        private void textBoxImageDate_TextChanged(object sender, EventArgs e)
        {
            IsChanged = true;
        }

        private void textBoxAuthor_TextChanged(object sender, EventArgs e)
        {
            IsChanged = true;
        }

        private void textBoxLatestRevision_TextChanged(object sender, EventArgs e)
        {
            IsChanged = true;
        }

        private void textBoxHardCopyLocation_TextChanged(object sender, EventArgs e)
        {
            IsChanged = true;
        }

        private void textBoxCategory_TextChanged(object sender, EventArgs e)
        {
            IsChanged = true;
        }

        private void textBoxComments_TextChanged(object sender, EventArgs e)
        {
            IsChanged = true;
        }

        private void textBoxeditor_TextChanged(object sender, EventArgs e)
        {
            IsChanged = true;
        }

        private void textBoxLanguage_TextChanged(object sender, EventArgs e)
        {
            IsChanged = true;
        }

        private void textBoxType_TextChanged(object sender, EventArgs e)
        {
            IsChanged = true;
        }
    }
}