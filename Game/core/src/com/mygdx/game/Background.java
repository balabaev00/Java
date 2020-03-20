package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

public class Background {
    class BackgroundPicture {
        private Texture texture;
        private Vector2 position;
        public BackgroundPicture(Vector2 position) {
            texture = new Texture("background.png");
            this.position = position;
        }
    }
    private int gameSpeed;
    private BackgroundPicture[] backgroundPictures;

    public Background() {
        backgroundPictures = new BackgroundPicture[2];
        backgroundPictures[0] = new BackgroundPicture(new Vector2(0,0));
        backgroundPictures[1] = new BackgroundPicture(new Vector2(800,0));
        gameSpeed = 4;
    }

    // Отрисовки для текстур
    public void render(SpriteBatch spriteBatch) {
        for (int i = 0; i < backgroundPictures.length; i++) {
            spriteBatch.draw(backgroundPictures[i].texture,backgroundPictures[i].position.x,backgroundPictures[i].position.y);
        }
    }

    public void update() {
        for (int i = 0; i < backgroundPictures.length; i++) {
            backgroundPictures[i].position.x -= gameSpeed;
        }
        if(backgroundPictures[0].position.x < -800) {
            backgroundPictures[0].position.x = 0;
            backgroundPictures[1].position.x = 800;
        }
    }
}
