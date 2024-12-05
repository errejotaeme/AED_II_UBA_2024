package aed;

public class Agenda {

    private Fecha _fecha;
    private ArregloRedimensionableDeRecordatorios _avisos;

    public Agenda(Fecha fechaActual) {
        this._fecha = new Fecha(fechaActual);
        this._avisos = new ArregloRedimensionableDeRecordatorios();
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        this._avisos.agregarAtras(recordatorio);
    }

    @Override
    public String toString() {
        String f = this.fechaActual().toString().concat("\n");
        String f_divisor = f.concat("=====\n");
        String aux = ""; 
        for (int i=0; i < this._avisos.longitud(); i++){
            if(this._avisos.obtener(i).fecha().dia() == this.fechaActual().dia()){
                String s = this._avisos.obtener(i).toString();
                aux = aux.concat(s.concat("\n"));
            }            
        }
        String res = f_divisor.concat(aux);
        return res;
    }

    public void incrementarDia() {
        this._fecha.incrementarDia();
    }

    public Fecha fechaActual() {
        Fecha res = new Fecha(this._fecha);
        return res;
    }

}
