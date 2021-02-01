package ru.clevertec.bill.util;

public class FilePath {
    public static final String ORDER_FILE_PATH = "C:/Users/IGOR/IdeaProjects/Clevertec_BillService" +
            "/order/order.txt";

    public static final String BILL_PATH = "C:/Users/IGOR/IdeaProjects//Clevertec_BillService/bill/bill-";
    //(bill- + time + extension) - name file, generated in class

    public static final String BILL_CLEVERTEC_PATH = "C:/Users/IGOR/IdeaProjects/Clevertec_BillService/bill/" +
            "bill(template)-";
    //(bill(template)- + time + extension) - name file, generated in class

    public static final String CLEVERTEC_TEMPLATE_PATH = "C:/Users/IGOR/IdeaProjects/Clevertec_BillService/src" +
            "/main/resources/templates/Clevertec_Template.pdf";

    private FilePath() {
    }
}
