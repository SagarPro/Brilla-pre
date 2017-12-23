package com.brightkidmont.brilla.my_bright_brain.visual.colors.red;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.red.RedActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.red.RedActivity.count;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.red.RedActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.red.RedActivity.redTab;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.red.RedActivity.valid;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.red.RedActivity.wrong;

public class Red7 extends Fragment {

    private View view;
    private LinearLayout llR, llE, llD;
    private LinearLayout llR1, llE1, llD1;
    private TextView tvR, tvE, tvD;

    public Red7(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.red7, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Arrange the spelling for the color given below");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_alizarin));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q.start();
            }
        });

        llR = (LinearLayout) view.findViewById(R.id.llR);
        llE = (LinearLayout) view.findViewById(R.id.llE);
        llD = (LinearLayout) view.findViewById(R.id.llD);

        llR1 = (LinearLayout) view.findViewById(R.id.llR1);
        llE1 = (LinearLayout) view.findViewById(R.id.llE1);
        llD1 = (LinearLayout) view.findViewById(R.id.llD1);

        llR.setOnDragListener(new MyDragListener());
        llE.setOnDragListener(new MyDragListener());
        llD.setOnDragListener(new MyDragListener());

        tvR = (TextView) view.findViewById(R.id.tvR);
        tvE = (TextView) view.findViewById(R.id.tvE);
        tvD = (TextView) view.findViewById(R.id.tvD);

        tvR.setOnTouchListener(new MyTouchListener());
        tvE.setOnTouchListener(new MyTouchListener());
        tvD.setOnTouchListener(new MyTouchListener());

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
        llR.removeAllViews();
        llE.removeAllViews();
        llD.removeAllViews();

        llR1.removeAllViews();
        llE1.removeAllViews();
        llD1.removeAllViews();
    }

    public void refreshView(){
        llR1.addView(tvR);
        llE1.addView(tvE);
        llD1.addView(tvD);

        tvR.setOnTouchListener(new MyTouchListener());
        tvE.setOnTouchListener(new MyTouchListener());
        tvD.setOnTouchListener(new MyTouchListener());

        llR.setOnDragListener(new MyDragListener());
        llE.setOnDragListener(new MyDragListener());
        llD.setOnDragListener(new MyDragListener());
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
                    if(count == 3){
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
        if(((ViewGroup)view.findViewById(tvR.getId()).getParent()).getId() == llR.getId())
            if(((ViewGroup)view.findViewById(tvE.getId()).getParent()).getId() == llE.getId())
                if(((ViewGroup)view.findViewById(tvD.getId()).getParent()).getId() == llD.getId())
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
