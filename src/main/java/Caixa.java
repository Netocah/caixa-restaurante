import java.sql.Array;

public class Caixa {
    public static void main(String[] args) {
        Pagamento pagamento= new Pagamento();

        System.out.println(pagamento.calcularValorDaCompra("credito", new String[]{"chantily,1","cafe,4","combo1,1"}));
    }

}
