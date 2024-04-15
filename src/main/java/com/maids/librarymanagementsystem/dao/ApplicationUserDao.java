package com.maids.librarymanagementsystem.dao;

import com.maids.librarymanagementsystem.domain.ApplicationUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ApplicationUserDao extends GenericDao<ApplicationUser, Integer> {

    @Query("SELECT U from ApplicationUser U " +
            "where U.username = :name and U.recordStatus=1")
    ApplicationUser findByUserName(@Param("name") String name);


}
