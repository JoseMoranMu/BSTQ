package com.bstq.Game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Jose on 10/03/2017.
 */

public class Barril extends Actor {
    private Texture imagen;
    boolean right,left,up,down;
    public Barril(Texture imagen){
        right=false;
        left = false;
        up = false;
        down = false;
        this.imagen=imagen;
        setSize(imagen.getWidth(),imagen.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(imagen,getX(),getY());
    }

    @Override
    public void act(float delta) {
        if(left)setX(getX()-225*delta);
    }

    public void setRight(boolean right){
        this.right=right;
    }
    public void setLeft(boolean left){
        this.left=left;
    }
}
