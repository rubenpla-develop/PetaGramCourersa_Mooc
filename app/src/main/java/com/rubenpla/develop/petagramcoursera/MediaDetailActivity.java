package com.rubenpla.develop.petagramcoursera;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rubenpla.develop.petagramcoursera.mvp.presenter.MediaDetailActivityPresenter;
import com.rubenpla.develop.petagramcoursera.mvp.view.baseview.IBaseMediaView;
import com.rubenpla.develop.petagramcoursera.pojo.LikeMediaParams;
import com.rubenpla.develop.petagramcoursera.util.ParamKeys;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.design.widget.Snackbar.LENGTH_LONG;

public class MediaDetailActivity extends AppCompatActivity implements IBaseMediaView {

    @BindView(R.id.imgFotoDetalle) ImageView detailPhoto;
    @BindView(R.id.tvLikesDetalle) TextView detailLikes;
    @BindView(R.id.like_container) View likeContainer;

    private MediaDetailActivityPresenter presenter;
    private LikeMediaParams mediaParams;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto_foto);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String url   = extras.getString(ParamKeys.KEY_EXTRA_URL);
            int likes    = extras.getInt(ParamKeys.KEY_EXTRA_LIKES);
            String userId = extras.getString(ParamKeys.KEY_EXTRA_USER_ID);
            String mediaId = extras.getString(ParamKeys.KEY_EXTRA_MEDIA_ID);

            presenter = new MediaDetailActivityPresenter(this);

            mediaParams = new LikeMediaParams(userId, mediaId);

            detailLikes.setText(String.valueOf(likes));

            Picasso.with(this)
                    .load(url)
                    .placeholder(R.drawable.shock_rave_bonus_icon)
                    .into(detailPhoto);
        }
    }

    @OnClick(R.id.like_container)
    void onClickLikeMedia(View view) {
        presenter.onClickLikeButton(mediaParams);
    }

    @Override
    public void showSnackBarSuccesMessage(String succesMessage) {
        Snackbar.make(findViewById(android.R.id.content), succesMessage, LENGTH_LONG).show();
    }

    @Override
    public void showSnackBarErrorMessage(String errorMessage) {
        Snackbar.make(findViewById(android.R.id.content), errorMessage, LENGTH_LONG).show();
    }

    @Override
    public void onClickLikeButton() {
        //TODO stuff
    }
}
