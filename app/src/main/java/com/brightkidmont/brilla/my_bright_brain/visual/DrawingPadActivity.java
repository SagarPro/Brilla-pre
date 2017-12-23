package com.brightkidmont.brilla.my_bright_brain.visual;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brightkidmont.brilla.R;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class DrawingPadActivity extends AppCompatActivity {

    private static final int REQUEST_STORAGE = 2;
    DrawingView dv ;
    private Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawing_pad_layout);

        RelativeLayout canvas = (RelativeLayout) findViewById(R.id.canvas);
        ImageView paints = (ImageView) findViewById(R.id.paints);

        //creating drawingView
        dv = new DrawingView(this);
        canvas.addView(dv);
        canvas.invalidate();
        //setContentView(dv);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(getResources().getColor(R.color.fbutton_color_alizarin)); //setting color for brush
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(10); //setting the size of brush

        paints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dv.clear();
                final BottomSheetDialog dialog = new BottomSheetDialog(new ContextThemeWrapper(DrawingPadActivity.this, R.style.DialogSlideAnim));
                dialog.setContentView(R.layout.paint_dialog);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.show();

                ImageView brush1 = (ImageView) dialog.findViewById(R.id.brush1);
                ImageView brush2 = (ImageView) dialog.findViewById(R.id.brush2);
                ImageView brush3 = (ImageView) dialog.findViewById(R.id.brush3);

                ImageView redPaint = (ImageView) dialog.findViewById(R.id.redPaint);
                ImageView yellowPaint = (ImageView) dialog.findViewById(R.id.yellowPaint);
                ImageView purplePaint = (ImageView) dialog.findViewById(R.id.purplePaint);
                ImageView greenPaint = (ImageView) dialog.findViewById(R.id.greenPaint);
                ImageView bluePaint = (ImageView) dialog.findViewById(R.id.bluePaint);
                ImageView pinkPaint = (ImageView) dialog.findViewById(R.id.pinkPaint);
                ImageView brownPaint = (ImageView) dialog.findViewById(R.id.brownPaint);
                ImageView orangePaint = (ImageView) dialog.findViewById(R.id.orangePaint);
                ImageView darkGreenPaint = (ImageView) dialog.findViewById(R.id.darkGreenPaint);
                ImageView silverPaint = (ImageView) dialog.findViewById(R.id.silverPaint);
                ImageView whitePaint = (ImageView) dialog.findViewById(R.id.whitePaint);
                ImageView blackPaint = (ImageView) dialog.findViewById(R.id.blackPaint);

                ImageView refresh = (ImageView) dialog.findViewById(R.id.refresh);
                ImageView eraser = (ImageView) dialog.findViewById(R.id.eraser);
                ImageView save = (ImageView) dialog.findViewById(R.id.save);

                //different sizes for brush
                brush1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPaint.setXfermode(null);
                        mPaint.setStrokeWidth(10);
                        dialog.dismiss();
                    }
                });
                brush2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPaint.setXfermode(null);
                        mPaint.setStrokeWidth(30);
                        dialog.dismiss();
                    }
                });
                brush3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPaint.setXfermode(null);
                        mPaint.setStrokeWidth(50);
                        dialog.dismiss();
                    }
                });

                //selecting different colors
                redPaint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPaint.setXfermode(null);
                        mPaint.setColor(getResources().getColor(R.color.fbutton_color_alizarin));
                        dialog.dismiss();
                    }
                });
                yellowPaint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPaint.setXfermode(null);
                        mPaint.setColor(getResources().getColor(R.color.fbutton_color_sun_flower));
                        dialog.dismiss();
                    }
                });
                purplePaint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPaint.setColor(getResources().getColor(R.color.fbutton_color_wisteria));
                        dialog.dismiss();
                    }
                });
                greenPaint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPaint.setXfermode(null);
                        mPaint.setColor(getResources().getColor(R.color.fbutton_color_emerald));
                        dialog.dismiss();
                    }
                });
                bluePaint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPaint.setXfermode(null);
                        mPaint.setColor(getResources().getColor(R.color.fbutton_color_peter_river));
                        dialog.dismiss();
                    }
                });
                pinkPaint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPaint.setXfermode(null);
                        mPaint.setColor(getResources().getColor(R.color.f_pink));
                        dialog.dismiss();
                    }
                });
                brownPaint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPaint.setXfermode(null);
                        mPaint.setColor(getResources().getColor(R.color.brown));
                        dialog.dismiss();
                    }
                });
                orangePaint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPaint.setXfermode(null);
                        mPaint.setColor(getResources().getColor(R.color.fbutton_color_carrot));
                        dialog.dismiss();
                    }
                });
                darkGreenPaint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPaint.setXfermode(null);
                        mPaint.setColor(getResources().getColor(R.color.f_dark_green));
                        dialog.dismiss();
                    }
                });
                silverPaint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPaint.setXfermode(null);
                        mPaint.setColor(getResources().getColor(R.color.fbutton_color_concrete));
                        dialog.dismiss();
                    }
                });
                whitePaint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPaint.setXfermode(null);
                        mPaint.setColor(getResources().getColor(R.color.fbutton_color_clouds));
                        dialog.dismiss();
                    }
                });
                blackPaint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPaint.setXfermode(null);
                        mPaint.setColor(getResources().getColor(R.color.navigationBarColor));
                        dialog.dismiss();
                    }
                });

                refresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPaint.setXfermode(null);
                        String title = "Are you sure you want to refresh the drawing pad?.";
                        AlertDialog.Builder builder = new AlertDialog.Builder(DrawingPadActivity.this);
                        builder.setTitle(title);

                        // clear the drawing in drawingView
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface nDialog, int which) {
                                dv.clear();
                                dialog.dismiss();
                                nDialog.dismiss();
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface nDialog, int which) {
                                dialog.dismiss();
                                nDialog.dismiss();
                            }
                        });

                        AlertDialog mDialog = builder.create();
                        mDialog.setCanceledOnTouchOutside(true);
                        mDialog.show();
                    }
                });
                eraser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                        dialog.dismiss();
                    }
                });
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPaint.setXfermode(null);
                        dialog.dismiss();
                        int permissionCheck = ContextCompat.checkSelfPermission(DrawingPadActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        if(permissionCheck == 0) {
                            takeScreenshot();
                        } else {
                            final Dialog dialog2 = new Dialog(DrawingPadActivity.this);
                            dialog2.setContentView(R.layout.permission_dialog);

                            TextView dialog_Title = (TextView) dialog2.findViewById(R.id.dialog_title);
                            dialog_Title.setText(R.string.storage_permission);
                            TextView dialog_message = (TextView) dialog2.findViewById(R.id.dialog_message);
                            dialog_message.setText("This app needs storage permission to save the drawings. You can allow permissions manually by clicking on settings below.");
                            TextView pCancel = (TextView) dialog2.findViewById(R.id.pCancel);
                            TextView pSettings = (TextView) dialog2.findViewById(R.id.pSettings);
                            TextView pOk = (TextView) dialog2.findViewById(R.id.pOk);
                            pCancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog2.dismiss();
                                }
                            });
                            pSettings.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog2.dismiss();
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                            Uri.fromParts("package", getPackageName(), null));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            });
                            pOk.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog2.dismiss();
                                    //ActivityCompat.requestPermissions(DrawingPadActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_STORAGE);
                                    ActivityCompat.requestPermissions(DrawingPadActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE);
                                }
                            });
                            dialog2.show();
                        }

                    }
                });
            }
        });

    }

    //takeScreenshot and save it in a folder named Brilla
    private void takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            //path to store image
            File path = Environment.getExternalStorageDirectory();
            File dir = new File(path+"/Brilla/Images/");
            dir.mkdirs();
            File imageFile = new File(dir,"Brilla-" +  now + ".jpg"); //file name

            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            openScreenshot(imageFile);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takeScreenshot();
                }
            }
        }
    }

    //drawingView class with canvas
    public class DrawingView extends View {

        public int width;
        public  int height;
        private Bitmap mBitmap;
        private Canvas mCanvas;
        private Path mPath;
        private Paint   mBitmapPaint;
        Context context;
        private Paint circlePaint;
        private Path circlePath;

        public DrawingView(Context c) {
            super(c);
            context=c;
            mPath = new Path();
            mBitmapPaint = new Paint(Paint.DITHER_FLAG);
            circlePaint = new Paint();
            circlePath = new Path();
            circlePaint.setAntiAlias(true);
            circlePaint.setColor(Color.BLUE);
            circlePaint.setStyle(Paint.Style.STROKE);
            circlePaint.setStrokeJoin(Paint.Join.MITER);
            circlePaint.setStrokeWidth(3f);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);

            width = w;
            height = h;
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawBitmap( mBitmap, 0, 0, mBitmapPaint);
            canvas.drawPath( mPath,  mPaint);
            canvas.drawPath( circlePath,  circlePaint);
        }

        private float mX, mY;
        private static final float TOUCH_TOLERANCE = 4;

        private void touch_start(float x, float y) {
            mPath.reset();
            mPath.moveTo(x, y);
            mX = x;
            mY = y;
        }

        private void touch_move(float x, float y) {
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
                mX = x;
                mY = y;

                circlePath.reset();
                circlePath.addCircle(mX, mY, 30, Path.Direction.CW);
            }
        }

        private void touch_up() {
            mPath.lineTo(mX, mY);
            circlePath.reset();
            // commit the path to our offscreen
            mCanvas.drawPath(mPath,  mPaint);
            // kill this so we don't double draw
            mPath.reset();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touch_start(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    touch_move(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    touch_up();
                    invalidate();
                    break;
            }
            return true;
        }

        //clearing drawing pad
        public void clear()
        {
            mBitmap = Bitmap.createBitmap(width,height , Bitmap.Config.ARGB_8888);

            mCanvas = new Canvas(mBitmap);
            mPath = new Path();
            mBitmapPaint = new Paint(Paint.DITHER_FLAG);

            //Added later..
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setDither(true);
            mPaint.setColor(getResources().getColor(R.color.fbutton_color_alizarin));
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.setStrokeWidth(10);
            invalidate();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
