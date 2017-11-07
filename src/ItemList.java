
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
public class ItemList {
    
    public static Map<String,Items> stockList = new HashMap<>();
    public int symbolNo=-1,nameNo=-1,priceNo=-1;
    BufferedReader br;
    String filename;
    
    public ItemList(String filename) {
        this.filename = filename; 
    }
    
     public void createList(){
            
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

        	String sCurrentLine;

                if((sCurrentLine = br.readLine()) != null){
                    String words[] = sCurrentLine.split(",");

                    for(int i =0 ;i<words.length;i++){
                        if(words[i].equals("Symbol")){
                            symbolNo = i;
                        }else if(words[i].equals(("Security Name"))){
                            nameNo = i;
                        }else if(words[i].equals("Price")){
                            priceNo = i;
                        }
                    
                    }
                }
                
                if(symbolNo==-1||nameNo==-1||priceNo==-1){
                    System.out.println(symbolNo+" "+nameNo+" "+priceNo);
                    System.out.println("CSV file is not in correct format");
                    return ;
                    
                }


		while ((sCurrentLine = br.readLine()) != null) {
				
                    String words[] = sCurrentLine.split(",");
                    
                    if(words[priceNo].equals("N") || words[priceNo].equals("D")){
                        words[priceNo] = "0";
                    }
                    stockList.put(words[symbolNo],new Items(words[symbolNo],words[nameNo],Double.parseDouble(words[priceNo])));
		
                }

		} catch (IOException e) {
			e.printStackTrace();
		}
        return;
    }
    
    
}
