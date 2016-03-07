package com.sigai.convolution;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Richard on 2/18/2016.
 */
public class GameScreen implements Screen {
    public static Stage stage;
    final GameBoard game;
    SpriteBatch batch;
    //private final SpriteBatch batch;

    public GameScreen(final GameBoard game, Stage stage)
    {
        this.game = game;
        this.stage = stage;
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        //stage.draw();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        /*
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        for(Cell[] i : Board.board)
            for(Cell j : i)
                j.draw(batch, 0);

        for(Player p : Board.pieces)
            p.draw(batch, 0);

        batch.end();
        */
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
