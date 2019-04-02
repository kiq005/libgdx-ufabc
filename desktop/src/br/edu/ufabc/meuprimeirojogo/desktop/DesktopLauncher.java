package br.edu.ufabc.meuprimeirojogo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import br.edu.ufabc.meuprimeirojogo.MeuJogo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Meu Primeiro Jogo na UFABC";
		new LwjglApplication(new MeuJogo(), config);
	}
}
