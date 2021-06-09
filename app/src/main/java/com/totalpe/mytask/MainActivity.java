package com.totalpe.mytask;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText et_email, et_mobile;
    Button btn_submit;
    SharedPreference sharedPreference;
    List<Details> detailsList;
    DetailsListAdapter detailsListAdapter;
    RecyclerView rv_list;
    TextInputLayout txtInptLytEmail, txtInptLytMobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewByID();
    }

    private void viewByID() {
        sharedPreference = new SharedPreference();
        et_email = findViewById(R.id.et_email);
        et_mobile = findViewById(R.id.et_mobile);
        btn_submit = findViewById(R.id.btn_submit);
        txtInptLytEmail = findViewById(R.id.txtInptLytEmail);
        txtInptLytMobile = findViewById(R.id.txtInptLytMobile);

        rv_list = findViewById(R.id.rv_list);


        rv_list.setHasFixedSize(true);
        rv_list.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });
    }

    private void addData() {

        String email = txtInptLytEmail.getEditText().getText().toString().trim();
        String mobile = txtInptLytMobile.getEditText().getText().toString().trim();
        //Toast.makeText(this, ""+cheackEmail(), Toast.LENGTH_SHORT).show();
        if (!Utils.isTextInputLayoutEmpty(txtInptLytEmail, "Email can't be empty")) {
        } else if (!cheackEmail()) {
        } else if (!Utils.isTextInputLayoutEmpty(txtInptLytMobile, "Mobile can't be empty")) {
        } else if (!cheackMobile()) {
        } else {
            Details details = new Details(1, email, mobile);
            sharedPreference.addFavorite(MainActivity.this, details);
            et_email.setText("");
            et_mobile.setText("");
            Toast.makeText(this, "Details successfully added.. ", Toast.LENGTH_SHORT).show();
            setDetails();
        }
    }

    private void setDetails() {
        detailsList = sharedPreference.getFavorites(MainActivity.this);
        if (detailsList != null) {
            detailsListAdapter = new DetailsListAdapter(getApplication(), detailsList);
            rv_list.setAdapter(detailsListAdapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setDetails();
    }

    public boolean cheackEmail() {
        String email = txtInptLytEmail.getEditText().getText().toString().trim();
        String mobile = txtInptLytMobile.getEditText().getText().toString().trim();
        detailsList = sharedPreference.getFavorites(MainActivity.this);


        if (detailsList != null) {
            for (int i=0;i<detailsList.size();i++){
                if (detailsList.get(i).getEmail().equalsIgnoreCase(email)){
                    txtInptLytEmail.setError("This Email is already exist. Use another");
                    return false;
                }
            }
            txtInptLytEmail.setError(null);
            return true;
        } else {
            txtInptLytEmail.setError(null);
            return true;
        }
    }

    public boolean cheackMobile() {

        String mobile = txtInptLytMobile.getEditText().getText().toString().trim();
        detailsList = sharedPreference.getFavorites(MainActivity.this);


        if (detailsList != null) {
            for (int i=0;i<detailsList.size();i++){
                if (detailsList.get(i).getMobile().equalsIgnoreCase(mobile)){
                    txtInptLytMobile.setError("This Mobile is already exist. Use another");
                    return false;
                }
            }
            txtInptLytMobile.setError(null);
            return true;
        } else {
            txtInptLytMobile.setError(null);
            return true;
        }
    }


}