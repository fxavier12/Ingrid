/*
 * Classe responsavel em criar a conexao com o botMaster
 * Executa comandos enviados pelo BotMaster
 */
package edu.br.utfpr.projectingrid;

import edu.br.utfpr.projectingrid.Setup.Setup;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Francisco Xavier 14/05/2018
 */
public class BotSlave {
    public static void main(String argv[]) throws Exception {
        Object sentence;
        String modifiedSentence;
        try (Socket clientSocket = new Socket(Setup.ip, Setup.porta)) {

             ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

             sentence = "teste";
             out.writeObject(sentence);

             System.out.println("FROM SERVER: "+in.readObject().toString());

         }catch(Exception err){
             err.printStackTrace();
         }
    }
}
