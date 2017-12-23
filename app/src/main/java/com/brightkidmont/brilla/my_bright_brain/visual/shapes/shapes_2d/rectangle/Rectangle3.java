package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rectangle;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rectangle.RectangleActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rectangle.RectangleActivity.count;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rectangle.RectangleActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rectangle.RectangleActivity.valid;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rectangle.RectangleActivity.wrong;

public class Rectangle3 extends Fragment {

    private View view;
    private LinearLayout llR, llE, llC, llT, llA, llN, llG, llL, llE1;
    private LinearLayout llR1, llE11, llC1, llT1, llA1, llN1, llG1, llL1, llE12;
    private TextView tvR, tvE, tvC, tvT, tvA, tvN, tvG, tvL, tvE1;
    private SwipeRefreshLayout swipeContainer;

    public Rectangle3(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.rectangle3, container, false);

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

        ImageView rectangle = (ImageView) view.findViewById(R.id.rectangle);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Frectangle.png?alt=media&token=dd5b0c32-109f-46f1-866d-ee0eea4a31d6").error(R.drawable.bright_kid_bg).into(rectangle);

        llR = (LinearLayout) view.findViewById(R.id.llR);
        llE = (LinearLayout) view.findViewById(R.id.llE);
        llC = (LinearLayout) view.findViewById(R.id.llC);
        llT = (LinearLayout) view.findViewById(R.id.llT);
        llA = (LinearLayout) view.findViewById(R.id.llA);
        llN = (LinearLayout) view.findViewById(R.id.llN);
        llG = (LinearLayout) view.findViewById(R.id.llG);
        llL = (LinearLayout) view.findViewById(R.id.llL);
        llE1 = (LinearLayout) view.findViewById(R.id.llE1);

        llR.setOnDragListener(new MyDragListener());
        llE.setOnDragListener(new MyDragListener());
        llC.setOnDragListener(new MyDragListener());
        llT.setOnDragListener(new MyDragListener());
        llA.setOnDragListener(new MyDragListener());
        llN.setOnDragListener(new MyDragListener());
        llG.setOnDragListener(new MyDragListener());
        llL.setOnDragListener(new MyDragListener());
        llE1.setOnDragListener(new MyDragListener());

        llR1 = (LinearLayout) view.findViewById(R.id.llR1);
        llE11 = (LinearLayout) view.findViewById(R.id.llE11);
        llC1 = (LinearLayout) view.findViewById(R.id.llC1);
        llT1 = (LinearLayout) view.findViewById(R.id.llT1);
        llA1 = (LinearLayout) view.findViewById(R.id.llA1);
        llN1 = (LinearLayout) view.findViewById(R.id.llN1);
        llG1 = (LinearLayout) view.findViewById(R.id.llG1);
        llL1 = (LinearLayout) view.findViewById(R.id.llL1);
        llE12 = (LinearLayout) view.findViewById(R.id.llE12);

        tvR = (TextView) view.findViewById(R.id.tvR);
        tvE = (TextView) view.findViewById(R.id.tvE);
        tvC = (TextView) view.findViewById(R.id.tvC);
        tvT = (TextView) view.findViewById(R.id.tvT);
        tvA = (TextView) view.findViewById(R.id.tvA);
        tvN = (TextView) view.findViewById(R.id.tvN);
        tvG = (TextView) view.findViewById(R.id.tvG);
        tvL = (TextView) view.findViewById(R.id.tvL);
        tvE1 = (TextView) view.findViewById(R.id.tvE1);

        tvR.setOnTouchListener(new MyTouchListener());
        tvE.setOnTouchListener(new MyTouchListener());
        tvC.setOnTouchListener(new MyTouchListener());
        tvT.setOnTouchListener(new MyTouchListener());
        tvA.setOnTouchListener(new MyTouchListener());
        tvN.setOnTouchListener(new MyTouchListener());
        tvG.setOnTouchListener(new MyTouchListener());
        tvL.setOnTouchListener(new MyTouchListener());
        tvE1.setOnTouchListener(new MyTouchListener());

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
        llE.removeAllViews();
        llC.removeAllViews();
        llT.removeAllViews();
        llA.removeAllViews();
        llN.removeAllViews();
        llG.removeAllViews();
        llL.removeAllViews();
        llE1.removeAllViews();

        llR1.removeAllViews();
        llE11.removeAllViews();
        llC1.removeAllViews();
        llT1.removeAllViews();
        llA1.removeAllViews();
        llN1.removeAllViews();
        llG1.removeAllViews();
        llL1.removeAllViews();
        llE12.removeAllViews();
    }

    public void refreshView(){
        llR1.addView(tvR);
        llE11.addView(tvE);
        llC1.addView(tvC);
        llT1.addView(tvT);
        llA1.addView(tvA);
        llN1.addView(tvN);
        llG1.addView(tvG);
        llL1.addView(tvL);
        llE12.addView(tvE1);

        tvR.setOnTouchListener(new MyTouchListener());
        tvE.setOnTouchListener(new MyTouchListener());
        tvC.setOnTouchListener(new MyTouchListener());
        tvT.setOnTouchListener(new MyTouchListener());
        tvA.setOnTouchListener(new MyTouchListener());
        tvN.setOnTouchListener(new MyTouchListener());
        tvG.setOnTouchListener(new MyTouchListener());
        tvL.setOnTouchListener(new MyTouchListener());
        tvE1.setOnTouchListener(new MyTouchListener());

        llR.setOnDragListener(new MyDragListener());
        llE.setOnDragListener(new MyDragListener());
        llC.setOnDragListener(new MyDragListener());
        llT.setOnDragListener(new MyDragListener());
        llA.setOnDragListener(new MyDragListener());
        llN.setOnDragListener(new MyDragListener());
        llG.setOnDragListener(new MyDragListener());
        llL.setOnDragListener(new MyDragListener());
        llE1.setOnDragListener(new MyDragListener());
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

                    if(count == 9){
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
            if(((View)view.findViewById(tvE.getId()).getParent()).getId() == llE.getId() || ((View)view.findViewById(tvE.getId()).getParent()).getId() == llE1.getId())
                if(((View)view.findViewById(tvC.getId()).getParent()).getId() == llC.getId())
                    if(((View)view.findViewById(tvT.getId()).getParent()).getId() == llT.getId())
                        if(((View)view.findViewById(tvA.getId()).getParent()).getId() == llA.getId())
                            if(((View)view.findViewById(tvN.getId()).getParent()).getId() == llN.getId())
                                if(((View)view.findViewById(tvG.getId()).getParent()).getId() == llG.getId())
                                    if(((View)view.findViewById(tvL.getId()).getParent()).getId() == llL.getId())
                                        if(((View)view.findViewById(tvE1.getId()).getParent()).getId() == llE.getId() || ((View)view.findViewById(tvE1.getId()).getParent()).getId() == llE1.getId())
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
