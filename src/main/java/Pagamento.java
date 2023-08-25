import java.util.Iterator;

public class Pagamento {
    private Cardapio listaItens = new Cardapio();
    private String formaDePagamentoCredito = "credito";
    private String formaDePagamentoDinheiro = "dinheiro";
    private String formaDePagamentoDebito = "debito";

    public String getFormaDePagamentoCredito() {
        return formaDePagamentoCredito;
    }

    public void setFormaDePagamentoCredito(String formaDePagamentoCredito) {
        this.formaDePagamentoCredito = formaDePagamentoCredito;
    }

    public String getFormaDePagamentoDinheiro() {
        return formaDePagamentoDinheiro;
    }

    public void setFormaDePagamentoDinheiro(String formaDePagamentoDinheiro) {
        this.formaDePagamentoDinheiro = formaDePagamentoDinheiro;
    }

    public String getFormaDePagamentoDebito() {
        return formaDePagamentoDebito;
    }

    public void setFormaDePagamentoDebito(String formaDePagamentoDebito) {
        this.formaDePagamentoDebito = formaDePagamentoDebito;
    }

    public Cardapio getCardapio() {
        return listaItens;
    }
    public void setCardapio(Cardapio cardapio) {
        this.listaItens = cardapio;
    }
    //Auto preenchendo o cardápio
    public void autoPreencher(){
        this.listaItens.adicionarItem("cafe", "Café", 3.00d, false, true);
        this.listaItens.adicionarItem("chantily", "Chantily", 1.50d, true, false);
        this.listaItens.adicionarItem("suco", "Suco Natural", 6.20d, false, true);
        this.listaItens.adicionarItem("sanduiche", "Sanduíche", 6.50d, false, true);
        this.listaItens.adicionarItem("queijo", "Queijo", 2.00d, true, false);
        this.listaItens.adicionarItem("salgado", "Salgado", 7.50d, false, true);
        this.listaItens.adicionarItem("combo1", "1 Suco e 1 Sanduíche", 9.50d, false, false);
        this.listaItens.adicionarItem("combo2", "1 Café e 1 Sanduíche", 7.50d, false, false);
    }
    public String calcularValorDaCompra(String formaDePagamento, String itens){
        autoPreencher();
        System.out.println(this.listaItens);
        //Validando método de pagamento
        if(formaDePagamento.toLowerCase().contains(this.formaDePagamentoCredito)||formaDePagamento.toLowerCase().contains(this.formaDePagamentoDebito)||formaDePagamento.toLowerCase().contains(this.formaDePagamentoDinheiro)){
            System.out.println("Forma de pagamento aceita");
            Iterator iterador = this.listaItens.getCardapio().iterator();
            //Validando se não forem pedidos itens, apresentar mensagem "Não há itens no carrinho de compra!"
            if(itens.toLowerCase().isEmpty()){
                System.out.println("Não há itens no carrinho de compra.");
            }
            //Validando se o item existe na lista
            while(iterador.hasNext()){
                Itens i = (Itens)iterador.next();
                Itens verificador = new Itens(itens);
                if(i.getCodigo().equalsIgnoreCase(verificador.getCodigo())){
                    System.out.println("Item existe");
                }else {
                    System.out.println("Item não existe");
                }
            }
        }else {
            System.out.println("Forma de pagamento não aceita");
        }
        return "Fim do método.";
    }
}
