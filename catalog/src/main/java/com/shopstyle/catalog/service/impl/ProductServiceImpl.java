package com.shopstyle.catalog.service.impl;

import com.shopstyle.catalog.model.Category;
import com.shopstyle.catalog.model.DTO.ProductDTO;
import com.shopstyle.catalog.model.Product;
import com.shopstyle.catalog.repository.CategoryRepository;
import com.shopstyle.catalog.repository.ProductRepository;
import com.shopstyle.catalog.service.ProductService;
import com.shopstyle.catalog.service.SequenceGeneration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MongoOperations mongoOperations;
    @Autowired
    SequenceGeneration sequence;
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Product create(ProductDTO productDTO) {
        Boolean active = false;
        Product product = new ModelMapper().map(productDTO, Product.class);
        product.setProduct_id(sequence.getSequenceNumber(Product.SEQUENCE_NAME));
        List<Long> category_ids = product.getCategory_ids();
        for (Long category_id : category_ids) {
            Category category = categoryRepository.findByCategory_id(category_id);
            active = category.getActive();
            if (active) {
                mongoTemplate.save(product);
                mongoTemplate.update(Category.class)
                        .matching(where("category_id").is(category.getCategory_id()))
                        .apply(new Update().push("productList", product))
                        .first();
            }
        }
        if (active) {
            return productRepository.save(product);
        } else {
            throw new RuntimeException("Category no active");
        }
    }

    @Override
    public Product findById(long product_id) {
        Product product = productRepository.findByProduct_id(product_id);
        if (product != null) {
            return product;
        } else throw new RuntimeException("Product not found");
    }

    @Override
    public Product update(ProductDTO productDTO) {
        findById(productDTO.getProduct_id());
        Product product = new ModelMapper().map(productDTO, Product.class);
        product.setProduct_id(productDTO.getProduct_id());
        return productRepository.save(product);
    }



    @Override
    public void deleteById(long product_id) {
        findById(product_id);
        productRepository.deleteById(product_id);
    }

    @Override
    public List<Product> findAll() {
        return   productRepository.findAll();
    }
    }




