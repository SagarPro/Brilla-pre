package com.brightkidmont.brilla.utils;

public class GuestUser {

    public String guestName;
    public String guestPhone;
    public String guestEmail;
    public String guestPassword;
    public String guestLocation;

    public GuestUser() {
    }

    public GuestUser(String guestName, String guestPhone, String guestEmail, String guestPassword, String guestLocation) {
        this.guestName = guestName;
        this.guestPhone = guestPhone;
        this.guestEmail = guestEmail;
        this.guestPassword = guestPassword;
        this.guestLocation = guestLocation;
    }

}
