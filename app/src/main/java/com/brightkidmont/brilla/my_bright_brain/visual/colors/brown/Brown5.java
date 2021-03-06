package com.brightkidmont.brilla.my_bright_brain.visual.colors.brown;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.brown.BrownActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.brown.BrownActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.brown.BrownActivity.wrong;

public class Brown5 extends Fragment implements View.OnClickListener {

    private ImageView ivbr1, ivbr2, ivbr3, ivbr4, ivbr5, ivbr6;

    public Brown5(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.brown5, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Select brown colored Objects");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.brown));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);

        ImageView br1 = (ImageView) view.findViewById(R.id.br1);
        ImageView br2 = (ImageView) view.findViewById(R.id.br2);
        ImageView br3 = (ImageView) view.findViewById(R.id.br3);
        ImageView br4 = (ImageView) view.findViewById(R.id.br4);
        ImageView br5 = (ImageView) view.findViewById(R.id.br5);
        ImageView br6 = (ImageView) view.findViewById(R.id.br6);

        String image1, image2, image3, image4, image5, image6;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBrown%2Fbr6.png?alt=media&token=ead93110-2525-4382-969e-1177a23a6acf";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBrown%2Fbr7.png?alt=media&token=3fad7061-1a11-45ee-8241-a6d2a3de6d53";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBrown%2Fbr8.png?alt=media&token=0fc164a7-5f36-489d-9bf1-32f13497ef73";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlack%2Fb1.png?alt=media&token=67bef59b-597e-4f4d-bc74-80318332ed15";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBrown%2Fbr10.png?alt=media&token=70cdd61f-783a-42bb-8b77-93d99bc2c021";
        image6 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBrown%2Fbr11.png?alt=media&token=0b70bba6-69ef-4f6f-ac42-cb97986f4cb4";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(br1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(br2);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(br3);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(br4);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(br5);
        Picasso.with(getContext()).load(image6).error(R.drawable.bright_kid_bg).into(br6);

        ivbr1 = (ImageView) view.findViewById(R.id.ivbr1);
        ivbr2 = (ImageView) view.findViewById(R.id.ivbr2);
        ivbr3 = (ImageView) view.findViewById(R.id.ivbr3);
        ivbr4 = (ImageView) view.findViewById(R.id.ivbr4);
        ivbr5 = (ImageView) view.findViewById(R.id.ivbr5);
        ivbr6 = (ImageView) view.findViewById(R.id.ivbr6);

        ivbr1.setVisibility(View.INVISIBLE);
        ivbr2.setVisibility(View.INVISIBLE);
        ivbr3.setVisibility(View.INVISIBLE);
        ivbr4.setVisibility(View.INVISIBLE);
        ivbr5.setVisibility(View.INVISIBLE);
        ivbr6.setVisibility(View.INVISIBLE);

        speaker.setOnClickListener(this);

        br1.setOnClickListener(this);
        br2.setOnClickListener(this);
        br3.setOnClickListener(this);
        br4.setOnClickListener(this);
        br5.setOnClickListener(this);
        br6.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ivbr1.setVisibility(View.INVISIBLE);
                ivbr2.setVisibility(View.INVISIBLE);
                ivbr3.setVisibility(View.INVISIBLE);
                ivbr4.setVisibility(View.INVISIBLE);
                ivbr5.setVisibility(View.INVISIBLE);
                ivbr6.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.br1:
                    correct.start();
                    ivbr1.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.br2:
                    wrong.start();
                    ivbr2.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.br3:
                    correct.start();
                    ivbr3.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.br4:
                    wrong.start();
                    ivbr4.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.br5:
                    correct.start();
                    ivbr5.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.br6:
                    correct.start();
                    ivbr6.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }
}
