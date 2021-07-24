package com.example.corowin.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.corowin.R;
import com.example.corowin.databinding.CentreRvItemBinding;
import com.example.corowin.model.VaccineModel;
import com.example.corowin.view.Web_Screen;

import java.util.List;

public class VaccinationAdapter extends RecyclerView.Adapter<VaccinationAdapter.ViewHolder> {
private LayoutInflater layoutInflater;
private List<VaccineModel> list_vaccine_center;
public Context c;

public VaccinationAdapter(Context mcontext,  List<VaccineModel> list_vaccine_center)
{
    this.layoutInflater=LayoutInflater.from(mcontext);
    this.list_vaccine_center=list_vaccine_center;
    c=mcontext;
}

    @NonNull
    @Override
    public ViewHolder  onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
       View view=layoutInflater.inflate(R.layout.centre_rv_item,parent,false);

        return  new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull  VaccinationAdapter.ViewHolder holder, int position) {

    holder.binding.idTVCenterName.setText(list_vaccine_center.get(position).getVaccineCentre());
        holder.binding.idTVCenterAddress.setText(list_vaccine_center.get(position).getVaccineCenterAddress());
        holder.binding.idTVCenterTimings.setText(String.valueOf(list_vaccine_center.get(position).getVaccinationTimings())+"-"+
              String.valueOf( list_vaccine_center.get(position).getVaccineCenterTime()));
        holder.binding.idTVVaccineName.setText(list_vaccine_center.get(position).getVaccineName());
        holder.binding.idTVAvaliablity.setText(String.valueOf(list_vaccine_center.get(position).getVaccineAvailaibility()));
        holder.binding.idTVAgeLimit.setText(String.valueOf(list_vaccine_center.get(position).getVaccinationAge()));
        holder.binding.vaccineCharges.setText(list_vaccine_center.get(position).getVaccinationCharges());

    }

    @Override
    public int getItemCount() {
        return list_vaccine_center.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        CentreRvItemBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CentreRvItemBinding.bind(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(v.getContext(),Web_Screen.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    v.getContext().startActivity(i);
                }
            });
        }

    }
}
