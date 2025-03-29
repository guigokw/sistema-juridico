import java.util.*;

public class Tribunal {
    Scanner input = new Scanner(System.in);

    List<Advogado> advogados = new ArrayList<>();
    Map<Integer, CasoJuridico> casosDoTribunal = new HashMap<>();
    List<Juiz> juizes = new ArrayList<>();

    public void adicionarCaso(int numeroCaso, CasoJuridico caso) {
        casosDoTribunal.put(caso.getNumeroProcesso(), caso);
        System.out.println("caso adicionado a loista de casos do tribunal");
    }

    public void adicionarAdvogado(Advogado advogado) {
        advogados.add(advogado);
        System.out.println("advogado " +advogado.getNomePessoa()+ " adicionado a lista de advogados");
    }

    public void adicionarJuiz(Juiz juiz) {
        juizes.add(juiz);
        System.out.println("juiz " +juiz.getNomePessoa()+ " adicionado a lista de juizes");
    }

    public void registrarAdvogadoNoProcesso() throws ProcessoNaoEncontradoException, AdvogadoNaoEncontradoException {
        if (advogados.isEmpty() || casosDoTribunal.isEmpty()) {
            System.out.println("nao foi possivel registrar advogado no processo pois a lista de advpgados ou a lista de casos esta vazia");
        } else {
            System.out.print("qual o id do advogado que vc deseja registrar no processo?");
            int idAdvogado = input.nextInt();

            Advogado advogado = advogados.stream()
                    .filter(a -> a.getIdPessoa() == idAdvogado)
                    .findFirst()
                    .orElseThrow(() -> new AdvogadoNaoEncontradoException("nao foi possivel registraro advogado pos nenhum foi encontrado com o id " + idAdvogado));

            advogado.exibirDetalhes();
            System.out.println("--------------");

            System.out.println("1 - sim");
            System.out.println("2 - não");
            System.out.print("esse é o advogado que deseja registrar no processo");
            int opcao = input.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("qual o numero do processo do caso que vc deseja registrar o advogado");
                    int numeroProcesso = input.nextInt();

                    CasoJuridico caso = casosDoTribunal.values().stream()
                            .filter(a -> a.getNumeroProcesso() == numeroProcesso)
                            .findFirst()
                            .orElseThrow(() -> new ProcessoNaoEncontradoException("nao foi possivel proseguir com o registro do advogado pois o numero do processo nao foi encontrado?"));

                    caso.exibirDetalhesCaso();
                    System.out.println("--------------");
                    System.out.println("1 - sim");
                    System.out.println("2 - não");
                    System.out.print("esse é o caso que vc deseja registrar o advogado?");
                    int opcao2 = input.nextInt();

                    if (opcao2 == 1) {
                        caso.setAdvogadoResponsavel(advogado);
                        advogado.adicionarClienteAoAdvogado(caso.getReuDoCaso());
                        System.out.println("advogado " + advogado.getNomePessoa() + " registrado no processo de numero " + caso.getNumeroProcesso());
                    } else if (opcao2 == 2) {
                        System.out.println("se vc deseja registrar o advogado em algum processo, por favortente novamente");
                    } else {
                        System.out.println("opcao invalida, por favor digite novamente");
                    }

                    break;

                case 2:
                    System.out.println("se vc deseja registrar o advogado em algum processo, por favortente novamente");
                    break;
                default:
                    System.out.println("opcao invalida, por favor digite novamente");
            }

        }
    }

    public void proferirSentencaCaso() throws JuizNaoEncontradoException {
        if (juizes.isEmpty()) {
            System.out.println("nao foi possivel proferir a sentença do caso pois a lista de juizes esta vazia");
        } else {
            System.out.println("qual o id do juiz que ira proferir a sentença");
            int idJuiz = input.nextInt();

            Juiz juiz = juizes.stream()
                    .filter(a -> a.getIdPessoa() == idJuiz)
                    .findFirst()
                    .orElseThrow(() -> new JuizNaoEncontradoException("nao foi poossivel proferir a sentença pois o juiz do id " + idJuiz + " nao foi encontrado"));

            juiz.proferirSentenca();
        }
    }
}
