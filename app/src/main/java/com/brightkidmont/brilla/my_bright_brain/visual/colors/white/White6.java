package com.brightkidmont.brilla.my_bright_brain.visual.colors.white;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.white.WhiteActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.white.WhiteActivity.count;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.white.WhiteActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.white.WhiteActivity.valid;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.white.WhiteActivity.whiteTab;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.white.WhiteActivity.wrong;

public class White6 extends Fragment {

    private LinearLayout llW, llH, llI, llT, llE;
    private LinearLayout llW1, llH1, llI1, llT1, llE1;
    private TextView tvW, tvH, tvI, tvT, tvE;
    private View view;

    public White6(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.white6, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Arrange the spelling of Pigeon's color");
        flash_header.setTextColor(getResources().getColor(R.color.f_black));

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_clouds));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setImageResource(R.drawable.speaker_b);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q.start();
            }
        });

        ImageView pigeon = (ImageView) view.findViewById(R.id.pigeon);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FWhite%2Fw4.png?alt=media&token=097db3d3-1951-4600-9f84-3455bc296a75").error(R.drawable.bright_kid_bg).into(pigeon);

        llW = (LinearLayout) view.findViewById(R.id.llW);
        llH = (LinearLayout) view.findViewById(R.id.llH);
        llI = (LinearLayout) view.findViewById(R.id.llI);
        llT = (LinearLayout) view.findViewById(R.id.llT);
        llE = (LinearLayout) view.findViewById(R.id.llE);

        llW.setOnDragListener(new MyDragListener());
        llH.setOnDragListener(new MyDragListener());
        llI.setOnDragListener(new MyDragListener());
        llT.setOnDragListener(new MyDragListener());
        llE.setOnDragListener(new MyDragListener());

        llW1 = (LinearLayout) view.findViewById(R.id.llW1);
        llH1 = (LinearLayout) view.findViewById(R.id.llH1);
        llI1 = (LinearLayout) view.findViewById(R.id.llI1);
        llT1 = (LinearLayout) view.findViewById(R.id.llT1);
        llE1 = (LinearLayout) view.findViewById(R.id.llE1);

        tvW = (TextView) view.findViewById(R.id.tvW);
        tvH = (TextView) view.findViewById(R.id.tvH);
        tvI = (TextView) view.findViewById(R.id.tvI);
        tvT = (TextView) view.findViewById(R.id.tvT);
        tvE = (TextView) view.findViewById(R.id.tvE);

        tvW.setOnTouchListener(new MyTouchListener());
        tvH.setOnTouchListener(new MyTouchListener());
        tvI.setOnTouchListener(new MyTouchListener());
        tvT.setOnTouchListener(new MyTouchListener());
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
        llW.removeAllViews();
        llH.removeAllViews();
        llI.removeAllViews();
        llT.removeAllViews();
        llE.removeAllViews();

        llW1.removeAllViews();
        llH1.removeAllViews();
        llI1.removeAllViews();
        llT1.removeAllViews();
        llE1.removeAllViews();
    }

    public void refreshView(){
        llW1.addView(tvW);
        llH1.addView(tvH);
        llI1.addView(tvI);
        llT1.addView(tvT);
        llE1.addView(tvE);

        tvW.setOnTouchListener(new MyTouchListener());
        tvH.setOnTouchListener(new MyTouchListener());
        tvI.setOnTouchListener(new MyTouchListener());
        tvT.setOnTouchListener(new MyTouchListener());
        tvE.setOnTouchListener(new MyTouchListener());

        llW.setOnDragListener(new MyDragListener());
        llH.setOnDragListener(new MyDragListener());
        llI.setOnDragListener(new MyDragListener());
        llT.setOnDragListener(new MyDragListener());
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
        if(((ViewGroup)view.findViewById(tvW.getId()).getParent()).getId() == llW.getId())
            if(((ViewGroup)view.findViewById(tvH.getId()).getParent()).getId() == llH.getId())
                if(((ViewGroup)view.findViewById(tvI.getId()).getParent()).getId() == llI.getId())
                    if(((ViewGroup)view.findViewById(tvT.getId()).getParent()).getId() == llT.getId())
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
