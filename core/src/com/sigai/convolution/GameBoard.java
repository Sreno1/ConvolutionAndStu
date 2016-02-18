package com.sigai.convolution;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputListener;


public class GameBoard extends Game implements InputProcessor {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
	public SpriteBatch batch;
    Player[] player;

	@Override
	public void create () {
        Board b = new Board("one","two");
        Board.board = new Cell[8][8];
        player = new Player[16];
        //Gdx.input.setInputProcessor(this);
        int cellSize = WIDTH/8;
        //int boardOffset =  (HEIGHT-cellSize*8)/2;
		batch = new SpriteBatch();
        int i = 0;
        for(int x=0;x<8;x++)
            for(int y=0;y<8;y++)
            {
                Board.board[x][y] = new Cell(x, y, cellSize, cellSize * (x + 1 / 2) + 4, cellSize * (y + 1 / 2) + 4);

                if(y == 0 || y == 7) {
                    player[i] = new Player(x, y, cellSize, cellSize * (x + 1 / 2) + 4, cellSize * (y + 1 / 2) + 4, Board.board[x][y]);
                    i++;

                }

            }
	}

	@Override
	public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //batch.begin();
        int i = 0;
        for(int x = 0; x < 8; x++)
            for(int y = 0; y < 8; y++)
            {
                Board.board[x][y].render(batch);
                if(y == 0 || y == 7) {
                    player[i].render(batch);
                    i++;
                }

                batch.end();
            }
        //batch.end();
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
