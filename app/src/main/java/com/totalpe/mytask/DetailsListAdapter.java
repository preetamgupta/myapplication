package com.totalpe.mytask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DetailsListAdapter extends RecyclerView.Adapter<DetailsListAdapter.ProductViewHolder> {
    private Context mCtx;
    private List<Details> detailsList;


    public DetailsListAdapter(Context mCtx, List<Details> detailsList) {
        this.mCtx = mCtx;
        this.detailsList = detailsList;
    }

    @Override
    public DetailsListAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_all_details, null);
        return new DetailsListAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DetailsListAdapter.ProductViewHolder holder, final int position) {
        //getting the product of the specified position
        final Details details = detailsList.get(position);

        holder.tv_email.setText("Email : "+details.getEmail());
        holder.tv_mobile.setText("Mobile : "+details.getMobile());


    }

    @Override
    public int getItemCount() {
        return detailsList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_email, tv_mobile;


        public ProductViewHolder(View itemView) {
            super(itemView);

            tv_email = itemView.findViewById(R.id.tv_email);
            tv_mobile = itemView.findViewById(R.id.tv_mobile);
        }
    }
}
