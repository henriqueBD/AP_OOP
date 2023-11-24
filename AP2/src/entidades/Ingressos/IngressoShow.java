package entidades.Ingressos;

import entidades.Eventos.Evento;

public class IngressoShow extends Ingresso{

    private String local;

    public IngressoShow(TipoIngresso tipo, Evento evento, String local){
        super(tipo, evento);
        this.local = local;
    }

    @Override
    public String toString(){
        return super.toString() + 
        "evento: show\nlocal ingresso: " + this.local + "\n"
        + "preço: " + getPreco() + "\n" ;
    }
    
}
