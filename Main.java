import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tribunal tribunal = new Tribunal();

        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("1 - cadastrar reus");



                System.out.print("qual dessas opcoes vc escolhe?");
                int opcao = input.nextInt();

                input.nextLine();

                switch (opcao) {
                    case 1 ->
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

            System.out.print("qual o nome do reu?");
            String nomePessoa = input.nextLine();

            input.nextLine();

            System.out.println("qual a idade do reu?");
            int idadeReu = input.nextInt();

            Reu reu = new Reu(idPessoa, nomePessoa, idadeReu);
            tribunal.cadastrarReu(reu);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (java.util.InputMismatchException e) {
            System.out.println("entrada invalida, por favor digite novamente");
            input.nextLine();
        }
    }

    private static void cadastrarAdvogado(Scanner input, Tribunal tribunal) {
        try {
            System.out.print("qual o id do advogado?");
            int idPessoa = input.nextInt();

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

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (java.util.InputMismatchException e) {
            System.out.println("entrada invalida, por favor digite novamente");
            input.nextLine();
        }
    }
}
