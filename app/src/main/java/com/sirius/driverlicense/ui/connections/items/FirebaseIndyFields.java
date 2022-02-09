package com.sirius.driverlicense.ui.connections.items;

import java.io.Serializable;

public class FirebaseIndyFields implements Serializable {
    String name;
    String referentId;
    String value;
    String description;
    boolean selfAttested;


    public FirebaseIndyFields(String referentId, String name, String value, String description, boolean selfAttested) {
        this.name = name;
        this.value = value;
        this.description = description;
        this.selfAttested = selfAttested;
        this.referentId = referentId;
    }

    public FirebaseIndyFields(FirebaseIndyFields firebaseIndyFields) {
        this.name = firebaseIndyFields.getName();
        this.value = firebaseIndyFields.getValue();
        this.description = firebaseIndyFields.getDescription();
        this.referentId = firebaseIndyFields.getReferentId();
        this.selfAttested = firebaseIndyFields.isSelfAttested();
    }

    public FirebaseIndyFields() {
    }


    public String getReferentId() {
        return referentId;
    }

    public void setReferentId(String referentId) {
        this.referentId = referentId;
    }


    public boolean isSelfAttested() {
        return selfAttested;
    }

    public void setSelfAttested(boolean selfAttested) {
        this.selfAttested = selfAttested;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descroption) {
        this.description = descroption;
    }


    @Override
    public String toString() {
        return "{name="+name+" value="+value+" description="+description+"}";
    }
}
