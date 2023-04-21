package com.example.ktratrenlop.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktratrenlop.R;
import com.example.ktratrenlop.activities.MainActivity;
import com.example.ktratrenlop.activities.UpdateDeleteActivity;
import com.example.ktratrenlop.adapter.RecyclerViewAdapter;
import com.example.ktratrenlop.database.SQLiteHelper;
import com.example.ktratrenlop.model.CongViec;

import java.util.Calendar;
import java.util.List;

public class FragmentTK extends Fragment implements View.OnClickListener{
    private SearchView searchView;
    private EditText nbd, nkt;
    private Button btnSearch;
    RecyclerView rviewTK;
    private RecyclerViewAdapter adapter;
    SQLiteHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tk, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rviewTK.setLayoutManager(manager);
        adapter = new RecyclerViewAdapter();
        db = new SQLiteHelper(getContext());
        List<CongViec> list = db.getAll();
        adapter.setList(list);
        rviewTK.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<CongViec> l = db.searchCvByTen(s);
                adapter.setList(l);
                return true;
            }
        });

        nbd.setOnClickListener(this);
        nkt.setOnClickListener(this);
        btnSearch.setOnClickListener(this);

    }

    private void initView(View view) {
        searchView = view.findViewById(R.id.search);
        nbd = view.findViewById(R.id.ngayBD);
        nkt = view.findViewById(R.id.ngayKT);
        btnSearch = view.findViewById(R.id.btnSearch);
        rviewTK = view.findViewById(R.id.rviewTK);

    }


    @Override
    public void onClick(View view) {
        if(view == nbd){
            final Calendar c = Calendar.getInstance();
            int y = c.get(Calendar.YEAR);
            int m = c.get(Calendar.MONTH);
            int d = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String date = "";
                    if(m>8){
                        date = y+"/"+(m+1)+"/"+d;
                    }else {
                        date = y+"/0"+(m+1)+"/"+d;
                    }
                    nbd.setText(date);
                }
            }, y, m, d);
            dialog.show();
        }
        if(view == nkt){
            final Calendar c = Calendar.getInstance();
            int y = c.get(Calendar.YEAR);
            int m = c.get(Calendar.MONTH);
            int d = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String date = "";
                    if(m>8){
                        date = y+"/"+(m+1)+"/"+d;
                    }else {
                        date = y+"/0"+(m+1)+"/"+d;
                    }
                    nkt.setText(date);
                }
            }, y, m, d);
            dialog.show();
        }
        if(view == btnSearch){
            if (nbd.getText().toString().equals("") || nkt.getText().toString().equals("")){
                Toast.makeText(getContext(), "Vui lòng nhập đầy đủ khoảng thời gian", Toast.LENGTH_SHORT).show();
            }
            List<CongViec> l = db.searchCvByNgay(nbd.getText().toString(), nkt.getText().toString());
            adapter.setList(l);

        }
    }
}
