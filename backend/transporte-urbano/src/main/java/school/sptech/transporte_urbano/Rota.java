package school.sptech.transporte_urbano;

public class Rota {

    private Integer id;
    private String origem;
    private String destino;
    private String estacaoInicial;
    private String estacaoFinal;
    private Integer duracaoMinutos;
    private Integer qtdBaldeacoes;

    public Rota() {
    }

    public Rota(Integer id, String origem, String destino, String estacaoInicial, String estacaoFinal, Integer duracaoMinutos, Integer qtdBaldeacoes) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.estacaoInicial = estacaoInicial;
        this.estacaoFinal = estacaoFinal;
        this.duracaoMinutos = duracaoMinutos;
        this.qtdBaldeacoes = qtdBaldeacoes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getEstacaoInicial() {
        return estacaoInicial;
    }

    public void setEstacaoInicial(String estacaoInicial) {
        this.estacaoInicial = estacaoInicial;
    }

    public String getEstacaoFinal() {
        return estacaoFinal;
    }

    public void setEstacaoFinal(String estacaoFinal) {
        this.estacaoFinal = estacaoFinal;
    }

    public Integer getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(Integer duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public Integer getQtdBaldeacoes() {
        return qtdBaldeacoes;
    }

    public void setQtdBaldeacoes(Integer qtdBaldeacoes) {
        this.qtdBaldeacoes = qtdBaldeacoes;
    }
}
