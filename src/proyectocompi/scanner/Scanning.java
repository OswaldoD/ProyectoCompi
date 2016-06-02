/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectocompi.scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.DefaultSymbolFactory;
import java_cup.runtime.Symbol;
import java_cup.runtime.SymbolFactory;
//import java_cup.sym;
import proyectocompi.jflex.Lexer;
import proyectocompi.jflex.Token;
import proyectocompi.jflex.TToken;
import proyectocompi.jflex.ListasTokens;
import proyectocompi.parser.sym;
//import proyectocompi.parser.sym;
/**
 *
 * @author usuario
 */
public class Scanning{

    private ArrayList<TToken> Tokens;
    ListasTokens listadoTokens = new ListasTokens();
    public String path;
    private Lexer lexer;
    private final SymbolFactory sf = new DefaultSymbolFactory();
    
    public Scanning(String path){
        
        Tokens = new ArrayList<TToken>();
        this.path= path;
    //    visual();
        
    }
    public void init(){
        this.lexer = fileReader(); // objeto lexer para el scanner
       // nextToken(); //retorna el siguiente token
        
      //  visualizarListasTokens();
    }
    
    public Lexer fileReader(){        
       try{
           Reader reader = new BufferedReader(new FileReader(this.path));
        
           Lexer lexer = new Lexer(reader);
           
           return lexer;
       }
       catch (Exception e){
           Lexer lexer = new Lexer(null);
           
           return lexer;
       }
    }
    public Symbol nextToken(){
        /*
        este es siguiente token nextToken
        
        Ahora ya no va a guardar una lista de tokens, va a retornar uno a uno-
        
        como retornar los tokens para que el parser los use?
        
        */
       String Resultados = "";
       
       while (true){
           try {
               Token token = this.lexer.yylex();
               if (token == null){
                  // Resultados = Resultados + "FIN\n";
                  // System.out.println(Resultados);
                  return sf.newSymbol("EOF", sym.EOF);
                  // break;
               }
               switch(token){
                   case ERROR:
                       Resultados = Resultados + "Error, no existe\n";
                       //enviar a la lista de errores
                       TToken token_error = new TToken(lexer.lexeme, token, Integer.toString(lexer.linea()));
                       System.out.println("> Error encontrado: " + token_error.getLexeme() + " en la linea " + token_error.getNumero_Linea());
                       return sf.newSymbol("Error", sym.error);
                      // listadoTokens.insertarTokenError(token_error);
                      // break;     
                   case Numero_Entero:
                   case Numero_Long:                       
                   case Numero_Flotante:
                       /*
                   case Identificador:
                       System.out.println("> Token encontrado -> " + lexer.lexeme + " de la familia -> ID" );

                       return sf.newSymbol(lexer.lexeme, sym.identificador);*/

                      // break;
                   default:                       
                       // almacenamiento de tokens
                       TToken token_resultado = new TToken(lexer.lexeme, token, Integer.toString(lexer.linea())); 
                       System.out.println("> Token encontrado -> " + token_resultado.getLexeme() + " de la familia -> " + token_resultado.getTipo_Token());
                       return sf.newSymbol(lexer.lexeme, sym.def);
                      // break;                                    
               }
           } catch (IOException ex) {
               Logger.getLogger(Scanning.class.getName()).log(Level.SEVERE, null, ex);
              // break;
              return sf.newSymbol("error archivo", sym.error);
           }
       }   
    }
    /*Función encargada de mostrar el menú que le permite al usuario elegir cual lista de resultados observar:
      1)tokens eontrados
      2)los errores léxicos encontrados.
      3)Regresar al menú principal
      4)Salir del programa*/
    public void visualizarListasTokens(){
        System.out.println("***************************************");
        System.out.println("***** Menú de Imprensión Listas de Tokens *****");
        System.out.println("***************************************");        
        System.out.println("*** ¿Qué desea hacer?: ");
        System.out.println("** 1- Imprimir Tokens Encontrados ");
        System.out.println("** 2- Imprimir Errores Léxicos Encontrados");
        System.out.println("** 4- Salir");
        System.out.print("**Eliga una opción: ");
        int seleccion;
        Scanner scan = new Scanner(System.in);
        seleccion = scan.nextInt();
        switch(seleccion){
            case 1:
                listadoTokens.imprimirListatoTotalTokens();
                break;
            case 2:
                listadoTokens.imprimirListaErrores();
                break;               
            case 4:
                System.exit(0);
                break;
        }
        //se encarga de hacer una llamada de nuevo al menu de lista de tokens.
        visualizarListasTokens();
    }
}
