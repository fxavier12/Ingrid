/*
 * Classe Host
 * Informacoes sobre sistemas 
 * Sistema Operacional / JVM  / Memoria
 */
package edu.br.utfpr.projectingrid.Core;

import java.io.Serializable;

/**
 *
 * @author Francisco Xavier
 */
public class Host implements Serializable{
    private static final long serialVersionUID = 1L;
    private String sistemaOperacional;
    private String versaoDoSistema;
    private String arquitetura;
    private int nucleos;
    private long memoriaDisponivel;//em bytes
    private long memoriaInstalada;//em bytes
    private long memoriaJvm;//memoria disponivel para Java Virtual Machine

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public String getVersaoDoSistema() {
        return versaoDoSistema;
    }

    public String getArquitetura() {
        return arquitetura;
    }

    public int getNucleos() {
        return nucleos;
    }

    public long getMemoriaDisponivel() {
        return memoriaDisponivel;
    }

    public long getMemoriaInstalada() {
        return memoriaInstalada;
    }

    public long getMemoriaJvm() {
        return memoriaJvm;
    }
    
    public Host(){
        updateInfo();
    }
    
    private void updateInfo(){
        sistemaOperacional = System.getProperty("os.name");
        versaoDoSistema = System.getProperty("os.version");
        arquitetura = System.getProperty("os.arch");
        nucleos =  Runtime.getRuntime().availableProcessors();
        memoriaDisponivel = Runtime.getRuntime().freeMemory();
        memoriaInstalada = Runtime.getRuntime().maxMemory();
        memoriaJvm = Runtime.getRuntime().totalMemory();
    }
    
     @Override
    public String toString() {
        return "Host{" + "sistemaOperacional=" + sistemaOperacional + ", versaoDoSistema=" + versaoDoSistema + ", arquitetura=" + arquitetura + ", nucleos=" + nucleos + ", memoriaDisponivel=" + memoriaDisponivel + ", memoriaInstalada=" + memoriaInstalada + ", memoriaJvm=" + memoriaJvm + '}';
    }
}
