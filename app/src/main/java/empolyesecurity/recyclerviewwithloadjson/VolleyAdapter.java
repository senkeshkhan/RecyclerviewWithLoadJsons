package empolyesecurity.recyclerviewwithloadjson;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by vivid on 1/11/17.
 */

public class VolleyAdapter extends RecyclerView.Adapter<VolleyAdapter.MyViewHolder> {
    private static final String BASE_URL_IMG = "https://image.tmdb.org/t/p/w150";
     Context mContext;
    private List<VolleyBean> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.textViewName);
            count = (TextView) view.findViewById(R.id.textViewVersion);
            thumbnail = (ImageView) view.findViewById(R.id.imageView);

        }
    }


    public VolleyAdapter(Context mContext, List<VolleyBean> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        VolleyBean album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.count.setText(album.getDate());
    /*    Picasso.with(mContext)
                .load(BASE_URL_IMG+album.getImageurl())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.thumbnail);*/
        Glide.with(mContext)
                .load(BASE_URL_IMG+album.getImageurl())
                .into(holder.thumbnail);
        // loading album cover using Glide library
        //Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);


    }




    @Override
    public int getItemCount() {
        return albumList.size();
    }
}