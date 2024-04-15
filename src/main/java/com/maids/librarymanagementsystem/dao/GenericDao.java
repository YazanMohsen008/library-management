package com.maids.librarymanagementsystem.dao;


import com.maids.librarymanagementsystem.domain.GenericDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface GenericDao<T extends GenericDomain, IdClass> extends JpaRepository<T, IdClass>, Serializable {



}
