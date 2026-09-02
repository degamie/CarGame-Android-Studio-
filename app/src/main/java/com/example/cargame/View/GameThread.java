package com.example.cargame.View;

public class GameThread {
    boolean b=false;
    public void setRunning(boolean b) {
        this.b = b;
    }

    public void start() {
        this.b = true;
    }

}
