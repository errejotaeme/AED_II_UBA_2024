package aed;

class Funciones {
    int cuadrado(int x) {
        int res;
        res = x * x;
        return res;
    }
    

    double distancia(double x, double y) {
        double res;
        res = Math.sqrt(x*x + y*y);
        return res;
    }

    boolean esPar(int n) {
        boolean res = false;
        if (n % 2 == 0){
            res = true;
        }
        return res;
    }

    boolean esBisiesto(int n) {
        boolean res = false;
        if (((n % 4 == 0) && (n % 100 != 0)) || (n % 400 == 0)){
            res = true;
        }
        return res;
    }

    int factorialIterativo(int n) {
        int res = 1;
        if (n == 0){
            return res;
        }
        for (int i=1; i<n; i++){
            res += res*i;
        } 
        return res;
    }

    int factorialRecursivo(int n) {
        int res;
        if (n == 0){
            return 1;
        } else {
            res = n * factorialRecursivo(n-1);
        }         
        return res; 
        
    }

    boolean esPrimo(int n) {
        boolean res = false;
        int divisores = 0; 
        for(int i=n; i>0; i--){
            if (n%i == 0){
                divisores += 1;
            }
        }
        if (divisores == 2){
            res = true;
        }
        return res;
    }

    int sumatoria(int[] numeros) {
        int res = 0;
        for (int i=0; i < numeros.length; i++){
            res += numeros[i];
        }
        return res;
    }

    int busqueda(int[] numeros, int buscado) {
        int res = 0;
        for (int i=0; i < numeros.length; i++){
            if (buscado == numeros[i]){
                res = i;
            }
        }
        return res;
    }

    boolean tienePrimo(int[] numeros) {
        boolean res = false;
        for (int i=0; i < numeros.length; i++){
            if (esPrimo(numeros[i])){
                res = true;
            }
        }
        return res;
    }

    boolean todosPares(int[] numeros) {
        boolean res = true;
        for(int i=0; i < numeros.length; i++){
            if(!esPar(numeros[i])){
                res = false;
            }
        }
        return res;
    }

    boolean esPrefijo(String s1, String s2) {
        boolean res = true;
        if(s1.length() > s2.length()){
            return false;
        }
        for(int i=0; i < s1.length(); i++){
            char a = s1.charAt(i);
            char b = s2.charAt(i);
            if (a != b){
                res = false;
            }
        }
        return res;
    }

    boolean esSufijo(String s1, String s2) {
        boolean res = true;
        if (s2.length() < s1.length()){
            return false;
        }
        for (int i=0; i < s1.length(); i++){
            char a = s1.charAt(s1.length() - 1 - i);
            char b = s2.charAt(s2.length() - 1 - i);
            if (a != b){
                res = false;
            }
        }
        return res;
    }
}
