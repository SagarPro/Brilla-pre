package com.brightkidmont.brilla;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.brightkidmont.brilla.utils.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.brightkidmont.brilla.PreschoolDetailsActivity.pbPreSchool;
import static com.brightkidmont.brilla.adapter.PSUserAdapter.psUserKey;

public class UserDetailsActivity extends AppCompatActivity {

    private TextView udChildName, udDOB, udPreSchool, udLevel, udLocation, udFatherName, udMotherName, udPhone, udEmail;
    public static ProgressBar pbUserDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details_layout);

        Typeface font = Typeface.createFromAsset(this.getAssets(), "adikku_panadai_menari.ttf");
        Toolbar toolbar = (Toolbar) findViewById(R.id.tbCustom);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView tvCustom = (TextView) findViewById(R.id.tvCustom);
        tvCustom.setText("Brilla Pre");
        tvCustom.setTypeface(font);

        pbUserDetails = (ProgressBar) findViewById(R.id.pbUserDetails);
        pbUserDetails.setVisibility(View.VISIBLE);

        udChildName = (TextView) findViewById(R.id.udChildName);
        udDOB = (TextView) findViewById(R.id.udDOB);
        udPreSchool = (TextView) findViewById(R.id.udPreSchool);
        udLevel = (TextView) findViewById(R.id.udLevel);
        udLocation = (TextView) findViewById(R.id.udLocation);
        udFatherName = (TextView) findViewById(R.id.udFatherName);
        udMotherName = (TextView) findViewById(R.id.udMotherName);
        udPhone = (TextView) findViewById(R.id.udPhone);
        udEmail = (TextView) findViewById(R.id.udEmail);

        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
        DatabaseReference mFirebaseDatabase = mFirebaseInstance.getReference().child("users").child(psUserKey);

        //displaying details to user
        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                udChildName.setText(user.childName);
                udDOB.setText(user.dob);
                udPreSchool.setText(user.preSchool);
                udLevel.setText(user.level);
                udLocation.setText(user.location);
                udFatherName.setText(user.fatherName);
                udMotherName.setText(user.motherName);
                udEmail.setText(user.email);
                udPhone.setText(user.phone);
                pbPreSchool.setVisibility(View.GONE);
                pbUserDetails.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                pbUserDetails.setVisibility(View.GONE);
            }
        });
    }
}
