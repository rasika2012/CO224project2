
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rasika
 */
public class GuiRunner extends Thread {
    @Override
    public void run(){
        StatusPanel viewer=new StatusPanel();
        viewer.intCompoment();
        viewer.setVisible(true);
        while(true){
            viewer.genarateTable();
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(GuiRunner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
}
