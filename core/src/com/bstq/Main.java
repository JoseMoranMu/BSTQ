package com.bstq;

import com.badlogic.gdx.Game;
import com.bstq.Model.User;
import com.bstq.view.LoginMenu;

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
