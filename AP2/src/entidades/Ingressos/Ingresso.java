package entidades.Ingressos;

import entidades.Eventos.Evento;

abstract public class Ingresso {

    protected Evento evento;
    protected TipoIngresso tipo;

    public Ingresso(TipoIngresso tipo, Evento evento){
        this.tipo = tipo;
        this.evento = evento;
    }

    public double getPreco(){
        return evento.getPrecoCheio() * (tipo.equals(TipoIngresso.MEIA) ? 0.5:1);
    };
    
    @Override 
    public String toString(){
        return "\ntipo: " + (tipo.equals(TipoIngresso.MEIA) ? "meia":"inteira") + "\n";
    }
    
}
