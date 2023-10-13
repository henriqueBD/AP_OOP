import java.util.Scanner;

import entidades.Eventos.Evento;
import entidades.Eventos.EventoExposicao;
import entidades.Eventos.EventoJogo;
import entidades.Eventos.EventoShow;
import entidades.Ingressos.Ingresso;
import entidades.Ingressos.IngressoExposicao;
import entidades.Ingressos.IngressoJogo;
import entidades.Ingressos.IngressoShow;
import entidades.Ingressos.TipoIngresso;

//Integrantes: Hudson Guilherme (202208385362) / Henrique Barbosa (202208818609) / João Marcio (202208385001) / Victor Hwang (202208766005)

public class App {

    static Scanner scanner = new Scanner(System.in);
    static Evento evento;
    static Ingresso ingresso;
    
    public static void main(String[] args) throws Exception {

        int op;
        String tipoEvento = "";
        Boolean loop = true;
        while(loop){
            System.out.print("""   
                        ********************************************
                                   Selecione a operação:
                        (1) Cadastrar evento
                        (2) Informações do evento
                        (3) Vender o ingresso
                        (4) Informações do ultimo ingresso vendido
                        (5) Exibir quantidade de ingressos disponíveis
                        (6) Encerrar aplicação
                        ********************************************
                        """);
            op = scanner.nextInt();
            scanner.nextLine();
            switch(op){

                case 1:
                tipoEvento = getTipoEvento();
                cadastrarEvento(tipoEvento);
                break;

                case 2:
                if(evento == null)
                    System.out.println("Cadastre um evento antes de exibir as informações");
                else
                    System.out.print("\nUltimo evento cadastrado:\n" + evento + "\n");
                    continuar();
                
                break;

                case 3:
                if(evento == null)
                    System.out.println("Cadastre um evento antes de vender um ingresso");
                else
                    realizarVendaIngresso(tipoEvento);
                
                break;

                case 4:
                if(ingresso == null)
                    System.out.println("Venda um ingresso antes de exibir as informações");
                else
                    System.out.println("Ultimo ingresso vendido:\n" + ingresso);
                    continuar();

                break;

                case 5:
                if(evento == null)
                    System.out.println("Cadastre um evento antes de ver os ingressos disponíveis");
                else
                    evento.printIngressosRestantes();
                    continuar();
                
                break;

                case 6:
                System.out.println("Aplicação encerrada");
                loop = false;
                break;

                default:
                System.out.println("Operação invalida");

            }
        }

        scanner.close();
    }

    static String getTipoEvento(){
        String res;
        while(true){
            System.out.print("Informe o tipo do evento: ");
            res = scanner.nextLine().toLowerCase();
            
            if (res.equals("show") || res.equals("jogo") || res.equals("exposicao")){
                break;
            }
            System.out.println("Tipo invalido, tente novamente");
        }
        return res;
    }

    static void cadastrarEvento(String res){
        System.out.print("Nome do evento: ");
        String nome = scanner.nextLine();
        System.out.print("Data do evento: ");
        String data = scanner.nextLine();
        System.out.print("Local do evento: ");
        String local = scanner.nextLine();
        System.out.print("Numero de ingressos inteira do evento: ");
        int ingressosInteira = scanner.nextInt();
        System.out.print("Numero de ingressos meia do evento: ");
        int ingressosMeia = scanner.nextInt();
        System.out.print("Preço cheio do evento: ");
        double precoCheio = scanner.nextDouble();
        scanner.nextLine();

        switch(res){
            case "show":
                System.out.print("Nome do artista: ");
                String nomeArtista = scanner.nextLine();
                System.out.print("Genero musical: ");
                String generoMusica = scanner.nextLine();
                evento = new EventoShow(nome, data, local, ingressosInteira, ingressosMeia, precoCheio, nomeArtista, generoMusica);
                break;

            case "jogo":
                System.out.print("Esporte: ");
                String esporte = scanner.nextLine();
                System.out.print("Equipes: ");
                String equipes = scanner.nextLine();
                evento = new EventoJogo(nome, data, local, ingressosInteira, ingressosMeia, precoCheio, esporte, equipes);
                break;

            case "exposicao":
                System.out.print("Faixa etaria: ");
                int faixaEtaria = scanner.nextInt();
                System.out.print("Duração em dias: ");
                int duracao = scanner.nextInt();
                evento = new EventoExposicao(nome, data, local, ingressosInteira, ingressosMeia, precoCheio, faixaEtaria, duracao);
                break;
            }

        System.out.print("Evento cadastrado com sucesso");
        continuar();
    }

    static TipoIngresso escolhaTipo(){
        String input;
        while(true){
            System.out.print("Informe o tipo do ingresso (meia / inteira): ");
            input = scanner.nextLine();
            if(input.equals("meia"))
                return TipoIngresso.MEIA;
            else if (input.equals("inteira"))
                return TipoIngresso.INTEIRA;
            else
                System.out.println("tipo invalido, tente novamente: ");
        }
    }

    static void realizarVendaIngresso(String tipoEvento){

        TipoIngresso tipo = escolhaTipo();

        if(!evento.isIngressoDisponivel(tipo, 1)){
            System.out.print("Ingresso indisponivel");
            continuar();
            return;
        }

        switch(tipoEvento){
            case "show":
                String localShow;
                while(true){
                    System.out.print("Informe o local do ingresso (pista / camarote): ");
                    localShow = scanner.nextLine().toLowerCase();
                    if(localShow.equals("pista") || localShow.equals("camarote"))
                        break;     
                    System.out.println("Local invalido, tente novamente");
                }
                confirmarCompraIngresso(new IngressoShow(tipo, evento, localShow), tipo);
                break;

            case "jogo":
            double descontoTorcedor;
                while(true){
                    System.out.print("Informe o desconto do torcedor (entre 0 a 100 %): ");
                    descontoTorcedor = scanner.nextDouble();
                    scanner.nextLine();
                    if (descontoTorcedor >= 0 && descontoTorcedor <= 100)
                        break;
                    System.out.println("Valor invalido tente novamente");
                }
                confirmarCompraIngresso(new IngressoJogo(tipo, evento, descontoTorcedor), tipo);
                break;

            case "exposicao":
                String tempE;
                while(true){
                    System.out.print("Informe se há desconto social (sim/não): ");
                    tempE = scanner.nextLine().toLowerCase();
                    if(tempE.equals("sim") || tempE.equals("nao"))
                        break;     
                    System.out.println("Resposta invalida, tente novamente");
                }
                boolean descontoSocial = tempE.equals("sim");
                confirmarCompraIngresso(new IngressoExposicao(tipo, evento, descontoSocial), tipo);
                break;
        }
    }

    static void confirmarCompraIngresso(Ingresso temp, TipoIngresso tipo){
        System.out.print("\nInformações do ingresso: " + temp + "Confirmar a compra? (sim/não): ");
        if(scanner.nextLine().toLowerCase().equals("sim")){
            ingresso = temp;
            evento.venderIngresso(tipo);
            System.out.print("Compra confirmada");
        }
        else
            System.out.print("Compra cancelada");
        continuar();
    }

    static void continuar(){
        System.out.println("\nAperte enter para continuar");
        scanner.nextLine();
    }
}