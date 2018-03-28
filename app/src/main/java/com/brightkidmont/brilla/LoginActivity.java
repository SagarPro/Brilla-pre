package com.brightkidmont.brilla;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.brightkidmont.brilla.utils.GuestUser;
import com.brightkidmont.brilla.utils.Preschool;
import com.brightkidmont.brilla.utils.SendMail;
import com.brightkidmont.brilla.utils.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail, etPassword;
    private ProgressBar loginProgress;
    private LinearLayout loginLayout;
    private SharedPreferences adminPreferences, loginPreferences;
    AlertDialog.Builder builder;
    String fp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        ImageView ivGifImage = (ImageView) findViewById(R.id.ivGifImage);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        TextView tvAdminLogin = (TextView) findViewById(R.id.tvAdminLogin);
        TextView tvEnrollNow = (TextView) findViewById(R.id.tvEnroll);
        TextView tvGuestUser = (TextView) findViewById(R.id.tvGuestUser);
        TextView tvForgotPassword = (TextView) findViewById(R.id.tvForgotPassword);
        loginLayout = (LinearLayout) findViewById(R.id.loginLayout);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);

        //assigning different types of alert dialog for different versions of android
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(LoginActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(LoginActivity.this);
        }

        loginProgress = (ProgressBar) findViewById(R.id.loginProgress);
        //setting color to progress bar
        loginProgress.getIndeterminateDrawable().setColorFilter(0xFFcc0000, android.graphics.PorterDuff.Mode.MULTIPLY);
        loginProgress.setVisibility(View.GONE);
        loginLayout.setVisibility(View.GONE);

        btnLogin.setOnClickListener(this);
        tvAdminLogin.setOnClickListener(this);
        tvEnrollNow.setOnClickListener(this);
        tvGuestUser.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);

        //rotate animation for imageView in login activity
        RotateAnimation rotate = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setDuration(15000);
        rotate.setRepeatCount(Animation.INFINITE);
        ivGifImage.startAnimation(rotate);

    }

    //shows admin dialog to enter login credentials as admin user
    public void showAdminDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.admin_login_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        final String[] adminLoginValid = {"not"};
        final EditText etAdminLogin = (EditText) dialog.findViewById(R.id.etAdminLogin);
        final EditText etAdminPassword = (EditText) dialog.findViewById(R.id.etAdminPassword);
        final ProgressBar pbAdminLogin = (ProgressBar) dialog.findViewById(R.id.pbAdminLogin);
        pbAdminLogin.setVisibility(View.INVISIBLE);

        Button btnACancel = (Button) dialog.findViewById(R.id.btnACancel);
        btnACancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginLayout.setVisibility(View.INVISIBLE);
                dialog.dismiss();
            }
        });

        Button btnALogin = (Button) dialog.findViewById(R.id.btnALogin);
        btnALogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbAdminLogin.setVisibility(View.VISIBLE);
                final String adminLogin = etAdminLogin.getText().toString();
                final String adminPassword = etAdminPassword.getText().toString();

                //checks for non empty fields in admin's email and password field
                if (TextUtils.isEmpty(adminLogin) || TextUtils.isEmpty(adminPassword)) {
                    Toast.makeText(getBaseContext(), "Please Login With Registered Admin Details", Toast.LENGTH_SHORT).show();
                    pbAdminLogin.setVisibility(View.INVISIBLE);
                } else {
                    //checking for valid and matching email id and password in database under node "preschool"
                    FirebaseDatabase.getInstance().getReference().child("preschool")
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        Preschool preschool = snapshot.getValue(Preschool.class);
                                        String login = preschool.preschoolLogin;
                                        String password = preschool.preschoolPassword;
                                        if (adminLogin.equals(login)) {
                                            if (adminPassword.equals(password)) {
                                                adminLoginValid[0] = "valid";
                                                adminPreferences = getSharedPreferences("ADMIN", MODE_PRIVATE);
                                                SharedPreferences.Editor editor = adminPreferences.edit();
                                                editor.putString("PRESCHOOL", preschool.preschoolName);
                                                editor.apply();
                                                pbAdminLogin.setVisibility(View.GONE);
                                                startActivity(new Intent(getBaseContext(), AdminActivity.class));
                                                finish();
                                                break;
                                            }
                                        }
                                    }
                                    if (adminLoginValid[0].equals("not")) {
                                        pbAdminLogin.setVisibility(View.INVISIBLE);
                                        Toast.makeText(getBaseContext(), "Please Login With Registered Admin Details", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    pbAdminLogin.setVisibility(View.INVISIBLE);
                                }
                            });
                }
            }
        });
        dialog.show();
    }

    //Sending password to guest user
    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Failed to send sms, please try again", Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
        }
    }

    //sending email to recover password
    protected boolean sendEmail() {
        String email = etEmail.getText().toString();
        String subject = "Password Recovery";
        String message = "Your Login Details Are:";
        String pas = fp;
        SendMail sm = new SendMail(LoginActivity.this, email, subject, message, pas);
        sm.execute();
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnLogin:
                loginProgress.setVisibility(View.VISIBLE);
                loginLayout.setVisibility(View.VISIBLE);

                final String email, password;
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                final String[] login = {"failed"};

                //checking non empty fields for email and password
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(getBaseContext(),"Please Enter Your Login Details", Toast.LENGTH_SHORT).show();
                    loginProgress.setVisibility(View.GONE);
                    loginLayout.setVisibility(View.GONE);
                } else if(email.equals("email") && password.equals("password")) { //if email and password belongs to superAdmin
                    loginProgress.setVisibility(View.GONE);
                    loginLayout.setVisibility(View.GONE);
                    startActivity(new Intent(LoginActivity.this, SuperAdminActivity.class));
                    finish();
                } else {

                    //if the entered login credentials are not superAdmin's details, check for users details
                    FirebaseDatabase.getInstance().getReference().child("users")
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        User user = snapshot.getValue(User.class);
                                        String e = user.email;
                                        String p = user.password;
                                        String a = user.acceptance;
                                        if(email.equals(e)){
                                            if(password.equals(p)){
                                                loginPreferences = getSharedPreferences("LOGINTYPE", MODE_PRIVATE);
                                                SharedPreferences.Editor editor = loginPreferences.edit();
                                                editor.putString("GUESTLOGIN", "not guest");
                                                editor.putString("USEREMAIL",e);
                                                editor.apply();
                                                //checks if the enrolled user is in wait state or approved state
                                                if(a.equals("waiting")){
                                                    login[0] = "success";
                                                    Toast.makeText(getBaseContext(), "Your Account Is Still In Progress For Approval.\nWait For 24 hrs after Enrolment.", Toast.LENGTH_LONG).show();
                                                    loginProgress.setVisibility(View.GONE);
                                                    loginLayout.setVisibility(View.GONE);
                                                }else {
                                                    login[0] = "success";
                                                    loginProgress.setVisibility(View.GONE);
                                                    loginLayout.setVisibility(View.GONE);
                                                    startActivity(new Intent(getBaseContext(), HomePageActivity.class));
                                                    finish();
                                                }
                                                break;
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Log.d("Login", "Login Cancelled");
                                }
                            });

                    //If the login credentials are belongs to guest user
                    FirebaseDatabase.getInstance().getReference().child("guest")
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        GuestUser guestUser = snapshot.getValue(GuestUser.class);
                                        String e = guestUser.guestEmail;
                                        String p = guestUser.guestPassword;
                                        if(email.equals(e)){
                                            if(password.equals(p)) {
                                                loginPreferences = getSharedPreferences("LOGINTYPE", MODE_PRIVATE);
                                                SharedPreferences.Editor editor = loginPreferences.edit();
                                                editor.putString("GUESTLOGIN", "guest");
                                                editor.apply();
                                                startActivity(new Intent(LoginActivity.this, HomePageActivity.class));
                                                finish();
                                                loginProgress.setVisibility(View.GONE);
                                                loginLayout.setVisibility(View.GONE);
                                                login[0] = "success";
                                                break;
                                            } else if(p.equals("GuestUserExpired")){
                                                Toast.makeText(getBaseContext(), "Your Account Has Been Expired", Toast.LENGTH_LONG).show();
                                                login[0] = "success";
                                                loginProgress.setVisibility(View.GONE);
                                                loginLayout.setVisibility(View.GONE);
                                                break;
                                            }
                                        }
                                    }
                                    if(login[0].equals("failed")){
                                        Toast.makeText(getBaseContext(), "Please Enter Registered Email Id And Password", Toast.LENGTH_SHORT).show();
                                        loginProgress.setVisibility(View.GONE);
                                        loginLayout.setVisibility(View.GONE);
                                    }
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Log.d("Login", "Login Cancelled");
                                }
                            });
                }
                break;

            case R.id.tvForgotPassword:

                //Password recovery will be sent if user has entered his registered email id
                if (TextUtils.isEmpty(etEmail.getText().toString())) {
                    Toast.makeText(getBaseContext(),"Please Enter Your Registered Email Id", Toast.LENGTH_SHORT).show();
                } else {

                    loginProgress.setVisibility(View.VISIBLE);
                    loginLayout.setVisibility(View.VISIBLE);

                    final String email_fp;
                    email_fp = etEmail.getText().toString();
                    final String[] login_fp = {"failed"};

                    //checks database for respective user to send the password through email only
                    FirebaseDatabase.getInstance().getReference().child("users")
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        User user = snapshot.getValue(User.class);
                                        String e = user.email;
                                        String a = user.acceptance;
                                        if(email_fp.equals(e)){
                                            //if user is in waiting progress, password can't be sent and display below message else send email with password
                                            if(a.equals("waiting")){
                                                login_fp[0] = "success";
                                                Toast.makeText(getBaseContext(), "Your Account Is Still In Progress For Approval.\nWait For 24 hrs after Enrolment.", Toast.LENGTH_LONG).show();
                                                loginProgress.setVisibility(View.GONE);
                                                loginLayout.setVisibility(View.GONE);
                                            } else {
                                                login_fp[0] = "success";
                                                fp = user.password;
                                                if(!sendEmail()){
                                                    Toast.makeText(LoginActivity.this, "Please Check With Your Internet Connection And Try Again", Toast.LENGTH_SHORT).show();
                                                    loginProgress.setVisibility(View.GONE);
                                                    loginLayout.setVisibility(View.GONE);
                                                } else {
                                                    loginProgress.setVisibility(View.GONE);
                                                    loginLayout.setVisibility(View.GONE);
                                                }
                                            }
                                        }
                                    }
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Log.d("Login", "Login Cancelled");
                                }
                            });

                    if(login_fp[0].equals("failed")) {

                        //checks guest user details to send password as SMS to registered number
                        FirebaseDatabase.getInstance().getReference().child("guest").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    if (String.valueOf(snapshot.child("guestEmail").getValue()).equals(email_fp)) {
                                        String message = "Your password for Brill as a guest user is : " + String.valueOf(snapshot.child("guestPassword").getValue());
                                        sendSMS(snapshot.getKey(), message);
                                        login_fp[0] = "success";
                                    }
                                }
                                if (login_fp[0].equals("failed")) {
                                    Toast.makeText(getBaseContext(), "Please Enter Registered Email id", Toast.LENGTH_SHORT).show();
                                    loginProgress.setVisibility(View.GONE);
                                    loginLayout.setVisibility(View.GONE);
                                }
                                loginProgress.setVisibility(View.GONE);
                                loginLayout.setVisibility(View.GONE);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.d("Login", "Login Cancelled");
                            }
                        });
                    }

                }
                break;

            case R.id.tvAdminLogin:
                showAdminDialog();
                break;

            case R.id.tvEnroll:
                //show EnrollActivity for creating new users
                startActivity(new Intent(getBaseContext(), EnrollActivity.class));
                break;

            case R.id.tvGuestUser:
                //show GuestUserActivity for creating guest user
                startActivity(new Intent(getBaseContext(), GuestUserActivity.class));
                break;

        }

    }

}
