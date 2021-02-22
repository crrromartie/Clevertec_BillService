package ru.clevertec.bill.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.clevertec.bill.controller.Router;
import ru.clevertec.bill.controller.command.*;
import ru.clevertec.bill.entity.Bill;
import ru.clevertec.bill.writer.BillWriter;
import ru.clevertec.bill.writer.BillWriterFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class WriteBillCommand implements Command {
    static Logger logger = LogManager.getLogger();

    private static final String BILL_SAVE_PARAMETER = "&saveBill=true";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(new StringBuilder()
                .append(request.getContextPath())
                .append(CommandPath.BILL_PASS)
                .append(BILL_SAVE_PARAMETER).toString());
        router.setRedirect();
        HttpSession session = request.getSession();
        Bill bill = (Bill) session.getAttribute(AttributeName.BILL);
        String writingFormat = request.getParameter(ParameterName.WRITING_FORMAT);
        BillWriter billWriter;
        switch (writingFormat) {
            case ParameterName.TXT -> billWriter = BillWriterFactory.BILL_TEXT_WRITER.getBillWriter();
            case ParameterName.PDF -> billWriter = BillWriterFactory.BILL_PDF_WRITER.getBillWriter();
            case ParameterName.PDF_CLEVERTEC -> billWriter = BillWriterFactory.BILL_CLEVERTEC_WRITER.getBillWriter();
            default -> {
                router.setPage(PagePath.ERROR_404);
                logger.log(Level.WARN, "Unexpected writing format");
                throw new IllegalStateException("Unexpected value: " + writingFormat);
            }
        }
        session.setAttribute(AttributeName.BILL_FILE_PATH, billWriter.writeBill(bill));
        return router;
    }
}
