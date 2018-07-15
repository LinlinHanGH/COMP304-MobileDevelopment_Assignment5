package com.example.linlinhan.linlinhan_comp304_assignment5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //initialise listview
        initBrandsListView();

    }// end of cnCreate

    private void initBrandsListView(){
        final String[]brands=getResources().getStringArray(R.array.brands);
        //get brands from string array
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,
                brands);
        ListView lstvBrand = findViewById(R.id.lstvBrand);
        lstvBrand.setAdapter(adapter);
        lstvBrand.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        SharedPreferences myPreference =
                                getSharedPreferences("Asg5SharedPreferences", 0);
                        SharedPreferences.Editor prefEditor = myPreference.edit();
                        Intent intent;
                        switch (position)
                        {
                            case 0:
                                intent = new Intent(MenuActivity.this, ShowroomActivity.class);
                                prefEditor.putString("selectedBrand", brands[0]);
                                prefEditor.commit();
                                startActivity(intent);
                                break;
                            case 1:

                                break;
                            case 2:

                                break;

                            default:
                                break;
                        }
                    }
                }
        );
    }
}


