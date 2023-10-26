package com.yusuf.kotaksaran.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.yusuf.kotaksaran.R;
import com.yusuf.kotaksaran.lapor.Notes;

import java.util.List;

public class NotesAdapter extends  RecyclerView.Adapter<NotesAdapter.ListViewHolder>{
    private List<Notes> mNotesList;

    private OnItemClickCallback onItemClickCallback;

    public NotesAdapter(List<Notes> notesList) {
        this.mNotesList= notesList;
    }

    //Onclick Method
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.report_item, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, @SuppressLint("RecyclerView") int position) {
        try{
            holder.tvSubjek.setText(mNotesList.get(position).getSubjek_laporan());
            holder.tvIsi.setText(mNotesList.get(position).getIsi_laporan());
            String status= mNotesList.get(position).getId_status();
            holder.tvStatus.setText(status);
            if ("diterima".equals(status)) {
                Drawable greenBackground = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.background_green_info);
                holder.tvStatus.setBackground(greenBackground);
            } else if ("ditolak".equals(status)) {
                Drawable redBackground = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.background_red_info);
                holder.tvStatus.setBackground(redBackground);
            } else if ("dalam antrian".equals(status)) {
                Drawable yellowBackground = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.background_yellow_info);
                holder.tvStatus.setBackground(yellowBackground);
            }
            holder.tvTanggal.setText(mNotesList.get(position).getTanggal_lapor());

            holder.itemView.setOnClickListener(v -> {
                showBottomSheetDialog(holder.itemView.getContext(), mNotesList.get(holder.getAdapterPosition()));
            });
        }catch (Exception ex){
            Log.d("ini eksepsi",ex.toString());
        }
    }

    private void showBottomSheetDialog(Context context, Notes data) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.report_item, null);
        bottomSheetDialog.setContentView(view);

        TextView tvDetSubjek = view.findViewById(R.id.tv_det_subjek);
        tvDetSubjek.setText(data.getSubjek_laporan());

        TextView tvDetTanggal = view.findViewById(R.id.tv_det_tanggal);
        tvDetTanggal.setText(data.getTanggal_lapor());

        TextView tvDetStatus = view.findViewById(R.id.tv_det_status);
        String status = data.getId_status();
        if ("diterima".equals(status)) {
            Drawable greenBackground = ContextCompat.getDrawable(context, R.drawable.background_green_info);
            tvDetStatus.setBackground(greenBackground);
        } else if ("ditolak".equals(status)) {
            Drawable redBackground = ContextCompat.getDrawable(context, R.drawable.background_red_info);
            tvDetStatus.setBackground(redBackground);
        } else if ("dalam antrian".equals(status)) {
            Drawable yellowBackground = ContextCompat.getDrawable(context, R.drawable.background_yellow_info);
            tvDetStatus.setBackground(yellowBackground);
        }

        TextView tvDetIsi = view.findViewById(R.id.tv_det_isi);
        tvDetIsi.setText(data.getIsi_laporan());

        TextView tvDetDokumen = view.findViewById(R.id.tv_det_dokumen);
        tvDetDokumen.setText(data.getDokumen());

        bottomSheetDialog.show();
    }


    @Override
    public int getItemCount() {
        return mNotesList.size();
    }



    public class ListViewHolder extends RecyclerView.ViewHolder {


        TextView tvSubjek, tvIsi, tvTanggal, tvStatus;
        public int count;
        public TextView mCount;
        public Button bttambah;
        public Button btnIncrement,btnDecrement;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubjek = itemView.findViewById(R.id.tv_subjek);
            tvIsi = itemView.findViewById(R.id.tv_isi);
            tvTanggal  = itemView.findViewById(R.id.tv_tanggal);
            tvStatus = itemView.findViewById(R.id.tv_status);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Notes data);
    }
}
