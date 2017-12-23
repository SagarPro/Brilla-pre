package com.brightkidmont.brilla.my_bright_brain.visual.colors.grey;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.grey.GreyActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.grey.GreyActivity.count;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.grey.GreyActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.grey.GreyActivity.valid;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.grey.GreyActivity.wrong;

public class Grey5 extends Fragment {

    private LinearLayout llG, llR, llE, llY;
    private LinearLayout llG1, llR1, llE1, llY1;
    private TextView tvG, tvR, tvE, tvY;
    View view;

    public Grey5(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.grey5, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Arrange the spelling for the color of Tom cat");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.f_grey));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q.start();
            }
        });

        ImageView grey = (ImageView) view.findViewById(R.id.grey);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FGrey%2Fgr9.png?alt=media&token=dab1ac0d-8946-40f3-97b3-da65591009f2").error(R.drawable.bright_kid_bg).into(grey);

        llG = (LinearLayout) view.findViewById(R.id.llG);
        llR = (LinearLayout) view.findViewById(R.id.llR);
        llE = (LinearLayout) view.findViewById(R.id.llE);
        llY = (LinearLayout) view.findViewById(R.id.llY);

        llG.setOnDragListener(new MyDragListener());
        llR.setOnDragListener(new MyDragListener());
        llE.setOnDragListener(new MyDragListener());
        llY.setOnDragListener(new MyDragListener());

        llG1 = (LinearLayout) view.findViewById(R.id.llG1);
        llR1 = (LinearLayout) view.findViewById(R.id.llR1);
        llE1 = (LinearLayout) view.findViewById(R.id.llE1);
        llY1 = (LinearLayout) view.findViewById(R.id.llY1);

        tvG = (TextView) view.findViewById(R.id.tvG);
        tvR = (TextView) view.findViewById(R.id.tvR);
        tvE = (TextView) view.findViewById(R.id.tvE);
        tvY = (TextView) view.findViewById(R.id.tvY);

        tvG.setOnTouchListener(new MyTouchListener());
        tvR.setOnTouchListener(new MyTouchListener());
        tvE.setOnTouchListener(new MyTouchListener());
        tvY.setOnTouchListener(new MyTouchListener());

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
        llG.removeAllViews();
        llR.removeAllViews();
        llE.removeAllViews();
        llY.removeAllViews();

        llG1.removeAllViews();
        llR1.removeAllViews();
        llE1.removeAllViews();
        llY1.removeAllViews();
    }

    public void refreshView(){
        llG1.addView(tvG);
        llR1.addView(tvR);
        llE1.addView(tvE);
        llY1.addView(tvY);

        tvG.setOnTouchListener(new MyTouchListener());
        tvR.setOnTouchListener(new MyTouchListener());
        tvE.setOnTouchListener(new MyTouchListener());
        tvY.setOnTouchListener(new MyTouchListener());

        llG.setOnDragListener(new MyDragListener());
        llR.setOnDragListener(new MyDragListener());
        llE.setOnDragListener(new MyDragListener());
        llY.setOnDragListener(new MyDragListener());
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
                    if(count == 4){
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
        if(((ViewGroup)view.findViewById(tvG.getId()).getParent()).getId() == llG.getId())
            if(((ViewGroup)view.findViewById(tvR.getId()).getParent()).getId() == llR.getId())
                if(((ViewGroup)view.findViewById(tvE.getId()).getParent()).getId() == llE.getId())
                    if(((ViewGroup)view.findViewById(tvY.getId()).getParent()).getId() == llY.getId())
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
