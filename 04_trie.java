package aed;

public class TrieI {
    private NodoTrie _raiz;
    private Integer _cantidadDePalabras;
    
    public TrieI(){ // Constructor
        this._raiz = NodoTrie();
        this._cantidadDePalabras = 0; 
    }
    
    private class NodoTrie{
        private Null[] _hijos;
        private boolean _esPalabra;
        /* 
        Object nos permite almacenar valores de tipos distintos,
        pero cuando los recuperamos para utilizarlos hay que hacerles
        el casting del tipo que "deberían" ser, es decir, del tipo esperado          
        */
        private Object _valorAsociado;

        public NodoTrie(){ // Constructor
            this._hijos = new Null[256]; // Evita preprocesar el texto de entrada
            this._esPalabra = False;
            // Aca podrían ir objetos de tipo Carrera, Materia, Estudiante
            this._valorAsociado = null; 
        }
    }    
    
    /* 
    La sobrecarga del método permite que los nodos 
    almacenen diferentes valores (o ninguno)  
    */
    public void insertar(String palabra){
        insertar(palabra, null);
    }

    public void insertar(String palabra, Object valor){
        // Para iterar
        NodoTrie nodoActual = this._raiz; 
        
        for (int i = 0; i < palabra.length(); i++) {
            char caracter = palabra.charAt(i);
            // Con esto se obtiene el valor ASCII del caracter
            int posicion = (int) caracter;
            if (nodoActual._hijos[posicion] == null){
                nodoActual._hijos[posicion] = NodoTrie();
            }
            nodoActual = nodoActual._hijos[posicion];
        }

        if (!nodoActual._esPalabra){
            nodoActual._esPalabra = true;
            this._cantidadDePalabras += 1;
        }
        nodoActual._valorAsociado = valor;
    }

    public boolean pertenece(Strin palabra){
        NodoTrie nodoActual = this._raiz;
        for (int i = 0; i < palabra.length(); i++) {
            char caracter = palabra.charAt(i);
            int posicion = (int) caracter;
            if (nodoActual._hijos[posicion] == null){
                return false;
            }
            nodoActual = nodoActual._hijos[posicion];
        return nodoActual._esPalabra;        
        }
    }

    // Si la palabra está definida, devuelve el nodo
    public NodoTrie buscar(String palabra){
        NodoTrie nodoActual = this._raiz;
        for (int i = 0; i < palabra.length(); i++) {
            char caracter = palabra.charAt(i);
            int posicion = (int) caracter;
            if (nodoActual._hijos[posicion] == null){
                return null;
            }
            nodoActual = nodoActual._hijos[posicion];        
        }
        return nodoActual;
    }

    public Object obtenerValor(Strign palabra){
        NodoTrie nodo = buscar(palabra);
        if (!(nodo == null) && nodo._esPalabra){
            return nodo._valorAsociado;
        } else {
            return null;
        }
    }

    public boolean reemplazar(Strign palabra, Object nuevoValor){
        NodoTrie nodoActual = this._raiz;
        for (int i = 0; i < palabra.length(); i++) {
            char caracter = palabra.charAt(i);
            int posicion = (int) caracter;
            // Si la palabra no está
            if (nodoActual._hijos[posicion] == null){
                return False;
            }
            nodoActual = nodoActual._hijos[posicion]; 
        }
        if (nodoActual._esPalabra){
            nodoActual._valorAsociado = nuevoValor;
            return true;
        } else {
            return false;
        }
    }

    public Integer longitud(){
        return this._cantidadDePalabras;
    }

    public boolean borrar(Strign palabra){
        if (! pertenece(palabra)){
            return false;
        }

        // Almacena los nodos y posiciones recorridos
        ArrayList<Object[]> nodosRecorridos = new ArrayList<>();

        NodoTrie nodoActual = this._raiz;
        for (int i = 0; i < palabra.length(); i++) {
            char caracter = palabra.charAt(i);
            int posicion = (int) caracter;
            nodosRecorridos.add(new Object[]{nodoActual, posicion});
            nodoActual = nodoActual._hijos[posicion];        
        }

        nodoActual._esPalabra = False;
        nodoActual._valorAsociado = null;
        this._cantidadDePalabras -= 1;
        
        /*
        Elimina los nodos vacíos y que no son palabras, es decir,
        la idea  es regresar de uno en uno desde el ultimo nodo eliminado
        y si no es palabra y no tiene hijos, se lo borra
        */
        for (int i = nodosRecorridos.size() - 1; i >= 0; i--) {
            if (nodosRecorridos[i][0]._esPalabra || tieneHijos(nodosRecorridos[i][0])){
                break;
            }
            nodosRecorridos[i][0]._hijos[nodosRecorridos[i][1]] = null;
        }
        return true;
    }

    // Método auxiliar a borrar 
    private boolean tieneHijos(NodoTrie nodo){
        boolean res = false;
        for (int i = 0; 1 < nodo.lenght(); i++){
            if (nodo._hijos[i] != null){
                res = true;
                return res;
            }
        return res;
        }
    }

    public void borrarTodo(){
        this._raiz = NodoTrie();
        this._cantidadDePalabras = 0;
    }

    public ArrayList<Strign> obtenerClaves(){
        ArrayList<Strign> claves = new ArrayList<>();
        nodoActual = this._raiz;
        recoleccion(nodoActual, prefijo, claves);
        return claves;
    }

    // Paso recursivo de obtenerClaves
    private void recoleccion(NodoTrie nodo, Strign prefijo,ArrayList<Strign> claves){
        if (nodo._esPalabra){
            claves.add(prefijo);
        }
        for (int i = 0; i < 256; i++) {
            if (nodo.hijos[i] != null) {
                char caracter = (char) i;
                recoleccion(nodo.hijos[i], prefijo + caracter, claves);
            }
        }
    }

}
