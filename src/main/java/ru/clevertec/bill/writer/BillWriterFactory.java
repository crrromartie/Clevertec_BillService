package ru.clevertec.bill.writer;

import ru.clevertec.bill.writer.impl.BillClevertecWriter;
import ru.clevertec.bill.writer.impl.BillPDFWriter;
import ru.clevertec.bill.writer.impl.BillTextWriter;

public enum BillWriterFactory {
    BILL_TEXT_WRITER(new BillTextWriter()),
    BILL_PDF_WRITER(new BillPDFWriter()),
    BILL_CLEVERTEC_WRITER(new BillClevertecWriter());

    private final BillWriter billWriter;

    BillWriterFactory(BillWriter billWriter) {
        this.billWriter = billWriter;
    }

    public BillWriter getBillWriter() {
        return billWriter;
    }
}
