package entidades.Ingressos;

import entidades.Eventos.Evento;

public class IngressoJogo extends Ingresso{

    private double percentualDescontoTorcedor;

    public IngressoJogo(TipoIngresso tipo, Evento evento, double desconto){
        super(tipo, evento);
        this.percentualDescontoTorcedor = desconto;
    }

    @Override
    public double getPreco(){
        return evento.getPrecoCheio() * (tipo.equals(TipoIngresso.MEIA) ? 0.5:1) * (1-(percentualDescontoTorcedor/100));
    }

    @Override
    public String toString(){
        return super.toString() + 
        "evento: jogo\nDesconto torcedor: " + this.percentualDescontoTorcedor + " %\n" 
        + "preço: " + getPreco() + "\n";
    }

}

