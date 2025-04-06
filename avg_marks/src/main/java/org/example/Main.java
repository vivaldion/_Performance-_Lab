package org.example;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // ./pupils
        try {
            // input path
            System.out.print("Введите путь к папке с файлами студентов: ");
            String path = scanner.nextLine().trim();

            // get file list
            FileList fileList = new FileList();
            List<File> files = fileList.GetFileList(path);

            if (files.isEmpty()) {
                System.out.println("В указанной папке нет подходящих файлов с именами формата 'Ф И О.txt'");
                return;
            }

            // analyze
            Analyzer analyzer = new Analyzer();
            AnalysisData data = analyzer.analyze(files);

            // console printer
            ConsolePrinter printer = new ConsolePrinter();
            printer.printToConsole(data);

            // report
            ReportWriter reportWriter = new ReportWriter();
            reportWriter.writeToFile(data, "отчет.txt");

        } catch (IllegalArgumentException e) {
            System.err.println("failture: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("failed while processing file" + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}