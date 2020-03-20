package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player {
    Texture texture;
    Vector2 position;

    public Player() {
        texture = new Texture("player.png");
        position = new Vector2(100,100);
    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture,position.x,position.y);
    }

    public void update() {

    }
}
