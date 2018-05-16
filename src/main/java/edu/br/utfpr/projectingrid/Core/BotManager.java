/*
 * Classe responsavel por gerenciar a thread de conexao de cada Bot 
 *
 */
package edu.br.utfpr.projectingrid.Core;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Francisco Xavier
 */
public class BotManager extends Thread{
    private Socket socket;
    
    public BotManager(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			Object recebido = null;
			
			do {
                                try{
                                    recebido = in.readObject();
                                    System.out.println(recebido.toString());
                                    out.writeObject(recebido);
                                }catch(EOFException err){
                                    
                                }
								
			} while (true);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
