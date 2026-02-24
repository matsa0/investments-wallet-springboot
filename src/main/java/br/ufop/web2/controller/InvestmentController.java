package br.ufop.web2.controller;

import br.ufop.web2.dto.investment.*;
import br.ufop.web2.service.InvestmentService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/investments")
@RequiredArgsConstructor
public class InvestmentController {

    private final InvestmentService service;

    @PostMapping("/user/{userId}")
    public ResponseEntity<InvestmentDTO> create(@PathVariable UUID userId, @RequestBody CreateInvestmentDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(userId, dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<InvestmentDTO>> findAllByUserId(@PathVariable(value = "userId") UUID id) {
        List<InvestmentDTO> investments = service.findAllByUserId(id);
        return ResponseEntity.ok(investments);
    }

    @GetMapping("/user/{userId}/type/{typeId}")
    public ResponseEntity<List<InvestmentDTO>> findAllByUserIdAndType(@PathVariable UUID userId, @PathVariable Integer typeId) {

        List<InvestmentDTO> investments = service.findAllByUserIdAndType(userId, typeId);
        return ResponseEntity.ok(investments);
    }

    @PutMapping
    public ResponseEntity<InvestmentDTO> update(@RequestBody UpdateInvestmentDTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody DeleteInvestmentDTO dto) {
        service.delete(dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/summary/{userId}")
    public ResponseEntity<SummaryDTO> summary(@PathVariable(value = "userId") UUID id) {
        SummaryDTO summary = service.summary(id);
        return ResponseEntity.ok(summary);
    }
}
