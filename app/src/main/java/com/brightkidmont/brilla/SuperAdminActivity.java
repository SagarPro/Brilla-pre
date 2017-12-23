package com.brightkidmont.brilla;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.brightkidmont.brilla.adapter.PreschoolAdapter;
import com.brightkidmont.brilla.utils.Preschool;
import com.brightkidmont.brilla.utils.SendMail;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SuperAdminActivity extends AppCompatActivity {

    private static final int REQUEST_SEND_SMS = 1 ;
    private ListView lvPreschool;
    public static SuperAdminActivity superAdminActivity;
    private ProgressBar pbSuperAdmin;
    List<String> preschools = new ArrayList<>();
    private SharedPreferences loginPreferences;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    String preschoolName, preschoolPhone, preschoolLogin, preschoolPassword, preschoolLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.super_admin_layout);

        Typeface font = Typeface.createFromAsset(this.getAssets(), "adikku_panadai_menari.ttf");
        Toolbar toolbar = (Toolbar) findViewById(R.id.tbCustom);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView tvCustom = (TextView) findViewById(R.id.tvCustom);
        tvCustom.setText("Brilla Pre");
        tvCustom.setTypeface(font);

        pbSuperAdmin = (ProgressBar) findViewById(R.id.pbSuperAdmin);
        pbSuperAdmin.setVisibility(View.VISIBLE);

        //Logged in as SuperAdmin
        loginPreferences = getSharedPreferences("LOGINTYPE", MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPreferences.edit();
        editor.putString("LOGIN", "super");
        editor.putString("GUESTLOGIN", "not guest");
        editor.apply();

        superAdminActivity = this;

        lvPreschool = (ListView) findViewById(R.id.lvPreschool);
        lvPreschool.setDividerHeight(0);
        Button btnCreatePreschool = (Button) findViewById(R.id.btnCreatePreschool);

        //directing to "preschool" node to get preschool details
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference().child("preschool");

        //add all preschools name with location to list to display in listView
        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String psKey = snapshot.getKey();
                    preschools.add(psKey);
                }
                //setting "PreschoolAdapter" to display preschool list
                lvPreschool.setAdapter(new PreschoolAdapter(SuperAdminActivity.this, preschools));
                pbSuperAdmin.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                pbSuperAdmin.setVisibility(View.GONE);
            }
        });
        btnCreatePreschool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPreschoolDialog();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.super_admin_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.logout){
            pbSuperAdmin.setVisibility(View.VISIBLE);
            loginPreferences = getSharedPreferences("LOGINTYPE", MODE_PRIVATE);
            SharedPreferences.Editor editor = loginPreferences.edit();
            editor.putString("LOGIN", "logout");
            editor.apply();
            pbSuperAdmin.setVisibility(View.GONE);
            startActivity(new Intent(getBaseContext(), LoginActivity.class));
            finish();
        }
        if(id == R.id.guest){
            startActivity(new Intent(SuperAdminActivity.this, GuestUsersListActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //show dialog to create preschool
    public void showPreschoolDialog() {
        final Dialog dialog = new Dialog(SuperAdminActivity.this);
        dialog.setContentView(R.layout.ps_register_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        final ProgressBar pbCreatePS = (ProgressBar) dialog.findViewById(R.id.pbCreatePS);
        pbCreatePS.setVisibility(View.GONE);

        final EditText etPreschoolName = (EditText) dialog.findViewById(R.id.etPreschoolName);
        final EditText etPreschoolPhone = (EditText) dialog.findViewById(R.id.etPreschoolPhone);
        final EditText etPreschoolLogin = (EditText) dialog.findViewById(R.id.etPreschoolLogin);
        final EditText etPreschoolPassword = (EditText) dialog.findViewById(R.id.etPreschoolPassword);
        final EditText etPreschoolLocation = (EditText) dialog.findViewById(R.id.etPreschoolLocation);

        Button btnCreate = (Button) dialog.findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pbCreatePS.setVisibility(View.VISIBLE);
                String validPhone = "valid";
                pbSuperAdmin.setVisibility(View.VISIBLE);
                preschoolName = etPreschoolName.getText().toString();
                preschoolPhone = etPreschoolPhone.getText().toString();
                if(preschoolPhone.length()!=10){
                    validPhone = "not";
                    etPreschoolPhone.setError("Please Enter Valid Phone Number");
                }
                preschoolLogin = etPreschoolLogin.getText().toString();
                preschoolPassword = etPreschoolPassword.getText().toString();
                preschoolLocation = etPreschoolLocation.getText().toString();

                //checking for non-empty fields of preschool details
                if(TextUtils.isEmpty(preschoolName) || TextUtils.isEmpty(preschoolPhone) || (validPhone.equals("not")) || TextUtils.isEmpty(preschoolLogin) || TextUtils.isEmpty(preschoolPassword) || TextUtils.isEmpty(preschoolLocation)){
                    Toast.makeText(getBaseContext(),"Please Enter valid Details For Registering Preschool", Toast.LENGTH_SHORT).show();
                    pbSuperAdmin.setVisibility(View.GONE);
                    pbCreatePS.setVisibility(View.GONE);
                } else {
                    pbSuperAdmin.setVisibility(View.GONE);
                    pbCreatePS.setVisibility(View.GONE);
                    final String[] psValid = {"valid"};
                    //checks whether the preschool is already existed or not
                    mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Preschool preschool = snapshot.getValue(Preschool.class);
                                String psName = preschool.preschoolName;
                                String psLocation = preschool.preschoolLocation;
                                if (psName.equals(preschoolName)) {
                                    if (psLocation.equals(preschoolLocation)) {
                                        Toast.makeText(getBaseContext(), psName + " PreSchool At " + psLocation + " Already Exists", Toast.LENGTH_LONG).show();
                                        psValid[0] = "not";
                                        break;
                                    }
                                }
                            }
                            int permissionCheck = ContextCompat.checkSelfPermission(SuperAdminActivity.this, android.Manifest.permission.SEND_SMS);
                            if(permissionCheck == 0) {
                                //sending sms with login credentials of preschool to admin
                                if (psValid[0].equals("valid")) {
                                    sendSMS(preschoolPhone, "Greetings From Brilla App\nYour Admin Login Details Are:\nLogin:" + preschoolLogin + "\nPassword:" + preschoolPassword);
                                } else {
                                    Toast.makeText(SuperAdminActivity.this, "Invalid details, please try again", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                final Dialog dialog2 = new Dialog(SuperAdminActivity.this);
                                dialog2.setContentView(R.layout.permission_dialog);

                                TextView pCancel = (TextView) dialog2.findViewById(R.id.pCancel);
                                TextView pSettings = (TextView) dialog2.findViewById(R.id.pSettings);
                                TextView pOk = (TextView) dialog2.findViewById(R.id.pOk);
                                pCancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog2.dismiss();
                                    }
                                });
                                pSettings.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog2.dismiss();
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                                Uri.fromParts("package", getPackageName(), null));
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                });
                                pOk.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog2.dismiss();
                                        ActivityCompat.requestPermissions(SuperAdminActivity.this, new String[]{android.Manifest.permission.SEND_SMS}, REQUEST_SEND_SMS);
                                    }
                                });
                                dialog2.show();
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            pbSuperAdmin.setVisibility(View.GONE);
                            pbCreatePS.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });
        dialog.show();

        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbSuperAdmin.setVisibility(View.GONE);
                pbCreatePS.setVisibility(View.GONE);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_SEND_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    sendSMS(preschoolPhone, "Greetings From Brilla App\nYour Admin Login Details Are:\nLogin:" + preschoolLogin + "\nPassword:" + preschoolPassword);
                }
            }
        }
    }

    //sending sms to admin
    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_LONG).show();
            //create preschool with details into database
            Preschool preschool = new Preschool(preschoolName, preschoolPhone, preschoolLogin, preschoolPassword, preschoolLocation);
            DatabaseReference databaseReference = mFirebaseInstance.getReference("preschool").child(preschoolName + "-" + preschoolLocation);
            databaseReference.setValue(preschool);
            startActivity(new Intent(getBaseContext(), SuperAdminActivity.class));
            finish();
            //sending confirmation email to admin
            sendEmail();
            Toast.makeText(getBaseContext(), " Successfully Created A Pre School ", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "SMS sending failed", Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    //sending email to admin
    protected boolean sendEmail() {
        String email = preschoolLogin;
        String subject = "Registration Successful";
        String message = ""+preschoolName+" Pre School is Successfully Registered with BrightKidMontessori House";
        String pas = preschoolPassword;
        SendMail sm = new SendMail(SuperAdminActivity.this, email, subject, message, pas);
        sm.execute();
        return true;
    }

    public static SuperAdminActivity getInstance(){
        return   superAdminActivity;
    }

    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }
}
