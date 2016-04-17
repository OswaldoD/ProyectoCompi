/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectocompi.jflex;

import java.io.File;
import java.nio.file.Paths;

/**
 *
 * @author Oswaldo Davila
 * @see Clase para generar los archivos del analizador lexico
 *      requiere de JFlex.
 */
public class Generate {
        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Generate generate = new Generate();
       
        
    }
    
    public Generate(){
       // String path = "C:/Users/usuario/NetBeansProjects/ProyectoCompi/src/"
                     //+ "proyectocompi/jflex/Lexer.flex";
        //String path = "C:/Users/esporras/Documents/NetBeansProjects/ProyectoCompi/src/proyectocompi/jflex/Lexer.flex";
                
      //  generateLexer(path);
        String path=Paths.get("").toAbsolutePath().toString() + "/src/proyectocompi/jflex/Lexer.flex";
        System.out.println(path);
        generateLexer(path);
    }
    
    /**
     * @author Oswaldo Davila
     * @param path ruta del archivo .flex
     * 
     */
    public void generateLexer(String path){        
        File file = new File(path);
        jflex.Main.generate(file);
        
    }
}
