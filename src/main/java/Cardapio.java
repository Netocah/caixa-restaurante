import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Cardapio {
    private List<Itens> cardapio = new ArrayList<>();

    //Construtor vazio
    public Cardapio() {
    }
    public List<Itens> getCardapio() {
        return cardapio;
    }
    public void setCardapio(List<Itens> cardapio) {
        this.cardapio = cardapio;
    }
    //Métodos para adicionar/remover itens do cardápio
    public void adicionarItem(String codigo, String descricao, double valor, boolean extra, boolean principal){
        Itens item = new Itens(codigo, descricao, valor, extra, principal);
        this.cardapio.add(item);
    }
    public void deleteItem(String codigo) {
        List<Itens> itensParaRemover = new ArrayList();
        if (!this.cardapio.isEmpty()) {
            Iterator var3 = this.cardapio.iterator();

            while(var3.hasNext()) {
                Itens i = (Itens)var3.next();
                if (i.getCodigo().equalsIgnoreCase(codigo)) {
                    itensParaRemover.add(i);
                }
            }
            this.cardapio.removeAll(itensParaRemover);
        } else {
            System.out.println("A lista está vazia!");
        }
    }
    //Verificar se há o item

    //To String
    @Override
    public String toString() {
        return "Cardapio{" +
                "cardapio=" + cardapio +
                '}';
    }
    //Equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cardapio cardapio1 = (Cardapio) o;
        return Objects.equals(cardapio, cardapio1.cardapio);
    }
    @Override
    public int hashCode() {
        return Objects.hash(cardapio);
    }
}