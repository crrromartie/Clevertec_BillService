package ru.clevertec.bill.writer.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.clevertec.bill.entity.Bill;
import ru.clevertec.bill.observer.EventManager;
import ru.clevertec.bill.observer.entity.State;
import ru.clevertec.bill.util.BillConverter;
import ru.clevertec.bill.util.FilePath;
import ru.clevertec.bill.util.impl.BillConverterImpl;
import ru.clevertec.bill.writer.BillWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BillTextWriter implements BillWriter {
    static Logger logger = LogManager.getLogger();

    private static final String FILE_FORMAT = ".txt";
    private static final String DATE_FORMAT = "dd-MM-yyyy_HH-mm-ss";

    private final EventManager eventManager = new EventManager(State.PRINT_TXT,
            State.PRINT_PDF, State.PRINT_CLEVERTEC);

    @Override
    public String writeBill(Bill bill) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String date = dateFormat.format(new Date());
        String filePath = FilePath.BILL_PATH + date + FILE_FORMAT;
        BillConverter billConverter = new BillConverterImpl();
        String billString = billConverter.convertBillToString(bill);
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(billString);
            eventManager.notify(State.PRINT_CLEVERTEC, filePath);
        } catch (IOException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return filePath;
    }

    @Override
    public EventManager getEventManager() {
        return eventManager;
    }
}
