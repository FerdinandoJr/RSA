package apresentacao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Descriptografa {
    
      public static void main(String args[]) throws FileNotFoundException, IOException{
         
        String path = "C:\\Users\\Angela\\OneDrive\\Documentos\\NetBeansProjects\\mavenproject1\\src\\main\\java\\apresentacao\\";
        String msgDescriptografada;
        String msg;

        /* PEGA A MENSAGEM CRIPTOGRAFADA */
        BufferedReader bf = new BufferedReader(new FileReader(path+"msgCriptografada.txt"));
        String s = "";
        String x;
        while ((x = bf.readLine()) != null){
            s += x;
        }
        msg = s;
        
        
        /* PEGA AS CHAVE PRIVADAS */
        bf = new BufferedReader(new FileReader(path+"chavePrivada.txt"));        
        int i = 0;
        BigInteger d = null;
        BigInteger n = null;         
        while ((x = bf.readLine()) != null){                        
            if(i==0){
                d = new BigInteger(x);
            }else{
                n = new BigInteger(x);
            }
            
            i++;
        }
        
        //System.out.println(msg);
        
        msgDescriptografada = new String(new BigInteger(msg).modPow(d, n).toByteArray());
        
        Path caminhoD  = Paths.get(path + "msgDescriptografada.txt");
        Files.write(caminhoD, msgDescriptografada.getBytes());
        //System.out.println(msgDescriptografada);
    }
    
}
