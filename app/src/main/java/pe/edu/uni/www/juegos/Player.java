package pe.edu.uni.www.juegos;

public class Player {
    private String name;
    private boolean turno;
    private int image;
    private String tag;

    public Player(){
        this.name="";
        this.turno=false;
        this.image=0;
        this.tag="";

    }

    public Player(String name, int image,String tag){
        this.name=name;
        this.turno=false;
        this.image=image;
        this.tag=tag;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
