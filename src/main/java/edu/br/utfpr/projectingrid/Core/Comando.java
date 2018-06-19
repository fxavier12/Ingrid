/*
 * Classe comando
 * representa um comando a ser executado no slave 
 * retorna resultado para ser interpretado pelo Master
 */
package edu.br.utfpr.projectingrid.Core;

import java.io.Serializable;

/**
 *
 * @author Francisco Xavier
 */
public class Comando implements Serializable{
    private static final long serialVersionUID = 1L;
    private String comando;
    
    public Comando(String comando){
        this.comando = comando;
    }
    
    public Comando(){
        
    }
    public Object executar(Host host){
        if(comando.equals("HostInfo")){
            return host;
        }else{
            return null;
        }
    }
    
    
}
