package ms.bank.controller;

import io.swagger.annotations.ApiOperation;
import ms.bank.model.Bank;
import ms.bank.service.IBankService;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/bank")
public class BankController {

  @Autowired
  private IBankService bankService;
  
  @GetMapping(value = "/findAllBanks", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Find all banks", notes = "Find all banks registered")
  public Mono<ResponseEntity<Flux<Bank>>> findAllBanks() {
    return Mono.just(ResponseEntity
      .ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(bankService.findAll()));
  }

  @GetMapping(value = "/findBankById", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Find bank by id", notes = "Fin bank registered by id")
  public Mono<ResponseEntity<Bank>> findBankById(@RequestParam("id")String id) {
    return bankService.findById(id).flatMap(c -> {
      return Mono.just(ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(c));
    }).defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @DeleteMapping(value = "/deleteBankById", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Delete bank by id", notes = "Delete bank registered by id")
  public Mono<ResponseEntity<Void>> deleteBankById(@RequestParam("id")String id) {
    return bankService.deleteById(id)
      .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
      .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PostMapping(value = "/createBank", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Creates a bank", notes = "Add a new bank",
      response = ResponseEntity.class)
  public Mono<ResponseEntity<Bank>> createBank(@Valid @RequestBody Bank bank) {
    return bankService.create(bank)
              .flatMap(c -> {
                return Mono.just(ResponseEntity.ok()
                      .contentType(MediaType.APPLICATION_JSON)
                      .body(c));
              }).defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PutMapping(value = "/updateBank", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Updates a bank", notes = "Updates a bank registered", 
      response = Bank.class)
  public Mono<ResponseEntity<Bank>> updateBank(@Valid @RequestBody Bank bank) {
    return bankService.update(bank)
              .flatMap(c -> {
                return Mono.just(ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(c));
              })
              .defaultIfEmpty(ResponseEntity.notFound().build());
  }
  
  @GetMapping(value = "/existsById", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Verify if bank exists by id", notes = "Verify if bank registered exists by id")
  public Mono<ResponseEntity<Boolean>> existsById(@RequestParam("id")String id) {
    return bankService.existsById(id).flatMap(c -> {
      return Mono.just(ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(c));
    }).defaultIfEmpty(ResponseEntity.notFound().build());
  }
}
