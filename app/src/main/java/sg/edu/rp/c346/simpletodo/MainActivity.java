package sg.edu.rp.c346.simpletodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter aa;
    ListView lv;
    EditText et;
    Button add,delete,clear;

    int index;
    ArrayList<String> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        lv = findViewById(R.id.listView);
        et = findViewById(R.id.editText);
        add = findViewById(R.id.buttonAdd);
        delete = findViewById(R.id.buttonDelete);
        clear = findViewById(R.id.buttonClear);

        al = new ArrayList<String>();

        aa = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        add.setEnabled(true);
                        delete.setEnabled(false);
                        clear.setEnabled(true);
                        et.setHint(R.string.hintAdd);

                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (et.getText().toString().isEmpty()){
                                    Toast.makeText(MainActivity.this, "Please type in a new task", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    al.add(et.getText().toString());
                                    aa.notifyDataSetChanged();
                                    et.setText("");
                                }
                            }
                        });

                        break;

                    case 1:
                        add.setEnabled(false);
                        delete.setEnabled(true);
                        clear.setEnabled(true);
                        et.setHint(R.string.hintRemove);

                        delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(et.getText().toString().isEmpty()){
                                    Toast.makeText(MainActivity.this, "The field is empty", Toast.LENGTH_SHORT).show();
                                }else if(Integer.parseInt(et.getText().toString()) > al.size()) {
                                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    index = Integer.parseInt(et.getText().toString());
                                    al.remove(index-1);
                                    aa.notifyDataSetChanged();}
                                et.setText("");
                            }
                        });
                        break;
                }
                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (al.size() == 0)   {
                            Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                        }/*
                        }*/
                        else{
                            index = al.size() -1 ;
                            for(int i = index; i>=0; i--){
                                al.remove(i);
                            }
                            aa.notifyDataSetChanged();
                            et.setText("");
                        }

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                et.setText(String.valueOf(position +1));
            }
        });
    }
}
