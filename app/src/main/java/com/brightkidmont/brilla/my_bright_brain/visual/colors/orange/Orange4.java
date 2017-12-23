package com.brightkidmont.brilla.my_bright_brain.visual.colors.orange;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.orange.OrangeActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.orange.OrangeActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.orange.OrangeActivity.wrong;

public class Orange4 extends Fragment implements View.OnClickListener{

    private TextView tvOrange;
    private TextView orange;
    private int orangeId;

    public Orange4(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.orange4, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Drag the name of the color given below");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_carrot));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        orange = (TextView) view.findViewById(R.id.orange);
        orange.setVisibility(View.INVISIBLE);

        TextView tvBlue = (TextView) view.findViewById(R.id.tvBlue);
        tvOrange = (TextView) view.findViewById(R.id.tvOrange);
        TextView tvRed = (TextView) view.findViewById(R.id.tvRed);
        TextView tvYellow = (TextView) view.findViewById(R.id.tvYellow);

        orangeId = tvOrange.getId();

        tvBlue.setOnTouchListener(new MyTouchListener());
        tvOrange.setOnTouchListener(new MyTouchListener());
        tvRed.setOnTouchListener(new MyTouchListener());
        tvYellow.setOnTouchListener(new MyTouchListener());

        LinearLayout llOrange = (LinearLayout) view.findViewById(R.id.llOrange);
        llOrange.setOnDragListener(new MyDragListener());

        return view;

    }

    public void transition_anim(){
        Animation fade_out = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        fade_out.setDuration(800);
        tvOrange.setVisibility(View.GONE);
        orange.setVisibility(View.VISIBLE);
        orange.startAnimation(fade_out);
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
                    if(i == orangeId) {
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.speaker:
                q.start();
                break;
        }
    }
}
