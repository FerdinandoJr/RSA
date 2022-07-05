/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package apresentacao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
/* Iterative Function to calculate
    // (a^n)%p in O(logy) */
    /*modPow(BigInteger exponent, BigInteger m)
    Returns a BigInteger whose value is (thisexponent mod m).*/
    static BigInteger power(BigInteger a, BigInteger n, BigInteger p)
    {
        // Initialize result
        BigInteger res;
        res = a.modPow(n, p);
        // Update 'a' if 'a' >= p
        //a = a % p;
        return res;
    }
     
    // If n is prime, then always returns true,
    // If n is composite than returns false with
    // high probability Higher value of k increases
    //  probability of correct result.
    static boolean isPrime(BigInteger n, long k){
        
        // Corner cases
        BigInteger two = new BigInteger("2");
        BigInteger three = new BigInteger("3");
        BigInteger four = new BigInteger("4");
        if (n.compareTo(BigInteger.ONE) < 1 || n.compareTo(four) == 0) return false;
        if (n.compareTo(three) < 1) return true;
        
        BigInteger a;
        
        
        while (k > 0){            
            a = two;
        
            BigInteger res = power(a, n.subtract(BigInteger.ONE), n);
            
            //System.out.println(res);
            
            if (res.compareTo(BigInteger.ONE) != 0)
                return false;
    
            // if (power(a, n - 1, n) != 1)
            //     return false;
        
            k--;
        }
        
            return true;
    }
    
    public static int randomNumber(){
        Random r = new Random();
        return r.nextInt(2);
    }
    
    public static BigInteger geraBigInt(int n){
        BigInteger result = new BigInteger("0");
        
        BigInteger aux;
        for(int i = 0; i < n; i++){
            if(i == 0 || i == n - 1){
                aux = new BigInteger("2");
                aux = aux.pow(i);
                result = result.add(aux);

            }else {
                if(randomNumber()==1){
                 aux = new BigInteger("2");    
                 aux = aux.pow(i);
                }else{
                 aux = new BigInteger("0");   
                }
                
                result = result.add(aux);
            }
            
        }
        
        return result;
        
    }

    public static BigInteger getRandom(BigInteger m, BigInteger n){
        BigInteger two = new BigInteger("2");
        BigInteger fim = n.subtract(two);
        
        return null;
    }
    
    
    public static BigInteger getPrimeNumber(int size){
        long k = 10;      
        BigInteger n;
       
        int i = 0;
        do{
            n = geraBigInt(size); 
            //System.out.println(n);
            i++;
        }while(!isPrime(n, k));
            
        //System.out.println(n);
        //System.out.println("Interações ocorridas: "+i);
        
        return n;
    }
    
    public static List<BigInteger> EuclidesEstendido(BigInteger a, BigInteger b){
        
        List<BigInteger> res = new ArrayList<BigInteger>();
        
        BigInteger r = a;        
        BigInteger r1 = b;
        BigInteger u = new BigInteger("1");
        BigInteger v = new BigInteger("0");
        BigInteger u1 = new BigInteger("0");
        BigInteger v1 = new BigInteger("1");
        // variáveis auxiliares para efetuar trocas
        BigInteger rs, us, vs, q;

        while (r1.compareTo(BigInteger.ZERO) != 0){
            q = r.divide(r1); // pega apenas a parte inteira
            rs = r;
            us = u;
            vs = v;
            r = r1;
            u = u1;
            v = v1;
            r1 = rs.subtract(q.multiply(r1));
            u1 = us.subtract(q.multiply(u));
            v1 = vs.subtract(q.multiply(v1));
	    }
        
        //res = {r, u, v};// tais que a*u + b*v = r et r = pgcd (a, b)
        res.add(r);
        res.add(u);
        res.add(v);
	    return res; 
    }
    
    
    public static void main(String args[]) throws FileNotFoundException, IOException
    {  
        
        BigInteger n, d, e;
        BigInteger two = new BigInteger("2");
        List<BigInteger> euclide;
        
        int sizeBit = 1024;
        
        BigInteger p = getPrimeNumber(sizeBit); 
        BigInteger q = getPrimeNumber(sizeBit); 
        
        System.out.println(p);
        System.out.println(q+"\n");
        //N = SEMIPRIMO 
        n = p.multiply(q);
        
        // m = (p-1)(q-1)
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        
        //Escolhe arbitrariamente um e
        e = new BigInteger("1");        
        while(true){
            e = e.add(two);
            euclide = EuclidesEstendido(e, m);
            //e <= m = (p-1)(q-1)
            if(euclide.get(0).compareTo(BigInteger.ONE) == 0 && e.compareTo(m) == -1){
                break;
            }
        }
        

        
        System.out.println("CHAVE PUBLICA("+e+", "+n+")");
         
        // PEGANDO UM CONGRUENTE
        d = euclide.get(1).add(m);
        
        System.out.println("CHAVE PRIVADA("+d+", "+n+")");
       
        
        String path = "C:\\Users\\Angela\\OneDrive\\Documentos\\NetBeansProjects\\mavenproject1\\src\\main\\java\\apresentacao\\";
        
        Path caminhoC = Paths.get(path + "chavePublica.txt");
        String chavePublica = e+"\n"+n;       
        Files.write(caminhoC, chavePublica.getBytes());
        
        Path caminhoD = Paths.get(path + "chavePrivada.txt");        
        String chavePrivada = d+"\n"+n;        
        Files.write(caminhoD, chavePrivada.getBytes());
        

    }

}
