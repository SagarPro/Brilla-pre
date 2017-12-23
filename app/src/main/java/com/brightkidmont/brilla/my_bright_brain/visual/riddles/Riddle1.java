package com.brightkidmont.brilla.my_bright_brain.visual.riddles;

import android.content.ClipData;
import android.os.Bundle;
import android.os.Handler;
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

import java.util.ArrayList;
import java.util.Arrays;

import static com.brightkidmont.brilla.my_bright_brain.visual.riddles.RiddlesActivity.points;
import static com.brightkidmont.brilla.my_bright_brain.visual.riddles.RiddlesActivity.submitCount;
import static com.brightkidmont.brilla.my_bright_brain.visual.riddles.RiddlesActivity.tabLayout;

public class Riddle1 extends Fragment {

    private View view;
    ArrayList<String> imagesList;
    ArrayList<ImageView> images;
    private ImageView iv1, iv2, iv3, iv4, iv5, iv6;
    private LinearLayout ll1, ll2, ll3, ll4, ll5, ll6, llTop, llBottom;
    private int count = 0;
    private String valid = "not", submitted = "not";

    public Riddle1(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.riddle1, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Arrange the numbers in a triangle so that all the sides of a triangle becomes 9");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_midnight_blue));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        imagesList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.riddle1)));

        iv1 = (ImageView) view.findViewById(R.id.iv1);
        iv2 = (ImageView) view.findViewById(R.id.iv2);
        iv3 = (ImageView) view.findViewById(R.id.iv3);
        iv4 = (ImageView) view.findViewById(R.id.iv4);
        iv5 = (ImageView) view.findViewById(R.id.iv5);
        iv6 = (ImageView) view.findViewById(R.id.iv6);

        images = new ArrayList<>();
        images.add(iv1);
        images.add(iv2);
        images.add(iv3);
        images.add(iv4);
        images.add(iv5);
        images.add(iv6);

        //setting image into imageView
        for(int i=0; i<6; i++){
            Picasso.with(getContext()).load(imagesList.get(i)).placeholder(R.drawable.bright_kid_bg).error(R.drawable.bright_kid_bg).into(images.get(i));
        }

        iv1.setOnTouchListener(new MyTouchListener());
        iv2.setOnTouchListener(new MyTouchListener());
        iv3.setOnTouchListener(new MyTouchListener());
        iv4.setOnTouchListener(new MyTouchListener());
        iv5.setOnTouchListener(new MyTouchListener());
        iv6.setOnTouchListener(new MyTouchListener());

        ll1 = (LinearLayout) view.findViewById(R.id.ll1);
        ll2 = (LinearLayout) view.findViewById(R.id.ll2);
        ll3 = (LinearLayout) view.findViewById(R.id.ll3);
        ll4 = (LinearLayout) view.findViewById(R.id.ll4);
        ll5 = (LinearLayout) view.findViewById(R.id.ll5);
        ll6 = (LinearLayout) view.findViewById(R.id.ll6);

        llTop = (LinearLayout) view.findViewById(R.id.llTop);
        llBottom = (LinearLayout) view.findViewById(R.id.llBottom);

        ll1.setOnDragListener(new MyDragListener());
        ll2.setOnDragListener(new MyDragListener());
        ll3.setOnDragListener(new MyDragListener());
        ll4.setOnDragListener(new MyDragListener());
        ll5.setOnDragListener(new MyDragListener());
        ll6.setOnDragListener(new MyDragListener());

        llTop.setOnDragListener(new MyDragListener());
        llBottom.setOnDragListener(new MyDragListener());

        //to accept the first answer only
        Button btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(submitted.equals("submit"))
                    Toast.makeText(getContext(), "You used your chance", Toast.LENGTH_SHORT).show();
                else if(count==6)
                    validateValues();
                else
                    Toast.makeText(getContext(), "Please fill all the sides of triangle", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
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
                    if(v.getId() == llTop.getId() || v.getId() == llBottom.getId()){
                        View view = (View) event.getLocalState();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                    } else if(((LinearLayout) v).getChildCount() != 1) {
                        View view = (View) event.getLocalState();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);

                        count = 6;

                        //Below code will initialize count value to 5 if all the views are not filled with image
                        if(ll1.getChildCount()!=1 || ll2.getChildCount()!=1 || ll3.getChildCount()!=1 || ll4.getChildCount()!=1 || ll5.getChildCount()!=1|| ll6.getChildCount()!=1){
                            count--;
                        }

                    }

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                default:
                    break;
            }
            return true;
        }
    }

    //validating answer
    private void validateValues() {

        submitCount++;
        submitted = "submit";
        if(((LinearLayout)view.findViewById(iv1.getId()).getParent()).getId() == ll1.getId()
                || ((LinearLayout)view.findViewById(iv1.getId()).getParent()).getId() == ll4.getId()
                || ((LinearLayout)view.findViewById(iv1.getId()).getParent()).getId() == ll6.getId())
            if(((LinearLayout)view.findViewById(iv2.getId()).getParent()).getId() == ll4.getId()
                    || ((LinearLayout)view.findViewById(iv2.getId()).getParent()).getId() == ll6.getId()
                    || ((LinearLayout)view.findViewById(iv2.getId()).getParent()).getId() == ll1.getId())
                if(((LinearLayout)view.findViewById(iv3.getId()).getParent()).getId() == ll6.getId()
                        || ((LinearLayout)view.findViewById(iv3.getId()).getParent()).getId() == ll4.getId()
                        || ((LinearLayout)view.findViewById(iv3.getId()).getParent()).getId() == ll1.getId())
                    if(((LinearLayout)view.findViewById(iv4.getId()).getParent()).getId() == ll5.getId()
                            || ((LinearLayout)view.findViewById(iv4.getId()).getParent()).getId() == ll2.getId()
                            || ((LinearLayout)view.findViewById(iv4.getId()).getParent()).getId() == ll3.getId())
                        if(((LinearLayout)view.findViewById(iv5.getId()).getParent()).getId() == ll3.getId()
                                || ((LinearLayout)view.findViewById(iv5.getId()).getParent()).getId() == ll2.getId()
                                || ((LinearLayout)view.findViewById(iv5.getId()).getParent()).getId() == ll5.getId())
                            if(((LinearLayout)view.findViewById(iv6.getId()).getParent()).getId() == ll2.getId()
                                    || ((LinearLayout)view.findViewById(iv6.getId()).getParent()).getId() == ll3.getId()
                                    || ((LinearLayout)view.findViewById(iv6.getId()).getParent()).getId() == ll5.getId())
                                valid = "valid";
        validate();
    }

    //assigning points after validation
    private void validate(){
        if(valid.equals("valid")){
            points = points+2;
            valid = "not";
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tabLayout.getTabAt(1).select();
            }
        }, 1000);
    }
}