import java.io.*;
import java.net.*;
import java.lang.*;

class CombinedServer{

    public static void main(String argv[])throws Exception
    {

	if (argv.length !=1){
	    System.err.println("Usage: server <n_port>");
	    System.exit(0);
	}
	int n_port = Integer.parseInt(argv[0]);

	// Print arguments
	System.out.println("Negotiation Port: " + n_port );

	String clientInteger;
	String r_port = "1026"; // Randomize later
	

	// Create datagram socket at port r_port
	DatagramSocket serverSocket = new DatagramSocket(Integer.parseInt(r_port));
	
	byte[] receiveData = new byte[1024];
	byte[] sendData = new byte[1024];
   



	// Create Socket at port 6789
	ServerSocket welcomeSocket = new ServerSocket(n_port);
	while(true){

	    // Wait, on welcoming socket for contact by client
	    Socket connectionSocket = welcomeSocket.accept();

	    // Create input stream, attached to socket
	    BufferedReader inFromClient = 
		new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); 

	    // Create output stream, attached to socket
	    DataOutputStream outToClient =
		new DataOutputStream(connectionSocket.getOutputStream());

	    // Read in line from socket
	    clientInteger = inFromClient.readLine();

	    System.out.println("Integer received: " + clientInteger );

	    // Write out line to socket
	    outToClient.writeBytes(r_port+'\n');

	    System.out.println("R_PORT sent: " + r_port );

	
	    /// UDP
	    
	    // Create space for received Datagram
	    DatagramPacket receivePacket =
		new DatagramPacket(receiveData,receiveData.length);
	    
	    // Receieve Datagram
	    serverSocket.receive(receivePacket);

	    
	    String sentence = new String(receivePacket.getData());

	    System.out.println("Sentence received: " + sentence);
	    
	    // Get IP addr, port#, of sender
	    InetAddress IPAddress = receivePacket.getAddress();
	    
	    int port = receivePacket.getPort();

	    String reversedSentence = new StringBuffer(sentence).reverse().toString();

	    sendData = reversedSentence.getBytes();

	    // Create Datagram to send to client
	    DatagramPacket sendPacket =
		new DatagramPacket(sendData, sendData.length, IPAddress, port);
	    
	    // Write out datagram to socket
	    serverSocket.send(sendPacket);

	    System.out.println("Reversed Sentence sent: " + reversedSentence);
	    

	}// End of loop. loop back and wait for another connection

    }

}