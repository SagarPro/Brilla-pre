package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.pentagon;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.pentagon.PentagonActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.pentagon.PentagonActivity.count;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.pentagon.PentagonActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.pentagon.PentagonActivity.valid;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.pentagon.PentagonActivity.wrong;

public class Pentagon3 extends Fragment {

    private View view;
    private LinearLayout llP, llE, llN, llT, llA, llG, llO, llN1;
    private TextView tvP, tvE, tvN, tvT, tvA, tvG, tvO, tvN1;
    private LinearLayout llP1, llE1, llN11, llT1, llA1, llG1, llO1, llN12;
    private SwipeRefreshLayout swipeContainer;

    public Pentagon3(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.pentagon3, container, false);

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

        ImageView pentagon = (ImageView) view.findViewById(R.id.pentagon);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Fpentagon.png?alt=media&token=32911db7-0479-481c-9072-84d9818a1fc8").error(R.drawable.bright_kid_bg).into(pentagon);

        llP = (LinearLayout) view.findViewById(R.id.llP);
        llE = (LinearLayout) view.findViewById(R.id.llE);
        llN = (LinearLayout) view.findViewById(R.id.llN);
        llT = (LinearLayout) view.findViewById(R.id.llT);
        llA = (LinearLayout) view.findViewById(R.id.llA);
        llG = (LinearLayout) view.findViewById(R.id.llG);
        llO = (LinearLayout) view.findViewById(R.id.llO);
        llN1 = (LinearLayout) view.findViewById(R.id.llN1);

        llP.setOnDragListener(new MyDragListener());
        llE.setOnDragListener(new MyDragListener());
        llN.setOnDragListener(new MyDragListener());
        llT.setOnDragListener(new MyDragListener());
        llA.setOnDragListener(new MyDragListener());
        llG.setOnDragListener(new MyDragListener());
        llO.setOnDragListener(new MyDragListener());
        llN1.setOnDragListener(new MyDragListener());

        tvP = (TextView) view.findViewById(R.id.tvP);
        tvE = (TextView) view.findViewById(R.id.tvE);
        tvN = (TextView) view.findViewById(R.id.tvN);
        tvT = (TextView) view.findViewById(R.id.tvT);
        tvA = (TextView) view.findViewById(R.id.tvA);
        tvG = (TextView) view.findViewById(R.id.tvG);
        tvO = (TextView) view.findViewById(R.id.tvO);
        tvN1 = (TextView) view.findViewById(R.id.tvN1);

        tvP.setOnTouchListener(new MyTouchListener());
        tvE.setOnTouchListener(new MyTouchListener());
        tvN.setOnTouchListener(new MyTouchListener());
        tvT.setOnTouchListener(new MyTouchListener());
        tvA.setOnTouchListener(new MyTouchListener());
        tvG.setOnTouchListener(new MyTouchListener());
        tvO.setOnTouchListener(new MyTouchListener());
        tvN1.setOnTouchListener(new MyTouchListener());

        llP1 = (LinearLayout) view.findViewById(R.id.llP1);
        llE1 = (LinearLayout) view.findViewById(R.id.llE1);
        llN11 = (LinearLayout) view.findViewById(R.id.llN11);
        llT1 = (LinearLayout) view.findViewById(R.id.llT1);
        llA1 = (LinearLayout) view.findViewById(R.id.llA1);
        llG1 = (LinearLayout) view.findViewById(R.id.llG1);
        llO1 = (LinearLayout) view.findViewById(R.id.llO1);
        llN12 = (LinearLayout) view.findViewById(R.id.llN12);

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
        llP.removeAllViews();
        llE.removeAllViews();
        llN.removeAllViews();
        llT.removeAllViews();
        llA.removeAllViews();
        llG.removeAllViews();
        llO.removeAllViews();
        llN1.removeAllViews();

        llP1.removeAllViews();
        llE1.removeAllViews();
        llN11.removeAllViews();
        llT1.removeAllViews();
        llA1.removeAllViews();
        llG1.removeAllViews();
        llO1.removeAllViews();
        llN12.removeAllViews();
    }

    public void refreshView(){
        llP1.addView(tvP);
        llE1.addView(tvE);
        llN11.addView(tvN);
        llT1.addView(tvT);
        llA1.addView(tvA);
        llG1.addView(tvG);
        llO1.addView(tvO);
        llN12.addView(tvN1);

        tvP.setOnTouchListener(new MyTouchListener());
        tvE.setOnTouchListener(new MyTouchListener());
        tvN.setOnTouchListener(new MyTouchListener());
        tvT.setOnTouchListener(new MyTouchListener());
        tvA.setOnTouchListener(new MyTouchListener());
        tvG.setOnTouchListener(new MyTouchListener());
        tvO.setOnTouchListener(new MyTouchListener());
        tvN1.setOnTouchListener(new MyTouchListener());

        llP.setOnDragListener(new MyDragListener());
        llE.setOnDragListener(new MyDragListener());
        llN.setOnDragListener(new MyDragListener());
        llT.setOnDragListener(new MyDragListener());
        llA.setOnDragListener(new MyDragListener());
        llG.setOnDragListener(new MyDragListener());
        llO.setOnDragListener(new MyDragListener());
        llN1.setOnDragListener(new MyDragListener());
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
                    if(count == 8){
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
        if(((View)view.findViewById(tvP.getId()).getParent()).getId() == llP.getId())
            if(((View)view.findViewById(tvE.getId()).getParent()).getId() == llE.getId())
                if(((View)view.findViewById(tvN.getId()).getParent()).getId() == llN.getId() || ((View)view.findViewById(tvN.getId()).getParent()).getId() == llN1.getId())
                    if(((View)view.findViewById(tvT.getId()).getParent()).getId() == llT.getId())
                        if(((View)view.findViewById(tvA.getId()).getParent()).getId() == llA.getId())
                            if(((View)view.findViewById(tvG.getId()).getParent()).getId() == llG.getId())
                                if(((View)view.findViewById(tvO.getId()).getParent()).getId() == llO.getId())
                                    if(((View)view.findViewById(tvN1.getId()).getParent()).getId() == llN.getId() || ((View)view.findViewById(tvN1.getId()).getParent()).getId() == llN1.getId())
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
