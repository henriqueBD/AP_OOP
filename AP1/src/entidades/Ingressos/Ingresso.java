package entidades.Ingressos;

abstract public class Ingresso {

    TipoIngresso tipo;

    public Ingresso(TipoIngresso tipo){
        this.tipo = tipo;
    }

    abstract double getPreco();

    @Override 
    public String toString(){
        return "\ntipo: " + (tipo.equals(TipoIngresso.MEIA) ? "meia":"inteira") + "\n";
    }
    
}
