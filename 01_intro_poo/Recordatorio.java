package aed;

public class Recordatorio {

    private String _mensaje;
    private Fecha _fecha;
    private Horario _horario;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        this._mensaje = mensaje;
        this._fecha = new Fecha(fecha);
        this._horario = new Horario(horario.hora(), horario.minutos());
    }

    public Horario horario() {
        Horario res = new Horario(this._horario.hora(), this._horario.minutos());
        return res;
    }

    public Fecha fecha() {
        Fecha res = new Fecha(this._fecha);
        return res;
    }

    public String mensaje() {
        String res = this._mensaje;
        return res;
    }

    @Override
    public String toString() {
        String res = this.mensaje() + " @ " + this._fecha.toString() + " " + this._horario.toString();
        return res;
    }


    @Override
    public boolean equals(Object otro) {
        boolean a = (otro == null);
        boolean b = this.getClass() != otro.getClass();
        if (a || b){
            return false;
        }

        //Comparacion de los atributos
        Recordatorio otroRecordatorio = (Recordatorio) otro;

        String s1 = this.mensaje();
        String s2 = otroRecordatorio.mensaje();

        boolean c1 = s1 == s2; //Deben ser iguales como cadenas
        boolean c2 = this.fecha().dia() == otroRecordatorio.fecha().dia();
        boolean c3 = this.fecha().mes() == otroRecordatorio.fecha().mes();  
        boolean c4 = this.horario().hora() == otroRecordatorio.horario().hora();
        boolean c5 = this.horario().minutos() == otroRecordatorio.horario().minutos();

        boolean res = c1 && c2 && c3 && c4 && c5;
        
        return res;  
    }

}
