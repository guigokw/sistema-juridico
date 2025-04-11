import java.util.*;

public class Tribunal {
    Scanner input = new Scanner(System.in);

    List<Advogado> advogados = new ArrayList<>();
    Set<String> numeroOab = new HashSet<>();
    Map<Integer, CasoJuridico> casosDoTribunal = new HashMap<>();
    Set<Integer> numeroProcesso = new HashSet<>();
    List<Juiz> juizes = new ArrayList<>();
    List<Reu> reus = new ArrayList<>();
    Set<Integer> idDoReu = new HashSet<>();
    Set<Integer> idDoAdvogado = new HashSet<>();
    Set<Integer> idDoJuiz = new HashSet<>();

    public void adicionarCaso(int numeroCaso, CasoJuridico caso) throws NumeroProcessoDuplicadoException {
        if (!numeroProcesso.add(numeroCaso)) {
            throw new NumeroProcessoDuplicadoException("nao foi possivel adicionar o caso, pois o numero do processo esta duplicado");
        } else {
            casosDoTribunal.put(caso.getNumeroProcesso(), caso);
            System.out.println("caso adicionado a lista de casos do tribunal");
        }
    }

    public void adicionarAdvogado(Advogado advogado) throws AdvogadoDuplicadoException {
        if (!numeroOab.add(advogado.getNumeroOab()) || !idDoAdvogado.add(advogado.getIdPessoa())) {
            throw new AdvogadoDuplicadoException("nao foi possivel adicionar o advogado pois o numero da oab ou o numero do id esta duplicado");
        } else {
            advogados.add(advogado);
            System.out.println("advogado " + advogado.nomePessoa + " adicionado a lista de advogados");
        }
    }

    public void adicionarJuiz(Juiz juiz) throws JuizDuplicadoException {
        if (!idDoJuiz.add(juiz.getIdPessoa())) {
            throw new JuizDuplicadoException("nao foi possivel adicionar o juiz pois o id esta invalido");
        } else {
            juizes.add(juiz);
            System.out.println("juiz " + juiz.nomePessoa + " adicionado a lista de juizes");
        }
    }

    public void cadastrarReu(Reu reu) {
        if (!idDoReu.add(reu.getIdPessoa())) {
            throw new ReuDuplicadoException("nao foi possivel adicionar o reu pois o seu id esta duplicado");
        } else {
            reus.add(reu);
            System.out.println("reu " + reu.nomePessoa + " adicionado a lista de reus do tribunal");
        }
    }

    public void listarTodosCasosJuridicos() {
        if (casosDoTribunal.isEmpty()) {
            System.out.println("a lista de casos juridicos esta vazia");
        } else {
            System.out.println("====== LISTA DE CASOS JURIDICOS =====");
            for (CasoJuridico casos : casosDoTribunal.values()) {
                casos.exibirDetalhesCaso();
            }
        }
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
                            .filter(b -> b.getTipoDeCaso() == advogado.getEspecialidadeAdvogado())
                            .findFirst()
                            .orElseThrow(() -> new ProcessoNaoEncontradoException("nao foi possivel proseguir com o registro do advogado pois o numero do processo nao foi encontrado || ou porque o tipo de especialidade do advogado nao bate com o tipo de especialidade do caso"));

                    caso.exibirDetalhesCaso();
                    System.out.println("--------------");
                    System.out.println("1 - sim");
                    System.out.println("2 - não");
                    System.out.print("esse é o caso que vc deseja registrar o advogado?");
                    int opcao2 = input.nextInt();

                    if (opcao2 == 1) {
                        long casosComMesmoAdvogadoEReu = casosDoTribunal.values().stream()
                                .filter(c -> c != caso)
                                .filter(c -> c.getAdvogadoResponsavel().equals(caso.getAdvogadoResponsavel()))
                                .filter(c -> c.getReuDoCaso().equals(caso.getReuDoCaso()))
                                .count();

                        if (casosComMesmoAdvogadoEReu == 0) {
                            caso.getAdvogadoResponsavel().removerClienteDoAdvogado(caso.getReuDoCaso());
                        }

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

            juiz.proferirSentenca(juiz);
        }
    }

    public void advogadoDefenderReu() throws ProcessoNaoEncontradoException {
        System.out.print("qual o numero do processo do caso em que deseja defender o reu?");
        int numero = input.nextInt();

        CasoJuridico caso = casosDoTribunal.values().stream()
                .filter(a -> a.getNumeroProcesso() == numero)
                .findFirst()
                .orElseThrow(() -> new ProcessoNaoEncontradoException("nao foi possivel fazer a defesa do reu pois o caso nao foi encontrado"));

        if (caso.getStatusDoCaso() == StatusProcesso.FINALIZADO) {
            System.out.println("nao é possivel deender o reu mais pois o processo ja foi finalizado");
        } else {
            caso.getAdvogadoResponsavel().defenderReu(caso);
        }
        
    }

    public void ordenarCasosPeloStatus(Tribunal tribunal) {
        List<CasoJuridico> casos = tribunal.casosDoTribunal.values().stream()
                .sorted(Comparator.comparing(CasoJuridico::getStatusDoCaso))
                .toList();

        if (casos.isEmpty()) {
            System.out.println("nao há nenhum caso juridico");
        } else {
            for (CasoJuridico caso : casos) {
                caso.exibirDetalhesCaso();
            }
        }
    }

    public void filtrarCasosPorTipo(Tribunal tribunal) throws IllegalArgumentException {
        System.out.println("1 - civil");
        System.out.println("2 - criminal");
        System.out.println("3 - trabalhista");
        System.out.println("4 - familiar");
        System.out.println("--------------------------");
        System.out.print("qual dessas opcoes de casos vc deseja filtrar?");
        int opcao = input.nextInt();

        TiposDeProcesso tipo = switch (opcao) {
            case 1 -> TiposDeProcesso.CIVIL;
            case 2 -> TiposDeProcesso.CRIMINAL;
            case 3 -> TiposDeProcesso.TRABALHISTA;
            case 4 -> TiposDeProcesso.FAMILIAR;
            default -> throw new IllegalArgumentException("opcao invalida, por favor digite novamente");
        };

        List<CasoJuridico> caso = tribunal.casosDoTribunal.values().stream()
                .filter(a -> a.getTipoDeCaso() == tipo)
                .toList();

        if (caso.isEmpty()) {
            System.out.println("nao há nenhum caso do tipo " + tipo);
        } else {
            System.out.println("===== CASOS " + tipo + " =====");
            for (CasoJuridico casos : caso) {
                casos.exibirDetalhesCaso();
            }
        }

    }

}
