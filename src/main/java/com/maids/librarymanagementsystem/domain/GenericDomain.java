package com.maids.librarymanagementsystem.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.maids.librarymanagementsystem.log.Loggable;
import com.maids.librarymanagementsystem.config.security.model.CustomUserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class GenericDomain<IdClass> implements Serializable , Loggable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected IdClass id;

    public GenericDomain() {
    }

    public GenericDomain(IdClass id) {
        this.id = id;
    }


    @Column(name = "Record_Status")
    Integer recordStatus = 1;

    @Column(name = "Creation_Date", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    Date creationDate;

    @Column(name = "CreatorId", updatable = false)
    Integer creatorId;

    @Column(name = "Modified_Date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    Date modifiedDate;

    @Column(name = "ModifierId")
    Integer modifierId;


    @PrePersist
    public void setDatePrePersist() {
        this.setCreationDate(new Date());
        if (CustomUserDetails.getCurrentInstance() != null && CustomUserDetails.getCurrentInstance().getApplicationUser() != null)
            this.setCreatorId(CustomUserDetails.getCurrentInstance().getApplicationUser().getId());
    }

    @PreUpdate
    public void setDatePreUpdate() {
        this.setModifiedDate(new Date());
        if (CustomUserDetails.getCurrentInstance() != null && CustomUserDetails.getCurrentInstance().getApplicationUser() != null)
            this.setModifierId(CustomUserDetails.getCurrentInstance().getApplicationUser().getId());
    }

    @Override
    public String fetchId() {
        return id.toString();
    }

    @Override
    public String getRelatedEntity() {
        return getClass().getSimpleName();
    }

    public IdClass getId() {
        return id;
    }

    public void setId(IdClass id) {
        this.id = id;
    }

    public Integer getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getModifierId() {
        return modifierId;
    }

    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
    }
}
