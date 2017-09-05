package com.rubenpla.develop.petagramcoursera;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rubenpla.develop.petagramcoursera.mvp.presenter.MediaDetailActivityPresenter;
import com.rubenpla.develop.petagramcoursera.mvp.presenter.basepresenter.IBaseFragmentPresenter;
import com.rubenpla.develop.petagramcoursera.mvp.view.baseview.IBaseMediaView;
import com.rubenpla.develop.petagramcoursera.pojo.LikeMediaParams;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.design.widget.Snackbar.LENGTH_LONG;

public class MediaDetailActivity extends AppCompatActivity implements IBaseMediaView {
    private static final String KEY_EXTRA_URL = "URL";
    private static final String KEY_EXTRA_LIKES = "LIKES";
    private static final String KEY_EXTRA_USER_ID = "USER_ID";
    private static final String KEY_EXTRA_MEDIA_ID = "MEDIA_ID";

    @BindView(R.id.imgFotoDetalle) ImageView detailPhoto;
    @BindView(R.id.tvLikesDetalle) TextView detailLikes;
    @BindView(R.id.like_container) View likeContainer;

    private IBaseFragmentPresenter presenter;
    private LikeMediaParams mediaParams;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto_foto);

        ButterKnife.bind(this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String url   = extras.getString(KEY_EXTRA_URL);
            int likes    = extras.getInt(KEY_EXTRA_LIKES);
            String userId = extras.getString(KEY_EXTRA_USER_ID);
            String mediaId = extras.getString(KEY_EXTRA_MEDIA_ID);

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
        presenter.likeMedia(mediaParams);
    }

    @Override
    public void syncLikeIconResourceState(boolean isLiked) {

    }

    @Override
    public void showSnackBarSuccesMessage(String succesMessage) {
        Snackbar.make(findViewById(android.R.id.content), succesMessage, LENGTH_LONG).show();
    }

    @Override
    public void showSnackBarErrorMessage(String errorMessage) {
        Snackbar.make(findViewById(android.R.id.content), errorMessage, LENGTH_LONG).show();
    }
}
