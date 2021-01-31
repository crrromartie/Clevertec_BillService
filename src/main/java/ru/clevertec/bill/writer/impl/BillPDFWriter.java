package ru.clevertec.bill.writer.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.clevertec.bill.entity.Bill;
import ru.clevertec.bill.util.BillConverter;
import ru.clevertec.bill.util.FilePath;
import ru.clevertec.bill.util.impl.BillConverterImpl;
import ru.clevertec.bill.writer.BillWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BillPDFWriter implements BillWriter {
    static Logger logger = LogManager.getLogger();

    private static final String FILE_FORMAT = ".pdf";
    private static final String DATE_FORMAT = "dd-MM-yyyy_HH-mm-ss";

    @Override
    public boolean writeBill(Bill bill) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String date = dateFormat.format(new Date());
        String filePath = FilePath.BILL_PATH + date + FILE_FORMAT;
        BillConverter billConverter = new BillConverterImpl();
        try (ByteArrayOutputStream byteBill = billConverter.convertBillToByteArrayOutputStream(bill);
             OutputStream outputStream = new FileOutputStream(filePath)) {
            byteBill.writeTo(outputStream);
        } catch (IOException e) {
            logger.log(Level.ERROR, e.getMessage());
            return false;
        }
        return true;
    }
}
