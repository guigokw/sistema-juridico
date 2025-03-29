import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Juiz extends Pessoa {
    private int anosExperiencia;

    Scanner input = new Scanner(System.in);

    Map<Integer, CasoJuridico> casosJulgados = new LinkedHashMap<>();

    public Juiz(int idPessoa, String nomePessoa, int anosExperiencia) throws IllegalArgumentException {
        super(idPessoa, nomePessoa);
        if (anosExperiencia < 0) {
            throw new IllegalArgumentException("nao foi possivel adicionar os anos de experiencia do juiz pois o valor registrado é invalido");
        } else {
            this.anosExperiencia = anosExperiencia;
        }
    }

    public int getAnosExperiencia() {
        return anosExperiencia;
    }

    public void setAnosExperiencia(int anosExperiencia) {
        this.anosExperiencia = anosExperiencia;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("ID DO JUIZ: " +this.idPessoa);
        System.out.println("NOME DO JUIZ: " +this.nomePessoa);
        System.out.println("ANOS DE EXPERIENCIA: " +this.anosExperiencia);
        if (casosJulgados.isEmpty()) {
            System.out.println("CASOS JULGADOS: nao há nenhum caso julgado");
        } else {
            System.out.println("====== LISTA DE CASOS ======");
            for (int casos : casosJulgados.keySet()) {
                System.out.println(casosJulgados.get(casos));
            }
        }
    }

    public void proferirSentenca() throws ProcessoNaoEncontradoException {
        System.out.print("qual o numero do processo do caso ja qual vc ira proferir a sentença?");
        int numeroProcesso = input.nextInt();

        CasoJuridico casoJuridico = casosJulgados.values().stream()
                .filter(a -> a.getNumeroProcesso() == numeroProcesso)
                .findFirst()
                .orElseThrow(() -> new ProcessoNaoEncontradoException("nao foi possivel proferir a sentença pois o processo com o numero " +numeroProcesso+ " nao foi encontrado"));

        System.out.println("====== DETALHES DO CASO =======");
        casoJuridico.exibirDetalhesCaso();
        System.out.println("----------------------------------");
        System.out.println("1 - sim");
        System.out.println("2 - não");
        System.out.print("esse é o caso que deseja proferir a sentença?");
        int opcao = input.nextInt();

        switch (opcao) {
            case 1:
                System.out.println("1 - culpado");
                System.out.println("2 - inocente");
                System.out.println("---------------");
                System.out.println("qual o veredicto do caso?");
                int opcao2 = input.nextInt();

                if (opcao2 == 1) {
                    System.out.print("qual a pena do reu " +casoJuridico.getReuDoCaso().getNomePessoa());
                    int pena = input.nextInt();

                    casoJuridico.setStatusDoCaso(StatusProcesso.FINALIZADO);
                    casoJuridico.setVeredicto(Sentenca.CULPADO);

                    System.out.println("====== DETALHES COMPLETOS DO CASO =======");
                    System.out.println("NUMERO DO PROCESSO: " +casoJuridico.getNumeroProcesso());
                    System.out.println("TIPO DE CASO: " +casoJuridico.getTipoDeCaso());
                    System.out.println("STATUS DO CASO: " +casoJuridico.getStatusDoCaso());
                    System.out.println("REU DO CASO: " + casoJuridico.getReuDoCaso().getNomePessoa());
                    System.out.println("ADVOGADO DO CASO: " +casoJuridico.getAdvogadoResponsavel().getNomePessoa());
                    System.out.println("JUIZ DO CASO: " +casoJuridico.getJuizResponsavel().getNomePessoa());
                    System.out.println("VEREDICTO DO CASO: " +casoJuridico.getVeredicto());
                    System.out.println("SENTENÇA: " +pena+ " anos de prisão");
                    System.out.println("---------------------");
                } else if (opcao2 == 2) {

                    casoJuridico.setStatusDoCaso(StatusProcesso.FINALIZADO);
                    casoJuridico.setVeredicto(Sentenca.INOCENTE);

                    System.out.println("====== DETALHES COMPLETOS DO CASO =======");
                    System.out.println("NUMERO DO PROCESSO: " +casoJuridico.getNumeroProcesso());
                    System.out.println("TIPO DE CASO: " +casoJuridico.getTipoDeCaso());
                    System.out.println("STATUS DO CASO: " +casoJuridico.getStatusDoCaso());
                    System.out.println("REU DO CASO: " + casoJuridico.getReuDoCaso().getNomePessoa());
                    System.out.println("ADVOGADO DO CASO: " +casoJuridico.getAdvogadoResponsavel().getNomePessoa());
                    System.out.println("JUIZ DO CASO: " +casoJuridico.getJuizResponsavel().getNomePessoa());
                    System.out.println("VEREDICTO DO CASO: " +casoJuridico.getVeredicto());
                    System.out.println("---------------------");
                } else {
                    System.out.println("opcao invalida, por favor digite novamente");
                }

                break;

            case 2:
                System.out.println("se deseja proferir alguma sentença, por favor tente inserir os dados novamente");
                break;

            default:
                System.out.println("opcao invalida, por favor digite novamente");
        }
    }
}
