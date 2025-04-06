package org.example;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FileList {
    private static final Pattern pattern = Pattern.compile("^[а-яА-ЯёЁ]+ [а-яА-ЯёЁ]+ [а-яА-ЯёЁ]+$");

    // correct files
    public List<File> GetFileList(String path) {
        List<File> result = new ArrayList<>();
        File dir = new File(path);

        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("Not direcory inputed" + path);
        }

        File[] files = dir.listFiles();
        if (files == null) {
            return result;
        }

        for (File file : files) {
            if (file.isFile() && correct_name(file)) {
                result.add(file);
            }
        }
        return result;
    }

    //if name is correct
    private boolean correct_name(File file) {
        String filename = file.getName();

        if (!filename.toLowerCase().endsWith(".txt")) {
            return false;
        }
        String onlyname = filename.substring(0, filename.lastIndexOf('.'));

        return pattern.matcher(onlyname).matches();
    }
}