import java.io.IOException;
import java.util.ArrayList;
public class Menu {
    private static ArrayList<Lanche> catalogoLanches = new ArrayList<>();
    public static void executarMenu() throws IOException {
        boolean flag = true;
        do {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            System.out.println("Escolha uma opcao\n1- Cadastrar lanche\n2- Calcular Compra\n3- Listar lanche\n4- SAIR\n--> ");
            switch (scanner.nextInt()) {
                case 1:
                    Mecanismos.adicionarLanche(catalogoLanches);
                    break;

                case 2:
                    Mecanismos.calcularCompra(catalogoLanches);
                    break;

                case 3:
                    Mecanismos.listarLanches(catalogoLanches);
                    break;

                case 4:
                    flag = false;
                    break;
                default:
                    System.out.println("Insira uma opcao valida!");
            }
        } while(flag);
    }
}
