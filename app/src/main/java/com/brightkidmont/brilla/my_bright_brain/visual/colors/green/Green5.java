package com.brightkidmont.brilla.my_bright_brain.visual.colors.green;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.green.GreenActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.green.GreenActivity.count;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.green.GreenActivity.greenTab;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.green.GreenActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.green.GreenActivity.valid;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.green.GreenActivity.wrong;

public class Green5 extends Fragment {

    private LinearLayout llG, llR, llE, llEE, llN;
    private LinearLayout llG1, llR1, llE1, llEE1, llN1;
    private TextView tvG, tvR, tvE, tvEE, tvN;
    View view;

    public Green5(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.green5, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Arrange the spellings for color of Peacock tail feathers");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_emerald));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q.start();
            }
        });

        ImageView peacock = (ImageView) view.findViewById(R.id.peacock);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FGreen%2Fg4.png?alt=media&token=593873c8-eb72-40d6-bc33-378a5cf1b2e5").error(R.drawable.bright_kid_bg).into(peacock);

        llG = (LinearLayout) view.findViewById(R.id.llG);
        llR = (LinearLayout) view.findViewById(R.id.llR);
        llE = (LinearLayout) view.findViewById(R.id.llE);
        llEE = (LinearLayout) view.findViewById(R.id.llEE);
        llN = (LinearLayout) view.findViewById(R.id.llN);

        llG.setOnDragListener(new MyDragListener());
        llR.setOnDragListener(new MyDragListener());
        llE.setOnDragListener(new MyDragListener());
        llEE.setOnDragListener(new MyDragListener());
        llN.setOnDragListener(new MyDragListener());

        llG1 = (LinearLayout) view.findViewById(R.id.llG1);
        llR1 = (LinearLayout) view.findViewById(R.id.llR1);
        llE1 = (LinearLayout) view.findViewById(R.id.llE1);
        llEE1 = (LinearLayout) view.findViewById(R.id.llEE1);
        llN1 = (LinearLayout) view.findViewById(R.id.llN1);

        tvG = (TextView) view.findViewById(R.id.tvG);
        tvR = (TextView) view.findViewById(R.id.tvR);
        tvE = (TextView) view.findViewById(R.id.tvE);
        tvEE = (TextView) view.findViewById(R.id.tvEE);
        tvN = (TextView) view.findViewById(R.id.tvN);

        tvG.setOnTouchListener(new MyTouchListener());
        tvR.setOnTouchListener(new MyTouchListener());
        tvE.setOnTouchListener(new MyTouchListener());
        tvEE.setOnTouchListener(new MyTouchListener());
        tvN.setOnTouchListener(new MyTouchListener());

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
        llG.removeAllViews();
        llR.removeAllViews();
        llE.removeAllViews();
        llEE.removeAllViews();
        llN.removeAllViews();

        llG1.removeAllViews();
        llR1.removeAllViews();
        llE1.removeAllViews();
        llEE1.removeAllViews();
        llN1.removeAllViews();
    }

    public void refreshView(){
        llG1.addView(tvG);
        llR1.addView(tvR);
        llE1.addView(tvE);
        llEE1.addView(tvEE);
        llN1.addView(tvN);

        tvG.setOnTouchListener(new MyTouchListener());
        tvR.setOnTouchListener(new MyTouchListener());
        tvE.setOnTouchListener(new MyTouchListener());
        tvEE.setOnTouchListener(new MyTouchListener());
        tvN.setOnTouchListener(new MyTouchListener());

        llG.setOnDragListener(new MyDragListener());
        llR.setOnDragListener(new MyDragListener());
        llE.setOnDragListener(new MyDragListener());
        llEE.setOnDragListener(new MyDragListener());
        llN.setOnDragListener(new MyDragListener());
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
                    if(count == 5){
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
        if(((ViewGroup)view.findViewById(tvG.getId()).getParent()).getId() == llG.getId())
            if(((ViewGroup)view.findViewById(tvR.getId()).getParent()).getId() == llR.getId())
                if(((ViewGroup)view.findViewById(tvE.getId()).getParent()).getId() == llE.getId() || ((ViewGroup)view.findViewById(tvE.getId()).getParent()).getId() == llEE.getId())
                    if(((ViewGroup)view.findViewById(tvEE.getId()).getParent()).getId() == llEE.getId() || ((ViewGroup)view.findViewById(tvEE.getId()).getParent()).getId() == llE.getId())
                        if(((ViewGroup)view.findViewById(tvN.getId()).getParent()).getId() == llN.getId())
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
