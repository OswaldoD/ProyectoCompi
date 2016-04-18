/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectocompi.jflex;

import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class TToken {
    private String lexeme;
    private Token Tipo_token;
    private String numero_linea;
    
    public TToken(String lexeme, Token Tipo_token, String numero_linea){
        this.lexeme = lexeme;
        this.Tipo_token = Tipo_token;
        this.numero_linea = numero_linea;
        
    }
    
    public void setNumeroLinea(String nuevoNumeroLinea){
        this.numero_linea = nuevoNumeroLinea;          
    }
    
    public String getLexeme(){
        return this.lexeme;
    }
    
    public String getTipo_Token(){
        return this.Tipo_token.toString();
    }
    
    public String getNumero_Linea(){
        return this.numero_linea;
    }      
        
}
