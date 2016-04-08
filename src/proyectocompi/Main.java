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
import jdk.nashorn.internal.parser.Lexer;

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
        
        

    }
    
    public void fileReader() throws IOException{
                Reader reader = new BufferedReader(new FileReader("file.txt"));
        
       // Lexer lexer = new Lexer(reader);
        
    }
     

}
