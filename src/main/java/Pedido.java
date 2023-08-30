import java.util.ArrayList;

public class Pedido {
    private ArrayList<String> itens = new ArrayList<>();
    private ArrayList<Integer> quantidade = new ArrayList<>();
    //Construtores
    public Pedido() {
    }
    public Pedido(ArrayList<String> item, ArrayList<Integer> quantidade) {
        this.itens = item;
        this.quantidade = quantidade;
    }
    //Getters e Setters
    public ArrayList<String> getItens() {
        return itens;
    }
    public void setItens(ArrayList<String> itens) {
        this.itens = itens;
    }
    public ArrayList<Integer> getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(ArrayList<Integer> quantidade) {
        this.quantidade = quantidade;
    }
    //To String
    @Override
    public String toString() {
        String mensagemPedido = "Pedido: ";
        for(int i = 0; i<itens.size();i++){
            mensagemPedido += quantidade.get(i)+"x"+itens.get(i)+" - ";
        }
        return mensagemPedido;
    }
}
