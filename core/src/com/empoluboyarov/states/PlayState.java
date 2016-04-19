package com.empoluboyarov.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.empoluboyarov.FlappyBird;
import com.empoluboyarov.sprites.Bird;
import com.empoluboyarov.sprites.Tube;

/**
 * Created by Evgeniy on 19.04.2016.
 */
public class PlayState extends State{

    private Bird bird;
    private Texture bg;
    public static final int TUBE_COUNT = 4;
    public static final int TUBE_SPACING = 125; //расстояние между появляющимимся трубами
    private Array<Tube> tubes;



    public PlayState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, FlappyBird.WIDTH/2, FlappyBird.HEIGHT/2);
        bird = new Bird(50, 300);
        bg = new Texture("bg.png");

        tubes = new Array<Tube>();
        for (int i =0; i < TUBE_COUNT; i++){
            tubes.add(new Tube(i*(TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
            bird.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);

        camera.position.x = bird.getPosition().x + 80;

        for (Tube tube: tubes){
            if (camera.position.x - (camera.viewportWidth/2) > tube.getPosBotTube().x + tube.getTopTube().getWidth())
                tube.reposition(tube.getPosTopTube().x+((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
        }

        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg,camera.position.x - (camera.viewportWidth)/2, 0);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube: tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
