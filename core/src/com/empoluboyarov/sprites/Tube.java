package com.empoluboyarov.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Evgeniy on 19.04.2016.
 */
public class Tube {
    public static final int FLUCTUATION = 130; //значение диапазона отклнений по высоте на котором будт создаваться трубы
    public static final int TUBE_GAP = 100; // значение высоты просвета
    public static final int LOWEST_OPENING = 120; //нижняя граница просвета



    public static final int TUBE_WIDTH = 52;

    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBotTube;
    private Random random;

    private Rectangle boundsTop, boundsBot;

    public Tube(float x) {
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        random = new Random();

        posTopTube = new Vector2(x , random.nextInt(FLUCTUATION)+ TUBE_GAP+ LOWEST_OPENING);
        posBotTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

        boundsTop = new Rectangle(posTopTube.x, posTopTube.y , topTube.getWidth(),topTube.getHeight());
        boundsBot = new Rectangle(posBotTube.x, posBotTube.y , bottomTube.getWidth() ,bottomTube.getHeight());
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Texture getTopTube() {
        return topTube;
    }

    public void reposition(float x){
        posTopTube.set(x , random.nextInt(FLUCTUATION)+ TUBE_GAP+ LOWEST_OPENING);
        posBotTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBot.setPosition(posBotTube.x, posBotTube.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
    }
}
