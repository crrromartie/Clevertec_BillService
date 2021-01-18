package ru.clevertec.bill.runner;

import ru.clevertec.bill.util.BillMaker;
import ru.clevertec.bill.builder.OrderBuilder;
import ru.clevertec.bill.entity.Order;
import ru.clevertec.bill.util.FilePath;
import ru.clevertec.bill.writer.BillPDFWriter;
import ru.clevertec.bill.writer.BillTextWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class Runner {

    public static void main(String[] args) {
        File file = new File(FilePath.ORDER_FILE_PATH);

        OrderBuilder orderBuilder = new OrderBuilder();
        Order order = orderBuilder.buildOrder(file);

        BillMaker billMaker = new BillMaker();
        String bill = billMaker.makeBill(order);
        ByteArrayOutputStream outputStreamBill = billMaker.makePDFBill(order);

        System.out.println(bill);

        BillTextWriter.writeBill(bill);
        BillPDFWriter.writeBillPDF(outputStreamBill);
        BillPDFWriter.writeBillTemplatePDF(outputStreamBill);
    }
}
