package com.bstq;

import com.badlogic.gdx.Game;

public class Main extends Game {
	com.bstq.view.MainMenu main;
	@Override
	public void create () {
		main= new com.bstq.view.MainMenu(this);
		setScreen(main);
	}

}
