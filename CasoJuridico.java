import java.util.ArrayList;
import java.util.List;

public class CasoJuridico {
    private int numeroProcesso;
    private TiposDeProcesso tipoDeCaso;
    private StatusProcesso statusDoCaso;
    private Reu reuDoCaso;
    private Advogado advogadoResponsavel;
    private Juiz juizResponsavel;
    private Sentenca veredicto;

    List<String> argumentosDeDefesa = new ArrayList<>();



    public CasoJuridico(int numeroProcesso, TiposDeProcesso tipoDeCaso, StatusProcesso statusDoCaso, Reu reuDoCaso, Advogado advogadoResponsavel, Juiz juizResponsavel, Sentenca veredicto) throws IllegalArgumentException {
        if (numeroProcesso < 0) {
            throw new IllegalArgumentException("numero de processo invalido, por favor digite novamente");
        } else {
            this.numeroProcesso = numeroProcesso;
            this.tipoDeCaso = tipoDeCaso;
            this.statusDoCaso = statusDoCaso;
            this.reuDoCaso = reuDoCaso;
            this.advogadoResponsavel = advogadoResponsavel;
            this.juizResponsavel = juizResponsavel;
            this.veredicto = null;
        }
    }

    public int getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(int numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public TiposDeProcesso getTipoDeCaso() {
        return tipoDeCaso;
    }

    public void setTipoDeCaso(TiposDeProcesso tipoDeCaso) {
        this.tipoDeCaso = tipoDeCaso;
    }

    public StatusProcesso getStatusDoCaso() {
        return statusDoCaso;
    }

    public void setStatusDoCaso(StatusProcesso statusDoCaso) {
        this.statusDoCaso = statusDoCaso;
    }

    public Reu getReuDoCaso() {
        return reuDoCaso;
    }

    public void setReuDoCaso(Reu reuDoCaso) {
        this.reuDoCaso = reuDoCaso;
    }

    public Advogado getAdvogadoResponsavel() {
        return advogadoResponsavel;
    }

    public void setAdvogadoResponsavel(Advogado advogadoResponsavel) {
        this.advogadoResponsavel = advogadoResponsavel;
    }

    public Juiz getJuizResponsavel() {
        return juizResponsavel;
    }

    public void setJuizResponsavel(Juiz juizResponsavel) {
        this.juizResponsavel = juizResponsavel;
    }

    public Sentenca getVeredicto() {
        return veredicto;
    }

    public void setVeredicto(Sentenca veredicto) {
        this.veredicto = null;
    }

    public void exibirDetalhesCaso() {
        System.out.println("NUMERO DO PROCESSO: " +this.numeroProcesso);
        System.out.println("TIPO DE CASO: " +this.tipoDeCaso);
        System.out.println("STATUS DO CASO: " +this.statusDoCaso);
        System.out.println("REU DO CASO: " +this.reuDoCaso.getNomePessoa());
        System.out.println("ADVOGADO DO CASO: " +this.advogadoResponsavel.getNomePessoa());
        System.out.println("JUIZ DO CASO: " +this.juizResponsavel.getNomePessoa());
        System.out.println("VEREDICTO DO CASO: " +this.veredicto);
        System.out.println("---------------------------------------");
    }


}
