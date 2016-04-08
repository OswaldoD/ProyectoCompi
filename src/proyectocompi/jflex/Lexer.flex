package proyectocompi.jflex;
import static proyectocompi.jflex.Token.*;
%%
%class Lexer
%type Token
L=[a-b]
D=[0-9]
white=[ ,\n]
%{
    public String lexeme;
%}
%%
{white} {/*Ignore*/}
"//".* {/*Ignore*/}
"=" {return igual;}
"+" {return suma;}
{L} {lexeme=yytext(); return Variable;}
{D} {lexeme=yytext(); return Numero;}
"*" {lexeme=yytext(); return multiplicacion;}
"-" {lexeme=yytext(); return resta;}
"/" {lexeme=yytext(); return division;}
. {return ERROR;}

