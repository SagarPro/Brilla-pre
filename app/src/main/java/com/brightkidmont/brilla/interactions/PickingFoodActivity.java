package com.brightkidmont.brilla.interactions;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.brightkidmont.brilla.R;
import com.brightkidmont.brilla.adapter.FoodAdapter;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Arrays;

import static com.brightkidmont.brilla.HomePageActivity.bgm3;
import static com.brightkidmont.brilla.HomePageActivity.bgm3PausePosition;
import static com.brightkidmont.brilla.adapter.FoodAdapter.foodCount;

public class PickingFoodActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    ArrayList<String> alName;
    ArrayList<String> alImage;
    public static ImageView ivFood1, ivFood2, ivFood3, ivFood4, ivFood;
    public ImageView ivPlate;
    public static TextView tvResult;
    public static PickingFoodActivity activity;
    public static SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picking_food_layout);

        Typeface font = Typeface.createFromAsset(this.getAssets(), "adikku_panadai_menari.ttf");
        Toolbar toolbar = (Toolbar) findViewById(R.id.tbCustom);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView tvCustom = (TextView) findViewById(R.id.tvCustom);
        tvCustom.setText("Brilla Pre");
        tvCustom.setTypeface(font);

        activity = this;
        foodCount = 0;

        ivFood1 = (ImageView) findViewById(R.id.ivFood1);
        ivFood2 = (ImageView) findViewById(R.id.ivFood2);
        ivFood3 = (ImageView) findViewById(R.id.ivFood3);
        ivFood4 = (ImageView) findViewById(R.id.ivFood4);
        ivFood = (ImageView) findViewById(R.id.ivFood);

        tvResult = (TextView) findViewById(R.id.tvResult);

        //fetching food plate image from database
        String path = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Story%20Images%2Fdinner_plate.png?alt=media&token=55b6010f-1cf9-468e-b9a6-f54001ae82f4";
        ivPlate = (ImageView) findViewById(R.id.ivPlate);
        Picasso.with(this).load(path).placeholder(R.drawable.bright_kid_bg).error(R.drawable.bright_kid_bg).into(ivPlate);

        preferences = getSharedPreferences("FOODTYPE", MODE_PRIVATE);
        String foodType = preferences.getString("TYPE", "BreakFast");

        //storing names and paths of selected food type in arrays
        switch (foodType){

            case "BreakFast":
                alName = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.breakfast_names)));
                alImage = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.breakfast)));
                break;

            case "Lunch":
                alName = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.lunch_names)));
                alImage = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.lunch)));
                break;

            case "Snacks":
                alName = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.snacks_names)));
                alImage = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.snacks)));
                break;

            case "Dinner":
                alName = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.dinner_names)));
                alImage = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.dinner)));
                break;
        }

        // Calling the RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //setting foodAdapter to display list of food items
        mAdapter = new FoodAdapter(PickingFoodActivity.this, alName, alImage);
        mRecyclerView.setAdapter(mAdapter);

        preferences = getSharedPreferences("FOODITEMS", MODE_PRIVATE);

        final String[] removedFood = new String[1];

        //Removal of food from food plate
        ivFood1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodCount = 0;
                ivFood1.setVisibility(View.INVISIBLE);
                removedFood[0] = preferences.getString("food1", null);
                tvResult.setText("Please Select Food Items To Eat");
            }
        });

        ivFood2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodCount = 0;
                ivFood2.setVisibility(View.INVISIBLE);
                removedFood[0] = preferences.getString("food2", null);
                tvResult.setText("Please Select Food Items To Eat");
            }
        });

        ivFood3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodCount = 0;
                ivFood3.setVisibility(View.INVISIBLE);
                removedFood[0] = preferences.getString("food3", null);
                tvResult.setText("Please Select Food Items To Eat");
            }
        });

        ivFood4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodCount = 0;
                ivFood4.setVisibility(View.INVISIBLE);
                removedFood[0] = preferences.getString("food4", null);
                tvResult.setText("Please Select Food Items To Eat");
            }
        });

        ivFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodCount = 0;
                ivFood.setVisibility(View.INVISIBLE);
                removedFood[0] = preferences.getString("food", null);
                tvResult.setText("Please Select Food Items To Eat");
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        bgm3.pause();
        bgm3PausePosition = bgm3.getCurrentPosition();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bgm3.seekTo(bgm3PausePosition);
        bgm3.start();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        bgm3.pause();
        bgm3PausePosition = bgm3.getCurrentPosition();
        finish();
    }

}
