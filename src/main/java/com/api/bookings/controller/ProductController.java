package com.api.bookings.controller;


import com.api.bookings.api.ProductApi;
import com.api.bookings.model.*;
import com.api.bookings.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.api.bookings.service.utils.ParserUtil.convertValue;
import static org.springframework.http.HttpStatus.*;

@RestController
public class ProductController extends BaseController implements ProductApi {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<ProductCodeTO> createProduct(@Valid ProductTO newProduct) {

        ProductModel product = ProductModel.builder()
                .name(newProduct.getName())
                .description(newProduct.getDescription())
                .category(newProduct.getCategory())
                .user(newProduct.getUser())
                .date(newProduct.getDate())
                .build();

        productRepository.save(product);

        ProductCodeTO response = new ProductCodeTO();
        return new ResponseEntity<>( CREATED);
    }

    @Override
    public ResponseEntity<ResponseProductsTO> getProduct(String code) {
        Optional<ProductModel> optional = productRepository.findById(code);
        if (!optional.isPresent()) throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        ResponseProductsTO list = new ResponseProductsTO();
        ProductResponseTO to =  convertValue(optional.get(), ProductResponseTO.class);
        list.addProductItem(to);
        return new ResponseEntity<>(list, OK);
    }

    @Override
    public ResponseEntity<ResponseProductsTO> getProducts() {
        List<ProductModel> products = productRepository.findAll();

        ResponseProductsTO apiResponseProductsTO = new ResponseProductsTO();
        products.forEach(product -> {
            ProductResponseTO to =  convertValue(product, ProductResponseTO.class);
            apiResponseProductsTO.addProductItem(to);
        });

        return new ResponseEntity<>(apiResponseProductsTO, OK);
    }

    @Override
    public ResponseEntity<Void> updateProduct(String code, @Valid UpdateProductTO product) {
        Optional<ProductModel> optional = productRepository.findById(code);
        ProductModel prod = optional.get();
        ProductModel up = prod.builder()
                .code(prod.getCode())
                .name(product.getName())
                .description(product.getDescription())
                .category(product.getCategory())
                .price(product.getPrice())
                .date(prod.getDate())
                .user(prod.getUser())
                .build();
        productRepository.save(up);

        return new ResponseEntity<>(OK);
    }
}


