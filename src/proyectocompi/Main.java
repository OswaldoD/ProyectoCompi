/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectocompi;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectocompi.jflex.TToken;
import proyectocompi.jflex.ListasTokens;
import proyectocompi.parser.Parser;
import proyectocompi.scanner.Scanning;

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
      //  String file = " ";
       /* for (String s: args){
            //System.out.println(s);
            file = s;
        }*/
        String option;
        String file;
        try{
            option = args[0];
        } catch(Exception e){
            option = "-h";
        }
        try{
            file = args[1];
        }catch(Exception e){
            file = "#";
        }
        Main main = new Main(option, file);
    }
    
    public Main(String option, String file){
        System.out.println("********************************");
        System.out.println("***** Compilador de Python *****");
        System.out.println("********************************");
        chooser(option, file);
    }
    private void chooser(String option, String file){
        switch (option) {
            case "-?":
            case "-h":
                helper();
                break;
            case "-c":
                if(!(file.equals("#"))){
                    System.out.println("> hay que buscar el archivo " + file);
                    String path=Paths.get("").toAbsolutePath().toString() + "/" + file;
                    System.out.println("> "+path);
                    Scanning scanner = new Scanning(path);
                    scanner.init();
               //     scanner.Scan(scanner.fileReader());
              //      scanner.visualizarListasTokens();
                    //Scan(fileReader(path)); // inicio del scanner
                    //visualizarListasTokens();//llamar a la función encargada de mostrar menú con opciones de visualización de als listas de token
                }
                else{
                    System.out.println("> Archivo no encontrado");
                }
                break;
            default:
                commands();
                break;
        }
    }
    private void commands(){
        System.out.println("> Comando incorrecto");
        System.out.println("> Utilice -? o -h para obtener ayuda del sistema");
        
    }
    private void helper(){
        System.out.println("> Comandos disponibles: ");
        System.out.println("> c <nombre_archivo> compila un archivo");
    }
    
    public void prueba(){
        try {
            String path="";
            Parser p = new Parser(new Scanning(path));
            p.parse();
            
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
