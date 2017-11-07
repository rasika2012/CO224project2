/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Puwasuru
 */
public class WorkerServer extends Thread{

    private Socket connectionSocket;
    public static Map <String,Bidder> bidderList = new HashMap<>(); //All biders
    
    String tempname;
    String tempitem;
    
    public WorkerServer(Socket sock){
        this.connectionSocket = sock;
    }
    
    public void run(){
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(this.connectionSocket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(this.connectionSocket.getOutputStream()));
            
            String line;
            
            //Enter name
            out.println("Enter your Name : ");
            out.flush();
            line = in.readLine();
            tempname = line;
            Bidder bidder1;
            if(bidderList.containsKey(line)){
                //old client
                bidder1 = bidderList.get(line);
            }else{
                //new client
                bidder1 = new Bidder(line);
                bidderList.put(line, bidder1);
            }
            
            out.println("Enter Symbol : ");
            out.flush();
            
            line = in.readLine();
            
            while(!ItemList.stockList.containsKey(line)){
                out.println("Item not available try again");
                out.flush();
                out.println("Enter Symbol : ");
                out.flush();
                line = in.readLine();
            }
            
            tempitem = line;
            
            while(line != null && !line.equals("quit")){
                if(ItemList.stockList.containsKey(tempitem)){
                    out.println("Current price is " + ItemList.stockList.get(tempitem).getPrice());
                    out.flush();
                    out.println("Enter bidding value : ");
                    out.flush();
                    line = in.readLine();
                    while(Float.parseFloat(line) <= ItemList.stockList.get(tempitem).getPrice()){
                        out.println("You have entered a smaller bid. Please enter a higher bid");
                        out.flush();
                        out.println("Enter bidding value : ");
                        out.flush();
                        line = in.readLine();
                    }
                    
                    if(Float.parseFloat(line) > ItemList.stockList.get(tempitem).getPrice()){
                        bidderList.get(tempname).updateList(tempitem, Double.parseDouble(line));
                        bidder1.updateList(tempitem, Double.parseDouble(line));
                        ItemList.stockList.get(tempitem).UpdatePrice(Double.parseDouble(line));
                        ItemList.stockList.get(tempitem).SetBidder(bidder1);
                        out.println("Your bid is successfully added");
                        out.flush();
                        out.println("Current price is " + ItemList.stockList.get(tempitem).getPrice());
                        out.flush();
                    }
                    
                    out.println("If you want to proceed enter 'y' or enter 'quit' ");
                    out.flush();
                    line = in.readLine();
                }
                           
            }
            
            out.println("End");
            out.flush();
            
            connectionSocket.close();
        
        
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
}
