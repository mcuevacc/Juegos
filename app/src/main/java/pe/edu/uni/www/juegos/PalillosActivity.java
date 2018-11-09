package pe.edu.uni.www.juegos;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PalillosActivity extends AppCompatActivity {

    private  AlertDialog.Builder builder;

    private Player p1;
    private Player p2;

    private TextView textViewTurno;
    private EditText editText;

    private TextView textViewTotal;
    private EditText editTextNumber;

    private Button buttonOk;

    private boolean t1;
    private boolean t2;

    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palillos);

        getSupportActionBar().setTitle("23 PALILLOS");

        initElement();

        reiniciar();
    }

    public void initElement(){
        builder = new AlertDialog.Builder(this);

        p1 = new Player("Jugador 1",R.drawable.x,"x");
        p2= new Player("Jugador 2",R.drawable.o,"o");

        textViewTurno = findViewById(R.id.textViewTurno);
        editText = findViewById(R.id.editText);

        textViewTotal = findViewById(R.id.textViewTotal);
        editTextNumber = findViewById(R.id.editTextNumber);

        buttonOk = findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retirar();
            }
        });
    }

    public void retirar(){
        String nombre = editText.getText().toString();

        if( t2 && nombre.equals("") ){
            Toast.makeText(PalillosActivity.this, "Nombre no válido", Toast.LENGTH_SHORT).show();
            return;
        }

        if( !t1 && nombre.equals(p1.getName()) ){
            Toast.makeText(PalillosActivity.this, "Nombre repetido", Toast.LENGTH_SHORT).show();
            return;
        }

        int number = Integer.parseInt(editTextNumber.getText().toString());
        if( number<1 || number>3 || total<number ){
            Toast.makeText(PalillosActivity.this, "Cantidad no válida", Toast.LENGTH_SHORT).show();
        }else{
            total-=number;
            if(total>1){
                if(t1){
                    t1=false;
                    p1.setName( editText.getText().toString());
                    editText.setText(p2.getName());
                }else if(t2){
                    t2=false;
                    p2.setName( editText.getText().toString());
                    editText.setVisibility(View.INVISIBLE);
                }
                cambiarTurno();
            }else{
                mostrarGanador();
            }
        }
    }

    public void mostrarGanador(){
        if( (p1.isTurno() && total==1) || (p2.isTurno() && total==0) ){
            builder.setTitle("Ganador: "+p1.getName());
        }else{
            builder.setTitle("Ganador: "+p2.getName());
        }
        builder.setMessage("    ");
        AlertDialog dialog = builder.create();
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                reiniciar();
            }
        });
        dialog.show();
    }

    public void cambiarTurno(){
        textViewTotal.setText("Total de Palillos: "+total);
        editTextNumber.setText(""+1);
        if(p1.isTurno()){
            p1.setTurno(false);

            p2.setTurno(true);
            if(!t1 && !t2) textViewTurno.setText("Turno de "+p2.getName());
        }else{
            p2.setTurno(false);

            p1.setTurno(true);
            if(!t1 && !t2) textViewTurno.setText("Turno de "+p1.getName());
        }
    }

    public void reiniciar(){
        total = 23;
        textViewTotal.setText("Total de Palillos: "+total);
        editTextNumber.setText(""+1);

        p1.setTurno(true);
        p2.setTurno(false);

        t1 = true;
        t2 = true;

        textViewTurno.setText("Turno de ");
        editText.setVisibility(View.VISIBLE);
        editText.setText(p1.getName());
    }
}
