package ru.clevertec.bill.model.service.impl;

import ru.clevertec.bill.entity.Bill;
import ru.clevertec.bill.entity.Order;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.service.BillService;

public class BillServiceImpl implements BillService {
    private static final BillServiceImpl INSTANCE = new BillServiceImpl();

    private final BillService billService = new BillServiceImplementation();

    private BillServiceImpl() {
    }

    public static BillServiceImpl getINSTANCE() {
        return INSTANCE;
    }

    public BillService getBillService() {
        return billService;
    }

    @Override
    public Bill makeBill(Order order) throws ServiceException {
        return billService.makeBill(order);
    }
}
