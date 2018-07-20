package com.example.linlinhan.linlinhan_comp304_assignment5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuActivity extends AppCompatActivity {
    private String[]_brands={};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //initialise listview
        initBrandsListView();

    }// end of cnCreate

    private void initBrandsListView(){
        _brands=getResources().getStringArray(R.array.brands);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, getData(), R.layout.list_brand, new String[] { "title",  "img" }, new int[] { R.id.title, R.id.img });

        ListView lstvBrand = findViewById(R.id.lstvBrand);
        lstvBrand.setAdapter(simpleAdapter);
        lstvBrand.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        SharedPreferences myPreference =
                                getSharedPreferences("Asg5SharedPreferences", 0);
                        SharedPreferences.Editor prefEditor = myPreference.edit();
                        Intent intent=new Intent(MenuActivity.this, ShowroomActivity.class);
                        prefEditor.putString("selectedBrand", _brands[position]);
                        prefEditor.commit();
                        startActivity(intent);
                    }
                }
        );
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        int imageId;
        for (int i=0;i<_brands.length;i++){
            map = new HashMap<String, Object>();
            map.put("title", _brands[i]);
            imageId= getResources().getIdentifier(_brands[i].toLowerCase(),
                    "drawable", getPackageName());
            map.put("img", imageId);
            list.add(map);
        }

        return list;
    }

}



