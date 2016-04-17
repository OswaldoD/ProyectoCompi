/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectocompi;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectocompi.jflex.Lexer;
import proyectocompi.jflex.Token;
import proyectocompi.jflex.TToken;

/**
 *
 * @author usuario
 */
public class Main {

    private ArrayList<TToken> Tokens;
    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        Main main = new Main();
    }
    
    public Main(){
        Tokens = new ArrayList<TToken>();
        visual();
        
    }
    
    public void visual(){
        System.out.println("***** Compilador de Python *****");
        System.out.println("*** ¿Qué desea hacer?: ");
        System.out.println("** 1- Comprobar palabras reservadas ");
        System.out.println("** 2- Comprobar operadores");
        System.out.println("** 3- Comprobar literales números");
        System.out.println("** 4- Comprobar literales string");
        System.out.println("** 5- Comprobar separadores");
        System.out.println("** 6- Comprobar contenedores");
        System.out.println("** 7- Comprobar identificadores");
        System.out.println("** 8- Prueba completa");
        System.out.println("** 9- Ejecutar archivo .mypy ");
        System.out.print("**Eliga una opción: ");
        
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
       // System.out.println(s);
        //int i = scan.nextInt();
       // Scan(fileReader());
        
        
        

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
                  // System.out.println(Resultados);
                   break;
               }
               switch(token){
                   case ERROR:
                       Resultados = Resultados + "Error, no existe\n";
                       break;     
                   case Numero_Entero:
                   case Numero_Long:                       
                   case Numero_Flotante:
                   case Numero_Complejo:
                    //   Resultados = Resultados + "Token: " + token + " " + lexer.lexeme + "\n";
                      // break;
                   default:
                       // almacenamiento de tokens
                       TToken token_resultado = new TToken(lexer.lexeme, token, 1);
                      // System.out.println(token_resultado.getLexeme());
                       Tokens.add(token_resultado);
                     //  Resultados = Resultados + "Token: " + token + " "+ lexer.lexeme + " linea: " +lexer.linea() +"\n";
                       break;
               
                       
               }
           } catch (IOException ex) {
               Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
              // break;
           }
  
           
           
       }
       System.out.println(Tokens.size()); // cantidad de tokens
       /*
       for(int i = 0; i < Tokens.size(); i++){
           System.out.println(Tokens.get(i).getLexeme());

       }*/
       
    }
     

}
