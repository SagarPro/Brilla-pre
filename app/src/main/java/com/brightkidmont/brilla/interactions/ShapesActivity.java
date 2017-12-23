package com.brightkidmont.brilla.interactions;

import android.content.Intent;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.brightkidmont.brilla.R;
import com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.arrow.ArrowActivity;
import com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.circle.CircleActivity;
import com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.cross.CrossActivity;
import com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.oval.OvalActivity;
import com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.pentagon.PentagonActivity;
import com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rectangle.RectangleActivity;
import com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rhombus.RhombusActivity;
import com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.square.SquareActivity;
import com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.star.StarActivity;
import com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.trapezium.TrapeziumActivity;
import com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.triangle.TriangleActivity;
import com.squareup.picasso.Picasso;

import static com.brightkidmont.brilla.HomePageActivity.bgm5;
import static com.brightkidmont.brilla.HomePageActivity.bgm5PausePosition;

public class ShapesActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shapes);

        tts = new TextToSpeech(this, this);

        bgm5.seekTo(bgm5PausePosition);
        bgm5.start();

        ImageView ivShapeCircle = (ImageView) findViewById(R.id.ivShapeCircle);
        ImageView ivShapeStar = (ImageView) findViewById(R.id.ivShapeStar);
        ImageView ivShapeRectangle = (ImageView) findViewById(R.id.ivShapeRectangle);
        ImageView ivShapeTriangle = (ImageView) findViewById(R.id.ivShapeTriangle);
        ImageView ivShapeCross = (ImageView) findViewById(R.id.ivShapeCross);
        ImageView ivShapeArrow = (ImageView) findViewById(R.id.ivShapeArrow);
        ImageView ivShapeRhombus = (ImageView) findViewById(R.id.ivShapeRhombus);
        ImageView ivShapeTrapezium = (ImageView) findViewById(R.id.ivShapeTrapezium);
        ImageView ivShapeOval = (ImageView) findViewById(R.id.ivShapeOval);
        ImageView ivShapePentagon = (ImageView) findViewById(R.id.ivShapePentagon);
        ImageView ivShapeSquare = (ImageView) findViewById(R.id.ivShapeSquare);

        //Setting images from database storage
        String image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FCircle%2Fcircle_b.png?alt=media&token=8225c28b-341b-49c3-a160-3f1e9ae80b67";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fstar_g.png?alt=media&token=8e589017-9411-4bf8-bd6d-83cce4a51b99";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Fsquare.png?alt=media&token=6924460b-2faf-4e93-9fef-6d44cf90463c";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FTriangle%2Ftriangle_g.png?alt=media&token=740e4511-f90b-4c29-ad67-9c75449c4e4d";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FCross%2Fcross.png?alt=media&token=967ce5f3-91d8-4e28-88c9-92772a359d2f";
        image6 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Farrow_b.png?alt=media&token=8fb1d071-13b3-4423-9bed-598ae502b0e9";
        image7 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FRhombus%2Frhombus.png?alt=media&token=17e5825c-062b-480a-aa49-2400d1935809";
        image8 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FTrapezium%2Ftrapezium.png?alt=media&token=7c934369-9fc6-45e9-89d7-d0fe0cf3da3c";
        image9 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FOval%2Foval_y.png?alt=media&token=065bd8f2-a3c5-4030-9cee-58306b305905";
        image10 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FPentagon%2Fpentagon_p.png?alt=media&token=96fa9984-e116-4595-a997-4552e655d60a";
        image11 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FRectangle%2Frectangle_b.png?alt=media&token=edea220f-b6f0-417d-8021-1a19b0c422c3";

        Picasso.with(this).load(image1).placeholder(R.drawable.bright_kid_bg).error(R.drawable.bright_kid_bg).into(ivShapeCircle);
        Picasso.with(this).load(image2).placeholder(R.drawable.bright_kid_bg).error(R.drawable.bright_kid_bg).into(ivShapeStar);
        Picasso.with(this).load(image3).placeholder(R.drawable.bright_kid_bg).error(R.drawable.bright_kid_bg).into(ivShapeSquare);
        Picasso.with(this).load(image4).placeholder(R.drawable.bright_kid_bg).error(R.drawable.bright_kid_bg).into(ivShapeTriangle);
        Picasso.with(this).load(image5).placeholder(R.drawable.bright_kid_bg).error(R.drawable.bright_kid_bg).into(ivShapeCross);
        Picasso.with(this).load(image6).placeholder(R.drawable.bright_kid_bg).error(R.drawable.bright_kid_bg).into(ivShapeArrow);
        Picasso.with(this).load(image7).placeholder(R.drawable.bright_kid_bg).error(R.drawable.bright_kid_bg).into(ivShapeRhombus);
        Picasso.with(this).load(image8).placeholder(R.drawable.bright_kid_bg).error(R.drawable.bright_kid_bg).into(ivShapeTrapezium);
        Picasso.with(this).load(image9).placeholder(R.drawable.bright_kid_bg).error(R.drawable.bright_kid_bg).into(ivShapeOval);
        Picasso.with(this).load(image10).placeholder(R.drawable.bright_kid_bg).error(R.drawable.bright_kid_bg).into(ivShapePentagon);
        Picasso.with(this).load(image11).placeholder(R.drawable.bright_kid_bg).error(R.drawable.bright_kid_bg).into(ivShapeRectangle);

        ivShapeCircle.setOnClickListener(this);
        ivShapeStar.setOnClickListener(this);
        ivShapeRectangle.setOnClickListener(this);
        ivShapeTriangle.setOnClickListener(this);
        ivShapeCross.setOnClickListener(this);
        ivShapeArrow.setOnClickListener(this);
        ivShapeRhombus.setOnClickListener(this);
        ivShapeTrapezium.setOnClickListener(this);
        ivShapeOval.setOnClickListener(this);
        ivShapePentagon.setOnClickListener(this);
        ivShapeSquare.setOnClickListener(this);

    }

    //opening respective activity onClick
    @Override
    public void onClick(View v) {
        int id = v.getId();
        Handler handler = new Handler();
        switch (id){
            case R.id.ivShapeCircle:
                tts.speak("Circle", TextToSpeech.QUEUE_FLUSH, null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ShapesActivity.this, CircleActivity.class));
                    }
                },1000);
                break;
            case R.id.ivShapeStar:
                tts.speak("Star", TextToSpeech.QUEUE_FLUSH, null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ShapesActivity.this, StarActivity.class));
                    }
                },1000);
                break;
            case R.id.ivShapeSquare:
                tts.speak("Square", TextToSpeech.QUEUE_FLUSH, null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ShapesActivity.this, SquareActivity.class));
                    }
                },1000);
                break;
            case R.id.ivShapeTriangle:
                tts.speak("Triangle", TextToSpeech.QUEUE_FLUSH, null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ShapesActivity.this, TriangleActivity.class));
                    }
                },1000);
                break;
            case R.id.ivShapeCross:
                tts.speak("Cross", TextToSpeech.QUEUE_FLUSH, null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ShapesActivity.this, CrossActivity.class));
                    }
                },1000);
                break;
            case R.id.ivShapeArrow:
                tts.speak("Arrow", TextToSpeech.QUEUE_FLUSH, null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ShapesActivity.this, ArrowActivity.class));
                    }
                },1000);
                break;
            case R.id.ivShapeRhombus:
                tts.speak("Rhombus", TextToSpeech.QUEUE_FLUSH, null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ShapesActivity.this, RhombusActivity.class));
                    }
                },1000);
                break;
            case R.id.ivShapeTrapezium:
                tts.speak("Trapezium", TextToSpeech.QUEUE_FLUSH, null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ShapesActivity.this, TrapeziumActivity.class));
                    }
                },1000);
                break;
            case R.id.ivShapeOval:
                tts.speak("Oval", TextToSpeech.QUEUE_FLUSH, null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ShapesActivity.this, OvalActivity.class));
                    }
                },1000);
                break;
            case R.id.ivShapePentagon:
                tts.speak("Pentagon", TextToSpeech.QUEUE_FLUSH, null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ShapesActivity.this, PentagonActivity.class));
                    }
                },1000);
                break;
            case R.id.ivShapeRectangle:
                tts.speak("Rectangle", TextToSpeech.QUEUE_FLUSH, null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ShapesActivity.this, RectangleActivity.class));
                    }
                },1000);
                break;
        }
    }

    @Override
    public void onInit(int status) {
    }

    @Override
    protected void onPause() {
        super.onPause();
        bgm5.pause();
        bgm5PausePosition = bgm5.getCurrentPosition();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bgm5.seekTo(bgm5PausePosition);
        bgm5.start();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        bgm5.pause();
        bgm5PausePosition = bgm5.getCurrentPosition();
        finish();
    }

}
