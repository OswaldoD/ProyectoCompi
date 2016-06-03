package proyectocompi.jflex;
import static proyectocompi.jflex.Token.*;
%%
/* Directivas de JFlex */
%public
%class Lexer
%unicode
%cup
%line /*conteo de lineas*/
%{
    public String lexeme;
%}
%{ 
    public int linea() {return yyline+1;}
%}
%type Token





/*Definicion de los numeros*/
numerosEnteros = \d+ | [-]\d+ | [-]\d+[x]\d+ | \d+[x]\d+
numerosLong = \d+[L] | [-]\d+[L] | [-]\d+[x]\d+[L] | \d+\x2a\d+[L] | \d+[x]([aA-fF]*)[L] | \d+([aA-fF]*)[L]

numerosFlotantes = \d+[.] | [-]\d+[.] | \d+[.]\d+ | \d+[.]\d+[eE] | \d+[.]\d+[eE]\d+ | \d+[.]\d+[+-][eE] | \d+[.]\d+[+-][eE]\d+ |
                   [-]\d+[.]\d+ | [-]\d+[.]\d+[eE] | [-]\d+[.]\d+[eE]\d+ | [-]\d+[.]\d+[+-][eE] | [-]\d+[.]\d+[+-][eE]\d+
numerosComplejos = \d+[.][jJ] | [-]\d+[.][jJ] | \d+[.]\d+[jJ] | \d+[.]\d+[eE][jJ] | \d+[.]\d+[eE]\d+[jJ] | \d+[.]\d+[eE][+-]\d+[jJ] |                     
                   [-]\d+[.]\d+[jJ] | [-]\d+[.]\d+[eE][jJ] | \d+[.]\d+[eE][+-]\d+[jJ] | [-]\d+[.]\d+[+-][eE][jJ] |
                   [-]\d+[.]\d+[+-][eE]\d+[jJ] | [-][.]\d+[jJ] | [.]\d+[jJ] |[-][.]\d+[+-]\d+[jJ] | [.]\d+[+-]\d+[jJ] | 
                   [-][.]\d+[eE][jJ] | [.]\d+[eE][jJ] |[-][.]\d+[eE][+-]\d+[jJ] | [.]\d+[eE][+-]\d+[jJ] |
                   [-][.]\d+[+-][eE]\d+[jJ] | [.]\d+[+-][eE]\d+[jJ] | \d+[eE][+-]\d+[jJ]

white=[ \n]

/*Definición de errores en los identificadores*/
errorIdetnificadores= \d+([aA-zZ]+|[_] )([aA-zZ]*|[0-9]|[_])*


/* Definicion de palabras reservadas */
palabrasReservadas = "assert" | "break" | "class" | "continue" | "def" |"del" | 
"elif" | "else" | "except" | "exec" | "finally" | "for" | "from" | "global" | 
"if" | "int" | "input"| "import" | "lambda" | "pass" | "print" | "raise" | 
"return" | "try" | "while" | "with" | "yield"

/* Definicion de operadores aritmeticos */
operadorAritmetico = "+" | "-" | "*" | "/" | "%" | "**" | "//" 
operadorComparativo = "==" | "!=" | "<>" | ">" | "<" | ">=" | "<="
operadorAsignativo = "=" | "+=" | "-=" | "*=" | "/=" | "%=" | "**=" | "//="
operadorBits = "&" | "|" | "^" | "~" | "<<" | ">>"
operadorLogico = "and" | "or" | "not"
comparadorIdentidad = "is" | "in" 

/* Definicion de separadores */
separadorComa = ","
separadorDosPuntos = ":"
separadorTab="  " /* revisar */

/* Definicion de contenedor */
contenedor = "{" | "}" | "[" | "]" | "(" | ")"

/* Definicion de literales */
/*string = "'"[^']*"'" | "'''"[^']*"'''" | "\""[^]*"\""*/
/*string = "'"[^']*"'" | '"'[^']*'"'*/
string = "'"[^']*"'" 
 /*"[ ' | " ] [aqui el resto de validaciones][' | "]"*/


/* Definicion de los identificadores */
identificadores = ([aA-zZ]+|[_] )([aA-zZ]*|[0-9]|[_])*

/* Definicion de literal booleano */
literalBool = "True" | "False"

/* Comentario de bloque */
comentarioBloque=("'''")[^']*("'''")

%%

{white} {/*Ignore*/}
/*"//".* {/*Ignore*/}*/
"#".* {/*Ignore*/}


/*Error identificadores*/
{errorIdetnificadores} {lexeme=yytext(); return Error_Identificador;}

/* Numeros */
{numerosEnteros} {lexeme=yytext(); return Numero_Entero;}
{numerosLong} {lexeme=yytext(); return Numero_Long;}
{numerosFlotantes} {lexeme=yytext(); return Numero_Flotante;}
{numerosComplejos} {lexeme=yytext(); return Numero_Complejo;}

/* palabras reservadas */
{palabrasReservadas} {lexeme=yytext(); return palabra_reservada;}

/* operadores */
{operadorAritmetico} {lexeme=yytext(); return operador_aritmetico;}
{operadorComparativo} {lexeme=yytext(); return operador_comparativo;}
{operadorAsignativo} {lexeme=yytext(); return operador_asignativo;}
{operadorBits} {lexeme=yytext(); return operador_bits;}
{operadorLogico} {lexeme=yytext(); return operador_logico;}
{comparadorIdentidad} {lexeme=yytext(); return comparador_identidad;}

/* separadores */
{separadorComa} {lexeme=yytext(); return separador_coma;}
{separadorTab} {lexeme=yytext(); return separador_tab;} /* revisar*/
{separadorDosPuntos} {lexeme=yytext(); return separador_dos_puntos;}

/* contenedores */
{contenedor} {lexeme=yytext(); return contenedor;}

/* literales */
{string} {lexeme=yytext(); return literal_string;}


/* Booleanos */
{literalBool} {lexeme=yytext(); return Booleano;}

/* Identificadores */
{identificadores} {lexeme=yytext(); return Identificador;}

/* Comentario Bloque */
{comentarioBloque} {/*Ignore*/}

. {return ERROR;}