package com.example.corowin.view;

import androidx.annotation.MainThread;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.corowin.R;
import com.example.corowin.adapter.VaccinationAdapter;
import com.example.corowin.databinding.ActivityMainBinding;
import com.example.corowin.model.VaccineModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {



    String baseURL ="https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=";
    ActivityMainBinding binding;
    String areaPin,avlDate;
     ArrayList<VaccineModel> vaccination_centers;
     VaccinationAdapter vaccinationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
         //       WindowManager.LayoutParams.FLAG_FULLSCREEN);


        vaccination_centers=new ArrayList<>();
        onCLickSetup();

    }

    private void onCLickSetup() {
        binding.idBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressBar.setVisibility(View.VISIBLE);
                DialogFragment dp=new Pickdate();
                dp.show(getSupportFragmentManager(),"pick a date");
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
       Calendar k= Calendar.getInstance();
k.set(Calendar.YEAR,year);
k.set(Calendar.MONTH,month);
k.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-YYYY");
        dateFormat.setTimeZone(k.getTimeZone());
        String d=dateFormat.format(k.getTime());
         setUp(d);


    }

    private void setUp(String d) {
        avlDate=d;
        fetchData();
    }

    private void fetchData() {
if(vaccination_centers != null) {
    vaccination_centers.clear();
}
        areaPin=binding.idEdtPinCode.getText().toString();
        String url_api=baseURL+areaPin+"&date="+avlDate;
        JsonObjectRequest ObjectRequest =new JsonObjectRequest(Request.Method.GET, url_api,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray centerArray = response.getJSONArray("centers");
                    if (centerArray.length()==0) {
                        Toast.makeText(MainActivity.this, "No Center Found", Toast.LENGTH_SHORT).show();
                    }

                    for (int i = 0; i < centerArray.length(); i++) {
                        JSONObject centerObject = centerArray.getJSONObject(i);
                        VaccineModel vaccineModel=new VaccineModel();
                       vaccineModel.setVaccineCentre(centerObject.getString("name"));
                        vaccineModel.setVaccineCenterAddress(centerObject.getString("address"));
                        vaccineModel.setVaccinationTimings(centerObject.getString("from"));
                        vaccineModel.setVaccineCenterTime(centerObject.getString("to"));
                        vaccineModel.setVaccinationCharges(centerObject.getString("fee_type"));


                         JSONObject sessionObject = centerObject.getJSONArray("sessions").getJSONObject(0);

                        vaccineModel.setVaccinationAge(sessionObject.getInt("min_age_limit"));
                        vaccineModel.setVaccineName(sessionObject.getString("vaccine")) ;
                        vaccineModel.setVaccineAvailaibility(sessionObject.getInt("available_capacity"));

                    vaccination_centers.add(vaccineModel);

                    }
                    VaccinationAdapter vaccinationAdapter = new VaccinationAdapter(getApplicationContext(),vaccination_centers);
                    binding.centersRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    binding.centersRV.setAdapter(vaccinationAdapter);
                    vaccinationAdapter.notifyDataSetChanged();
                    binding.progressBar.setVisibility(View.INVISIBLE);

                } catch (Exception e) {
                    binding.progressBar.setVisibility(View.INVISIBLE);
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                binding.progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(),"Please Enter correct pincode",Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(ObjectRequest);

    }
}