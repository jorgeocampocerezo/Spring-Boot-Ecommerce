package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.PageDto;
import com.posgrado.ecommerce.dto.ProductDto;
import com.posgrado.ecommerce.entity.Category;
import com.posgrado.ecommerce.entity.Product;
import com.posgrado.ecommerce.exception.CategoryNotFound;
import com.posgrado.ecommerce.exception.ProductNotFound;
import com.posgrado.ecommerce.mapper.ProductMapper;
import com.posgrado.ecommerce.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

  private ProductRepository productRepository;
  private CategoryService categoryService;
  private ProductMapper productMapper;

  @Override
  public Product save(ProductDto dto) {
    Category category = categoryService.getById(dto.getCategoryId());
    Product product = productMapper.fromDto(dto);
    product.setCategory(category);
    return productRepository.save(product);
  }

  @Override
  public Product getById(UUID id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Product not found"));
  }

  @Override
  public Page<Product> getProduct(Pageable pageable) {
    return productRepository.findAll(pageable);
  }

  @Override
  public PageDto<Product> getProductsFiltered(Double minPrice, Double maxPrice, Pageable pageable) {
    Page<Product> page = productRepository.findByPriceBetween(minPrice, maxPrice, pageable);
    return productMapper.fromEntity(page);
  }

  @Override
  public Product update(UUID id, ProductDto dto) {
    Boolean existsProduct = productRepository.existsById(id);
    if(!existsProduct){
      throw new ProductNotFound(id);
    }
    Product product = productRepository.findById(id).orElseThrow(
        ()-> new RuntimeException("Resource not found")
    );
    Category category = categoryService.getById(dto.getCategoryId());
    product.setName(dto.getName());
    product.setDescription(dto.getDescription());
    product.setImageUrl(dto.getImageUrl());
    product.setPrice(dto.getPrice());
    product.setStock(dto.getStock());
    product.setActive(dto.isActive());
    product.setCategory(category);
    productRepository.save(product);
    return product;

  }

  @Override
  public PageDto<Product> getProductsByIdCategory(UUID idCategory, Pageable pageable) {

    boolean existsCategory = productRepository.existsByCategoryId(idCategory);
    if(!existsCategory){
      throw new CategoryNotFound(idCategory);
    }

    Page<Product> page = productRepository.findProductByCategoryId(idCategory, pageable);
    return productMapper.fromEntity(page);
  }


}
