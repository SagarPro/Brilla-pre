package com.brightkidmont.brilla;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.brightkidmont.brilla.utils.GuestExpiration;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SplashScreenActivity extends AppCompatActivity {

    private boolean first = true;

    SharedPreferences loginPreferences;
    String login = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_layout);

        //Preferences to login status
        loginPreferences = getSharedPreferences("LOGINTYPE", MODE_PRIVATE);
        login = loginPreferences.getString("LOGIN", "logout");

        //Checking for internet connection and displaying respective messages
        if (isOnline()){
            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Not Connected", Toast.LENGTH_SHORT).show();
        }

        //getting guest expiration values from database to update the expired guest user details
        FirebaseDatabase.getInstance().getReference().child("guestExpiration").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy HH:mm:ss");
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String registeredDateTime = String.valueOf(snapshot.getValue());
                    String currentDateTime = simpleDateFormat.format(Calendar.getInstance().getTime());

                    try {
                        Date registeredDate = simpleDateFormat.parse(registeredDateTime);
                        Date currentDate = simpleDateFormat.parse(currentDateTime);

                        GuestExpiration guestExpiration = new GuestExpiration();
                        int dateDifference = (int) guestExpiration.printDifference(registeredDate, currentDate);

                        //if guest registered before 1 week, update respective guest user details in database
                        if(dateDifference >= 7){
                            FirebaseDatabase.getInstance().getReference().child("guest").child(snapshot.getKey()).child("guestPassword").setValue("GuestUserExpired");
                            FirebaseDatabase.getInstance().getReference().child("guestExpiration").child(snapshot.getKey()).removeValue();
                            if(loginPreferences.getString("GUESTLOGIN", "not guest").equals("guest")){
                                login = "logout";
                            }
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }


                if(first){
                    first = false;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loginType();
                        }
                    }, 1000);
                } else {
                    loginType();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(SplashScreenActivity.this, "Failed to get data from database.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //method to see which type of user is already logged in using sharedpreferences and start respective activity
    public void loginType(){
        if(login.equals("login")){
            startActivity(new Intent(getBaseContext(), HomePageActivity.class));
        } else if(login.equals("admin")){
            startActivity(new Intent(getBaseContext(), AdminActivity.class));
        } else if(login.equals("super")){
            startActivity(new Intent(getBaseContext(), SuperAdminActivity.class));
        } else {
            //if the user has logged out or new user
            startActivity(new Intent(getBaseContext(), LoginActivity.class));
        }
        finish();
    }

    //checking the connectivity with network
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(LoginActivity.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    //checking connectivity with internet
    public Boolean isOnline() {
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = p1.waitFor();
            return (returnVal==0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
