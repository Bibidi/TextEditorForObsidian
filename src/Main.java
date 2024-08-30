import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        generateRepetitiveSentences();
    }

    private static void removePattern() {
        Scanner sc = new Scanner(System.in);
        String directory = sc.nextLine();

        try {
            ObsidianTextEditor.removePatternsFromTargetDirectoryFiles(Paths.get(directory));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateRepetitiveSentences() {
        String basicSentence = "- 백엔드 면접 질문 외우기";
        int startNumber = 1;
        int endNumber = 50;
        int gap = 5;
        String result = ObsidianTextEditor.generateRepetitiveSentences(basicSentence, startNumber, endNumber, gap);
        System.out.println(result);
    }
}