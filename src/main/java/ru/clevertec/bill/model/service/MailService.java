package ru.clevertec.bill.model.service;

import ru.clevertec.bill.exception.ServiceException;

public interface MailService {

    void sendEmail(String filePath) throws ServiceException;
}
