
import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Puwasuru
 */
class Bidder {
    private String name;
    private Map <String,Double>  bidList; 
    
    public Bidder(String name){
        this.name = name;
        this.bidList = new HashMap<>();
    }
    
    
    public String getName(){
        return this.name;
    }
    
    public Map getBidList(){
        return this.bidList;
    }
    
    public void updateList(String symbol,Double value){
        bidList.put(symbol, value);
    }
}
