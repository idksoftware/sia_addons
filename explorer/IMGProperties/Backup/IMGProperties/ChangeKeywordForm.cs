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
    public partial class ChangeKeywordForm : Form
    {
        private XmlDocument document = null;

        public ChangeKeywordForm(XmlDocument doc)
        {
            InitializeComponent();
            document = doc;
        }

        private void Form_Load(object sender, EventArgs e)
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
            if (nodeList.Count != 0)
            {
                keywordListBox.SetSelected(0, true);
            }
        }

        private void buttonAdd_Click(object sender, EventArgs e)
        {
            KeywordForm form = new KeywordForm(null);
            DialogResult result = form.ShowDialog();

            if (result == DialogResult.Cancel)
            {
                return;
            }

            XmlNodeList nodeList = document.GetElementsByTagName("Keywords");
            
            for (int i = 0; i < nodeList.Count; ++i)
            {

                XmlNode node = nodeList.Item(i);
                XmlElement element = document.CreateElement("Keyword");
                element.InnerText = form.keyword;
                node.AppendChild(element);
            }
            keywordListBox.Items.Add(form.keyword);
 //           document.Save("c:\\test1.xml");
        }

        private void buttonEdit_Click(object sender, EventArgs e)
        {
            
            //String itemStr = tagListBox.Items.
            //tagListBox.SelectedIndex
            String itemStr = keywordListBox.SelectedItem.ToString();
            KeywordForm form = new KeywordForm(itemStr);
            DialogResult result = form.ShowDialog();

            if (result == DialogResult.Cancel)
            {
                return;
            }
            String newStr = form.keyword;
            if (newStr == null)
            {
                return;
            }
            XmlNodeList nodeList = document.GetElementsByTagName("Keywords");
            XmlNode childNode = null;
            XmlNode node = null;
            for (int i = 0; i < nodeList.Count; ++i)
            {
                node = nodeList.Item(i);
                XmlNodeList childNodeList = node.ChildNodes;
                for (int j = 0; j < childNodeList.Count; j++)
                {
                    childNode = childNodeList.Item(j);
                    if (childNode.InnerText.Equals(itemStr))
                    {
                        childNode.InnerText = form.keyword;
                        break;
                    }
                }

                XmlElement element = document.CreateElement("Keyword");
                element.InnerText = newStr;
                node.ReplaceChild(element, childNode);
            }
            keywordListBox.Items.RemoveAt(keywordListBox.SelectedIndex);
            keywordListBox.Items.Add(newStr);
            
            document.Save("c:\\test1.xml");
        }

        private void buttonDelete_Click(object sender, EventArgs e)
        {

            String itemStr = keywordListBox.SelectedItem.ToString();

            XmlNodeList nodeList = document.GetElementsByTagName("Keywords");
            XmlNode childNode = null;
            XmlNode node = null;
            for (int i = 0; i < nodeList.Count; ++i)
            {
                node = nodeList.Item(i);
                XmlNodeList childNodeList = node.ChildNodes;
                for (int j = 0; j < childNodeList.Count; j++)
                {
                    childNode = childNodeList.Item(j);
                    if (childNode.InnerText.Equals(itemStr))
                    {
                        node.RemoveChild(childNode);
                        break;
                    }
                }    
            }
            keywordListBox.Items.RemoveAt(keywordListBox.SelectedIndex);
            
            document.Save("c:\\test1.xml");
        }
    }
}