package ru.clevertec.bill.writer;

import ru.clevertec.bill.entity.Bill;
import ru.clevertec.bill.observer.EventManager;

public interface BillWriter {

    String writeBill(Bill bill);

    EventManager getEventManager();
}
