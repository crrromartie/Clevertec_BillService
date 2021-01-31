package ru.clevertec.bill.util;

import ru.clevertec.bill.entity.Bill;

import java.io.ByteArrayOutputStream;

public interface BillConverter {

    String convertBillToString(Bill bill);

    ByteArrayOutputStream convertBillToByteArrayOutputStream(Bill bill);
}
