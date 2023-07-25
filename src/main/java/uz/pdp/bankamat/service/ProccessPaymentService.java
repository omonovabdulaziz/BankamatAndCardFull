package uz.pdp.bankamat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.bankamat.entity.*;
import uz.pdp.bankamat.payload.ApiResponse;
import uz.pdp.bankamat.payload.ProccessPaymentDTO;
import uz.pdp.bankamat.repository.*;

import java.util.Objects;
import java.util.Optional;

@Service
public class ProccessPaymentService {
    @Autowired
    ProccessPaymentRepository proccessPaymentRepository;
    @Autowired
    BankRepository bankRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    KupyuralarRepository kupyuralarRepository;
    @Autowired
    PlastikTuriRepository plastikTuriRepository;
    @Autowired
    PulBirligiRepository pulBirligiRepository;
    @Autowired
    BankamatRepository bankamatRepository;

    public ApiResponse addProccessPayment(ProccessPaymentDTO proccessPaymentDTO) {


        Optional<Card> optionalCard = cardRepository.findByCVVAndParol(proccessPaymentDTO.getCardCvv(), String.valueOf(proccessPaymentDTO.getCardParol()));

        if (!optionalCard.isPresent())
            return new ApiResponse("card topiilmadi yoki parol hato", false);

        Card card = optionalCard.get();
        CardType plastikTuri = card.getPlastikTuri();
        boolean checker = false;
        for (Integer integer : plastikTuriRepository.findByBankamatId(proccessPaymentDTO.getBankamatId())) {
            CardType cardType = plastikTuriRepository.findById(integer).get();
            if (Objects.equals(plastikTuri.getId(), cardType.getId())) {
                checker = true;
            }
        }
        if (checker) {
            Long umumiyPulMiqdori = 0L;
            Optional<Bankamat> optionalBankamat = bankamatRepository.findById(proccessPaymentDTO.getBankamatId());
            if (!optionalBankamat.isPresent())
                return new ApiResponse("bankamat yoq", false);
            for (Long count : proccessPaymentDTO.getKupyuralar()) {
                Kupyuralar kupyuralar = kupyuralarRepository.findByCount(count).get();
                Integer id = kupyuralar.getPulBirligi().getId();
                Long counti = kupyuralar.getCount();
                Integer nechta = kupyuralar.getNechta();
                Long pulMiqdori = (Long) counti * nechta;
                switch (id) {
                    case 2 -> {
                        pulMiqdori = pulMiqdori * 11630;
                    }
                    case 3 -> {
                        pulMiqdori = pulMiqdori * 12800;
                    }
                    case 4 -> {
                        pulMiqdori = pulMiqdori * 128;
                    }

                }
                umumiyPulMiqdori += pulMiqdori;
            }
            if (umumiyPulMiqdori > optionalBankamat.get().getMaxPayMoney())
                return new ApiResponse("Bankamatdan juda kop pul miqdori talab qilyapsiz bankamat " + optionalBankamat.get().getMaxPayMoney() + " gacha pul yecha oladi", true);

            umumiyPulMiqdori += optionalBankamat.get().getCommision();
            long newbalance = cardRepository.findByCVV(proccessPaymentDTO.getCardCvv()).get().getBalance() - umumiyPulMiqdori;
            cardRepository.updateByBalance(newbalance, proccessPaymentDTO.getCardCvv());
            long newbalancebankamat = optionalBankamat.get().getBalance() - (umumiyPulMiqdori - optionalBankamat.get().getCommision());
            bankamatRepository.updateByBankamat(newbalancebankamat, proccessPaymentDTO.getBankamatId());
            return new ApiResponse("Tolov muvaffaqiyatli amalga oshirildi kartangizni olishingiz mumkin", true);

        }

        return new ApiResponse("sizni kartangiz turi bilan bankamatniki mos kelmadi", false, "sizning cardtypeingiz  " + plastikTuri.getNomi() + " bankamatniki esa boshqa");

    }

    public Page<ProccesPayment> getPageProccessPayment(int page, int count, Integer bankamatId) {
        Pageable pageable = PageRequest.of(page, count);
        return proccessPaymentRepository.findByBankamatId(bankamatId, pageable);

    }
}
