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
    public partial class CameraChangeForm : Form
    {

        private XmlDocument document = null;
        bool IsChanged = true;
        public CameraChangeForm(XmlDocument doc)
        {
            InitializeComponent();
            document = doc;
            LoadDetails();
        }


        private void LoadDetails()
        {
            XmlNodeList nodeList = document.GetElementsByTagName("CameraDetails");
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
                        if (childNode.Name == "Camera")
                        {
                            this.textBoxCamera.Text = childNode.InnerText;
                            continue;
                        }
                        if (childNode.Name == "Metering")
                        {
                            this.textBoxMetering.Text = childNode.InnerText;
                            continue;
                        }
                        if (childNode.Name == "Shutter")
                        {
                            this.textBoxShutter.Text = childNode.InnerText;
                            continue;
                        }
                        if (childNode.Name == "Aperture")
                        {
                            this.textBoxAperture.Text = childNode.InnerText;
                            continue;
                        }
                        if (childNode.Name == "ModeExp")
                        {
                            this.textBoxExposureMode.Text = childNode.InnerText;
                            continue;
                        }
                        if (childNode.Name == "FocalLength")
                        {
                            this.textBoxFocalLength.Text = childNode.InnerText;
                            continue;
                        }
                        if (childNode.Name == "FlashMode")
                        {
                            this.textBoxFlashMode.Text = childNode.InnerText;
                            continue;
                        }
                    }
                }

            }
        }

        private void buttonOk_Click(object sender, EventArgs e)
        {
            if (IsChanged != true)
            {
                return;
            }
            XmlNodeList nodeList = document.GetElementsByTagName("CameraDetails");
            for (int i = 0; i < nodeList.Count; ++i)
            {
                XmlNode node = nodeList.Item(i);
                if (node.HasChildNodes)
                {
                    XmlNodeList childNodeList = node.ChildNodes;
                    for (int j = 0; j < childNodeList.Count; j++)
                    {
                        XmlNode childNode = childNodeList.Item(j);
                        if (childNode.Name == "Camera")
                        {
                            XmlElement element = document.CreateElement("Camera");
                            element.InnerText = textBoxCamera.Text;
                            node.ReplaceChild(element, childNode);
                            continue;
                        }
                        if (childNode.Name == "Metering")
                        {
                            XmlElement element = document.CreateElement("Metering");
                            element.InnerText = this.textBoxMetering.Text;
                            node.ReplaceChild(element, childNode);
                            continue;
                        }
                        if (childNode.Name == "Shutter")
                        {
                            XmlElement element = document.CreateElement("Shutter");
                            element.InnerText = this.textBoxShutter.Text;
                            node.ReplaceChild(element, childNode);
                            continue;

                        }
                        if (childNode.Name == "Aperture")
                        {
                            XmlElement element = document.CreateElement("Aperture");
                            element.InnerText = this.textBoxAperture.Text;
                            node.ReplaceChild(element, childNode);
                            continue;

                        }
                        if (childNode.Name == "FocalLength")
                        {
                            XmlElement element = document.CreateElement("FocalLength");
                            element.InnerText = this.textBoxFocalLength.Text;
                            node.ReplaceChild(element, childNode);
                            continue;

                        }
                        if (childNode.Name == "FlashMode")
                        {
                            XmlElement element = document.CreateElement("FlashMode");
                            element.InnerText = this.textBoxFlashMode.Text;
                            node.ReplaceChild(element, childNode);
                            continue;

                        }
                    }
                }
            }
            //document.Save("c:\\test1.xml");
        }
    }
}