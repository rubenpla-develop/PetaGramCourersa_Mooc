package com.rubenpla.develop.petagramcoursera;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileInfoDetailActivity extends AppCompatActivity {
    private static final String KEY_EXTRA_URL = "url";
    private static final String KEY_EXTRA_LIKES = "like";

    @BindView(R.id.imgFotoDetalle) ImageView detailPhoto;
    @BindView(R.id.tvLikesDetalle) TextView detailLikes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto_foto);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        String url   = extras.getString(KEY_EXTRA_URL);
        int likes    = extras.getInt(KEY_EXTRA_LIKES);

        detailLikes.setText(String.valueOf(likes));

        Picasso.with(this)
                .load(url)
                .placeholder(R.drawable.shock_rave_bonus_icon)
                .into(detailPhoto);
    }
}
