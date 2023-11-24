package entidades.Eventos;

import java.time.LocalDate;

public class EventoJogo extends Evento{

    private String esporte;
    private String equipes;

    public EventoJogo(String nome, LocalDate data, String local, int ingressosInteira, int ingressosMeia, double precoCheio,
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
