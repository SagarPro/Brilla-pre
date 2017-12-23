package com.brightkidmont.brilla.my_bright_brain.visual.visual_discrimination;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.brightkidmont.brilla.R;
import com.squareup.picasso.Picasso;

import static com.brightkidmont.brilla.my_bright_brain.visual.visual_discrimination.VisualDiscriminationActivity.imageList;
import static com.brightkidmont.brilla.my_bright_brain.visual.visual_discrimination.VisualDiscriminationActivity.resultList;

public class VDFragment1 extends Fragment implements View.OnClickListener{

    private ImageView image3;
    private ImageView ivResult;
    private MediaPlayer wrong, correct;

    public VDFragment1(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.visual_discrimination_fragment_layout, container, false);

        correct = MediaPlayer.create(getContext(), R.raw.correct);
        wrong = MediaPlayer.create(getContext(), R.raw.ur_wrong);

        ImageView image1 = (ImageView) view.findViewById(R.id.image1);
        ImageView image2 = (ImageView) view.findViewById(R.id.image2);
        image3 = (ImageView) view.findViewById(R.id.image3);
        ImageView image4 = (ImageView) view.findViewById(R.id.image4);
        ImageView image5 = (ImageView) view.findViewById(R.id.image5);
        ImageView image6 = (ImageView) view.findViewById(R.id.image6);

        ivResult = (ImageView) view.findViewById(R.id.ivResult);

        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        image4.setOnClickListener(this);
        image5.setOnClickListener(this);
        image6.setOnClickListener(this);

        //setting images in imageView
        Picasso.with(getContext()).load(imageList.get(0)).error(R.drawable.bright_kid_bg).into(image1);
        Picasso.with(getContext()).load(imageList.get(0)).error(R.drawable.bright_kid_bg).into(image2);
        Picasso.with(getContext()).load(imageList.get(1)).error(R.drawable.bright_kid_bg).into(image3);
        Picasso.with(getContext()).load(imageList.get(0)).error(R.drawable.bright_kid_bg).into(image4);
        Picasso.with(getContext()).load(imageList.get(0)).error(R.drawable.bright_kid_bg).into(image5);
        Picasso.with(getContext()).load(imageList.get(0)).error(R.drawable.bright_kid_bg).into(image6);

        return view;
    }

    private void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ivResult.setVisibility(View.GONE);
            }
        }, 1000);
    }

    //displaying result
    @Override
    public void onClick(View v) {
        ivResult.setVisibility(View.VISIBLE);
        switch (v.getId()){
            case R.id.image1:
                ivResult.setImageResource(R.drawable.wrong);
                wrong.start();
                break;
            case R.id.image2:
                ivResult.setImageResource(R.drawable.wrong);
                wrong.start();
                break;
            case R.id.image3:
                ivResult.setImageResource(R.drawable.right);
                Picasso.with(getContext()).load(resultList.get(0)).placeholder(image3.getDrawable()).into(image3);
                correct.start();
                break;
            case R.id.image4:
                ivResult.setImageResource(R.drawable.wrong);
                wrong.start();
                break;
            case R.id.image5:
                ivResult.setImageResource(R.drawable.wrong);
                wrong.start();
                break;
            case R.id.image6:
                ivResult.setImageResource(R.drawable.wrong);
                wrong.start();
                break;
        }
        hideView();
    }
}
