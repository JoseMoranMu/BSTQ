package com.bstq.Game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Jose on 18/04/2017.
 */

public class Boton extends ImageButton {
    int id;

    public Boton(TextureRegionDrawable myTexRegionDrawable,int x, int y){
        super(myTexRegionDrawable);
        this.setX(x);
        this.setY(y);

    }
    public void setId(int id){ this.id=id;}
    public int getId(){
        return id;
    }
}
