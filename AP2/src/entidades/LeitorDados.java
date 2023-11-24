package entidades;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import entidades.Ingressos.TipoIngresso;

public class LeitorDados {

    private Scanner scanner = new Scanner(System.in);

    public String getString(String mensagem) {
        String res = "";
        boolean loop = true;

        while (loop) {
            try {
                System.out.print(mensagem);
                res = scanner.nextLine();
                loop = false;
            } catch (Exception e) {
                System.out.println("Resposta invalida, tente novamente");
                scanner.nextLine();
            }
        }
        return res;
    }

    public int getInt(String mensagem) {
        int res = 0;
        boolean loop = true;

        while (loop) {
            try {
                System.out.print(mensagem);
                res = scanner.nextInt();
                scanner.nextLine();
                loop = false;
            } catch (InputMismatchException e) {
                System.out.println("Resposta invalida, tente novamente");
                scanner.nextLine();
            }
        }
        return res;
    }

    public double getDouble(String mensagem) {
        double res = 0;
        boolean loop = true;

        while (loop) {
            try {
                System.out.print(mensagem);
                res = scanner.nextDouble();
                scanner.nextLine();
                loop = false;
            } catch (InputMismatchException e) {
                System.out.println("Resposta invalida, tente novamente");
                scanner.nextLine();
            }
        }

        return res;
    }

    public LocalDate getData(String mensagem) {
        LocalDate data = null;
        boolean loop = true;

        while (loop) {
            try {
                System.out.print(mensagem);
                String input = scanner.nextLine();
                data = LocalDate.parse(input);
                loop = false;
            } catch (DateTimeParseException e) {
                System.out.println("Resposta invalida, tente novamente.");
            }
        }
        return data;
    }

    public String getStringOp(String mensagem, String[] op){
        String res = "";
        boolean loop = true;

        try{
            while(loop){
                System.out.print(mensagem);
                res = scanner.nextLine().toLowerCase();
                for (String current : op) {
                    if(current.equals(res)){
                        loop = false;
                        break;
                    }
                }
                if(loop) System.out.println("Resposta invalida, tente novamente.");
            }
        }
        catch(Exception e){
            System.out.println("Resposta invalida, tente novamente");
            scanner.nextLine();
        }
        return res;
    }

    public TipoIngresso getTipoIngresso(){
        String[] tipoIng = {"meia", "inteira"};
        String res = getStringOp("Informe o tipo do ingresso (meia / inteira): ", tipoIng);
        return res.equals("meia") ? TipoIngresso.MEIA : TipoIngresso.INTEIRA;
    }

    public void finalizar(){
        scanner.close();
    }

}