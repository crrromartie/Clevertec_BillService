package ru.clevertec.bill.model.service;

import ru.clevertec.bill.entity.Bill;
import ru.clevertec.bill.entity.Order;
import ru.clevertec.bill.exception.ServiceException;

public interface BillService {

    Bill makeBill(Order order) throws ServiceException;
}
