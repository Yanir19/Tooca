package com.example.yanir.tooca;

import java.util.Calendar;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.TimePicker;
import android.widget.Toast;
/**
 * Created by Jesus on 4/18/2015.
 */
public class Establecer_alarma  extends ActionBarActivity {
    // This is a handle so that we can call methods on our service
    private ScheduleClient scheduleClient;
    // This is the date picker used to select the date for our notification
    private DatePicker picker;
    private TimePicker timepicker;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.establecer_alarma);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Se verifica la version de la API que dispone el usuario
        if (Build.VERSION.SDK_INT < 19) {
            FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
            ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
            layoutParams.height = 0;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }


        // Create a new service client and bind our activity to this service
        scheduleClient = new ScheduleClient(this);
        scheduleClient.doBindService();

        // Get a reference to our date picker
        picker = (DatePicker) findViewById(R.id.scheduleTimePicker);
        timepicker = (TimePicker) findViewById(R.id.timePicker);
    }

    /**
     * This is the onClick called from the XML to set a new notification
     */
    public void onDateSelectedButtonClick(View v){
        // Get the date from our datepicker
        int day = picker.getDayOfMonth();
        int month = picker.getMonth();
        int year = picker.getYear();
        int hour = timepicker.getCurrentHour();
        int min = timepicker.getCurrentMinute();
        // Create a new calendar set to the date chosen
        // we set the time to midnight (i.e. the first minute of that day)
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);

        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, min);
        c.set(Calendar.SECOND, 0);
        // Ask our service to set an alarm for that date, this activity talks to the client that talks to the service
        scheduleClient.setAlarmForNotification(c);

        // Notify the user what they just did
        Toast.makeText(this, "Notification set for: "+ day +"/"+ (month+1) +"/"+ year, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        // When our activity is stopped ensure we also stop the connection to the service
        // this stops us leaking our activity into the system *bad*
        if(scheduleClient != null)
            scheduleClient.doUnbindService();
        super.onStop();
    }
}
