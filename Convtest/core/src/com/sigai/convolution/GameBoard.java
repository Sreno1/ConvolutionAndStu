package com.sigai.convolution;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class GameBoard extends Game{

    private Game game;
    private GameScreen screen;
    //public Stage stage;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    Board board;
    //Player[] player;
    //public SpriteBatch batch;

    public GameBoard()
    {
        game = this;
        //setScreen(new GameScreen(game));
    }

    @Override
    public void create () {

        screen = new GameScreen(this, new Stage());
        board = new Board("one","two");
        game.setScreen(screen);
    }

    @Override
    public void render () {
        super.render();
        //batch.end();
    }

}
