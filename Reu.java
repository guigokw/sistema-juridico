import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reu extends Pessoa{
    private int idadeReu;

    Scanner input = new Scanner(System.in);

    List<String> historicoCriminal = new ArrayList<>();

    public Reu(int idPessoa, String nomePessoa, int idadeReu) throws IllegalArgumentException {
        super(idPessoa, nomePessoa);
        if (idadeReu < 0 || idadeReu > 120) {
            throw new IllegalArgumentException("nao foi possivel adicionar o reu pois a idade esta invalida");
        }
    }

    public int getIdadeReu() {
        return idadeReu;
    }

    public void setIdadeReu(int idadeReu) {
        this.idadeReu = idadeReu;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("ID DO RÉU: " +this.idPessoa);
        System.out.println("NOME DO RÉU: " +this.nomePessoa);
        System.out.println("IDADE DO RÉU: " +this.idadeReu);
        if (historicoCriminal.isEmpty()) {
            System.out.println("HISTORICO CRIMINAL: o reu nao ha nenhum delito registrado");
        } else {
            System.out.println("====== HISTORICO CRIMINAL ======");
            for (String crimes : historicoCriminal) {
                System.out.println(crimes);
                System.out.println("--------------------------");
            }
        }
    }

    public void adicionarHistoricoCriminal() throws IllegalArgumentException {
        System.out.print("qual crime do reu " +this.nomePessoa+ " vc deseja adicionar ho historico criminal");
        String crime = input.nextLine();

        if (crime.isEmpty()) {
            throw new IllegalArgumentException("nao foi possivel adicionar o crime para o historico criminal pois o crime esta vazio");
        } else {
            historicoCriminal.add(crime);
            System.out.println("crime adicionado ao historico criminal de " +this.nomePessoa);
        }
    }
}
