package com.brightkidmont.brilla.my_bright_brain.visual.colors.purple;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.purple.PurpleActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.purple.PurpleActivity.count;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.purple.PurpleActivity.purpleTab;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.purple.PurpleActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.purple.PurpleActivity.valid;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.purple.PurpleActivity.wrong;

public class Purple7 extends Fragment {

    private LinearLayout llP1, llU, llR, llP2, llL, llE;
    private LinearLayout llP11, llU1, llR1, llP21, llL1, llE1;
    private TextView tvP1, tvU, tvR, tvP2, tvL, tvE;
    View view;

    public Purple7(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.purple7, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Arrange the spelling of the dress color");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q.start();
            }
        });

        ImageView purple = (ImageView) view.findViewById(R.id.purple);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPurple%2Fpr6.png?alt=media&token=7826051d-e50f-48b5-a6c7-fba67a20ded0").error(R.drawable.bright_kid_bg).into(purple);

        llP1 = (LinearLayout) view.findViewById(R.id.llP1);
        llU = (LinearLayout) view.findViewById(R.id.llU);
        llR = (LinearLayout) view.findViewById(R.id.llR);
        llP2 = (LinearLayout) view.findViewById(R.id.llP2);
        llL = (LinearLayout) view.findViewById(R.id.llL);
        llE = (LinearLayout) view.findViewById(R.id.llE);

        llP1.setOnDragListener(new MyDragListener());
        llU.setOnDragListener(new MyDragListener());
        llR.setOnDragListener(new MyDragListener());
        llP2.setOnDragListener(new MyDragListener());
        llL.setOnDragListener(new MyDragListener());
        llE.setOnDragListener(new MyDragListener());

        llP11 = (LinearLayout) view.findViewById(R.id.llP11);
        llU1 = (LinearLayout) view.findViewById(R.id.llU1);
        llR1 = (LinearLayout) view.findViewById(R.id.llR1);
        llP21 = (LinearLayout) view.findViewById(R.id.llP21);
        llL1 = (LinearLayout) view.findViewById(R.id.llL1);
        llE1 = (LinearLayout) view.findViewById(R.id.llE1);

        tvP1 = (TextView) view.findViewById(R.id.tvP1);
        tvU = (TextView) view.findViewById(R.id.tvU);
        tvR = (TextView) view.findViewById(R.id.tvR);
        tvP2 = (TextView) view.findViewById(R.id.tvP2);
        tvL = (TextView) view.findViewById(R.id.tvL);
        tvE = (TextView) view.findViewById(R.id.tvE);

        tvP1.setOnTouchListener(new MyTouchListener());
        tvU.setOnTouchListener(new MyTouchListener());
        tvR.setOnTouchListener(new MyTouchListener());
        tvP2.setOnTouchListener(new MyTouchListener());
        tvL.setOnTouchListener(new MyTouchListener());
        tvE.setOnTouchListener(new MyTouchListener());

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
        llP1.removeAllViews();
        llU.removeAllViews();
        llR.removeAllViews();
        llP2.removeAllViews();
        llL.removeAllViews();
        llE.removeAllViews();

        llP11.removeAllViews();
        llU1.removeAllViews();
        llR1.removeAllViews();
        llP21.removeAllViews();
        llL1.removeAllViews();
        llE1.removeAllViews();
    }

    public void refreshView(){
        llP11.addView(tvP1);
        llU1.addView(tvU);
        llR1.addView(tvR);
        llP21.addView(tvP2);
        llL1.addView(tvL);
        llE1.addView(tvE);

        tvP1.setOnTouchListener(new MyTouchListener());
        tvU.setOnTouchListener(new MyTouchListener());
        tvR.setOnTouchListener(new MyTouchListener());
        tvP2.setOnTouchListener(new MyTouchListener());
        tvL.setOnTouchListener(new MyTouchListener());
        tvE.setOnTouchListener(new MyTouchListener());

        llP1.setOnDragListener(new MyDragListener());
        llU.setOnDragListener(new MyDragListener());
        llR.setOnDragListener(new MyDragListener());
        llP2.setOnDragListener(new MyDragListener());
        llL.setOnDragListener(new MyDragListener());
        llE.setOnDragListener(new MyDragListener());
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
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                default:
                    break;
            }
            return true;
        }

    }

    public void validateValues(){
        if(((ViewGroup)view.findViewById(tvP1.getId()).getParent()).getId() == llP1.getId() || ((ViewGroup)view.findViewById(tvP1.getId()).getParent()).getId() == llP2.getId())
            if(((ViewGroup)view.findViewById(tvU.getId()).getParent()).getId() == llU.getId())
                if(((ViewGroup)view.findViewById(tvR.getId()).getParent()).getId() == llR.getId())
                    if(((ViewGroup)view.findViewById(tvP2.getId()).getParent()).getId() == llP2.getId() || ((ViewGroup)view.findViewById(tvP2.getId()).getParent()).getId() == llP1.getId())
                        if(((ViewGroup)view.findViewById(tvL.getId()).getParent()).getId() == llL.getId())
                            if(((ViewGroup)view.findViewById(tvE.getId()).getParent()).getId() == llE.getId())
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
