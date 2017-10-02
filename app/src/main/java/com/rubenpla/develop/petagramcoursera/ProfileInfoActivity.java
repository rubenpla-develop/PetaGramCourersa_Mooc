package com.rubenpla.develop.petagramcoursera;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.rubenpla.develop.petagramcoursera.mvp.model.ProfileInfo;
import com.rubenpla.develop.petagramcoursera.mvp.presenter.ProfileInfoActivityPresenter;
import com.rubenpla.develop.petagramcoursera.util.ParamKeys;
import com.rubenpla.develop.petagramcoursera.view.activity.PetagramActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileInfoActivity extends PetagramActivity {

    @BindView(R.id.profile_info_photo)
    CircleImageView userPhoto;
    @BindView(R.id.profile_info_username) TextView username;

    private ProfileInfoActivityPresenter presenter;

    private ProfileInfo profileInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profile_info_activity);

        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            profileInfo = bundle.getParcelable(ParamKeys.KEY_EXTRA_PROFILE_INFO);
        }

        loadProfile();
    }

    private void loadProfile() {
        Picasso.with(this).load(profileInfo.getUrlAvatar())
                .noFade()
                .placeholder(R.drawable.shock_rave_icon)
                .error(R.drawable.forest_mushroom_icon)
                .into(userPhoto);

        username.setText(profileInfo.getUserName());
    }


}
