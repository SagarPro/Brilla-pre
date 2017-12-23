package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.oval;

import android.content.ClipData;
import android.media.MediaPlayer;
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
import android.widget.Toast;

import com.brightkidmont.brilla.R;
import com.squareup.picasso.Picasso;

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.oval.OvalActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.oval.OvalActivity.count;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.oval.OvalActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.oval.OvalActivity.valid;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.oval.OvalActivity.wrong;

public class Oval3 extends Fragment {

    private View view;
    private LinearLayout llO, llV, llA, llL;
    private LinearLayout llO1, llV1, llA1, llL1;
    private TextView tvO, tvV, tvA, tvL;
    private SwipeRefreshLayout swipeContainer;

    public Oval3(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.oval3, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Can you arrange the letters to spell the word for the shape given below");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!q.isPlaying() && !wrong.isPlaying()) {
                    q.start();
                }
            }
        });

        ImageView oval = (ImageView) view.findViewById(R.id.oval);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Foval.png?alt=media&token=f63dffcc-9cde-4d52-831d-006b8bc32489").error(R.drawable.bright_kid_bg).into(oval);

        llO = (LinearLayout) view.findViewById(R.id.llO);
        llV = (LinearLayout) view.findViewById(R.id.llV);
        llA = (LinearLayout) view.findViewById(R.id.llA);
        llL = (LinearLayout) view.findViewById(R.id.llL);

        llO.setOnDragListener(new MyDragListener());
        llV.setOnDragListener(new MyDragListener());
        llA.setOnDragListener(new MyDragListener());
        llL.setOnDragListener(new MyDragListener());

        llO1 = (LinearLayout) view.findViewById(R.id.llO1);
        llV1 = (LinearLayout) view.findViewById(R.id.llV1);
        llA1 = (LinearLayout) view.findViewById(R.id.llA1);
        llL1 = (LinearLayout) view.findViewById(R.id.llL1);

        tvO = (TextView) view.findViewById(R.id.tvO);
        tvV = (TextView) view.findViewById(R.id.tvV);
        tvA = (TextView) view.findViewById(R.id.tvA);
        tvL = (TextView) view.findViewById(R.id.tvL);

        tvO.setOnTouchListener(new MyTouchListener());
        tvV.setOnTouchListener(new MyTouchListener());
        tvA.setOnTouchListener(new MyTouchListener());
        tvL.setOnTouchListener(new MyTouchListener());

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
        llO.removeAllViews();
        llV.removeAllViews();
        llA.removeAllViews();
        llL.removeAllViews();

        llO1.removeAllViews();
        llV1.removeAllViews();
        llA1.removeAllViews();
        llL1.removeAllViews();
    }

    public void refreshView(){
        llO1.addView(tvO);
        llV1.addView(tvV);
        llA1.addView(tvA);
        llL1.addView(tvL);

        tvO.setOnTouchListener(new MyTouchListener());
        tvV.setOnTouchListener(new MyTouchListener());
        tvA.setOnTouchListener(new MyTouchListener());
        tvL.setOnTouchListener(new MyTouchListener());

        llO.setOnDragListener(new MyDragListener());
        llV.setOnDragListener(new MyDragListener());
        llA.setOnDragListener(new MyDragListener());
        llL.setOnDragListener(new MyDragListener());
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

                    if(count == 4){
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
        if(((View)view.findViewById(tvO.getId()).getParent()).getId() == llO.getId())
            if(((View)view.findViewById(tvV.getId()).getParent()).getId() == llV.getId())
                if(((View)view.findViewById(tvA.getId()).getParent()).getId() == llA.getId())
                    if(((View)view.findViewById(tvL.getId()).getParent()).getId() == llL.getId())
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
