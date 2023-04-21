package com.example.ktratrenlop.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ktratrenlop.R;
import com.example.ktratrenlop.database.SQLiteHelper;
import com.example.ktratrenlop.model.CongViec;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText eTen, eND, eNgay;
    CheckBox cbTT, cbCT;
    Button btnUpdate, btnDelete, btnCancel;
    CongViec cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        initView();

        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        eNgay.setOnClickListener(this);

        Intent intent = getIntent();
        cv = (CongViec) intent.getSerializableExtra("cv");
        eTen.setText(cv.getTen());
        eND.setText(cv.getNoidung());
        eNgay.setText(cv.getNgay());
        cbTT.setChecked(cv.isTinhtrang()?true:false);
        cbCT.setChecked(cv.isCongtac()?true:false);
    }
    private void initView() {
        eTen = findViewById(R.id.eTen);
        eND = findViewById(R.id.eND);
        eNgay = findViewById(R.id.eNgay);
        cbTT = findViewById(R.id.cbTT);
        cbCT = findViewById(R.id.cbCT);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

    }

    @Override
    public void onClick(View view) {
        if(view == eNgay){
            final Calendar c = Calendar.getInstance();
            int y = c.get(Calendar.YEAR);
            int m = c.get(Calendar.MONTH);
            int d = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(UpdateDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String date = "";
                    if(m>8){
                        date = y+"/"+(m+1)+"/"+d;
                    }else {
                        date = y+"/0"+(m+1)+"/"+d;
                    }
                    eNgay.setText(date);
                }
            }, y, m, d);
            dialog.show();
        }
        if (view == btnCancel){
            finish();
        }
        if(view == btnUpdate){

            String ten = eTen.getText().toString();
            String nd = eND.getText().toString();
            String ngay = eNgay.getText().toString();
            Boolean tt = cbTT.isChecked();
            Boolean ct = cbCT.isChecked();
            if (ten.equals("")){
                Toast.makeText(this, "Vui lòng nhập tên công việc", Toast.LENGTH_SHORT).show();
            }
            else if (nd.equals("")){
                Toast.makeText(this, "Vui lòng nhập nội dung công việc", Toast.LENGTH_SHORT).show();
            }
            else if (ngay.equals("")){

                Toast.makeText(this, "Vui lòng nhập ngày thực hiện", Toast.LENGTH_SHORT).show();
            }else {
                SQLiteHelper db = new SQLiteHelper(this);
                db.update(new CongViec(cv.getId(), ten, nd, ngay, tt, ct));
                Toast.makeText(this, "Sửa công việc thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        if(view == btnDelete){
            int id = cv.getId();
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thông báo");
            builder.setMessage("Bạn có muốn xóa công việc "+ cv.getTen()+ " không?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SQLiteHelper db = new SQLiteHelper(view.getContext());
                    db.delete(id);
                    Toast.makeText(UpdateDeleteActivity.this, "Xóa công việc thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}