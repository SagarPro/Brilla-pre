package com.brightkidmont.brilla.my_bright_brain.visual.colors.pink;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.pink.PinkActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.pink.PinkActivity.count;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.pink.PinkActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.pink.PinkActivity.valid;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.pink.PinkActivity.wrong;

public class Pink6 extends Fragment {

    private LinearLayout llP, llI, llN, llK;
    private LinearLayout llP1, llI1, llN1, llK1;
    private TextView tvP, tvI, tvN, tvK;
    private View view;

    public Pink6(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.pink6, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Arrange the spelling of the color of the Pig");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.f_pink));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q.start();
            }
        });

        ImageView pink = (ImageView) view.findViewById(R.id.pink);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPink%2Fpig.png?alt=media&token=26a4140c-c147-492c-a234-0fc533dd0678").error(R.drawable.bright_kid_bg).into(pink);

        llP = (LinearLayout) view.findViewById(R.id.llP);
        llI = (LinearLayout) view.findViewById(R.id.llI);
        llN = (LinearLayout) view.findViewById(R.id.llN);
        llK = (LinearLayout) view.findViewById(R.id.llK);

        llP.setOnDragListener(new MyDragListener());
        llI.setOnDragListener(new MyDragListener());
        llN.setOnDragListener(new MyDragListener());
        llK.setOnDragListener(new MyDragListener());

        llP1 = (LinearLayout) view.findViewById(R.id.llP1);
        llI1 = (LinearLayout) view.findViewById(R.id.llI1);
        llN1 = (LinearLayout) view.findViewById(R.id.llN1);
        llK1 = (LinearLayout) view.findViewById(R.id.llK1);

        tvP = (TextView) view.findViewById(R.id.tvP);
        tvI = (TextView) view.findViewById(R.id.tvI);
        tvN = (TextView) view.findViewById(R.id.tvN);
        tvK = (TextView) view.findViewById(R.id.tvK);

        tvP.setOnTouchListener(new MyTouchListener());
        tvI.setOnTouchListener(new MyTouchListener());
        tvN.setOnTouchListener(new MyTouchListener());
        tvK.setOnTouchListener(new MyTouchListener());

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
        llP.removeAllViews();
        llI.removeAllViews();
        llN.removeAllViews();
        llK.removeAllViews();

        llP1.removeAllViews();
        llI1.removeAllViews();
        llN1.removeAllViews();
        llK1.removeAllViews();
    }

    public void refreshView(){
        llP1.addView(tvP);
        llI1.addView(tvI);
        llN1.addView(tvN);
        llK1.addView(tvK);

        tvP.setOnTouchListener(new MyTouchListener());
        tvI.setOnTouchListener(new MyTouchListener());
        tvN.setOnTouchListener(new MyTouchListener());
        tvK.setOnTouchListener(new MyTouchListener());

        llP.setOnDragListener(new MyDragListener());
        llI.setOnDragListener(new MyDragListener());
        llN.setOnDragListener(new MyDragListener());
        llK.setOnDragListener(new MyDragListener());
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
        if(((ViewGroup)view.findViewById(tvP.getId()).getParent()).getId() == llP.getId())
            if(((ViewGroup)view.findViewById(tvI.getId()).getParent()).getId() == llI.getId())
                if(((ViewGroup)view.findViewById(tvN.getId()).getParent()).getId() == llN.getId())
                    if(((ViewGroup)view.findViewById(tvK.getId()).getParent()).getId() == llK.getId())
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
