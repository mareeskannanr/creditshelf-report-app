package com.challenge.app.services;

import com.challenge.app.exceptions.AppException;
import com.challenge.app.models.Company;
import com.challenge.app.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import static com.challenge.app.utils.AppConstants.ValidationService.*;

@Service
public class ValidationService {

    private final CompanyRepository companyRepository;

    public ValidationService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    /**
     * This method used to validate company name
     *
     * @param name
     * @return
     */
    public Company validateCompany(String name) {
        if (name.trim().isEmpty()) {
            throw new AppException(COMPANY_NAME_EMPTY);
        }

        //Considering application allows only one time to upload a file with the company name
        if (!companyRepository.existsByName(name.trim())) {
            throw new AppException(COMPANY_NOT_EXISTS.replace(COMPANY_NAME_HOLDER, name));
        }

        return companyRepository.findByName(name).get(0);
    }

}
