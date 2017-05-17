package com.bstq.view;

import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.bstq.Main;

/**
 * Created by Jose on 17/05/2017.
 */

public class SignUpMenu extends Menu {
    final Main main;
    TextField userName;
    TextField email;
    TextField pass;
    TextField repeatPass;
    public SignUpMenu(Main main){
        this.main=main;
    }

}
