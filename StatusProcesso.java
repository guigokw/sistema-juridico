public enum StatusProcesso {
    ABERTO("Um processo aberto é um processo judicial que está em tramitação, ou seja, que ainda não foi finalizado."),
    EM_ANDAMENTO("Um processo em andamento é um processo que está em tramitação, ou seja, em movimento, seguindo os procedimentos e fases definidos por lei. "),
    FINALIZADO("Um processo finalizado é um processo judicial que chegou ao fim e não possui mais etapas pendentes naquela instância");
    ;


    private String descricao;

    StatusProcesso(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
