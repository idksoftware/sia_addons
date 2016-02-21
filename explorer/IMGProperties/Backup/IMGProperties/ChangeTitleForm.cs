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
    public partial class ChangeTitleForm : Form
    {
        private XmlDocument document = null;
        private bool IsChanged = false;

        public ChangeTitleForm(XmlDocument doc)
        {
            InitializeComponent();
            document = doc;
            LoadTitleAndSubject();
            IsChanged = false;  // Note loading the text into the control will trigger
                                // textBoxXXX_TextChanged function
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
                        if (childNode.Name == "Title")
                        {
                            XmlElement element = document.CreateElement("Title");
                            element.InnerText = textBoxTitle.Text;
                            node.ReplaceChild(element, childNode);
                            continue;
                        }
                        if (childNode.Name == "Subject")
                        {
                            XmlElement element = document.CreateElement("Subject");
                            element.InnerText = textBoxSubject.Text;
                            node.ReplaceChild(element, childNode);
                            continue;
                        }
                    }
                }
            }
            document.Save("c:\\test1.xml");
        }

        private void LoadTitleAndSubject()
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
                        if (childNode.Name == "Title")
                        {
                            textBoxTitle.Text = childNode.InnerText;
                            continue;
                        }
                        if (childNode.Name == "Subject")
                        {
                            textBoxSubject.Text = childNode.InnerText;
                            continue;
                        }
                    }
                }

            }
        }

        private void textBoxTitle_TextChanged(object sender, EventArgs e)
        {
            IsChanged = true;
        }

        private void textBoxSubject_TextChanged(object sender, EventArgs e)
        {
            IsChanged = true;
        }
    }


}