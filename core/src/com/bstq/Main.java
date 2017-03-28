package com.bstq;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Main extends Game {
	private com.bstq.view.MainMenu main;
	@Override
	//Main
	public void create () {
		main= new com.bstq.view.MainMenu(this);
		setScreen(main);
	}

}
