package com.kanefron5.candyupdater;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    private ImageButton mCrowsCounterButton;
    private int mCount = 0;
    private int x;
    




    public void onBackPressed(){
        new AlertDialog.Builder(this)
                .setMessage("Выйти? Данные сохранятся!")
                .setCancelable(false)
                .setPositiveButton("Ога" , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        finish();

                    }
                }).setNegativeButton("Нит", null).show();



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCrowsCounterButton = (ImageButton) findViewById(R.id.buttonCrowsCounter);
        mCrowsCounterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TextView mInfoTextView = (TextView) findViewById(R.id.textView);
                mInfoTextView.setText("Вы нажали " + ++ mCount + " раз");

        onClick:
                x++;



                if(x>3){
                    x=0;
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Не надоело?", Toast.LENGTH_SHORT); toast.show();
                }



                if(x>1000){
                    x=0;
                   Toast toast = Toast.makeText(getApplicationContext(),
                           "Не надоело?", Toast.LENGTH_SHORT); toast.show();
                }


                if(x>1000000){
                    x=0;

                    Intent intent = new Intent(MainActivity.this, Act.class);
                    startActivity(intent);
                }

            }
        });
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }


    @Override
    protected void onResume(){
        super.onResume();

        final TextView mInfoTextView = (TextView) findViewById(R.id.textView);

        if(mSettings.contains(APP_PREFERENCES_COUNTER)){
            //число из сэйва
            mCount = mSettings.getInt(APP_PREFERENCES_COUNTER, 0);
            //выводим данные
            mInfoTextView.setText("Вы нажали " + ++ mCount + " раз");

        }

    }

    @Override
    protected void onPause(){
        super.onPause();
        //запоминаем данные
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(APP_PREFERENCES_COUNTER, mCount);
        editor.apply();
    }





    public void onClick(View view){
        TextView helloTextView = (TextView)findViewById(R.id.textView);
        helloTextView.setText(" ");

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        menu.findItem(R.id.action_settings);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        // Здесь кароч заглушка
        switch(item.getItemId())
        {
            case R.id.action_settings:



                Intent i = new Intent(this, Prefs.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_COUNTER = "count";
    private SharedPreferences mSettings;



}
