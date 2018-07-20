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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //initialise listview
        initBrandsListView();

    }// end of cnCreate

    private void initBrandsListView(){
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, getData(), R.layout.list_brand, new String[] { "title",  "img" }, new int[] { R.id.title, R.id.img });



        final String[]brands=getResources().getStringArray(R.array.brands);
        //get brands from string array
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,
                brands);
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
                        prefEditor.putString("selectedBrand", brands[position]);
                        prefEditor.commit();
                        startActivity(intent);
                    }
                }
        );
    }

    private List<Map<String, Object>> getData() {
        //map.put(参数名字,参数值)
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "Honda");
        map.put("img", R.drawable.honda);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "Chevrolet");
        map.put("img", R.drawable.chevrolet);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "Ford");
        map.put("img", R.drawable.ford);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "Nissan");
        map.put("img", R.drawable.nissan);
        list.add(map);
        return list;
    }

}



