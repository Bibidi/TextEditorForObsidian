import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ObsidianTextEditor {
    public static void removePatternsFromAllFiles(Path directory) throws IOException {
        File[] files = directory.toFile().listFiles();
        if (files == null) return;
        for (File file : files) {
            if (file.isDirectory()) {
                removePatternsFromAllFiles(file.toPath());
                continue;
            }
            if (!file.isFile()) continue;
            if (!file.getName().endsWith(".md")) continue;
            removeAnkiIdFromFile(file.toPath());
            removeExcessiveNewlines(file.toPath());
        }
    }

    public static void removePatternsFromTargetDirectoryFiles(Path directory) throws IOException {
        File[] files = directory.toFile().listFiles();
        if (files == null) return;
        for (File file : files) {
            if (!file.isFile()) continue;
            if (!file.getName().endsWith(".md")) continue;
            removeAnkiIdFromFile(file.toPath());
            removeExcessiveNewlines(file.toPath());
        }
    }

    public static String generateRepetitiveSentences(String basicSentence, int startNumber, int endNumber, int gap) {
        StringBuilder sb = new StringBuilder();
        while (startNumber <= endNumber) {
            sb
                    .append(basicSentence)
                    .append(" ")
                    .append(startNumber)
                    .append(" ~ ");
            if (startNumber + gap > endNumber) sb.append(endNumber);
            else sb.append(startNumber + gap - 1);
            sb.append("\n");
            startNumber += gap;
        }

        return sb.toString();
    }

    private static void removeAnkiIdFromFile(Path filePath) throws IOException {
        String targetPattern = "<!--ID: \\d+-->";
        String replacement = "";
        replacePatternFromFile(filePath, targetPattern, replacement);
    }

    private static void removeExcessiveNewlines(Path filePath) throws IOException {
        String targetPattern = "(\\r?\\n){4,}";
        String replacement = "\n\n\n";
        replacePatternFromFile(filePath, targetPattern, replacement);
    }

    private static void replacePatternFromFile(Path filePath, String pattern, String replacement) throws IOException {
        byte[] fileBytes = Files.readAllBytes(filePath);
        String content = new String(fileBytes, StandardCharsets.UTF_8);
        String modifiedContent = content.replaceAll(pattern, replacement);
        byte[] modifiedBytes = modifiedContent.getBytes(StandardCharsets.UTF_8);

        Files.write(filePath, modifiedBytes, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
