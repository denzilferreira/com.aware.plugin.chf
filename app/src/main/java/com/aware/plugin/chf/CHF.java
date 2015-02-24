package com.aware.plugin.chf;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.aware.Aware;
import com.aware.Aware_Preferences;

import java.util.Calendar;


public class CHF extends ActionBarActivity {

    private static SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences(getPackageName(), MODE_PRIVATE);
    }

    private void loadSchedule() {
        setContentView(R.layout.settings_chf);

        Toolbar tb = (Toolbar) findViewById(R.id.aware_toolbar);
        tb.setTitle(getTitle());
        setSupportActionBar(tb);

        ImageButton saveSchedule = (ImageButton) findViewById(R.id.save_button);

        final TimePicker evening_timer = (TimePicker) findViewById(R.id.evening_start_time);
        evening_timer.setIs24HourView(true);
        if( prefs.contains("evening_hours") ) {
            evening_timer.setCurrentHour(prefs.getInt("evening_hours",0));
        }
        if( prefs.contains("evening_minutes")) {
            evening_timer.setCurrentMinute(prefs.getInt("evening_minutes",0));
        }
        saveSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("evening_hours", evening_timer.getCurrentHour());
                editor.putInt("evening_minutes", evening_timer.getCurrentMinute());

                editor.putBoolean("scheduled", true);
                editor.commit();

                Toast.makeText(getApplicationContext(), "Saving schedule...", Toast.LENGTH_SHORT).show();

                Intent plugin = new Intent(getApplicationContext(), Plugin.class);
                plugin.setAction(Plugin.ACTION_JOIN_STUDY);
                startService(plugin);

                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if( ! prefs.contains("scheduled") ) {
            loadSchedule();
            return;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());

        setContentView(R.layout.activity_chf);

        Toolbar tb = (Toolbar) findViewById(R.id.aware_toolbar);
        tb.setTitle(getTitle());
        setSupportActionBar(tb);

        final TextView q1_rating = (TextView) findViewById(R.id.q1_rating);
        SeekBar q1 = (SeekBar) findViewById(R.id.rate_q1);
        q1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                q1_rating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView q2_rating = (TextView) findViewById(R.id.q2_rating);
        SeekBar q2 = (SeekBar) findViewById(R.id.rate_q2);
        q2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                q2_rating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView q3_rating = (TextView) findViewById(R.id.q3_rating);
        SeekBar q3 = (SeekBar) findViewById(R.id.rate_q3);
        q3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                q3_rating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView q4_rating = (TextView) findViewById(R.id.q4_rating);
        SeekBar q4 = (SeekBar) findViewById(R.id.rate_q4);
        q4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                q4_rating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView q5_rating = (TextView) findViewById(R.id.q5_rating);
        SeekBar q5 = (SeekBar) findViewById(R.id.rate_q5);
        q5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                q5_rating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView q6_rating = (TextView) findViewById(R.id.q6_rating);
        SeekBar q6 = (SeekBar) findViewById(R.id.rate_q6);
        q6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                q6_rating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView q7_rating = (TextView) findViewById(R.id.q7_rating);
        SeekBar q7 = (SeekBar) findViewById(R.id.rate_q7);
        q7.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                q7_rating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView q8_rating = (TextView) findViewById(R.id.q8_rating);
        SeekBar q8 = (SeekBar) findViewById(R.id.rate_q8);
        q8.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                q8_rating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView q9_rating = (TextView) findViewById(R.id.q9_rating);
        SeekBar q9 = (SeekBar) findViewById(R.id.rate_q9);
        q9.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                q9_rating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView q10_rating = (TextView) findViewById(R.id.q10_rating);
        SeekBar q10 = (SeekBar) findViewById(R.id.rate_q10);
        q10.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                q10_rating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView q11_rating = (TextView) findViewById(R.id.q11_rating);
        SeekBar q11 = (SeekBar) findViewById(R.id.rate_q11);
        q11.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                q11_rating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView q12_rating = (TextView) findViewById(R.id.q12_rating);
        SeekBar q12 = (SeekBar) findViewById(R.id.rate_q12);
        q12.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                q12_rating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView q13_rating = (TextView) findViewById(R.id.q13_rating);
        SeekBar q13 = (SeekBar) findViewById(R.id.rate_q13);
        q13.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                q13_rating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView q14_rating = (TextView) findViewById(R.id.q14_rating);
        SeekBar q14 = (SeekBar) findViewById(R.id.rate_q14);
        q14.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                q14_rating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView q15_rating = (TextView) findViewById(R.id.q15_rating);
        SeekBar q15 = (SeekBar) findViewById(R.id.rate_q15);
        q15.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                q15_rating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView q16_rating = (TextView) findViewById(R.id.q16_rating);
        SeekBar q16 = (SeekBar) findViewById(R.id.rate_q16);
        q16.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                q16_rating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView q17_rating = (TextView) findViewById(R.id.q17_rating);
        SeekBar q17 = (SeekBar) findViewById(R.id.rate_q17);
        q17.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                q17_rating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView q18_rating = (TextView) findViewById(R.id.q18_rating);
        SeekBar q18 = (SeekBar) findViewById(R.id.rate_q18);
        q18.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                q18_rating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView q19_rating = (TextView) findViewById(R.id.q19_rating);
        SeekBar q19 = (SeekBar) findViewById(R.id.rate_q19);
        q19.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                q19_rating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView q20_rating = (TextView) findViewById(R.id.q20_rating);
        SeekBar q20 = (SeekBar) findViewById(R.id.rate_q20);
        q20.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                q20_rating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final TextView q21_rating = (TextView) findViewById(R.id.q21_rating);
        SeekBar q21 = (SeekBar) findViewById(R.id.rate_q21);
        q21.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                q21_rating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final ImageButton answer_questions = (ImageButton) findViewById(R.id.answer_questionnaire);
        answer_questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues answer = new ContentValues();
                answer.put(Provider.CHF_Data.DEVICE_ID, Aware.getSetting(getApplicationContext(), Aware_Preferences.DEVICE_ID));
                answer.put(Provider.CHF_Data.TIMESTAMP, System.currentTimeMillis());
                answer.put(Provider.CHF_Data.SCORE_SWELLING, q1_rating.getText().toString());
                answer.put(Provider.CHF_Data.SCORE_SIT_LIE, q2_rating.getText().toString());
                answer.put(Provider.CHF_Data.SCORE_WALKING_CLIMBING, q3_rating.getText().toString());
                answer.put(Provider.CHF_Data.SCORE_WORKING_HOUSE_YARD, q4_rating.getText().toString());
                answer.put(Provider.CHF_Data.SCORE_GOING_PLACES_AWAY, q5_rating.getText().toString());
                answer.put(Provider.CHF_Data.SCORE_SLEEPING_WELL, q6_rating.getText().toString());
                answer.put(Provider.CHF_Data.SCORE_RELATING_FRIENDS_FAMILY, q7_rating.getText().toString());
                answer.put(Provider.CHF_Data.SCORE_WORKING_EARN_LIVING, q8_rating.getText().toString());
                answer.put(Provider.CHF_Data.SCORE_RECREATIONAL_HOBBIES, q9_rating.getText().toString());
                answer.put(Provider.CHF_Data.SCORE_SEXUAL, q10_rating.getText().toString());
                answer.put(Provider.CHF_Data.SCORE_EATING_LESS, q11_rating.getText().toString());
                answer.put(Provider.CHF_Data.SCORE_SHORT_BREATH, q12_rating.getText().toString());
                answer.put(Provider.CHF_Data.SCORE_TIRED_LOW_ENERGY, q13_rating.getText().toString());
                answer.put(Provider.CHF_Data.SCORE_STAY_HOSPITAL, q14_rating.getText().toString());
                answer.put(Provider.CHF_Data.SCORE_COST_MEDICAL_CARE, q15_rating.getText().toString());
                answer.put(Provider.CHF_Data.SCORE_SIDE_EFFECTS_MEDICATION, q16_rating.getText().toString());
                answer.put(Provider.CHF_Data.SCORE_BURDEN_FRIENDS_FAMILY, q17_rating.getText().toString());
                answer.put(Provider.CHF_Data.SCORE_LOSS_SELF_CONTROL, q18_rating.getText().toString());
                answer.put(Provider.CHF_Data.SCORE_WORRY, q19_rating.getText().toString());
                answer.put(Provider.CHF_Data.SCORE_DIFFICULT_CONCENTRATE, q20_rating.getText().toString());
                answer.put(Provider.CHF_Data.SCORE_DEPRESSED, q21_rating.getText().toString());

                getContentResolver().insert(Provider.CHF_Data.CONTENT_URI, answer);

                Log.d("UPMC", "Answers:" + answer.toString());

                Toast.makeText(getApplicationContext(), "Saved successfully.", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            loadSchedule();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
