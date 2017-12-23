package com.brightkidmont.brilla.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.brightkidmont.brilla.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.brightkidmont.brilla.HomePageActivity.genderDress;
import static com.brightkidmont.brilla.interactions.DressActivity.image;
import static com.brightkidmont.brilla.interactions.DressActivity.mpBoys;
import static com.brightkidmont.brilla.interactions.DressActivity.mpGirls;

public class DressAdapter extends RecyclerView.Adapter<DressAdapter.ViewHolder>{

    private ArrayList<String> alName;
    private ArrayList<String> alImage1;
    private Context context;

    public DressAdapter(Context context, ArrayList<String> alName, ArrayList<String> alImage1) {
        super();
        this.context = context;
        this.alName = alName;
        this.alImage1 = alImage1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.dress_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return alName.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvSpecies.setText(alName.get(i));
        Picasso.with(context).load(alImage1.get(i)).placeholder(R.drawable.bright_kid_bg).error(R.drawable.bright_kid_bg).into(viewHolder.imgThumbnail);

        //onClicking, respective image will be displayed
        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    Toast.makeText(context, alName.get(position) + " (Long click)", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean playing = false;
                    for(int i=0; i<9; i++){
                        if(mpBoys[i].isPlaying()) {
                            playing = true;
                            break;
                        }
                    }
                    for(int i=0; i<10; i++){
                        if(mpGirls[i].isPlaying()) {
                            playing = true;
                            break;
                        }
                    }
                    if(playing){
                        Toast.makeText(context, "Please wait a sec", Toast.LENGTH_SHORT).show();
                    } else {
                        audio(position);
                        Picasso.with(context).load(alImage1.get(position)).placeholder(R.drawable.bright_kid_bg).error(R.drawable.bright_kid_bg).into(image);
                    }
                }
            }

        });
    }

    //respective audio files
    private void audio(final int position){
        if(genderDress.equals("BOY")){
            mpBoys[position].start();
        } else {
            mpGirls[position].start();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        ImageView imgThumbnail;
        TextView tvSpecies;
        private ItemClickListener clickListener;

        ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            tvSpecies = (TextView) itemView.findViewById(R.id.tv_species);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }
    }

}
