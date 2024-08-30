import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String directory = sc.nextLine();

        try {
            ObsidianTextEditor.removePatternsFromTargetDirectoryFiles(Paths.get(directory));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}