using System;
using System.Net;
using System.Net.Sockets;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;

namespace ProgressDialog
{
    public sealed class UDPReader
    {
        Thread _readThread;
        volatile bool _keepReading;
        private const int listenPort = 64322;
        //begin Singleton pattern
        static readonly UDPReader instance = new UDPReader();
        
        // Explicit static constructor to tell C# compiler
        // not to mark type as beforefieldinit
        static UDPReader()
        {
        }

        UDPReader()
        {
            _readThread = null;
            _keepReading = false;
        }

        public static UDPReader Instance
        {
            get
            {
                return instance;
            }
        }

        public delegate void EventHandler(string param);
        public EventHandler StatusChanged;
        public EventHandler DataReceived;
        public void Open()
        {
            // Init udp
            if (_keepReading == false)
            {
                StartReading();
            }
            
        }
        private void StartReading()
        {
            if (!_keepReading)
            {
                _keepReading = true;
                _readThread = new Thread(ReadUDP);
                _readThread.Start();
            }
        }

        public void Close()
        {
            if (_keepReading)
            {
                _keepReading = false;
                _readThread = null;
            }
        }
        private void ReadUDP()
        {
            //bool done = false;
            UdpClient listener = new UdpClient(listenPort);
            IPEndPoint groupEP = new IPEndPoint(IPAddress.Any, listenPort);
            string received_data;
            byte[] receive_byte_array;
            try
            {
                while (_keepReading)
                {
                    //Console.WriteLine("Waiting for broadcast");
                    // this is the line of code that receives the broadcase message.
                    // It calls the receive function from the object listener (class UdpClient)
                    // It passes to listener the end point groupEP.
                    // It puts the data from the broadcast message into the byte array 
                    // named received_byte_array.
                    // I don't know why this uses the class UdpClient and IPEndPoint like this. 
                    // Contrast this with the talker code. It does not pass by reference.
                    // Note that this is a synchronous or blocking call.
                    receive_byte_array = listener.Receive(ref groupEP);
                    //Console.WriteLine("Received a broadcast from {0}", groupEP.ToString());
                    received_data = Encoding.ASCII.GetString(receive_byte_array, 0, receive_byte_array.Length);
                    //Console.WriteLine("data follows \n{0}\n\n", received_data);
                    DataReceived(received_data + "\n");
                    //Console.WriteLine("{0}", received_data);
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.ToString());
            }
            listener.Close();
            return;

        }
    }
}
