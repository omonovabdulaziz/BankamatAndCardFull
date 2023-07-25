package uz.pdp.bankamat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.bankamat.entity.Bankamat;
import uz.pdp.bankamat.entity.Kupyuralar;
import uz.pdp.bankamat.entity.PulBirligi;
import uz.pdp.bankamat.payload.ApiResponse;
import uz.pdp.bankamat.payload.KupyuralarDTO;
import uz.pdp.bankamat.repository.BankamatRepository;
import uz.pdp.bankamat.repository.KupyuralarRepository;
import uz.pdp.bankamat.repository.PulBirligiRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class KupyuralarService {
    @Autowired
    KupyuralarRepository kupyuralarRepository;

    @Autowired
    PulBirligiRepository pulBirligiRepository;
    @Autowired
    BankamatRepository bankamatRepository;
    @Autowired
    JavaMailSender javaMailSender;

    //create
    Kupyuralar kupyuralar = new Kupyuralar();

    public ApiResponse addKupyuralar(KupyuralarDTO kupyuralarDTO) {
        Optional<Bankamat> optionalBankamat = bankamatRepository.findById(kupyuralarDTO.getBankamatId());
        if (!optionalBankamat.isPresent())
            return new ApiResponse("Kupyuralar boruvchi bankamat topilmadi", false);

        Optional<PulBirligi> optionalPulBirligi = pulBirligiRepository.findById(kupyuralarDTO.getPulBirligiId());
        if (!optionalBankamat.isPresent())
            return new ApiResponse("kupyuralar uchun pul birligi topilmadi", false);
        kupyuralar.setBankamat(optionalBankamat.get());
        kupyuralar.setNechta(kupyuralarDTO.getNechta());
        kupyuralar.setCount(kupyuralarDTO.getCount());
        kupyuralar.setPulBirligi(optionalPulBirligi.get());
        kupyuralar.setPasswordForEnabled(UUID.randomUUID().toString());
        kupyuralar.setBankHodimiEmailiForEnabled(optionalBankamat.get().getEmployee().getEmail());
        Kupyuralar kupyuralaredited = kupyuralarRepository.save(kupyuralar);

        sendEmail(kupyuralaredited.getBankHodimiEmailiForEnabled(), kupyuralaredited.getPasswordForEnabled());
        return new ApiResponse("Jonatishni tasdiqlang ", true);
    }


    public Boolean sendEmail(String sendingEmail, String emailCode) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("aloqa@pdp.com");
            simpleMailMessage.setTo(sendingEmail);
            simpleMailMessage.setSubject("Bankamatga borayotgan pulni tasdiqlang");
            simpleMailMessage.setText("<a href = 'http://localhost:8080/api/kupyuralar/verifyEmail?emailCode=" + emailCode + "&email=" + sendingEmail + "' >Tasdiqlang sizning bankamatingizga " + kupyuralar.getCount() + "    " + kupyuralar.getNechta() + " ta " + kupyuralar.getPulBirligi().getNomi() + " bilan toldiriladi </a>");
            javaMailSender.send(simpleMailMessage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public ApiResponse verifyEmail(String email, String emailCode) {
        Optional<Kupyuralar> optionalKupyuralar = kupyuralarRepository.findByBankHodimiEmailiForEnabledAndPasswordForEnabled(email, emailCode);
        if (optionalKupyuralar.isPresent()) {
            Kupyuralar editedKupyuralar = optionalKupyuralar.get();
            editedKupyuralar.setEnabled(true);
            editedKupyuralar.setPasswordForEnabled(null);
            return new ApiResponse("tasdiqlandi ", true);

        }
        return new ApiResponse("allaqachon tasdiqlangan", false);
    }

    //read
    public Page<Kupyuralar> getKupyuralar(int page, int count, Integer id) {
        Pageable pageable = PageRequest.of(page, count);
        return kupyuralarRepository.findByBankamatId(id, pageable);
    }


    //updateable and deleteable false
}
