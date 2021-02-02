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

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BillPDFWriter implements BillWriter {
    static Logger logger = LogManager.getLogger();

    private final EventManager eventManager = new EventManager(State.PRINT_TXT,
            State.PRINT_PDF, State.PRINT_CLEVERTEC);

    @Override
    public String writeBill(Bill bill) {
        BillConverter billConverter = new BillConverterImpl();
        try (ByteArrayOutputStream byteBill = billConverter.convertBillToByteArrayOutputStream(bill);
             OutputStream outputStream = new FileOutputStream(FilePath.BILL_PDF_PATH)) {
            byteBill.writeTo(outputStream);
            eventManager.notify(State.PRINT_PDF, FilePath.BILL_PDF_PATH);
        } catch (IOException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return FilePath.BILL_PDF_PATH;
    }

    @Override
    public EventManager getEventManager() {
        return eventManager;
    }
}
