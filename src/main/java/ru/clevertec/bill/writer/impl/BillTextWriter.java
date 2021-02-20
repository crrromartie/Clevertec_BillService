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


public class BillTextWriter implements BillWriter {
    static Logger logger = LogManager.getLogger();

    private final EventManager eventManager = new EventManager(State.PRINT_TXT,
            State.PRINT_PDF, State.PRINT_CLEVERTEC);

    @Override
    public String writeBill(Bill bill) {
        BillConverter billConverter = new BillConverterImpl();
        String billString = billConverter.convertBillToString(bill);
        try (FileWriter writer = new FileWriter(FilePath.BILL_TXT_PATH)) {
            writer.write(billString);
            eventManager.notify(State.PRINT_CLEVERTEC, FilePath.BILL_TXT_PATH);
        } catch (IOException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return FilePath.BILL_TXT_PATH;
    }

    @Override
    public EventManager getEventManager() {
        return eventManager;
    }
}
