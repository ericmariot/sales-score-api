package com.xpto.sales_score_api.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xpto.sales_score_api.dto.product.MostSoldProductDTO;
import com.xpto.sales_score_api.dto.product.ProductDTO;
import com.xpto.sales_score_api.dto.product.ProductUpdateDTO;
import com.xpto.sales_score_api.exception.NotFoundException;
import com.xpto.sales_score_api.mapper.ProductMapper;
import com.xpto.sales_score_api.model.Product;
import com.xpto.sales_score_api.repo.ProductRepository;
import com.xpto.sales_score_api.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDTO> getProducts() {
        return productRepository.findAll().stream().map(ProductMapper::toDTO).toList();
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("product", id));

        return ProductMapper.toDTO(product);
    }

    @GetMapping("/quantity-sold")
    public ResponseEntity<List<MostSoldProductDTO>> getMostSoldProducts(
            @RequestParam(defaultValue = "desc") String order) {
        List<MostSoldProductDTO> products = productService.getMostSoldProducts(order);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> postProduct(@Valid @RequestBody ProductDTO productDTO) throws URISyntaxException {
        Product product = ProductMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        ProductDTO savedProductDTO = ProductMapper.toDTO(savedProduct);

        return ResponseEntity.created(new URI("/api/products/" + savedProductDTO.getId())).body(savedProductDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> putProduct(@PathVariable Long id,
            @Valid @RequestBody ProductUpdateDTO productUpdateDTO) {
        Product currentProduct = productRepository.findById(id).orElseThrow(() -> new NotFoundException("product", id));

        if (productUpdateDTO.getName() != null) {
            currentProduct.setName(productUpdateDTO.getName());
        }

        if (productUpdateDTO.getPrice() != null) {
            currentProduct.setPrice(productUpdateDTO.getPrice());
        }

        Product updatedProduct = productRepository.save(currentProduct);
        ProductDTO updatedProductDTO = ProductMapper.toDTO(updatedProduct);

        return ResponseEntity.ok(updatedProductDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
