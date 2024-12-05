package aed;

class ArregloRedimensionableDeRecordatorios implements SecuenciaDeRecordatorios {

    private Recordatorio [] _recordatorios;

    public ArregloRedimensionableDeRecordatorios() {
        this._recordatorios = new Recordatorio[0];
    }


    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {  

        Recordatorio [] nuevos_recordatorios = new Recordatorio[vector.longitud()];        
        for(int i = 0; i < nuevos_recordatorios.length; i++){
            nuevos_recordatorios[i] = vector.obtener(i);
        }
        this._recordatorios = nuevos_recordatorios;
    }


    public int longitud() {
        return this._recordatorios.length;
    }


    public void agregarAtras(Recordatorio i) {

        if (this._recordatorios.length == 0){ 
            /*Cuando esta vacío crea una nueva secuencia 
            y agrega el que recibe evitando aliasing*/
            Recordatorio [] nuevos_recordatorios = new Recordatorio[1];            
            nuevos_recordatorios[0] = new Recordatorio(
                i.mensaje(), 
                new Fecha(i.fecha().dia(), i.fecha().mes()), 
                new Horario(i.horario().hora(), i.horario().minutos())
            );            
            this._recordatorios = nuevos_recordatorios;

        } else{ 
            /*Cuando hay elementos copia los existentes 
            evitando aliasing a través de obtener()*/
            Recordatorio [] nuevos_recordatorios = new Recordatorio[this._recordatorios.length + 1];      
            for (int j = 0; j < this._recordatorios.length; j++){    
                nuevos_recordatorios[j] = this.obtener(j);
            }
            //Copia el nuevo evitando aliasign
            nuevos_recordatorios[nuevos_recordatorios.length-1] = new Recordatorio(
                i.mensaje(), 
                new Fecha(i.fecha().dia(), i.fecha().mes()), 
                new Horario(i.horario().hora(), i.horario().minutos())
            );
            this._recordatorios = nuevos_recordatorios;
        }        
    }


    public Recordatorio obtener(int i) {
        Recordatorio aux = this._recordatorios[i];
        Recordatorio res = new Recordatorio(
            aux.mensaje(), 
            new Fecha(aux.fecha().dia(), aux.fecha().mes()), 
            new Horario(aux.horario().hora(), aux.horario().minutos())
        );
        return res;
    }


    public void quitarAtras() {
        Recordatorio [] nuevos_recordatorios = new Recordatorio[this._recordatorios.length - 1];
        for (int j = 0; j < nuevos_recordatorios.length; j++){
            nuevos_recordatorios[j] = this.obtener(j);
        }
        this._recordatorios = nuevos_recordatorios;
    }


    public void modificarPosicion(int indice, Recordatorio valor) {
        
        Recordatorio [] nuevos_recordatorios = new Recordatorio[this._recordatorios.length];
        
        for (int j = 0; j < indice; j++){
            nuevos_recordatorios[j] = this.obtener(j);
        }

        Recordatorio r = valor;
        Recordatorio rec = new Recordatorio(
            r.mensaje(), 
            new Fecha(r.fecha().dia(), r.fecha().mes()), 
            new Horario(r.horario().hora(), r.horario().minutos())
        );

        nuevos_recordatorios[indice] = rec;

        for (int j = indice +1; j < nuevos_recordatorios.length; j++){
            nuevos_recordatorios[j] = this.obtener(j);
        }
        this._recordatorios = nuevos_recordatorios;
    }

    
    public ArregloRedimensionableDeRecordatorios copiar() {

        ArregloRedimensionableDeRecordatorios res = new ArregloRedimensionableDeRecordatorios();
        for (int i = 0; i < this._recordatorios.length; i++){
            res.agregarAtras(this.obtener(i)); 
        }
        return res;      
    }
    
}
