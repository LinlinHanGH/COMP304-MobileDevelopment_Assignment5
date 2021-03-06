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

public class ShowroomActivity extends AppCompatActivity {
    private String _seletedBrand;
    private String[]_showrooms={};
    private String[]_addresses={};
    private String[]_phones={};

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
        int arrayIntShowroom=0;
        int arrayIntAddress=0;
        int arrayIntPhone=0;

        switch (_seletedBrand){
            case "Honda":
                arrayIntShowroom=R.array.Honda;
                arrayIntAddress=R.array.HondaAddress;
                arrayIntPhone=R.array.HondaPhones;
                break;
            case "Chevrolet":
                arrayIntShowroom=R.array.Chevrolet;
                arrayIntAddress=R.array.ChevroletAddress;
                arrayIntPhone=R.array.ChevroletPhones;
                break;
            case "Ford":
                arrayIntShowroom=R.array.Ford;
                arrayIntAddress=R.array.FordAddress;
                arrayIntPhone=R.array.FordPhones;
                break;
            case "Nissan":
                arrayIntShowroom=R.array.Nissan;
                arrayIntAddress=R.array.NissanAddress;
                arrayIntPhone=R.array.NissanPhones;
                break;
            default:
                break;
        }

        _showrooms=getResources().getStringArray(arrayIntShowroom);
        _addresses=getResources().getStringArray(arrayIntAddress);
        _phones=getResources().getStringArray(arrayIntPhone);

    }

    private void initBrandsListView(){
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, getData(), R.layout.list_brand, new String[] { "title",  "img" }, new int[] { R.id.title, R.id.img });

        //get brands from string array
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,
               // _showrooms);
        ListView lstvBrand = findViewById(R.id.lstvShowroom);
        lstvBrand.setAdapter(simpleAdapter);
        lstvBrand.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        SharedPreferences myPreference =
                                getSharedPreferences("Asg5SharedPreferences", 0);
                        SharedPreferences.Editor prefEditor = myPreference.edit();
                        Intent intent=new Intent(ShowroomActivity.this, MapsActivity.class);
                        prefEditor.putString("selectedShowroom", _showrooms[position]);
                        prefEditor.putString("selectedAddress", _addresses[position]);
                        prefEditor.putString("salesNum", _phones[position]);
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
        for (int i=0;i<_showrooms.length;i++){
            map = new HashMap<String, Object>();
            map.put("title", _showrooms[i]);
            imageId= getResources().getIdentifier(_seletedBrand.toLowerCase(),
                    "drawable", getPackageName());
            map.put("img", imageId);
            list.add(map);
        }

        return list;
    }
}
