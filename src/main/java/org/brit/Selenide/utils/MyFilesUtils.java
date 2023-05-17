package org.brit.Selenide.utils;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MyFilesUtils {
    @SneakyThrows
    public static File generateLoremFile() {
        Faker faker = new Faker();
        List<String> words = faker.lorem().words(100);
        File generatedFile = new File("files", RandomStringUtils.randomAlphanumeric(10) + ".txt");
        FileUtils.writeLines(generatedFile, words);
        return generatedFile;
    }

    @SneakyThrows
    public static void cleanFilesFolder() {
        File fileDir = new File("files");
        if (!fileDir.exists()) {
            fileDir.mkdir();
        } else {
            FileUtils.cleanDirectory(new File("files"));
        }
    }

    public static void cleanScreenshotsDirectory() {
        createOrCleanDir("screenshots");
    }

    @SneakyThrows
    public static void cleanDownloadsDirectory() {
        FileUtils.cleanDirectory(new File("downloads"));
    }

    private static void createOrCleanDir(String dirName) {
        File fileDir = new File(dirName);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        } else {
            try {
                FileUtils.cleanDirectory(new File(dirName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SneakyThrows
    public static File waitTillFileIsLoaded(File file) {
        int count = 0;
        while (count < 60) {
            if (!file.exists()) {
                Thread.sleep(1000);
                count++;
            } else {
                break;
            }
        }

        count = 0;
        while(count < 60) {
            long lengthBefore = file.length();
            Thread.sleep(1000);
            long lengthAfter = file.length();
            if (lengthBefore == lengthAfter) {
                return file;
            } else {
                count++;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        generateLoremFile();
    }
}
