/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocompi.jflex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import proyectocompi.jflex.Token;
import proyectocompi.jflex.TToken;
/**
 *
 * @author esporras
 */
public class ordenamientoTokens {


    public static void ordenamientoTokens(ArrayList<TToken> arrayResultadosTokens) {
        //Sobreeescritura de la función de ordenamiento de java, para ordenar el array de tokens por medio del nombre del lexeme
        Collections.sort(arrayResultadosTokens, new Comparator<TToken>(){
            @Override
            public int compare(TToken token1, TToken token2) {
                return token1.getLexeme().compareTo(token2.getLexeme());
            }
        });
    }
}
