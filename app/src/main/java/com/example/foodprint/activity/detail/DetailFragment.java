package com.example.foodprint.activity.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodprint.R;
import com.example.foodprint.activity.login.LoginActivity;
import com.example.foodprint.base.BaseFragment;
import com.example.foodprint.model.restaurant.ParsedRestaurantData;
import com.example.foodprint.model.user.User;
import com.example.foodprint.utils.session.UserSessionRepositoryRepository;
import com.example.foodprint.utils.utility.UtilProvider;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailFragment extends BaseFragment<DetailActivity, DetailContract.Presenter> implements DetailContract.View {
    ParsedRestaurantData parsedRestaurantData;
    private ImageView ivDetail;
    private TextView tvVicinity;
    private TextView tvTypes;
    private TextView tvPriceLevel;
    private TextView tvRating;
    private TextView tvName;
    private Button btDirection;

    public DetailFragment(ParsedRestaurantData parsedRestaurantData){
        this.parsedRestaurantData = parsedRestaurantData;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_detail_restaurant, container, false);
        mPresenter = new DetailPresenter(this, getActivity(), new UserSessionRepositoryRepository(getActivity()));
        mPresenter.start();

        ivDetail = (ImageView) fragmentView.findViewById(R.id.ivDetail);
        tvName = (TextView) fragmentView.findViewById(R.id.tvName);
        tvVicinity = (TextView) fragmentView.findViewById(R.id.tvVicinity);
        tvTypes = (TextView) fragmentView.findViewById(R.id.tvTypes);
        tvPriceLevel = (TextView) fragmentView.findViewById(R.id.tvPriceLevel);
        tvRating = (TextView) fragmentView.findViewById(R.id.tvRating);
        btDirection = (Button) fragmentView.findViewById(R.id.btDirection);

        setData();

        return fragmentView;
    }

    public void gotoNewTask(Intent intent){
        startActivity(intent);
        activity.finish();
    }

    private void setData(){
        //set photo
        ivDetail.setImageResource(R.mipmap.ic_notfound);
        if(parsedRestaurantData.getPhoto() == null)
            ivDetail.setImageResource(R.mipmap.ic_notfound);
        else{
            String URL = settingUpURL("400", "400", parsedRestaurantData.getPhoto());
            Picasso.get().load(URL).into(ivDetail);
        }

        //set name
        tvName.setText(parsedRestaurantData.getName());
        //set vicinity
        tvVicinity.setText(parsedRestaurantData.getVicinity());

        //set types
        String types = getTypes(parsedRestaurantData.getTypes());
        tvTypes.setText(types);

        //set rating
        tvRating.setText(parsedRestaurantData.getRating().toString());

        //set price level
        tvPriceLevel.setText(parsedRestaurantData.getPriceLevel().toString());
    }

    private String getTypes(List<String> data){
        String values = "";

        if(data == null)
            values = "No Match Data";
        else
            for(int i = 0 ; i < data.size(); i++)
                values = data.get(i) + ", " + values;

        return values;
    }

    private String settingUpURL(String width, String height, String reference){
        String URL = "https://maps.googleapis.com/maps/api/place/photo?maxwidth="+width
                +"&maxheight="+height+"&photoreference="+reference+"&key="+ UtilProvider.getKey();

        return URL;
    }

    public void gotoLogin(){
        gotoNewTask(new Intent(activity, LoginActivity.class));
    }

    public void setPresenter(DetailContract.Presenter presenter){
        mPresenter = presenter;
    }
}
