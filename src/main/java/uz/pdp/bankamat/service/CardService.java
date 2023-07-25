package uz.pdp.bankamat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.bankamat.entity.Bank;
import uz.pdp.bankamat.entity.Card;
import uz.pdp.bankamat.entity.CardType;
import uz.pdp.bankamat.payload.ApiResponse;
import uz.pdp.bankamat.payload.CardDTO;
import uz.pdp.bankamat.repository.BankRepository;
import uz.pdp.bankamat.repository.CardRepository;
import uz.pdp.bankamat.repository.PlastikTuriRepository;

import java.util.Optional;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    BankRepository bankRepository;
    @Autowired
    PlastikTuriRepository plastikTuriRepository;

    public ApiResponse addCard(CardDTO cardDTO) {
        Optional<Bank> optionalBank = bankRepository.findById(cardDTO.getBankId());
        if (!optionalBank.isPresent())
            return new ApiResponse("bank topilmadi", false);

        Optional<CardType> optionalCardType = plastikTuriRepository.findById(cardDTO.getPlastikTuri());
        if (!optionalCardType.isPresent())
            return new ApiResponse("plastik turi yoq", false);

        if (cardRepository.findByCVV(cardDTO.getCVV()).isPresent())
            return new ApiResponse("bunday carta mavjud", false);

        Card card = new Card();
        card.setBalance(cardDTO.getBalance());
        card.setCVV(cardDTO.getCVV());
        card.setParol(cardDTO.getParol());
        card.setMijozFullName(cardDTO.getMijozFullName());
        card.setBank(optionalBank.get());
        card.setPlastikTuri(optionalCardType.get());
        cardRepository.save(card);
        return new ApiResponse("saqlandi ", true);

    }

    public Page<Card> getPageOfCard(int page, int count, int bankId) {
        Pageable pageable = PageRequest.of(page, count);
        return cardRepository.findByBankId(bankId, pageable);
    }

 //updateable and deleteable false
}
