/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocompi.jflex;

import java.util.ArrayList;
import proyectocompi.jflex.Token;
import proyectocompi.jflex.TToken;
import proyectocompi.jflex.ordenamientoTokens;
import static proyectocompi.jflex.ordenamientoTokens.ordenamientoTokens;

/**
 *
 * @author esporras
 */
public class ListasTokens {
    private ArrayList<TToken> separadores = new ArrayList<TToken>();
    private ArrayList<TToken> contenedores = new ArrayList<TToken>();
    private ArrayList<TToken> operadores  = new ArrayList<TToken>();
    private ArrayList<TToken> literales   = new ArrayList<TToken>();
    private ArrayList<TToken> numeros   = new ArrayList<TToken>();
    private ArrayList<TToken> palabrasReservadas = new ArrayList<TToken>();
    private ArrayList<TToken> identificadores = new ArrayList<TToken>();
    private ArrayList<TToken> listaErrores = new ArrayList<TToken>();
    public ArrayList<TToken> listadoTotalTokens = new ArrayList<TToken>();


    public void insertarTokenSeparador(TToken token ){  
         System.out.println("Token: "+token.getLexeme());
        int contador=0;
        if(separadores.size()==0){
                separadores.add(token);
        }
        else{
           // System.out.println("Tamaño array: "+ separadores.size());
            //System.out.println("");
            for(int i=0; i<separadores.size(); i++){                     
                if(token.getLexeme().equals(separadores.get(i).getLexeme())){  
                    String numeroLinea=separadores.get(i).getNumero_Linea();
                    numeroLinea+=", "+token.getNumero_Linea();
                    //token.setNumeroLinea(numeroLinea);
                    separadores.get(i).setNumeroLinea(numeroLinea);
                    //System.out.println(token.getNumero_Linea());
                    //separadores.set(i, token);
                    contador++;
                }
            }
            if(contador==0){
                separadores.add(token);
            }
        }  
    }

    public void insertarTokenContenedor(TToken token ){        
        contenedores.add(token);
    }
    
    public void insertarTokenOperador(TToken token ){
        operadores.add(token);
    }
    
    public void insertarTokenLiteral(TToken token ){
        literales.add(token);
    }

    public void insertarTokenNumero(TToken token ){
        numeros.add(token);
    }
    
    public void insertarTokenPalabrasReservada(TToken token  ){
        palabrasReservadas.add(token);
    }
   
    public void insertarTokenIdentificadores(TToken token ){
        identificadores.add(token);
    }
    
    public void insertarTokenError(TToken token  ){
       listaErrores.add(token);
    }    
    
    
    //insertar todos los tokens encontrados ya ordenados alfabéticamente
    public void insertarlistadoTotalTokens( ){
        //ordenamiento alfabetico de cada una de dichas listas, por tipo de token.
        ordenamientoTokens(palabrasReservadas);
        ordenamientoTokens(literales);
        ordenamientoTokens(identificadores);
        //agregar las listas al array principal de tokens
        listadoTotalTokens.addAll(palabrasReservadas);
        listadoTotalTokens.addAll(literales);
        listadoTotalTokens.addAll(identificadores);
        //ordenar nuevamente el array principal de token.
        ordenamientoTokens(listadoTotalTokens);
        //agregar los tokens restantes ordenados por tipo de token.
        listadoTotalTokens.addAll(separadores);
        listadoTotalTokens.addAll(contenedores);
        listadoTotalTokens.addAll(operadores);
        listadoTotalTokens.addAll(numeros);                                  
    }
    
    public void imprimirListatoTotalTokens(){
        System.out.println("*******************************************");
        System.out.println("     Listado de los Tokens Encontrados     ");
        System.out.println("*******************************************");        
        System.out.println("Token   |      Tipo de Token      |   Línea");
        System.out.println("-------------------------------------------");
        if(listadoTotalTokens.size()==0){
            System.out.println("\n No se han encontrado tokens. \n");            
        }
        else{
            for(int i=0; i< listadoTotalTokens.size(); i++){            
               System.out.println(listadoTotalTokens.get(i).getLexeme()+"  |   "+
                                  listadoTotalTokens.get(i).getTipo_Token() + "    |   "+
                                  listadoTotalTokens.get(i).getNumero_Linea());
               System.out.println("-------------------------------------------");
           }           
        }
    }
                

    public void imprimirListaErrores(){
        System.out.println("*******************************************");
        System.out.println("          Listado de Errores Léxicos       ");
        System.out.println("*******************************************");
        System.out.println("Token   |      Tipo de Token      |   Línea");
        System.out.println("-------------------------------------------");
        if(listaErrores.size()==0){
            System.out.println("\n No se han encontrado errores. \n");            
        }
        else{
            for(int i=0; i< listaErrores.size(); i++){            
                System.out.println(listaErrores.get(i).getLexeme()+"  |   "+
                                   listaErrores.get(i).getTipo_Token() + "    |   "+
                                   listaErrores.get(i).getNumero_Linea());
                System.out.println("-------------------------------------------");
            }            
        }

    }
   
           
            
}
