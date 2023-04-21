package com.example.ktratrenlop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktratrenlop.R;
import com.example.ktratrenlop.model.CongViec;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHoler>{

    List<CongViec> list;

    private CVListener cvListener;

    public void setCvListener(CVListener cvListener) {
        this.cvListener = cvListener;
    }

    public RecyclerViewAdapter() {
        list = new ArrayList<>();
    }

    public List<CongViec> getList() {
        return list;
    }

    public void setList(List<CongViec> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public CongViec getCV(int position){
        return list.get(position);
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        CongViec cv = list.get(position);
        if(cv==null){
            return;
        }
        holder.txtTen.setText(cv.getTen());
        holder.txtND.setText(cv.getNoidung());
        holder.txtngay.setText(cv.getNgay());
        holder.txtTT.setText(cv.isTinhtrang()?"Da hoan thanh":"Chua hoan thanh");
        holder.txtCT.setText(cv.isCongtac()?"Lam chung":"1 minh");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtTen, txtND, txtTT, txtngay, txtCT;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txtTen);
            txtND = itemView.findViewById(R.id.txtND);
            txtngay = itemView.findViewById(R.id.txtngay);
            txtTT = itemView.findViewById(R.id.txtTT);
            txtCT = itemView.findViewById(R.id.txtCT);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(cvListener!=null){
                cvListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface CVListener{
        void onItemClick(View view, int position);
    }
}
