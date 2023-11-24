package entidades.Eventos;

import java.time.LocalDate;

public class EventoShow extends Evento {

    private String nomeArtista;
    private String generoMusica;

    public EventoShow(String nome, LocalDate data, String local, int ingressosInteira, int ingressosMeia, double precoCheio,
        String nomeArtista, String generoMusica){
        super(nome, data, local, ingressosInteira, ingressosMeia, precoCheio);
        this.nomeArtista = nomeArtista;
        this.generoMusica = generoMusica;
    }

    @Override
    public String toString(){
        return "Show: " + super.toString() +
            "Nome do Artista: " + this.nomeArtista + "\n" +
            "Genero Musical: " + this.generoMusica;
    }
}
