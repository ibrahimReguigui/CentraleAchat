package com.CentraleAchat.userservice.services.entitiesService;

import com.CentraleAchat.userservice.dto.CompanyDto;
import com.CentraleAchat.userservice.entities.Company;
import com.CentraleAchat.userservice.mappers.CompanyMapper;
import com.CentraleAchat.userservice.repositories.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyServiceImp implements CompanyService{


    private CompanyRepository companyRepository;



    @Override
    public CompanyDto addCompany(CompanyDto companyDto) {
        Company company= companyRepository.save(CompanyMapper.mapToEntity(companyDto));
        return CompanyMapper.mapToDto(company);
    }

//    @Override
//    @Transactional
//    public void deleteCompany(Long idCompany) {
//        userRepository.deleteAllByCompanyIdCompany(idCompany);
//        companyRepository.delete(companyRepository.findById(idCompany).get());
//    }

    @Override
    public CompanyDto getCompany(Long idCompany) {
        return CompanyMapper.mapToDto(companyRepository.findById(idCompany).get());
    }

    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }
}
