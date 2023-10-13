package entidades.Ingressos;

import entidades.Eventos.Evento;

public class IngressoShow extends Ingresso{

    Evento evento;
    String local;

    public IngressoShow(TipoIngresso tipo, Evento evento, String local){
        super(tipo);
        this.evento = evento;
        this.local = local;
    }

    double getPreco(){
        return evento.precoCheio * (tipo.equals(TipoIngresso.MEIA) ? 0.5:1);
    }

    @Override
    public String toString(){
        return super.toString() + 
        "evento: show\nlocal ingresso: " + this.local + "\n"
        + "preço: " + getPreco() + "\n" ;
    }
    
}
