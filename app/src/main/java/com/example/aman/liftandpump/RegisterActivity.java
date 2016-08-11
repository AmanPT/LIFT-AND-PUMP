package com.example.aman.liftandpump;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aman.liftandpump.model.CustomerInfo;

import io.realm.Realm;

public class RegisterActivity extends AppCompatActivity {
    TextView etName, etPwd, etPhn, etEmail;
    Button btRgt, btCnl;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        realm = Realm.getDefaultInstance();
        btRgt=(Button)findViewById(R.id.btrgt);
        etName = (EditText) findViewById(R.id.tvnm);
        etPwd = (EditText) findViewById(R.id.tvpwd);
        etPhn = (EditText) findViewById(R.id.tvphn);
        etEmail = (EditText) findViewById(R.id.tvmail);
        //etAdres = (EditText) findViewById(R.id.t);
        btRgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"button clicked",Toast.LENGTH_SHORT).show();
                if(etName==null){
                    Log.w("RegisterActivity","eName is nulll");
                }
                else {
                    String name = etName.getText().toString().trim();
                    String phoneNo = etPhn.getText().toString().trim();
                    String email = etEmail.getText().toString().trim();
                    String pwd = etPwd.getText().toString().trim();
                    //String address = etAdres.getText().toString().trim();
                    save_into_database(name, phoneNo, email, pwd);
                    Toast.makeText(getApplicationContext(), "Success",Toast.LENGTH_SHORT).show();

                }
                // view_refresh();

            }
        });
    }
    public void save_into_database(final String name,final String phoneNo,final String email,final String password ){
        CustomerInfo info=new CustomerInfo();
        info.setcName(name);
        info.setcEmail(email);
        info.setcPhoneNo(phoneNo);
        info.setcPassword(password);
        //info.setcAddress(address);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(info);
        realm.commitTransaction();
    }
}
