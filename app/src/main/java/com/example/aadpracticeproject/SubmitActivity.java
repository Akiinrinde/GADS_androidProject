package com.example.aadpracticeproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.google.android.material.textfield.TextInputEditText;

public class SubmitActivity extends AppCompatActivity {

    EditText fName, lName, email, gitLink;
    Button submitForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        fName = findViewById(R.id.first_name);
        lName = findViewById(R.id.last_name);
        email = findViewById(R.id.editTextTextEmailAddress);
        gitLink = findViewById(R.id.gitLink);
        submitForm = findViewById(R.id.button);

        submitForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public SubmitRequest createRequest(){
        SubmitRequest submitRequest = new SubmitRequest();
        submitRequest.setEmailAddress(email.getText().toString());
        submitRequest.setName(fName.getText().toString());
        submitRequest.setLastName(lName.getText().toString());
        submitRequest.setProjectUrl(gitLink.getText().toString());

        return submitRequest;
    }

    public void setSubmitForm (SubmitRequest submitRequest){
        Call<ResponseBody> call = SubmitApiClient.getSubmitApiInterface().savePost( email.getText().toString(),fName.getText().toString(),lName.getText().toString(),gitLink.getText().toString());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                AlertDialog.Builder successBuilder = new AlertDialog.Builder(SubmitActivity.this);
                successBuilder.setIcon(R.drawable.green_tick_2).setMessage("Submission Successful");
                AlertDialog successAlert = successBuilder.create();
                successAlert.show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                AlertDialog.Builder failBuilder = new AlertDialog.Builder(SubmitActivity.this);
                failBuilder.setIcon(R.drawable.alert).setMessage("Submission Failed");
                AlertDialog failAlert = failBuilder.create();
                failAlert.show();
            }
        });
    }

}