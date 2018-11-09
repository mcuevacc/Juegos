package pe.edu.uni.www.juegos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnMichi;
    private Button btnPalillos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Juegos by Miguel Cueva");

        btnMichi = (Button) findViewById(R.id.buttonMichi);
        btnMichi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MichiActivity.class);
                startActivity(intent);
            }
        });

        btnPalillos = (Button) findViewById(R.id.buttonPalillos);
        btnPalillos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PalillosActivity.class);
                startActivity(intent);
            }
        });
    }


}
