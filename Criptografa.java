package apresentacao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Criptografa {
    
    public static void main(String args[]) throws FileNotFoundException, IOException{
        String path = "C:\\Users\\Angela\\OneDrive\\Documentos\\NetBeansProjects\\mavenproject1\\src\\main\\java\\apresentacao\\";
        
        String msgCriptografada;
        String msg;

        /* PEGA A MENSAGEM */
        BufferedReader bf = new BufferedReader(new FileReader(path+"file.txt"));
        String s = "";
        String x;
        while ((x = bf.readLine()) != null){
            s += x;
        }
        msg = s;
        
        
        /* PEGA A CHAVE PUBLICA */
        bf = new BufferedReader(new FileReader(path+"chavePublica.txt"));        
        int i = 0;
        BigInteger e = null;
        BigInteger n = null;         
        while ((x = bf.readLine()) != null){                        
            if(i==0){
                e = new BigInteger(x);
            }else{
                n = new BigInteger(x);
            }
            i++;
        }
        //getBytes tranforma a string em um byte array, declaramos um BigInteger com esse array, elevamos a 'e'
        //e fazemos a operação modular com n
        msgCriptografada = new BigInteger(msg.getBytes()).modPow(e, n).toString();
        
        Path caminhoD  = Paths.get(path + "msgCriptografada.txt");
        Files.write(caminhoD, msgCriptografada.getBytes());
        //System.out.println(msgDescriptografada);
    }
    
    
}
