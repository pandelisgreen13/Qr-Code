package com.example.padpad.qrcode.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.padpad.qrcode.R;
import com.example.padpad.qrcode.callback.MainCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QrRecyclerViewAdapter extends RecyclerView.Adapter<QrRecyclerViewAdapter.QrItemHolder> {

    private Context context;
    private List<String> qrCodeList;
    private MainCallback mainCallback;

    public QrRecyclerViewAdapter(Context context, List<String> qrCodeList, MainCallback mainCallback) {
        this.context = context;
        this.qrCodeList = qrCodeList;
        this.mainCallback = mainCallback;
    }

    @NonNull
    @Override
    public QrItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QrItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_qr, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QrItemHolder holder, final int position) {
        holder.qrTextView.setText(qrCodeList.get(position));

        holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mainCallback!=null){
                    mainCallback.updateList(position);
                    notifyItemRemoved(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return qrCodeList != null ? qrCodeList.size() : 0;
    }

    class QrItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.qrTextView)
        AppCompatTextView qrTextView;

        @BindView(R.id.deleteImageView)
        AppCompatImageView deleteImageView;

        QrItemHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
