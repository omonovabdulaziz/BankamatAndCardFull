package uz.pdp.bankamat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.event.ExceptionEvent;
import org.springframework.stereotype.Service;
import uz.pdp.bankamat.entity.Bank;
import uz.pdp.bankamat.entity.Bankamat;
import uz.pdp.bankamat.entity.CardType;
import uz.pdp.bankamat.entity.Employee;
import uz.pdp.bankamat.payload.ApiResponse;
import uz.pdp.bankamat.payload.BankamatDTO;
import uz.pdp.bankamat.repository.BankRepository;
import uz.pdp.bankamat.repository.BankamatRepository;
import uz.pdp.bankamat.repository.EmployeeRepository;
import uz.pdp.bankamat.repository.PlastikTuriRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class BankamatService {
    @Autowired
    BankamatRepository bankamatRepository;
    @Autowired
    BankRepository bankRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PlastikTuriRepository plastikTuriRepository;

    public ApiResponse addBankamat(BankamatDTO bankamatDTO) {
        Optional<Bank> optionalBank = bankRepository.findById(bankamatDTO.getBankId());
        if (!optionalBank.isPresent())
            return new ApiResponse("Bankamat uchun ulanadigan banki topilmadi", false);

        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(bankamatDTO.getHodimEmail());
        if (!optionalEmployee.isPresent())
            return new ApiResponse("Bankamat uchun ulanadigan hodim topilmadi", false);


        Bankamat bankamat = new Bankamat();
        //commission pul biriligi somda
        bankamat.setCommision(bankamatDTO.getCommision());
        bankamat.setManzil(bankamatDTO.getManzil());
        bankamat.setMaxPayMoney(bankamatDTO.getMaxPayMoney());
        bankamat.setBank(optionalBank.get());
        bankamat.setEmployee(optionalEmployee.get());
        Integer i = 1;
        Set<CardType> cardTypes = new HashSet<>();
        for (Integer integer : bankamatDTO.getPlastikTuriId()) {
            Optional<CardType> optionalCardType = plastikTuriRepository.findById(integer);
            if (!optionalCardType.isPresent()) {
                return new ApiResponse("xatolik plastikTuri joylashda", false, i + " chi id li plastik turi mavjud emas");
            } else {
                CardType cardType = optionalCardType.get();
                cardTypes.add(cardType);
                i++;
            }
        }
        bankamat.setCardType(cardTypes);
        bankamatRepository.save(bankamat);
        return new ApiResponse("saved", true);

    }

    public Page<Bankamat> getBankamat(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return bankamatRepository.findAll(pageable);
    }


    public ApiResponse updateBankamat(Integer id, BankamatDTO bankamatDTO) {

        Optional<Bankamat> optionalBankamat = bankamatRepository.findById(id);
        if (!optionalBankamat.isPresent())
            return new ApiResponse("tahrirlanadigan bankamat topilmadi", false);

        Optional<Bank> optionalBank = bankRepository.findById(bankamatDTO.getBankId());
        if (!optionalBank.isPresent())
            return new ApiResponse("Bankamat uchun ulanadigan banki topilmadi", false);

        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(bankamatDTO.getHodimEmail());
        if (!optionalEmployee.isPresent())
            return new ApiResponse("Bankamat uchun ulanadigan hodim topilmadi", false);

        Bankamat bankamat = optionalBankamat.get();
        //commission pul biriligi somda
        bankamat.setCommision(bankamatDTO.getCommision());
        bankamat.setManzil(bankamatDTO.getManzil());
        bankamat.setMaxPayMoney(bankamatDTO.getMaxPayMoney());
        bankamat.setBank(optionalBank.get());
        bankamat.setEmployee(optionalEmployee.get());
        Integer i = 1;
        Set<CardType> cardTypes = new HashSet<>();
        for (Integer integer : bankamatDTO.getPlastikTuriId()) {
            Optional<CardType> optionalCardType = plastikTuriRepository.findById(integer);
            if (!optionalCardType.isPresent()) {
                return new ApiResponse("xatolik plastikTuri joylashda", false, i + " chi id li plastik turi mavjud emas");
            } else {
                CardType cardType = optionalCardType.get();
                cardTypes.add(cardType);
                i++;
            }
        }
        bankamat.setCardType(cardTypes);
        bankamat.setId(id);
        bankamatRepository.save(bankamat);
        return new ApiResponse("updated", true);
    }

    public ApiResponse deleteBankamat(Integer id) {
        try {
            bankamatRepository.deleteById(id);
            return new ApiResponse("deleted ", true);
        } catch (Exception e) {
            return new ApiResponse("Xatolik ", false);
        }
    }
}
