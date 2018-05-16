/*
 * Classe responsavel em criar a conexao com o botMaster
 * Executa comandos enviados pelo BotMaster
 */
package edu.br.utfpr.projectingrid.Core;

import edu.br.utfpr.projectingrid.Setup.Setup;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Francisco Xavier 14/05/2018
 */
public class BotSlave {
    
    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    
    
    public static void main(String argv[]) throws Exception {
        Object sentence;
        String modifiedSentence;
        try (Socket clientSocket = new Socket(Setup.ip, Setup.porta)) {

             out = new ObjectOutputStream(clientSocket.getOutputStream());
             in = new ObjectInputStream(clientSocket.getInputStream());
            
             while(true){
                 try{
                     executarComando(in.readObject().toString());
                 }catch(EOFException err){
                     
                 }
             }
 

         }catch(Exception err){
             err.printStackTrace();
         }
    }
    
    public static void executarComando(String comando){
        System.out.println("--"+comando);
    }
}
