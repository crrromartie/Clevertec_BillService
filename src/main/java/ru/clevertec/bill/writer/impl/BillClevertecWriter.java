package ru.clevertec.bill.writer.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
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

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BillClevertecWriter implements BillWriter {
    static Logger logger = LogManager.getLogger();

    private static final String FILE_FORMAT = ".pdf";
    private static final String DATE_FORMAT = "dd-MM-yyyy_HH-mm-ss";

    private static final int PAGE_NUMBER_1 = 1;
    private static final float DEVIATION_X = 0.0f;
    private static final float DEVIATION_Y = 0.0f;

    private final EventManager eventManager = new EventManager(State.PRINT_TXT,
            State.PRINT_PDF, State.PRINT_CLEVERTEC);

    @Override
    public String writeBill(Bill bill) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String date = dateFormat.format(new Date());
        String filePath = FilePath.BILL_CLEVERTEC_PATH + date + FILE_FORMAT;
        Document document = new Document();
        BillConverter billConverter = new BillConverterImpl();
        FileInputStream templateInputStream = null;
        InputStream billInputStream = null;
        try (ByteArrayOutputStream byteBill = billConverter.convertBillToByteArrayOutputStream(bill);
             OutputStream outputStream = new FileOutputStream(filePath)) {
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();

            templateInputStream = new FileInputStream(FilePath.CLEVERTEC_TEMPLATE_PATH);
            printLayer(writer, templateInputStream);

            byte[] billByteArray = byteBill.toByteArray();
            billInputStream = new ByteArrayInputStream(billByteArray);
            printLayer(writer, billInputStream);

            document.close();
            eventManager.notify(State.PRINT_CLEVERTEC, filePath);
        } catch (IOException | DocumentException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            if (templateInputStream != null) {
                try {
                    templateInputStream.close();
                } catch (IOException e) {
                    logger.log(Level.ERROR, e.getMessage());
                }
            }
            if (billInputStream != null) {
                try {
                    templateInputStream.close();
                } catch (IOException e) {
                    logger.log(Level.ERROR, e.getMessage());
                }
            }
        }
        return filePath;
    }

    private void printLayer(PdfWriter writer, InputStream stream) throws IOException {
        PdfReader reader = new PdfReader(stream);
        PdfImportedPage page = writer.getImportedPage(reader, PAGE_NUMBER_1);
        PdfContentByte contentByte = writer.getDirectContent();
        contentByte.addTemplate(page, DEVIATION_X, DEVIATION_Y);
    }

    @Override
    public EventManager getEventManager() {
        return eventManager;
    }
}
