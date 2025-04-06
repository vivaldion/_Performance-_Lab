package org.example;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ReportWriter {
    public void writeToFile(AnalysisData data, String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            data.globalAverages().forEach((subject, avg) ->
                    writer.printf("%s: %.2f%n", subject, avg));

            List<File> bestFiles = data.bestFiles();
            List<File> worstFiles = data.worstFiles();
            int totalStudents = data.fileAverages().size();

            if (bestFiles.equals(worstFiles)) {
                writeStudentList(writer,
                        "\nЛучший ученик:",
                        "Лучшие ученики:",
                        data.bestFiles(),
                        data.fileAverages());
            } else {

                writeStudentList(writer,
                        "\nЛучший ученик:",
                        "Лучшие ученики:",
                        data.bestFiles(),
                        data.fileAverages());

                writeStudentList(writer,
                        "\nХудший ученик:",
                        "Худшие ученики:",
                        data.worstFiles(),
                        data.fileAverages());
            }

            writer.println("\nКоличество учеников: " + totalStudents);

            if (!data.incorrectFiles().isEmpty()) {
                writer.println("\nНеправильное заполненные файлы");
                data.incorrectFiles().forEach(file -> writer.println(file.getName()));
            }
        }
    }
    private void writeStudentList(PrintWriter writer,
                                  String singleTitle,
                                  String multipleTitle,
                                  List<File> files,
                                  Map<File, Double> averages) {
        if (!files.isEmpty()) {
            writer.println(files.size() == 1 ? singleTitle : multipleTitle);
            files.forEach(file ->
                    writer.printf("%s (средний балл - %.2f)%n",
                            file.getName(), averages.get(file)));
        }
    }
}