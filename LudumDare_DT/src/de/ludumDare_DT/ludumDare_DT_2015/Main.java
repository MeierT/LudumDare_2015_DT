package de.ludumDare_DT.ludumDare_DT_2015;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.ludumDare_DT.ludumDare_DT_2015.game.Game;

public class Main {
	public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "LudumDare_DT_2015";
        config.width = 800;
        config.height = 480;
        config.resizable = false;
        new LwjglApplication(new Game(), config);
    }
}
