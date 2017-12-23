package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.star;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.star.StarActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.star.StarActivity.count;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.star.StarActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.star.StarActivity.valid;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.star.StarActivity.wrong;

public class Star3 extends Fragment {

    private View view;
    private LinearLayout llS, llT, llA, llR;
    private LinearLayout llS1, llT1, llA1, llR1;
    private TextView tvS, tvT, tvA, tvR;
    private SwipeRefreshLayout swipeContainer;

    public Star3(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.star3, container, false);

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

        ImageView star = (ImageView) view.findViewById(R.id.star);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Fstar.png?alt=media&token=400c5703-b974-4ebf-abb3-a469fa275924").error(R.drawable.bright_kid_bg).into(star);

        llS = (LinearLayout) view.findViewById(R.id.llS);
        llT = (LinearLayout) view.findViewById(R.id.llT);
        llA = (LinearLayout) view.findViewById(R.id.llA);
        llR = (LinearLayout) view.findViewById(R.id.llR);

        llS.setOnDragListener(new MyDragListener());
        llT.setOnDragListener(new MyDragListener());
        llA.setOnDragListener(new MyDragListener());
        llR.setOnDragListener(new MyDragListener());

        llS1 = (LinearLayout) view.findViewById(R.id.llS1);
        llT1 = (LinearLayout) view.findViewById(R.id.llT1);
        llA1 = (LinearLayout) view.findViewById(R.id.llA1);
        llR1 = (LinearLayout) view.findViewById(R.id.llR1);

        tvS = (TextView) view.findViewById(R.id.tvS);
        tvT = (TextView) view.findViewById(R.id.tvT);
        tvA = (TextView) view.findViewById(R.id.tvA);
        tvR = (TextView) view.findViewById(R.id.tvR);

        tvS.setOnTouchListener(new MyTouchListener());
        tvT.setOnTouchListener(new MyTouchListener());
        tvA.setOnTouchListener(new MyTouchListener());
        tvR.setOnTouchListener(new MyTouchListener());

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
        llT.removeAllViews();
        llA.removeAllViews();
        llR.removeAllViews();

        llS1.removeAllViews();
        llT1.removeAllViews();
        llA1.removeAllViews();
        llR1.removeAllViews();
    }

    public void refreshView(){
        llS1.addView(tvS);
        llT1.addView(tvT);
        llA1.addView(tvA);
        llR1.addView(tvR);

        tvS.setOnTouchListener(new MyTouchListener());
        tvT.setOnTouchListener(new MyTouchListener());
        tvA.setOnTouchListener(new MyTouchListener());
        tvR.setOnTouchListener(new MyTouchListener());

        llS.setOnDragListener(new MyDragListener());
        llT.setOnDragListener(new MyDragListener());
        llA.setOnDragListener(new MyDragListener());
        llR.setOnDragListener(new MyDragListener());
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
        if(((View)view.findViewById(tvS.getId()).getParent()).getId() == llS.getId())
            if(((View)view.findViewById(tvT.getId()).getParent()).getId() == llT.getId())
                if(((View)view.findViewById(tvA.getId()).getParent()).getId() == llA.getId())
                    if(((View)view.findViewById(tvR.getId()).getParent()).getId() == llR.getId())
                        valid = "valid";
        validate();
    }

    public void validate(){
        if(valid.equals("valid"))
            correct.start();
        else
            wrong.start();
    }

}
