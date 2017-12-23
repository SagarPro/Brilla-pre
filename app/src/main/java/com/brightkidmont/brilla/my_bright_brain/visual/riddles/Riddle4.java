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
import android.widget.Toast;

import com.brightkidmont.brilla.R;
import com.squareup.picasso.Picasso;

import static com.brightkidmont.brilla.my_bright_brain.visual.riddles.RiddlesActivity.points;
import static com.brightkidmont.brilla.my_bright_brain.visual.riddles.RiddlesActivity.submitCount;
import static com.brightkidmont.brilla.my_bright_brain.visual.riddles.RiddlesActivity.tabLayout;

public class Riddle4 extends Fragment {

    private View view;
    private RadioGroup rg;
    private String submitted = "not";

    public Riddle4(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.riddle4, container, false);

        ImageView ivRiddle4 = (ImageView) view.findViewById(R.id.ivRiddle4);

        Button btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rg = (RadioGroup) view.findViewById(R.id.rg);

        //setting image from url
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Logical%20Thinking%2FRiddles%2Fapple_tree.png?alt=media&token=496e8964-0a16-4e7e-832f-ebf9fd8b1208").error(R.drawable.bright_kid_bg).into(ivRiddle4);

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
                        if (rb.getText().toString().equals("4"))
                            points = points+2;
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                tabLayout.getTabAt(4).select();
                            }
                        }, 1000);
                    }
                }
            }
        });

        return view;
    }

}
