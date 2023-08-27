import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Pagamento {
    private Cardapio cardapio = new Cardapio();
    private String formaDePagamentoCredito = "credito";
    private String formaDePagamentoDinheiro = "dinheiro";
    private String formaDePagamentoDebito = "debito";
    private Pedido pedido = new Pedido();

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
        return cardapio;
    }

    public void setCardapio(Cardapio cardapio) {
        this.cardapio = cardapio;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    //Auto preenchendo o cardápio
    public void autoPreencher() {
        this.cardapio.adicionarItem("cafe", "Café", 3.00d, false, true);
        this.cardapio.adicionarItem("chantily", "Chantily", 1.50d, true, false);
        this.cardapio.adicionarItem("suco", "Suco Natural", 6.20d, false, true);
        this.cardapio.adicionarItem("sanduiche", "Sanduíche", 6.50d, false, true);
        this.cardapio.adicionarItem("queijo", "Queijo", 2.00d, true, false);
        this.cardapio.adicionarItem("salgado", "Salgado", 7.50d, false, true);
        this.cardapio.adicionarItem("combo1", "1 Suco e 1 Sanduíche", 9.50d, false, false);
        this.cardapio.adicionarItem("combo2", "1 Café e 1 Sanduíche", 7.50d, false, false);
    }

    //Método verificador da forma de pagamento
    public boolean validarMetodoDePagamento(String formaDePagamento) {
        if (formaDePagamento.toLowerCase().contains(this.formaDePagamentoCredito) || formaDePagamento.toLowerCase().contains(this.formaDePagamentoDebito) || formaDePagamento.toLowerCase().contains(this.formaDePagamentoDinheiro)) {
            return true;
        }
        return false;
    }

    //Método verificador de carrinho vazio
    public boolean validarCarrinho(ArrayList<String> itens) {
        if (itens.isEmpty()) {
            return true;
        }
        return false;
    }
    //Método verificador do item extra sem principal
    public boolean validarExtra(Itens itemIterador, ArrayList itens){
            for(int i1 = 0;i1 < itens.size();i1++){
                if(itemIterador.getCodigo().contains("chantily")){
                    if(!itens.contains("cafe")){
                        System.out.println("Item cafe não encontrado. Pedido inválido.");
                        return false;
                    }
                    return true;
                }
                if(itemIterador.getCodigo().contains("queijo")){
                    if(!itens.contains("sanduiche")){
                        System.out.println("Item sanduiche não encontrado. Pedido inválido.");
                        return false;
                    }
                    return true;
                }
            }
        return false;
    }

    public String calcularValorDaCompra(String formaDePagamento, String[] pedido) {
        autoPreencher();
        ArrayList<String> itens = new ArrayList<>();
        ArrayList<Integer> quantidade = new ArrayList<>();
        for (int i = 0; i < pedido.length; i++) {
            String[] separador = pedido[i].split(",");
            itens.add(separador[0]);
            quantidade.add(Integer.parseInt(separador[1]));
            if(quantidade.get(i)<1){
                return "Quantidade inválida para o item: "+separador[0];
            }
        }
        Pedido pedido1 = new Pedido(itens, quantidade);
        this.pedido = pedido1;
        Double resultado = 0d;
        //Validando método de pagamento
        if (!validarMetodoDePagamento(formaDePagamento)) {
            System.out.println("Forma de pagamento não aceita");
        } else {
            //Validando se não forem pedidos itens, apresentar mensagem "Não há itens no carrinho de compra!"
            if (validarCarrinho(itens)) {
                System.out.println("Não há itens no carrinho de compra.");
            } else {
                System.out.println("Há itens, vamos verificar se são válidos.");
                //Utilizarei um iterador para checar se os itens do pedido batem com algum item do cardapio
                Iterator iterador = this.cardapio.getCardapio().iterator();
                //Validando se os itens existem no Cardápio
                while (iterador.hasNext()) {
                    Itens itemIterador = (Itens) iterador.next();
                    for (int i = 0; i < itens.size(); i++) {
                        Itens verificador = new Itens(itens.get(i));
                        if (verificador.getCodigo().equalsIgnoreCase(itemIterador.getCodigo())) {
                            System.out.println("Item " + itemIterador.getCodigo() + " existe.");
                            if (itemIterador.isExtra()) {
                                if (validarExtra(itemIterador, itens)) {
                                    resultado += itemIterador.getValor() * quantidade.get(i);
                                } else {
                                    return "Item principal não encontrado!";
                                }
                            }
                            if (!itemIterador.isExtra() && itemIterador.isPrincipal()){
                                resultado += itemIterador.getValor() * quantidade.get(i);
                            }else if(!itemIterador.isExtra() && !itemIterador.isPrincipal()){
                                return "Combo não pode ser pedido sem o principal";
                            }
                        }
                    }
                }
            }
        }

        return this.pedido + "Valor total: " + resultado;
    }

}
