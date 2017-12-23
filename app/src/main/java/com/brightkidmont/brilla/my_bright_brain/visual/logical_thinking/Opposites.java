package com.brightkidmont.brilla.my_bright_brain.visual.logical_thinking;

import android.content.ClipData;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.brightkidmont.brilla.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

public class Opposites extends Fragment{

    private MediaPlayer correct, wrong;
    private ImageView result1, result2, result3, result4;
    private LinearLayout ll1;
    private LinearLayout ll2;
    private LinearLayout ll3;
    private LinearLayout ll4;
    private ImageView image11;
    private ImageView image21;
    private ImageView image31;
    private ImageView image41;

    public Opposites(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.opposites_fragment_layout, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Drag and match the opposite");
        flash_header.setTypeface(Typeface.DEFAULT_BOLD);

        correct = MediaPlayer.create(getContext(), R.raw.correct);
        wrong = MediaPlayer.create(getContext(), R.raw.ur_wrong);

        //storing image urls in array
        ArrayList<String> imagesList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.opposites)));

        result1 = (ImageView) view.findViewById(R.id.result1);
        result2 = (ImageView) view.findViewById(R.id.result2);
        result3 = (ImageView) view.findViewById(R.id.result3);
        result4 = (ImageView) view.findViewById(R.id.result4);

        ImageView image1 = (ImageView) view.findViewById(R.id.image1);
        ImageView image2 = (ImageView) view.findViewById(R.id.image2);
        ImageView image3 = (ImageView) view.findViewById(R.id.image3);
        ImageView image4 = (ImageView) view.findViewById(R.id.image4);
        image11 = (ImageView) view.findViewById(R.id.image11);
        image21 = (ImageView) view.findViewById(R.id.image21);
        image31 = (ImageView) view.findViewById(R.id.image31);
        image41 = (ImageView) view.findViewById(R.id.image41);

        //setting image in imageView
        Picasso.with(getContext()).load(imagesList.get(0)).error(R.drawable.bright_kid_bg).into(image1);
        Picasso.with(getContext()).load(imagesList.get(1)).error(R.drawable.bright_kid_bg).into(image31);
        Picasso.with(getContext()).load(imagesList.get(2)).error(R.drawable.bright_kid_bg).into(image41);
        Picasso.with(getContext()).load(imagesList.get(3)).error(R.drawable.bright_kid_bg).into(image2);
        Picasso.with(getContext()).load(imagesList.get(4)).error(R.drawable.bright_kid_bg).into(image3);
        Picasso.with(getContext()).load(imagesList.get(5)).error(R.drawable.bright_kid_bg).into(image11);
        Picasso.with(getContext()).load(imagesList.get(6)).error(R.drawable.bright_kid_bg).into(image4);
        Picasso.with(getContext()).load(imagesList.get(7)).error(R.drawable.bright_kid_bg).into(image21);

        ll1 = (LinearLayout) view.findViewById(R.id.ll1);
        ll2 = (LinearLayout) view.findViewById(R.id.ll2);
        ll3 = (LinearLayout) view.findViewById(R.id.ll3);
        ll4 = (LinearLayout) view.findViewById(R.id.ll4);
        LinearLayout ll5 = (LinearLayout) view.findViewById(R.id.ll5);

        ll1.setOnDragListener(new MyDragListener());
        ll2.setOnDragListener(new MyDragListener());
        ll3.setOnDragListener(new MyDragListener());
        ll4.setOnDragListener(new MyDragListener());
        ll5.setOnDragListener(new MyDragListener());

        image11.setOnTouchListener(new MyTouchListener());
        image21.setOnTouchListener(new MyTouchListener());
        image31.setOnTouchListener(new MyTouchListener());
        image41.setOnTouchListener(new MyTouchListener());

        return view;
    }

    //validating result
    private void validate(){
        if(image11.getParent() == ll3)
            result3.setImageResource(R.drawable.right);
        else
            result3.setImageResource(R.drawable.wrong);
        if(image21.getParent() == ll4)
            result4.setImageResource(R.drawable.right);
        else
            result4.setImageResource(R.drawable.wrong);
        if(image31.getParent() == ll1)
            result1.setImageResource(R.drawable.right);
        else
            result1.setImageResource(R.drawable.wrong);
        if(image41.getParent() == ll2)
            result2.setImageResource(R.drawable.right);
        else
            result2.setImageResource(R.drawable.wrong);
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
                    if(((LinearLayout) v).getChildCount() != 1) {
                        View view = (View) event.getLocalState();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        switch (view.getId()){
                            case R.id.image11:
                                if(image11.getParent() == ll3)
                                    correct.start();
                                else
                                    wrong.start();
                                break;
                            case R.id.image21:
                                if(image21.getParent() == ll4)
                                correct.start();
                            else
                                wrong.start();
                                break;
                            case R.id.image31:
                                if(image31.getParent() == ll1)
                                correct.start();
                            else
                                wrong.start();
                                break;
                            case R.id.image41:
                                if(image41.getParent() == ll2)
                                correct.start();
                            else
                                wrong.start();
                                break;
                        }
                    }
                    validate();
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                default:
                    break;
            }
            return true;
        }

    }
}
