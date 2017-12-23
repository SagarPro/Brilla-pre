package com.brightkidmont.brilla.my_bright_brain.visual.jigsaw_puzzles;

import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.brightkidmont.brilla.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.brightkidmont.brilla.my_bright_brain.visual.jigsaw_puzzles.JigsawPuzzlesActivity.jpBG;
import static com.brightkidmont.brilla.my_bright_brain.visual.jigsaw_puzzles.JigsawPuzzlesActivity.jpFull;
import static com.brightkidmont.brilla.my_bright_brain.visual.jigsaw_puzzles.JigsawPuzzlesActivity.jpImages;
import static com.brightkidmont.brilla.my_bright_brain.visual.jigsaw_puzzles.JigsawPuzzlesActivity.tabLayout;

public class SeaAnimals extends Fragment {

    private LinearLayout lltl, lltr, llbl, llbr;
    private ImageView ivBG;

    public SeaAnimals(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.jigsaw_puzzles_fragment_layout, container, false);

        RelativeLayout rlBg = (RelativeLayout) view.findViewById(R.id.rlBg);

        lltl = (LinearLayout) view.findViewById(R.id.lltl);
        lltr = (LinearLayout) view.findViewById(R.id.lltr);
        llbl = (LinearLayout) view.findViewById(R.id.llbl);
        llbr = (LinearLayout) view.findViewById(R.id.llbr);

        lltl.setOnDragListener(new MyDragListener());
        lltr.setOnDragListener(new MyDragListener());
        llbl.setOnDragListener(new MyDragListener());
        llbr.setOnDragListener(new MyDragListener());

        ImageView ivtl = (ImageView) view.findViewById(R.id.ivtl);
        ImageView ivtr = (ImageView) view.findViewById(R.id.ivtr);
        ImageView ivbl = (ImageView) view.findViewById(R.id.ivbl);
        ImageView ivbr = (ImageView) view.findViewById(R.id.ivbr);

        ivBG = (ImageView) view.findViewById(R.id.ivBG);

        List<ImageView> imageList = new ArrayList<>();
        imageList.add(ivbr);
        imageList.add(ivbl);
        imageList.add(ivtr);
        imageList.add(ivtl);

        //setting image to imageView from database storage
        int j=0;
        for(int i=16; i<20; i++){
            Picasso.with(getContext()).load(jpImages.get(i)).error(R.drawable.bright_kid_bg).into(imageList.get(j));
            imageList.get(j).setOnTouchListener(new MyTouchListener());
            j++;
        }

        //setting background image
        try {
            BitmapDrawable ob = new BitmapDrawable(getResources(), new LoadImage().execute().get());
            rlBg.setBackground(ob);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return view;
    }

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int id = view.getId();
            switch (id){
                case R.id.ivtl:
                    lltl.setOnDragListener(null);
                    lltr.setOnDragListener(null);
                    llbl.setOnDragListener(null);
                    llbr.setOnDragListener(new MyDragListener());
                    break;
                case R.id.ivtr:
                    lltl.setOnDragListener(null);
                    lltr.setOnDragListener(null);
                    llbl.setOnDragListener(new MyDragListener());
                    llbr.setOnDragListener(null);
                    break;
                case R.id.ivbl:
                    lltl.setOnDragListener(null);
                    lltr.setOnDragListener(new MyDragListener());
                    llbl.setOnDragListener(null);
                    llbr.setOnDragListener(null);
                    break;
                case R.id.ivbr:
                    lltl.setOnDragListener(new MyDragListener());
                    lltr.setOnDragListener(null);
                    llbl.setOnDragListener(null);
                    llbr.setOnDragListener(null);
                    break;
            }
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
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);
                    validate();
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                default:
                    break;
            }
            return true;
        }
    }

    //validating puzzle
    private void validate(){
        if(lltl.getChildCount()==1
                && lltr.getChildCount()==1
                && llbl.getChildCount()==1
                && llbr.getChildCount()==1){
            Picasso.with(getContext()).load(jpFull.get(4)).into(ivBG);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tabLayout.getTabAt(5).select();
                }
            },2000);
        }

    }

    //loading image from url
    class LoadImage extends AsyncTask<Void, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Void... params) {
            URL url;
            Bitmap bmp = null;
            try {
                url = new URL(jpBG.get(4));
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmp;
        }
    }

}
