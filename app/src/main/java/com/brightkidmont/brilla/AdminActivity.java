package com.brightkidmont.brilla;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.brightkidmont.brilla.adapter.UsersAdapter;
import com.brightkidmont.brilla.utils.Preschool;
import com.brightkidmont.brilla.utils.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private ListView lvUsers;
    static AdminActivity adminActivity;
    private Button btnAllUsers, btnNewUsers;
    private SharedPreferences loginPreferences;
    String psAdmin;
    List<String> userNames = new ArrayList<>();
    private DatabaseReference mFirebaseDatabase;
    private SharedPreferences preferences;
    public static ProgressBar adminProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_layout);

        //applying font style to app name in tool bar
        Typeface font = Typeface.createFromAsset(this.getAssets(), "adikku_panadai_menari.ttf");
        Toolbar toolbar = (Toolbar) findViewById(R.id.tbCustom);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView tvCustom = (TextView) findViewById(R.id.tvCustom);
        tvCustom.setText("Brilla Pre");
        tvCustom.setTypeface(font);

        //puts login type as admin, for the use of sharedPreferences
        loginPreferences = getSharedPreferences("LOGINTYPE", MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPreferences.edit();
        editor.putString("LOGIN", "admin");
        editor.putString("GUESTLOGIN", "not guest");
        editor.commit();

        //getting preschool Name to get the respective details
        SharedPreferences adminPreferences = getSharedPreferences("ADMIN", MODE_PRIVATE);
        psAdmin = adminPreferences.getString("PRESCHOOL", "sag");

        adminActivity = this;

        adminProgress = (ProgressBar) findViewById(R.id.adminProgress);
        adminProgress.setVisibility(View.VISIBLE);

        //database to point to users
        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference().child("users");

        lvUsers = (ListView) findViewById(R.id.lvUsers);
        btnAllUsers = (Button) findViewById(R.id.btnAllUsers);
        btnNewUsers = (Button) findViewById(R.id.btnNewUsers);
        btnNewUsers.setVisibility(View.GONE);

        preferences = getSharedPreferences("ADMIN", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = preferences.edit();
        editor1.putString("UserType", "new");
        editor1.apply();

        //listing all the newly enrolled users or users in waiting state
        mFirebaseDatabase.orderByChild("preSchool").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    String name = user.fatherName;
                    String ps = user.preSchool;
                    String acceptance = user.acceptance;
                    if(ps.equals(psAdmin)) {
                        if (acceptance.equals("waiting")) {
                            userNames.add(name);
                        }
                    }
                }
                //if there are no new users enrolled for this preschool
                if(userNames.size() == 0){
                    Toast.makeText(AdminActivity.this, "There are no new users", Toast.LENGTH_SHORT).show();
                }
                //sorting fathers name to display in the alphabetical order in listView
                Collections.sort(userNames);
                //setting userAdapter to display all new users
                lvUsers.setAdapter(new UsersAdapter(AdminActivity.this, userNames));
                adminProgress.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                adminProgress.setVisibility(View.GONE);
            }
        });

        btnAllUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("UserType", "approved");
                editor.commit();
                adminProgress.setVisibility(View.VISIBLE);
                userNames.clear();
                btnAllUsers.setVisibility(View.GONE);
                btnNewUsers.setVisibility(View.VISIBLE);
                //listing all the approved users
                mFirebaseDatabase.orderByChild("preSchool").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            User user = snapshot.getValue(User.class);
                            String name = user.fatherName;
                            String ps = user.preSchool;
                            String acceptance = user.acceptance;
                            if(ps.equals(psAdmin)) {
                                if (acceptance.equals("approved")) {
                                    userNames.add(name);
                                }
                            }
                        }
                        //if no users are approved to this preschool
                        if(userNames.size() == 0){
                            Toast.makeText(AdminActivity.this, "No users have been approved for this preschool", Toast.LENGTH_SHORT).show();
                        }
                        //sorting fathers name to display in the alphabetical order in listView
                        Collections.sort(userNames);
                        //setting userAdapter to display all approved users
                        lvUsers.setAdapter(new UsersAdapter(AdminActivity.this, userNames));
                        adminProgress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        adminProgress.setVisibility(View.GONE);
                    }
                });
            }
        });

        btnNewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("UserType", "new");
                editor.commit();
                adminProgress.setVisibility(View.VISIBLE);
                userNames.clear();
                btnAllUsers.setVisibility(View.VISIBLE);
                btnNewUsers.setVisibility(View.GONE);
                //listing all the newly enrolled users or users in waiting state
                mFirebaseDatabase.orderByChild("preSchool").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            User user = snapshot.getValue(User.class);
                            String name = user.fatherName;
                            String ps = user.preSchool;
                            String acceptance = user.acceptance;
                            if(ps.equals(psAdmin)) {
                                if (acceptance.equals("waiting")) {
                                    userNames.add(name);
                                }
                            }
                        }
                        //if there are no new users enrolled for this preschool
                        if(userNames.size() == 0){
                            Toast.makeText(AdminActivity.this, "There are no new users", Toast.LENGTH_SHORT).show();
                        }
                        //sorting fathers name to display in the alphabetical order in listView
                        Collections.sort(userNames);
                        //setting userAdapter to display all new users
                        lvUsers.setAdapter(new UsersAdapter(AdminActivity.this, userNames));
                        adminProgress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        adminProgress.setVisibility(View.GONE);
                    }
                });
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.logout){
            loginPreferences = getSharedPreferences("LOGINTYPE", MODE_PRIVATE);
            SharedPreferences.Editor editor = loginPreferences.edit();
            editor.putString("LOGIN", "logout");
            editor.apply();
            startActivity(new Intent(getBaseContext(), LoginActivity.class));
            finish();
        }

        if(id == R.id.profile){
            showPreschoolDetails();
        }

        return super.onOptionsItemSelected(item);
    }

    //show preschool or admin details
    public void showPreschoolDetails(){

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.ps_details_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        final ProgressBar pbPSDetails = (ProgressBar) dialog.findViewById(R.id.pbPSDetails);
        pbPSDetails.setVisibility(View.VISIBLE);

        final TextView psName = (TextView) dialog.findViewById(R.id.psName);
        final TextView psPhone = (TextView) dialog.findViewById(R.id.psPhone);
        final TextView psLogin = (TextView) dialog.findViewById(R.id.psLogin);
        final TextView psPassword = (TextView) dialog.findViewById(R.id.psPassword);
        final TextView psLocation = (TextView) dialog.findViewById(R.id.psLocation);

        //get preschool details based on preschool name that is stored in "psAdmin"
        FirebaseDatabase.getInstance().getReference().child("preschool").orderByChild("preschoolName").equalTo(psAdmin).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    Preschool preschool = childSnapshot.getValue(Preschool.class);
                    psName.setText(preschool.preschoolName);
                    psPhone.setText(preschool.preschoolPhone);
                    psLogin.setText(preschool.preschoolLogin);
                    psPassword.setText(preschool.preschoolPassword);
                    psLocation.setText(preschool.preschoolLocation);
                    pbPSDetails.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        dialog.show();
    }

    public static AdminActivity getInstance(){
        return   adminActivity;
    }

    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }
}