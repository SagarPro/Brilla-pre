package com.brightkidmont.brilla.my_bright_brain.visual.riddles;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brightkidmont.brilla.R;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

import static com.brightkidmont.brilla.my_bright_brain.visual.riddles.RiddlesActivity.player;
import static com.brightkidmont.brilla.my_bright_brain.visual.riddles.RiddlesActivity.points;
import static com.brightkidmont.brilla.my_bright_brain.visual.riddles.RiddlesActivity.riddlesActivity;

public class Trophy extends Fragment {

    private static ImageView gif_falling_papers, congratulations, better_luck, trophy;
    private static TextView pointsBanner;
    private static ArrayList<String> imagesList;
    public static RelativeLayout rlTrophy;

    public Trophy(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.trophy, container, false);

        gif_falling_papers = (ImageView) view.findViewById(R.id.gif_falling_papers);
        congratulations = (ImageView) view.findViewById(R.id.congratulations);
        better_luck = (ImageView) view.findViewById(R.id.better_luck);
        trophy = (ImageView) view.findViewById(R.id.trophy);
        pointsBanner = (TextView) view.findViewById(R.id.pointsBanner);
        rlTrophy = (RelativeLayout) view.findViewById(R.id.rlTrophy);

        imagesList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.trophy)));

        return view;
    }

    //trophy distribution based on points
    public static void trophyDistribution(){

        if(points == 0){
            Picasso.with(riddlesActivity).load(imagesList.get(4)).placeholder(R.drawable.bright_kid_bg).error(R.drawable.bright_kid_bg).into(better_luck);
        } else{
            better_luck.setVisibility(View.GONE);
            Glide.with(riddlesActivity)
                    .asGif()
                    .load(imagesList.get(0))
                    .into(gif_falling_papers);
            Picasso.with(riddlesActivity).load(imagesList.get(5)).placeholder(R.drawable.bright_kid_bg).error(R.drawable.bright_kid_bg).into(congratulations);

            String imageUrl;
            if(points>0 && points<=4)
                imageUrl = imagesList.get(3);
            else if(points>4 && points<=8)
                imageUrl = imagesList.get(2);
            else
                imageUrl = imagesList.get(1);

            Glide.with(riddlesActivity)
                    .asGif()
                    .load(imageUrl)
                    .into(trophy);

            pointsBanner.setText("You scored " + points + " points!!!");

            player.start();
        }

    }

}
