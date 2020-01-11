package com.bw.miaoheng20200111;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.miaoheng20200111.entity.ClsEntity;

import java.util.Date;
import java.util.List;

/**
 * 时间 :2020/1/11  8:56
 * 作者 :苗恒
 * 功能 :
 */
class ClsAdapter  extends RecyclerView.Adapter<ClsAdapter.MyViewHolder> {
    public Context context;
    public List<ClsEntity.OrderListBean> list;

    public ClsAdapter(Context context, List<ClsEntity.OrderListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(View.inflate(context, R.layout.item, null));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       holder.orderListId.setText("订单编号:"+list.get(position).getOrderId());
       holder.orderSend.setText("                       快递公司:"+list.get(position).getExpressCompName());
       final List<ClsEntity.OrderListBean.DetailListBean> detailList = list.get(position).getDetailList();
        String commodityPic = null;
       if(detailList.size()>0){
           for (int i = 0; i < detailList.size(); i++) {
                commodityPic = detailList.get(i).getCommodityPic();
               holder.tv_xinxi.setText(detailList.get(i).getCommodityName());
               holder.tv_price.setText("￥"+detailList.get(i).getCommodityPrice());
           }
           String[] split = commodityPic.split(",");
           Glide.with(context).load(split[0]).into(holder.imageView);
       }




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final TextView tv_price;
        private final TextView tv_xinxi;
        private final TextView orderListId;
        private final TextView orderSend;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_xinxi = itemView.findViewById(R.id.tv_xinxi);
            orderListId = itemView.findViewById(R.id.orderListId);
            orderSend = itemView.findViewById(R.id.orderSend);

        }
    }
}
