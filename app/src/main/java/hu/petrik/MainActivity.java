package hu.petrik;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button btn_minusz, btn_plusz, btn_tipp;
    private TextView betu, megoldas;
    private ImageView img;
    private Random  random;
    private String[] szavak;
    private int betuindex;
    private String kitalaltSzo,alahuzasok,megfejtes;
    private int hossz,probalkozas;
    private String[] betuk;
    private char[] megfejtesArray;
    private List<String> hasznaltBetu;
    private AlertDialog.Builder alertBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        ujJatek();
        btn_minusz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(betuindex==0){
                    betuindex=betuk.length-1;
                }else{
                    betuindex--;
                }

                if (hasznaltBetu.contains(betuk[betuindex])){
                    betu.setTextColor(Color.BLACK);
                }else{
                    betu.setTextColor(Color.RED);
                }
                betu.setText(betuk[betuindex]);
            }
        }
        );
        btn_plusz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(betuindex==betuk.length-1){
                    betuindex=0;
                }
                else{
                    betuindex++;
                }
                if (hasznaltBetu.contains(betuk[betuindex])){
                    betu.setTextColor(Color.BLACK);
                }else{
                    betu.setTextColor(Color.RED);
                }
                betu.setText(betuk[betuindex]);
            }
        });

        btn_tipp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!hasznaltBetu.contains(betuk[betuindex])){
                    hasznaltBetu.add(betuk[betuindex]);
                    betu.setTextColor(Color.BLACK);

                    boolean vanBenne = false;
                    megfejtes = "";

                    for (int i = 0; i < hossz; i++) {
                        if(betuk[betuindex].charAt(0) == kitalaltSzo.charAt(i)){
                            vanBenne = true;
                            megfejtesArray[i] = betuk[betuindex].charAt(0);
                        }
                    }


                    if(!vanBenne){
                        kepMegvaltoztat();
                    }
                    else{
                        for (int i = 0; i < megfejtesArray.length; i++) {
                            megfejtes+=megfejtesArray[i];
                        }
                        megoldas.setText(megfejtes);
                        kitalaltaE();
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Ezt a betűt már használtad",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void kitalaltaE(){
        boolean kitalalta=true;
        for (char karakter:
             megfejtesArray) {if(karakter=='_'){
                 kitalalta=false;
        }

        }
        if(kitalalta){
            alertBuilder.setTitle("Nyertél");
            alertBuilder.create();
            alertBuilder.show();
        }
    }
    private void kepMegvaltoztat(){
        probalkozas++;
        switch(probalkozas){
            case 1:
                img.setBackgroundResource(R.drawable.akasztofa01);
            break;
            case 2:
                img.setBackgroundResource(R.drawable.akasztofa02);
                break;
            case 3:
                img.setBackgroundResource(R.drawable.akasztofa03);
                break;
            case 4:
                img.setBackgroundResource(R.drawable.akasztofa04);
                break;
            case 5:
                img.setBackgroundResource(R.drawable.akasztofa05);
                break;
            case 6:
                img.setBackgroundResource(R.drawable.akasztofa06);
                break;
            case 7:
                img.setBackgroundResource(R.drawable.akasztofa07);
                break;
            case 8:
                img.setBackgroundResource(R.drawable.akasztofa08);
                break;
            case 9:
                img.setBackgroundResource(R.drawable.akasztofa09);
                break;
            case 10:
                img.setBackgroundResource(R.drawable.akasztofa10);
                break;
            case 11:
                img.setBackgroundResource(R.drawable.akasztofa11);
                break;
            case 12:
                img.setBackgroundResource(R.drawable.akasztofa12);
                break;
            case 13:
                img.setBackgroundResource(R.drawable.akasztofa13);
                alertBuilder.setTitle("Vesztettél");
                alertBuilder.create();
                alertBuilder.show();
                break;


        }
    }

    private void ujJatek() {
        kitalaltSzo = szavak[random.nextInt(szavak.length)];
        hossz = kitalaltSzo.length();
        megfejtesArray = new char[hossz];
        megfejtes = "";
        for (int i = 0; i < megfejtesArray.length; i++) {
            megfejtesArray[i] = '_';
            megfejtes +="_";
        }

        hasznaltBetu.clear();
        betuindex=0;
        betu.setText(betuk[betuindex]);
        megoldas.setText(megfejtes);
        probalkozas=0;
        img.setBackgroundResource(R.drawable.akasztofa00);
        betu.setTextColor(Color.BLACK);
    }

    private void init() {
        btn_minusz = findViewById(R.id.btn_minusz);
        btn_plusz = findViewById(R.id.btn_plusz);
        btn_tipp = findViewById(R.id.btn_tipp);
        betu = findViewById(R.id.betu);
        megoldas = findViewById(R.id.megoldas);
        img = findViewById(R.id.img);
        random=new Random();
        szavak=new String[]{"ALMA","KUTYA","CICA","MOKUS","PINGVIN","ELEFANT","TIGRIS","OROSZLAN","MAJOM","HAL","CAPA"};
        betuindex=0;
        betuk =new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        hasznaltBetu=new ArrayList<>();
        probalkozas=0;
        alertBuilder=new AlertDialog.Builder(this);
        CreateAlertDialog();
    }
    private void CreateAlertDialog(){
        alertBuilder.setMessage("Szeretne új játékot?");
        alertBuilder.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {finish();}
        });
        alertBuilder.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ujJatek();
                closeContextMenu();
            }
        });
        alertBuilder.setCancelable(false);
    }
}

