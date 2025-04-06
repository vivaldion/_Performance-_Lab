package org.example;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConsolePrinter {

//all results
    public void printToConsole(AnalysisData data) {
        List<File> bestFiles = data.bestFiles();
        List<File> worstFiles = data.worstFiles();
        int totalStudents = data.fileAverages().size();
        // im too tired to make it better

        if (bestFiles.equals(worstFiles)) {
            printAverages(data.globalAverages());
            printStudents("\nЛучший ученик:", "Лучшие ученики:",
                    data.bestFiles(), data.fileAverages());
        } else {


            printAverages(data.globalAverages());
            printStudents("Лучший ученик:", "Лучшие ученики:",
                    data.bestFiles(), data.fileAverages());
            printStudents("\nХудший ученик:", "\nХудшие ученики:",
                    data.worstFiles(), data.fileAverages());
            printInvalidFiles(data.incorrectFiles());
        }
        System.out.println("\nКоличество учеников: " + totalStudents);
    }

   //avg grades
    private void printAverages(Map<String, Double> globalAverages) {
        globalAverages.forEach((subject, avg) ->
                System.out.printf("%s: %.2f%n", subject, avg));
        System.out.println();
    }

// best worst
    private void printStudents(String singleTitle, String multipleTitle,
                               List<File> files, Map<File, Double> averages) {
        if (!files.isEmpty()) {
            System.out.println(files.size() == 1 ? singleTitle : multipleTitle);
            files.forEach(file ->
                    System.out.printf("%s (средний балл - %.2f)%n", file.getName(), averages.get(file)));
        }
    }

// incorrect files
    private void printInvalidFiles(Set<File> incorrectFiles) {
        if (!incorrectFiles.isEmpty()) {
            System.out.println("\nНеправильно заполненные файлы");
            incorrectFiles.forEach(file -> System.out.println(file.getName()));
        }
    }
}
