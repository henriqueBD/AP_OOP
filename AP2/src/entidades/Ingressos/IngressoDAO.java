package entidades.Ingressos;

import entidades.Eventos.Evento;

public class IngressoDAO {

    private Ingresso ingresso;

    public void venderIngressoShow(TipoIngresso tipo, Evento evento, String local){
        ingresso = new IngressoShow(tipo, evento, local);
    }

    public void venderIngressoJogo(TipoIngresso tipo, Evento evento, double descontoTorcedor){
        ingresso = new IngressoJogo(tipo, evento, descontoTorcedor);
    }

    public void venderIngressoExposicao(TipoIngresso tipo, Evento evento, boolean descontoSocial){
        ingresso = new IngressoExposicao(tipo, evento, descontoSocial);
    }

    public Ingresso getIngresso(){
        return ingresso;
    }
    
}
