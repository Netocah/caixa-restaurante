import java.sql.Array;

public class Caixa {
    public static void main(String[] args) {
        Pagamento pagamento= new Pagamento();

        System.out.println(pagamento.calcularValorDaCompra("credito", new String[]{"combo1,1","suco,1","combo1,1", "combo2,1"}));
    }

}
