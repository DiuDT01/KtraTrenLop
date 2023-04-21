package com.example.ktratrenlop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktratrenlop.R;
import com.example.ktratrenlop.activities.UpdateDeleteActivity;
import com.example.ktratrenlop.adapter.RecyclerViewAdapter;
import com.example.ktratrenlop.database.SQLiteHelper;
import com.example.ktratrenlop.model.CongViec;

import java.util.List;

public class FragmentDS extends Fragment implements RecyclerViewAdapter.CVListener {
    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private SQLiteHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ds, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rviewDS);
        adapter = new RecyclerViewAdapter();
        db = new SQLiteHelper(getContext());
//        db.addCv(new CongViec("Lau nha", "Lau nha", "2023/04/13", false, false));
//        db.addCv(new CongViec("Bai tap", "Nhieu qua", "2023/04/26", false, true));
        List<CongViec> list = db.getAll();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setCvListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        CongViec cv = adapter.getCV(position);
        Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("cv", cv);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<CongViec> list = db.getAll();
        adapter.setList(list);
    }
}
