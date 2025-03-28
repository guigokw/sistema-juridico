public abstract class Pessoa {
    protected int idPessoa;
    protected String nomePessoa;

    public Pessoa(int idPessoa, String nomePessoa) throws IllegalArgumentException {
        if (nomePessoa.isEmpty()) {
            throw new IllegalArgumentException("nao foi possivel adicionar essa pessoa pois o nome esta nulo");
        } else {
            this.idPessoa = idPessoa;
            this.nomePessoa = nomePessoa;
        }
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public abstract void exibirDetalhes();
}
