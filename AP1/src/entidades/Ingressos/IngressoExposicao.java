package entidades.Ingressos;

import entidades.Eventos.Evento;

public class IngressoExposicao extends Ingresso{

    Evento evento;
    boolean descontoSocial;

    public IngressoExposicao(TipoIngresso tipo, Evento evento, boolean descontoSocial){
        super(tipo);
        this.evento = evento;
        this.descontoSocial = descontoSocial;
    }

    double getPreco(){
        if(descontoSocial){
            return 0;
        }

        if(tipo.equals(TipoIngresso.MEIA)){
            return evento.precoCheio/2;
        }
        else if(tipo.equals(TipoIngresso.INTEIRA)){
            return evento.precoCheio;
        }

        return -1;
    }

    @Override
    public String toString(){
        return super.toString() + 
        "evento: exposição\nDesconto social: " + (this.descontoSocial ? "sim":"não") + "\n"
        + "preço: " + getPreco() + "\n";
    }
    
}
