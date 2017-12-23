package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.trapezium;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.trapezium.TrapeziumActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.trapezium.TrapeziumActivity.count;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.trapezium.TrapeziumActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.trapezium.TrapeziumActivity.valid;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.trapezium.TrapeziumActivity.wrong;

public class Trapezium6 extends Fragment {

    private View view;
    private LinearLayout llT, llR, llA, llP, llE, llZ, llI, llU, llM;
    private LinearLayout llT1, llR1, llA1, llP1, llE1, llZ1, llI1, llU1, llM1;
    private TextView tvT, tvR, tvA, tvP, tvE, tvZ, tvI, tvU, tvM;
    private SwipeRefreshLayout swipeContainer;

    public Trapezium6(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.trapezium6, container, false);

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

        ImageView trapezium = (ImageView) view.findViewById(R.id.trapezium);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FTrapezium%2Ftrapezium.png?alt=media&token=7c934369-9fc6-45e9-89d7-d0fe0cf3da3c").error(R.drawable.bright_kid_bg).into(trapezium);

        llT = (LinearLayout) view.findViewById(R.id.llT);
        llR = (LinearLayout) view.findViewById(R.id.llR);
        llA = (LinearLayout) view.findViewById(R.id.llA);
        llP = (LinearLayout) view.findViewById(R.id.llP);
        llE = (LinearLayout) view.findViewById(R.id.llE);
        llZ = (LinearLayout) view.findViewById(R.id.llZ);
        llI = (LinearLayout) view.findViewById(R.id.llI);
        llU = (LinearLayout) view.findViewById(R.id.llU);
        llM = (LinearLayout) view.findViewById(R.id.llM);

        llT.setOnDragListener(new MyDragListener());
        llR.setOnDragListener(new MyDragListener());
        llA.setOnDragListener(new MyDragListener());
        llP.setOnDragListener(new MyDragListener());
        llE.setOnDragListener(new MyDragListener());
        llZ.setOnDragListener(new MyDragListener());
        llI.setOnDragListener(new MyDragListener());
        llU.setOnDragListener(new MyDragListener());
        llM.setOnDragListener(new MyDragListener());

        llT1 = (LinearLayout) view.findViewById(R.id.llT1);
        llR1 = (LinearLayout) view.findViewById(R.id.llR1);
        llA1 = (LinearLayout) view.findViewById(R.id.llA1);
        llP1 = (LinearLayout) view.findViewById(R.id.llP1);
        llE1 = (LinearLayout) view.findViewById(R.id.llE1);
        llZ1 = (LinearLayout) view.findViewById(R.id.llZ1);
        llI1 = (LinearLayout) view.findViewById(R.id.llI1);
        llU1 = (LinearLayout) view.findViewById(R.id.llU1);
        llM1 = (LinearLayout) view.findViewById(R.id.llM1);

        tvT = (TextView) view.findViewById(R.id.tvT);
        tvR = (TextView) view.findViewById(R.id.tvR);
        tvA = (TextView) view.findViewById(R.id.tvA);
        tvP = (TextView) view.findViewById(R.id.tvP);
        tvE = (TextView) view.findViewById(R.id.tvE);
        tvZ = (TextView) view.findViewById(R.id.tvZ);
        tvI = (TextView) view.findViewById(R.id.tvI);
        tvU = (TextView) view.findViewById(R.id.tvU);
        tvM = (TextView) view.findViewById(R.id.tvM);

        tvT.setOnTouchListener(new MyTouchListener());
        tvR.setOnTouchListener(new MyTouchListener());
        tvA.setOnTouchListener(new MyTouchListener());
        tvP.setOnTouchListener(new MyTouchListener());
        tvE.setOnTouchListener(new MyTouchListener());
        tvZ.setOnTouchListener(new MyTouchListener());
        tvI.setOnTouchListener(new MyTouchListener());
        tvU.setOnTouchListener(new MyTouchListener());
        tvM.setOnTouchListener(new MyTouchListener());

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
        llT.removeAllViews();
        llR.removeAllViews();
        llA.removeAllViews();
        llP.removeAllViews();
        llE.removeAllViews();
        llZ.removeAllViews();
        llI.removeAllViews();
        llU.removeAllViews();
        llM.removeAllViews();

        llT1.removeAllViews();
        llR1.removeAllViews();
        llA1.removeAllViews();
        llP1.removeAllViews();
        llE1.removeAllViews();
        llZ1.removeAllViews();
        llI1.removeAllViews();
        llU1.removeAllViews();
        llM1.removeAllViews();
    }

    public void refreshView(){
        llT1.addView(tvT);
        llR1.addView(tvR);
        llA1.addView(tvA);
        llP1.addView(tvP);
        llE1.addView(tvE);
        llZ1.addView(tvZ);
        llI1.addView(tvI);
        llU1.addView(tvU);
        llM1.addView(tvM);

        tvT.setOnTouchListener(new MyTouchListener());
        tvR.setOnTouchListener(new MyTouchListener());
        tvA.setOnTouchListener(new MyTouchListener());
        tvP.setOnTouchListener(new MyTouchListener());
        tvE.setOnTouchListener(new MyTouchListener());
        tvZ.setOnTouchListener(new MyTouchListener());
        tvI.setOnTouchListener(new MyTouchListener());
        tvU.setOnTouchListener(new MyTouchListener());
        tvM.setOnTouchListener(new MyTouchListener());

        llT.setOnDragListener(new MyDragListener());
        llR.setOnDragListener(new MyDragListener());
        llA.setOnDragListener(new MyDragListener());
        llP.setOnDragListener(new MyDragListener());
        llE.setOnDragListener(new MyDragListener());
        llZ.setOnDragListener(new MyDragListener());
        llI.setOnDragListener(new MyDragListener());
        llU.setOnDragListener(new MyDragListener());
        llM.setOnDragListener(new MyDragListener());
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
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                default:
                    break;
            }
            return true;
        }
    }

    public void validateValues(){
        if(((View)view.findViewById(tvT.getId()).getParent()).getId() == llT.getId())
            if(((View)view.findViewById(tvR.getId()).getParent()).getId() == llR.getId())
                if(((View)view.findViewById(tvA.getId()).getParent()).getId() == llA.getId())
                    if(((View)view.findViewById(tvP.getId()).getParent()).getId() == llP.getId())
                        if(((View)view.findViewById(tvE.getId()).getParent()).getId() == llE.getId())
                            if(((View)view.findViewById(tvZ.getId()).getParent()).getId() == llZ.getId())
                                if(((View)view.findViewById(tvI.getId()).getParent()).getId() == llI.getId())
                                    if(((View)view.findViewById(tvU.getId()).getParent()).getId() == llU.getId())
                                        if(((View)view.findViewById(tvM.getId()).getParent()).getId() == llM.getId())
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
