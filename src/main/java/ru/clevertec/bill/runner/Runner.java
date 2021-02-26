package ru.clevertec.bill.runner;

import ru.clevertec.bill.builder.OrderBuilder;
import ru.clevertec.bill.builder.impl.OrderBuilderImpl;
import ru.clevertec.bill.entity.Bill;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.service.BillService;
import ru.clevertec.bill.model.service.ServiceFactory;
import ru.clevertec.bill.parser.TextOrderParser;
import ru.clevertec.bill.parser.impl.TextOrderParserImpl;
import ru.clevertec.bill.reader.FileDataReader;
import ru.clevertec.bill.reader.impl.FileDataReaderImpl;
import ru.clevertec.bill.util.BillConverter;
import ru.clevertec.bill.util.FilePath;
import ru.clevertec.bill.util.impl.BillConverterImpl;
import ru.clevertec.bill.writer.BillWriter;
import ru.clevertec.bill.writer.BillWriterFactory;

import java.io.File;
import java.util.Map;

public class Runner {
    public static void main(String[] args) throws ServiceException {
        File file = new File(FilePath.ORDER_FILE_PATH);

        FileDataReader fileDataReader = new FileDataReaderImpl();
        String data = fileDataReader.readData(file);

        TextOrderParser textOrderParser = new TextOrderParserImpl();
        Map<Long, Integer> orderParameters = textOrderParser.parsOrderParameters(data);
        int cardNumber = textOrderParser.parsCardNumber(data);

        OrderBuilder orderBuilder = new OrderBuilderImpl();
        orderBuilder.setPurchaseParameters(orderParameters);
        orderBuilder.setCardNumber(cardNumber);

        BillService billService = ServiceFactory.getINSTANCE().getBillService();
        Bill bill = billService.makeBill(orderBuilder.getOrder());

        BillWriter billWriter = BillWriterFactory.BILL_CLEVERTEC_WRITER.getBillWriter();
        System.out.println(billWriter.writeBill(bill));

        BillConverter billConverter = new BillConverterImpl();
        String billString = billConverter.convertBillToString(bill);
        System.out.println(billString);
    }
}
