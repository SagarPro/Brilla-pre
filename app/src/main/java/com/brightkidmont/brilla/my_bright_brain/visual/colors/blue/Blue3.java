package com.brightkidmont.brilla.my_bright_brain.visual.colors.blue;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.blue.BlueActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.blue.BlueActivity.count;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.blue.BlueActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.blue.BlueActivity.valid;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.blue.BlueActivity.wrong;

public class Blue3 extends Fragment {

    private LinearLayout llB, llL, llU, llE;
    private LinearLayout llB1, llL1, llU1, llE1;
    private TextView tvB, tvL, tvU, tvE;
    View view;

    public Blue3(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.blue3, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Arrange the spelling for Butterfly color");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_belize_hole));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q.start();
            }
        });

        ImageView blue = (ImageView) view.findViewById(R.id.blue);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlue%2Fbb1.png?alt=media&token=fa6aeb94-1b3c-4285-b1b4-17a039b0d511").error(R.drawable.bright_kid_bg).into(blue);

        llB = (LinearLayout) view.findViewById(R.id.llB);
        llL = (LinearLayout) view.findViewById(R.id.llL);
        llU = (LinearLayout) view.findViewById(R.id.llU);
        llE = (LinearLayout) view.findViewById(R.id.llE);

        llB.setOnDragListener(new MyDragListener());
        llL.setOnDragListener(new MyDragListener());
        llU.setOnDragListener(new MyDragListener());
        llE.setOnDragListener(new MyDragListener());

        llB1 = (LinearLayout) view.findViewById(R.id.llB1);
        llL1 = (LinearLayout) view.findViewById(R.id.llL1);
        llU1 = (LinearLayout) view.findViewById(R.id.llU1);
        llE1 = (LinearLayout) view.findViewById(R.id.llE1);

        tvB = (TextView) view.findViewById(R.id.tvB);
        tvL = (TextView) view.findViewById(R.id.tvL);
        tvU = (TextView) view.findViewById(R.id.tvU);
        tvE = (TextView) view.findViewById(R.id.tvE);

        tvB.setOnTouchListener(new MyTouchListener());
        tvL.setOnTouchListener(new MyTouchListener());
        tvU.setOnTouchListener(new MyTouchListener());
        tvE.setOnTouchListener(new MyTouchListener());

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
        llB.removeAllViews();
        llL.removeAllViews();
        llU.removeAllViews();
        llE.removeAllViews();

        llB1.removeAllViews();
        llL1.removeAllViews();
        llU1.removeAllViews();
        llE1.removeAllViews();
    }

    public void refreshView(){
        llB1.addView(tvB);
        llL1.addView(tvL);
        llU1.addView(tvU);
        llE1.addView(tvE);

        tvB.setOnTouchListener(new MyTouchListener());
        tvL.setOnTouchListener(new MyTouchListener());
        tvU.setOnTouchListener(new MyTouchListener());
        tvE.setOnTouchListener(new MyTouchListener());

        llB.setOnDragListener(new MyDragListener());
        llL.setOnDragListener(new MyDragListener());
        llU.setOnDragListener(new MyDragListener());
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
        if(((ViewGroup)view.findViewById(tvB.getId()).getParent()).getId() == llB.getId())
            if(((ViewGroup)view.findViewById(tvL.getId()).getParent()).getId() == llL.getId())
                if(((ViewGroup)view.findViewById(tvU.getId()).getParent()).getId() == llU.getId())
                    if(((ViewGroup)view.findViewById(tvE.getId()).getParent()).getId() == llE.getId())
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
