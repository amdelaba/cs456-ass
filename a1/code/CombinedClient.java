import java.io.*;
import java.net.*;
import java.lang.*;

class CombinedClient{

    public static void main(String argv[])throws Exception
    {

	// Command Line inputs: <server_address>,<n_port>,<msg>

	if (argv.length !=3){
	    System.err.println("Usage: client <server_address> <n_port> <msg>");
	    System.exit(0);
	}

	String serverAddress = argv[0];
	int n_port = Integer.parseInt(argv[1]);
	String msg = argv[2];
	

	// Print arguments
	System.out.println("Server Address: " + serverAddress);
	System.out.println("Negotiation Port: " + n_port );
	System.out.println("Messagex: " + msg);

	
	String integerToSend = "13";
	String r_port = new String();


	// Create client socket, connect to server
	Socket clientSocket = new Socket(serverAddress, n_port);

	// Create output stream attached to socket
	DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

	// Create input stream attached to socket
	BufferedReader inFromServer = 
	    new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	
	// Send line to Server
	outToServer.writeBytes(integerToSend+'\n');
	
	System.out.println("IntegerToSend: " + integerToSend );
	

	// Read Line from Server
	r_port = inFromServer.readLine();
	
	System.out.println("R_PORT received: " + r_port );

	clientSocket.close();

	
	/// UDP

	String sentence = msg;
	int randomPort = Integer.parseInt(r_port);

	
	// Create Client Socket
	DatagramSocket clientSocket2 = new DatagramSocket();

	// Translate hostname to IP address using DNS
	InetAddress IPAddress = InetAddress.getByName(serverAddress);

	byte[] sendData = new byte[1024];
	byte[] receiveData = new byte[1024];

	
	sendData = sentence.getBytes();

	// Create datagram with data-to-send.length,IPaddress, port
	DatagramPacket sendPacket =
	    new DatagramPacket(sendData, sendData.length, IPAddress, randomPort);

	// Send Datagram to server 
	clientSocket2.send(sendPacket);

	System.out.println("Sentence sent: " + sentence);


	// Read Datagram from Server
	DatagramPacket receivePacket =
	    new DatagramPacket(receiveData, receiveData.length);


	clientSocket2.receive(receivePacket);

	String modifiedSentence = new String(receivePacket.getData());

	System.out.println("FROM SERVER:"+ modifiedSentence );
	clientSocket2.close();
	
    }
}