package entidades.Ingressos;

import entidades.Eventos.Evento;

public class IngressoJogo extends Ingresso{

    Evento evento;
    double percentualDescontoTorcedor;

    public IngressoJogo(TipoIngresso tipo, Evento evento, double desconto){
        super(tipo);
        this.evento = evento;
        this.percentualDescontoTorcedor = desconto;
    }

    double getPreco(){
        return evento.precoCheio * (tipo.equals(TipoIngresso.MEIA) ? 0.5:1) * (1-(percentualDescontoTorcedor/100));
    }

    @Override
    public String toString(){
        return super.toString() + 
        "evento: jogo\nDesconto torcedor: " + this.percentualDescontoTorcedor + " %\n" 
        + "preço: " + getPreco() + "\n";
    }

}

