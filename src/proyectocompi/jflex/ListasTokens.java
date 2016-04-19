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
    private ArrayList<TToken> booleanos = new ArrayList<TToken>();
    public ArrayList<TToken> listadoTotalTokens = new ArrayList<TToken>();


    public void insertarTokenSeparador(TToken token ){           
        if(separadores.size()==0){
                separadores.add(token);
        }
        else{
            busquedaTokens(token, separadores);
        }  
    }

    public void insertarTokenContenedor(TToken token ){        
        if(contenedores.size()==0){
            contenedores.add(token);
        }
        else{
            busquedaTokens(token, contenedores);
        }         
    }
    
    public void insertarTokenOperador(TToken token ){
        if(operadores.size()==0){
            operadores.add(token);
        }
        else{
            busquedaTokens(token, operadores);
        } 
    }
    
    public void insertarTokenLiteral(TToken token ){
         if(literales.size()==0){
            literales.add(token);
        }
        else{
            busquedaTokens(token, literales);
        } 
    }

    public void insertarTokenNumero(TToken token ){
        if(numeros.size()==0){
            numeros.add(token);
        }
        else{
            busquedaTokens(token, numeros);
        } 
    }
    
    public void insertarTokenPalabrasReservada(TToken token  ){
        if(palabrasReservadas.size()==0){
            palabrasReservadas.add(token);
        }
        else{
            busquedaTokens(token, palabrasReservadas);
        } 
    }
   
    public void insertarTokenIdentificadores(TToken token ){
        if(identificadores.size()==0){
            identificadores.add(token);
        }
        else{
            busquedaTokens(token, identificadores);
        } 
    }
    
    public void insertarTokenBooleano(TToken token  ){
       if(booleanos.size()==0){
           booleanos.add(token);
       }
       else{
           busquedaTokens(token, booleanos);
       }        
    } 
    
    public void insertarTokenError(TToken token  ){
       listaErrores.add(token);
    }   
       
    
    
    //insertar todos los tokens encontrados ya ordenados alfabéticamente
    public void insertarlistadoTotalTokens( ){
        //ordenamiento alfabetico de cada una de dichas listas, por tipo de token.
        //ordenamientoTokens(palabrasReservadas);
        //ordenamientoTokens(literales);
        //ordenamientoTokens(identificadores);
        //ordenamientoTokens(booleanos);
        //agregar las listas al array principal de tokens
        listadoTotalTokens.addAll(palabrasReservadas);
        ordenamientoTokens(listadoTotalTokens);
        listadoTotalTokens.addAll(booleanos);
        ordenamientoTokens(listadoTotalTokens);
        listadoTotalTokens.addAll(literales);
        ordenamientoTokens(listadoTotalTokens);
        listadoTotalTokens.addAll(identificadores);
        ordenamientoTokens(listadoTotalTokens);
        //ordenar nuevamente el array principal de token.
        ordenamientoTokens(listadoTotalTokens);
        //agregar los tokens restantes ordenados por tipo de token.
        listadoTotalTokens.addAll(separadores);
        listadoTotalTokens.addAll(contenedores);
        listadoTotalTokens.addAll(operadores);
        ordenamientoTokens(operadores);
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
   
    public void busquedaTokens(TToken token, ArrayList<TToken> nombreLista){   
            int contador=0;
            for(int i=0; i<nombreLista.size(); i++){                     
                if(token.getLexeme().equals(nombreLista.get(i).getLexeme())){  
                    String numeroLinea=nombreLista.get(i).getNumero_Linea();
                    numeroLinea+=", "+token.getNumero_Linea();
                    //token.setNumeroLinea(numeroLinea);
                    nombreLista.get(i).setNumeroLinea(numeroLinea);
                    //System.out.println(token.getNumero_Linea());
                    //separadores.set(i, token);
                    contador++;
                }
            }
            if(contador==0){
                nombreLista.add(token);
            }
    }
    //Función encargada de limpiar todos los arrgelso encaso de que el usuario desee volver al menú principal nuevamente y proceder con otra prueba.
    public void limpiarArreglos(){
        separadores.clear();
        contenedores.clear();
        operadores.clear();
        literales.clear();
        numeros.clear();
        palabrasReservadas.clear();
        identificadores.clear();
        listaErrores.clear();
        booleanos.clear();
        listadoTotalTokens.clear();
    }
            
}
