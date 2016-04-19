package com.empoluboyarov.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
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
    private Texture ground;
    public static final int TUBE_COUNT = 4;
    public static final int TUBE_SPACING = 125; //расстояние между появляющимимся трубами
    public static final int GROUND_Y_OFFSET = -30;
    private Array<Tube> tubes;
    private Vector2 groundPos1, groundPos2;



    public PlayState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, FlappyBird.WIDTH/2, FlappyBird.HEIGHT/2);
        bird = new Bird(50, 300);
        bg = new Texture("bg.png");
        ground = new Texture("ground.png");
        tubes = new Array<Tube>();
        for (int i =0; i < TUBE_COUNT; i++){
            tubes.add(new Tube(i*(TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth/2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((camera.position.x - camera.viewportWidth/2) + ground.getWidth(), GROUND_Y_OFFSET);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
            bird.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);

        camera.position.x = bird.getPosition().x + 80;

        for (int i = 0; i < tubes.size; i++){
            Tube tube = tubes.get(i);

            if (camera.position.x - (camera.viewportWidth/2) > tube.getPosBotTube().x + tube.getTopTube().getWidth())
                tube.reposition(tube.getPosTopTube().x+((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));

            if (tube.collides(bird.getBounds()))// проверяем на столкновение с трубами
                gsm.set(new PlayState(gsm));
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
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();
    }

    public void updateGround(){
        if (camera.position.x - (camera.viewportWidth/2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth()*2, 0);
        if (camera.position.x - (camera.viewportWidth/2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth()*2, 0);
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        for (Tube tube : tubes)
            tube.dispose();
        ground.dispose();

    }
}
