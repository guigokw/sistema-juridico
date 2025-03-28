import java.util.*;
import java.util.Scanner;

public class Advogado extends Pessoa {
    private String numeroOab;
    private TiposDeProcesso especialidadeAdvogado;

    Scanner input = new Scanner(System.in);

    Set<Integer> idReu = new LinkedHashSet<>();
    Map<Integer, Reu> clientesAdvogado = new LinkedHashMap<>();

    public Advogado(int idPessoa, String nomePessoa, String numeroOab, TiposDeProcesso especialidadeAdvogado) throws IllegalArgumentException {
        super(idPessoa, nomePessoa);
        if (numeroOab.length() == 6) {
            this.numeroOab = numeroOab;
            this.especialidadeAdvogado = especialidadeAdvogado;
        } else {
            throw new IllegalArgumentException("nao foi possivel adicionar o numero da OAB pois a");
        }
    }

    public String getNumeroOab() {
        return numeroOab;
    }

    public void setNumeroOab(String numeroOab) {
        this.numeroOab = numeroOab;
    }

    public TiposDeProcesso getEspecialidadeAdvogado() {
        return especialidadeAdvogado;
    }

    public void setEspecialidadeAdvogado(TiposDeProcesso especialidadeAdvogado) {
        this.especialidadeAdvogado = especialidadeAdvogado;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("ID DO ADVOGADO: " +this.idPessoa);
        System.out.println("NOME DO ADVOGADO: " +this.nomePessoa);
        System.out.println("NUMERO DA OAB: UF " +this.numeroOab);
        System.out.println("ESPECIALIDADE DO ADVOGADO: " +especialidadeAdvogado);
        System.out.println("DESCRICAO DA ESPECIALIDADE: " +especialidadeAdvogado.getDescricao());
    }

    public void adicionarClienteAoAdvogado(Reu reu) throws ReuDuplicadoException {
        if (!idReu.add(reu.getIdPessoa())) {
            throw new ReuDuplicadoException("nao foi possivel adicionar o reu a lista de clientes do advogado pois o reu esta duplicado");
        } else {
            clientesAdvogado.put(reu.getIdPessoa(), reu);
            System.out.println("cliente " +reu.getNomePessoa()+ " adicionado a lista de clientes do advpgado " +this.nomePessoa);
        }
    }

                 // CasoJuridico caso -- adicionar depois da criação da classe
    public void defenderReu() {
        System.out.print("qual o id do reu que vc deseja defender?");
        int id = input.nextInt();

        Reu reu = clientesAdvogado.values().stream()
                .filter(a -> a.getIdPessoa() == id)
                .findFirst()
                .orElseThrow(() -> new ReuNaoEncontradoException("nao foi possivel apresentar uma defesa ao reu pois ele nao foi encontrado"));

        System.out.println("====== INFORMAÇÕES DO REU =======");
        reu.exibirDetalhes();

        System.out.println("1 - sim");
        System.out.println("2 - não");
        System.out.println("----------------------");
        System.out.print("esse é o reu que vc deseja defender?");
        int opcao = input.nextInt();

        switch (opcao) {
            case 1:
                System.out.print("insira a defesa que deseja fazer ao reu:");
                String defesa = input.nextLine();

                // caso.argumentosDefesa.add(defesa) -- adicionar depois

                break;

            case 2:
                System.out.println("caso ainda deseja defender um reu, consulte a lista de reus e depois insira a defesa");
                break;

            default:
                System.out.println("opcao invalida, por favor digite novamente");
        }
    }
}
