import java.sql.Array;

public class Caixa {
    public static void main(String[] args) {
        Pagamento pagamento= new Pagamento();

        System.out.println(pagamento.calcularValorDaCompra("credito", new String[]{"cafe,um","queijo,1","combo1,1"}));
    }

}
