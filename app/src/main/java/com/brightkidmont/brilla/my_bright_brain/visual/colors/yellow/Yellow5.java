package com.brightkidmont.brilla.my_bright_brain.visual.colors.yellow;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.brightkidmont.brilla.R;
import com.squareup.picasso.Picasso;

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.yellow.YellowActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.yellow.YellowActivity.count;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.yellow.YellowActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.yellow.YellowActivity.valid;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.yellow.YellowActivity.wrong;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.yellow.YellowActivity.yellowTab;

public class Yellow5 extends Fragment {

    private LinearLayout llY, llE, llL, llL1, llO, llW;
    private LinearLayout llY1, llE1, llL2, llL12, llO1, llW1;
    private TextView tvY, tvE, tvL, tvL1, tvO, tvW;
    View view;

    public Yellow5(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.yellow5, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Arrange the spelling for sunflower petals color");
        flash_header.setTextColor(getResources().getColor(R.color.f_black));

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_sun_flower));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setImageResource(R.drawable.speaker_b);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q.start();
            }
        });

        ImageView yellow = (ImageView) view.findViewById(R.id.yellow);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FYellow%2Fy8.png?alt=media&token=062ac63e-e501-406d-b7f8-a86099c62bdf").error(R.drawable.bright_kid_bg).into(yellow);

        llY = (LinearLayout) view.findViewById(R.id.llY);
        llE = (LinearLayout) view.findViewById(R.id.llE);
        llL = (LinearLayout) view.findViewById(R.id.llL);
        llL1 = (LinearLayout) view.findViewById(R.id.llL1);
        llO = (LinearLayout) view.findViewById(R.id.llO);
        llW = (LinearLayout) view.findViewById(R.id.llW);

        llY.setOnDragListener(new MyDragListener());
        llE.setOnDragListener(new MyDragListener());
        llL.setOnDragListener(new MyDragListener());
        llL1.setOnDragListener(new MyDragListener());
        llO.setOnDragListener(new MyDragListener());
        llW.setOnDragListener(new MyDragListener());

        llY1 = (LinearLayout) view.findViewById(R.id.llY1);
        llE1 = (LinearLayout) view.findViewById(R.id.llE1);
        llL2 = (LinearLayout) view.findViewById(R.id.llL2);
        llL12 = (LinearLayout) view.findViewById(R.id.llL12);
        llO1 = (LinearLayout) view.findViewById(R.id.llO1);
        llW1 = (LinearLayout) view.findViewById(R.id.llW1);

        tvY = (TextView) view.findViewById(R.id.tvY);
        tvE = (TextView) view.findViewById(R.id.tvE);
        tvL = (TextView) view.findViewById(R.id.tvL);
        tvL1 = (TextView) view.findViewById(R.id.tvL1);
        tvO = (TextView) view.findViewById(R.id.tvO);
        tvW = (TextView) view.findViewById(R.id.tvW);

        tvY.setOnTouchListener(new MyTouchListener());
        tvE.setOnTouchListener(new MyTouchListener());
        tvL.setOnTouchListener(new MyTouchListener());
        tvL1.setOnTouchListener(new MyTouchListener());
        tvO.setOnTouchListener(new MyTouchListener());
        tvW.setOnTouchListener(new MyTouchListener());

        Button refresh = (Button) view.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                valid = "not";
                removeView();
                refreshView();
            }
        });

        return view;
    }

    public void removeView(){
        llY.removeAllViews();
        llE.removeAllViews();
        llL.removeAllViews();
        llL1.removeAllViews();
        llO.removeAllViews();
        llW.removeAllViews();

        llY1.removeAllViews();
        llE1.removeAllViews();
        llL2.removeAllViews();
        llL12.removeAllViews();
        llO1.removeAllViews();
        llW1.removeAllViews();
    }

    public void refreshView(){
        llY1.addView(tvY);
        llE1.addView(tvE);
        llL2.addView(tvL);
        llL12.addView(tvL1);
        llO1.addView(tvO);
        llW1.addView(tvW);

        tvY.setOnTouchListener(new MyTouchListener());
        tvE.setOnTouchListener(new MyTouchListener());
        tvL.setOnTouchListener(new MyTouchListener());
        tvL1.setOnTouchListener(new MyTouchListener());
        tvO.setOnTouchListener(new MyTouchListener());
        tvW.setOnTouchListener(new MyTouchListener());

        llY.setOnDragListener(new MyDragListener());
        llE.setOnDragListener(new MyDragListener());
        llL.setOnDragListener(new MyDragListener());
        llL1.setOnDragListener(new MyDragListener());
        llO.setOnDragListener(new MyDragListener());
        llW.setOnDragListener(new MyDragListener());

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
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);

                    view.setOnTouchListener(null);
                    v.setOnDragListener(null);

                    count++;

                    if(count == 6){
                        validateValues();
                    }
                    //correct.start();
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                default:
                    break;
            }
            return true;
        }

    }

    public void validateValues(){
        if(((ViewGroup)view.findViewById(tvY.getId()).getParent()).getId() == llY.getId())
            if(((ViewGroup)view.findViewById(tvE.getId()).getParent()).getId() == llE.getId())
                if(((ViewGroup)view.findViewById(tvL.getId()).getParent()).getId() == llL.getId() || ((ViewGroup)view.findViewById(tvL.getId()).getParent()).getId() == llL1.getId())
                    if(((ViewGroup)view.findViewById(tvL1.getId()).getParent()).getId() == llL1.getId() || ((ViewGroup)view.findViewById(tvL1.getId()).getParent()).getId() == llL.getId())
                        if(((ViewGroup)view.findViewById(tvO.getId()).getParent()).getId() == llO.getId())
                            if(((ViewGroup)view.findViewById(tvW.getId()).getParent()).getId() == llW.getId())
                                valid = "valid";
        validate();
    }

    public void validate(){
        if(valid.equals("valid")) {
            correct.start();
            Toast.makeText(getContext(), "Correct", Toast.LENGTH_SHORT).show();
        }
        else
            wrong.start();
    }

}
