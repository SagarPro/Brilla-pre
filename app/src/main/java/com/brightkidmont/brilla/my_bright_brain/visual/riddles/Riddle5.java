package com.brightkidmont.brilla.my_bright_brain.visual.riddles;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.brightkidmont.brilla.R;

import static com.brightkidmont.brilla.my_bright_brain.visual.riddles.RiddlesActivity.points;
import static com.brightkidmont.brilla.my_bright_brain.visual.riddles.RiddlesActivity.submitCount;
import static com.brightkidmont.brilla.my_bright_brain.visual.riddles.RiddlesActivity.tabLayout;

public class Riddle5 extends Fragment {

    private View view;
    public static VideoView vvStory;
    private LinearLayout ll;
    private RadioGroup rg;
    private String submitted = "not";
    private ProgressBar pbStory;
    private TextView play_button;

    public Riddle5(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.riddle5, container, false);

        vvStory = (VideoView) view.findViewById(R.id.vvStory);
        ll = (LinearLayout) view.findViewById(R.id.ll);
        Button btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
        rg = (RadioGroup) view.findViewById(R.id.rg);
        pbStory = (ProgressBar) view.findViewById(R.id.pbStory);
        play_button = (TextView) view.findViewById(R.id.play_button);

        pbStory.setVisibility(View.VISIBLE);
        play_button.setVisibility(View.GONE);

        MediaController controller=new MediaController(getContext());
        controller.setPadding(0, 0, 0, 0);
        vvStory.setMediaController(controller);
        vvStory.requestFocus();

        //url to play video in videoView
        String storyPath = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Stories%2FHare%20And%20Tortoise.mp4?alt=media&token=54d77a86-1cb8-4bac-9b73-a084c8eb492d";
        vvStory.setVideoURI(Uri.parse(storyPath));
        vvStory.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                pbStory.setVisibility(View.GONE);
                play_button.setVisibility(View.VISIBLE);
            }
        });

        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play_button.setVisibility(View.GONE);
                vvStory.start();
            }
        });

        //onCompletion of story display questions
        vvStory.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                vvStory.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Story Completed", Toast.LENGTH_SHORT).show();
                ll.setVisibility(View.VISIBLE);
            }
        });

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
                        if (rb.getText().toString().equals("Tortoise"))
                            points = points+2;
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(submitCount!=5)
                                    tabLayout.getTabAt(0).select();
                                else
                                    tabLayout.getTabAt(5).select();
                            }
                        }, 1000);
                    }
                }
            }
        });

        return view;
    }

}
