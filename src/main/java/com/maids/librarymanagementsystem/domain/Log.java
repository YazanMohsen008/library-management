package com.maids.librarymanagementsystem.domain;
import javax.persistence.*;

@Entity
@Table(name = "Logs")
public class Log extends GenericDomain<Integer> {

    @ManyToOne
    @JoinColumn(name = "Application_User_Id", referencedColumnName = "id")
    ApplicationUser applicationUser;


    @Column(name = "action_type")
    String actionType;

    @Column(name = "Entity_Name")
    private String entityName;

    @Column(name = "Entity_Id")
    private String entityId;


    public Log(ApplicationUser applicationUser, String actionType, String entityName, String entityId) {
        this.applicationUser = applicationUser;
        this.actionType = actionType;
        this.entityName = entityName;
        this.entityId = entityId;
    }

    public Log() {
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

}
