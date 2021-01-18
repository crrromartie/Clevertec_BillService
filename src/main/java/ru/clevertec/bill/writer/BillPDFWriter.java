package ru.clevertec.bill.writer;

import ru.clevertec.bill.util.FilePath;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BillPDFWriter {
    static Logger logger = LogManager.getLogger();

    private static final String FILE_FORMAT = ".pdf";
    private static final String DATE_FORMAT = "dd-MM-yyyy_HH-mm-ss";
    private static final int PAGE_NUMBER_1 = 1;
    private static final float DEVIATION_X = 0.0f;
    private static final float DEVIATION_Y = 0.0f;

    private static final String DATE;

    static {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        DATE = dateFormat.format(new Date());
    }

    private BillPDFWriter() {
    }

    public static boolean writeBillPDF(ByteArrayOutputStream byteArrayOutputStream) {
        String filePath = FilePath.BILL_PATH + DATE + FILE_FORMAT;
        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            byteArrayOutputStream.writeTo(outputStream);
        } catch (IOException e) {
            logger.log(Level.ERROR, e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean writeBillTemplatePDF(ByteArrayOutputStream byteArrayOutputStream) {
        String filePath = FilePath.BILL_PATH_TEMPLATE + DATE + FILE_FORMAT;
        Document document = new Document();
        try {
            OutputStream outputStream = new FileOutputStream(filePath);
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();

            FileInputStream template = new FileInputStream(FilePath.TEMPLATE_PATH);
            printLayer(writer, template);

            byte[] billByteArray = byteArrayOutputStream.toByteArray();
            InputStream bill = new ByteArrayInputStream(billByteArray);
            printLayer(writer, bill);

            document.close();
        } catch (DocumentException | IOException e) {
            logger.log(Level.ERROR, e.getMessage());
            return false;
        }
        return true;
    }

    private static void printLayer(PdfWriter writer, InputStream stream) throws IOException {
        PdfReader reader = new PdfReader(stream);
        PdfImportedPage page = writer.getImportedPage(reader, PAGE_NUMBER_1);
        PdfContentByte contentByte = writer.getDirectContent();
        contentByte.addTemplate(page, DEVIATION_X, DEVIATION_Y);
    }
}
