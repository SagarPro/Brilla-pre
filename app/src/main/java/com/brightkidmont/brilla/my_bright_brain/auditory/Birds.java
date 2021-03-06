package com.brightkidmont.brilla.my_bright_brain.auditory;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.brightkidmont.brilla.R;
import com.brightkidmont.brilla.adapter.AuditoryAdapter;
import com.bumptech.glide.Glide;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;
import java.util.Arrays;

import static com.brightkidmont.brilla.my_bright_brain.auditory.AuditoryActivity.player;
import static com.brightkidmont.brilla.my_bright_brain.auditory.AuditoryActivity.stopAudio;

public class Birds extends Fragment implements TextToSpeech.OnInitListener {

    AuditoryAdapter adapter;
    TwoWayView twoWayView;
    ImageView imageGif;
    ArrayList<String> imagesList, gifList;
    private TextToSpeech tts;

    public Birds(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.auditory_fragment_layout, container, false);

        stopAudio();
        tts = new TextToSpeech(getContext(), this);
        imagesList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.birds)));
        //array to store bird names
        final String[] nameList = {"parrot", "crow", "pigeon", "duck", "hen", "peacock", "owl"};

        twoWayView = (TwoWayView) view.findViewById(R.id.horizontalListView);
        twoWayView.setAdapter( adapter = new AuditoryAdapter(getContext(), imagesList, nameList));

        //list to store gif
        gifList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.birds_gif)));
        //array to store audio files
        final int[] audioList = {R.raw.parrot, R.raw.crow, R.raw.pigeon, R.raw.duck, R.raw.hen, R.raw.peacock, R.raw.owl};

        imageGif = (ImageView) view.findViewById(R.id.imageGif);

        //play respective gif and audio onClick
        twoWayView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                imageGif.setVisibility(View.VISIBLE);
                Glide.with(Birds.this)
                        .asGif()
                        .load(gifList.get(position))
                        .into(imageGif);
                stopAudio();
                tts.speak(nameList[position], TextToSpeech.QUEUE_FLUSH, null);
                player = MediaPlayer.create(getContext(),audioList[position]);
                player.setLooping(true);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        player.start();
                    }
                }, 500);
            }
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        stopAudio();
        imageGif.setVisibility(View.GONE);
    }

    @Override
    public void onInit(int status) {
    }
}
