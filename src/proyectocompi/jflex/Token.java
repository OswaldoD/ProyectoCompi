/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectocompi.jflex;

/**
 *
 * @author Oswaldo Davila
 * Clase para manejar las familias o tipos de tokens existentes
 * 
 */
public enum Token {
    palabra_reservada,
    operador_aritmetico,
    operador_comparativo, 
    operador_asignativo,
    operador_bits,
    operador_logico,
    comparador_identidad, 
    separador_coma,
    separador_dos_puntos,
    separador_tab,
    literal_string,
    literal_string_parrafo,
    contenedor,
    Variable, 
    Numero_Entero, 
    Numero_Long,
    Numero_Flotante, 
    Numero_Complejo,
    ERROR;
}
