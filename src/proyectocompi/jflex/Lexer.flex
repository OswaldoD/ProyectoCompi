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
white=[ ,\n]

/* Definicion de palabras reservadas */
palabrasReservadas="and" | "assert" | "break" | "class" | "continue" | "def" |
"del" | "elif" | "else" | "except" | "exec" | "finally" | 
"for" | "from" | "global" | "if" | "import" | "in" | "is" |
"lambda" | "not" | "or" | "pass" | "print" | "raise" |
"return" | "try" | "while" | "with" | "yield"

/* Definicion de operadores aritmeticos */

%%
{white} {/*Ignore*/}
"//".* {/*Ignore*/}
"=" {lexeme=yytext(); return igual;}
"+" {lexeme=yytext(); return suma;}
{L} {lexeme=yytext(); return Variable;}
{D} {lexeme=yytext(); return Numero;}
"*" {lexeme=yytext(); return multiplicacion;}
"-" {lexeme=yytext(); return resta;}
"/" {lexeme=yytext(); return division;}
{palabrasReservadas} {lexeme=yytext(); return palabra_reservada;}
. {return ERROR;}

