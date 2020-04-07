package com.example.aplikacja;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ListView listadzien;
    ListView listamiesiac;
    ListView listarok;
    int dzien1,miesiac1,rok1;
    TextView pokaz,pokazdata;

    LinearLayout pokaz1;
    Button licz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        final int dzien = liczdzientygodnia(4,7,2020);
       listadzien = (ListView)findViewById(R.id.listadzien);
       listamiesiac = (ListView)findViewById(R.id.listamiesiac);
       pokaz1 = (LinearLayout)findViewById(R.id.layoutpokaz);
       pokaz = (TextView) findViewById(R.id.pokazdzien);
       pokazdata = (TextView) findViewById(R.id.pokazdata);
       licz = (Button)findViewById(R.id.przycisk);
       listarok = (ListView)findViewById(R.id.listarok);
        ArrayList<String> listitems = new ArrayList<String>();
        ArrayList<String> listitems2 = new ArrayList<String>();
        ArrayList<String> listitems3 = new ArrayList<String>();
        ArrayAdapter<String> adapter;
        ArrayAdapter<String> adapter1;
        ArrayAdapter<String> adapter2;
        for(int i =1;i<=31;i++)
        {
            listitems.add(i+"");
        }
        for(int i =1;i<=12;i++)
        {
            listitems2.add(i+"");
        }
        for(int i =0;i<=4000;i++)
        {
            listitems3.add(i+"");
        }

        adapter=new ArrayAdapter<String>(this,
                R.layout.lista,
                listitems);
        adapter1=new ArrayAdapter<String>(this,
                R.layout.lista,
                listitems2);
        adapter2=new ArrayAdapter<String>(this,
                R.layout.lista,
                listitems3);

        listadzien.setAdapter(adapter);
        listamiesiac.setAdapter(adapter1);
        listarok.setAdapter(adapter2);
        Calendar calendar = Calendar.getInstance();
        int dzien11 = calendar.get(Calendar.DAY_OF_MONTH);
        listadzien.setSelection(dzien11-1);
        listadzien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              //  Toast.makeText(getApplicationContext(),adapterView.getItemAtPosition(i)+"",Toast.LENGTH_LONG).show();

                int z = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
                dzien1 = z ;
                licz.setVisibility(View.VISIBLE);
                pokaz1.setVisibility(View.GONE);
              //  listadzien.getChildAt(i).setBackgroundColor(Color.YELLOW);
            }
        });
       // Calendar calendar = Calendar.getInstance();
        int miesiac = calendar.get(Calendar.MONTH);
        listamiesiac.setSelection(miesiac);
        listamiesiac.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // Toast.makeText(getApplicationContext(),adapterView.getItemAtPosition(i)+"",Toast.LENGTH_LONG).show();

                int z = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
                miesiac1 = z ;
                licz.setVisibility(View.VISIBLE);
                pokaz1.setVisibility(View.GONE);
            //    listamiesiac.getChildAt(i).setBackgroundColor(Color.YELLOW);
            }
        });
       // Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        listarok.setSelection(year);
        listarok.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              //  Toast.makeText(getApplicationContext(),adapterView.getItemAtPosition(i)+"",Toast.LENGTH_LONG).show();

                int z = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
                rok1 = z ;
                licz.setVisibility(View.VISIBLE);
                pokaz1.setVisibility(View.GONE);
             //   listarok.getChildAt(i).setBackgroundColor(Color.YELLOW);
            }
        });

        licz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int wpisz_dzien =    liczdzientygodnia(miesiac1,dzien1,rok1);
            licz.setVisibility(View.GONE);
            String nazwa = null;
                pokaz1.setVisibility(View.VISIBLE);

            switch (wpisz_dzien)
            {
                case 0:
                    nazwa="Wtorek";
                    break;
                case 1:
                    nazwa="Sroda";
                    break;
                case 2:
                    nazwa="Czwartek";
                    break;
                case 3:
                    nazwa="Piątek";
                    break;
                case 4:
                    nazwa="Sobota";
                    break;
                case 5:
                    nazwa="Niedziela";
                    break;
                case 6:
                    nazwa="Poniedziałek";
                    break;
                            
            }
            String dzien_2=null;
            String miesiac_2=null;
            if(dzien1<10)
            {
                dzien_2="0"+dzien1;
            }
            else
            {
                dzien_2=""+dzien1;
            }

                if(miesiac1<10)
                {
                    miesiac_2="0"+miesiac1;
                }
                else
                {
                    miesiac_2=""+miesiac1;
                }
            pokazdata.setText("Dzień daty "+dzien_2 +"."+miesiac_2+"."+rok1+"."+" to:");
                pokaz.setText(nazwa);
            }
        });



    }

    int liczdzientygodnia(int m, int d, int y){
        int dzientygodnia;
        int p = 23*m/9;
        int z = 0;
        if(m<3){
            z=y-1;
        }
        else if(m>=3) {
            z = y;
        }
        int s=z/4;
        int t = z/100;
        int f = z/400;
        int c=0;
        if(m<3){
            c=0;
        }
        else if(m>=3) {
            c=2;
        }

        int koncowe;
        koncowe = (p + d + 4 + y + s + t + f - c)%7;
        return koncowe;
    }

}
