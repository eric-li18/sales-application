package com.b07;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.b07.database.DatabaseInsertHelper;
import android.util.Log;
import com.b07.database.DatabaseSelectHelper;
import com.b07.users.User;


public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.sign_up);
    String TAG = "mainActivity";
    int userId = DatabaseInsertHelper.insertNewUser("Eric Li", 19, "idk", "abc123", this);
    int roleId = DatabaseInsertHelper.insertRole("ADMIN", this);
    DatabaseInsertHelper.insertUserRole(userId, roleId, this);
    User user = DatabaseSelectHelper.getUserDetails(userId, this);
    if(user == null){
      Log.e(TAG, "user is null!");
    }
    else {
      String name = user.getName();
      Log.e(TAG, name);
      Log.e(TAG, "I am here");
    }
  }
}
