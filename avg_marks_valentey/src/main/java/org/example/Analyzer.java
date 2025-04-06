package org.example;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Pattern;

// class analyzer
public class Analyzer {

    public AnalysisData analyze(List<File> files) throws IOException {
        Map<String, List<Double>> subjectGrades = new HashMap<>(); // store subject grades
        Map<File, Double> fileAverages = new HashMap<>(); // avg grades
        Set<File> incorrectFiles = new HashSet<>(); // incorrect filled files

        //file  eval
        for (File file : files) {
            try {
                List<String> lines = Files.readAllLines(file.toPath());
                List<Double> grades = new ArrayList<>();
                boolean isValid = true;


                for (String line : lines) {
                    if (!processLine(line, grades, subjectGrades)) {
                        isValid = false;
                    }
                }

                // is valid file?
                if (!isValid || grades.isEmpty()) {
                    incorrectFiles.add(file);
                } else {
                    fileAverages.put(file, calculateAverage(grades));
                }
            } catch (IOException e) {
                incorrectFiles.add(file);
            }
        }

        return new AnalysisData(
                calculateGlobalAverages(subjectGrades),

                findExtremeFiles(fileAverages, true), //best
                findExtremeFiles(fileAverages, false), //worst
                fileAverages,
                incorrectFiles
        );
    }

    // string processing
    private boolean processLine(String line, List<Double> grades, Map<String, List<Double>> subjectGrades) {
        try {
            String[] parts = line.split("-");
            if (parts.length != 2) return false;

            String subject = parts[0].trim().toLowerCase(); // how can i capitalize first letter?
            String gradeStr = parts[1].trim();

            // is correct file?
            if (!Pattern.matches("[а-яА-ЯёЁ\\s-]+", subject)) return false;

            double grade = Double.parseDouble(gradeStr);
            // is correct grade?
            if (grade <= 0 || grade > 5) return false;

            grades.add(grade);
            subjectGrades.computeIfAbsent(subject, k -> new ArrayList<>()).add(grade);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // avg grade list
    private double calculateAverage(List<Double> grades) {
        return grades.stream().mapToDouble(d -> d).average().orElse(0);
    }

    // avg list
    private Map<String, Double> calculateGlobalAverages(Map<String, List<Double>> subjectGrades) {
        Map<String, Double> globalAverages = new HashMap<>();
        subjectGrades.forEach((subject, grades) ->
                globalAverages.put(subject, calculateAverage(grades)));
        return globalAverages;
    }

    // min max
    private List<File> findExtremeFiles(Map<File, Double> fileAverages, boolean findMax) {
        if (fileAverages.isEmpty()) return new ArrayList<>();

        double targetValue = findMax ?
                Collections.max(fileAverages.values()) :
                Collections.min(fileAverages.values());

        List<File> result = new ArrayList<>();
        fileAverages.forEach((file, avg) -> {
            if (avg == targetValue) result.add(file);
        });
        return result;
    }
}