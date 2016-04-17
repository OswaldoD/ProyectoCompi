/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectocompi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectocompi.jflex.Lexer;
import proyectocompi.jflex.Token;

/**
 *
 * @author usuario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Main main = new Main();
    }
    
    public Main(){
        visual();
        
    }
    
    public void visual(){
        System.out.println("Compilador de Python");
        
        Scan(fileReader());
        
        
        

    }
    
    public Lexer fileReader(){
      // String path = "/Users/usuario/NetBeansProjects/ProyectoCompi/src/"
        //              + "proyectocompi/file.txt";
       // /Users/usuario/NetBeansProjects/ProyectoCompi/src/proyectocompi
       
       //String path = "C:/Users/esporras/Documents/NetBeansProjects/ProyectoCompi/src/proyectocompi/file.txt";
       String path = Paths.get("").toAbsolutePath().toString() + "/src/proyectocompi/file.txt";
        
       try{
           Reader reader = new BufferedReader(new FileReader(path));
        
           Lexer lexer = new Lexer(reader);
           
           return lexer;
       }
       catch (Exception e){
           Lexer lexer = new Lexer(null);
           
           return lexer;
       }
       
   
       
    }
    public void Scan(Lexer lexer){
       String Resultados = "";
       
       while (true){
           try {
               Token token = lexer.yylex();
               if (token == null){
                   Resultados = Resultados + "FIN\n";
                   System.out.println(Resultados);
                   break;
               }
               switch(token){
                   case ERROR:
                       Resultados = Resultados + "Error, no existe\n";
                       break;     
                   case Variable:
                   case Numero_Entero:
                   case Numero_Long:                       
                   case Numero_Flotante:
                   case Numero_Complejo:
                       Resultados = Resultados + "Token: " + token + " " + lexer.lexeme + "\n";
                       break;
                   default:
                       Resultados = Resultados + "Token: " + token + " "+ lexer.lexeme + "\n";
                       break;
               
                       
               }
           } catch (IOException ex) {
               Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
              // break;
           }
  
           
           
       }
       
    }
     

}
