package com.bstq.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bstq.Game.Boton;

/**
 * Created by Jose on 28/02/2017.
 */

public class ButtonHandler extends Button {
    private Boton b;

    public ButtonHandler() {

    }

    /**
     * Method to create a Button actor,
     * @param texture texture of the button
     * @param x position X of the button
     * @param y position Y of the button
     * @return Button object
     */
    public Boton getButton(Texture texture, int x, int y){
        TextureRegion myTextureRegion = new TextureRegion(texture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        b = new Boton(myTexRegionDrawable,x,y);
        return b;
    }

}
