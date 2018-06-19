/*
 * Classe responsavel por gerenciar a thread de conexao de cada Bot 
 *
 */
package edu.br.utfpr.projectingrid.Core;

import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Francisco Xavier
 */
public class BotManager extends Thread{
    private Socket socket;

    public Host getBotHost() {
        return botHost;
    }

    public boolean isComandoPendente() {
        return comandoPendente;
    }

    public Comando getComando() {
        return comando;
    }
    private Host botHost;
    private boolean comandoPendente;
    private Comando comando;
    
    public BotManager(Socket socket){
        this.socket = socket;
        this.botHost = null;
        this.comandoPendente = false;
       
    }
    @Override
    public void run() {
		try {

                       ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		       ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                        do {
                            
                            //recebendo informacoes do Host
                            if(botHost==null){
                                out.writeObject(new Comando("HostInfo"));
                                while(botHost == null){
                                    botHost= (Host)in.readObject();
                                }
                                System.out.println(botHost.toString());
                            }else{
                            //executando comandos (se estiver pendente)
                                if(comandoPendente){
                                    out.writeObject(comando);
                                    Object resposta = null;
                                    System.out.println("enviando comando");
                                    comandoPendente = false;
                                }
                                    
                            }
                            
                            Thread.sleep(500);
                            
                            /*
                            try{
                                
                                
                                
                                if(cond){
                                    System.out.print("["+usuario.getHostAddress()+"]>> ");
                                    retorno = digite.nextLine();
                                  //  System.out.println(recebido.toString());
                                    //int length = in.read(buffer);
                                   // System.out.println(new String(buffer,0,0,length));
                                    //out.write(buffer,0,length);

                                    out.writeObject(retorno);
                                    cond = false;
                                }else{
                                    System.out.print("Recebendo Objects\n");
                                    try{
                                        vet = retorno.split(" ");
                                        if(vet[0].equals("cop") && vet != null){
                                            try{
                                                File fs = new File(vet[2]);
                                            
                                                byte recebe[] = (byte[]) in.readObject();
                                                FileOutputStream fos = new FileOutputStream(fs);
                                                fos.write(recebe);
                                                System.out.println("arquivo recebido..");
                                            }catch(Exception ex){
                                                System.out.println("Erro ao converter .."+ ex);
                                            }
                                        }else{
                                            recebido = (Object) in.readObject();
                                            System.out.println(recebido.toString());
                                        }
                                    }catch(Exception ex){
                                        System.out.println("Erro ao recer object "+ex);
                                        
                                    }
                                    cond = true;
                                }
                               // out.flush();
                                

                            }catch(EOFException err){
                                System.out.println("Erro na conexao "+ err);
                            }
                            */
								
			} while (true);
		} catch (IOException e) {
                    System.err.println("Erro ao execuar comandos "+e);
		} catch (Exception e) {
                    System.err.println("Erro ao execuar comandos "+e);
		}
	}
    
    public void enviarComando(Comando comando){
        System.out.println("enviar comando");
        this.comando = comando;
        this.comandoPendente = true;
    }
}
