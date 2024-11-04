package com.xpto.sales_score_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xpto.sales_score_api.dto.sale.SaleDTO;
import com.xpto.sales_score_api.exception.NotFoundException;
import com.xpto.sales_score_api.mapper.SaleMapper;
import com.xpto.sales_score_api.model.Sale;
import com.xpto.sales_score_api.repo.SaleRepository;
import com.xpto.sales_score_api.service.SaleService;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleService saleService;

    @GetMapping
    public List<SaleDTO> findAll() {
        return saleRepository.findAll().stream().map(SaleMapper::toDTO).toList();
    }

    @GetMapping("/{id}")
    public SaleDTO getSale(@PathVariable Long id) {
        Sale sale = saleRepository.findById(id).orElseThrow(() -> new NotFoundException("sale", id));

        return SaleMapper.toDTO(sale);
    }

    @PostMapping()
    public ResponseEntity<SaleDTO> createSale(@RequestBody SaleDTO saleDTO) {
        SaleDTO savedSale = saleService.createSale(saleDTO);
        return new ResponseEntity<>(savedSale, HttpStatus.CREATED);
    }
}
