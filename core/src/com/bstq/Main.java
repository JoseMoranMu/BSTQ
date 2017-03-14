package com.bstq;

import com.badlogic.gdx.Game;

public class Main extends Game {
	private com.bstq.view.MainMenu main;
	@Override
	//Main hola
	public void create () {
		main= new com.bstq.view.MainMenu(this);
		setScreen(main);
	}

}
