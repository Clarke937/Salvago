package com.mastercode.salvago.tools;

import android.content.Context;
import android.content.Intent;

import com.mastercode.salvago.Access;
import com.mastercode.salvago.Dashboard;
import com.mastercode.salvago.Fanpage;
import com.mastercode.salvago.Giftcards;
import com.mastercode.salvago.Home;
import com.mastercode.salvago.Map;

public class AppNavigation {

    public static void goFanpage(Context ctx){
        ctx.startActivity(new Intent(ctx, Fanpage.class));
    }

    public static void goHome(Context ctx){
        ctx.startActivity(new Intent(ctx, Home.class));
    }

    public static void goAccess(Context ctx){
        ctx.startActivity(new Intent(ctx, Access.class));
    }

    public static void goDashboard(Context ctx){
        ctx.startActivity(new Intent(ctx, Dashboard.class));
    }

    public static void goGiftcards(Context ctx){
        ctx.startActivity(new Intent(ctx, Giftcards.class));
    }
    public static void goMap(Context ctx, double lat, double lon){
        Intent i = new Intent(ctx,Map.class);
        i.putExtra("lat",lat);
        i.putExtra("lon",lon);
        ctx.startActivity(i);
    }


}
