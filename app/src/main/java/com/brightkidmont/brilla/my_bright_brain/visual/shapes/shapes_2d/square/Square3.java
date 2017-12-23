package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.square;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.square.SquareActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.square.SquareActivity.count;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.square.SquareActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.square.SquareActivity.valid;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.square.SquareActivity.wrong;

public class Square3 extends Fragment {

    private View view;
    private LinearLayout llS, llQ, llU, llA, llR, llE;
    private LinearLayout llS1, llQ1, llU1, llA1, llR1, llE1;
    private TextView tvS, tvQ, tvU, tvA, tvR, tvE;
    private SwipeRefreshLayout swipeContainer;

    public Square3(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.square3, container, false);

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

        ImageView square = (ImageView) view.findViewById(R.id.square);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Fsquare.png?alt=media&token=6924460b-2faf-4e93-9fef-6d44cf90463c").error(R.drawable.bright_kid_bg).into(square);

        llS = (LinearLayout) view.findViewById(R.id.llS);
        llQ = (LinearLayout) view.findViewById(R.id.llQ);
        llU = (LinearLayout) view.findViewById(R.id.llU);
        llA = (LinearLayout) view.findViewById(R.id.llA);
        llR = (LinearLayout) view.findViewById(R.id.llR);
        llE = (LinearLayout) view.findViewById(R.id.llE);

        llS.setOnDragListener(new MyDragListener());
        llQ.setOnDragListener(new MyDragListener());
        llU.setOnDragListener(new MyDragListener());
        llA.setOnDragListener(new MyDragListener());
        llR.setOnDragListener(new MyDragListener());
        llE.setOnDragListener(new MyDragListener());

        llS1 = (LinearLayout) view.findViewById(R.id.llS1);
        llQ1 = (LinearLayout) view.findViewById(R.id.llQ1);
        llU1 = (LinearLayout) view.findViewById(R.id.llU1);
        llA1 = (LinearLayout) view.findViewById(R.id.llA1);
        llR1 = (LinearLayout) view.findViewById(R.id.llR1);
        llE1 = (LinearLayout) view.findViewById(R.id.llE1);

        tvS = (TextView) view.findViewById(R.id.tvS);
        tvQ = (TextView) view.findViewById(R.id.tvQ);
        tvU = (TextView) view.findViewById(R.id.tvU);
        tvA = (TextView) view.findViewById(R.id.tvA);
        tvR = (TextView) view.findViewById(R.id.tvR);
        tvE = (TextView) view.findViewById(R.id.tvE);

        tvS.setOnTouchListener(new MyTouchListener());
        tvQ.setOnTouchListener(new MyTouchListener());
        tvU.setOnTouchListener(new MyTouchListener());
        tvA.setOnTouchListener(new MyTouchListener());
        tvR.setOnTouchListener(new MyTouchListener());
        tvE.setOnTouchListener(new MyTouchListener());

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
        llS.removeAllViews();
        llQ.removeAllViews();
        llU.removeAllViews();
        llA.removeAllViews();
        llR.removeAllViews();
        llE.removeAllViews();

        llS1.removeAllViews();
        llQ1.removeAllViews();
        llU1.removeAllViews();
        llA1.removeAllViews();
        llR1.removeAllViews();
        llE1.removeAllViews();
    }

    public void refreshView(){
        llS1.addView(tvS);
        llQ1.addView(tvQ);
        llU1.addView(tvU);
        llA1.addView(tvA);
        llR1.addView(tvR);
        llE1.addView(tvE);

        tvS.setOnTouchListener(new MyTouchListener());
        tvQ.setOnTouchListener(new MyTouchListener());
        tvU.setOnTouchListener(new MyTouchListener());
        tvA.setOnTouchListener(new MyTouchListener());
        tvR.setOnTouchListener(new MyTouchListener());
        tvE.setOnTouchListener(new MyTouchListener());

        llS.setOnDragListener(new MyDragListener());
        llQ.setOnDragListener(new MyDragListener());
        llU.setOnDragListener(new MyDragListener());
        llA.setOnDragListener(new MyDragListener());
        llR.setOnDragListener(new MyDragListener());
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
        if(((View)view.findViewById(tvS.getId()).getParent()).getId() == llS.getId())
            if(((View)view.findViewById(tvQ.getId()).getParent()).getId() == llQ.getId())
                if(((View)view.findViewById(tvU.getId()).getParent()).getId() == llU.getId())
                    if(((View)view.findViewById(tvA.getId()).getParent()).getId() == llA.getId())
                        if(((View)view.findViewById(tvR.getId()).getParent()).getId() == llR.getId())
                            if(((View)view.findViewById(tvE.getId()).getParent()).getId() == llE.getId())
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
