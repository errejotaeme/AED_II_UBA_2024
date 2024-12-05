package aed;

import java.util.*;


public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    Nodo _raiz;
    int _cardinal;

    private class Nodo {
        T dato;
        Nodo izq;
        Nodo der;
        Nodo padre;

        Nodo(T d){
            this.dato = d;
            this.izq = null;
            this.der = null;
            this.padre = null;
        }
    }

    public ABB() {
        this._raiz = null;
        this._cardinal = 0;
    }

    public int cardinal() {
        int res = this._cardinal;
        return res;
    }

    public T minimo(){
        T res = null;
        if (this._raiz != null){
            Nodo actual = this._raiz;
            while(actual.izq != null){
                actual = actual.izq;
            }
            res = actual.dato;
        }    
        return res;
    }

    public T maximo(){
        T res = null;
        if (this._raiz != null){
            Nodo actual = this._raiz;
            while(actual.der != null){
                actual = actual.der;
            }
            res = actual.dato;
        }    
        return res;
    }

    public void insertar(T elem){
        if (!pertenece(elem)){
            Nodo nuevo_nodo = new Nodo(elem);
            Nodo nodo_actual_padre = null;
            Nodo nodo_actual = this._raiz;
            while(nodo_actual != null){ // árbol no vacío
                nodo_actual_padre = nodo_actual; // descenso
                if (nuevo_nodo.dato.compareTo(nodo_actual.dato) < 0){ // elem es menor
                    nodo_actual = nodo_actual.izq;
                } else { // elem es mayor
                    nodo_actual = nodo_actual.der;
                }
            }
            nuevo_nodo.padre = nodo_actual_padre;
            if (nodo_actual_padre == null){ // árbol vacío
                this._raiz = nuevo_nodo;
            } else if (nuevo_nodo.dato.compareTo(nodo_actual_padre.dato) < 0){ // lo ubica donde corresponde
                nodo_actual_padre.izq = nuevo_nodo;
            } else{
                nodo_actual_padre.der = nuevo_nodo;
            }
            this._cardinal++;
        }
    }

    public boolean pertenece(T elem){
        if (this._raiz == null){
            return false;
        } else {
            Nodo nodo_aux = this._raiz;
            while(nodo_aux != null && elem.compareTo(nodo_aux.dato) != 0){
                if (elem.compareTo(nodo_aux.dato) < 0) {
                    nodo_aux = nodo_aux.izq;
                } else {
                    nodo_aux = nodo_aux.der;
                }
            }
            if (nodo_aux == null){
                return false;
            } else{
                return nodo_aux.dato.compareTo(elem) == 0; 
            }                   
        }   
    }

    public Nodo nodoMenor(Nodo nodo){
        Nodo nodo_aux = nodo;
        while(nodo_aux.izq != null){
            nodo_aux = nodo_aux.izq;
        }
        return nodo_aux;
    }

    public Nodo nodoMayor(Nodo nodo){
        Nodo nodo_aux = nodo;
        while(nodo_aux.der != null){
            nodo_aux = nodo_aux.der;
        }
        return nodo_aux;
    }

    public Nodo sucesor(Nodo nodo){
        Nodo nodo_aux_1 = nodo;
        if (nodo_aux_1.der != null){
            return nodoMenor(nodo_aux_1.der);
        }
        Nodo nodo_aux_2 = nodo_aux_1.padre;
        while(nodo_aux_2 != null && nodo_aux_1.dato.compareTo(nodo_aux_2.der.dato) == 0){
            nodo_aux_1 = nodo_aux_2;
            nodo_aux_2 = nodo_aux_2.padre;
        }
        return nodo_aux_2;
    }

    public void eliminar(T elem){
        if(pertenece(elem)){

            Nodo nodo_a_borrar = buscar(elem); // Encuentro el nodo que debo borrar

            if (esRaiz(nodo_a_borrar)){ // Caso 1: es raíz
                if (esHoja(this._raiz)){
                    this._raiz = null;                    
                } else if (tieneUnHijo(this._raiz)){ // Tiene un descendiente
                    if (tieneHijoMenor(this._raiz)){
                        this._raiz = this._raiz.izq;
                    } else { // Tiene hijo mayor
                        this._raiz = this._raiz.der;                        
                    }
                    this._raiz.padre = null;
                } else { // Tiene dos descendientes 
                    Nodo sucesor = sucesor(this._raiz);
                    this._raiz.izq.padre = sucesor; // Asignacion izq del nodo a borar
                    sucesor.izq = this._raiz.izq; 
                    if (sucesor.padre.dato.compareTo(this._raiz.dato) != 0){ // Sucesor lejano
                        
                        if (tieneHijoMayor(sucesor)){
                            sucesor.der.padre = sucesor.padre; //asig der de sucesor
                        }
                        sucesor.padre.izq = sucesor.der;

                        this._raiz.der.padre = sucesor; // asig der del nodo a borrar
                        sucesor.der = this._raiz.der;
                    }
                    this._raiz = sucesor; 
                }

            } else if (esHoja(nodo_a_borrar)){ // Caso 2: el nodo no tiene descendencia
                if (esHijoMenor(nodo_a_borrar)){
                    nodo_a_borrar.padre.izq = null;
                } else { // Es hijo mayor
                    nodo_a_borrar.padre.der = null;
                }    

            } else if (tieneUnHijo(nodo_a_borrar)){ // Caso 3: tiene un descendiente
                cambiarVinculos(nodo_a_borrar);

            } else { // Caso 4 Tiene dos descendientes
                Nodo sucesor = sucesor(nodo_a_borrar);
                nodo_a_borrar.izq.padre = sucesor;      // Asignacion izq del nodo a borar
                sucesor.izq = nodo_a_borrar.izq;        // Asignacion izq del sucesor
                if (sucesor.padre.dato.compareTo(nodo_a_borrar.dato) != 0){ // El sucesor no es el nodo mas cercano

                    if (tieneHijoMayor(sucesor)){       // Asignacion der del sucesor
                        sucesor.der.padre = sucesor.padre; 
                    }
                    sucesor.padre.izq = sucesor.der;   

                    nodo_a_borrar.der.padre = sucesor;  // Asignacion der del nodo a borrar
                    sucesor.der = nodo_a_borrar.der;
                }
                sucesor.padre = nodo_a_borrar.padre; // Asignacion del padre del nodo a borrar
                if (esHijoMenor(nodo_a_borrar)){
                        nodo_a_borrar.padre.izq = sucesor;
                } else { // Es hijo mayor
                        nodo_a_borrar.padre.der = sucesor;
                }
            }
            this._cardinal--;
        }
    }

    public void cambiarVinculos(Nodo nodo_a_borrar){
        if(esHijoMenor(nodo_a_borrar)){
            if (tieneHijoMenor(nodo_a_borrar)){
                nodo_a_borrar.padre.izq = nodo_a_borrar.izq;
                nodo_a_borrar.izq.padre = nodo_a_borrar.padre;
            } else { // Tiene hijo mayor
                nodo_a_borrar.padre.izq = nodo_a_borrar.der;
                nodo_a_borrar.der.padre = nodo_a_borrar.padre;
            }                                        
        } else { // Es hijo mayor
            if (tieneHijoMenor(nodo_a_borrar)){
                nodo_a_borrar.padre.der = nodo_a_borrar.izq;
                nodo_a_borrar.izq.padre = nodo_a_borrar.padre; 
            } else { // Tiene hijo mayor
                nodo_a_borrar.padre.der = nodo_a_borrar.der;
                nodo_a_borrar.der.padre = nodo_a_borrar.padre; 
            }   
        }
    }

    // Metodos auxiliares a eliminar()
    public boolean esRaiz(Nodo nodo){
        return this._raiz.dato.compareTo(nodo.dato) == 0;
    }

    public boolean esHoja(Nodo nodo){  
        return nodo.izq == null && nodo.der == null;       
    }

    public boolean esHijoMenor(Nodo nodo){
        boolean res = false;
        if (nodo.padre.izq != null){
            res = nodo.padre.izq.dato.compareTo(nodo.dato) == 0;
        }
        return res;
    }

    public boolean tieneHijoMayor(Nodo nodo){
        return nodo.der != null;
    }

    public boolean tieneHijoMenor(Nodo nodo){
        return nodo.izq != null;
    }

    public boolean tieneUnHijo(Nodo nodo){
        boolean c_1 = tieneHijoMayor(nodo) || tieneHijoMenor(nodo);
        boolean c_2 = !(tieneHijoMayor(nodo) && tieneHijoMenor(nodo));
        return c_1 && c_2;
    }

    public Nodo buscar(T valor){ // Requiere que exista el nodo
        Nodo nodo_aux = this._raiz;
        while(nodo_aux != null && valor.compareTo(nodo_aux.dato) != 0){
            if (valor.compareTo(nodo_aux.dato) < 0) {
                nodo_aux = nodo_aux.izq;
            } else {
                nodo_aux = nodo_aux.der;
            }
        }
        return nodo_aux;                      
    }

    public String toString(){
        String res = "{";
        Iterador<T> iter = iterador();
        while(iter.haySiguiente()){
            T dato = iter.siguiente();
            String cadena_aux = dato.toString();
            cadena_aux = cadena_aux.concat(",");
            res = res.concat(cadena_aux);
        }
        res = res.concat(iter.siguiente().toString()); // El ultimo valor del arbol
        res = res.concat("}");
        return res;
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;
        private Nodo _nodo_maximo;

        ABB_Iterador(){ 
            this._actual = nodoMenor(_raiz);
            this._nodo_maximo = nodoMayor(_raiz);
        }

        public boolean haySiguiente() {
            boolean res = this._actual.dato.compareTo(this._nodo_maximo.dato) < 0;            
            return res; 
        }
    
        public T siguiente() {
            T res = this._actual.dato;
            if (haySiguiente()){
                this._actual = sucesor(this._actual);
            }
            return res;    
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
