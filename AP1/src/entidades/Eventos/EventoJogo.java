package entidades.Eventos;

public class EventoJogo extends Evento{

    String esporte;
    String equipes; //TODO: ver se ta certo

    public EventoJogo(String nome, String data, String local, int ingressosInteira, int ingressosMeia, double precoCheio,
    String esporte, String equipes){
        super(nome, data, local, ingressosInteira, ingressosMeia, precoCheio);
        this.esporte = esporte;
        this.equipes = equipes;
    }

    @Override
    public String toString(){
        return "Jogo: " + super.toString() +
            "Esporte: " + this.esporte + "\n" +
            "Equipes: " + this.equipes;
    }
}
