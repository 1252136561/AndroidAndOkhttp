package com.hbw.demoapplication;

import android.content.Intent;

import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import com.hbw.data.DatabaseUtil;
import com.hbw.entity.Result;
import com.hbw.entity.UserTable;
import com.hbw.http.HttpAddress;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
      private TextView tv=null;
      EditText userName = null;
      EditText pwd = null ;
      Button login=null;

      private Button registry=null;

      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.index_ui);

            setTitle("登录");
            login = (Button)findViewById(R.id.bt_login);
            registry = (Button)findViewById(R.id.bt_register);
            userName=(EditText) findViewById(R.id.text_user);
            pwd=findViewById(R.id.text_userPassword);
            login.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {

                        String user = userName.getText().toString();
                        String password=pwd.getText().toString();
                        UserTable user1=new UserTable(user,password);
                        String[] list = new String[]{user,password};

                        Result result= DatabaseUtil.insert(user1,
                                HttpAddress.get(HttpAddress.user(),"login"));
                        if (result.getCode() == 200){
                              Handler handler = new Handler(Looper.getMainLooper());
                              handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                          //放在UI线程弹Toast
                                          Toast.makeText(MainActivity.this, "登录成功！", Toast.LENGTH_LONG).show();
                                          Intent intent2 = new Intent(MainActivity.this, MainActivity.class);
                                          Bundle bundle = new Bundle();
                                          bundle.putCharSequence("userName", user);
                                          intent2.putExtras(bundle);
                                          //启动事件
                                          startActivity(intent2);
                                    }
                              });
                        }else{
                              Handler handler = new Handler(Looper.getMainLooper());
                              handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                          //放在UI线程弹Toast
                                          Toast.makeText(MainActivity.this, result.getMsg(), Toast.LENGTH_LONG).show();
                                          Intent intent2 = new Intent(MainActivity.this, MainActivity.class);
                                          //启动事件
                                          startActivity(intent2);
                                    }
                              });
                        }
                        /**删除单个数据
                         Result result=DatabaseUtil.deleteById(HttpAddress.
                         get(HttpAddress.user(),"delete",id));
                         System.out.println(result.toString());
                         // */

                        /**更新单个数据
                         Result result=DatabaseUtil.updateById(user1,
                         HttpAddress.get(HttpAddress.user(),"update"));
                         System.out.println(result.toString());
                         //*/

                        /**查单行数据
                         Result result=DatabaseUtil.selectLineById(
                         HttpAddress.get(HttpAddress.user(),"line",id));
                         User user=DatabaseUtil.getEntity(result,User.class);
                         System.out.println(user.toString());
                         // */

                        /**查列表数据
                         Result result =DatabaseUtil.selectList(
                         HttpAddress.get(HttpAddress.user(),"list"));
                         List<User> users=DatabaseUtil.getList(result);
                         System.out.println(users.toString());
                         // */
                  }
            });



      }

}

