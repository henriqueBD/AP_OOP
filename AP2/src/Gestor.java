import java.time.LocalDate;

import entidades.LeitorDados;
import entidades.Eventos.Evento;
import entidades.Eventos.EventoDAO;
import entidades.Ingressos.IngressoDAO;
import entidades.Ingressos.TipoIngresso;

public class Gestor {
    static LeitorDados leitor = new LeitorDados();

    static EventoDAO eventoDAO = new EventoDAO();
    static IngressoDAO ingressoDAO = new IngressoDAO();
    
    public static void main(String[] args) throws Exception {

        int op;
        String tipoEvento = "";
        Boolean loop = true;
        String nomeEvento;
        Evento eventoTmp;
        while(loop){
            System.out.print("""   
                        ********************************************
                                   Selecione a operação:
                        (1) Cadastrar evento
                        (2) Buscar evento
                        (3) Vender o ingresso
                        (4) Listar eventos cadastrados
                        (5) Exibir quantidade de ingressos disponíveis
                        (6) Excluir um evento
                        (7) Atualizar um evento
                        (8) Encerrar aplicação
                        ********************************************
                        """);
            op = leitor.getInt("");
            switch(op){

                case 1:
                //Cadastrar evento
                String[] tipos = {"show", "jogo", "exposicao"};
                tipoEvento = leitor.getStringOp("Informe o tipo do evento: ", tipos);

                String nome = leitor.getString("Nome do evento: ");

                if(eventoDAO.getEvento(nome) != null){
                    String[] opcoesTmp = {"sim", "nao"};
                    System.out.println("Ja existe um evento com esse nome. Sobrescrever esse evento?");
                    if(leitor.getStringOp("(sim/nao): ",opcoesTmp).equals("nao")) break;
                }

                LocalDate data = leitor.getData("Data do evento (yyyy-mm-dd): ");
                String local = leitor.getString("Local do evento: ");
                int ingressosInteira = leitor.getInt("Numero de ingressos inteira do evento: ");
                int ingressosMeia = leitor.getInt("Numero de ingressos meia do evento: ");
                double precoCheio = leitor.getDouble("Preço cheio do evento: ");

                switch(tipoEvento){
                    case "show":
                        String nomeArtista = leitor.getString("Nome do artista: ");
                        String generoMusica = leitor.getString("Genero musical: ");
                        eventoDAO.cadastrarEventoShow(nome, data, local, ingressosInteira, ingressosMeia, precoCheio, nomeArtista, generoMusica);
                        break;

                    case "jogo":
                        String esporte = leitor.getString("Esporte: ");
                        String equipes = leitor.getString("Equipes: ");
                        eventoDAO.cadastrarEventoJogo(nome, data, local, ingressosInteira, ingressosMeia, precoCheio, esporte, equipes);
                        break;

                    case "exposicao":
                        int faixaEtaria = leitor.getInt("Faixa etaria: ");
                        int duracao = leitor.getInt("Duração em dias: ");
                        eventoDAO.cadastrarEventoExposicao(nome, data, local, ingressosInteira, ingressosMeia, precoCheio, faixaEtaria, duracao);
                        break;
                    }

                System.out.println("Evento Cadastrado com sucesso");
                continuar();
                break;

                case 2:
                //Buscar evento
                nomeEvento = leitor.getString("Insira o nome do evento: ");
                eventoTmp = eventoDAO.getEvento(nomeEvento);
                if(eventoTmp != null) System.out.println(eventoTmp);
                else System.out.println("Evento não encontrado");
                continuar();
                break;

                case 3:
                //vender ingresso
                if(eventoDAO.mapVazio()){
                    System.out.println("Cadastre ao menos um evento antes de vender um ingresso");
                    continuar();
                    break;
                }

                nomeEvento = leitor.getString("Insira o nome do evento: ");
                eventoTmp = eventoDAO.getEvento(nomeEvento);
                if(eventoTmp == null){
                    System.out.println("Evento não encontrado");
                    continuar();
                    break;
                }

                String nomeClasse = eventoTmp.getClass().getSimpleName();;
                TipoIngresso tipo = leitor.getTipoIngresso();

                if (!eventoDAO.isIngressoDisponivel(tipo, eventoTmp)){
                    System.out.println("Ingresso não disponivel para esse evento");
                    continuar();
                    break;
                };

                //"entidades.Eventos."
                
                switch (nomeClasse) {
                    case "EventoShow":
                        String[] tiposLocal = {"pista", "camarote"};
                        String localShow = leitor.getStringOp("Informe o local do ingresso (pista / camarote): ", tiposLocal);
                        if(confirmarCompra()){
                            ingressoDAO.venderIngressoShow(tipo, eventoTmp, localShow);
                            eventoDAO.venderIngresso(tipo, eventoTmp);
                            System.out.println("Compra confirmada");
                        }
                        else System.out.println("Compra cancelada");
                        break;

                    case "EventoJogo":
                        double descontoTorcedor;
                        while(true){
                            descontoTorcedor = leitor.getDouble("Informe o desconto do torcedor (entre 0 a 100 %): ");
                            if (descontoTorcedor >= 0 && descontoTorcedor <= 100)
                                break;
                            System.out.println("Valor invalido tente novamente");
                        }
                        if(confirmarCompra()){
                            ingressoDAO.venderIngressoJogo(tipo, eventoTmp, descontoTorcedor);
                            eventoDAO.venderIngresso(tipo, eventoTmp);
                            System.out.println("Compra confirmada");
                        }
                        else System.out.println("Compra cancelada");
                        break;

                    case "EventoExposicao":
                        String[] desconto = {"sim", "nao"};
                        boolean descontoSocial = leitor.getStringOp("Informe se há desconto social (sim/nao): ",desconto).equals("sim");
                        ingressoDAO.venderIngressoExposicao(tipo, eventoTmp, descontoSocial);
                        if(confirmarCompra()){
                            ingressoDAO.venderIngressoExposicao(tipo, eventoTmp, descontoSocial);
                            eventoDAO.venderIngresso(tipo, eventoTmp);
                            System.out.println("Compra confirmada");
                        }
                        else System.out.println("Compra cancelada");
                        break;

                    case "Evento":
                        break;
                
                    default:
                        break;
                }
                System.out.println("Informações do ingresso:");
                System.out.println(ingressoDAO.getIngresso());
                continuar();
                break;

                case 4:
                //Listar eventos
                if(eventoDAO.mapVazio()) System.out.println("Cadastre ao menos um evento antes de listar");
                else eventoDAO.listarEventos();
                continuar();
                break;

                case 5:
                //Ver ingressos disponiveis
                if(eventoDAO.mapVazio())
                    System.out.println("Cadastre um evento antes de ver os ingressos disponíveis");
                else{
                    nomeEvento = leitor.getString("Insira o nome do evento: ");
                    eventoTmp = eventoDAO.getEvento(nomeEvento);
                    if(eventoTmp != null) eventoTmp.printIngressosRestantes();
                    else System.out.println("Evento não encontrado");
                }
                continuar();
                break;

                case 6:
                //excluir evento
                if(eventoDAO.mapVazio())
                    System.out.println("Cadastre um evento antes de excluir");
                else{
                    nomeEvento = leitor.getString("Insira o nome do evento: ");
                    if(eventoDAO.excluirEvento(nomeEvento) != null) System.out.println("Evento excluido");
                    else System.out.println("Evento não encontrado");
                }
                continuar();
                break;

                case 7:
                //atualizar evento
                if(eventoDAO.mapVazio())
                    System.out.println("Cadastre um evento antes de atualizar");
                else{
                    nomeEvento = leitor.getString("Insira o nome do evento: ");
                    eventoTmp = eventoDAO.getEvento(nomeEvento);
                    if(eventoTmp != null){
                        String localUpdate = leitor.getString("Insira o novo local do evento: ");
                        LocalDate dataUpdate = leitor.getData("Insira a nova data do evento (yyyy-mm-dd): ");
                        eventoDAO.updateEvento(nomeEvento, localUpdate, dataUpdate);
                    }
                    else System.out.println("Evento não encontrado");
                }
                continuar();
                break;

                case 8:
                eventoDAO.finalizar();
                leitor.finalizar();
                System.out.println("Aplicação encerrada");
                loop = false;
                break;

                default:
                System.out.println("Operação invalida");
            }
        }
    }

    static boolean confirmarCompra(){
        String[] opcoesCompra = {"sim", "nao"};
        return leitor.getStringOp("Confirmar compra? (sim/nao): ", opcoesCompra).equals("sim");
    }

    static void continuar(){
        leitor.getString("\nAperte enter para continuar");
    }
}
