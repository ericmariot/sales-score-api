package com.xpto.sales_score_api.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

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

import com.xpto.sales_score_api.dto.salesperson.ProfitSalespersonDTO;
import com.xpto.sales_score_api.dto.salesperson.SalesCountSalespersonDTO;
import com.xpto.sales_score_api.dto.salesperson.SalespersonDTO;
import com.xpto.sales_score_api.dto.salesperson.SalespersonUpdateDTO;
import com.xpto.sales_score_api.exception.NotFoundException;
import com.xpto.sales_score_api.mapper.SalespersonMapper;
import com.xpto.sales_score_api.model.Salesperson;
import com.xpto.sales_score_api.repo.SalespersonRepository;
import com.xpto.sales_score_api.service.SalespersonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/salespersons")
public class SalespersonController {

    private final SalespersonRepository salespersonRepository;

    @Autowired
    private SalespersonService salespersonService;

    public SalespersonController(SalespersonRepository salespersonRepository) {
        this.salespersonRepository = salespersonRepository;
    }

    @GetMapping
    public List<SalespersonDTO> getSalespersons(@RequestParam(required = false) String registration) {
        return salespersonService.findAll(registration);
    }

    @GetMapping("/{id}")
    public SalespersonDTO getSalesperson(@PathVariable Long id) {
        Salesperson salesperson = salespersonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("salesperson", id));

        return SalespersonMapper.toDTO(salesperson);
    }

    @GetMapping("/sales-count")
    public ResponseEntity<List<SalesCountSalespersonDTO>> getSalesCountSalesperson() {
        List<SalesCountSalespersonDTO> salesCount = salespersonService.getSalesCountSalesperson();

        return new ResponseEntity<>(salesCount, HttpStatus.OK);
    }

    @GetMapping("/profit")
    public ResponseEntity<List<ProfitSalespersonDTO>> getProfitSalesperson() {
        List<ProfitSalespersonDTO> profits = salespersonService.getProfitSalesperson();

        return new ResponseEntity<>(profits, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> postSalesperson(@Valid @RequestBody SalespersonDTO salespersonDTO)
            throws URISyntaxException {
        try {
            Salesperson salesperson = SalespersonMapper.toEntity(salespersonDTO);
            Salesperson savedSalesperson = salespersonService.createSalesperson(salesperson);
            SalespersonDTO savedSalespersonDTO = SalespersonMapper.toDTO(savedSalesperson);

            return ResponseEntity.created(new URI("/api/salespersons/" + savedSalespersonDTO.getId()))
                    .body(savedSalespersonDTO);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putSalesperson(@PathVariable Long id,
            @Valid @RequestBody SalespersonUpdateDTO salespersonUpdateDTO) {
        try {
            Salesperson currentSalesperson = salespersonRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("salesperson", id));

            if (salespersonUpdateDTO.getName() != null) {
                currentSalesperson.setName(salespersonUpdateDTO.getName());
            }

            if (salespersonUpdateDTO.getRegistration() != null) {
                currentSalesperson.setRegistration(salespersonUpdateDTO.getRegistration());
            }

            Salesperson updatedSalesperson = salespersonService.updateSalesperson(currentSalesperson);
            SalespersonDTO updatedSalespersonDTO = SalespersonMapper.toDTO(updatedSalesperson);

            return ResponseEntity.ok(updatedSalespersonDTO);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SalespersonDTO> deleteSalesperson(@PathVariable Long id) {
        salespersonService.deleteSalesperson(id);
        return ResponseEntity.ok().build();
    }
}
