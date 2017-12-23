package com.brightkidmont.brilla;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.brightkidmont.brilla.adapter.PSUserAdapter;
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

import static com.brightkidmont.brilla.adapter.PreschoolAdapter.preSchoolKey;

public class PreschoolDetailsActivity extends AppCompatActivity {

    private TextView psName, psPhone, psLogin, psPassword, psLocation;
    private ListView lvpsUsers;
    private DatabaseReference mFirebaseDatabase;
    public static ProgressBar pbPreSchool;
    public static String preSchoolName;
    public static List<String> psUsers = new ArrayList<>();
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preschool_details_layout);

        Typeface font = Typeface.createFromAsset(this.getAssets(), "adikku_panadai_menari.ttf");
        Toolbar toolbar = (Toolbar) findViewById(R.id.tbCustom);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView tvCustom = (TextView) findViewById(R.id.tvCustom);
        tvCustom.setText("Brilla Pre");
        tvCustom.setTypeface(font);

        pbPreSchool = (ProgressBar) findViewById(R.id.pbPreSchool);
        pbPreSchool.setVisibility(View.VISIBLE);

        psName = (TextView) findViewById(R.id.psName);
        psPhone = (TextView) findViewById(R.id.psPhone);
        psLogin = (TextView) findViewById(R.id.psLogin);
        psPassword = (TextView) findViewById(R.id.psPassword);
        psLocation = (TextView) findViewById(R.id.psLocation);

        Button btnDeletePS = (Button) findViewById(R.id.btnDeletePS);

        lvpsUsers = (ListView) findViewById(R.id.lvpsUsers);

        SharedPreferences preferences = getSharedPreferences("ADMIN", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("UserType", "super");
        editor.apply();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(PreschoolDetailsActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(PreschoolDetailsActivity.this);
        }

        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference();

        //directing to specific preschool based on"preschoolKey" and setting values to textView
        DatabaseReference child = mFirebaseDatabase.child("preschool").child(preSchoolKey);
        child.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Preschool preschool = dataSnapshot.getValue(Preschool.class);
                psName.setText(preschool.preschoolName);
                psPhone.setText(preschool.preschoolPhone);
                psLogin.setText(preschool.preschoolLogin);
                psPassword.setText(preschool.preschoolPassword);
                psLocation.setText(preschool.preschoolLocation);

                preSchoolName = preschool.preschoolName;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("SuperAdmin", "error fetching preschool details");
            }
        });

        //to show users which are approved by their respective preschool admins
        DatabaseReference user = mFirebaseDatabase.child("users");
        user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                psUsers.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    String name = user.fatherName;
                    String acceptance = user.acceptance;
                    String preSchool = user.preSchool;
                    if(preSchool.equals(preSchoolName) && acceptance.equals("approved")){
                        psUsers.add(name);
                    }
                    //sorting preschool names in alphabetical order
                    Collections.sort(psUsers);
                }
                lvpsUsers.setAdapter(new PSUserAdapter(PreschoolDetailsActivity.this, psUsers));
                pbPreSchool.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("SuperAdmin", "Adding users to list failed");
            }
        });

        //Avoiding misClick of deleting preschool
        btnDeletePS.setOnClickListener(new View.OnClickListener() {
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

    }

    //Deleting preschool details from database
    public void decline(){
        final DatabaseReference child = mFirebaseDatabase.child("preschool").child(preSchoolKey);
        child.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    child.removeValue();
                    Toast.makeText(getBaseContext(), psName.getText().toString()+" PreSchool Is Deleted From Database", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("SuperAdmin", "Deleting Preschool failed");
            }
        });

    }

    //Deleting all user details belongs to deleted preschool
    public void deleteUsers(){
        final DatabaseReference child = mFirebaseDatabase.child("users");
        child.orderByChild("preSchool").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                            User user = childSnapshot.getValue(User.class);
                            String preSchool = user.preSchool;
                            if(preSchool.equals(preSchoolName)){
                                String preSchoolUser = childSnapshot.getKey();
                                try {
                                    child.child(preSchoolUser).removeValue();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("SuperAdmin","deleting preschool users failed");
                    }
                });
    }

    //Avoiding misClick on delete preschool button
    public void showDeclineDialog() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText etDialog = (EditText) dialogView.findViewById(R.id.etDialog);

        //checking if the user is SuperAdmin
        dialogBuilder.setTitle("SUPER ADMIN");
        dialogBuilder.setMessage("Please Enter Super Admin Password To Delete This Entry");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                pbPreSchool.setVisibility(View.VISIBLE);
                String adminPassword = etDialog.getText().toString();
                if(TextUtils.isEmpty(adminPassword)){
                    Toast.makeText(getBaseContext(),"Please Enter Super Admin Password", Toast.LENGTH_SHORT).show();
                } else if(adminPassword.equals("SuperAdmin")) {
                    decline();
                    deleteUsers();
                    openAdmin();
                    pbPreSchool.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getBaseContext(),"Incorrect Password, Please Try Again", Toast.LENGTH_SHORT).show();
                    pbPreSchool.setVisibility(View.GONE);
                }
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Log.d("SuperAdmin", "Failed to authenticate as SuperAdmin");
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    //Opening SuperAdminActivity after deleting preschool
    public void openAdmin(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SuperAdminActivity.getInstance().finish();
                startActivity(new Intent(getBaseContext(), SuperAdminActivity.class));
                finish();
            }
        },2000);
    }

}
