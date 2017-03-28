package com.bstq;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Main extends Game {
	@Override
	//Main
	public void create () {
		setScreen(new com.bstq.view.MainMenu(this));
	}

}
