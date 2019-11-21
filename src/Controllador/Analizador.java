/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllador;

import Modelo.Token;
import java.util.ArrayList;

/**
 *
 * @author RODRIGUEZ
 */
public class Analizador {
    
    int fila=1;
    int columna=1;
    int filaMatriz=0;
    int filaPrevia = 0;
    int columnaMatriz=0;
    String token = "";
    String tipoToken ="";
    //nota el 404 significa error
        int [][] matriz = 
            {{0,1,6,3,4,5,404,8,10,12,3,404}, 
            {1,1,2,404,404,404,404,404,404,404,404,404},
            {2,404,2,404,404,404,404,404,404,404,404,404},
            {3,404,404,404,404,404,404,404,404,404,404,404},
            {4,404,404,404,404,404,404,404,404,404,404,404},
            {5,404,404,404,404,404,404,404,404,404,404,404},
            {6,404,6,404,404,404,7,404,404,404,404,404},
            {7,404,7,404,404,404,404,404,404,404,404,404},
            {8,8,8,8,8,404,404,9,404,404,404,8},
            {9,404,404,404,404,404,404,404,404,404,404,404},
            {10,404,404,404,404,404,404,404,11,404,404,404},
            {11,404,404,404,404,404,404,404,404,404,404,404},
            {12,3,3,3,3,3,3,3,3,16,13,404},
            {13,13,13,13,13,13,13,13,13,13,14,13},
            {14,404,404,404,404,404,404,404,404,15,404,404},
            {15,404,404,404,404,404,404,404,404,404,404,404},
            {16,16,16,16,16,16,16,16,16,16,16,17},
            {17,404,404,404,404,404,404,404,404,404,404,404},
            };
        
        
    public ArrayList<Token> analizarTexto(String textoEntrada){
        ArrayList<Token> listaToken = new ArrayList<>();
        int contadorChar=0;
        while(contadorChar < textoEntrada.length()){
            char letra = textoEntrada.charAt(contadorChar);
                //System.out.println("letra: "+letra);
                columnaMatriz = tokenEnTabla(letra);
                filaMatriz = matriz[filaMatriz][columnaMatriz];
                //System.out.println("fila matriz: "+filaMatriz);
                if(filaMatriz == 7 && letra=='.'){
                    System.out.println("Error por punto con token: "+token+". en la fila: "+fila+" y columna: "+columna);
                    listaToken.add(new Token(token,"Error",fila,columna));
                    filaMatriz=0;
                    token="";
                }
                else if(filaMatriz == 404){
                    //System.out.println("fila previa: "+filaPrevia);
                    if(filaPrevia ==1  || filaPrevia == 2){
                        if(token.equals("funcion") || token.equals("imprimir") || token.equals("principal") || token.equals("retornar") || token.equals("vacio")
                                || token.equals("variable") || token.equals("entero") || token.equals("decimal") || token.equals("booleano") || token.equals("cadena")
                                || token.equals("caracter") || token.equals("si") || token.equals("sino") || token.equals("mientras") || token.equals("para")
                                || token.equals("hacer")){
                            tipoToken = "Palabra Recervada";
                        }else if(token.equals("VERDADERO") || token.equals("FALSO")){
                               tipoToken = "Boolean";
                        }
                        else{
                            tipoToken = "Identificador";
                        } 
                    }else if(filaPrevia == 3){
                        tipoToken = "Operador";
                    }else if(filaPrevia == 4){
                        tipoToken = "Agrupacion";
                    }else if(filaPrevia == 5){
                        tipoToken = "Signo";
                    }else if(filaPrevia == 6){
                        tipoToken = "Numero";
                    }else if(filaPrevia == 7){
                        tipoToken  = "Numero Flotante";
                    }else if(filaPrevia == 9){
                        tipoToken = "Cadena";
                    }else if(filaPrevia == 11){
                        tipoToken = "Caracter";
                    }else if(filaPrevia == 17){
                        tipoToken = "Comentario";
                    }else if(filaPrevia == 15){
                        tipoToken = "Comentario de bloque";
                    }
                    if(letra == ' '){
                        System.out.println(tipoToken+" con token : "+token+" en la fila: "+fila+" y columna: "+columna);
                        listaToken.add(new Token(token,tipoToken,fila,columna));
                        filaMatriz = 0;
                        token = "";
                    }else if(Character.toString(letra).equals("\n")){
                        filaMatriz = 0;
                        token = "";
                        fila++;
                        columna=0;
                    }else if(Character.toString(letra).equals("\t")){
                         filaMatriz = 0;
                        token = "";
                    }else{
                        System.out.println(tipoToken+" con token : "+token+" en la fila: "+fila+" y columna: "+columna);
                        listaToken.add(new Token(token,tipoToken,fila,columna));
                    filaMatriz = 0;
                    token = "";
                    contadorChar--;   
                    }
                    
                }else{
                    token += letra;
                }
                filaPrevia = filaMatriz;
                columna++;
            contadorChar++;
        }
        
        return listaToken;
    }
    
    private int tokenEnTabla(char valor){
        //analizar identificadores
        if(valor >='a' && valor<='z' || valor >='A' && valor <='Z'){
            return 1;
        }
        else if(valor >='0' && valor<='9'){
            return 2;
        }
        else if(valor == '+' || valor == '-' || valor == '%' || valor == '=' || valor == '<' || valor =='>'){
            return 3;
        }
        else if(valor == '{' || valor == '}' || valor == '(' || valor == ')'){
            return 4;
        }
        else if(valor == ';' || valor == ','){
            return 5;
        }
        else if(valor == '.'){
            return 6;
        }
        else if(valor == '"'){
            return 7;
        }
        else if(valor == '\''){
            return 8;
        }
        else if(valor == '/'){
            return 9;
        }
        else if(valor == '*'){
            return 10;
        }
        else if(valor == '\n'){
            return 11;
        }else{
            
        }
        return 11;
    }
}
