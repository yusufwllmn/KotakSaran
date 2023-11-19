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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.yusuf.kotaksaran.Model.Status;
import com.yusuf.kotaksaran.Model.Subjek;
import com.yusuf.kotaksaran.R;
import com.yusuf.kotaksaran.Model.Laporan;

import java.util.List;

public class LaporanAdapter extends RecyclerView.Adapter<LaporanAdapter.ListViewHolder> {
    private List<Laporan> listLaporan;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public LaporanAdapter(List<Laporan> laporanList) {
        this.listLaporan = laporanList;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.report_item, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        try {
            Laporan laporan = listLaporan.get(position);
            Subjek bagian = laporan.getBagian();
            holder.tvSubjek.setText(bagian.getBagian());
            holder.tvIsi.setText(laporan.getIsi_laporan());
            Status status = laporan.getStatus();
            if (status != null) {
                holder.tvStatus.setText(status.getStatus());
                String statusValue = status.getStatus();
                if ("diterima".equals(statusValue)) {
                    Drawable greenBackground = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.background_green_info);
                    holder.tvStatus.setBackground(greenBackground);
                } else if ("ditolak".equals(statusValue)) {
                    Drawable redBackground = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.background_red_info);
                    holder.tvStatus.setBackground(redBackground);
                } else if ("dalam antrian".equals(statusValue)) {
                    Drawable yellowBackground = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.background_yellow_info);
                    holder.tvStatus.setBackground(yellowBackground);
                }
            }
            holder.tvTanggal.setText(laporan.getTanggal_lapor());

            holder.itemView.setOnClickListener(v -> showBottomSheetDialog(holder.itemView.getContext(), listLaporan.get(holder.getAdapterPosition())));

        } catch (Exception ex) {
            Log.d("ini eksepsi", ex.toString());
        }
    }

    private void showBottomSheetDialog(Context context, Laporan laporan) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.BottomSheetDialogTheme);
        View view = LayoutInflater.from(context).inflate(R.layout.activity_detail, null);
        bottomSheetDialog.setContentView(view);

        TextView tvDetSubjek = view.findViewById(R.id.tv_det_subjek);
        Subjek bagian = laporan.getBagian();
        tvDetSubjek.setText(bagian.getBagian());

        TextView tvDetTanggal = view.findViewById(R.id.tv_det_tanggal);
        tvDetTanggal.setText(laporan.getTanggal_lapor());

        TextView tvDetStatus = view.findViewById(R.id.tv_det_status);
        Status status = laporan.getStatus();
        if (status != null) {
            tvDetStatus.setText(status.getStatus());

            String statusValue = status.getStatus();
            if ("diterima".equals(statusValue)) {
                Drawable greenBackground = ContextCompat.getDrawable(context, R.drawable.background_green_info);
                tvDetStatus.setBackground(greenBackground);
            } else if ("ditolak".equals(statusValue)) {
                Drawable redBackground = ContextCompat.getDrawable(context, R.drawable.background_red_info);
                tvDetStatus.setBackground(redBackground);
            } else if ("dalam antrian".equals(statusValue)) {
                Drawable yellowBackground = ContextCompat.getDrawable(context, R.drawable.background_yellow_info);
                tvDetStatus.setBackground(yellowBackground);
            }
        }

        TextView tvDetIsi = view.findViewById(R.id.tv_det_isi);
        tvDetIsi.setText(laporan.getIsi_laporan());

        TextView tvDetDokumen = view.findViewById(R.id.tv_det_dokumen);
        String dokumen = laporan.getDokumen();
        if (dokumen != null && !dokumen.isEmpty()) {
            tvDetDokumen.setText(dokumen);
        } else {
            tvDetDokumen.setVisibility(View.GONE);
        }

        bottomSheetDialog.show();
    }

    @Override
    public int getItemCount() {
        return listLaporan.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tvSubjek, tvIsi, tvTanggal, tvStatus;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubjek = itemView.findViewById(R.id.tv_subjek);
            tvIsi = itemView.findViewById(R.id.tv_isi);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
            tvStatus = itemView.findViewById(R.id.tv_status);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Laporan data);
    }
}
