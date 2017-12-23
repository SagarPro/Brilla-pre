package com.brightkidmont.brilla.my_bright_brain.visual.colors.red;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brightkidmont.brilla.R;

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.red.RedActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.red.RedActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.red.RedActivity.wrong;

public class Red5 extends Fragment {

    private TextView tvRed;
    private TextView red;
    private int redId;

    public Red5(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.red5, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Drag the name of the color given below");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_alizarin));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q.start();
            }
        });

        red = (TextView) view.findViewById(R.id.red);
        red.setVisibility(View.INVISIBLE);

        TextView tvBlue = (TextView) view.findViewById(R.id.tvBlue);
        TextView tvGreen = (TextView) view.findViewById(R.id.tvGreen);
        tvRed = (TextView) view.findViewById(R.id.tvRed);
        TextView tvYellow = (TextView) view.findViewById(R.id.tvYellow);

        redId = tvRed.getId();

        tvBlue.setOnTouchListener(new MyTouchListener());
        tvGreen.setOnTouchListener(new MyTouchListener());
        tvRed.setOnTouchListener(new MyTouchListener());
        tvYellow.setOnTouchListener(new MyTouchListener());

        LinearLayout llRed = (LinearLayout) view.findViewById(R.id.llRed);
        llRed.setOnDragListener(new MyDragListener());

        return view;
    }

    public void transition_anim(){
        Animation fade_out = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        fade_out.setDuration(800);
        tvRed.setVisibility(View.GONE);
        red.setVisibility(View.VISIBLE);
        red.startAnimation(fade_out);
    }

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                if(!q.isPlaying() && !wrong.isPlaying()) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                    view.startDrag(data, shadowBuilder, view, 0);
                }
                return true;
            } else {
                return false;
            }
        }
    }

    class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    View view = (View) event.getLocalState();
                    int i = view.getId();
                    if(i == redId) {
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        transition_anim();
                        correct.start();
                    } else {
                        wrong.start();
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                default:
                    break;
            }
            return true;
        }

    }

}
