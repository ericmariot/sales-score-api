package com.xpto.sales_score_api.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xpto.sales_score_api.dto.SalespersonDTO;
import com.xpto.sales_score_api.exception.SalespersonNotFoundException;
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
    public List<SalespersonDTO> findAll(@RequestParam(required = false) String registration) {
        return salespersonService.findAll(registration);
    }

    @GetMapping("/{id}")
    public SalespersonDTO getSalesperson(@PathVariable Long id) {
        Salesperson salesperson = salespersonRepository.findById(id)
                .orElseThrow(() -> new SalespersonNotFoundException(id));

        return SalespersonMapper.toDTO(salesperson);
    }

    @PostMapping()
    public ResponseEntity<Object> createClient(@Valid @RequestBody SalespersonDTO salespersonDTO)
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
    public ResponseEntity<Object> updateClient(@PathVariable Long id, @RequestBody SalespersonDTO salespersonDTO) {
        try {
            Salesperson currentSalesperson = salespersonRepository.findById(id)
                    .orElseThrow(() -> new SalespersonNotFoundException(id));
            currentSalesperson.setName(salespersonDTO.getName());
            currentSalesperson.setRegistration(salespersonDTO.getRegistration());

            Salesperson updatedSalesperson = salespersonService.updateSalesperson(currentSalesperson);
            SalespersonDTO updatedSalespersonDTO = SalespersonMapper.toDTO(updatedSalesperson);

            return ResponseEntity.ok(updatedSalespersonDTO);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SalespersonDTO> deleteSalesperson(@PathVariable Long id) {
        salespersonRepository.findById(id).orElseThrow(() -> new SalespersonNotFoundException(id));
        salespersonRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
