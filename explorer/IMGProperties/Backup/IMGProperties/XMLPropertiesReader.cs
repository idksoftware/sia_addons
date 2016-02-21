using System;
using System.Collections.Generic;
using System.Text;
using System.Xml;
using System.Xml.XPath;
using System.Diagnostics;
 
namespace IMGProperties
{
    

    class NameValue
    {
        public String name;
        public String value;
    }

    public class ImageProperties
    {
        // Identity
        public String number;
        public String revision;
        // Jounal
        public String timesBackedUp;
        public String inPrimaryStorage;
        public String checkedStatus;
        // File
        public String originalFile;
        public String lastModified; 
        public String size;
        public String crc;
        public String md5; 
        public String archiveName;
        public String uUID;
        // Details
        public String title;
        public String subject; 
        public String imageDate; 
        public String latestRevision; 
        public String author;
        public String hardCopyLocation; 
        public String comments;
        public String editor;
        public String language; 
        public String keywords; 
        public String type;
        public String tags;
        public String category;
        // Camera Details
        public String camera; 
        public String metering; 
        public String shutter;
        public String aperture; 
        public String modeExp;
        public String focalLength;
        public String flashMode;
    }
    /*
<Document>
- <Identity>
  <Number>1</Number> 
  <Revision>1</Revision> 
  </Identity>
- <Jounal>
  <TimesBackedUp>0</TimesBackedUp> 
  <InPrimaryStorage>Yes</InPrimaryStorage> 
  <CheckedStatus>No</CheckedStatus> 
  </Jounal>
- <File>
  <OriginalFile>DSC_3140.NEF</OriginalFile> 
  <LastModified>04/13/2008 11:26:48</LastModified> 
  <Size>9940212</Size> 
  <Crc>2442639641</Crc> 
  <Md5>6ff15b8e6bf3e33332f1715cdf155d17</Md5> 
  <ArchiveName>IMG000000A7.NEF</ArchiveName> 
  <UUID>067e6162-3b6f-4ae2-a171-2470b63dff00</UUID> 
  </File>
- <Details>
  <Title>Climbing in Wye Valley</Title> 
  <Subject /> 
  <ImageDate>Unknown</ImageDate> 
  <LatestRevision /> 
  <Author>Iain Ferguson</Author> 
  <HardCopyLocation>Cabinet-11/789</HardCopyLocation> 
  <Comments>None</Comments> 
  <Editor>Nome</Editor> 
  <Language>English</Language> 
  <Keywords>Climbing,Wye Valley,Landscape</Keywords> 
  <Type>None</Type> 
  <Tags /> 
  <Category /> 
  </Details>
- <CameraDetails>
  <Camera>NIKON D80</Camera> 
  <Metering>Center weighted average</Metering> 
  <Shutter>1/160 sec</Shutter> 
  <Aperture>F7.1</Aperture> 
  <ModeExp>Auto exposure</ModeExp> 
  <FocalLength>80.0 mm</FocalLength> 
  <FlashMode>Flash</FlashMode> 
  </CameraDetails>
- <Exif>


     */
    public class XMLPropertiesReader
    {

        ImageProperties imageProperties = new ImageProperties();

        XmlTextReader reader = null;
        XmlDocument document = new XmlDocument();

        List<NameValue> propertiesList = null;
        NameValue currentNameValue = null;
        public XMLPropertiesReader(string fileName)
        {
            reader = new XmlTextReader(fileName);
            document.Load(fileName);
            propertiesList = new List<NameValue>();
        }
        
        public void Process()
        {
            reader.Read();
            document.Load(reader);

            XmlNodeList nodeList = document.GetElementsByTagName("Identity");
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
                        if (childNode.Name == "Revision")
                        {
                            imageProperties.revision = childNode.InnerText;
                        }
                        if (childNode.Name == "Number")
                        {
                            imageProperties.number = childNode.InnerText;
                        }
                        
                    }
                }
                //Debug.Write(nodeList.Item(i).);
            }
            nodeList = document.GetElementsByTagName("Jounal");
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
                        if (childNode.Name == "TimesBackedUp")
                        {
                            imageProperties.timesBackedUp = childNode.InnerText;
                        }
                        if (childNode.Name == "InPrimaryStorage")
                        {
                            imageProperties.inPrimaryStorage = childNode.InnerText;
                        }
                        if (childNode.Name == "CheckedStatus")
                        {
                            imageProperties.checkedStatus = childNode.InnerText;
                        }

                    }
                }
                //Debug.Write(nodeList.Item(i).);
            }
            nodeList = document.GetElementsByTagName("File");
            for (int i = 0; i < nodeList.Count; ++i)
            {
                XmlNode node = nodeList.Item(i);
                if (node.HasChildNodes)
                {
                    XmlNodeList childNodeList = node.ChildNodes;
                    for (int j = 0; j < childNodeList.Count; j++)
                    {

                        XmlNode childNode = childNodeList.Item(j);
                        if (childNode.Name == "OriginalFile")
                        {
                            imageProperties.originalFile = childNode.InnerText;
                        }
                        if (childNode.Name == "LastModified")
                        {
                            imageProperties.lastModified = childNode.InnerText;
                        }
                        if (childNode.Name == "Size")
                        {
                            imageProperties.size = childNode.InnerText;
                        }
                        if (childNode.Name == "Crc")
                        {
                            imageProperties.crc = childNode.InnerText;
                        }
                        if (childNode.Name == "Md5")
                        {
                            imageProperties.md5 = childNode.InnerText;
                        }
                        if (childNode.Name == "ArchiveName")
                        {
                            imageProperties.archiveName = childNode.InnerText;
                        }
                        if (childNode.Name == "UUID")
                        {
                            imageProperties.uUID = childNode.InnerText;
                        }
                    }
                }
            }
            nodeList = document.GetElementsByTagName("Details");
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
                            imageProperties.title = childNode.InnerText;
                        }
                        if (childNode.Name == "Subject")
                        {
                            imageProperties.subject = childNode.InnerText;
                        }
                        if (childNode.Name == "LatestRevision")
                        {
                            imageProperties.latestRevision = childNode.InnerText;
                        }
                        if (childNode.Name == "Author")
                        {
                            imageProperties.author = childNode.InnerText;
                        }
                        if (childNode.Name == "HardCopyLocation")
                        {
                            imageProperties.hardCopyLocation = childNode.InnerText;
                        }
                        if (childNode.Name == "Comments")
                        {
                            imageProperties.comments = childNode.InnerText;
                        }
                        if (childNode.Name == "Editor")
                        {
                            imageProperties.editor = childNode.InnerText;
                        }
                           if (childNode.Name == "Language")
                        {
                            imageProperties.language = childNode.InnerText;
                        }
                        if (childNode.Name == "Keywords")
                        {
                            imageProperties.keywords = childNode.InnerText;
                        }
                        if (childNode.Name == "Type")
                        {
                            imageProperties.type = childNode.InnerText;
                        }
                        if (childNode.Name == "Tags")
                        {
                            imageProperties.tags = childNode.InnerText;
                        }
                        if (childNode.Name == "Category")
                        {
                            imageProperties.category = childNode.InnerText;
                        }
                    }
                }
            }
            nodeList = document.GetElementsByTagName("CameraDetails");
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
                            imageProperties.camera = childNode.InnerText;
                        }
                        if (childNode.Name == "Metering")
                        {
                            imageProperties.metering = childNode.InnerText;
                        }
                        if (childNode.Name == "Shutter")
                        {
                            imageProperties.shutter = childNode.InnerText;
                        }
                        if (childNode.Name == "Aperture")
                        {
                            imageProperties.aperture = childNode.InnerText;
                        }
                        if (childNode.Name == "ModeExp")
                        {
                            imageProperties.modeExp = childNode.InnerText;
                        }
                        if (childNode.Name == "FocalLength")
                        {
                            imageProperties.focalLength = childNode.InnerText;
                        }
                        if (childNode.Name == "FlashMode")
                        {
                            imageProperties.flashMode = childNode.InnerText;
                        }
                    }
                }
                //Debug.Write(nodeList.Item(i).);
            } 
        }
        public XmlDocument XMLDocument { get { return document; } }
        public ImageProperties ImageProperties { get { return imageProperties; } }
    }
}
