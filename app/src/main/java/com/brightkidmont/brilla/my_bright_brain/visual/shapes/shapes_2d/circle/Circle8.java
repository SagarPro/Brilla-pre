package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.circle;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.circle.CircleActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.circle.CircleActivity.count;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.circle.CircleActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.circle.CircleActivity.valid;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.circle.CircleActivity.wrong;

public class Circle8 extends Fragment {

    private View view;
    private LinearLayout llC, llI, llR, llC2, llL, llE;
    private LinearLayout llC1, llI1, llR1, llC12, llL1, llE1;
    private TextView tvC, tvI, tvR, tvC2, tvL, tvE;
    private SwipeRefreshLayout swipeContainer;

    public Circle8(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.circle8, container, false);

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

        ImageView circle = (ImageView) view.findViewById(R.id.circle);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Fcircle.png?alt=media&token=23e0cf7e-fb40-4f89-b796-24b75c9d2a31").error(R.drawable.bright_kid_bg).into(circle);

        llC = (LinearLayout) view.findViewById(R.id.llC);//2131755332
        llI = (LinearLayout) view.findViewById(R.id.llI);
        llR = (LinearLayout) view.findViewById(R.id.llR);
        llC2 = (LinearLayout) view.findViewById(R.id.llC2);
        llL = (LinearLayout) view.findViewById(R.id.llL);
        llE = (LinearLayout) view.findViewById(R.id.llE);

        llC.setOnDragListener(new MyDragListener());
        llI.setOnDragListener(new MyDragListener());
        llR.setOnDragListener(new MyDragListener());
        llC2.setOnDragListener(new MyDragListener());
        llL.setOnDragListener(new MyDragListener());
        llE.setOnDragListener(new MyDragListener());

        tvC = (TextView) view.findViewById(R.id.tvC);
        tvI = (TextView) view.findViewById(R.id.tvI);
        tvR = (TextView) view.findViewById(R.id.tvR);
        tvC2 = (TextView) view.findViewById(R.id.tvC2);
        tvL = (TextView) view.findViewById(R.id.tvL);
        tvE = (TextView) view.findViewById(R.id.tvE);

        tvC.setOnTouchListener(new MyTouchListener());
        tvI.setOnTouchListener(new MyTouchListener());
        tvR.setOnTouchListener(new MyTouchListener());
        tvC2.setOnTouchListener(new MyTouchListener());
        tvL.setOnTouchListener(new MyTouchListener());
        tvE.setOnTouchListener(new MyTouchListener());

        llC1 = (LinearLayout) view.findViewById(R.id.llC1);
        llI1 = (LinearLayout) view.findViewById(R.id.llI1);
        llR1 = (LinearLayout) view.findViewById(R.id.llR1);
        llC12 = (LinearLayout) view.findViewById(R.id.llC12);
        llL1 = (LinearLayout) view.findViewById(R.id.llL1);
        llE1 = (LinearLayout) view.findViewById(R.id.llE1);

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

    @Override
    public void onStart() {
        super.onStart();
        removeView();
        refreshView();
    }

    //Removing all views in linearLayout when refreshed
    public void removeView(){
        llC.removeAllViews();
        llI.removeAllViews();
        llR.removeAllViews();
        llC2.removeAllViews();
        llL.removeAllViews();
        llE.removeAllViews();

        llC1.removeAllViews();
        llI1.removeAllViews();
        llR1.removeAllViews();
        llC12.removeAllViews();
        llL1.removeAllViews();
        llE1.removeAllViews();
    }

    public void refreshView(){
        llC1.addView(tvC);
        llI1.addView(tvI);
        llR1.addView(tvR);
        llC12.addView(tvC2);
        llL1.addView(tvL);
        llE1.addView(tvE);

        tvC.setOnTouchListener(new MyTouchListener());
        tvI.setOnTouchListener(new MyTouchListener());
        tvR.setOnTouchListener(new MyTouchListener());
        tvC2.setOnTouchListener(new MyTouchListener());
        tvL.setOnTouchListener(new MyTouchListener());
        tvE.setOnTouchListener(new MyTouchListener());

        llC.setOnDragListener(new MyDragListener());
        llI.setOnDragListener(new MyDragListener());
        llR.setOnDragListener(new MyDragListener());
        llC2.setOnDragListener(new MyDragListener());
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
                    int i = view.getId();
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

    //Validating spellings for circle
    public void validateValues(){
        if(((ViewGroup)view.findViewById(tvC.getId()).getParent()).getId() == llC.getId() || ((ViewGroup)view.findViewById(tvC.getId()).getParent()).getId() == llC2.getId())
            if(((ViewGroup)view.findViewById(tvI.getId()).getParent()).getId() == llI.getId())
                if(((ViewGroup)view.findViewById(tvR.getId()).getParent()).getId() == llR.getId())
                    if(((ViewGroup)view.findViewById(tvC2.getId()).getParent()).getId() == llC.getId() || ((ViewGroup)view.findViewById(tvC2.getId()).getParent()).getId() == llC2.getId())
                        if(((ViewGroup)view.findViewById(tvL.getId()).getParent()).getId() == llL.getId())
                            if(((ViewGroup)view.findViewById(tvE.getId()).getParent()).getId() == llE.getId()){
                                valid = "valid";
                            }
        validate();
    }

    //Display validated result
    public void validate(){
        if(valid.equals("valid"))
            correct.start();
        else
            wrong.start();
    }

}
