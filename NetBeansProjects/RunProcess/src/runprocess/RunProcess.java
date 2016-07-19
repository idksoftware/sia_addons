/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runprocess;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Iain
 */
public class RunProcess {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ExecProcess execProcess = new ExecProcess();
        try {
            execProcess.process();
        } catch (InterruptedException ex) {
            Logger.getLogger(RunProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
