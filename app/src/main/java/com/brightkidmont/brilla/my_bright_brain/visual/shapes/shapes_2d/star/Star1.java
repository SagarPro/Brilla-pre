package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.star;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brightkidmont.brilla.R;
import com.squareup.picasso.Picasso;

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.star.StarActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.star.StarActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.star.StarActivity.wrong;

public class Star1 extends Fragment implements View.OnClickListener{

    private MediaPlayer river_sound;
    private ImageView ivCloud1Wrong, ivCloud2Wrong, moonWrong, star1Right, star2Right, star3Right, star4Right, star5Right, square1Wrong, square2Wrong, triangle1Wrong;

    public Star1(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.star1, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Click all stars in the sky");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        river_sound = MediaPlayer.create(getContext(), R.raw.river_sound);
        river_sound.setLooping(true);

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        ImageView ivCloud1 = (ImageView) view.findViewById(R.id.ivCloud1);
        ImageView ivCloud2 = (ImageView) view.findViewById(R.id.ivCloud2);
        ImageView moon = (ImageView) view.findViewById(R.id.moon);
        ImageView star1 = (ImageView) view.findViewById(R.id.star1);
        ImageView star2 = (ImageView) view.findViewById(R.id.star2);
        ImageView star3 = (ImageView) view.findViewById(R.id.star3);
        ImageView star4 = (ImageView) view.findViewById(R.id.star4);
        ImageView star5 = (ImageView) view.findViewById(R.id.star5);
        ImageView square1 = (ImageView) view.findViewById(R.id.square1);
        ImageView square2 = (ImageView) view.findViewById(R.id.square2);
        ImageView triangle1 = (ImageView) view.findViewById(R.id.triangle1);
        ImageView river = (ImageView) view.findViewById(R.id.river);

        String image1, image2, image3, image4, image5, image6;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fcloud.png?alt=media&token=68761af4-1902-417f-a64b-0e874069d9a2";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fmoon.png?alt=media&token=ac135b8d-346c-4b51-9d0d-1bbd92cdf7fb";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fwhite_star.png?alt=media&token=d30b94df-f951-45cb-8f1f-a07b8851833a";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fwhite_square.png?alt=media&token=c93e9d83-6562-4475-9d50-2036bdaeea6a";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fwhite_triangle.png?alt=media&token=b2aee053-16a6-4632-925d-2a92f22f3d11";
        image6 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Friver.jpg?alt=media&token=e573d109-d057-4cbf-b575-f20308db7835";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(ivCloud1);
        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(ivCloud2);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(moon);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(star1);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(star2);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(star3);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(star4);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(star5);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(square1);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(square2);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(triangle1);
        Picasso.with(getContext()).load(image6).error(R.drawable.bright_kid_bg).into(river);

        ivCloud1.setOnClickListener(this);
        ivCloud2.setOnClickListener(this);
        moon.setOnClickListener(this);
        star1.setOnClickListener(this);
        star2.setOnClickListener(this);
        star3.setOnClickListener(this);
        star4.setOnClickListener(this);
        star5.setOnClickListener(this);
        square1.setOnClickListener(this);
        square2.setOnClickListener(this);
        triangle1.setOnClickListener(this);
        river.setOnClickListener(this);

        ivCloud1Wrong = (ImageView) view.findViewById(R.id.ivCloud1Wrong);
        ivCloud2Wrong = (ImageView) view.findViewById(R.id.ivCloud2Wrong);
        moonWrong = (ImageView) view.findViewById(R.id.moonWrong);
        star1Right = (ImageView) view.findViewById(R.id.star1Right);
        star2Right = (ImageView) view.findViewById(R.id.star2Right);
        star3Right = (ImageView) view.findViewById(R.id.star3Right);
        star4Right = (ImageView) view.findViewById(R.id.star4Right);
        star5Right = (ImageView) view.findViewById(R.id.star5Right);
        square1Wrong = (ImageView) view.findViewById(R.id.square1Wrong);
        square2Wrong = (ImageView) view.findViewById(R.id.square2Wrong);
        triangle1Wrong = (ImageView) view.findViewById(R.id.triangle1Wrong);

        ivCloud1Wrong.setVisibility(View.GONE);
        ivCloud2Wrong.setVisibility(View.GONE);
        moonWrong.setVisibility(View.GONE);
        star1Right.setVisibility(View.GONE);
        star2Right.setVisibility(View.GONE);
        star3Right.setVisibility(View.GONE);
        star4Right.setVisibility(View.GONE);
        star5Right.setVisibility(View.GONE);
        square1Wrong.setVisibility(View.GONE);
        square2Wrong.setVisibility(View.GONE);
        triangle1Wrong.setVisibility(View.GONE);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ivCloud1Wrong.setVisibility(View.GONE);
                ivCloud2Wrong.setVisibility(View.GONE);
                moonWrong.setVisibility(View.GONE);
                star1Right.setVisibility(View.GONE);
                star2Right.setVisibility(View.GONE);
                star3Right.setVisibility(View.GONE);
                star4Right.setVisibility(View.GONE);
                star5Right.setVisibility(View.GONE);
                square1Wrong.setVisibility(View.GONE);
                square2Wrong.setVisibility(View.GONE);
                triangle1Wrong.setVisibility(View.GONE);
            }
        }, 1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.ivCloud1:
                    ivCloud1Wrong.setVisibility(View.VISIBLE);
                    wrong.start();
                    hideView();
                    break;
                case R.id.ivCloud2:
                    ivCloud2Wrong.setVisibility(View.VISIBLE);
                    wrong.start();
                    hideView();
                    break;
                case R.id.moon:
                    moonWrong.setVisibility(View.VISIBLE);
                    wrong.start();
                    hideView();
                    break;
                case R.id.star1:
                    star1Right.setVisibility(View.VISIBLE);
                    correct.start();
                    hideView();
                    break;
                case R.id.star2:
                    star2Right.setVisibility(View.VISIBLE);
                    correct.start();
                    hideView();
                    break;
                case R.id.star3:
                    star3Right.setVisibility(View.VISIBLE);
                    correct.start();
                    hideView();
                    break;
                case R.id.star4:
                    star4Right.setVisibility(View.VISIBLE);
                    correct.start();
                    hideView();
                    break;
                case R.id.star5:
                    star5Right.setVisibility(View.VISIBLE);
                    correct.start();
                    hideView();
                    break;
                case R.id.square1:
                    square1Wrong.setVisibility(View.VISIBLE);
                    wrong.start();
                    hideView();
                    break;
                case R.id.square2:
                    square2Wrong.setVisibility(View.VISIBLE);
                    wrong.start();
                    hideView();
                    break;
                case R.id.triangle1:
                    triangle1Wrong.setVisibility(View.VISIBLE);
                    wrong.start();
                    hideView();
                    break;
                case R.id.river:
                    if (river_sound.isPlaying()) {
                        river_sound.pause();
                    } else {
                        river_sound.start();
                    }
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
