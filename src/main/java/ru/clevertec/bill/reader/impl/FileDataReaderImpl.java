package ru.clevertec.bill.reader.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.clevertec.bill.reader.FileDataReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class FileDataReaderImpl implements FileDataReader {
    static Logger logger = LogManager.getLogger();

    @Override
    public String readData(File file) {
        String filePath = file.getPath();
        String text;
        try {
            text = Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            logger.log(Level.FATAL, "File reading error", e);
            throw new RuntimeException("File not available, data not read", e);
        }
        return text;
    }
}
