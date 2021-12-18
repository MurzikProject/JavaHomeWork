package com.pb.potapenko.hw11;

import java.util.Date;
import java.time.LocalDateTime;

public class Contact{
    private static int countContacts = 0;

    private String contactFIO;
    private Date contactBDAY;
    private String[] contactPHONES;
    private String contactADRESS;
    private LocalDateTime contactMODIFYDT;

    public Contact(String contactFIO, Date contactBDAY, String[] contactPHONES, String contactADRESS, LocalDateTime contactMODIFYDT){
        this.contactFIO = contactFIO;
        this.contactBDAY = contactBDAY;
        this.contactPHONES = contactPHONES;
        this.contactADRESS = contactADRESS;
        this.contactMODIFYDT = contactMODIFYDT;
        countContacts++;
    }

    public String getContactFIO() {
        return contactFIO;
    }

    public Date getContactBDAY() {
        return contactBDAY;
    }

    public String[] getContactPHONES() {
        return contactPHONES;
    }

    public String getContactADRESS() {
        return contactADRESS;
    }

    public LocalDateTime getContactMODIFYDT() {
        return contactMODIFYDT;
    }

    public void setContactFIO(String contactFIO) {
        this.contactFIO = contactFIO;
    }

    public void setContactBDAY(Date contactBDAY) {
        this.contactBDAY = contactBDAY;
    }

    public void setContactMODIFYDT(LocalDateTime contactMODIFYDT) {
        this.contactMODIFYDT = contactMODIFYDT;
    }

    public static int getCountContacts(){
        return countContacts;
    }
}
