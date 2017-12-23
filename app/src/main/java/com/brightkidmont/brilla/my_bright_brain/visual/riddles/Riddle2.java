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

import static com.brightkidmont.brilla.my_bright_brain.visual.riddles.RiddlesActivity.points;
import static com.brightkidmont.brilla.my_bright_brain.visual.riddles.RiddlesActivity.submitCount;
import static com.brightkidmont.brilla.my_bright_brain.visual.riddles.RiddlesActivity.tabLayout;

public class Riddle2 extends Fragment implements View.OnClickListener{

    private View view;
    private RadioGroup rg1, rg2, rg3, rg4;
    private int count = 0;
    private String submitted = "not";

    public Riddle2(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.riddle2, container, false);

        rg1 = (RadioGroup) view.findViewById(R.id.rg1);
        rg2 = (RadioGroup) view.findViewById(R.id.rg2);
        rg3 = (RadioGroup) view.findViewById(R.id.rg3);
        rg4 = (RadioGroup) view.findViewById(R.id.rg4);

        ImageView speaker1 = (ImageView) view.findViewById(R.id.speaker1);
        ImageView speaker2 = (ImageView) view.findViewById(R.id.speaker2);
        ImageView speaker3 = (ImageView) view.findViewById(R.id.speaker3);
        ImageView speaker4 = (ImageView) view.findViewById(R.id.speaker4);

        speaker1.setOnClickListener(this);
        speaker2.setOnClickListener(this);
        speaker3.setOnClickListener(this);
        speaker4.setOnClickListener(this);

        Button btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

        return view;
    }

    //assigning point based on correct answer
    private void validate(){
        submitCount++;
        submitted = "submit";
        if(count>0 && count<3)
            points++;
        else if(count==4 || count==3)
            points = points+2;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tabLayout.getTabAt(2).select();
            }
        }, 1000);
    }

    //verifying the answer and non-answered questions
    @Override
    public void onClick(View v) {
        int id;
        RadioButton rb;
        switch (v.getId()){
            case R.id.btnSubmit:
                if(submitted.equals("submit")){
                    Toast.makeText(getContext(), "You already answered these questions", Toast.LENGTH_SHORT).show();
                } else {
                    if(rg1.getCheckedRadioButtonId() == -1
                            || rg2.getCheckedRadioButtonId() == -1
                            || rg3.getCheckedRadioButtonId() == -1
                            || rg4.getCheckedRadioButtonId() == -1){
                        Toast.makeText(getContext(), "Please select one answer from each question before submitting", Toast.LENGTH_SHORT).show();
                    } else {
                        id = rg1.getCheckedRadioButtonId();
                        rb = (RadioButton) view.findViewById(id);
                        if (rb.getText().toString().equals("Seven"))
                            count++;
                        id = rg2.getCheckedRadioButtonId();
                        rb = (RadioButton) view.findViewById(id);
                        if (rb.getText().toString().equals("12"))
                            count++;
                        id = rg3.getCheckedRadioButtonId();
                        rb = (RadioButton) view.findViewById(id);
                        if (rb.getText().toString().equals("Your Name"))
                            count++;
                        id = rg4.getCheckedRadioButtonId();
                        rb = (RadioButton) view.findViewById(id);
                        if (rb.getText().toString().equals("FootPrints"))
                            count++;
                        validate();
                    }

                }
                break;
            case R.id.speaker1:
                break;
            case R.id.speaker2:
                break;
            case R.id.speaker3:
                break;
            case R.id.speaker4:
                break;
        }
    }
}
