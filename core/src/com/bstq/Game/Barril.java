package com.bstq.Game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;

/**
 * Created by Jose on 10/03/2017.
 */

public class Barril extends Actor {
    private Texture imagen;
    boolean right, left, up, down;
    float x;
    float y;
    public Barril(Texture imagen, float x, float y) {
        right = false;
        left = false;
        up = false;
        down = false;
        this.imagen = imagen;
        this.x=x;
        this.y=y;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(imagen,x,y);
    }

}