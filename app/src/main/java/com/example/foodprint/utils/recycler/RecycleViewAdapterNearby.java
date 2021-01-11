package com.example.foodprint.utils.recycler;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.foodprint.R;
import com.example.foodprint.model.restaurant.ParsedRestaurantData;
import com.example.foodprint.utils.utility.UtilProvider;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecycleViewAdapterNearby extends RecyclerView.Adapter<RecycleViewAdapterNearby.MyViewHolder>{
    private static ArrayList<ParsedRestaurantData> mDataset;
    private static MyClickListener myClickListener;
    private Resources resources;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView ivLaundryPhoto;
        TextView tvLaundryName;
        TextView tvLaundryDistance;
        TextView tvRating;
        Button btStatus;
        public MyViewHolder(View itemView){
            super(itemView);
            ivLaundryPhoto = (ImageView) itemView.findViewById(R.id.ivLaundryPhoto);
            tvLaundryName = (TextView) itemView.findViewById(R.id.tvLaundryName);
            tvLaundryDistance = (TextView) itemView.findViewById(R.id.tvLaundryDistance);
            tvRating = (TextView) itemView.findViewById(R.id.tvRating);
            btStatus = (Button) itemView.findViewById(R.id.btStatus);
            itemView.setOnClickListener(this);
        }

        public void onClick(View view){
            int position = getAdapterPosition();
            myClickListener.onItemClick(position, view);
        }
    }

    public RecycleViewAdapterNearby(ArrayList<ParsedRestaurantData> myDataset, Resources resources){
        mDataset = myDataset;
        this.resources = resources;
    }

    public RecycleViewAdapterNearby.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent, false);
        RecycleViewAdapterNearby.MyViewHolder myViewHolder = new RecycleViewAdapterNearby.MyViewHolder(view);

        return myViewHolder;
    }

    public void onBindViewHolder(MyViewHolder holder, int position){
        holder.tvLaundryDistance.setText(String.format("%.2f", mDataset.get(position).getDistance()) + " KM From Your Location");

        //Set Laundry Name
        holder.tvLaundryName.setText(mDataset.get(position).getName());

        //Set Rating
        if(mDataset.get(position).getRating() == null)
            holder.tvRating.setText("Not Rated");
        else
            holder.tvRating.setText(mDataset.get(position).getRating().toString());


        holder.ivLaundryPhoto.setImageResource(R.mipmap.ic_notfound);
        //Set Photo
        if(mDataset.get(position).getPhoto() == null)
            holder.ivLaundryPhoto.setImageResource(R.mipmap.ic_notfound);
        else{
            String URL = settingUpURL("400", "400", mDataset.get(position).getPhoto());
            Picasso.get().load(URL).into(holder.ivLaundryPhoto);
        }

        //Set Status Open
        Drawable greenButton = resources.getDrawable(R.drawable.custom_status);
        Drawable redButton = resources.getDrawable(R.drawable.custom_status_red);
        if(mDataset.get(position).getOpen() == null){
            holder.btStatus.setText("Close");
            holder.btStatus.setBackground(redButton);
        }else {
            if(mDataset.get(position).getOpen() == true){
                holder.btStatus.setText("Open");
                holder.btStatus.setBackground(greenButton);
            }else{
                holder.btStatus.setText("Close");
                holder.btStatus.setBackground(redButton);
            }
        }
    }

    private String settingUpURL(String width, String height, String reference){
        String URL = "https://maps.googleapis.com/maps/api/place/photo?maxwidth="+width
                +"&maxheight="+height+"&photoreference="+reference+"&key="+ UtilProvider.getKey();

        return URL;
    }

    private Double countDistance(LatLng from, LatLng to){
        return SphericalUtil.computeDistanceBetween(from ,to) / 1000;
    }

    public int getItemCount(){
        return mDataset.size();
    }

    public void setOnItemClickListener(MyClickListener myClickListener){
        this.myClickListener = myClickListener;
    }

    public interface MyClickListener{
        public void onItemClick(int position, View view);
    }
}
