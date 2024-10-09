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
    public Salesperson getSalesperson(@PathVariable Long id) {
        return salespersonRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity<Object> createClient(@Valid @RequestBody Salesperson salesperson)
            throws URISyntaxException {
        try {
            Salesperson savedSalesperson = salespersonService.createSalesperson(salesperson);
            return ResponseEntity.created(new URI("/api/salespersons/" + savedSalesperson.getId())).body(savedSalesperson);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Salesperson> updateClient(@PathVariable Long id, @RequestBody Salesperson salesperson) {
        Salesperson currentSalesperson = salespersonRepository.findById(id).orElseThrow(RuntimeException::new);
        currentSalesperson.setName(salesperson.getName());
        currentSalesperson = salespersonRepository.save(salesperson);

        return ResponseEntity.ok(currentSalesperson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Salesperson> deleteSalesperson(@PathVariable Long id) {
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
