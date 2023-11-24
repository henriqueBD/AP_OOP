package entidades.Ingressos;

import entidades.Eventos.Evento;

public class IngressoExposicao extends Ingresso{

    private boolean descontoSocial;

    public IngressoExposicao(TipoIngresso tipo, Evento evento, boolean descontoSocial){
        super(tipo, evento);
        this.descontoSocial = descontoSocial;
    }

    @Override
    public double getPreco(){
        if(descontoSocial){
            return 0;
        }
        
        return evento.getPrecoCheio() * (tipo.equals(TipoIngresso.MEIA) ? 0.5:1);
    }

    @Override
    public String toString(){
        return super.toString() + 
        "evento: exposição\nDesconto social: " + (this.descontoSocial ? "sim":"não") + "\n"
        + "preço: " + getPreco() + "\n";
    }
    
}
