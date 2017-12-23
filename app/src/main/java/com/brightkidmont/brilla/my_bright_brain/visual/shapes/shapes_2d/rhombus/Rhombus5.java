package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rhombus;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rhombus.RhombusActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rhombus.RhombusActivity.count;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rhombus.RhombusActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rhombus.RhombusActivity.valid;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rhombus.RhombusActivity.wrong;

public class Rhombus5 extends Fragment {

    private View view;
    private LinearLayout llR, llH, llO, llM, llB, llU, llS;
    private LinearLayout llR1, llH1, llO1, llM1, llB1, llU1, llS1;
    private TextView tvR, tvH, tvO, tvM, tvB, tvU, tvS;
    private SwipeRefreshLayout swipeContainer;

    public Rhombus5(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.rhombus5, container, false);

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

        ImageView rhombus = (ImageView) view.findViewById(R.id.rhombus);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FRhombus%2Frhombus.png?alt=media&token=17e5825c-062b-480a-aa49-2400d1935809").error(R.drawable.bright_kid_bg).into(rhombus);

        llR = (LinearLayout) view.findViewById(R.id.llR);
        llH = (LinearLayout) view.findViewById(R.id.llH);
        llO = (LinearLayout) view.findViewById(R.id.llO);
        llM = (LinearLayout) view.findViewById(R.id.llM);
        llB = (LinearLayout) view.findViewById(R.id.llB);
        llU = (LinearLayout) view.findViewById(R.id.llU);
        llS = (LinearLayout) view.findViewById(R.id.llS);

        llR.setOnDragListener(new MyDragListener());
        llH.setOnDragListener(new MyDragListener());
        llO.setOnDragListener(new MyDragListener());
        llM.setOnDragListener(new MyDragListener());
        llB.setOnDragListener(new MyDragListener());
        llU.setOnDragListener(new MyDragListener());
        llS.setOnDragListener(new MyDragListener());

        llR1 = (LinearLayout) view.findViewById(R.id.llR1);
        llH1 = (LinearLayout) view.findViewById(R.id.llH1);
        llO1 = (LinearLayout) view.findViewById(R.id.llO1);
        llM1 = (LinearLayout) view.findViewById(R.id.llM1);
        llB1 = (LinearLayout) view.findViewById(R.id.llB1);
        llU1 = (LinearLayout) view.findViewById(R.id.llU1);
        llS1 = (LinearLayout) view.findViewById(R.id.llS1);

        tvR = (TextView) view.findViewById(R.id.tvR);
        tvH = (TextView) view.findViewById(R.id.tvH);
        tvO = (TextView) view.findViewById(R.id.tvO);
        tvM = (TextView) view.findViewById(R.id.tvM);
        tvB = (TextView) view.findViewById(R.id.tvB);
        tvU = (TextView) view.findViewById(R.id.tvU);
        tvS = (TextView) view.findViewById(R.id.tvS);

        tvR.setOnTouchListener(new MyTouchListener());
        tvH.setOnTouchListener(new MyTouchListener());
        tvO.setOnTouchListener(new MyTouchListener());
        tvM.setOnTouchListener(new MyTouchListener());
        tvB.setOnTouchListener(new MyTouchListener());
        tvU.setOnTouchListener(new MyTouchListener());
        tvS.setOnTouchListener(new MyTouchListener());

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
        llR.removeAllViews();
        llH.removeAllViews();
        llO.removeAllViews();
        llM.removeAllViews();
        llB.removeAllViews();
        llU.removeAllViews();
        llS.removeAllViews();

        llR1.removeAllViews();
        llH1.removeAllViews();
        llO1.removeAllViews();
        llM1.removeAllViews();
        llB1.removeAllViews();
        llU1.removeAllViews();
        llS1.removeAllViews();
    }

    public void refreshView(){
        llR1.addView(tvR);
        llH1.addView(tvH);
        llO1.addView(tvO);
        llM1.addView(tvM);
        llB1.addView(tvB);
        llU1.addView(tvU);
        llS1.addView(tvS);

        tvR.setOnTouchListener(new MyTouchListener());
        tvH.setOnTouchListener(new MyTouchListener());
        tvO.setOnTouchListener(new MyTouchListener());
        tvM.setOnTouchListener(new MyTouchListener());
        tvB.setOnTouchListener(new MyTouchListener());
        tvU.setOnTouchListener(new MyTouchListener());
        tvS.setOnTouchListener(new MyTouchListener());

        llR.setOnDragListener(new MyDragListener());
        llH.setOnDragListener(new MyDragListener());
        llO.setOnDragListener(new MyDragListener());
        llM.setOnDragListener(new MyDragListener());
        llB.setOnDragListener(new MyDragListener());
        llU.setOnDragListener(new MyDragListener());
        llS.setOnDragListener(new MyDragListener());
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
            int action = event.getAction();
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

                    if(count == 7){
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
        if(((View)view.findViewById(tvR.getId()).getParent()).getId() == llR.getId())
            if(((View)view.findViewById(tvH.getId()).getParent()).getId() == llH.getId())
                if(((View)view.findViewById(tvO.getId()).getParent()).getId() == llO.getId())
                    if(((View)view.findViewById(tvM.getId()).getParent()).getId() == llM.getId())
                        if(((View)view.findViewById(tvB.getId()).getParent()).getId() == llB.getId())
                            if(((View)view.findViewById(tvU.getId()).getParent()).getId() == llU.getId())
                                if(((View)view.findViewById(tvS.getId()).getParent()).getId() == llS.getId())
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
