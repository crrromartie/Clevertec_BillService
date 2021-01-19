package ru.clevertec.bill.util;

import ru.clevertec.bill.entity.SinglePurchase;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.util.List;

import static ru.clevertec.bill.util.BillConfiguratorConstants.*;

public final class BillConfigurator {
    static Logger logger = LogManager.getLogger();

    public String configureBill(List<SinglePurchase> purchases,
                                double cardDiscount,
                                double cardDiscountPercent,
                                double promoDiscount,
                                double totalForPay,
                                String date) {
        StringBuilder builder = new StringBuilder();
        builder.append(SPACE)
                .append(CASH_RECEIPT)
                .append(NEW_LINE)
                .append(SHOP_NAME)
                .append(NEW_LINE)
                .append(CASHIER)
                .append(NEW_LINE)
                .append(date)
                .append(NEW_LINE)
                .append(QUANTITY)
                .append(SPACE)
                .append(DESCRIPTION)
                .append(SPACE)
                .append(PRICE)
                .append(SPACE)
                .append(TOTAL)
                .append(SPACE)
                .append(PROMO)
                .append(NEW_LINE);
        for (SinglePurchase purchase : purchases) {
            builder.append(String.format("%-4d", purchase.getQuantity()))
                    .append(String.format("%-12s", purchase.getProduct().getName()))
                    .append(String.format("%-6.1f", purchase.getProduct().getPrice()))
                    .append(String.format("%-6.1f", purchase.getTotal()))
                    .append(String.format("%-6.1f", purchase.getPromoDiscount()))
                    .append(NEW_LINE);
        }
        builder.append(PROMO_DISCOUNT)
                .append(SPACE)
                .append(String.format("%.1f", promoDiscount))
                .append(NEW_LINE);
        if (cardDiscountPercent != 0) {
            builder.append(CARD_DISCOUNT)
                    .append(OPEN_PARENTHESIS)
                    .append(String.format("%.0f", cardDiscountPercent))
                    .append(PERCENT)
                    .append(CLOSED_PARENTHESIS_COLON)
                    .append(SPACE)
                    .append(String.format("%.1f", cardDiscount))
                    .append(NEW_LINE);
        } else {
            builder.append(NO_DISCOUNT_CARD)
                    .append(NEW_LINE);
        }
        builder.append(TOTAL_PAY)
                .append(SPACE)
                .append(String.format("%.1f", totalForPay));
        return builder.toString();
    }

    public ByteArrayOutputStream configurePDFBill(List<SinglePurchase> purchases,
                                                  double cardDiscount,
                                                  double cardDiscountPercent,
                                                  double promoDiscount,
                                                  double totalForPay,
                                                  String date) {
        Document bill = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(bill, byteArrayOutputStream);
            bill.open();

            Font fontHeaderBill = new Font(Font.FontFamily.COURIER, FONT_SIZE_22);
            addHeaderBill(bill, date, fontHeaderBill);

            PdfPTable table = new PdfPTable(TABLE_COLUMN_AMOUNT);
            table.setSpacingBefore(SPACING_BEFORE_TABLE);
            table.setWidthPercentage(WIDTH_TABLE_PERCENT);
            table.setWidths(new int[]{WIDTH_CELL_QUANTITY, WIDTH_CELL_DESCRIPTION,
                    WIDTH_CELL_PRICE, WIDTH_CELL_TOTAL, WIDTH_CELL_PROMO});

            Font fontHead = new Font(Font.FontFamily.COURIER, FONT_SIZE_18, Font.BOLD);
            fillHeadTable(table, fontHead);

            Font fontBody = new Font(Font.FontFamily.COURIER, FONT_SIZE_18);
            fillBodyTable(table, purchases, fontBody);

            bill.add(table);

            Font fontFooterBill = new Font(Font.FontFamily.COURIER, FONT_SIZE_22);
            addFooterBill(bill, cardDiscount, cardDiscountPercent, promoDiscount, totalForPay, fontFooterBill);

            bill.close();
        } catch (DocumentException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return byteArrayOutputStream;
    }

    private void addHeaderBill(Document document, String date, Font font) throws DocumentException {
        addParagraph(document, CASH_RECEIPT, ALIGNMENT_CENTER_1, LEADING_160, font);
        addParagraph(document, SHOP_NAME, ALIGNMENT_CENTER_1, LEADING_30, font);
        addParagraph(document, CASHIER, ALIGNMENT_CENTER_1, LEADING_30, font);
        addParagraph(document, date, ALIGNMENT_CENTER_1, LEADING_30, font);
    }

    private void fillHeadTable(PdfPTable table, Font font) {
        addCellTable(table, QUANTITY, font);
        addCellTable(table, DESCRIPTION, font);
        addCellTable(table, PRICE, font);
        addCellTable(table, TOTAL, font);
        addCellTable(table, PROMO, font);
    }

    private void fillBodyTable(PdfPTable table, List<SinglePurchase> purchases, Font font) {
        for (SinglePurchase item : purchases) {
            int quantity = item.getQuantity();
            addCellTable(table, String.valueOf(quantity), font);

            String name = item.getProduct().getName();
            addCellTable(table, name, font);

            double price = item.getProduct().getPrice();
            addCellTable(table, String.format("%.1f", price), font);

            double total = item.getTotal();
            addCellTable(table, String.format("%.1f", total), font);

            double promo = item.getPromoDiscount();
            addCellTable(table, String.format("%.1f", promo), font);
        }
    }

    private void addFooterBill(Document document, double cardDiscount,
                               double cardDiscountPercent, double promoDiscount,
                               double totalForPay, Font font) throws DocumentException {

        addParagraph(document, PROMO_DISCOUNT + String.format("%.1f", promoDiscount), ALIGNMENT_RIGHT_0,
                LEADING_30, font);
        if (cardDiscountPercent != 0) {
            addParagraph(document, CARD_DISCOUNT + OPEN_PARENTHESIS
                    + String.format("%.0f", cardDiscountPercent) + PERCENT + CLOSED_PARENTHESIS_COLON
                    + String.format("%.1f", cardDiscount), ALIGNMENT_RIGHT_0, LEADING_30, font);
        } else {
            addParagraph(document, NO_DISCOUNT_CARD, ALIGNMENT_RIGHT_0, LEADING_30, font);
        }
        addParagraph(document, TOTAL_PAY + String.format("%.1f", totalForPay), ALIGNMENT_RIGHT_0, LEADING_30, font);
    }

    private void addParagraph(Document document, String text, int alignment, float leading, Font font)
            throws DocumentException {
        Paragraph paragraph = new Paragraph(new Phrase(text, font));
        paragraph.setAlignment(alignment);
        paragraph.setLeading(leading);
        document.add(paragraph);
    }

    private void addCellTable(PdfPTable table, String text, Font font) {
        PdfPCell c1 = new PdfPCell(new Phrase(text, font));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setBorder(NO_BORDER_0);
        table.addCell(c1);
    }
}
