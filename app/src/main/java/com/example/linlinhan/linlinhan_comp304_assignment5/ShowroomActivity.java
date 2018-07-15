package com.example.linlinhan.linlinhan_comp304_assignment5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShowroomActivity extends AppCompatActivity {
    private String _seletedBrand;
    private String[]_showrooms={};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showroom);

        initValues();

        initBrandsListView();
    }// end of onCreate

    private void initValues(){
        SharedPreferences myPref = getSharedPreferences("Asg5SharedPreferences", MODE_PRIVATE);
        _seletedBrand = myPref.getString("selectedBrand","");

        switch (_seletedBrand){
            case "Honda":
                _showrooms=getResources().getStringArray(R.array.Honda);
                break;
                default:
                    break;
        }


    }

    private void initBrandsListView(){


        //get brands from string array
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,
                _showrooms);
        ListView lstvBrand = findViewById(R.id.lstvShowroom);
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
                                intent = new Intent(ShowroomActivity.this, MapsActivity.class);
                                prefEditor.putString("selectedShowroom", _showrooms[0]);
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
