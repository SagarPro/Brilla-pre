package com.brightkidmont.brilla;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.brightkidmont.brilla.utils.Preschool;
import com.brightkidmont.brilla.utils.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnrollActivity extends AppCompatActivity implements View.OnClickListener {

    //private ImageView ivKidImage;
    SharedPreferences sharedpreferences;
    private EditText etFatherName, etMotherName, etEmail, etPhone, etChildName;
    private TextView tvDOB;
    private CheckBox cbTC;
    private DatabaseReference mFirebaseDatabase;
    private AutoCompleteTextView actPreSchool;
    String fatherName, motherName, email, phone, childName, dob, password, acceptance;
    Calendar myCalendar;
    private String userId;
    String sPreSchool, sLocation, sLevel;
    List<String> psList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enroll_layout);

        Typeface font = Typeface.createFromAsset(this.getAssets(), "adikku_panadai_menari.ttf");
        Toolbar toolbar = (Toolbar) findViewById(R.id.tbCustom);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView tvCustom = (TextView) findViewById(R.id.tvCustom);
        tvCustom.setText("Brilla Pre");
        tvCustom.setTypeface(font);

        Button btnEnroll = (Button) findViewById(R.id.btnEnroll);
        //ivKidImage = (ImageView) findViewById(R.id.ivKidImage);

        Spinner spinnerLevel = (Spinner) findViewById(R.id.spinnerLevel);
        spinnerLevel.getBackground().setColorFilter(getResources().getColor(R.color.textColorPrimary), PorterDuff.Mode.SRC_ATOP);

        actPreSchool = (AutoCompleteTextView) findViewById(R.id.actPreSchool);

        etFatherName = (EditText) findViewById(R.id.etFatherName);
        etMotherName = (EditText) findViewById(R.id.etMotherName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etChildName = (EditText) findViewById(R.id.etChildName);

        ImageView ivDOB = (ImageView) findViewById(R.id.ivDOB);

        tvDOB = (TextView) findViewById(R.id.tvDOB);
        TextView tvTC = (TextView) findViewById(R.id.tvTC);
        cbTC = (CheckBox) findViewById(R.id.cbTC);

        myCalendar = Calendar.getInstance();

        btnEnroll.setOnClickListener(this);
        //ivKidImage.setOnClickListener(this);
        ivDOB.setOnClickListener(this);

        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        //directing to preschools
        DatabaseReference databaseReference = mFirebaseInstance.getReference("preschool");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String ps = snapshot.getKey();
                    psList.add(ps);
                }
                Collections.sort(psList);

                final ArrayAdapter<String> psAdapter = new ArrayAdapter<String>(EnrollActivity.this, android.R.layout.simple_spinner_item, psList);

                psAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                actPreSchool.setThreshold(1);
                actPreSchool.setAdapter(psAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("ENROLL", "Failed to get preschool name and location");
            }
        });

        // Spinner Drop down elements
        List<String> level = new ArrayList<String>();
        level.add("Play Group");
        level.add("Nursery");
        level.add("LKG");
        level.add("UKG");

        ArrayAdapter<String> levelAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, level);
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevel.setAdapter(levelAdapter);

        spinnerLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                sLevel = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("ENROLL", "Nothing is selected");
            }
        });

        //T&C Dialog
        final Dialog dialog = new Dialog(EnrollActivity.this);
        dialog.setContentView(R.layout.terms_conditions);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        TextView TC = (TextView) dialog.findViewById(R.id.TC);
        TC.setText(R.string.Users_Terms_and_conditions);

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

    }

    //set dob of child in tvDOB in specific format
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tvDOB.setText(sdf.format(myCalendar.getTime()));
    }

    //creating user details with key in database
    private void createUser(String fatherName, String motherName, String email, String phone, String childName, String dob, String preSchool, String level, String location, String password, String acceptance) {
        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }
        User user = new User(fatherName, motherName, email, phone, childName, dob, preSchool, level, location, password, acceptance);
        mFirebaseDatabase.child(userId).setValue(user);
        Toast.makeText(getBaseContext(), "Your Account Will Be Verified Within 24 hours", Toast.LENGTH_LONG).show();
        finish();
    }

    //to set image in imageview
    /*protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    ivKidImage.setImageURI(selectedImage);
                }
                break;
        }
    }*/

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){

            /*case R.id.ivKidImage:
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);
                break;*/

            case R.id.btnEnroll:
                validate();
                break;

            //Pick dob of a child from datePicker
            case R.id.ivDOB:
                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateLabel();
                    }

                };

                new DatePickerDialog(EnrollActivity.this,date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
        }
    }

    //Validating user entered details
    public void validate(){

        sharedpreferences = getSharedPreferences("USER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean("FIRST", true);
        editor.apply();

        fatherName = etFatherName.getText().toString();
        motherName = etMotherName.getText().toString();
        email = etEmail.getText().toString();
        phone = etPhone.getText().toString();
        childName = etChildName.getText().toString();
        dob = tvDOB.getText().toString();
        //Default password and acceptance value
        password = "Brightedge068";
        acceptance = "waiting";
        String validPhone = "valid";
        String validEmail = "valid";
        final String[] validUser = {"valid"};

        //final String[] registeredEmail = {"not"};

        //checking email pattern
        if (TextUtils.isEmpty(email)) {
            validEmail = "not";
        } else {
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            Pattern patternEmail = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
            Matcher matcherEmail = patternEmail.matcher(email);
            if (!matcherEmail.find()) {
                validEmail = "not";
            }
        }

        //validating phone number
        if (TextUtils.isEmpty(phone)) {
            validPhone = "not";
            validUser[0] = "not";
        } else if(phone.length()!=10) {
                validPhone = "not";
                validUser[0] = "not";
        }

        //validating remaining details
        if(TextUtils.isEmpty(childName)) {
            etChildName.setError("Enter Your Child Name");
            validUser[0] = "not";
        } else if(TextUtils.isEmpty(dob)){
            Toast.makeText(getBaseContext(), "Please Select DOB Of Child From Calender", Toast.LENGTH_SHORT).show();
            validUser[0] = "not";
        } else if(TextUtils.isEmpty(fatherName)){
            etFatherName.setError("Enter Child's Father Name");
            validUser[0] = "not";
        } else if(TextUtils.isEmpty(motherName)){
            etMotherName.setError("Enter Child's Mother Name");
            validUser[0] = "not";
        } else if(validEmail.equals("not")) {
            etEmail.setError("Enter Valid Email id");
            validUser[0] = "not";
        } else if(validPhone.equals("not")) {
            etPhone.setError("Enter Valid Phone Number");
            validUser[0] = "not";
        } else if(!psList.contains(actPreSchool.getText().toString())){
            Toast.makeText(this, "Enter Valid Preschool Name", Toast.LENGTH_SHORT).show();
            validUser[0] = "not";
        } else if(!cbTC.isChecked()) {
            Toast.makeText(getBaseContext(), "Please Accept Terms and Conditions Before Enrollment", Toast.LENGTH_SHORT).show();
            validUser[0] = "not";
        }

        final String emailId = etEmail.getText().toString();
        final String[] finalValid = {validUser[0]};
        final String finalValidEmail = validEmail;
        final String finalValidPhone = validPhone;
        if(finalValid[0].equals("valid")) {
            //check whether user is already registered
            FirebaseDatabase.getInstance().getReference().child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User user = snapshot.getValue(User.class);
                        String e = user.email;
                        if (emailId.equals(e)) {
                            Toast.makeText(getBaseContext(), "User with this Email Id Is already Registered.", Toast.LENGTH_SHORT).show();
                            finalValid[0] = "not";
                            break;
                        } else {
                            finalValid[0] = "valid";
                        }
                    }
                    //if not registered create new user in database else toast appropriate message
                    if (finalValid[0].equals("valid")) {
                        if (TextUtils.isEmpty(userId) && finalValidEmail.equals("valid") && finalValidPhone.equals("valid")) {
                            String s = actPreSchool.getText().toString();
                            sPreSchool = s.substring(0, s.indexOf("-"));
                            sLocation = s.substring(s.lastIndexOf("-")+1);
                            createUser(fatherName, motherName, email, phone, childName, dob, sPreSchool, sLevel, sLocation, password, acceptance);
                        } else {
                            Toast.makeText(getBaseContext(), "Please Enter Your Valid Details", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d("ENROLL", "Failed to create user details in database");
                }
            });

        } else {
            if(cbTC.isChecked())
                Toast.makeText(getBaseContext(), "Please Enter Your Valid Details", Toast.LENGTH_SHORT).show();
        }

    }
}
