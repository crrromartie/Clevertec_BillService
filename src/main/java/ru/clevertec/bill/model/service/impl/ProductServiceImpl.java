package ru.clevertec.bill.model.service.impl;

import ru.clevertec.bill.builder.ProductBuilder;
import ru.clevertec.bill.builder.impl.ProductBuilderImpl;
import ru.clevertec.bill.controller.command.ParameterName;
import ru.clevertec.bill.entity.Product;
import ru.clevertec.bill.exception.DaoException;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.dao.DaoFactory;
import ru.clevertec.bill.model.dao.ProductDao;
import ru.clevertec.bill.model.service.ProductService;
import ru.clevertec.bill.validator.ProductValidator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao = DaoFactory.getINSTANCE().getProductDao();

    @Override
    public Optional<Product> findById(long id) throws ServiceException {
        try {
            return productDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> findAll() throws ServiceException {
        try {
            return productDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Product> add(Map<String, String> productParameters) throws ServiceException {
        if (!ProductValidator.isProductParametersValid(productParameters)) {
            return Optional.empty();
        }
        ProductBuilder productBuilder = new ProductBuilderImpl();
        productBuilder.setName(productParameters.get(ParameterName.PRODUCT_NAME));
        productBuilder.setPrice(BigDecimal.valueOf(Double
                .parseDouble(productParameters.get(ParameterName.PRODUCT_PRICE))));
        try {
            return productDao.add(productBuilder.getProduct());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Product> edit(Map<String, String> editProductParameters, long id) throws ServiceException {
        if (!ProductValidator.isProductParametersValid(editProductParameters)) {
            return Optional.empty();
        }
        ProductBuilder productBuilder = new ProductBuilderImpl();
        productBuilder.setProductId(id);
        productBuilder.setName(editProductParameters.get(ParameterName.PRODUCT_NAME));
        productBuilder.setPrice(BigDecimal.valueOf(Double.parseDouble(editProductParameters
                .get(ParameterName.PRODUCT_PRICE))));
        productBuilder.setPromo(Boolean.parseBoolean(editProductParameters.get(ParameterName.PRODUCT_PROMO)));
        try {
            return productDao.edit(productBuilder.getProduct());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
            productDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isProductNameUnique(String name) throws ServiceException {
        try {
            return productDao.findByName(name).isEmpty();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
