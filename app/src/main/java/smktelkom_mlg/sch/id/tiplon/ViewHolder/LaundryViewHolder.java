package smktelkom_mlg.sch.id.tiplon.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import smktelkom_mlg.sch.id.tiplon.Interface.ItemClickListener;
import smktelkom_mlg.sch.id.tiplon.R;


public class LaundryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView laundry_name;
    public ImageView laundry_image;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public LaundryViewHolder(@NonNull View itemView) {
        super(itemView);

        laundry_name = (TextView) itemView.findViewById(R.id.laundry_name);
        laundry_image = (ImageView) itemView.findViewById(R.id.laundry_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
