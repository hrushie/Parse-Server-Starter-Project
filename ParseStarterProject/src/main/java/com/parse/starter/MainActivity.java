/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;
import java.util.zip.InflaterInputStream;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {
EditText user_edit, pass_edit;
  TextView changemode_tv;
  Boolean signupmode = true;
  Button signup_button;
  RelativeLayout mainlayout;
  public void SignUp(View view){
    if (user_edit.getText().toString().matches("")  || pass_edit.getText().toString().matches("")){
      Toast.makeText(this, "Username and Password Required", Toast.LENGTH_SHORT).show();
    } else {
      if (signupmode) {
        ParseUser user = new ParseUser();
        user.setUsername(user_edit.getText().toString());
        user.setPassword(pass_edit.getText().toString());
        user.signUpInBackground(new SignUpCallback() {
          @Override
          public void done(ParseException e) {
            if (e == null) {
              Log.i("SignUp", "Successful");
            } else {
              Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
          }
        });
      } else {

        ParseUser.logInInBackground(user_edit.getText().toString(), pass_edit.getText().toString(), new LogInCallback() {
          @Override
          public void done(ParseUser user, ParseException e) {
            if (user !=null){
              Log.i("Sign Up", "Login Successful");
            } else {
              Log.i("Sign Up", "Error");
            }
          }
        });
      }
    }

  }
  @Override
  public void onClick(View view) {
    if (view.getId() == R.id.changemode_text){

      if (signupmode == true){
        signup_button.setText("Login");
        changemode_tv.setText("Sign Up");
        signupmode = false;
      } else {
        signup_button.setText("Sign Up");
        changemode_tv.setText("Login");

        signupmode = true;
      }
      Log.i("App info", "Change mode");
    } else if (view.getId() == R.id.main_layout){
      InputMethodManager inputmanager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
      inputmanager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
    }
  }

  @Override
  public boolean onKey(View view, int i, KeyEvent keyEvent) {
    if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN){
      SignUp(view);
    }
    return false;
  }
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    user_edit = (EditText)findViewById(R.id.et_User);
    pass_edit = (EditText)findViewById(R.id.et_pass);
    signup_button = (Button)findViewById(R.id.log_btn);
    changemode_tv = (TextView)findViewById(R.id.changemode_text);
    changemode_tv.setOnClickListener(this);
    pass_edit.setOnKeyListener(this);
    mainlayout = (RelativeLayout)findViewById(R.id.main_layout);
    mainlayout.setOnClickListener(this);
//    ParseUser.logOut();

//    if (ParseUser.getCurrentUser() != null){
//      Log.i("Current User Loggedin", ParseUser.getCurrentUser().getUsername());
//    } else {
//      Log.i("Current not", "Loggedin");
//    }
//    ParseUser.logInInBackground("hrushie", "Jawali", new LogInCallback() {
//      @Override
//      public void done(ParseUser user, ParseException e) {
//        if (user!=null){
//          Log.i("Signup ", "Successful");
//        } else {
//          Log.i("Signup ", "UnSuccessful");
//
//        }
//      }
//    });

//    ParseUser user = new ParseUser();
//    user.setUsername("hrushie");
//    user.setPassword("Jawali");
//
//    user.signUpInBackground(new SignUpCallback() {
//      @Override
//      public void done(ParseException e) {
//        if (e== null){
//          Log.i("Signup", "Successufull");
//        } else {
//          Log.i("Signup", "Unsucceswsful");
//        }
//      }
//    });

//    ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
//    query.whereGreaterThan("score", 200);
//    query.findInBackground(new FindCallback<ParseObject>() {
//      @Override
//      public void done(List<ParseObject> objects, ParseException e) {
//        if (e == null && objects != null){
//          for (ParseObject object:objects){
//            object.put("score", object.getInt("score")+50);
//            object.saveInBackground();
//          }
//        }
//      }
//    });

//    ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
//    query.whereGreaterThan("score", 250);
//    query.findInBackground(new FindCallback<ParseObject>() {
//      @Override
//      public void done(List<ParseObject> objects, ParseException e) {
//        if(e == null){
//
//          Log.i("findInBackground", "Retrieved : " + objects.size());
//          if (objects.size()>0){
//
//            for (ParseObject object: objects){
//              object.put("score", object.getInt("score")+ 50 );
//               Log.i("findInResult",  Integer.toString(object.getInt("score")));
//            }
//          }
//        }
//      }
//    });

//    ParseObject tweet = new ParseObject("tweet");
//    tweet.put("username", "hrushie");
//    tweet.put("tweet", "good win for warriors today");
//    tweet.saveInBackground(new SaveCallback() {
//      @Override
//      public void done(ParseException e) {
//        if (e == null){
//          Log.i("SaveInBackground", "Successful");
//        } else {
//          Log.i("SaveInBackground", "Failed :" + e.toString());
//        }
//      }
//    });
//    final ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("tweet");
//    query.getInBackground("5ukRFB8sUK", new GetCallback<ParseObject>() {
//      @Override
//      public void done(ParseObject object, ParseException e) {
//        if (object != null && e == null){
//          object.put("tweet", "Spurs won 2 days ago");
//          object.saveInBackground();
//          Log.i("Username :", object.getString("username"));
//          Log.i("Tweet :", object.getString("tweet"));
//        }
//
//      }
//    });
//    ParseObject score = new ParseObject("Score");
//    score.put("username", "rushi");
//    score.put("score",86);
//    score.saveInBackground(new SaveCallback() {
//      @Override
//      public void done(ParseException e) {
//        if (e == null){
//          Log.i("SaveInBackground", "Successful");
//        } else {
//          Log.i("SaveInBackground", "Failed. Error = " + e.toString() );
//        }
//      }
//    });
//    ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
//    query.getInBackground("O7sYC03Uzp", new GetCallback<ParseObject>() {
//      @Override
//      public void done(ParseObject object, ParseException e) {
//        if (e == null && object != null){
//
//          object.put("score", 200);
//          object.saveInBackground();
//          Log.i("ObjectValue :",object.getString("username"));
//          Log.i("ObjectValue :", Integer.toString(object.getInt("score")));
//        } else {
//          Log.i("SaveInBackground", "Failed. Error = " + e.toString() );
//
//        }
//      }
//    });
    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }


}