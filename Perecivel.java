import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Perecivel extends Produto{

    LocalDate dataValidade; // data, mes e ano


    public Perecivel(String nome, String id, int quant, double preco, LocalDate dataValidade) {
        super(nome, id, quant, preco);
        this.dataValidade = dataValidade;
    }

    public Perecivel(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    /*
    boolean estaVencido(int dia , int mes , int ano){
        LocalDate hoje = LocalDate.now();
        boolean verif = hoje.isBefore(LocalDate.of(ano, mes, dia));
        return verif;
    }
    */

    public long diasParaVencer() {
        LocalDate hoje = LocalDate.now();
        long diasFaltando = ChronoUnit.DAYS.between(hoje,dataValidade);
        return diasFaltando;
    }

    double aplicaDesconto(double desconto){
        double valorFinal;
        return valorFinal = preco - (preco * desconto/100);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return super.toString() + ", validade=" + dataValidade.format(formatter);
    }
}
