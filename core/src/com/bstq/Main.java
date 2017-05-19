package com.bstq;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.bstq.Service.User;
import com.bstq.view.LoginMenu;
import com.bstq.view.MainMenu;

public class Main extends Game {
	User userLoged;
	@Override
	//Main
	public void create () {

		setScreen(new LoginMenu(this));

	}

	public User getUserLoged() {
		return userLoged;
	}

	public void setUserLoged(User userLoged) {
		this.userLoged = userLoged;
	}
}
