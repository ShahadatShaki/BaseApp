package com.acoder.base.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.acoder.base.R;
import com.acoder.base.databinding.ListUserBinding;

import java.util.ArrayList;

public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.ViewFilesHolder> {

    private ArrayList<String> list;
    private Context context;
    private LayoutInflater layoutInflater;


    public CommonAdapter(Context context, ArrayList<String> list) {

        this.context = context;
        this.list = list;
        layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public CommonAdapter.ViewFilesHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ListUserBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_user, parent, false);
        return new CommonAdapter.ViewFilesHolder(binding);
    }

    @Override
    public void onBindViewHolder(final CommonAdapter.ViewFilesHolder holder, int position) {
        ListUserBinding b = holder.binding;
        String item = list.get(position);

        b.name.setText(item);


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    class ViewFilesHolder extends RecyclerView.ViewHolder {

        private final ListUserBinding binding;

        ViewFilesHolder(final ListUserBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }


}