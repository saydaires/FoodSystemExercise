import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Mecanismos {
    public static void adicionarLanche(List<Lanche> catalogoLanches) throws IOException {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Nome/Especificacao do lanche: ");
        String especificacao = scanner.nextLine();
        System.out.println("Preco: R$ ");
        double preco = Double.parseDouble(scanner.nextLine());
        System.out.println("Insira o caminho da imagem: ");
        String filePath = scanner.nextLine();
        Lanche lanche = new Lanche(preco, especificacao, filePath);
        catalogoLanches.add(lanche);
        System.out.println("\nLanche cadastrado com sucesso! Codigo: " + lanche.getCode());
    }

    public static void calcularCompra(List<Lanche> catalogoLanches) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        Lanche lancheBuscado = null;
        System.out.println("Insira o CODIGO do produto: ");
        int codigoInserido = scanner.nextInt();

        for(Lanche ptrLanche : catalogoLanches) {
            if(ptrLanche.getCode() == codigoInserido) {
                lancheBuscado = ptrLanche;
                break;
            }
        }
        if(lancheBuscado == null) {
            System.out.println("Codigo fornecido invalido! Consulte a lista de produtos!\n");
            return;
        }

        System.out.println("Insira a QUANTIDADE comprada: ");
        int quantidade = scanner.nextInt();
        System.out.printf("COMPRA TOTAL: R$ %.2f\n\n", quantidade * lancheBuscado.getPrice());
    }

    public static void listarLanches(List<Lanche> catalogoLanches) {
        if(catalogoLanches.isEmpty()) {
            System.out.println("Catalogo de Lanches vazio!\n");
            return;
        }
        for(Lanche ptrLanche : catalogoLanches)
            System.out.println(ptrLanche + "\n");
    }
}
