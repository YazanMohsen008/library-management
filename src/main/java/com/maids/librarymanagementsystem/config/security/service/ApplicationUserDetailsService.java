package com.maids.librarymanagementsystem.config.security.service;

import com.maids.librarymanagementsystem.config.security.model.CustomUserDetails;
import com.maids.librarymanagementsystem.dao.ApplicationUserDao;
import com.maids.librarymanagementsystem.domain.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {


    private final ApplicationUserDao applicationUserDao;

    @Autowired
    public ApplicationUserDetailsService(ApplicationUserDao applicationUserDao) {
        this.applicationUserDao = applicationUserDao;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        // Fetch the appUser corresponding to the given username
        ApplicationUser applicationUser = applicationUserDao.findByUserName(name);
        // If the appUser doesn't exist
        if (applicationUser == null)
            throw new UsernameNotFoundException("User" + name + " not found");

        String password = applicationUser.getPassword();
        User user = new CustomUserDetails(applicationUser.getUsername(), password, true, true, true, true, new ArrayList<>());
        ((CustomUserDetails) user).setApplicationUser(applicationUser);
        return user;
    }


}