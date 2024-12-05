package aed;

public class Horario {

    private int _hora;
    private int _minutos;

    public Horario(int hora, int minutos) {
        this._hora = hora;
        this._minutos = minutos;
    }

    public int hora() {
        return this._hora;
    }

    public int minutos() {
        return this._minutos;
    }

    @Override
    public String toString() {
        String res = String.valueOf(this.hora()) + ":" + String.valueOf(this.minutos());
        return res;
    }

    @Override
    public boolean equals(Object otro) {
        boolean a = (otro == null);
        boolean b = this.getClass() != otro.getClass();
        if (a || b){
            return false;
        }

        Horario otroHorario = (Horario) otro;
        
        return this.hora() == otroHorario.hora() && this.minutos() == otroHorario.minutos(); 
    }

}
