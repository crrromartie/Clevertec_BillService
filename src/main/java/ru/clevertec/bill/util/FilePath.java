package ru.clevertec.bill.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FilePath {
    private static final String DATE_FORMAT = "dd-MM-yyyy_HH-mm-ss";
    private static final String DATE = new SimpleDateFormat(DATE_FORMAT).format(new Date());

    public static final String ORDER_FILE_PATH = "C:/Users/IGOR/IdeaProjects/Clevertec_BillService" +
            "/order/order.txt";

    public static final String BILL_TXT_PATH = "C:/Users/IGOR/IdeaProjects//Clevertec_BillService/bill/bill-" +
            DATE + ".txt";

    public static final String BILL_PDF_PATH = "C:/Users/IGOR/IdeaProjects//Clevertec_BillService/bill/bill-" +
            DATE + ".pdf";

    public static final String BILL_CLEVERTEC_PATH = "C:/Users/IGOR/IdeaProjects//Clevertec_BillService/bill" +
            "/bill(clevertec)-" + DATE + ".pdf";

    public static final String CLEVERTEC_TEMPLATE_PATH = "C:/Users/IGOR/IdeaProjects/Clevertec_BillService/src" +
            "/main/resources/templates/Clevertec_Template.pdf";

    private FilePath() {
    }
}
