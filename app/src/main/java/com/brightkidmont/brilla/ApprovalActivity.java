package com.brightkidmont.brilla;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.brightkidmont.brilla.utils.Preschool;
import com.brightkidmont.brilla.utils.SendMail;
import com.brightkidmont.brilla.utils.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.brightkidmont.brilla.AdminActivity.adminProgress;
import static com.brightkidmont.brilla.adapter.UsersAdapter.userKey;

public class ApprovalActivity extends AppCompatActivity {

    private static final int REQUEST_SEND_SMS = 1 ;
    private TextView tvFName, tvMName, tvAEmail, tvAPhone, tvCName, tvCDOB, tvPSchool, tvALevel, tvALocation, tvAPassword;
    private EditText etFName, etMName, etAEmail, etAPhone, etCName, etCDOB, etPSchool, etALevel, etALocation;
    private Button btnDelete, btnSave, btnEdit;
    private TextView tvNewUsers, tvEnrolledUsers, tvUpdateUser;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private ProgressBar approvalProgress;
    String password, phone, login, aPassword;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.approval_layout);

        Typeface font = Typeface.createFromAsset(this.getAssets(), "adikku_panadai_menari.ttf");
        Toolbar toolbar = (Toolbar) findViewById(R.id.tbCustom);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView tvCustom = (TextView) findViewById(R.id.tvCustom);
        tvCustom.setText("Brilla Pre");
        tvCustom.setTypeface(font);

        approvalProgress = (ProgressBar) findViewById(R.id.approvalProgress);
        approvalProgress.setVisibility(View.VISIBLE);

        //checks user type(new or approved)
        SharedPreferences preferences = getSharedPreferences("ADMIN", MODE_PRIVATE);
        String userType = preferences.getString("UserType", null);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(ApprovalActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(ApprovalActivity.this);
        }

        tvFName = (TextView) findViewById(R.id.tvFName);
        tvMName = (TextView) findViewById(R.id.tvMName);
        tvAEmail = (TextView) findViewById(R.id.tvAEmail);
        tvAPhone = (TextView) findViewById(R.id.tvAPhone);
        tvCName = (TextView) findViewById(R.id.tvCName);
        tvCDOB = (TextView) findViewById(R.id.tvCDOB);
        tvPSchool = (TextView) findViewById(R.id.tvPSchool);
        tvALevel = (TextView) findViewById(R.id.tvALevel);
        tvALocation = (TextView) findViewById(R.id.tvALocation);
        tvAPassword = (TextView) findViewById(R.id.tvAPassword);

        etFName = (EditText) findViewById(R.id.etFName);
        etMName = (EditText) findViewById(R.id.etMName);
        etAEmail = (EditText) findViewById(R.id.etAEmail);
        etAPhone = (EditText) findViewById(R.id.etAPhone);
        etCName = (EditText) findViewById(R.id.etCName);
        etCDOB = (EditText) findViewById(R.id.etCDOB);
        etPSchool = (EditText) findViewById(R.id.etPSchool);
        etALevel = (EditText) findViewById(R.id.etALevel);
        etALocation = (EditText) findViewById(R.id.etALocation);

        Button btnDecline = (Button) findViewById(R.id.btnDecline);
        Button btnAccept = (Button) findViewById(R.id.btnAccept);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnSave = (Button) findViewById(R.id.btnSave);

        tvNewUsers = (TextView) findViewById(R.id.tvNewUsers);
        tvEnrolledUsers = (TextView) findViewById(R.id.tvEnrolledUsers);
        tvUpdateUser = (TextView) findViewById(R.id.tvUpdateUser);

        tvFName.setVisibility(View.VISIBLE);
        tvMName.setVisibility(View.VISIBLE);
        tvAEmail.setVisibility(View.VISIBLE);
        tvAPhone.setVisibility(View.VISIBLE);
        tvCName.setVisibility(View.VISIBLE);
        tvCDOB.setVisibility(View.VISIBLE);
        tvPSchool.setVisibility(View.VISIBLE);
        tvALevel.setVisibility(View.VISIBLE);
        tvALocation.setVisibility(View.VISIBLE);
        tvAPassword.setVisibility(View.VISIBLE);

        etFName.setVisibility(View.GONE);
        etMName.setVisibility(View.GONE);
        etAEmail.setVisibility(View.GONE);
        etAPhone.setVisibility(View.GONE);
        etCName.setVisibility(View.GONE);
        etCDOB.setVisibility(View.GONE);
        etPSchool.setVisibility(View.GONE);
        etALevel.setVisibility(View.GONE);
        etALocation.setVisibility(View.GONE);

        LinearLayout llPassword = (LinearLayout) findViewById(R.id.llPassword);

        if(userType.equals("new")){
            btnDecline.setVisibility(View.VISIBLE);
            btnAccept.setVisibility(View.VISIBLE);
            btnDelete.setVisibility(View.GONE);
            btnEdit.setVisibility(View.GONE);
            btnSave.setVisibility(View.GONE);
            tvNewUsers.setVisibility(View.VISIBLE);
            tvEnrolledUsers.setVisibility(View.GONE);
            tvUpdateUser.setVisibility(View.GONE);
            llPassword.setVisibility(View.GONE);
        } else {
            btnDecline.setVisibility(View.GONE);
            btnAccept.setVisibility(View.GONE);
            btnDelete.setVisibility(View.VISIBLE);
            btnEdit.setVisibility(View.VISIBLE);
            btnSave.setVisibility(View.GONE);
            tvNewUsers.setVisibility(View.GONE);
            tvEnrolledUsers.setVisibility(View.VISIBLE);
            tvUpdateUser.setVisibility(View.GONE);
            llPassword.setVisibility(View.VISIBLE);
        }

        //directing to user details by respective userKey
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference().child("users").child(userKey);

        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);

                tvFName.setText(user.fatherName);
                tvMName.setText(user.motherName);
                tvAEmail.setText(user.email);
                tvAPhone.setText(user.phone);
                tvCName.setText(user.childName);
                tvCDOB.setText(user.dob);
                tvPSchool.setText(user.preSchool);
                tvALevel.setText(user.level);
                tvALocation.setText(user.location);
                tvAPassword.setText(user.password);

                etFName.setText(user.fatherName);
                etMName.setText(user.motherName);
                etAEmail.setText(user.email);
                etAPhone.setText(user.phone);
                etCName.setText(user.childName);
                etCDOB.setText(user.dob);
                etPSchool.setText(user.preSchool);
                etALevel.setText(user.level);
                etALocation.setText(user.location);

                approvalProgress.setVisibility(View.GONE);
                adminProgress.setVisibility(View.GONE);

                phone = tvAPhone.getText().toString();
                login = tvAEmail.getText().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Approval")
                        .setMessage("Are You Sure You Want To Approve This Entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                showPasswordDialog();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(R.drawable.alert)
                        .show();
            }
        });

        btnDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Decline")
                        .setMessage("Are You Sure You Want To Decline This Entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                showDeclineDialog();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(R.drawable.alert)
                        .show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Delete")
                        .setMessage("Are You Sure You Want To Delete This Entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                showDeclineDialog();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(R.drawable.alert)
                        .show();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminPassword();
                builder.setTitle("Edit")
                        .setMessage("Are You Sure You Want To Update This User Details?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                showUpdateDialog();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(R.drawable.alert)
                        .show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation()) {
                    update();
                    openAdmin();
                }
            }
        });

    }

    //check to avoid misClick on edit
    public void adminPassword(){
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference();
        mFirebaseDatabase.child("preschool")
                .orderByChild("preschoolName")
                .equalTo(tvPSchool.getText().toString())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                            Preschool preschool = childSnapshot.getValue(Preschool.class);
                            aPassword = preschool.preschoolPassword;
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("Admin", "Wrong admin password");
                    }
                });
    }

    //Approving user for respective preschool
    public void approve(){

        int permissionCheck = ContextCompat.checkSelfPermission(ApprovalActivity.this, android.Manifest.permission.SEND_SMS);
        if(permissionCheck == 0) {
            //sending login credentials for users phone
            sendSMS(phone, "Greetings From Brilla App\nYour Login Details Are:\nLogin:"+login+"\nPassword:"+password);
            openAdmin();
        } else {
            final Dialog dialog2 = new Dialog(ApprovalActivity.this);
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
                    ActivityCompat.requestPermissions(ApprovalActivity.this, new String[]{android.Manifest.permission.SEND_SMS}, REQUEST_SEND_SMS);
                }
            });
            dialog2.show();
        }

    }

    //Declining user for respective preschool
    public void decline(){
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference().child("users").child(userKey);
        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    //remove user details from database
                    mFirebaseDatabase.removeValue();
                    Toast.makeText(getBaseContext(), "This Entry Is Deleted From Database", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Admin", "Failed to decline user");
            }
        });

    }

    //updating user details in the database
    public void update(){
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(userKey);
        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    //setting values for all the fields of user
                    mFirebaseDatabase.child("childName").setValue(etCName.getText().toString());
                    mFirebaseDatabase.child("dob").setValue(etCDOB.getText().toString());
                    mFirebaseDatabase.child("preSchool").setValue(etPSchool.getText().toString());
                    mFirebaseDatabase.child("level").setValue(etALevel.getText().toString());
                    mFirebaseDatabase.child("location").setValue(etALocation.getText().toString());
                    mFirebaseDatabase.child("fatherName").setValue(etFName.getText().toString());
                    mFirebaseDatabase.child("motherName").setValue(etMName.getText().toString());
                    mFirebaseDatabase.child("phone").setValue(etAPhone.getText().toString());
                    mFirebaseDatabase.child("email").setValue(etAEmail.getText().toString());
                    Toast.makeText(getBaseContext(), "User Details Are Updated", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Admin", "Failed to update user details");
            }
        });
    }

    //opening admin activity
    public void openAdmin(){
        approvalProgress.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                AdminActivity.getInstance().finish();
                startActivity(new Intent(getBaseContext(), AdminActivity.class));
                finish();
                approvalProgress.setVisibility(View.GONE);
            }
        },2000);
    }

    //setting password for user when a user is under approval
    public void showPasswordDialog() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);
        final EditText etDialog = (EditText) dialogView.findViewById(R.id.etDialog);
        dialogBuilder.setTitle("PASSWORD");
        dialogBuilder.setMessage("Enter Password For " + tvFName.getText().toString());
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                password = etDialog.getText().toString();
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getBaseContext(),"Please Provide Password For This User", Toast.LENGTH_SHORT).show();
                } else {
                    approve();
                }
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    //avoiding misClick on decline button
    public void showDeclineDialog() {
        adminPassword();
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);
        final EditText etDialog = (EditText) dialogView.findViewById(R.id.etDialog);
        dialogBuilder.setTitle("ADMIN");
        dialogBuilder.setMessage("Please Enter Admin Password To Delete This Entry");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String password = etDialog.getText().toString();
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getBaseContext(),"Please Enter Admin Password", Toast.LENGTH_SHORT).show();
                } else if(password.equals(aPassword)) {
                    decline();
                    openAdmin();
                } else {
                    Toast.makeText(getBaseContext(),"Incorrect Password, Please Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    //avoiding misClick on update button by showing dialog for confirmation
    public void showUpdateDialog() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);
        final EditText etDialog = (EditText) dialogView.findViewById(R.id.etDialog);
        dialogBuilder.setTitle("ADMIN");
        dialogBuilder.setMessage("Please Enter Admin Password To Update This User Details");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String adminPassword = etDialog.getText().toString();
                if(TextUtils.isEmpty(adminPassword)){
                    Toast.makeText(getBaseContext(),"Please Enter Admin Password", Toast.LENGTH_SHORT).show();
                } else if(adminPassword.equals(aPassword)) {
                    btnDelete.setVisibility(View.GONE);
                    btnEdit.setVisibility(View.GONE);
                    btnSave.setVisibility(View.VISIBLE);
                    tvEnrolledUsers.setVisibility(View.GONE);
                    tvNewUsers.setVisibility(View.GONE);
                    tvUpdateUser.setVisibility(View.VISIBLE);

                    tvFName.setVisibility(View.GONE);
                    tvMName.setVisibility(View.GONE);
                    tvAEmail.setVisibility(View.GONE);
                    tvAPhone.setVisibility(View.GONE);
                    tvCName.setVisibility(View.GONE);
                    tvCDOB.setVisibility(View.GONE);
                    tvPSchool.setVisibility(View.GONE);
                    tvALevel.setVisibility(View.GONE);
                    tvALocation.setVisibility(View.GONE);

                    etFName.setVisibility(View.VISIBLE);
                    etMName.setVisibility(View.VISIBLE);
                    etAEmail.setVisibility(View.VISIBLE);
                    etAPhone.setVisibility(View.VISIBLE);
                    etCName.setVisibility(View.VISIBLE);
                    etCDOB.setVisibility(View.VISIBLE);
                    etPSchool.setVisibility(View.VISIBLE);
                    etALevel.setVisibility(View.VISIBLE);
                    etALocation.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getBaseContext(),"Incorrect Password, Please Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    //sending sms with login credentials if the user is approved
    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_LONG).show();
            //approving user for preschool and updating password field
            mFirebaseDatabase.child("acceptance").setValue("approved");
            mFirebaseDatabase.child("password").setValue(password);
            //if sms is sent send email
            sendEmail();
            Toast.makeText(getBaseContext(), "This Entry Is Approved As User", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "SMS sending failed" , Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    //sending email with a default message and login credentials
    protected boolean sendEmail() {
        String email = login;
        String subject = "Registration Successful";
        String message = "Successfully Registered as a user with "+tvPSchool.getText().toString() + " Pre School";
        String pas = password;
        //Creating SendMail object
        SendMail sm = new SendMail(ApprovalActivity.this, email, subject, message, pas);
        //Executing sendmail to send email
        sm.execute();

        return true;
    }

    //validating all the user fields before updating values into database
    public boolean validation(){
        if(TextUtils.isEmpty(etCName.getText().toString())
                || TextUtils.isEmpty(etCDOB.getText().toString())
                || TextUtils.isEmpty(etPSchool.getText().toString())
                || TextUtils.isEmpty(etALevel.getText().toString())
                || TextUtils.isEmpty(etALocation.getText().toString())
                || TextUtils.isEmpty(etFName.getText().toString())
                || TextUtils.isEmpty(etMName.getText().toString())
                || TextUtils.isEmpty(etAPhone.getText().toString())
                || TextUtils.isEmpty(etAEmail.getText().toString())){
            Toast.makeText(getBaseContext(), "Please Fill All The Details Before Saving This User Details", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_SEND_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //sending login credentials for users phone
                    sendSMS(phone, "Greetings From Brilla App\nYour Login Details Are:\nLogin:" + login + "\nPassword:" + password);
                    openAdmin();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        AdminActivity.getInstance().finish();
        startActivity(new Intent(getBaseContext(), AdminActivity.class));
        finish();
        super.onBackPressed();
    }
}
