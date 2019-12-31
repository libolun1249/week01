package com.bawei.libolun20191231.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.libolun20191231.CodeActivity;
import com.bawei.libolun20191231.R;
import com.bawei.libolun20191231.bean.HomeBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 李博伦
 * @createTime 2019/12/31 9:03
 * @description 适配器
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

    public Context context;
    public List<HomeBean.RankingBean> list;

    public MyAdapter(Context context, List<HomeBean.RankingBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_layout, null);
        VH vh = new VH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        //圆角图片
        Glide.with(context).load(list.get(position).getAvatar())
                .transform(new RoundedCorners(10))
                .into(holder.iv);
        holder.name.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, CodeActivity.class));
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class VH extends RecyclerView.ViewHolder {

        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.name)
        TextView name;

        public VH(@NonNull View itemView) {
            super(itemView);
            //初始化
            ButterKnife.bind(this, itemView);
        }
    }
}
