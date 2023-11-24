package entidades.Eventos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import entidades.Ingressos.TipoIngresso;

public class EventoDAO {

    private Map<String, Evento> eventosMap = new HashMap<>();

    public void cadastrarEventoShow(String nome, LocalDate data, String local, int ingressosInteira, 
    int ingressosMeia, double precoCheio, String nomeArtista, String generoMusica){
        Evento evento = new EventoShow(nome, data, local, ingressosInteira, ingressosMeia, precoCheio, nomeArtista, generoMusica);
        eventosMap.put(nome, evento);
    }

    public void cadastrarEventoJogo(String nome, LocalDate data, String local, int ingressosInteira, 
    int ingressosMeia, double precoCheio, String esportes, String equipes){
        Evento evento = new EventoJogo(nome, data, local, ingressosInteira, ingressosMeia, precoCheio, esportes, equipes);
        eventosMap.put(nome, evento);
    }

    public void cadastrarEventoExposicao(String nome, LocalDate data, String local, int ingressosInteira, 
    int ingressosMeia, double precoCheio, int faixaEtaria, int duracao){
        Evento evento = new EventoExposicao(nome, data, local, ingressosInteira, ingressosMeia, precoCheio, faixaEtaria, duracao);
        eventosMap.put(nome, evento);
    }   

    public Evento getEvento(String nome){
        return eventosMap.get(nome);
    }

    public Evento excluirEvento(String nome){
        return eventosMap.remove(nome);
    }

    public void listarEventos(){
        for (Map.Entry<String, Evento> entry : eventosMap.entrySet()) {
            System.out.println(entry.getValue());
            System.out.println("--------------------");
        }
    }

    public void updateEvento(String nome, String localUpdate, LocalDate dataUpdate){
        Evento procura = eventosMap.get(nome);
        procura.atualizarDados(localUpdate, dataUpdate);
    }

    public boolean mapVazio(){
        return eventosMap.isEmpty();
    }

    public boolean isIngressoDisponivel(TipoIngresso tipo, Evento evento){
        return evento.isIngressoDisponivel(tipo, 1);
    }

    public void venderIngresso(TipoIngresso tipo, Evento evento){
        evento.venderIngresso(tipo);
    }

    public void finalizar(){
        try (FileWriter fw = new FileWriter("DB.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw)) {

            for (Map.Entry<String, Evento> evento : eventosMap.entrySet()) {
                out.print(evento.getValue().toString());
                out.print("\n---------------------\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
