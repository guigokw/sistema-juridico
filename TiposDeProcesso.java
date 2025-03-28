public enum TiposDeProcesso {
    CIVIL("O Direito processual civil é um ramo do direito público, composto por um conjunto de princípios e normas jurídicas que guiam os processos civis, a solução de conflitos de interesses e, o uso da jurisdição do Estado. Isto é, de fazer valer o respeito às leis de forma definitiva e coativa"),
    CRIMINAL("O Direito Processual Penal ou Direito Processual Criminal é o ramo de estudo tradicionalmente voltado à atividade de jurisdição de um Estado soberano no julgamento do acusado de praticar um crime"),
    TRABALHISTA("Ação trabalhista é a denominação que se dá ao direito de provocar o exercício da tutela jurisdicional pelo Estado, para solucionar uma controvérsia existente entre os sujeitos da relação de trabalho, geralmente o trabalhador e empregador ou tomador de serviço."),
    FAMILIAR("Um processo familiar é um processo judicial que envolve questões de família. As etapas de um processo familiar podem incluir: Consulta inicial com um advogado, Apresentação de uma petição inicial, Audiência de conciliação, Julgamento, Execução");


    private String descricao;

     TiposDeProcesso(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
