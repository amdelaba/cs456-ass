import java.io.*;
import java.net.*;

class TCPClient{

    public static void main(String argv[])throws Exception
    {
	String sentence;
	String modifiedSentence;
	
	// Create input Stream
	BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

	// Create client socket, connect to server
	Socket clientSocket = new Socket("127.0.0.1",6789);

	// Create output stream attached to socket
	DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
	
	// Create input stream attached to socket
	BufferedReader inFromServer = 
	    new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

	System.out.print("ENTER TEXT TO SEND TO SERVER:");

	sentence = inFromUser.readLine();

	// Send line to Server
	outToServer.writeBytes(sentence+'\n');

	// Read Line from Server
	modifiedSentence = inFromServer.readLine();
	
	System.out.println("FROM SERVER: " + modifiedSentence);

	clientSocket.close();
		

    }


}