package ru.clevertec.bill.model.service.impl;

import ru.clevertec.bill.entity.Product;
import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.service.ProductService;
import ru.clevertec.bill.model.service.proxy.ProductServiceHandler;

import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private static final ProductServiceImpl INSTANCE = new ProductServiceImpl();

    private final ProductService productService = new ProductServiceImplementation();

    private ProductServiceImpl() {
    }

    public static ProductServiceImpl getINSTANCE() {
        return INSTANCE;
    }

    public ProductService getProductService() {
        ClassLoader productServiceClassLoader = productService.getClass().getClassLoader();
        Class<?>[] productServiceInterfaces = productService.getClass().getInterfaces();
        return (ProductService) Proxy.newProxyInstance(productServiceClassLoader,
                productServiceInterfaces, new ProductServiceHandler(productService));
    }

    @Override
    public List<Product> findAll() throws ServiceException {
        return productService.findAll();
    }

    @Override
    public Optional<Product> findById(long id) throws ServiceException {
        return productService.findById(id);
    }

    @Override
    public boolean add(Product product) throws ServiceException {
        return productService.add(product);
    }

    @Override
    public void delete(long id) throws ServiceException {
        productService.delete(id);
    }

    @Override
    public Optional<Product> findByName(String name) throws ServiceException {
        return productService.findByName(name);
    }

    @Override
    public void delete(String name) throws ServiceException {
        productService.delete(name);
    }

    @Override
    public boolean changePrice(long id, BigDecimal price) throws ServiceException {
        return productService.changePrice(id, price);
    }

    @Override
    public boolean changePrice(String name, BigDecimal price) throws ServiceException {
        return productService.changePrice(name, price);
    }

    @Override
    public boolean makePromo(long id) throws ServiceException {
        return productService.makePromo(id);
    }

    @Override
    public boolean makePromo(String name) throws ServiceException {
        return productService.makePromo(name);
    }

    @Override
    public boolean removePromo(long id) throws ServiceException {
        return productService.removePromo(id);
    }

    @Override
    public boolean removePromo(String name) throws ServiceException {
        return productService.removePromo(name);
    }

    @Override
    public boolean addPromoProduct(Product product) throws ServiceException {
        return productService.addPromoProduct(product);
    }
}
