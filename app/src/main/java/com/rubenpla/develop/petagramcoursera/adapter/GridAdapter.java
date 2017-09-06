package com.rubenpla.develop.petagramcoursera.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.rubenpla.develop.petagramcoursera.MediaDetailActivity;
import com.rubenpla.develop.petagramcoursera.R;
import com.rubenpla.develop.petagramcoursera.mvp.model.PetModel;
import com.rubenpla.develop.petagramcoursera.mvp.presenter.basepresenter.BaseMediaPresenter;
import com.rubenpla.develop.petagramcoursera.mvp.view.IRecyclerViewFragmentView;
import com.rubenpla.develop.petagramcoursera.mvp.view.IViewHolderView;
import com.rubenpla.develop.petagramcoursera.pojo.LikeMediaParams;
import com.rubenpla.develop.petagramcoursera.util.ParamKeys;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.PetagramViewHolder> implements
        AdapterView.OnItemClickListener {

    private Context context;
    private ArrayList<PetModel> list;
    private RecyclerView.ViewHolder viewHolder;
    private LikeMediaParams params;
    BaseMediaPresenter presenter;


    public GridAdapter(Context context, ArrayList<PetModel> list) {
        this.list = list;
        this.context = context;
    }

    public GridAdapter(Context context, ArrayList<PetModel> list,
                       BaseMediaPresenter presenter) {
        this.list = list;
        this.context = context;
        this.presenter = presenter;
    }

    //UPDATE ADAPTER CONTENT SYNCHRONIOUSLY WITHOU CALLING CONSTRUCTOR AGAIN
    public synchronized void updateListData(Context context, ArrayList<PetModel> list) {
        if (list != null && list.size() > 0) {
            this.list = list;
            this.context = context;

            notifyDataSetChanged();
        }
    }

    @Override
    public PetagramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pet_item, parent, false);
        PetagramViewHolder viewHolder = new PetagramViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PetagramViewHolder holder, final int position) {
        holder.bindPet(position);
        holder.petPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MediaDetailActivity.class);
                intent.putExtra(ParamKeys.KEY_EXTRA_URL, list.get(position).geturlPhoto());
                intent.putExtra(ParamKeys.KEY_EXTRA_LIKES, list.get(position).getLikes());
                intent.putExtra(ParamKeys.KEY_EXTRA_USER_ID, list.get(position).getId());
                intent.putExtra(ParamKeys.KEY_EXTRA_MEDIA_ID, list.get(position).getMediaId());
                context.startActivity(intent);
            }
        });


        holder.likePhotoContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = list.get(position).getId();
                String mediaId = list.get(position).getMediaId();
                LikeMediaParams params = new LikeMediaParams(user, mediaId);
                presenter.onClickLikeButton(params);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((IRecyclerViewFragmentView) this).showPetPhoto(context, view, position);
    }


    //VIEWHOLDER CLASS
    public class PetagramViewHolder extends RecyclerView.ViewHolder implements IViewHolderView {

        @BindView(R.id.tvLikes) TextView petLikes;
        @BindView(R.id.imgFoto) ImageView petPhoto;
        @BindView(R.id.like_photo_container) View likePhotoContainer;

        public PetagramViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindPet(int position) {
            petLikes.setText(String.valueOf(list.get(position).getLikes()));
            Picasso.with(context)
                    .load(list.get(position).geturlPhoto())
                    .placeholder(R.drawable.shock_rave_icon)
                    .error(R.drawable.shock_rave_bonus_icon)
                    .into(petPhoto);
        }
    }
}
