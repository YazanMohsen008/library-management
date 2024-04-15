package com.maids.librarymanagementsystem.log;

import javax.persistence.Transient;

public interface Loggable {
    @Transient
    String fetchId();
    @Transient
    String getRelatedEntity();


}
