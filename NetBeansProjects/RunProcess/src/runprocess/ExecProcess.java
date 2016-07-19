/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runprocess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

/**
 *
 * @author Iain
 */
public class ExecProcess {
    
    static final String processName = "sia.exe";
    static final String processDiscription = "";
    int errCode = 0;
    
    ProcessBuilder pb = new ProcessBuilder(processName, processDiscription);
    
    void process() throws InterruptedException {

        try {
            Process process = pb.start();
            errCode = process.waitFor();

        } catch (IOException ex) {
            Logger.getLogger(ExecProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static String output(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line = null;
            
            while ((line = br.readLine()) != null) {
                sb.append(line + System.getProperty("line.separator"));
            }
        }
        return sb.toString();

    }

    static class IOThreadHandler extends Thread {
        private InputStream inputStream;
        private StringBuilder output = new StringBuilder();

        IOThreadHandler(InputStream inputStream) {
            this.inputStream = inputStream;

        }
        public void run() {
            Scanner br = null;
            try {
                br = new Scanner(new InputStreamReader(inputStream));
                String line = null;
                while (br.hasNextLine()) {
                    line = br.nextLine();
                    output.append(line
                            + System.getProperty("line.separator"));
                }
            } finally {
                br.close();
            }
        }

        public StringBuilder getOutput() {
            return output;
        }
    }

}
