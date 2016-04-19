package com.empoluboyarov.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.empoluboyarov.FlappyBird;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// задаем параметры для десктопной версии
		config.width = FlappyBird.WIDTH;
		config.height = FlappyBird.HEIGHT;
		config.title = FlappyBird.TITLE;

		new LwjglApplication(new FlappyBird(), config);
	}
}
