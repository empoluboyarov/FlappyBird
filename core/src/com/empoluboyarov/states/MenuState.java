package com.empoluboyarov.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.empoluboyarov.FlappyBird;

/**
 * Created by Evgeniy on 19.04.2016.
 */
public class MenuState extends State{

    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
    }

    @Override
    protected void handleInput() {
        // запускаем игровой экран при клики или нажатии
        if(Gdx.input.justTouched())
            gsm.set(new PlayState(gsm));

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0, 0 , FlappyBird.WIDTH, FlappyBird.HEIGHT);
        sb.draw(playBtn, (FlappyBird.WIDTH/2) - (playBtn.getWidth()/2), FlappyBird.HEIGHT/2);
        sb.end();
    }

    @Override
    public void dispose() {
        playBtn.dispose();
        background.dispose();
    }
}
