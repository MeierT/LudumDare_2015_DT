package de.ludumDare_DT.ludumDare_DT_2015.game.components;

import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class LightComponent {

    private PointLight light;

    public LightComponent(RayHandler rayHandler, int rayNumber, Color color,
            float lightingDistance, Vector2 position) {

        light = new PointLight(rayHandler, rayNumber, color, lightingDistance,
                position.x, position.y);

    }

    public PointLight getLight() {
        return light;
    }

    public void setLight(PointLight light) {
        this.light = light;
    }
}
