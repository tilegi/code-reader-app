package com.tilegi.codereader;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{
    private List<InfoModel> infoModelList;

    public ItemAdapter(List<InfoModel> infoModelList) {
        this.infoModelList = infoModelList;
    }
    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        holder.setData(
                infoModelList.get(position).getSaveName(),
                infoModelList.get(position).getRegisterName(),
                infoModelList.get(position).getRimType(),
                infoModelList.get(position).getTireType(),
                infoModelList.get(position).getSizeName(),
                infoModelList.get(position).getRimCodeInside(),
                infoModelList.get(position).getRimCodeOutside(),
                infoModelList.get(position).getTireCode(),
                position
        );
    }

    @Override
    public int getItemCount() {
        return infoModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView saveNameTxt,
                sizeNameTxt,
                registerTxt,
                rimTypeTxt,
                tireTypeTxt,
                rimCodeInsideTxt,
                rimCodeOutsideTxt,
                tireCodeTxt;
        private ImageView deleteIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            saveNameTxt = itemView.findViewById(R.id.saveTxt);
            sizeNameTxt = itemView.findViewById(R.id.sizeTxt);
            registerTxt = itemView.findViewById(R.id.registerName);
            rimTypeTxt = itemView.findViewById(R.id.rimType);
            tireTypeTxt = itemView.findViewById(R.id.tireType);
            rimCodeInsideTxt = itemView.findViewById(R.id.rimInTxt);
            rimCodeOutsideTxt = itemView.findViewById(R.id.rimOutTxt);
            tireCodeTxt = itemView.findViewById(R.id.tireTxt);
            deleteIcon = itemView.findViewById(R.id.deleteIcon);
        }
        public void setData(String saveName,String registerName,String rimType,String tireType, String sizeName, String rimCodeInside, String rimCodeOutside, String tireCode,int position) {
            saveNameTxt.setText(saveName);
            registerTxt.setText(registerName);
            rimTypeTxt.setText(rimType);
            tireTypeTxt.setText(tireType);
            sizeNameTxt.setText(sizeName);
            rimCodeInsideTxt.setText(rimCodeInside);
            rimCodeOutsideTxt.setText(rimCodeOutside);
            tireCodeTxt.setText(tireCode);
            deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    infoModelList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position,infoModelList.size());
                }
            });
        }

    }
}
