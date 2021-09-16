package com.app.source;

import com.app.source.controllers.AuthController;
import com.app.source.entities.Company;
import com.app.source.entities.Major;
import com.app.source.exceptions.CompanyNotExistedException;
import com.app.source.exceptions.EmailAlreadyExistedException;
import com.app.source.exceptions.EmptyRoleException;
import com.app.source.exceptions.MajorNotExistedException;
import com.app.source.exceptions.UsernameAlreadyExistedException;
import com.app.source.payload.request.SignupRequest;
import com.app.source.repositories.AccountRepository;
import com.app.source.repositories.CompanyRepository;
import com.app.source.repositories.MajorRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Seeder {

    private final AuthController authController;
    private final AccountRepository accountRepository;
    private final MajorRepository majorRepository;
    private final CompanyRepository companyRepository;


    public Seeder(AuthController authController, AccountRepository accountRepository, MajorRepository majorRepository, CompanyRepository companyRepository) {
        this.authController = authController;
        this.accountRepository = accountRepository;
        this.majorRepository = majorRepository;
        this.companyRepository = companyRepository;
    }

    @EventListener
    private void seed(ApplicationReadyEvent event) {
        if (companyRepository.count() == 0) {
            seedCompany();
        }
        if (majorRepository.count() == 0) {
            seedMajor();
        }
        if (accountRepository.count() == 0) {
            seedAccount();
        }
    }

    private void seedMajor() {
        List<Major> majors = Arrays.asList(
                new Major("Software Engineering"),
                new Major("Business Administration")
        );
        majorRepository.saveAll(majors);
    }

    private void seedCompany() {
        List<Company> companies = Arrays.asList(
                new Company("Company A", "Description for company A"),
                new Company("Company B", "Description for company B")
        );
        companyRepository.saveAll(companies);
    }

    private void seedAccount() {
        List<SignupRequest> signupRequests = Arrays.asList(
                new SignupRequest("thanhthu0321@gmail.com", "123456", "Thu", "SYS_ADMIN", "0988988796"),
                new SignupRequest("student1@gmailcom", "123456", "First Student", "STUDENT", "0123867861", "123 Street, Ward 11, A District, ABC city", "SE150099", Long.valueOf(1)),
                new SignupRequest("student2@gmailcom", "123456", "Second Student", "STUDENT", "0123667861", "124 Street, Ward 11, A District, ABC city", "SE150098", Long.valueOf(1)),
                new SignupRequest("student3@gmailcom", "123456", "Third Student", "STUDENT", "0123897861", "125 Street, Ward 11, A District, ABC city", "BA150097", Long.valueOf(2)),
                new SignupRequest("representative1@gmail.com", "123456", "First Representative", "COMPANY_REPRESENTATIVE", "1237894560", Long.valueOf(1)),
                new SignupRequest("representative2@gmail.com", "123456", "Second Representative", "COMPANY_REPRESENTATIVE", "1237894560", Long.valueOf(2)));

        signupRequests.stream().forEach(signupRequest -> {
            try {
                authController.registerUser(signupRequest);
            } catch (UsernameAlreadyExistedException | EmailAlreadyExistedException | EmptyRoleException | CompanyNotExistedException | MajorNotExistedException e) {
                e.printStackTrace();
            }
        });
    }
}