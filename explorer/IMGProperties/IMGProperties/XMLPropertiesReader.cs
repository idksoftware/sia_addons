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
        // AssetProperties
        //    public String revision;
        public String number;
        public String filename;
        public String originalFile;
        public String uUID;
        public String label;
        public String rating;
        public String mediaType;
        public String md5;
        public String crc;
        public String size;
        public String dateCreate;
        public String lastModified;
        public String dateAdded;
        // Jounal
        public String timesBackedUp;
        public String inPrimaryStorage;
        public String checkedStatus;
        public String lastModifiedVersion;
        // MediaProerties
        public String width;
        public String height;
        public String resolution;
        public String depth;
        public String viewRotation;
        public String sampleColor;
        public String colorSpace;
        public String compression;
        public String primaryEncoding;
        // CameraInformation
        public String maker;
        public String model;
        public String software;
        public String sourceURL;
        public String exifVersion;
        public String captureDate;
        public String exposureProgram;
        public String isoSpeedRating;
        public String exposureBias;
        public String exposureTime;
        public String aperture;
        public String meteringMode;
        public String lightSource;
        public String flash;
        public String focalLength;
        public String sensingMethod;
        public String digitalZoom;
        // GPS
        public String latitude;
        public String longitude;
        public String gpsTimeStamp;
        // CopyrightProperties
        public String copyright;
        public String usageRights;
        public String copyrightURL;
    }
    
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

            XmlNodeList nodeList = document.GetElementsByTagName("AssetProperties");
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
                        //if (childNode.Name == "Revision")
                        //{
                        //    imageProperties.revision = childNode.InnerText;
                        //}
                        if (childNode.Name == "SequenceId")
                        {
                            imageProperties.number = childNode.InnerText;
                        }
                        if (childNode.Name == "Filename")
                        {
                            imageProperties.filename = childNode.InnerText;
                        }
                        if (childNode.Name == "OrginalName")
                        {
                            imageProperties.originalFile = childNode.InnerText;
                        }
                        if (childNode.Name == "UniqueId")
                        {
                            imageProperties.uUID = childNode.InnerText;
                        }
                        if (childNode.Name == "Label")
                        {
                            imageProperties.label = childNode.InnerText;
                        }
                        if (childNode.Name == "Rating")
                        {
                            imageProperties.rating = childNode.InnerText;
                        }
                        if (childNode.Name == "MediaType")
                        {
                            imageProperties.mediaType = childNode.InnerText;
                        }
                        if (childNode.Name == "Md5")
                        {
                            imageProperties.md5 = childNode.InnerText;
                        }
                        if (childNode.Name == "Crc")
                        {
                            imageProperties.crc = childNode.InnerText;
                        }
                        if (childNode.Name == "Size")
                        {
                            imageProperties.size = childNode.InnerText;
                        }
                        if (childNode.Name == "DateCreate")
                        {
                            imageProperties.dateCreate = childNode.InnerText;
                        }
                        if (childNode.Name == "LastModified")
                        {
                            imageProperties.lastModified = childNode.InnerText;
                        }
                        if (childNode.Name == "DateAdded")
                        {
                            imageProperties.dateAdded = childNode.InnerText;
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
                        
                        if (childNode.Name == "LastModified")
                        {
                            imageProperties.lastModified = childNode.InnerText;
                        }
                       
                    }
                }
                //Debug.Write(nodeList.Item(i).);
            }
            nodeList = document.GetElementsByTagName("MediaProerties");
            for (int i = 0; i < nodeList.Count; ++i)
            {
                XmlNode node = nodeList.Item(i);
                if (node.HasChildNodes)
                {
                    XmlNodeList childNodeList = node.ChildNodes;
                    for (int j = 0; j < childNodeList.Count; j++)
                    {
                        XmlNode childNode = childNodeList.Item(j);
                        if (childNode.Name == "Width")
                        {
                            imageProperties.width = childNode.InnerText;
                        }
                        if (childNode.Name == "Height")
                        {
                            imageProperties.height = childNode.InnerText;
                        }
                        if (childNode.Name == "Resolution")
                        {
                            imageProperties.resolution = childNode.InnerText;
                        }
                        if (childNode.Name == "Depth")
                        {
                            imageProperties.depth = childNode.InnerText;
                        }
                        if (childNode.Name == "ViewRotation")
                        {
                            imageProperties.viewRotation = childNode.InnerText;
                        }
                        if (childNode.Name == "SampleColor")
                        {
                            imageProperties.sampleColor = childNode.InnerText;
                        }
                        if (childNode.Name == "ColorSpace")
                        {
                            imageProperties.colorSpace = childNode.InnerText;
                        }
                        if (childNode.Name == "Compression")
                        {
                            imageProperties.compression = childNode.InnerText;
                        }
                        if (childNode.Name == "PrimaryEncoding")
                        {
                            imageProperties.primaryEncoding = childNode.InnerText;
                        }
                    }
                }
            }
            nodeList = document.GetElementsByTagName("CameraInformation");
            for (int i = 0; i < nodeList.Count; ++i)
            {
                XmlNode node = nodeList.Item(i);
                if (node.HasChildNodes)
                {
                    XmlNodeList childNodeList = node.ChildNodes;
                    for (int j = 0; j < childNodeList.Count; j++)
                    {

                        XmlNode childNode = childNodeList.Item(j);
                        if (childNode.Name == "Maker")
                        {
                            imageProperties.maker = childNode.InnerText;
                        }
                        if (childNode.Name == "Model")
                        {
                            imageProperties.model = childNode.InnerText;
                        }
                        if (childNode.Name == "Software")
                        {
                            imageProperties.software = childNode.InnerText;
                        }
                        if (childNode.Name == "SourceURL")
                        {
                            imageProperties.sourceURL = childNode.InnerText;
                        }
                        if (childNode.Name == "ExifVersion")
                        {
                            imageProperties.exifVersion = childNode.InnerText;
                        }
                        if (childNode.Name == "CaptureDate")
                        {
                            imageProperties.captureDate = childNode.InnerText;
                        }
                        if (childNode.Name == "ExposureProgram")
                        {
                            imageProperties.exposureProgram = childNode.InnerText;
                        }
                        if (childNode.Name == "ISOSpeedRating")
                        {
                            imageProperties.isoSpeedRating = childNode.InnerText;
                        }
                        if (childNode.Name == "ExposureBias")
                        {
                            imageProperties.exposureBias = childNode.InnerText;
                        }
                        if (childNode.Name == "ExposureTime")
                        {
                            imageProperties.exposureTime = childNode.InnerText;
                        }
                        if (childNode.Name == "Aperture")
                        {
                            imageProperties.aperture = childNode.InnerText;
                        }
                        if (childNode.Name == "MeteringMode")
                        {
                            imageProperties.meteringMode = childNode.InnerText;
                        }
                        if (childNode.Name == "LightSource")
                        {
                            imageProperties.lightSource = childNode.InnerText;
                        }
                        if (childNode.Name == "Flash")
                        {
                            imageProperties.flash = childNode.InnerText;
                        }
                        if (childNode.Name == "FocalLength")
                        {
                            imageProperties.focalLength = childNode.InnerText;
                        }
                        if (childNode.Name == "SensingMethod")
                        {
                            imageProperties.sensingMethod = childNode.InnerText;
                        }
                        if (childNode.Name == "DigitalZoom")
                        {
                            imageProperties.digitalZoom = childNode.InnerText;
                        }
                    }
                }
            }
            nodeList = document.GetElementsByTagName("GPS");
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
                        if (childNode.Name == "Latitude")
                        {
                            imageProperties.latitude = childNode.InnerText;
                        }
                        if (childNode.Name == "Longitude")
                        {
                            imageProperties.longitude = childNode.InnerText;
                        }
                        if (childNode.Name == "GPSTimeStamp")
                        {
                            imageProperties.gpsTimeStamp = childNode.InnerText;
                        }
                    }
                }
            }
            nodeList = document.GetElementsByTagName("CopyrightProperties");
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
                        if (childNode.Name == "Copyright")
                        {
                            imageProperties.copyright = childNode.InnerText;
                        }
                        if (childNode.Name == "UsageRights")
                        {
                            imageProperties.usageRights = childNode.InnerText;
                        }
                        if (childNode.Name == "CopyrightURL")
                        {
                            imageProperties.copyrightURL = childNode.InnerText;
                        }
                    }
                }
            } 



        }
        public XmlDocument XMLDocument { get { return document; } }
        public ImageProperties ImageProperties { get { return imageProperties; } }
    }
}
