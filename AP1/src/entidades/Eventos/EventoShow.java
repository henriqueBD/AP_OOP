package entidades.Eventos;
public class EventoShow extends Evento {

    String nomeArtista;
    String generoMusica;

    String local;

    public EventoShow(String nome, String data, String local, int ingressosInteira, int ingressosMeia, double precoCheio,
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
