import java.util.ArrayList;
import java.util.Iterator;

public class Pagamento {
    private Cardapio cardapio = new Cardapio();
    private Pedido pedido = new Pedido();
    private boolean existePrincipal = false;

    //Getters e Setters

    public boolean isExistePrincipal() {
        return existePrincipal;
    }

    public void setExistePrincipal(boolean existePrincipal) {
        this.existePrincipal = existePrincipal;
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
        return formaDePagamento.toLowerCase().contains("credito") || formaDePagamento.toLowerCase().contains("debito") || formaDePagamento.toLowerCase().contains("dinheiro");
    }

    //Método verificador de carrinho vazio
    public boolean validarCarrinho(ArrayList<String> itens) {
        if (itens.isEmpty()) {
            return false;
        }
        return true;
    }
    //Método verificador do item extra sem principal
    public boolean validarExtra(Itens itemIterador, ArrayList itens){
            for(int i = 0;i < itens.size();i++){
                if(itemIterador.getCodigo().contains("chantily")){
                    if(!itens.contains("cafe")){
                        return false;
                    }
                    return true;
                }
                if(itemIterador.getCodigo().contains("queijo")){
                    if(!itens.contains("sanduiche")){
                        return false;
                    }
                    return true;
                }
            }
        return false;
    }
    //Método que valida se o pedido contém um item principal
    public boolean validaPrincipal(ArrayList<String> itens){
        Iterator cardapioI = this.cardapio.getCardapio().iterator();
        while(cardapioI.hasNext()){
            Itens itemIterado =  (Itens) cardapioI.next();
            for(int i = 0; i<this.pedido.getItens().size(); i++){
                Itens itemVerificador = new Itens(itens.get(i));
                if(itemIterado.getCodigo().equals(itemVerificador.getCodigo())){
                    if(itemIterado.isPrincipal()){
                        this.existePrincipal = true;
                    }
                }
            }
        }
        return this.existePrincipal;
    }
    //Método que aplica o Desconto/Taxa
    public Double aplicaDescTax(String formaDePagamento,Double resultado){
        if(formaDePagamento.equalsIgnoreCase("dinheiro")){
            resultado=resultado*0.95d;
        }else if(formaDePagamento.equalsIgnoreCase("credito")){
            resultado=resultado*1.03d;
        }
        return resultado;
    }
    public String calcularValorDaCompra(String formaDePagamento, String[] pedido) {
        autoPreencher();
        ArrayList<String> itens = new ArrayList<>();
        ArrayList<Integer> quantidade = new ArrayList<>();
        Double resultado = 0d;
        for (int i = 0; i < pedido.length; i++) {
            String[] separador = pedido[i].split(",");
            itens.add(separador[0]);
            quantidade.add(Integer.parseInt(separador[1]));
            if(quantidade.get(i)<1){
                return "Quantidade inválida para o item: "+separador[0];
            }
        }
        /*Salvando o pedido*/
        this.pedido = new Pedido(itens, quantidade);
        //Validando método de pagamento
        if (!validarMetodoDePagamento(formaDePagamento)) {
            return "Método de pagamento não aceita. Escolher: Credito, Debito ou Dinheiro. (Não aceitamos PIX)";
        } else {
            System.out.println("Método de pagamento: "+formaDePagamento);
            //Validando se não forem pedidos itens, apresentar mensagem "Não há itens no carrinho de compra!"
            if (!validarCarrinho(itens)) {
                return "Não há itens no carrinho de compras.";
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
                                    return itemIterador.getCodigo()+" sem o item principal, refazer pedido.";
                                }
                            }
                            if (!itemIterador.isExtra() && itemIterador.isPrincipal()){
                                resultado += itemIterador.getValor() * quantidade.get(i);
                            }
                            if(!itemIterador.isExtra() && !itemIterador.isPrincipal()){
                                if(!validaPrincipal(itens)){
                                    return "Combo não é principal. Favor, refazer pedido com um item principal.";
                                }else{
                                    resultado += itemIterador.getValor() * quantidade.get(i);
                                }
                            }
                        }
                    }
                }
            }
        }
        resultado =aplicaDescTax(formaDePagamento, resultado);
        return this.pedido + "Valor total: " + String.format("%.2f", resultado);
    }

}
