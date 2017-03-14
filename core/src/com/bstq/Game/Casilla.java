package com.bstq.Game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Jose on 14/03/2017.
 */

public class Casilla extends Actor {
    Texture t;
    float x;
    float y;
    public Casilla(Texture t, float x, float y){
        this.t=t;
        this.x=x;
        this.y=y;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(t,x,y);
    }

}
