package com.brightkidmont.brilla.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.brightkidmont.brilla.R;
import com.brightkidmont.brilla.interactions.PickingFoodActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.brightkidmont.brilla.interactions.PickFoodActivity.mpBreakfast;
import static com.brightkidmont.brilla.interactions.PickFoodActivity.mpDinner;
import static com.brightkidmont.brilla.interactions.PickFoodActivity.mpLunch;
import static com.brightkidmont.brilla.interactions.PickFoodActivity.mpSnacks;
import static com.brightkidmont.brilla.interactions.PickingFoodActivity.ivFood;
import static com.brightkidmont.brilla.interactions.PickingFoodActivity.ivFood1;
import static com.brightkidmont.brilla.interactions.PickingFoodActivity.ivFood2;
import static com.brightkidmont.brilla.interactions.PickingFoodActivity.ivFood3;
import static com.brightkidmont.brilla.interactions.PickingFoodActivity.ivFood4;
import static com.brightkidmont.brilla.interactions.PickingFoodActivity.tvResult;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> implements TextToSpeech.OnInitListener{

    private ArrayList<String> alName;
    private ArrayList<String> alImage;
    private Context context;
    public static int foodCount = 0;

    public FoodAdapter(Context context, ArrayList<String> alName, ArrayList<String> alImage) {
        super();
        this.context = context;
        this.alName = alName;
        this.alImage = alImage;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.grid_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvSpecies.setText(alName.get(i));
        Picasso.with(context).load(alImage.get(i)).placeholder(R.drawable.bright_kid_bg).error(R.drawable.bright_kid_bg).into(viewHolder.imgThumbnail);

        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    Toast.makeText(context, alName.get(position) + " (Long click)", Toast.LENGTH_SHORT).show();
                } else {

                    //Storing all audio files in respective array
                    Boolean playing = false;
                    for(int i=0; i<20; i++) {
                        if (mpBreakfast[i].isPlaying()) {
                            playing = true;
                            break;
                        }
                    }
                    for(int i=0; i<20; i++) {
                        if (mpLunch[i].isPlaying()) {
                            playing = true;
                            break;
                        }
                    }
                    for(int i=0; i<11; i++) {
                        if (mpSnacks[i].isPlaying()) {
                            playing = true;
                            break;
                        }
                    }
                    for(int i=0; i<18; i++) {
                        if (mpDinner[i].isPlaying()) {
                            playing = true;
                            break;
                        }
                    }

                    if(playing){
                        Toast.makeText(context, "Please wait a sec", Toast.LENGTH_SHORT).show();
                    } else {

                        //list of all big food names
                        List<String> foodNames = new ArrayList<>(Arrays.asList(context.getResources().getStringArray(R.array.food_items)));

                        SharedPreferences.Editor editor = PickingFoodActivity.preferences.edit();

                        if (foodNames.contains(alName.get(position))) {
                            if (ivFood.getVisibility() == View.INVISIBLE) {
                                ivFood.setVisibility(View.VISIBLE);
                                ivFood1.setVisibility(View.INVISIBLE);
                                ivFood2.setVisibility(View.INVISIBLE);
                                ivFood3.setVisibility(View.INVISIBLE);
                                ivFood4.setVisibility(View.INVISIBLE);
                                Picasso.with(context).load(alImage.get(position)).placeholder(R.drawable.bright_kid_bg).error(R.drawable.bright_kid_bg).into(ivFood);
                                tvResult.setText("This is good to eat");
                                editor.putString("food", alName.get(position));
                                audio(position);
                                foodCount = 33;
                            } else {
                                foodCount++;
                            }
                        }

                        //place food in first space
                        if (foodCount == 0) {
                            if (ivFood1.getVisibility() == View.INVISIBLE) {
                                ivFood1.setVisibility(View.VISIBLE);
                                Picasso.with(context).load(alImage.get(position)).placeholder(R.drawable.bright_kid_bg).error(R.drawable.bright_kid_bg).into(ivFood1);
                                tvResult.setText("Are You On A Diet?");
                                editor.putString("food1", alName.get(position));
                                audio(position);
                            } else {
                                foodCount++;
                            }
                        }
                        //place food in second space
                        if (foodCount == 1) {
                            if (ivFood2.getVisibility() == View.INVISIBLE) {
                                ivFood2.setVisibility(View.VISIBLE);
                                Picasso.with(context).load(alImage.get(position)).placeholder(R.drawable.bright_kid_bg).error(R.drawable.bright_kid_bg).into(ivFood2);
                                tvResult.setText("You have to add some healthy food");
                                editor.putString("food2", alName.get(position));
                                audio(position);
                            } else {
                                foodCount++;
                            }
                        }
                        //place food in third space
                        if (foodCount == 2) {
                            if (ivFood3.getVisibility() == View.INVISIBLE) {
                                ivFood3.setVisibility(View.VISIBLE);
                                Picasso.with(context).load(alImage.get(position)).placeholder(R.drawable.bright_kid_bg).error(R.drawable.bright_kid_bg).into(ivFood3);
                                tvResult.setText("These Foods are good for health");
                                editor.putString("food3", alName.get(position));
                                audio(position);
                            } else {
                                foodCount++;
                            }
                        }
                        //place food in forth space
                        if (foodCount == 3) {
                            if (ivFood4.getVisibility() == View.INVISIBLE) {
                                ivFood4.setVisibility(View.VISIBLE);
                                Picasso.with(context).load(alImage.get(position)).placeholder(R.drawable.bright_kid_bg).error(R.drawable.bright_kid_bg).into(ivFood4);
                                tvResult.setText("Perfect food to eat");
                                editor.putString("food4", alName.get(position));
                                audio(position);
                            } else {
                                foodCount++;
                            }
                        }
                        if (foodCount == 33) {
                            tvResult.setText("This is good to eat");
                        } else if (foodCount > 3) {
                            String text = "More Food Is Not Good For Health";
                            tvResult.setText(text);
                        }
                        foodCount++;

                        if (ivFood1.getVisibility() == View.INVISIBLE && ivFood2.getVisibility() == View.INVISIBLE
                                && ivFood3.getVisibility() == View.INVISIBLE && ivFood4.getVisibility() == View.INVISIBLE && ivFood.getVisibility() == View.INVISIBLE) {
                            tvResult.setText("Please Select Food Items To Eat");
                        }
                        editor.apply();
                    }
                }
            }

        });

    }

    //starting audio based on food type and position
    private void audio(final int position){
        SharedPreferences pref = context.getSharedPreferences("FOODTYPE", MODE_PRIVATE);
        String foodType = pref.getString("TYPE", "BreakFast");
        switch (foodType){
            case "BreakFast":
                mpBreakfast[position].start();
                break;
            case "Lunch":
                mpLunch[position].start();
                break;
            case "Snacks":
                mpSnacks[position].start();
                break;
            case "Dinner":
                mpDinner[position].start();
                break;
        }
    }

    @Override
    public int getItemCount() {
        return alName.size();
    }

    @Override
    public void onInit(int status) {
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        ImageView imgThumbnail;
        TextView tvSpecies;
        private ItemClickListener clickListener;

        ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            tvSpecies = (TextView) itemView.findViewById(R.id.tv_species);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }
    }

}
