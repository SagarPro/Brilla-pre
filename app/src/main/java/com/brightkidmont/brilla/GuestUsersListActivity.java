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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.brightkidmont.brilla.adapter.GuestAdapter;
import com.brightkidmont.brilla.utils.GuestUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GuestUsersListActivity extends AppCompatActivity {

    private ListView lvGuestUserList;
    public static List<String> guestList = new ArrayList<>();
    private ProgressBar pbGuestList;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    static GuestUsersListActivity guestUsersListActivity;
    AlertDialog.Builder builder;
    private int numberOfGuestUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_users_list_layout);

        Typeface font = Typeface.createFromAsset(this.getAssets(), "adikku_panadai_menari.ttf");
        Toolbar toolbar = (Toolbar) findViewById(R.id.tbCustom);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView tvCustom = (TextView) findViewById(R.id.tvCustom);
        tvCustom.setText("Brilla Pre");
        tvCustom.setTypeface(font);

        guestUsersListActivity = this;

        pbGuestList = (ProgressBar) findViewById(R.id.pbGuestList);
        pbGuestList.setVisibility(View.VISIBLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(GuestUsersListActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(GuestUsersListActivity.this);
        }

        lvGuestUserList = (ListView) findViewById(R.id.lvGuestUserList);
        Button btnPreSchoolList = (Button) findViewById(R.id.btnPreSchoolList);

        //directing to guests in database and retrieving their names to display as list to SuperAdmin
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference().child("guest");
        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                guestList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    GuestUser guestUser = snapshot.getValue(GuestUser.class);
                    String name =  guestUser.guestName;
                    guestList.add(name);
                }
                numberOfGuestUsers = guestList.size();
                Collections.sort(guestList);
                lvGuestUserList.setAdapter(new GuestAdapter(GuestUsersListActivity.this, guestList));
                pbGuestList.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Guest", "Adding guest to list failed");
            }
        });

        btnPreSchoolList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuestUsersListActivity.this, SuperAdminActivity.class));
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.guest_user_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.logout){
            SharedPreferences loginPreferences = getSharedPreferences("LOGINTYPE", MODE_PRIVATE);
            SharedPreferences.Editor editor = loginPreferences.edit();
            editor.putString("LOGIN", "logout");
            editor.commit();
            startActivity(new Intent(getBaseContext(), LoginActivity.class));
            finish();
        }
        //To delete all guest users with confirmation
        if(id == R.id.deleteAll){
            if(numberOfGuestUsers == 0){
                Toast.makeText(getBaseContext(), "There Are No Guest Users To Delete.", Toast.LENGTH_LONG).show();
            } else {
                builder.setTitle("Delete")
                        .setMessage("Are You Sure You Want To Delete This Entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                showDeclineDialog();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(R.drawable.alert)
                        .show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    //Deleting all Guest user details from database
    public void showDeclineDialog() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText etDialog = (EditText) dialogView.findViewById(R.id.etDialog);

        dialogBuilder.setTitle("SUPER ADMIN");
        dialogBuilder.setMessage("Please Enter Super Admin Password To Delete This Entry");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String adminPassword = etDialog.getText().toString();
                if(TextUtils.isEmpty(adminPassword)){
                    Toast.makeText(getBaseContext(),"Please Enter Super Admin Password", Toast.LENGTH_SHORT).show();
                } else if(adminPassword.equals("SuperAdmin")) {
                    deleteAll();
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

    //to delete all guests at once
    public void deleteAll(){

        pbGuestList.setVisibility(View.VISIBLE);
        final DatabaseReference child = mFirebaseInstance.getReference().child("guest");
        child.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    child.removeValue();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    guestList.clear();
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        GuestUser guestUser = snapshot.getValue(GuestUser.class);
                                        String name =  guestUser.guestName;
                                        guestList.add(name);
                                    }
                                    numberOfGuestUsers = guestList.size();
                                    Collections.sort(guestList);
                                    lvGuestUserList.setAdapter(new GuestAdapter(GuestUsersListActivity.this, guestList));
                                    pbGuestList.setVisibility(View.GONE);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });
                            Toast.makeText(getBaseContext(), "All Guest User Details are Deleted From Database", Toast.LENGTH_LONG).show();
                        }
                    },2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Guest", "Failed to delete all guests from database");
            }
        });

    }

    public static GuestUsersListActivity getInstance(){
        return   guestUsersListActivity;
    }
}
