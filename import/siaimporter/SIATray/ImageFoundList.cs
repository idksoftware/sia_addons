using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.IO;
using System.Windows.Media.Imaging;

namespace IATray
{


    public partial class ImageFoundList : Form
    {
        String strDirectory;
        ImageList imglstT = null;
        ImageList imglstS = null;
        ImageList imglstL = null;
        View view;

        public ImageFoundList()
        {
            InitializeComponent();
            listViewImagesFound.View = View.LargeIcon;

            imglstT = new ImageList();
            imglstT.Images.Add(new Icon(GetType(), "imgarchive.ico"));
            imglstT.ImageSize = new Size(8, 8);
            imglstS = new ImageList();
            imglstS.Images.Add(new Icon(GetType(), "imgarchive.ico"));
            imglstS.ImageSize = new Size(32, 32);
            imglstL = new ImageList();
            imglstL.Images.Add(new Icon(GetType(), "imgarchive.ico"));
            imglstL.ImageSize = new Size(64, 64);
            imglstL.ColorDepth = ColorDepth.Depth24Bit;
            //imglst.Images.Add(new Bitmap(GetType(), "EXE.BMP"));
            //listViewImagesFound.StateImageList = imglstT;
            listViewImagesFound.SmallImageList = imglstS;
            listViewImagesFound.LargeImageList = imglstL;
            //listViewImagesFound.TileSize = new Size(64,64);
        }

        public void ShowFiles(List<String> filesFound)
        {
            
            listViewImagesFound.Items.Clear();

            foreach (String fn in filesFound)
            {
                FileInfo fi = new FileInfo(fn);

                ListViewItem lvi = new ListViewItem(fi.Name);
                //Image thumbnailImage = null;
                if (Path.GetExtension(fi.Name).ToUpper() == ".JPG")
                {
                    // create an image object, using the filename we just retrieved
                    Stream imageStreamSource = new FileStream(fi.FullName, FileMode.Open, FileAccess.Read, FileShare.Read);
                    JpegBitmapDecoder decoder = new JpegBitmapDecoder(imageStreamSource, BitmapCreateOptions.PreservePixelFormat, BitmapCacheOption.Default);
                    BitmapSource bitmapSource = decoder.Frames[0];

                    // Draw the Image
                    Image image = Image.FromStream(imageStreamSource);
                    
                    //image.Stretch = Stretch.None;
                    //image.Margin = new Thickness(20);


                    //Image image = System.Drawing.Image.FromFile(fi.FullName);
                    // create the actual thumbnail image
                    Image thumbnailImage = image.GetThumbnailImage
                            (32, 32, new Image.GetThumbnailImageAbort(ThumbnailCallback), IntPtr.Zero);

                    int idx = imglstS.Images.Add(thumbnailImage, Color.Transparent);
                    //int idx = imglstS.Images.Add(Image.FromFile(fi.FullName), Color.Transparent);
                    lvi.ImageIndex = idx;

                    // create the actual thumbnail image
                    thumbnailImage = image.GetThumbnailImage
                            (64, 64, new Image.GetThumbnailImageAbort(ThumbnailCallback), IntPtr.Zero);

                    idx = imglstL.Images.Add(thumbnailImage, Color.Transparent);
                    lvi.ImageIndex = idx;
                }
                else
                {
                    lvi.ImageIndex = 0;
                }
                lvi.SubItems.Add(fi.Length.ToString("N0"));
                lvi.SubItems.Add(fi.LastWriteTime.ToString());

                string strAttr = "";

                if ((fi.Attributes & FileAttributes.Archive) != 0)
                    strAttr += "A";

                if ((fi.Attributes & FileAttributes.Hidden) != 0)
                    strAttr += "H";

                if ((fi.Attributes & FileAttributes.ReadOnly) != 0)
                    strAttr += "R";

                if ((fi.Attributes & FileAttributes.System) != 0)
                    strAttr += "S";

                lvi.SubItems.Add(strAttr);

                listViewImagesFound.Items.Add(lvi);
            }
        }

        public void ShowFiles(string strDirectory)
        {
            this.strDirectory = strDirectory;

            listViewImagesFound.Items.Clear();
            DirectoryInfo dirinfo = new DirectoryInfo(strDirectory);
            FileInfo[] afileinfo;

            try
            {
                afileinfo = dirinfo.GetFiles();
            }
            catch
            {
                return;
            }

            foreach (FileInfo fi in afileinfo)
            {

                ListViewItem lvi = new ListViewItem(fi.Name);
                //Image thumbnailImage = null;
                if (Path.GetExtension(fi.Name).ToUpper() == ".JPG")
                {
                    // create an image object, using the filename we just retrieved
                    Image image = System.Drawing.Image.FromFile(fi.FullName);
                    // create the actual thumbnail image
                    Image thumbnailImage = image.GetThumbnailImage
                            (32, 32, new Image.GetThumbnailImageAbort(ThumbnailCallback), IntPtr.Zero);
                    
                    int idx = imglstS.Images.Add(thumbnailImage,Color.Transparent);
                    //int idx = imglstS.Images.Add(Image.FromFile(fi.FullName), Color.Transparent);
                    lvi.ImageIndex = idx;

                    // create the actual thumbnail image
                    thumbnailImage = image.GetThumbnailImage
                            (200, 200, new Image.GetThumbnailImageAbort(ThumbnailCallback), IntPtr.Zero);

                    idx = imglstL.Images.Add(thumbnailImage, Color.Transparent);
                    lvi.ImageIndex = idx;
                }
                else
                {
                    lvi.ImageIndex = 0;
                }
                lvi.SubItems.Add(fi.Length.ToString("N0"));
                lvi.SubItems.Add(fi.LastWriteTime.ToString());

                string strAttr = "";

                if ((fi.Attributes & FileAttributes.Archive) != 0)
                    strAttr += "A";

                if ((fi.Attributes & FileAttributes.Hidden) != 0)
                    strAttr += "H";

                if ((fi.Attributes & FileAttributes.ReadOnly) != 0)
                    strAttr += "R";

                if ((fi.Attributes & FileAttributes.System) != 0)
                    strAttr += "S";

                lvi.SubItems.Add(strAttr);

                listViewImagesFound.Items.Add(lvi);
            }
        }
        public bool ThumbnailCallback()
        {
            return true;
        }

        private void thumbneilsToolStripMenuItem_Click(object sender, EventArgs e)
        {
            view = View.LargeIcon;
            listViewImagesFound.View = view;
            listViewImagesFound.Sort();
            int c = listViewImagesFound.LargeImageList.Images.Count;
            //listViewImagesFound.LargeImageList.Images.RemoveAt(c-1);
            listViewImagesFound.LargeImageList.Images[c - 1] = listViewImagesFound.LargeImageList.Images[c - 2];
        }

        private void tilesToolStripMenuItem_Click(object sender, EventArgs e)
        {
            view = View.SmallIcon;
            listViewImagesFound.View = view;
            listViewImagesFound.Sort();
        }

        private void iconsToolStripMenuItem_Click(object sender, EventArgs e)
        {
            view = View.Tile;
            listViewImagesFound.View = view;
        }

        private void listToolStripMenuItem_Click(object sender, EventArgs e)
        {
            view = View.List;
            listViewImagesFound.View = view;
        }

        private void detailsToolStripMenuItem_Click(object sender, EventArgs e)
        {
            view = View.Details;
            listViewImagesFound.View = view;
            listViewImagesFound.Sort();
        }
    }

}