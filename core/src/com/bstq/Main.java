package com.bstq;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.bstq.view.MainMenu;

public class Main extends Game {
	@Override
	//Main
	public void create () {
		setScreen(new MainMenu(this));
	}

}
