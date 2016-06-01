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
import proyectocompi.jflex.Lexer;
import proyectocompi.jflex.Token;
import proyectocompi.jflex.TToken;
import proyectocompi.jflex.ListasTokens;

/**
 *
 * @author usuario
 */
public class Scanning {

    private ArrayList<TToken> Tokens;
    ListasTokens listadoTokens = new ListasTokens();
    private String path;
    
    public Scanning(String path){
        
        Tokens = new ArrayList<TToken>();
        this.path= path;
    //    visual();
        
    }
    public void init(){
        Scan(fileReader());
        visualizarListasTokens();
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
    public void Scan(Lexer lexer){
        /*
        este es siguiente token next_token
        
        Ahora ya no va a guardar una lista de tokens, va a retornar uno a uno-
        
        */
       String Resultados = "";
       
       while (true){
           try {
               Token token = lexer.yylex();
               if (token == null){
                  // Resultados = Resultados + "FIN\n";
                  // System.out.println(Resultados);
                   break;
               }
               switch(token){
                   case ERROR:
                       Resultados = Resultados + "Error, no existe\n";
                       //enviar a la lista de errores
                       TToken token_error = new TToken(lexer.lexeme, token, Integer.toString(lexer.linea()));
                       listadoTokens.insertarTokenError(token_error);
                       break;     
                   case Numero_Entero:
                   case Numero_Long:                       
                   case Numero_Flotante:
                   case Numero_Complejo:
                      // break;
                   default:                       
                       // almacenamiento de tokens
                       TToken token_resultado = new TToken(lexer.lexeme, token, Integer.toString(lexer.linea()));                       
                       //enviar el token a su respectiva lista según el tipo de token.
                       if(token_resultado.getTipo_Token()=="palabra_reservada"){                           
                           listadoTokens.insertarTokenPalabrasReservada(token_resultado);                           
                       }
                       else if(token_resultado.getTipo_Token()=="literal_string" || token_resultado.getTipo_Token()=="literal_string_parrafo"){
                           listadoTokens.insertarTokenLiteral(token_resultado);
                       }
                       else if(token_resultado.getTipo_Token()=="Identificador"){
                           listadoTokens.insertarTokenIdentificadores(token_resultado);
                       }
                       else if(token_resultado.getTipo_Token()=="operador_aritmetico" || 
                               token_resultado.getTipo_Token()=="operador_comparativo" ||
                               token_resultado.getTipo_Token()=="operador_asignativo" ||
                               token_resultado.getTipo_Token()=="operador_bits" ||
                               token_resultado.getTipo_Token()=="operador_logico"){
                           listadoTokens.insertarTokenOperador(token_resultado);                           
                       }
                       else if(token_resultado.getTipo_Token()=="separador_coma" || 
                               token_resultado.getTipo_Token()=="separador_dos_puntos" ||
                               token_resultado.getTipo_Token()=="separador_tab"){
                           listadoTokens.insertarTokenSeparador(token_resultado);
                       }
                       else if(token_resultado.getTipo_Token()=="contenedor"){
                           listadoTokens.insertarTokenContenedor(token_resultado);
                       }
                       else if(token_resultado.getTipo_Token()=="Numero_Entero" || 
                               token_resultado.getTipo_Token()=="Numero_Long" ||
                               token_resultado.getTipo_Token()=="Numero_Flotante" ||
                               token_resultado.getTipo_Token()=="Numero_Complejo"){
                           listadoTokens.insertarTokenNumero(token_resultado);                           
                       } 
                       else if(token_resultado.getTipo_Token()=="Booleano" ){
                           listadoTokens.insertarTokenBooleano(token_resultado);                           
                       }
                       else if(token_resultado.getTipo_Token()=="ERROR" || token_resultado.getTipo_Token()=="Error_Identificador"){
                           listadoTokens.insertarTokenError(token_resultado);
                       }                                                                                                                
                       break;                                    
               }
           } catch (IOException ex) {
               Logger.getLogger(Scanning.class.getName()).log(Level.SEVERE, null, ex);
              // break;
           }

       }     
       /*Una vez finalizada la lectura del archivo y el análisis respectivo de cada token encontrado.
       se procede con la llamada a la función encargada de insertar todo los tokens previamente ordenados alfabeticamente
       y con su respectiva busqueda de token repetidos, al arreglo final de los mismos, para su posterior impresión.*/
       listadoTokens.insertarlistadoTotalTokens();
       
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
