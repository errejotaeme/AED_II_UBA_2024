package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    private int _longitud;
    private Nodo _primero;
    private Nodo _ultimo;
    

    private class Nodo {
        T dato;
        Nodo anterior;        
        Nodo siguiente;

        Nodo(T valor) { 
            this.dato = valor; }
    }

    public ListaEnlazada() {
        this._longitud = 0;
    }

    public int longitud() {
        return this._longitud;
    }

    public boolean estaVacia() {
        return this._longitud == 0;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo_nodo = new Nodo(elem);
        nuevo_nodo.anterior = null;        
        if (!estaVacia()) {
            nuevo_nodo.siguiente = this._primero;
            this._primero.anterior = nuevo_nodo;
        } else {
            this._ultimo = nuevo_nodo;
        }
        this._primero = nuevo_nodo;
        this._longitud ++;
    }

    public void agregarAtras(T elem) {
        Nodo nuevo_nodo = new Nodo(elem);
        nuevo_nodo.siguiente = null;        
        if (!estaVacia()) {
            nuevo_nodo.anterior = this._ultimo;
            this._ultimo.siguiente = nuevo_nodo;
        } else {
            this._primero = nuevo_nodo;
        }
        this._ultimo = nuevo_nodo;
        this._longitud ++;
    }

    public T obtener(int i) {
        Iterador<T> iter = iterador();
        int contador = 0;
        T res = null;
        while(contador <= i){
            T aux = iter.siguiente();
            res = aux;
            contador ++;
        }
        return res;

    }

    public void eliminar(int i) {
        if (i==0 && longitud() == 1){
            this._primero = null;
        }else if (i==0){
            this._primero = this._primero.siguiente;
            this._primero.anterior = null;
        } else if (longitud() - i == 1){
            this._ultimo = this._ultimo.anterior;
            this._ultimo.siguiente = null;
        } else {
            int contador = 0;
            Nodo actual = this._primero; 
            while(contador < i) {
                actual = actual.siguiente;
                contador ++;
            }
            actual.anterior.siguiente = actual.siguiente;
            actual.siguiente.anterior = actual.anterior;
        }
        this._longitud --;
    }

    public void modificarPosicion(int indice, T elem) {
        int contador = 0;
            Nodo actual = this._primero; 
            while(contador < indice) {
                actual = actual.siguiente;
                contador ++;
            }
            actual.dato = elem;      
    }

    public ListaEnlazada<T> copiar() {
        ListaEnlazada<T> res = new ListaEnlazada<>();
        Iterador<T> iter = iterador();
        res._primero = new Nodo(null);
        Nodo actual = res._primero;
        while(iter.haySiguiente()){
            T aux = iter.siguiente();
            actual.dato = aux;
            Nodo nuevo_nodo = new Nodo(null);
            actual.siguiente = nuevo_nodo;
            nuevo_nodo.anterior = actual;
            actual = nuevo_nodo;
            res._longitud++;
        }        
        return res;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        Iterador<T> iter = lista.iterador();
        this._primero = new Nodo(null);
        Nodo actual = this._primero;
        while(iter.haySiguiente()){
            T aux = iter.siguiente();
            actual.dato = aux;
            Nodo nuevo_nodo = new Nodo(null);
            actual.siguiente = nuevo_nodo;
            nuevo_nodo.anterior = actual;
            actual = nuevo_nodo;
            this._longitud++;
        }          
    }
    
    @Override
    public String toString() {
        String res = "[";
        Iterador<T> iter = iterador();
        while(iter.haySiguiente()){
            T aux = iter.siguiente();
            String cadena = aux.toString();
            cadena = cadena.concat(", ");
            res = res.concat(cadena);
        }
        res = res.substring(0, res.length()-2);
        res = res.concat("]");
        return res;
    }




    private class ListaIterador implements Iterador<T> {
    	int _posicion;
        Nodo _actual; 

        ListaIterador() {
            this._posicion = 0;
            this._actual = _primero;
        }

        public boolean haySiguiente() {
            return this._posicion < longitud();
        }
        
        public boolean hayAnterior() {
	        return this._posicion > 0;
        }

        public T siguiente() {
            T res = this._actual.dato;  
            if (haySiguiente() && this._actual.siguiente != null){                            
                this._actual = this._actual.siguiente;                 
            }
            this._posicion++;                 
            return res;
        }

        public T anterior() {
            if (this._posicion != longitud() && hayAnterior()) {              
                this._actual = this._actual.anterior;                
            }
            this._posicion--; 
            T res = this._actual.dato;  
            return res;    
        }

    }

    public Iterador<T> iterador() {
        ListaIterador iterador = new ListaIterador();
        return iterador;
    }

}
