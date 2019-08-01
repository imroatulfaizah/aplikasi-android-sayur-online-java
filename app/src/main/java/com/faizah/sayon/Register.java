package com.faizah.sayon;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.faizah.sayon.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    EditText txtphone, txtnama, txtpassword;
    Button btn_regis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        txtphone = (EditText) findViewById(R.id.txtphone);
        txtnama = (EditText) findViewById(R.id.txtnama);
        txtpassword = (EditText) findViewById(R.id.txtpassword);

        btn_regis = (Button) findViewById(R.id.btn_regis);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(Register.this);
                mDialog.setMessage("Please waiting..");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //check jika sudah ada nomor telepon
                        if (dataSnapshot.child(txtphone.getText().toString()).exists())
                        {
                            mDialog.dismiss();
                            Toast.makeText(Register.this, "Nomor telepon sudah terdaftar", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            mDialog.dismiss();
                            User user = new User(txtnama.getText().toString(), txtpassword.getText().toString());
                            table_user.child(txtphone.getText().toString()).setValue(user);
                            Toast.makeText(Register.this, "Register sukses", Toast.LENGTH_SHORT).show();
                            finish();
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
