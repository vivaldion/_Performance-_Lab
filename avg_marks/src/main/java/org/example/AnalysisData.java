// AnalysisData - record класс для хранения результатов анализа
package org.example;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
globalAverages - avg grades
bestFiles - best students
worstFiles - worst student
fileAverages - avg in file
incorrectFiles - incorrect filled files
 */
public record AnalysisData(
        Map<String, Double> globalAverages,
        List<File> bestFiles,
        List<File> worstFiles,
        Map<File, Double> fileAverages,
        Set<File> incorrectFiles
        ) {}
