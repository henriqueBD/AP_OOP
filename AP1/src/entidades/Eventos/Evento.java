package entidades.Eventos;

import entidades.Ingressos.TipoIngresso;

abstract public class Evento {
    String nome;
    String data;
    String local;

    int ingressosInteira;
    int ingressosMeia;

    int ingressosInteiraCurrent;
    int ingressosMeiaCurrent;

    public double precoCheio;

    public Evento(String nome, String data, String local, int ingressosInteira, int ingressosMeia, double precoCheio){
        this.nome = nome;
        this.data = data;
        this.local = local;
        this.ingressosInteira = ingressosInteira;
        this.ingressosMeia = ingressosMeia;
        this.ingressosInteiraCurrent = ingressosInteira;
        this.ingressosMeiaCurrent = ingressosMeia;
        this.precoCheio = precoCheio;
    }


    public boolean isIngressoDisponivel(TipoIngresso tipo, int quantidade){
        if(tipo.equals(TipoIngresso.MEIA)){
            return ingressosMeiaCurrent >= quantidade;
        }
        else if(tipo.equals(TipoIngresso.INTEIRA)){
            return ingressosInteiraCurrent >= quantidade;
        }
        System.out.println("Tipo invalido de ingresso");
        return false;
    }

    public void venderIngresso(TipoIngresso tipo){
        if(tipo.equals(TipoIngresso.MEIA)){
            ingressosMeiaCurrent--;
            return;
        }
        else if(tipo.equals(TipoIngresso.INTEIRA)){
            ingressosInteiraCurrent--;
            return;
        }
        System.out.println("Tipo invalido de ingresso");
        return;
    }

    public void printIngressosRestantes(){
        System.out.println("Numero de inteiras restantes: " + this.ingressosInteiraCurrent +
        "\nNumero de meias restantes: " + this.ingressosMeiaCurrent);
    }

    @Override
    public String toString(){
        return this.nome + "\n"+
            "Data: " + this.data +
            "\nLocal: " + this.local + 
            "\nNumero total de Ingressos Inteira: " + this.ingressosInteira + 
            "\nNumero total de Ingressos Meia: " + this.ingressosMeia +
            "\nPreco Cheio do Ingresso: " + this.precoCheio + "\n";
    }
}
