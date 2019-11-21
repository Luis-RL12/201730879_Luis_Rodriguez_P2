/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author RODRIGUEZ
 */
public class Token {
    
    private String token;
    private String tipo;
    private int linea;
    private int columna;

    public Token(String token, String tipo, int linea, int columna) {
        this.token = token;
        this.tipo = tipo;
        this.linea = linea;
        this.columna = columna;
    }
    
    public Token(){
        
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
    
    
}
