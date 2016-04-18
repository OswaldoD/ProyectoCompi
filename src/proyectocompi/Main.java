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
import static proyectocompi.jflex.ordenamientoTokens.ordenamientoTokens;
import proyectocompi.jflex.ListasTokens;

/**
 *
 * @author usuario
 */
public class Main {

    private ArrayList<TToken> Tokens;
    ListasTokens listadoTokens = new ListasTokens();
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
        System.out.print("** Eliga una opción: ");
        
        int s;
        String path;
        while(true){
            /*
            Ciclo para que el rango de numeros sea entre 0 y 1.
            */
            Scanner scan = new Scanner(System.in);
            s = scan.nextInt();
     
            if((s > 9) || (s < 1)){
                System.out.println("** Opción incorrecta: ");
                System.out.print("** Eliga una opción: ");
                s = scan.nextInt();
            }
            else if(s == 9){
                System.out.println("Ingrese la ruta del archivo: ");
                path = scan.next();
                System.out.println(path);
                break;
            }
            else{
                path = rutaArchivo(s);
                System.out.println(path);
                break;
            }
        }
//       String path = Paths.get("").toAbsolutePath().toString() + "/src/proyectocompi/file.txt";

        Scan(fileReader(path)); // inicio del scanner
        visualizarListasTokens();//llamar a la función encargada de mostrar menú con opciones de visualización de als listas de token
    }
    
    public Lexer fileReader(String path){
      // String path = "/Users/usuario/NetBeansProjects/ProyectoCompi/src/"
        //              + "proyectocompi/file.txt";
       // /Users/usuario/NetBeansProjects/ProyectoCompi/src/proyectocompi
       
       //String path = "C:/Users/esporras/Documents/NetBeansProjects/ProyectoCompi/src/proyectocompi/file.txt";
      // String path = Paths.get("").toAbsolutePath().toString() + "/src/proyectocompi/file.txt";
        
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
                  // Resultados = Resultados + "FIN\n";
                  // System.out.println(Resultados);
                   break;
               }
               switch(token){
                   case ERROR:
                       Resultados = Resultados + "Error, no existe\n";
                       //enviar a la lista de errores
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
                       else if(token_resultado.getTipo_Token()=="ERROR" || token_resultado.getTipo_Token()=="Error_Identificador"){
                           listadoTokens.insertarTokenError(token_resultado);
                       }                                              
                       //Tokens.add(token_resultado);                                            
                       break;                                    
               }
           } catch (IOException ex) {
               Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
              // break;
           }

       }      
       listadoTokens.insertarlistadoTotalTokens();
        //listadoTokens.imprimirListatoTotalTokens();
       // listadoTokens.imprimirListaErrores();
       
    }
     
    private String rutaArchivo(int opcion){
        /*
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
        */
        switch(opcion){
            case 1:
                return Paths.get("").toAbsolutePath().toString() + "/src/"
                        + "pruebas/palabrasReservadas.txt";
                //break;
            case 2:
                return Paths.get("").toAbsolutePath().toString() + "/src/"
                        + "pruebas/operadores.txt";
            case 3:
                return Paths.get("").toAbsolutePath().toString() + "/src/"
                        + "pruebas/numeros.txt";
            case 4:
                return Paths.get("").toAbsolutePath().toString() + "/src/"
                        + "pruebas/string.txt";
            case 5:
                return Paths.get("").toAbsolutePath().toString() + "/src/"
                        + "pruebas/separadores.txt";
            case 6:
                return Paths.get("").toAbsolutePath().toString() + "/src/"
                        + "pruebas/contenedores.txt";
            case 7:
                return Paths.get("").toAbsolutePath().toString() + "/src/"
                        + "pruebas/identificadores.txt";
            case 8:
                return Paths.get("").toAbsolutePath().toString() + "/src/"
                        + "pruebas/principal.txt";
            default:
                return "ruta incorrecta";
              //  break;
        }
    }
    
    
    public void visualizarListasTokens(){
        System.out.println("***************************************");
        System.out.println("***** Menú de Imprensión Listas de Tokens *****");
        System.out.println("***************************************");        
        System.out.println("*** ¿Qué desea hacer?: ");
        System.out.println("** 1- Imprimir Tokens Encontrados ");
        System.out.println("** 2- Imprimir Errores Léxicos Encontrados");
        System.out.println("** 3- Salir");
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
            case 3:
                System.exit(0);
                break;

        }
        visualizarListasTokens();
    }

}
