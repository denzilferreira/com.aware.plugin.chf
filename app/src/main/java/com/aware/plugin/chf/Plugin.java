package com.aware.plugin.chf;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.aware.Aware;
import com.aware.Aware_Preferences;
import com.aware.utils.Aware_Plugin;

import java.util.Calendar;

/**
 * Created by denzil on 19/02/15.
 */
public class Plugin extends Aware_Plugin {

    public static final String ACTION_JOIN_STUDY = "ACTION_JOIN_STUDY";

    private static SharedPreferences prefs;

    @Override
    public void onCreate() {
        super.onCreate();
        prefs = getSharedPreferences(getPackageName(), MODE_PRIVATE);

        TAG = "CHF";

        DATABASE_TABLES = Provider.DATABASE_TABLES;
        TABLES_FIELDS = Provider.TABLES_FIELDS;
        CONTEXT_URIS = new Uri[]{Provider.CHF_Data.CONTENT_URI};
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        DEBUG = Aware.getSetting(this, Aware_Preferences.DEBUG_FLAG).equals("true");

        if( intent != null && intent.getAction() != null && intent.getAction().equals(ACTION_JOIN_STUDY) ) {
            if (Aware.getSetting(getApplicationContext(), "study_id").length() == 0) {
                Intent join_study = new Intent(getApplicationContext(), Aware_Preferences.StudyConfig.class);
                join_study.putExtra("study_url", "https://api.awareframework.com/index.php/webservice/index/253/Gv9VCxnaJVXO");
                startService(join_study);
            }
        }

        if( prefs.getBoolean("scheduled", false) ) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(System.currentTimeMillis());
            if (cal.get(Calendar.HOUR_OF_DAY) > prefs.getInt("evening_hours", 0) || cal.get(Calendar.MINUTE) > prefs.getInt("evening_minutes", 0)) {
                //lets set the calendar for the following day, repeating every day after that
                cal.add(Calendar.DAY_OF_YEAR, 1); //set it to tomorrow
                cal.set(Calendar.HOUR_OF_DAY, prefs.getInt("evening_hours", 0));
                cal.set(Calendar.MINUTE, prefs.getInt("evening_minutes", 0));
                cal.set(Calendar.SECOND, 0);
            } else {
                cal.set(Calendar.HOUR_OF_DAY, prefs.getInt("evening_hours", 0));
                cal.set(Calendar.MINUTE, prefs.getInt("evening_minutes", 0));
                cal.set(Calendar.SECOND, 0);
            }

            Intent survey = new Intent(getApplicationContext(), Survey.class);
            PendingIntent surveyTrigger = PendingIntent.getService(getApplicationContext(), 0, survey, PendingIntent.FLAG_UPDATE_CURRENT);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis()+10000, AlarmManager.INTERVAL_DAY, surveyTrigger); //shift the schedule by 10 seconds to make sure it gets triggered

            Log.d(TAG, "Questions are scheduled for: " + cal.getTime().toString());
        }

        return super.onStartCommand(intent, flags, startId);
    }

    public static class Survey extends IntentService {
        public Survey() {
            super("Survey service");
        }

        @Override
        protected void onHandleIntent(Intent intent) {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
            mBuilder.setSmallIcon(R.drawable.ic_stat_survey);
            mBuilder.setContentTitle("CHF");
            mBuilder.setContentText("Questionnaire available. Answer?");
            mBuilder.setDefaults(Notification.DEFAULT_ALL);
            mBuilder.setAutoCancel(true);

            Intent survey = new Intent(this, CHF.class);
            PendingIntent onclick = PendingIntent.getActivity(this, 0, survey, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(onclick);

            NotificationManager notManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notManager.notify(43, mBuilder.build());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
