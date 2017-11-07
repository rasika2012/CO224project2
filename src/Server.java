/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Puwasuru
 */
public class Server {
    public static final int BASE_PORT = 2004;     
    
    private static ServerSocket serverSocket; 
    private static int socketNumber;
    
    
    public Server(int socket) throws IOException { 
	serverSocket = new ServerSocket(socket); 
	socketNumber = socket; 
	
    }
    
    public void server_loop() throws IOException { 
	while(true) { 
	    Socket socket = serverSocket.accept(); 	    
	    WorkerServer woker = new WorkerServer(socket);
            woker.start();
        }
    }
    
    public static void main(String [] args) throws IOException { 
        
        
       GuiRunner Gui=new GuiRunner();
       Gui.start();
        
        System.out.println("Server.main()");
	Server server = new Server(BASE_PORT);
        ItemList itemslist = new ItemList("stocks.csv");
        itemslist.createList();
       
	server.server_loop(); 
        
    }
}
