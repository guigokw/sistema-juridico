public enum Sentenca {
    CULPADO("significa que o reu é considerado culpado de suas acusações"),
    INOCENTE("significa que o reu é considerado inocente de suas acusações");


    private String descricao;

    Sentenca(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
