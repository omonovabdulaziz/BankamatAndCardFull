package uz.pdp.bankamat.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import uz.pdp.bankamat.entity.Card;
import uz.pdp.bankamat.payload.ApiResponse;
import uz.pdp.bankamat.payload.CardDTO;
import uz.pdp.bankamat.service.CardService;


import javax.validation.Valid;

@RestController
@RequestMapping("/api/card")
public class CardController {
    @Autowired
    CardService cardService;

    //create
//    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<?> addCard(@Valid @RequestBody CardDTO cardDTO) {
        ApiResponse apiResponse = cardService.addCard(cardDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }


    //    @PreAuthorize("hasRole('DIREKTOR')")
    //bank orqali kardlarni korish
    @GetMapping("/{bankId}")
    public Page<Card> getPageCard(@RequestParam int page, @RequestParam int count , @PathVariable int bankId) {
    return cardService.getPageOfCard(page , count  , bankId);
    }

    //updateable and deleteable false


}
