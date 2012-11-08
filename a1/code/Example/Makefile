JCC = javac

JFLAGS = -g

default: TCPClient TCPServer UDPClient UDPServer

#name dependencies
TCPClient: TCPClient.java
	$(JCC) $(JFLAGS) TCPClient.java		
TCPServer: TCPServer.java
	$(JCC) $(JFLAGS) TCPServer.java		
UDPClient: UDPClient.java
	$(JCC) $(JFLAGS) UDPClient.java		
UDPServer: UDPServer.java
	$(JCC) $(JFLAGS) UDPServer.java		

clean:
	rm *.class *~ *#* 