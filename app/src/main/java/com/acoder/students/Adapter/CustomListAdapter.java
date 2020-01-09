package com.acoder.students.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acoder.students.ModelClass.MerchantProfile;
import com.acoder.students.R;
import com.acoder.students.Utility.Constants;
import com.acoder.students.databinding.ListUserBinding;
import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;

/**
 * Created by Shaki
 */
public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<MerchantProfile> dataList;
    ListUserBinding b;


    public CustomListAdapter(Context context, ArrayList<MerchantProfile> dataList) {
        this.context = context;
        this.dataList = dataList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        b = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.list_user, viewGroup, false);

        return new ViewHolder(b.getRoot());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        MerchantProfile merchantProfile = dataList.get(i);

        b.name.setText(merchantProfile.getName());
        b.userId.setText( merchantProfile.getId());

        try {
            Constants.loadImageSqure(b.imageView, merchantProfile.getThumbnail(), 20, 3);
        } catch (Exception e) {
            Crashlytics.logException(e);
        }


    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        ViewHolder(View itemView) {

            super(itemView);


//            itemView.callOnClick();
            itemView.setClickable(true);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
//            Intent i = new Intent(context, MerchantActivity.class);
//            i.putExtra("obj", dataList.get(getAdapterPosition()));
//            context.startActivity(i);
        }


    }


}
