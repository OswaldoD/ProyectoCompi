package proyectocompi.jflex;
import static proyectocompi.jflex.Token.*;
%%
/* Directivas de JFlex */
%public
%class Lexer
%unicode
%line /*conteo de lineas*/
%{
    public String lexeme;
%}
%{ 
    public int linea() {return yyline+1;}
%}
%type Token
L=[a-b]
D=[0-9]
white=[ \n]

/* Definicion de palabras reservadas */
palabrasReservadas = "assert" | "break" | "class" | "continue" | "def" |"del" | 
"elif" | "else" | "except" | "exec" | "finally" | "for" | "from" | "global" | 
"if" | "import" | "lambda" | "pass" | "print" | "raise" | "return" | "try" | 
"while" | "with" | "yield"

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
separadorTab = "  " /* revisar */

%%
{white} {/*Ignore*/}
/*"//".* {/*Ignore*/}*/
{L} {lexeme=yytext(); return Variable;}
{D} {lexeme=yytext(); return Numero;}

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
. {return ERROR;}

