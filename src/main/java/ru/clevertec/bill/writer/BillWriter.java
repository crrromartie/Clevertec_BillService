package ru.clevertec.bill.writer;

import ru.clevertec.bill.entity.Bill;

public interface BillWriter {

    boolean writeBill(Bill bill);
}
