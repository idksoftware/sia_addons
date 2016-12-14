package Import;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPListener implements Runnable {
    // get a datagram socket
	DatagramSocket socket = null;
	InetAddress address = null;
	int port = 0;
	static final int IDLE_TIME = 1000;
	
	private boolean running = false;
	
	public UDPListener(String addr, int port) {
		initialise(addr, port);
	}
	public UDPListener(int port) {
		initialise("localhost", port);
	}
	
	public UDPListener() {
		initialise("localhost", 64321);
	}


	
	public void initialise(String addr, int port) {
		// send request
		byte[] buf = new byte[256];
		try {
			address = InetAddress.getByName(addr);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void send(byte[] buf) {
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
    void listen() {
    	byte[] buf = new byte[256];
    	DatagramPacket packet = new DatagramPacket(buf, buf.length);
    	try {
			socket.receive(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	// display response
    	String received = new String(packet.getData(), 0, packet.getLength());
    	System.out.println("rep: " + received);

    	socket.close();
    }
	@Override
	public void run() {
		while (true) {
			if (running) {
				listen();
			} else {
				try {
					Thread.sleep(IDLE_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	void start() {
		running = true;
	}
	void stop() {
		running = false;
	}
	
	void pause() {
		running = false;
	}
}


/*
import java.io.*;
import java.net.*;
import java.util.*;
 
public class QuoteClient {
    public static void main(String[] args) throws IOException {
 
        if (args.length != 1) {
             System.out.println("Usage: java QuoteClient <hostname>");
             return;
        }
 
            // get a datagram socket
        DatagramSocket socket = new DatagramSocket();
 
            // send request
        byte[] buf = new byte[256];
        InetAddress address = InetAddress.getByName(args[0]);
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);
     
            // get response
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
 
        // display response
        String received = new String(packet.getData(), 0, packet.getLength());
        System.out.println("Quote of the Moment: " + received);
     
        socket.close();
    }
}

*/