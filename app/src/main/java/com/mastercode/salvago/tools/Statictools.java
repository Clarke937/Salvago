package com.mastercode.salvago.tools;

import java.util.Calendar;
import java.util.Date;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Statictools {

    public static String getSimpleDate(){
        String fecha = "";
        Calendar cal = Calendar.getInstance();
        fecha += cal.get(Calendar.DAY_OF_MONTH) + "-";
        fecha += (cal.get(Calendar.MONTH) + 1) + "-";
        fecha += cal.get(Calendar.YEAR);
        return fecha;
    }

    public static boolean isEmailValid(String mail){
        boolean ok = true;
        try {
            InternetAddress ia = new InternetAddress(mail);
            ia.validate();
        }catch (AddressException e){
            ok = false;
        }
        return ok;
    }

}
