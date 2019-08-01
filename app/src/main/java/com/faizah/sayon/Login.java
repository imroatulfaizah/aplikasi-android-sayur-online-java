package com.faizah.sayon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.faizah.sayon.Common.Common;
import com.faizah.sayon.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Login extends AppCompatActivity {

    EditText txtphone, txtpassword;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtphone = (EditText) findViewById(R.id.txtphone);
        txtpassword = (EditText) findViewById(R.id.txtpassword);

        btn_login = (Button) findViewById(R.id.btn_login);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog(Login.this);
                mDialog.setMessage("Please waiting..");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        //Check if user not exist

                        if (dataSnapshot.child(txtphone.getText().toString()).exists()) {
                            //Get user information
                            mDialog.dismiss();
                            User user = dataSnapshot.child(txtphone.getText().toString()).getValue(User.class);
                            user.setPhone(txtphone.getText().toString()); //set phone
                            if (user.getPassword().equals(txtpassword.getText().toString())) {
                                {
                                    Intent homeIntent = new Intent(Login.this, Home.class);
                                    Common.currentUser = user;
                                    startActivity(homeIntent);
                                    finish();
                                }
                            } else {
                                Toast.makeText(Login.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            mDialog.dismiss();
                            Toast.makeText(Login.this, "User tidak ada ", Toast.LENGTH_SHORT).show();
                        }

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}