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
import com.rubenpla.develop.petagramcoursera.mvp.view.IRecyclerViewFragmentView;
import com.rubenpla.develop.petagramcoursera.mvp.view.MainActivityView;
import com.rubenpla.develop.petagramcoursera.pojo.LikeMediaParams;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PetGridAdapter extends RecyclerView.Adapter<PetGridAdapter.PetViewHolder> implements
        AdapterView.OnItemClickListener {

    private Context context;
    private ArrayList<PetModel> list;
    private RecyclerView.ViewHolder viewHolder;

    public PetGridAdapter(Context context, ArrayList<PetModel> list) {
        this.list = list;
        this.context = context;
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
    public PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pet_item, parent, false);
        PetViewHolder viewHolder = new PetViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PetViewHolder holder, final int position) {
        holder.bindPet(position);
        holder.petPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MediaDetailActivity.class);
                intent.putExtra("url", list.get(position).geturlPhoto());
                intent.putExtra("like", list.get(position).getLikes());
                context.startActivity(intent);
            }
        });


        holder.likePhotoContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = list.get(position).getId();
                String mediaId = list.get(position).getMediaId();
                LikeMediaParams params = new LikeMediaParams(user, mediaId);
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
    public class PetViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvLikes) TextView petLikes;
        @BindView(R.id.imgFoto) ImageView petPhoto;
        @BindView(R.id.like_photo_container) View likePhotoContainer;

        public PetViewHolder(View itemView) {
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
