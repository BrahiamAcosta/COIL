package modelos;

/**
 *
 * @author Brahiam
 */
public class PersonajePpal {
    private boolean juego1Terminado = false;
    private boolean juego2Terminado = false;
    private boolean juego3Terminado = false;
    
    public boolean terminado(){
        if(juego1Terminado && juego2Terminado && juego3Terminado){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean posibleMovDer(int x, int y){
        if((x>380)&&((y>360)&&(y<430))){
            return false;
        }else{
            return true;
        }
    }

    public boolean isJuego1Terminado() {
        return juego1Terminado;
    }

    public void setJuego1Terminado(boolean juego1Terminado) {
        this.juego1Terminado = juego1Terminado;
    }

    public boolean isJuego2Terminado() {
        return juego2Terminado;
    }

    public void setJuego2Terminado(boolean juego2Terminado) {
        this.juego2Terminado = juego2Terminado;
    }

    public boolean isJuego3Terminado() {
        return juego3Terminado;
    }

    public void setJuego3Terminado(boolean juego3Terminado) {
        this.juego3Terminado = juego3Terminado;
    }
}
