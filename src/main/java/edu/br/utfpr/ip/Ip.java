/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.br.utfpr.ip;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author sirnande lima
 */
public class Ip {
    
    private String ip;
    
     public String connectionsIps(String ip, int porta){
		//String vetor[] = ip.split("*");
	
		String ips = ip;
		String test = "";
		for(int i = 1; i < 254; i++){
			test = ips+i;
			try{
				if(testConnect(test)){
                                    if( ports(test,porta)){
                                        return test;
                                    }
                                        
                                }
			}catch(Exception ex){
				System.out.println("******************* error");
			}
		}

                return null;
	}
         
        public  boolean testConnect(String ip) throws Exception{
	     if(InetAddress.getByName(ip).isReachable(100)){
	    	 System.out.println(ip+"\t\tAtivo"); 
                return true;
             }else{
			 System.out.println(ip+"\t\tInativo");
                         return false;
             }
//	     System.out.println("\nFim do teste");
	}
         public  boolean ports(String ip, int porta) {
    		try {
        		Socket socket = new Socket();
        		socket.connect(new InetSocketAddress(ip, porta), 100);
        		socket.close();
        		return true;
    		} catch (Exception ex) {
        		return false;
    		}
	}
}
