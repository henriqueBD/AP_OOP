package entidades.Eventos;

import java.time.LocalDate;

public class EventoExposicao extends Evento {

    private int faixaEtaria;
    private int duracao;
    
    public EventoExposicao(String nome, LocalDate data, String local, int ingressosInteira, int ingressosMeia, double precoCheio,
    int faixaEtaria, int duracao){
        super(nome, data, local, ingressosInteira, ingressosMeia, precoCheio);
        this.faixaEtaria = faixaEtaria;
        this.duracao = duracao;
    }

    @Override
    public String toString(){
        return "Exposicao: " + super.toString() +
            "Faixa Etaria: " + this.faixaEtaria + "\n" +
            "Duracao: " + this.duracao;
    }
    
}
