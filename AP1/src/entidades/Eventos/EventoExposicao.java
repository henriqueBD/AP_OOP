package entidades.Eventos;

public class EventoExposicao extends Evento {

    int faixaEtaria;
    int duracao;
    
    public EventoExposicao(String nome, String data, String local, int ingressosInteira, int ingressosMeia, double precoCheio,
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
