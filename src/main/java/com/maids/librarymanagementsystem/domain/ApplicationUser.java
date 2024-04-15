package com.maids.librarymanagementsystem.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Application_User")
public class ApplicationUser extends GenericDomain <Integer>{

    @Column(name = "English_Name")
    private String englishName;

    @Column(name = "Arabic_Name")
    private String arabicName;

    @Column(name = "Username")
    private String username;

    @Column(name = "Enable")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean isEnable;

    @Column(name = "Password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;



    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Date getCreationDate() {
        return super.getCreationDate();
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Integer getCreatorId() {
        return super.getCreatorId();
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Date getModifiedDate() {
        return super.getModifiedDate();
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Integer getModifierId() {
        return super.getModifierId();
    }

    public ApplicationUser() {
    }

    public ApplicationUser(String username, String encode) {
        this.username = username;
        this.password = encode;
        isEnable = true;
    }

    public ApplicationUser(Integer id, String username, Boolean isEnable, String arabicName, String englishName) {
        this.setId(id);
        this.arabicName = arabicName;
        this.englishName = englishName;
        this.username = username;
        this.isEnable = isEnable;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getArabicName() {
        return arabicName;
    }

    public void setArabicName(String arabicName) {
        this.arabicName = arabicName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getEnable() {
        return isEnable;
    }

    public void setEnable(Boolean enable) {
        isEnable = enable;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
