JCC = javac

JFLAGS =  #-g

default: CombinedClient CombinedServer

#name dependencies
CombinedClient: CombinedClient.java
	$(JCC) $(JFLAGS) CombinedClient.java		
CombinedServer: CombinedServer.java
	$(JCC) $(JFLAGS) CombinedServer.java		

clean:
	rm *.class *~ *#* 