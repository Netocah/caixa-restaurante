public class Itens {
    private String codigo;
    private String descricao;
    private Double valor;
    private boolean extra;
    private boolean principal;

//Construtores
    public Itens(String codigo, String descricao, Double valor, boolean extra, boolean principal) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
        this.extra = extra;
        this.principal = principal;
    }
    public Itens(String codigo) {
        this.codigo = codigo;
        this.descricao = "vazio";
        this.valor = 0d;
        this.extra = false;
        this.principal = false;
    }

    //Getters e Setters
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public boolean isExtra() {
        return extra;
    }
    public void setExtra(boolean extra) {
        this.extra = extra;
    }
    public boolean isPrincipal() {
        return principal;
    }
    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }
//toString
    @Override
    public String toString() {
        return "Itens{" +
                "codigo='" + codigo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", extra=" + extra +
                ", principal=" + principal +
                '}';
    }
}
