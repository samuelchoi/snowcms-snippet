package org.snow.snippet.log4j2;

public class FlowTracingApp {
	
    public static void main( String[] args ) {
    	
        TestService service = new TestService();
        service.retrieveMessage();
        service.retrieveMessage();
        service.exampleException();
    }
}