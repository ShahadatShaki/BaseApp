//package com.acoder.students.Adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//
//import androidx.databinding.DataBindingUtil;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.acoder.students.R;
//
//import java.util.ArrayList;
//
//public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.ViewFilesHolder> {
//
//    private ArrayList<String> list;
//    private Context context;
//    private LayoutInflater layoutInflater;
//
//
//    public CommonAdapter(Context context, ArrayList<String> list) {
//
//        this.context = context;
//        this.list = list;
//        layoutInflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    public void setData(ArrayList<String> list) {
//        this.list = list;
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public CommonAdapter.ViewFilesHolder onCreateViewHolder( ViewGroup parent, int viewType) {
//        if (layoutInflater == null) {
//            layoutInflater = LayoutInflater.from(parent.getContext());
//        }
//        ListSearchBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_search, parent, false);
//        return new CommonAdapter.ViewFilesHolder(binding);
//    }
//
//    @Override
//    public void onBindViewHolder(final CommonAdapter.ViewFilesHolder holder, int position) {
//        ListSearchBinding b = holder.binding;
//        String item = list.get(position);
//
//        b.name.setText(item);
//
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//
//    class ViewFilesHolder extends RecyclerView.ViewHolder {
//
//        private final ListSearchBinding binding;
//
//        ViewFilesHolder(final ListSearchBinding itemBinding) {
//            super(itemBinding.getRoot());
//            this.binding = itemBinding;
//        }
//    }
//
//
//}