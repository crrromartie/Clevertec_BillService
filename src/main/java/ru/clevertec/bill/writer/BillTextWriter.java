package ru.clevertec.bill.writer;

import ru.clevertec.bill.util.FilePaths;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BillTextWriter {
    private static final String FILE_FORMAT = ".txt";
    private static final String DATE_FORMAT = "dd-MM-yyyy_HH-mm-ss";

    private BillTextWriter() {
    }

    public static boolean writeBill(String text) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String date = dateFormat.format(new Date());
        String filePath = FilePaths.BILL_PATH + date + FILE_FORMAT;
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write(text);
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
