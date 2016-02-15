using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace IDK.Gui
{
    public partial class MakeAllSameForm : Form
    {
        public int SunMin = 0;
        public int SunHour = 0;
        public int SatMin = 0;
        public int SatHour = 0;
        public int FriMin = 0;
        public int FriHour = 0;
        public int ThurMin = 0;
        public int ThurHour = 0;
        public int WedsMin = 0;
        public int WedsHour = 0;
        public int TuesMin = 0;
        public int TuesHour = 0;
        public int MonMin = 0;
        public int MonHour = 0;

       
        public MakeAllSameForm()
        {
            InitializeComponent();

           

        }

        private void comboBoxDays_SelectedValueChanged(object sender, EventArgs e)
        {
            SelectedItem(comboBoxDays.SelectedIndex);
        }

        private void SelectedItem(int idx)
        {
            switch (idx)
            {
                case 6:
                    numericUpDownMin.Value = (decimal)SunMin;
                    numericUpDownHour.Value = (decimal)SunHour;
                    break;
                case 5:
                    numericUpDownMin.Value = (decimal)SatMin;
                    numericUpDownHour.Value = SatHour;
                    break;
                case 4:
                    numericUpDownMin.Value = (decimal)FriMin;
                    numericUpDownHour.Value = FriHour;
                    break;
                case 3:
                    numericUpDownMin.Value = (decimal)ThurMin;
                    numericUpDownHour.Value = ThurHour;
                    break;
                case 2:
                    numericUpDownMin.Value = (decimal)WedsMin;
                    numericUpDownHour.Value = WedsHour;
                    break;
                case 1:
                    numericUpDownMin.Value = (decimal)TuesMin;
                    numericUpDownHour.Value = TuesHour;
                    break;
                case 0:
                    numericUpDownMin.Value = (decimal)MonMin;
                    numericUpDownHour.Value = (decimal)MonHour;
                    break;
            }
        }

        public int SameMin { get { return (int)this.numericUpDownMin.Value; } }
        public int SameHour { get { return (int)this.numericUpDownHour.Value; } }

        private void MakeAllSameForm_Load(object sender, EventArgs e)
        {
            comboBoxDays.Text = comboBoxDays.Items[0].ToString();
            SelectedItem(0);
        }
           
    }
}