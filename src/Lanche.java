import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.StandardCopyOption;

public class Lanche {
    private final String TARGET_DIR = "imagens"; // diretorio na pasta 'src' do projeto
    private static int codeCount = 0; // inicializando a 'var' estatica uma so vez na static memory
    private final int code;
    private double price;
    private String specification;
    private String imageFilePath;

    public Lanche(double price, String specification, String imageFilePath) throws IOException {
        code = codeCount++; this.price = price;
        this.specification = specification; this.imageFilePath = imageFilePath;
        storingLancheImage(imageFilePath);
    }

    public int getCode() {
        return code;
    }
    public double getPrice() {
        return price;
    }
    public String getName() {
        return specification;
    }
    public String getImageFilePath() {
        return imageFilePath;
    }
    private void storingLancheImage(String imageFilePath) throws IOException {
        Path originFile = Paths.get(imageFilePath);
        Path targetDirectory = Paths.get(TARGET_DIR);
        Path filePathDirectory = targetDirectory.resolve(originFile.getFileName());
        Files.copy(originFile, filePathDirectory, StandardCopyOption.REPLACE_EXISTING);
    }
    @Override
    public String toString() {
        return String.format("\"codigo\": \"%d\", \"preco\": \"%.2f\", \"nome\": \"%s\"", this.code, this.price, this.specification);
    }
}
