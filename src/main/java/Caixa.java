public class Caixa {
    public static void main(String[] args) {
        Pagamento pagamento= new Pagamento();

        System.out.println(pagamento.calcularValorDaCompra("credito", new String[]{"combo1,5","combo1,1", "chantily,1", "cafe,1"}));
    }

}
