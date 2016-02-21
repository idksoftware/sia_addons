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
    public partial class ChangeTagForm : Form
    {
        private XmlDocument document = null;

        public ChangeTagForm(XmlDocument doc)
        {
            InitializeComponent();
            document = doc;
        }

        private void Form_Load(object sender, EventArgs e)
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
            if (nodeList.Count != 0)
            {
                tagListBox.SetSelected(0,true);
            }
        }

        private void buttonAdd_Click(object sender, EventArgs e)
        {
            TagForm form = new TagForm(null);
            DialogResult result = form.ShowDialog();

            if (result == DialogResult.Cancel)
            {
                return;
            }
            
            XmlNodeList nodeList = document.GetElementsByTagName("Tags");
            
            for (int i = 0; i < nodeList.Count; ++i)
            {

                XmlNode node = nodeList.Item(i);
                XmlElement element = document.CreateElement("Tag");
                element.InnerText = form.tag;
                node.AppendChild(element);
            }
            tagListBox.Items.Add(form.tag);
 //           document.Save("c:\\test1.xml");
        }

        private void buttonEdit_Click(object sender, EventArgs e)
        {
            
            //String itemStr = tagListBox.Items.
            //tagListBox.SelectedIndex
            String itemStr = tagListBox.SelectedItem.ToString();
            TagForm form = new TagForm(itemStr);
            DialogResult result = form.ShowDialog();

            if (result == DialogResult.Cancel)
            {
                return;
            }
            String newStr = form.tag;
            if (newStr == null)
            {
                return;
            }
            XmlNodeList nodeList = document.GetElementsByTagName("Tags");
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
                        childNode.InnerText = form.tag;
                        break;
                    }
                }
               
                XmlElement element = document.CreateElement("Tag");
                element.InnerText = newStr;
                node.ReplaceChild(element, childNode);
            }
            tagListBox.Items.RemoveAt(tagListBox.SelectedIndex);
            tagListBox.Items.Add(newStr);
            
            document.Save("c:\\test1.xml");
        }

        private void buttonDelete_Click(object sender, EventArgs e)
        {
           
            String itemStr = tagListBox.SelectedItem.ToString();
          
            XmlNodeList nodeList = document.GetElementsByTagName("Tags");
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
            tagListBox.Items.RemoveAt(tagListBox.SelectedIndex);
            
            document.Save("c:\\test1.xml");
        }
    }
}