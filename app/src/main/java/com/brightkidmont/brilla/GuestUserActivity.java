package com.brightkidmont.brilla;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.brightkidmont.brilla.utils.GuestUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuestUserActivity extends AppCompatActivity {

    private static final int REQUEST_SEND_SMS = 1 ;
    private EditText etGuestName, etGuestPhone, etGuestEmail, etGuestLocation;
    private String guestName, guestPhone, guestEmail, guestLocation, guestPassword;
    private CheckBox cbTC;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private ProgressBar guestProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_user_layout);

        //directing to guest node in database
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("guest");

        guestProgress = (ProgressBar) findViewById(R.id.guestProgress);
        guestProgress.setVisibility(View.GONE);

        etGuestName = (EditText) findViewById(R.id.etGuestName);
        etGuestPhone = (EditText) findViewById(R.id.etGuestPhone);
        etGuestEmail = (EditText) findViewById(R.id.etGuestEmail);
        etGuestLocation = (EditText) findViewById(R.id.etGuestLocation);

        cbTC = (CheckBox) findViewById(R.id.cbTC);

        final Dialog dialog = new Dialog(GuestUserActivity.this);
        dialog.setContentView(R.layout.terms_conditions);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        TextView TC = (TextView) dialog.findViewById(R.id.TC);
        TC.setText(R.string.Terms_and_conditions);

        //display T&C dialog
        TextView tvTC = (TextView) findViewById(R.id.tvTC);
        tvTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        Button btnAgree = (Button) dialog.findViewById(R.id.btnAgree);
        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbTC.setChecked(true);
                dialog.cancel();
            }
        });

        Button btnGuestRegister = (Button) findViewById(R.id.btnGuestRegister);
        btnGuestRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guestProgress.setVisibility(View.VISIBLE);
                validate();
            }
        });

        TextView tvLogin = (TextView) findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    //validating email id with matching pattern
    public Boolean emailValidation(){
        guestEmail = etGuestEmail.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        Pattern patternEmail = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcherEmail = patternEmail.matcher(guestEmail);
        if (!matcherEmail.find()) {
            return true;
        }
        return false;
    }

    //validating guest user details
    public void validate(){
        guestName = etGuestName.getText().toString();
        guestPhone = etGuestPhone.getText().toString();
        guestEmail = etGuestEmail.getText().toString();
        guestLocation = etGuestLocation.getText().toString();

        //checking for non-empty fields
        if(TextUtils.isEmpty(guestName)){
            etGuestName.setError("Please Enter Your Name");
            Toast.makeText(getBaseContext(), "Please Enter Your Name", Toast.LENGTH_SHORT).show();
            guestProgress.setVisibility(View.GONE);
        } else if(TextUtils.isEmpty(guestPhone) || (guestPhone.length()!= 10)){
            etGuestPhone.setError("Please Enter Your Valid Mobile Number");
            Toast.makeText(getBaseContext(), "Please Enter Your Valid Mobile Number", Toast.LENGTH_SHORT).show();
            guestProgress.setVisibility(View.GONE);
        } else if(TextUtils.isEmpty(guestEmail) || emailValidation()){
            etGuestEmail.setError("Please Enter Your Valid Email Id");
            Toast.makeText(getBaseContext(), "Please Enter Your Valid Email Id", Toast.LENGTH_SHORT).show();
            guestProgress.setVisibility(View.GONE);
        } else if(TextUtils.isEmpty(guestLocation)){
            etGuestLocation.setError("Please Enter Your Location");
            Toast.makeText(getBaseContext(), "Please Enter Your Location", Toast.LENGTH_SHORT).show();
            guestProgress.setVisibility(View.GONE);
        } else if(!cbTC.isChecked()) {
            Toast.makeText(getBaseContext(), "Please Accept Terms and Conditions Before Registering", Toast.LENGTH_SHORT).show();
            guestProgress.setVisibility(View.GONE);
        } else {
            FirebaseDatabase.getInstance().getReference().child("guest").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //checks if guest is already registered
                    if(dataSnapshot.hasChild(guestPhone)){
                        Toast.makeText(GuestUserActivity.this, "User with these details is already registered", Toast.LENGTH_SHORT).show();
                        guestProgress.setVisibility(View.GONE);
                    } else {
                        guestProgress.setVisibility(View.GONE);

                        //permission check for sms
                        int permissionCheck = ContextCompat.checkSelfPermission(GuestUserActivity.this, android.Manifest.permission.SEND_SMS);
                        if(permissionCheck == 0) {
                            guestPassword = generateRandomPassword();
                            String message = "Your login details for brilla as a guest user is:\nLogin : "+guestEmail+"\nPassword : "+guestPassword;
                            sendSMS(guestPhone, message);
                        } else {
                            final Dialog dialog = new Dialog(GuestUserActivity.this);
                            dialog.setContentView(R.layout.permission_dialog);

                            TextView pCancel = (TextView) dialog.findViewById(R.id.pCancel);
                            TextView pSettings = (TextView) dialog.findViewById(R.id.pSettings);
                            TextView pOk = (TextView) dialog.findViewById(R.id.pOk);
                            pCancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                            pSettings.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                            Uri.fromParts("package", getPackageName(), null));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            });
                            pOk.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    ActivityCompat.requestPermissions(GuestUserActivity.this, new String[]{android.Manifest.permission.SEND_SMS}, REQUEST_SEND_SMS);
                                }
                            });
                            dialog.show();
                        }

                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d("GUEST", "Failed to create guest user");
                }
            });
        }
    }

    //method to generate random string for password
    public static String generateRandomPassword(){
        String password;
        int passwordSize = 7;
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < passwordSize; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        password = sb.toString();
        return password;
    }

    //sending login credentials to guest by sms
    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            createGuestUser(guestName, guestPhone, guestEmail, guestLocation);
            Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    //creating guest user with details
    private void createGuestUser(String guestName, String guestPhone, String guestEmail, String guestLocation) {
        GuestUser guestUser = new GuestUser(guestName, guestPhone, guestEmail, guestPassword, guestLocation);
        mFirebaseDatabase.child(guestPhone).setValue(guestUser);
        guestProgress.setVisibility(View.GONE);
        //storing registered date for guest user, which is used to check for guest validation
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy HH:mm:ss");
        String date = simpleDateFormat.format(Calendar.getInstance().getTime());
        mFirebaseInstance.getReference("guestExpiration").child(guestPhone).setValue(date);
        startActivity(new Intent(getBaseContext(), LoginActivity.class));
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_SEND_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    guestPassword = generateRandomPassword();
                    String message = "Your login details for brilla as a guest user is:\nLogin : "+guestEmail+"\nPassword : "+guestPassword;
                    sendSMS(guestPhone, message);
                }
            }
        }
    }

}
