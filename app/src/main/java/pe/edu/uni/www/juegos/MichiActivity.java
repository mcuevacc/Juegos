package pe.edu.uni.www.juegos;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MichiActivity extends AppCompatActivity {

    private  AlertDialog.Builder builder;

    private TextView textViewTurno;
    private EditText editText;
    private ImageButton imgBtnNO;
    private ImageButton imgBtnN;
    private ImageButton imgBtnNE;
    private ImageButton imgBtnCO;
    private ImageButton imgBtnC;
    private ImageButton imgBtnCE;
    private ImageButton imgBtnSO;
    private ImageButton imgBtnS;
    private ImageButton imgBtnSE;

    private Player p1;
    private Player p2;
    private boolean t1;
    private boolean t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_michi);

        getSupportActionBar().setTitle("MICHI");

        initElement();

        reiniciar();
    }

    public void initElement(){
        builder = new AlertDialog.Builder(this);

        p1 = new Player("Jugador 1",R.drawable.x,"x");
        p2= new Player("Jugador 2",R.drawable.o,"o");

        textViewTurno = findViewById(R.id.textViewTurno);
        editText = findViewById(R.id.editText);

        imgBtnNO = findViewById(R.id.imgBtn1);
        imgBtnN = findViewById(R.id.imgBtn2);
        imgBtnNE = findViewById(R.id.imgBtn3);
        imgBtnCO = findViewById(R.id.imgBtn4);
        imgBtnC = findViewById(R.id.imgBtn5);
        imgBtnCE = findViewById(R.id.imgBtn6);
        imgBtnSO = findViewById(R.id.imgBtn7);
        imgBtnS = findViewById(R.id.imgBtn8);
        imgBtnSE = findViewById(R.id.imgBtn9);

        imgBtnNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marcar(1);
            }
        });

        imgBtnN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marcar(2);
            }
        });

        imgBtnNE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marcar(3);
            }
        });

        imgBtnCO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marcar(4);
            }
        });

        imgBtnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marcar(5);
            }
        });

        imgBtnCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marcar(6);
            }
        });

        imgBtnSO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marcar(7);
            }
        });

        imgBtnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marcar(8);
            }
        });

        imgBtnSE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marcar(9);
            }
        });
    }

    public void marcar(int pos){
        String nombre = editText.getText().toString();

        if( t2 && nombre.equals("") ){
            Toast.makeText(MichiActivity.this, "Nombre no válido", Toast.LENGTH_SHORT).show();
            return;
        }

        if( !t1 && nombre.equals(p1.getName()) ){
            Toast.makeText(MichiActivity.this, "Nombre repetido", Toast.LENGTH_SHORT).show();
            return;
        }

        if(espacioLibre(pos)){
            asignarEspacio(pos);
            if(empate()){
                mostrarGanador(false);
            }else{
                if(verificar()){
                    mostrarGanador(true);
                }else{
                    if(t1){
                        t1=false;
                        p1.setName(nombre);
                        editText.setText(p2.getName());
                    }else if(t2){
                        t2=false;
                        p2.setName(nombre);
                        editText.setVisibility(View.INVISIBLE);
                    }
                    cambiarTurno();
                }
            }
        }else{
            Toast.makeText(MichiActivity.this, "Movimiento no válido", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean espacioLibre(int pos){
        String imageName="";
        switch (pos){
            case 1:
                imageName = (String) imgBtnNO.getTag();
                break;
            case 2:
                imageName = (String) imgBtnN.getTag();
                break;
            case 3:
                imageName = (String) imgBtnNE.getTag();
                break;
            case 4:
                imageName = (String) imgBtnCO.getTag();
                break;
            case 5:
                imageName = (String) imgBtnC.getTag();
                break;
            case 6:
                imageName = (String) imgBtnCE.getTag();
                break;
            case 7:
                imageName = (String) imgBtnSO.getTag();
                break;
            case 8:
                imageName = (String) imgBtnS.getTag();
                break;
            case 9:
                imageName = (String) imgBtnSE.getTag();
                break;
        }

        return ImagenLibre(imageName);
    }

    public boolean ImagenLibre(String imagen){
        if( imagen.equals("empty") ){
            return true;
        }else
            return false;
    }

    public void asignarEspacio(int pos) {

        int img;
        String tag;
        if(p1.isTurno()){
            img = p1.getImage();
            tag = p1.getTag();
        }else{
            img = p2.getImage();
            tag = p2.getTag();
        }

        switch (pos){
            case 1:
                imgBtnNO.setImageResource(img);
                imgBtnNO.setTag(tag);
                break;
            case 2:
                imgBtnN.setImageResource(img);
                imgBtnN.setTag(tag);
                break;
            case 3:
                imgBtnNE.setImageResource(img);
                imgBtnNE.setTag(tag);
                break;
            case 4:
                imgBtnCO.setImageResource(img);
                imgBtnCO.setTag(tag);
                break;
            case 5:
                imgBtnC.setImageResource(img);
                imgBtnC.setTag(tag);
                break;
            case 6:
                imgBtnCE.setImageResource(img);
                imgBtnCE.setTag(tag);
                break;
            case 7:
                imgBtnSO.setImageResource(img);
                imgBtnSO.setTag(tag);
                break;
            case 8:
                imgBtnS.setImageResource(img);
                imgBtnS.setTag(tag);
                break;
            case 9:
                imgBtnSE.setImageResource(img);
                imgBtnSE.setTag(tag);
                break;
        }
    }

    public void cambiarTurno(){
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

    public boolean empate(){
        if( !((String) imgBtnNO.getTag()).equals("empty") &&
                !((String) imgBtnN.getTag()).equals("empty") &&
                !((String) imgBtnNE.getTag()).equals("empty") &&
                !((String) imgBtnCO.getTag()).equals("empty") &&
                !((String) imgBtnC.getTag()).equals("empty") &&
                !((String) imgBtnCE.getTag()).equals("empty") &&
                !((String) imgBtnSO.getTag()).equals("empty") &&
                !((String) imgBtnS.getTag()).equals("empty") &&
                !((String) imgBtnSE.getTag()).equals("empty") ){
            return true;
        }else
            return false;
    }

    public boolean verificar(){

        String strNO = (String) imgBtnNO.getTag();
        String strN = (String) imgBtnN.getTag();
        String strNE = (String) imgBtnNE.getTag();
        String strCO = (String) imgBtnCO.getTag();
        String strC = (String) imgBtnC.getTag();
        String strCE = (String) imgBtnCE.getTag();
        String strSO = (String) imgBtnSO.getTag();
        String strS = (String) imgBtnS.getTag();
        String strSE = (String) imgBtnSE.getTag();

        if( !strN.equals("empty") && strN.equals(strNO) && strN.equals(strNE) )
            return true;
        else if( !strC.equals("empty") && strC.equals(strCO) && strC.equals(strCE) )
            return true;
        else if( !strS.equals("empty") && strS.equals(strSO) && strS.equals(strSE) )
            return true;
        else if( !strCO.equals("empty") && strCO.equals(strNO) && strCO.equals(strSO) )
            return true;
        else if( !strC.equals("empty") && strC.equals(strN) && strC.equals(strS) )
            return true;
        else if( !strCE.equals("empty") && strCE.equals(strNE) && strCE.equals(strSE) )
            return true;
        else if( !strC.equals("empty") && strC.equals(strNO) && strC.equals(strSE) )
            return true;
        else if( !strC.equals("empty") && strC.equals(strSO) && strC.equals(strNE) )
            return true;
        else
            return false;
    }

    public void mostrarGanador(boolean flag){
        if(!flag){
             builder.setTitle("Empate");
        }else{
            if(p1.isTurno()){
                builder.setTitle("Ganador: "+p1.getName());
            }else{
                builder.setTitle("Ganador: "+p2.getName());
            }
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

    public void reiniciar(){
        imgBtnNO.setImageResource(R.drawable.empty);
        imgBtnN.setImageResource(R.drawable.empty);
        imgBtnNE.setImageResource(R.drawable.empty);
        imgBtnCO.setImageResource(R.drawable.empty);
        imgBtnC.setImageResource(R.drawable.empty);
        imgBtnCE.setImageResource(R.drawable.empty);
        imgBtnSO.setImageResource(R.drawable.empty);
        imgBtnS.setImageResource(R.drawable.empty);
        imgBtnSE.setImageResource(R.drawable.empty);

        imgBtnNO.setTag("empty");
        imgBtnN.setTag("empty");
        imgBtnNE.setTag("empty");
        imgBtnCO.setTag("empty");
        imgBtnC.setTag("empty");
        imgBtnCE.setTag("empty");
        imgBtnSO.setTag("empty");
        imgBtnS.setTag("empty");
        imgBtnSE.setTag("empty");

        p1.setTurno(true);
        p2.setTurno(false);

        t1 = true;
        t2 = true;

        textViewTurno.setText("Turno de ");
        editText.setVisibility(View.VISIBLE);
        editText.setText(p1.getName());
    }
}

