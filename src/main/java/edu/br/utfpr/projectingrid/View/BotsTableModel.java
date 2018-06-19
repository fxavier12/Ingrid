/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.br.utfpr.projectingrid.View;

import edu.br.utfpr.projectingrid.Core.BotManager;
import edu.br.utfpr.projectingrid.Core.Host;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 2Dverse
 */
public class BotsTableModel extends AbstractTableModel{

    public ArrayList<BotManager> bots ;
    private String[] colunas = {"Id","Sistema","Memoria JVM","Nucleos"};    

    public BotsTableModel(ArrayList<BotManager> bots){
        this.bots = bots;
    }
    
    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    @Override
    public int getRowCount() {
        return bots.size();
    }

    @Override
    public int getColumnCount() {
        return  colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        BotManager bot = bots.get(linha);
       switch (coluna) {
            case 0:
                return linha;
            case 1:
                return bot.getBotHost().getSistemaOperacional();
            case 2:
                return ""+bytesToMegas(bot.getBotHost().getMemoriaDisponivel())+"MB / "
                +bytesToMegas(bot.getBotHost().getMemoriaInstalada())+"MB";
            case 3:
                return bot.getBotHost().getNucleos();
        }        
        return null;  
    }
    
    private long bytesToMegas(long bytes){
        long kb = bytes/1024;
        long mb = kb/1024;
        return mb;
        
        
    }
}
