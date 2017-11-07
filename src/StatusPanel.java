
import java.awt.Panel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rasika
 */
public class StatusPanel extends JFrame {
    private javax.swing.JScrollPane Panel;
    private javax.swing.JTable Table;
    
    
    
    public void intCompoment(){
        Panel = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable(){
            public boolean isCellEditable(int row,int col){
                return false;
            }
        };
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        Table.setRowHeight(30);
        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, "hellow", "hi", null}
            },
            new String [] {
                "Item Name", "Symbol", "Current Price", "Bidder"
            }
        ));
        //Table.setTableHeader(new JTableHeader());
        //Table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Table.enableInputMethods(false);
        Panel.setViewportView(Table);
        Table.getColumnModel().getColumn(2).setWidth(100);
        this.add(Panel);
        genarateTable();
        this.setVisible(true);
        this.setSize(600,700);
    }
    public void genarateTable(){
        
        String [] searchList={"FB", "VRTU",
"MSFT", "GOOGL", "YHOO", "XLNX", "TSLA", "TXN"};
        String [][] tableData=new String[searchList.length][4];
        for(int i=0;i<searchList.length;i++){
           //System.out.println(ItemList.stockList.isEmpty());
            
            //tableData[i]=new String []{"f","d
            if(ItemList.stockList.containsKey(searchList[i]) && ItemList.stockList.get(searchList[i]).GetBidder()!=null){
                tableData[i]= new String [] {ItemList.stockList.get(searchList[i]).getSymbol(),
                        ItemList.stockList.get(searchList[i]).getName(),
                        String.valueOf(ItemList.stockList.get(searchList[i]).getPrice())  , 
                        ItemList.stockList.get(searchList[i]).GetBidder().getName()     };        
            }else if(ItemList.stockList.containsKey(searchList[i])){
                tableData[i]= new String [] {ItemList.stockList.get(searchList[i]).getSymbol(),
                        ItemList.stockList.get(searchList[i]).getName(),
                        String.valueOf(ItemList.stockList.get(searchList[i]).getPrice())  , 
                        "-" };      
            }
        }
        
        this.Table.setModel(new javax.swing.table.DefaultTableModel(
            tableData,
            new String [] {
                "Item Name", "Symbol", "Current Price", "Bidder"
            }
        ));
    }
    
}
