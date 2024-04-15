package com.maids.librarymanagementsystem.utils.service;

import com.maids.librarymanagementsystem.dao.ApplicationUserDao;
import com.maids.librarymanagementsystem.domain.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Seeder {
    private final ApplicationUserDao applicationUserDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public Seeder(ApplicationUserDao applicationUserDao, PasswordEncoder passwordEncoder) {
        this.applicationUserDao = applicationUserDao;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void prepareData() {
        try {
            String adminName = "Admin";
            String encodePassword = passwordEncoder.encode("123456");
            ApplicationUser admin = applicationUserDao.findByUserName(adminName);
            if (admin == null) {
                admin = new ApplicationUser(adminName, encodePassword);
                applicationUserDao.save(admin);
            }
            String employeeName = "employee";
            ApplicationUser employee = applicationUserDao.findByUserName(employeeName);
            if (employee == null) {
                employee = new ApplicationUser(employeeName, encodePassword);
                applicationUserDao.save(employee);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
