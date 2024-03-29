using System;
using System.Collections.Generic;
using System.Text;
using System.ComponentModel;

namespace IMGProperties
{
    [DefaultPropertyAttribute("Exif")]
    public class ExifContainer
    {
        
        private string _Make = "NIKON CORPORATION";
        private string _SSN = "123545";
        private string _Model = " NIKON D80";  
        private string _Orientation = " Top, left side (Horizontal / normal)";  
        private string _XResolution = " 300 dots per inch";  
        private string _YResolution = " 300 dots per inch";  
        private string _ResolutionUnit = "Inch";  
        private string _Software = "Ver.1.10";  
        private string _DateTime = "2008:03:08 18:57:50";  
        private string _YCbCrPositioning = "Datum point";  
        private string _ExposureTime = "1/125 sec";  
        private string _F_Number = "F4.5";  
        private string _ExposureProgram = "Unknown program (0)";  
        private string _ISOSpeedRatings = "100";  
        private string _ExifVersion = "2.21";  
        private string _DateTimeOriginal = "2008:03:08 18:57:50";  
        private string _DateTimeDigitized = "2008:03:08 18:57:50";  
        private string _ComponentsConfiguration = "YCbCr";  
        private string _CompressedBitsPerPixel = "1 bit/pixel";  
        private string _ExposureBiasValue = "0 EV";  
        private string _MaxApertureValue = "F4.4";  
        private string _MeteringMode = "Multi-segment";  
        private string _LightSource = "Unknown";  
        private string _Flash = "Flash fired, return detected, auto";  
        private string _FocalLength = "70.0 mm";  
        private string _UserComment = ""; 
        private string _Sub_SecTime = "50";  
        private string _Sub_SecTimeOriginal = "50";  
        private string _Sub_SecTimeDigitized = "50";  
        private string _FlashPixVersion = "1.00";  
        private string _ColorSpace = "sRGB";  
        private string _ExifImageWidth = "3872 pixels";  
        private string _ExifImageHeight = "2592 pixels";  
        private string _SensingMethod = "One-chip color area sensor";  
        private string _FileSource = "Digital Still Camera (DSC)";  
        private string _SceneType = "Directly photographed image";  
        private string _CFAPattern = "0 2 0 2 1 2 0 1";  
        private string _CustomRendered = "Normal process"; 
        private string _ExposureMode = "Auto exposure"; 
        private string _WhiteBalance = "Auto white balance";  
        private string _DigitalZoomRatio = "1";  
        private string _FocalLength35 = "105mm";  
        private string _SceneCaptureType = "Standard";  
        private string _GainControl = "None";  
        private string _Contrast = "None";  
        private string _Saturation = "None";  
        private string _Sharpness = "None";  
        private string _SubjectDistanceRange = "Unknown";  
        private string _Compression = "JPEG (old-style)";  
        private string _ThumbnailOffset = "29196 bytes";  
        private string _ThumbnailLength = "9370 bytes";  
        private string _ThumbnailData = "[9370 bytes of thumbnail data]";  
        // Name property with category attribute and 
        // description attribute added 
        [CategoryAttribute("Camera"), DescriptionAttribute("Make of camera")]
        public string Make
        {
            get
            {
                return _Make;
            }
            set
            {
                _Make = value;
            }
        }

        [CategoryAttribute("Camera"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string Model
        {
            get
            {
                return _Model;
            }
            set
            {
                _Model = value;
            }
        }

        [CategoryAttribute("Exif"),
        DescriptionAttribute("Orientation")]
        public string Orientation
        {
            get
            {
                return _Orientation;
            }
            set
            {
                _Orientation = value;
            }
        }
        [CategoryAttribute("Exif"),
        DescriptionAttribute("XResolution")]
        public string XResolution
        {
            get
            {
                return _XResolution;
            }
            set
            {
                _XResolution = value;
            }
        }
        [CategoryAttribute("Exif"),
        DescriptionAttribute("YResolution")]
        public string YResolution
        {
            get
            {
                return _YResolution;
            }
            set
            {
                _YResolution = value;
            }
        }
        [CategoryAttribute("Exif"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string ResolutionUnit
        {
            get
            {
                return _ResolutionUnit;
            }
            set
            {
                _ResolutionUnit = value;
            }
        }
        [CategoryAttribute("Exif"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string Software
        {
            get
            {
                return _Software;
            }
            set
            {
                _Software = value;
            }
        }
        [CategoryAttribute("Date Time"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string DateTime
        {
            get
            {
                return _DateTime;
            }
            set
            {
                _DateTime = value;
            }
        }
        [CategoryAttribute("Exif"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string YCbCrPositioning
        {
            get
            {
                return _YCbCrPositioning;
            }
            set
            {
                _YCbCrPositioning = value;
            }
        }
        [CategoryAttribute("Settings"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string ExposureTime
        {
            get
            {
                return _ExposureTime;
            }
            set
            {
                _ExposureTime = value;
            }
        }
        [CategoryAttribute("Settings"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string F_Number
        {
            get
            {
                return _F_Number;
            }
            set
            {
                _F_Number = value;
            }
        }
        [CategoryAttribute("Settings"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string ExposureProgram
        {
            get
            {
                return _ExposureProgram;
            }
            set
            {
                _ExposureProgram = value;
            }
        }
        [CategoryAttribute("Settings"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string ISOSpeedRatings
        {
            get
            {
                return _ISOSpeedRatings;
            }
            set
            {
                _ISOSpeedRatings = value;
            }
        }
        [CategoryAttribute("Exif"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string ExifVersion
        {
            get
            {
                return _ExifVersion;
            }
            set
            {
                _ExifVersion = value;
            }
        }
        [CategoryAttribute("Date Time"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string DateTimeOriginal
        {
            get
            {
                return _DateTimeOriginal;
            }
            set
            {
                _DateTimeOriginal = value;
            }
        }
        [CategoryAttribute("Date Time"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string DateTimeDigitized
        {
            get
            {
                return _DateTimeDigitized;
            }
            set
            {
                _DateTimeDigitized = value;
            }
        }
        [CategoryAttribute("Exif"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string ComponentsConfiguration
        {
            get
            {
                return _ComponentsConfiguration;
            }
            set
            {
                _ComponentsConfiguration = value;
            }
        }
        [CategoryAttribute("Exif"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string CompressedBitsPerPixel
        {
            get
            {
                return _CompressedBitsPerPixel;
            }
            set
            {
                _CompressedBitsPerPixel = value;
            }
        }
        [CategoryAttribute("Settings"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string ExposureBiasValue
        {
            get
            {
                return _ExposureBiasValue;
            }
            set
            {
                _ExposureBiasValue = value;
            }
        }
        [CategoryAttribute("Exif"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string MaxApertureValue
        {
            get
            {
                return _MaxApertureValue;
            }
            set
            {
                _MaxApertureValue = value;
            }
        }
        [CategoryAttribute("Settings"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string MeteringMode
        {
            get
            {
                return _MeteringMode;
            }
            set
            {
                _MeteringMode = value;
            }
        }
        [CategoryAttribute("Settings"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string LightSource
        {
            get
            {
                return _LightSource;
            }
            set
            {
                _LightSource = value;
            }
        }
        [CategoryAttribute("Settings"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string Flash
        {
            get
            {
                return _Flash;
            }
            set
            {
                _Flash = value;
            }
        }
        [CategoryAttribute("Settings"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string FocalLength
        {
            get
            {
                return _FocalLength;
            }
            set
            {
                _FocalLength = value;
            }
        }
        [CategoryAttribute("Exif"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string UserComment
        {
            get
            {
                return _UserComment;
            }
            set
            {
                _UserComment = value;
            }
        }
        [CategoryAttribute("Exif"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string Sub_SecTime
        {
            get
            {
                return _Sub_SecTime;
            }
            set
            {
                _Sub_SecTime = value;
            }
        }
        [CategoryAttribute("Exif"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string Sub_SecTimeOriginal
        {
            get
            {
                return _Sub_SecTimeOriginal;
            }
            set
            {
                _Sub_SecTimeOriginal = value;
            }
        }
        [CategoryAttribute("Exif"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string Sub_SecTimeDigitized
        {
            get
            {
                return _Sub_SecTimeDigitized;
            }
            set
            {
                _Sub_SecTimeDigitized = value;
            }
        }
        [CategoryAttribute("Settings"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string FlashPixVersion
        {
            get
            {
                return _FlashPixVersion;
            }
            set
            {
                _FlashPixVersion = value;
            }
        }
        [CategoryAttribute("Exif"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string ColorSpace
        {
            get
            {
                return _ColorSpace;
            }
            set
            {
                _ColorSpace = value;
            }
        }
        [CategoryAttribute("Exif"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string ExifImageWidth
        {
            get
            {
                return _ExifImageWidth;
            }
            set
            {
                _ExifImageWidth = value;
            }
        }
        [CategoryAttribute("Exif"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string ExifImageHeight
        {
            get
            {
                return _ExifImageHeight;
            }
            set
            {
                _ExifImageHeight = value;
            }
        }
        [CategoryAttribute("Settings"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string SensingMethod
        {
            get
            {
                return _SensingMethod;
            }
            set
            {
                _SensingMethod = value;
            }
        }
        [CategoryAttribute("Exif"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string FileSource
        {
            get
            {
                return _FileSource;
            }
            set
            {
                _FileSource = value;
            }
        }
        [CategoryAttribute("Exif"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string SceneType
        {
            get
            {
                return _SceneType;
            }
            set
            {
                _SceneType = value;
            }
        }
        [CategoryAttribute("Exif"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string CFAPattern
        {
            get
            {
                return _CFAPattern;
            }
            set
            {
                _CFAPattern = value;
            }
        }
        [CategoryAttribute("Exif"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string CustomRendered
        {
            get
            {
                return _CustomRendered;
            }
            set
            {
                _CustomRendered = value;
            }
        }
        [CategoryAttribute("Settings"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string ExposureMode
        {
            get
            {
                return _ExposureMode;
            }
            set
            {
                _ExposureMode = value;
            }
        }
        [CategoryAttribute("Settings"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string WhiteBalance
        {
            get
            {
                return _WhiteBalance;
            }
            set
            {
                _WhiteBalance = value;
            }
        }
        [CategoryAttribute("Settings"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string DigitalZoomRatio
        {
            get
            {
                return _DigitalZoomRatio;
            }
            set
            {
                _DigitalZoomRatio = value;
            }
        }
        [CategoryAttribute("Settings"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string FocalLength35
        {
            get
            {
                return _FocalLength35;
            }
            set
            {
                _FocalLength35 = value;
            }
        }
        [CategoryAttribute("Exif"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string SceneCaptureType
        {
            get
            {
                return _SceneCaptureType;
            }
            set
            {
                _SceneCaptureType = value;
            }
        }
        [CategoryAttribute("Exif"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string GainControl
        {
            get
            {
                return _GainControl;
            }
            set
            {
                _GainControl = value;
            }
        }
        [CategoryAttribute("Exif"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string Contrast
        {
            get
            {
                return _Contrast;
            }
            set
            {
                _Contrast = value;
            }
        }
        [CategoryAttribute("Settings"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string Saturation
        {
            get
            {
                return _Saturation;
            }
            set
            {
                _Saturation = value;
            }
        }
        [CategoryAttribute("Settings"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string Sharpness
        {
            get
            {
                return _Sharpness;
            }
            set
            {
                _Sharpness = value;
            }
        }
        [CategoryAttribute("Settings"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string SubjectDistanceRange
        {
            get
            {
                return _SubjectDistanceRange;
            }
            set
            {
                _SubjectDistanceRange = value;
            }
        }
        [CategoryAttribute("Exif"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string Compression
        {
            get
            {
                return _Compression;
            }
            set
            {
                _Compression = value;
            }
        }
        [CategoryAttribute("Thumbnail"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string ThumbnailOffset
        {
            get
            {
                return _ThumbnailOffset;
            }
            set
            {
                _ThumbnailOffset = value;
            }
        }
        [CategoryAttribute("Thumbnail"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string ThumbnailLength
        {
            get
            {
                return _ThumbnailLength;
            }
            set
            {
                _ThumbnailLength = value;
            }
        }
        [CategoryAttribute("Thumbnail"),
        DescriptionAttribute("Date of Birth of the Customer (optional)")]
        public string ThumbnailData
        {
            get
            {
                return _ThumbnailData;
            }
            set
            {
                _ThumbnailData = value;
            }
        }        
    } 

}


/*
<Exif>
  Make NIKON CORPORATION 
  Model NIKON D80 
  Orientation Top, left side (Horizontal / normal)</Orientation> 
  XResolution 300 dots per inch</XResolution> 
  YResolution 300 dots per inch</YResolution> 
  ResolutionUnit>Inch</ResolutionUnit> 
  Software>Ver.1.10</Software> 
  DateTime>2008:03:08 18:57:50</DateTime> 
  YCbCrPositioning>Datum point</YCbCrPositioning> 
  ExposureTime>1/125 sec</ExposureTime> 
  F-Number>F4.5</F-Number> 
  ExposureProgram>Unknown program (0)</ExposureProgram> 
  ISOSpeedRatings>100</ISOSpeedRatings> 
  ExifVersion>2.21</ExifVersion> 
  DateTimeOriginal>2008:03:08 18:57:50</DateTimeOriginal> 
  DateTimeDigitized>2008:03:08 18:57:50</DateTimeDigitized> 
  ComponentsConfiguration>YCbCr</ComponentsConfiguration> 
  CompressedBitsPerPixel>1 bit/pixel</CompressedBitsPerPixel> 
  ExposureBiasValue>0 EV</ExposureBiasValue> 
  MaxApertureValue>F4.4</MaxApertureValue> 
  MeteringMode>Multi-segment</MeteringMode> 
  LightSource>Unknown</LightSource> 
  Flash>Flash fired, return detected, auto</Flash> 
  FocalLength>70.0 mm</FocalLength> 
  UserComment /> 
  Sub-SecTime>50</Sub-SecTime> 
  Sub-SecTimeOriginal>50</Sub-SecTimeOriginal> 
  Sub-SecTimeDigitized>50</Sub-SecTimeDigitized> 
  FlashPixVersion>1.00</FlashPixVersion> 
  ColorSpace>sRGB</ColorSpace> 
  ExifImageWidth>3872 pixels</ExifImageWidth> 
  ExifImageHeight>2592 pixels</ExifImageHeight> 
  SensingMethod>One-chip color area sensor</SensingMethod> 
  FileSource>Digital Still Camera (DSC)</FileSource> 
  SceneType>Directly photographed image</SceneType> 
  CFAPattern>0 2 0 2 1 2 0 1</CFAPattern> 
  CustomRendered>Normal process</CustomRendered> 
  ExposureMode>Auto exposure</ExposureMode> 
  WhiteBalance>Auto white balance</WhiteBalance> 
  DigitalZoomRatio>1</DigitalZoomRatio> 
  FocalLength35>105mm</FocalLength35> 
  SceneCaptureType>Standard</SceneCaptureType> 
  GainControl>None</GainControl> 
  Contrast>None</Contrast> 
  Saturation>None</Saturation> 
  Sharpness>None</Sharpness> 
  SubjectDistanceRange>Unknown</SubjectDistanceRange> 
  Compression>JPEG (old-style)</Compression> 
  ThumbnailOffset>29196 bytes</ThumbnailOffset> 
  ThumbnailLength>9370 bytes</ThumbnailLength> 
  ThumbnailData>[9370 bytes of thumbnail data]</ThumbnailData> 
  </Exif>
- <NikonMakernote>
  <FirmwareVersion>2.10</FirmwareVersion> 
  <ISO>ISO 100</ISO> 
  <ColorMode>COLOR</ColorMode> 
  <QualityAndFileFormat>BASIC</QualityAndFileFormat> 
  <WhiteBalance>AUTO</WhiteBalance> 
  <Sharpening>AUTO</Sharpening> 
  <AFType>AF-A</AFType> 
  <FlashSyncMode>NORMAL</FlashSyncMode> 
  <AutoFlashMode>Built-in,TTL</AutoFlashMode> 
  <WhiteBalanceFine>0</WhiteBalanceFine> 
  <WhiteBalanceRBCoefficients>506/256 310/256 256/256 256/256</WhiteBalanceRBCoefficients> 
  <AutoFlashCompensation>Unknown</AutoFlashCompensation> 
  <ISO>0 100</ISO> 
  <ToneCompensation>AUTO</ToneCompensation> 
  <Lens>18-70mm f/3.5-4.5</Lens> 
  <AFFocusPosition>Unknown (0 0 0 1)</AFFocusPosition> 
  <ColourMode>MODE3a</ColourMode> 
  <Lightsource>SPEEDLIGHT</Lightsource> 
  <CameraHueAdjustment>0 degrees</CameraHueAdjustment> 
  <NoiseReduction>OFF</NoiseReduction> 
  <ExposureSequenceNumber>4617</ExposureSequenceNumber> 
  </NikonMakernote>
- <Interoperability>
  <InteroperabilityIndex>Recommended Exif Interoperability Rules (ExifR98)</InteroperabilityIndex> 
  <InteroperabilityVersion>1.00</InteroperabilityVersion> 
  </Interoperability>
- <Jpeg>
  <DataPrecision>8 bits</DataPrecision> 
  <ImageHeight>2592 pixels</ImageHeight> 
  <ImageWidth>3872 pixels</ImageWidth> 
  <NumberOfComponents>3</NumberOfComponents> 
  <Component1>Y component: Quantization table 0, Sampling factors 1 horiz/2 vert</Component1> 
  <Component2>Cb component: Quantization table 1, Sampling factors 1 horiz/1 vert</Component2> 
  <Component3>Cr component: Quantization table 1, Sampling factors 1 horiz/1 vert</Component3> 
  </Jpeg>
*/
