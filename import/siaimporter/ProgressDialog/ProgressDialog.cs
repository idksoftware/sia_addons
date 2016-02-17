using System;
using System.IO;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Diagnostics;

namespace ProgressDialog
{
    public partial class ProgressDialog : Form
    {
        enum Action {
            Idle,
            Initalisation,
            Analising,
            Importing,
            Commplete,
            Canceled,
            Eborted
        }
        private class Line
        {
            public string Str;
            public Color ForeColor;

            public Line(string str, Color color)
            {
                Str = str;
                ForeColor = color;
            }
        };

        ArrayList lines = new ArrayList();

        

        string filterString = "";
        bool scrolling = true;
        Color receivedColor = Color.Green;
        Color sentColor = Color.Blue;
        bool serverOpen = false;
        bool running = true;
        bool canceling = false;
        int maxItems = 0;
        int currentItem = 0;
        Action action = Action.Idle;
        String currentFilename;
        String currentAction;
        
        public ProgressDialog()
        {
            InitializeComponent();

            LaunchCommandLine launchCommandLine = LaunchCommandLine.Instance;
            LaunchCommandLine.StatusChanged += OnStatusChanged;
            this.progressBar.Maximum = 100;

            outputList_Initialize();

            UDPReader reader = UDPReader.Instance;
           
            //com.StatusChanged += OnStatusChanged;
            reader.DataReceived += OnDataReceived;
            reader.Open();
            buttonCancel.Image = Properties.Resources.cancel;
            buttonPause.Image = Properties.Resources.running;
            setTextFields();
            launchCommandLine.Run();
        }

        // shutdown the worker thread when the form closes
        protected override void OnClosed(EventArgs e)
        {
            
            base.OnClosed(e);
        }

        private void outputList_Initialize()
        {
            // owner draw for listbox so we can add color
            outputList.DrawMode = DrawMode.OwnerDrawFixed;
            outputList.DrawItem += new DrawItemEventHandler(outputList_DrawItem);
            outputList.ClearSelected();
            
        }

        /// <summary>
        /// draw item with color in output window
        /// </summary>
        void outputList_DrawItem(object sender, DrawItemEventArgs e)
        {
            e.DrawBackground();
            if (e.Index >= 0 && e.Index < outputList.Items.Count)
            {
                Line line = (Line)outputList.Items[e.Index];
                Trace.WriteLine(line.Str);

                // if selected, make the text color readable
                Color color = line.ForeColor;
                if ((e.State & DrawItemState.Selected) == DrawItemState.Selected)
                {
                    color = Color.Black;	// make it readable
                }

                e.Graphics.DrawString(line.Str, e.Font, new SolidBrush(color),
                    e.Bounds, StringFormat.GenericDefault);
            }
            e.DrawFocusRectangle();
        }

        /// <summary>
        /// Scroll to bottom of output window
        /// </summary>
        void outputList_Scroll()
        {
            if (scrolling)
            {
                int itemsPerPage = (int)(outputList.Height / outputList.ItemHeight);
                outputList.TopIndex = outputList.Items.Count - itemsPerPage;
            }
        }

        /// <summary>
        /// Enable/Disable copy selection in output window
        /// </summary>
        private void outputList_SelectedIndexChanged(object sender, EventArgs e)
        {
            //popUpMenu.MenuItems[0].Enabled = (outputList.SelectedItems.Count > 0);
        }

        /// <summary>
        /// copy selection in output window to clipboard
        /// </summary>
        private void outputList_Copy(object sender, EventArgs e)
        {
            int iCount = outputList.SelectedItems.Count;
            if (iCount > 0)
            {
                String[] source = new String[iCount];
                for (int i = 0; i < iCount; ++i)
                {
                    source[i] = ((Line)outputList.SelectedItems[i]).Str;
                }

                String dest = String.Join("\r\n", source);
                Clipboard.SetText(dest);
            }
        }

        /// <summary>
        /// copy all lines in output window
        /// </summary>
        private void outputList_CopyAll(object sender, EventArgs e)
        {
            int iCount = outputList.Items.Count;
            if (iCount > 0)
            {
                String[] source = new String[iCount];
                for (int i = 0; i < iCount; ++i)
                {
                    source[i] = ((Line)outputList.Items[i]).Str;
                }

                String dest = String.Join("\r\n", source);
                Clipboard.SetText(dest);
            }
        }

        /// <summary>
        /// select all lines in output window
        /// </summary>
        private void outputList_SelectAll(object sender, EventArgs e)
        {
            outputList.BeginUpdate();
            for (int i = 0; i < outputList.Items.Count; ++i)
            {
                outputList.SetSelected(i, true);
            }
            outputList.EndUpdate();
        }

        /// <summary>
        /// clear selected in output window
        /// </summary>
        private void outputList_ClearSelected(object sender, EventArgs e)
        {
            outputList.ClearSelected();
            outputList.SelectedItem = -1;
        }

        /// <summary>
        /// Partial line for AddData().
        /// </summary>
        private Line partialLine = null;

        /// <summary>
        /// Add data to the output.
        /// </summary>
        /// <param name="StringIn"></param>
        /// <returns></returns>
        private Line AddData(String StringIn)
        {
            String StringOut = PrepareData(StringIn);

            // if we have a partial line, add to it.
            if (partialLine != null)
            {
                // tack it on
                partialLine.Str = StringOut;
                outputList_Update(partialLine);
                return partialLine;
            }

            return outputList_Add(StringOut, receivedColor);
        }

        private String PrepareData(String StringIn)
        {
            return StringIn;
        }
        /// <summary>
        /// add a new line to output window
        /// </summary>
        Line outputList_Add(string str, Color color)
        {
            Line newLine = new Line(str, color);
            lines.Add(newLine);

            //if (outputList_ApplyFilter(newLine.Str))
            //{
                outputList.Items.Add(newLine);
                outputList_Scroll();
            //}

            return newLine;
        }

        /// <summary>
        /// check to see if filter matches string
        /// </summary>
        /// <param name="s">string to check</param>
        /// <returns>true if matches filter</returns>
        bool outputList_ApplyFilter(String s)
        {
            if (filterString == "")
            {
                return true;
            }
            else if (s == "")
            {
                return false;
            }
            //else if (Settings.Option.FilterUseCase)
            //{
            //    return (s.IndexOf(filterString) != -1);
            //}
            else
            {
                string upperString = s.ToUpper();
                string upperFilter = filterString.ToUpper();
                return (upperString.IndexOf(upperFilter) != -1);
            }
        }
        /// <summary>
        /// Update a line in the output window.
        /// </summary>
        /// <param name="line">line to update</param>
        void outputList_Update(Line line)
        {
            // should we add to output?
            if (outputList_ApplyFilter(line.Str))
            {
                // is the line already displayed?
                bool found = false;
                for (int i = 0; i < outputList.Items.Count; ++i)
                {
                    int index = (outputList.Items.Count - 1) - i;
                    if (line == outputList.Items[index])
                    {
                        
                        // is item visible?
                        int itemsPerPage = (int)(outputList.Height / outputList.ItemHeight);
                        if (index >= outputList.TopIndex &&
                            index < (outputList.TopIndex + itemsPerPage))
                        {
                            // is there a way to refresh just one line
                            // without redrawing the entire listbox?
                            // changing the item value has no effect
                            outputList.Refresh();
                        }
                        found = true;
                        break;
                    }
                }
                if (found)
                {
                    // not found, so add it
                    outputList.Items.Add(line);
                }
            }
        }

        // delegate used for Invoke
        internal delegate void StringDelegate(string data);

        /// <summary>
        /// Handle data received event from serial port.
        /// </summary>
        /// <param name="data">incoming data</param>
        public void OnDataReceived(string dataStrIn)
        {
            //Handle multi-threading
            if (InvokeRequired)
            {
                Invoke(new StringDelegate(OnDataReceived), new object[] { dataStrIn });
                return;
            }
            int index;
            String codeString = dataStrIn.Substring(0, 3);
            String dataIn = dataStrIn.Substring(4, dataStrIn.Length - 4);
            int code;
            if (Int32.TryParse(codeString, out code))
            {
                if (code == 10)
                {
                    currentAction = dataIn;
                    setTextFields();
                }
                if (code == 30)
                {
                    action = Action.Initalisation;
                    AsynchronousClient.StartClient();
                    serverOpen = true;
                    setTextFields();
                }
                if (code == 31)
                {
                    action = Action.Analising;
                    setTextFields();
                }
                if (code == 32)
                {
                    action = Action.Importing;
                    setTextFields();
                }
                if (code == 101)
                {
                    int files;
                    int idx = dataIn.IndexOf(" ");
                    String numStr = dataIn.Substring(0, idx);
                    if (Int32.TryParse(numStr, out files))
                    {
                        this.maxItems = files;
                        setTextFields();
                       
                    }
                }
                if (code == 103)
                {
                    int current;
                    int idx = dataIn.IndexOf(" ");
                    String numStr = dataIn.Substring(0, idx);
                    if (Int32.TryParse(numStr, out current))
                    {
                        this.currentItem = current;
                        setTextFields();

                    }
                }
                if (code == 104)
                {
                    currentFilename = dataIn;
                    setTextFields();
                }
                /*
                String tmp = dataIn.Substring(4, dataIn.Length - 4); ;
                index = tmp.IndexOf(" ");
                String ItemString = tmp.Substring(0, index);
                ProcessingLabel.Text = "Processing item " + ItemString;
                 */
                
            }
            // pause scrolling to speed up output of multiple lines
            bool saveScrolling = scrolling;
            scrolling = false;

            // if we detect a line terminator, add line to output
           
            
            while (dataIn.Length > 0 &&
                ((index = dataIn.IndexOf("\r")) != -1 ||
                (index = dataIn.IndexOf("\n")) != -1))
            {
                String StringIn = dataIn.Substring(0, index);
                dataIn = dataIn.Remove(0, index + 1);
                AddData(StringIn);
                //logFile_writeLine(AddData(StringIn).Str);
                partialLine = null;	// terminate partial line
            }
            
            // if we have data remaining, add a partial line
            if (dataIn.Length > 0)
            {
                partialLine = AddData(dataIn);
            }

            // restore scrolling
            scrolling = saveScrolling;
            outputList_Scroll();
        }

        public void setTextFields()
        {
            if (serverOpen)
            {
                this.buttonCancel.Visible = true;
                this.buttonPause.Visible = true;
            }
            else
            {
                this.buttonCancel.Visible = false;
                this.buttonPause.Visible = false;
            }

            labelItemText.Text = String.Format("{0} of {1}", currentItem, maxItems);
            if (currentItem == 0)
            {
                ActionLabel.Text = String.Format("{0} 0% complete", actionString());
            }
            else
            {
                if (maxItems != 0) {
                    double cur = currentItem;
                    double max = maxItems;
                    double percent = cur / max * 100;
                    int intPercent = (int)percent;
                    ActionLabel.Text = String.Format("{0} {1}% complete", actionString(), intPercent);
                    this.progressBar.Value = intPercent;
                    int elapsedTime = LaunchCommandLine.Instance.ElapsedTime;
                    this.timeRemainingLabel.Text = String.Format("{0}", elapsedTime);
                }
                else
                {
                    ActionLabel.Text = String.Format("{0}", actionString());
                }
            }
            

            labelImageName.Text = currentFilename;
            labelAction.Text = currentAction;
        }

        string actionString()
        {
            switch(action) {
                case Action.Idle: return "Idle";
                case Action.Initalisation: return "Initalising";
                case Action.Analising: return "Analising";
                case Action.Importing: return "Importing";
                case Action.Commplete: return "Commplete";
            }
            return "Idle";
        }

        void OnStatusChanged(LaunchCommandLine.Status param)
        {
            switch(param) {
                case LaunchCommandLine.Status.Running:
                    break;
                case LaunchCommandLine.Status.Completed:
                    break;
                case LaunchCommandLine.Status.Error:
                    break;
            }
        }

        private void buttonPause_Click(object sender, EventArgs e)
        {
            if (running)
            {
                buttonPause.Image = Properties.Resources.pause;
                running = false;
                AsynchronousClient.pauseCommand();
            }
            else
            {
                buttonPause.Image = Properties.Resources.running;
                running = true;
                AsynchronousClient.runCommand();
            }
        }

        private void buttonCancel_Click(object sender, EventArgs e)
        {
            if (canceling == false)
            {
                buttonCancel.Image = Properties.Resources.canceling;
                canceling = true;
                AsynchronousClient.stopCommand();
            }
           
        }
        
    }
}
