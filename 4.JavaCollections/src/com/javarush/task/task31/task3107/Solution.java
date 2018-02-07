package com.javarush.task.task31.task3107;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
Null Object Pattern
*/
public class Solution {
    private FileData fileData;

    public Solution(String pathToFile) {
        Path path = Paths.get(pathToFile);
        boolean fileIsHidden = false,
                fileIsDirectory = false,
                fileIsExecutable = false,
                fileIsWritable = false;

        try {
            /*if (Files.exists(path)) {*/
                if (Files.isHidden(path)) fileIsHidden = true;
                if (Files.isDirectory(path)) fileIsDirectory = true;
                if (Files.isExecutable(path)) fileIsExecutable = true;
                if (Files.isWritable(path)) fileIsWritable = true;
            /*} else
                fileData = new NullFileData(new NullPointerException());*/

            fileData = new ConcreteFileData(fileIsHidden, fileIsExecutable, fileIsDirectory, fileIsWritable);
        } catch (IOException e) {
            fileData = new NullFileData(e);
        }
    }

    public FileData getFileData() {
        return fileData;
    }
}
