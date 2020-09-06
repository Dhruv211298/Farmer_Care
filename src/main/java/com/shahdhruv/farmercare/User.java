package com.shahdhruv.farmercare;

import android.content.Context;
import android.content.SharedPreferences;

public class User {
    Context context;
    SharedPreferences sharedPreferences;
    String name,email,mobile_no,password,location;

    public User()
    {

    }
    public User(Context context)
    {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("User_Info",Context.MODE_PRIVATE);
    }
    public void removeUser()
    {
        sharedPreferences.edit().clear().commit();
    }

   public String getName()
   {
        name=sharedPreferences.getString("User_name","");
        return name;
   }

    public void setName(String name)
    {
        this.name = name;
        sharedPreferences.edit().putString("User_name",name).commit();
    }

    public String getEmail()
    {
        email=sharedPreferences.getString("User_email","");
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
        sharedPreferences.edit().putString("User_email",email).commit();
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
