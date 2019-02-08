package com.mastercode.salvago.tools;

import java.util.Calendar;
import java.util.Date;

public class Statictools {

    public static String getSimpleDate(){
        String fecha = "";
        Calendar cal = Calendar.getInstance();
        fecha += cal.get(Calendar.DAY_OF_MONTH) + "-";
        fecha += (cal.get(Calendar.MONTH) + 1) + "-";
        fecha += cal.get(Calendar.YEAR);
        return fecha;
    }


}
