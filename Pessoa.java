public abstract class Pessoa {
    protected int idPessoa;
    protected String nomePessoa;

    public Pessoa(int idPessoa, String nomePessoa) throws IllegalArgumentException {
        if (nomePessoa == null || nomePessoa.isEmpty()) {
            throw new IllegalArgumentException("nao foi possivel adicionar essa pessoa pois o nome esta nulo");
        } else if (idPessoa < 0) {
            throw new IllegalArgumentException("nao foi possivel adicionar essa pessoa pois o id esta invalido");
        } else {
            this.idPessoa = idPessoa;
            this.nomePessoa = nomePessoa;
        }
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) throws IllegalArgumentException {
        if (idPessoa < 0) {
            throw new IllegalArgumentException("nao foi possivel adicionar essa pessoa pois o id esta invalido");
        } else {
            this.idPessoa = idPessoa;
        }
    }
    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) throws IllegalArgumentException {
        if (nomePessoa == null || nomePessoa.isEmpty()) {
            throw new IllegalArgumentException("nao foi possivel adicionar essa pessoa pois o nome esta nulo");
        } else {
            this.nomePessoa = nomePessoa;
        }
    }

    public abstract void exibirDetalhes();
}
