import java.io.*;
import java.net.*;

class UDPClient {
    public static void main(String args[]) throws Exception
    {

	// Create Input Stream
	BufferedReader inFromUser = 
	    new BufferedReader(new InputStreamReader(System.in));

	// Create Client Socket
	DatagramSocket clientSocket = new DatagramSocket();

	// Translate hostname to IP address using DNS
	InetAddress IPAddress = InetAddress.getByName("localhost");

	byte[] sendData = new byte[1024];
	byte[] receiveData = new byte[1024];
	
	System.out.print("ENTER TEXT TO SEND SERVER: ");
	String sentence = inFromUser.readLine();
	sendData = sentence.getBytes();

	// Create datagram with data-to-send.length,IPaddress, port
	DatagramPacket sendPacket =
	    new DatagramPacket(sendData, sendData.length, IPAddress, 9876);

	// Send Datagram to server 
	clientSocket.send(sendPacket);

	// Read Datagram from Server
	DatagramPacket receivePacket =
	    new DatagramPacket(receiveData, receiveData.length);
	
	clientSocket.receive(receivePacket);

	String modifiedSentence = new String(receivePacket.getData());

	System.out.println("FROM SERVER:"+ modifiedSentence );
	clientSocket.close();
    }    
}