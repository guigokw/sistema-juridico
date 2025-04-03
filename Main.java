import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tribunal tribunal = new Tribunal();

        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("1 - cadastrar reus");
                System.out.println("2 - cadastrar advogado");
                System.out.println("3 - cadastrar juiz");
                System.out.println("4 - adicionar caso");
                System.out.println("5 - listar todos os casos Juridicos");
                System.out.println("6 - mudar de advogado no processo");


                System.out.print("qual dessas opcoes vc escolhe?");
                int opcao = input.nextInt();

                input.nextLine();

                switch (opcao) {
                    case 1 -> cadastrarReu(input, tribunal);
                    case 2 -> cadastrarAdvogado(input, tribunal);
                    case 3 -> cadastrarJuiz(input, tribunal);
                    case 4 -> adicionarCaso(input, tribunal);
                    case 5 -> listarCasosJuridicos(tribunal);
                    case 6 -> mudarAdvogado(tribunal);
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("entrada invalida, por favor digite novamente");
                input.nextLine();
            }
        }
    }

    private static void cadastrarReu(Scanner input, Tribunal tribunal) {
        try {
            System.out.print("qual o id do reu?");
            int idPessoa = input.nextInt();

            input.nextLine();

            System.out.print("qual o nome do reu?");
            String nomePessoa = input.nextLine();

            System.out.print("qual a idade do reu?");
            int idadeReu = input.nextInt();

            Reu reu = new Reu(idPessoa, nomePessoa, idadeReu);
            tribunal.cadastrarReu(reu);

        } catch (IllegalArgumentException | ReuDuplicadoException e) {
            System.out.println(e.getMessage());
        } catch (java.util.InputMismatchException e) {
            System.out.println("entrada invalida, por favor digite novamente");
            input.nextLine();
        }
    }

    private static void cadastrarAdvogado(Scanner input, Tribunal tribunal) throws IllegalArgumentException, AdvogadoDuplicadoException, java.util.InputMismatchException {
        try {
            System.out.print("qual o id do advogado?");
            int idPessoa = input.nextInt();

            input.nextLine();

            System.out.print("qual o nome do advogado?");
            String nomePessoa = input.nextLine();

            System.out.print("qual o numero da oab do advogado?");
            String numeroOab = input.nextLine();

            System.out.println("1 - civil");
            System.out.println("2 - criminal");
            System.out.println("3 - trabalhista");
            System.out.println("4 - familiar");
            System.out.println("-----------------------");
            System.out.print("qual é a especialidade do advogado?");
            int opcao = input.nextInt();

            TiposDeProcesso tipo = switch (opcao) {
                case 1 -> TiposDeProcesso.CIVIL;
                case 2 -> TiposDeProcesso.CRIMINAL;
                case 3 -> TiposDeProcesso.TRABALHISTA;
                case 4 -> TiposDeProcesso.FAMILIAR;
                default -> throw new IllegalArgumentException("opcao invalida, por favor digite novamente");
            };

            Advogado advogado = new Advogado(idPessoa, nomePessoa, numeroOab.replaceAll("[\\D]", "").strip(), tipo);
            tribunal.adicionarAdvogado(advogado);

        } catch (IllegalArgumentException | AdvogadoDuplicadoException e) {
            System.out.println(e.getMessage());
        } catch (java.util.InputMismatchException e) {
            System.out.println("entrada invalida, por favor digite novamente");
            input.nextLine();
        }
    }

    private static void cadastrarJuiz(Scanner input, Tribunal tribunal) throws IllegalArgumentException, java.util.InputMismatchException {
        try {
            System.out.print("qual o id do juiz?");
            int idPessoa = input.nextInt();

            input.nextLine();

            System.out.print("qual o nome do juiz?");
            String nomePessoa = input.nextLine();

            System.out.print("quantos anos de experiencia tem o juiz");
            int anosExperiencia = input.nextInt();

            Juiz juiz = new Juiz(idPessoa, nomePessoa, anosExperiencia);
            tribunal.adicionarJuiz(juiz);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (java.util.InputMismatchException e) {
            System.out.println("entrada invalida, por favor digite novamente");
            input.nextLine();
        }
    }

    private static void adicionarCaso(Scanner input, Tribunal tribunal) throws IllegalArgumentException, java.util.InputMismatchException, AdvogadoNaoEncontradoException, JuizNaoEncontradoException, ReuDuplicadoException, ReuNaoEncontradoException {
        try {
            if (tribunal.advogados.isEmpty() || tribunal.reus.isEmpty() || tribunal.juizes.isEmpty()) {
                System.out.println("nao é possivel adicionar um caso enquanto alguma das listas de juizes, advogados ou reus estiverem vazias");
            } else {
                System.out.print("qual o numero do processo?");
                int numeroProcesso = input.nextInt();

                System.out.println("1 - civil");
                System.out.println("2 - criminal");
                System.out.println("3 - trabalhista");
                System.out.println("4 - familiar");
                System.out.println("---------------");
                System.out.print("qual o tipo de caso do processo?");
                int opcao = input.nextInt();

                TiposDeProcesso tipo = switch (opcao) {
                    case 1 -> TiposDeProcesso.CIVIL;
                    case 2 -> TiposDeProcesso.CRIMINAL;
                    case 3 -> TiposDeProcesso.TRABALHISTA;
                    case 4 -> TiposDeProcesso.FAMILIAR;
                    default -> throw new IllegalArgumentException("opcao invalida, por favor digite novamente");
                };

                System.out.println("1 - aberto");
                System.out.println("2 - em andamento");
                System.out.println("3 - finalizado");
                System.out.println("----------------");
                System.out.print("qual é o status do processo?");
                int opcao2 = input.nextInt();

                StatusProcesso status = switch (opcao2) {
                    case 1 -> StatusProcesso.ABERTO;
                    case 2 -> StatusProcesso.EM_ANDAMENTO;
                    case 3 -> StatusProcesso.FINALIZADO;
                    default -> throw new IllegalArgumentException("opcao invalida, por favor digite novamente");
                };

                System.out.print("qual o id do reu que vc deseja adicionar ao processo?");
                int idReu = input.nextInt();

                Reu reu = tribunal.reus.stream()
                        .filter(a -> a.getIdPessoa() == idReu)
                        .findFirst()
                        .orElseThrow(() -> new ReuNaoEncontradoException("nao foi possivel adicionar o reu, pois o seu id nao foi encontrado"));

                System.out.print("qual o id do advogado que vc deseja adicionar ao processo?");
                int idAdvogado = input.nextInt();

                Advogado advogado = tribunal.advogados.stream()
                        .filter(a -> a.getIdPessoa() == idAdvogado)
                        .filter(b -> b.getEspecialidadeAdvogado() == tipo)
                        .findFirst()
                        .orElseThrow(() -> new AdvogadoNaoEncontradoException("nao foi possivel adicionar o advogado no processo porque ou o id nao foi encontrado, ou a especialidade do advogado nao é compativel com o tipo de caso"));

                System.out.print("qual o id do juiz que vc deseja adicionar ao processo");
                int idJuiz = input.nextInt();

                Juiz juiz = tribunal.juizes.stream()
                        .filter(a -> a.getIdPessoa() == idJuiz)
                        .findFirst()
                        .orElseThrow(() -> new JuizNaoEncontradoException("nao foi possivel adicionar o juiz no processo, pois o seu id nao foi encontrado"));

                    CasoJuridico caso = new CasoJuridico(numeroProcesso, tipo, status, reu, advogado, juiz, null);
                    tribunal.adicionarCaso(numeroProcesso, caso);
                    advogado.adicionarClienteAoAdvogado(reu);
                    juiz.casosJulgados.put(numeroProcesso, caso);
            }
        } catch (IllegalArgumentException | AdvogadoNaoEncontradoException | JuizNaoEncontradoException | ReuNaoEncontradoException | ReuDuplicadoException e) {
            System.out.println(e.getMessage());
        } catch (java.util.InputMismatchException e) {
            System.out.println("entrada invalida, por favor digite novamente");
            input.nextLine();
        }
    }

    private static void listarCasosJuridicos(Tribunal tribunal) {
        tribunal.listarTodosCasosJuridicos();
    }

    private static void mudarAdvogado(Tribunal tribunal) throws ProcessoNaoEncontradoException, AdvogadoNaoEncontradoException, java.util.InputMismatchException {
        try {
            tribunal.registrarAdvogadoNoProcesso();
        } catch (ProcessoNaoEncontradoException | AdvogadoNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (java.util.InputMismatchException e) {
            System.out.println("entrada invalida, por favor digite novamente");
        }
    }
}
