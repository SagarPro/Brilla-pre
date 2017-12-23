package com.brightkidmont.brilla.my_bright_brain.visual.riddles;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.brightkidmont.brilla.R;
import com.squareup.picasso.Picasso;

import static com.brightkidmont.brilla.my_bright_brain.visual.riddles.RiddlesActivity.points;
import static com.brightkidmont.brilla.my_bright_brain.visual.riddles.RiddlesActivity.submitCount;
import static com.brightkidmont.brilla.my_bright_brain.visual.riddles.RiddlesActivity.tabLayout;

public class Riddle3 extends Fragment {

    private View view;
    private RadioGroup rg;
    private String submitted = "not";

    public Riddle3(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.riddle3, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Who will go all the way to home?");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_midnight_blue));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ImageView ivRiddle3 = (ImageView) view.findViewById(R.id.ivRiddle3);

        Button btnSubmit = (Button) view.findViewById(R.id.btnSubmit);

        rg = (RadioGroup) view.findViewById(R.id.rg);

        //setting image from url
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Logical%20Thinking%2FRiddles%2Friddle3.png?alt=media&token=7dd237f1-fada-4abc-9d6d-4ee59ea98ecd").error(R.drawable.bright_kid_bg).into(ivRiddle3);

        //validating answer
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id;
                RadioButton rb;
                if (submitted.equals("submit")) {
                    Toast.makeText(getContext(), "You already answered this riddle", Toast.LENGTH_SHORT).show();
                } else {
                    if (rg.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(getContext(), "Please select one answer from above options before submitting", Toast.LENGTH_SHORT).show();
                    } else {
                        submitCount++;
                        submitted = "submit";
                        id = rg.getCheckedRadioButtonId();
                        rb = (RadioButton) view.findViewById(id);
                        if (rb.getText().toString().equals("Horse"))
                            points = points+2;
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                tabLayout.getTabAt(3).select();
                            }
                        }, 1000);
                    }
                }
            }
        });

        return view;
    }
}
