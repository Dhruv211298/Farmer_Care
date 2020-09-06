package com.shahdhruv.farmercare;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class activity_facilities extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    LinearLayout clickpic,environment,result,feedback,solution;
    TextView name,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities);
        clickpic=findViewById(R.id.clickpic);
        environment=findViewById(R.id.enviroment);
        result=findViewById(R.id.result);
        feedback=findViewById(R.id.feedback);
        solution=findViewById(R.id.solution);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View v= navigationView.getHeaderView(0);
        name=v.findViewById(R.id.user_name);
        email=v.findViewById(R.id.user_email);
        name.setText(getIntent().getStringExtra("name"));
        email.setText(getIntent().getStringExtra("email"));
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_profile, R.id.nav_acc_set, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();
        clickpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(activity_facilities.this,ClassifierActivity.class);
                startActivity(i);
            }
        });
        environment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(activity_facilities.this,environmentData.class);
                startActivity(i);
            }
        });
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(activity_facilities.this,result.class);
                startActivity(i);
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(activity_facilities.this,feedback.class);
                i.putExtra("email",getIntent().getStringExtra("email"));
                startActivity(i);
            }
        });
        solution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(activity_facilities.this,Solution.class);
                i.putExtra("email",getIntent().getStringExtra("email"));
                startActivity(i);
            }
        });
        navigationView.setCheckedItem(R.id.nav_home);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_facilities, menu);
        return true;
    }*/

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        int id = menuItem.getItemId();
        //profile
        if(id == R.id.nav_profile)
        {
            User user = new User(activity_facilities.this);
            Intent i=new Intent(activity_facilities.this,profile_page.class);
            i.putExtra("email",user.getEmail());
            startActivity(i);
        }
        //Account Setting
        if(id == R.id.nav_acc_set)
        {
            Intent i=new Intent(activity_facilities.this,account_setting.class);
            String Email=email.getText().toString();
            i.putExtra("email",Email);
            startActivity(i);
        }
        //logout
        if(id == R.id.nav_logout)
        {
            new User(activity_facilities.this).removeUser();
            Intent i=new Intent(activity_facilities.this,LoginActivity.class);
            startActivity(i);
            finish();
            Toast.makeText(this, "You Are Successfully Logout.", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}
