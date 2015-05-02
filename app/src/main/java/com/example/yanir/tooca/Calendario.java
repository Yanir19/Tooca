package com.example.yanir.tooca;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Camera;
import android.os.Bundle;
import android.provider.CalendarContract;

import java.util.Calendar;

/**
 * Created by LjnozZ on 01/04/2015.
 */
public class Calendario extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.Calendario);
        addEventToCalendar(this);
    }

    private void addEventToCalendar(Activity activity) {
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.DAY_OF_MONTH, 30);
        cal.set(Calendar.MONTH, 2);
        cal.set(Calendar.YEAR, 2015);

        cal.set(Calendar.HOUR_OF_DAY, 13);
        cal.set(Calendar.MINUTE, 45);

        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");


        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, cal.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, cal.getTimeInMillis() + 60 * 60 * 1000);
        intent.putExtra(CalendarContract.Events.ALL_DAY, false);
        intent.putExtra(CalendarContract.Events.RRULE, "FREQ=MONTHLY");
        intent.putExtra(CalendarContract.Events.TITLE, "TOOCA: ¡Autoexamen hoy!");
        intent.putExtra(CalendarContract.Events.DESCRIPTION, "Te esperamos en TOOCA para cuidarte y darte algunos tips");
        //intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Calle ....");
        activity.startActivity(intent);

    }

}
