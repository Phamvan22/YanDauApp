package com.example.doan3;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.doan3.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginByPhoneActivity extends AppCompatActivity {
    EditText edtPhoneNumber, edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_by_phone);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtPhoneNumber = (EditText) findViewById(R.id.edtPhoneNumber);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(LoginByPhoneActivity.this);
                mDialog.setMessage("Please waiting ... ");
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // Check neu user ko ton tai trong firebase
                        if(snapshot.child(edtPhoneNumber.getText().toString()).exists()){
                            //User
                            mDialog.dismiss();
                            User user = snapshot.child(edtPhoneNumber.getText().toString()).getValue(User.class);
                            if(user.getPassword().equals(edtPassword.getText().toString())){
                                Toast.makeText(LoginByPhoneActivity.this, "Sign in successfully !", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(LoginByPhoneActivity.this, "Sign in failed !", Toast.LENGTH_SHORT).show();

                            }
                        }
                        else {
                            Toast.makeText(LoginByPhoneActivity.this, "User not exist in Database !", Toast.LENGTH_SHORT).show();

                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}