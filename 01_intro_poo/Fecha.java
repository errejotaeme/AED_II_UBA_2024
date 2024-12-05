package aed;

public class Fecha {

    private int _dia;
    private int _mes;

    public Fecha(int dia, int mes) {
        this._dia = dia;
        this._mes = mes;
    }

    public Fecha(Fecha fecha) {
        this._dia = fecha.dia();
        this._mes = fecha.mes();
    }

    public Integer dia() {
        return this._dia;
    }

    public Integer mes() {
        return this._mes;
    }

    @Override
    public String toString() {
        String res = this.dia().toString() + "/" + this.mes().toString();
        return res;
    }

    @Override
    public boolean equals(Object otra) {
        boolean a = (otra == null);
        boolean b = this.getClass() != otra.getClass();
        if (a || b){
            return false;
        }

        Fecha otraFecha = (Fecha) otra;
        
        return this.dia() == otraFecha.dia() && this.mes() == otraFecha.mes();  
    }


    public void incrementarDia() {
        boolean excedeEnDias = this.dia() + 1 > this.diasEnMes(this._mes);
        boolean excedeMeses = this.mes() > 11;
        
        if (excedeMeses && excedeEnDias) {
            this._mes = 1;
            this._dia = 1;        
        } else if (excedeEnDias){
            this._mes = this._mes + 1;
            this._dia = 1;
        } else {
            this._dia = this._dia + 1;
        }
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

}
