package com.javarush.task.task31.task3105;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив

Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String    fileName = args[0];
        String zipFileName = args[1];

        Path zipPath = Paths.get(zipFileName);
        Path     filePath = Paths.get(fileName);
        ZipEntry fileZipEntry = new ZipEntry(Paths.get("new",filePath.getFileName().toString()).toString());

        Map<String,ByteArrayOutputStream> zipContent = new HashMap<>();
        ZipEntry currentZipEnty;
        ByteArrayOutputStream currentBytes;

        try(ZipInputStream in = new ZipInputStream(Files.newInputStream(zipPath))){
            while ((currentZipEnty = in.getNextEntry())!=null) {
                if(currentZipEnty.getName().equals(fileZipEntry.getName()))
                    continue;
                currentBytes = makeByteArrayOutputStream(in);

                zipContent.put(currentZipEnty.getName(), currentBytes);
            }
        }

        try(ZipOutputStream out = new ZipOutputStream(Files.newOutputStream(zipPath))){
            for(Map.Entry<String,ByteArrayOutputStream> pair: zipContent.entrySet()){
                currentZipEnty = new ZipEntry(pair.getKey());
                currentBytes = pair.getValue();

                out.putNextEntry(currentZipEnty);
                currentBytes.writeTo(out);
                out.closeEntry();
            }
            out.putNextEntry(fileZipEntry);
            Files.copy(filePath,out);
            out.closeEntry();
        }
    }

    public static ByteArrayOutputStream makeByteArrayOutputStream(InputStream in) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int countWritedBytes;

        while ((countWritedBytes = in.read(buffer)) > 0)
            result.write(buffer, 0, countWritedBytes);

        return result;
    }
}
