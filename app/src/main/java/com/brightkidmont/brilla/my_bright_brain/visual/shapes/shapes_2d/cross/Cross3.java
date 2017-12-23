package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.cross;

import android.content.ClipData;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brightkidmont.brilla.R;
import com.squareup.picasso.Picasso;

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.cross.CrossActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.cross.CrossActivity.count;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.cross.CrossActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.cross.CrossActivity.valid;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.cross.CrossActivity.wrong;

public class Cross3 extends Fragment {

    private View view;
    private LinearLayout llC, llR, llO, llS, llS1;
    private LinearLayout llC1, llR1, llO1, llS11, llS12;
    private TextView tvC, tvR, tvO, tvS, tvS1;
    private SwipeRefreshLayout swipeContainer;

    public Cross3(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.cross3, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Can you arrange the letters to spell the word for the shape given below");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!q.isPlaying() && !wrong.isPlaying())
                    q.start();
            }
        });

        ImageView cross = (ImageView) view.findViewById(R.id.cross);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FCross%2Fcross.png?alt=media&token=967ce5f3-91d8-4e28-88c9-92772a359d2f").error(R.drawable.bright_kid_bg).into(cross);

        llC = (LinearLayout) view.findViewById(R.id.llC);
        llR = (LinearLayout) view.findViewById(R.id.llR);
        llO = (LinearLayout) view.findViewById(R.id.llO);
        llS = (LinearLayout) view.findViewById(R.id.llS);
        llS1 = (LinearLayout) view.findViewById(R.id.llS1);

        llC.setOnDragListener(new MyDragListener());
        llR.setOnDragListener(new MyDragListener());
        llO.setOnDragListener(new MyDragListener());
        llS.setOnDragListener(new MyDragListener());
        llS1.setOnDragListener(new MyDragListener());

        llC1 = (LinearLayout) view.findViewById(R.id.llC1);
        llR1 = (LinearLayout) view.findViewById(R.id.llR1);
        llO1 = (LinearLayout) view.findViewById(R.id.llO1);
        llS11 = (LinearLayout) view.findViewById(R.id.llS11);
        llS12 = (LinearLayout) view.findViewById(R.id.llS12);

        tvC = (TextView) view.findViewById(R.id.tvC);
        tvR = (TextView) view.findViewById(R.id.tvR);
        tvO = (TextView) view.findViewById(R.id.tvO);
        tvS = (TextView) view.findViewById(R.id.tvS);
        tvS1 = (TextView) view.findViewById(R.id.tvS1);

        tvC.setOnTouchListener(new MyTouchListener());
        tvR.setOnTouchListener(new MyTouchListener());
        tvO.setOnTouchListener(new MyTouchListener());
        tvS.setOnTouchListener(new MyTouchListener());
        tvS1.setOnTouchListener(new MyTouchListener());

        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeContainer.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        count = 0;
                        valid = "not";
                        removeView();
                        refreshView();
                        swipeContainer.setRefreshing(false);
                    }
                },800);
            }
        });

        return view;
    }

    public void removeView(){
        llC.removeAllViews();
        llR.removeAllViews();
        llO.removeAllViews();
        llS.removeAllViews();
        llS1.removeAllViews();

        llC1.removeAllViews();
        llR1.removeAllViews();
        llO1.removeAllViews();
        llS11.removeAllViews();
        llS12.removeAllViews();
    }

    public void refreshView(){
        llC1.addView(tvC);
        llR1.addView(tvR);
        llO1.addView(tvO);
        llS11.addView(tvS);
        llS12.addView(tvS1);

        tvC.setOnTouchListener(new MyTouchListener());
        tvR.setOnTouchListener(new MyTouchListener());
        tvO.setOnTouchListener(new MyTouchListener());
        tvS.setOnTouchListener(new MyTouchListener());
        tvS1.setOnTouchListener(new MyTouchListener());

        llC.setOnDragListener(new MyDragListener());
        llR.setOnDragListener(new MyDragListener());
        llO.setOnDragListener(new MyDragListener());
        llS.setOnDragListener(new MyDragListener());
        llS1.setOnDragListener(new MyDragListener());
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
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);

                    view.setOnTouchListener(null);
                    v.setOnDragListener(null);

                    count++;
                    if(count == 5){
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
        if(((View)view.findViewById(tvC.getId()).getParent()).getId() == llC.getId())
            if(((View)view.findViewById(tvR.getId()).getParent()).getId() == llR.getId())
                if(((View)view.findViewById(tvO.getId()).getParent()).getId() == llO.getId())
                    if(((View)view.findViewById(tvS.getId()).getParent()).getId() == llS.getId() || ((View)view.findViewById(tvS.getId()).getParent()).getId() == llS1.getId())
                        if(((View)view.findViewById(tvS1.getId()).getParent()).getId() == llS.getId() || ((View)view.findViewById(tvS1.getId()).getParent()).getId() == llS1.getId())
                            valid = "valid";
        validate();
    }

    public void validate(){
        if(valid.equals("valid")) {
            correct.start();
        }
        else
            wrong.start();
    }

}
