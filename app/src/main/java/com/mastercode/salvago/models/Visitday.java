package com.mastercode.salvago.models;

import com.mastercode.salvago.tools.Statictools;

public class Visitday implements Comparable<Visitday>{

    public String date;
    public long count;


    @Override
    public int compareTo(Visitday o) {
        long thisMilis = Statictools.SimpleDateToMilis(this.date);
        long objMilis = Statictools.SimpleDateToMilis(o.date);
        boolean c1 = thisMilis > objMilis;
        boolean c2 = thisMilis < objMilis;

        return c1 ? 1 : c2 ? -1 : 0;
    }
}
