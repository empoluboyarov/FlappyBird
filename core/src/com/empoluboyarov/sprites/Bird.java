package com.empoluboyarov.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Evgeniy on 19.04.2016.
 */
public class Bird {

    private static final int MOVEMENT = 100;
    private static final int GRAVITY = -15;

    private Vector3 position; // координаты
    private Vector3 velocity; // вектор скорости
    private Texture bird;

    private Rectangle bounds;

    public Bird(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        bird = new Texture("bird.png");
        bounds = new Rectangle(x, y, bird.getWidth(), bird.getHeight());
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getBird() {
        return bird;
    }

    public void update(float dt){
        //расчитываем силу притяжения и скорость падения птички , что бы скорость падения увеличичвалсь с течением времени
        if (position.y >0)
            velocity.add(0, GRAVITY, 0);

        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);

        if (position.y < 0)// что бы птичка не улетала за экран
            position.y = 0;

        velocity.scl(1/dt);

        bounds.setPosition(position.x , position.y);
    }

    public void jump(){
        velocity.y = 200;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        bird.dispose();
    }
}
