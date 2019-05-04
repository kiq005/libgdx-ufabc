package br.edu.ufabc.meuprimeirojogo.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.edu.ufabc.meuprimeirojogo.model.Hero;
import br.edu.ufabc.meuprimeirojogo.util.Utilities;

public class HUD {
	private Texture _hud_bg;
	
	private Texture _health_lanter_bg;
	private Texture _health_lanter_fg;
	private Texture _health_bar;
	
	private Texture _power_box;
	private Texture _power_fire;
	private Texture _power_shield;
	private Texture _power_speed;
	private Texture _power_sword;
	private Texture _power_water;
	
	private DungeonAction action;
	
	private BitmapFont bitmapFont;
	
	public HUD(DungeonAction action) {
		this.action = action;
		
		_hud_bg = new Texture(Gdx.files.internal("UI/HUD/inventory-middle.png"));
		
		_health_lanter_bg = new Texture(Gdx.files.internal("UI/HUD/health-lantern.png"));
		_health_lanter_fg = new Texture(Gdx.files.internal("UI/HUD/health-lantern-front.png"));
		_health_bar = new Texture(Gdx.files.internal("UI/HUD/health-bar.png"));
		
		_power_box = new Texture(Gdx.files.internal("UI/HUD/box.png"));
		
		_power_fire = new Texture(Gdx.files.internal("UI/HUD/power-fire.png"));
		_power_shield = new Texture(Gdx.files.internal("UI/HUD/power-shield.png"));
		_power_speed = new Texture(Gdx.files.internal("UI/HUD/power-speed.png"));
		_power_sword = new Texture(Gdx.files.internal("UI/HUD/power-sword.png"));
		_power_water = new Texture(Gdx.files.internal("UI/HUD/power-water.png"));
		
		bitmapFont = new BitmapFont(Gdx.files.internal("Fonts/diabloFont.fnt"));
	}

	public void draw(SpriteBatch batch) {
		batch.draw(_health_lanter_bg, 10, 10, 64, 120);
		batch.draw(_health_bar, 20, 30, 44, 50 * Math.max(0f, action.hero.getHealthPoints() / action.hero.getMaxHealthPoints()) );
		batch.draw(_health_lanter_fg, 10, 10, 64, 120);
		
		batch.draw(_power_box, 80, 10, 40, 40);
		batch.draw(_power_sword, 80, 10, 40, 40);
		
		bitmapFont.draw(batch, String.valueOf(action.hero.GetCounter()), 100, 100);
		if(action.hero.GetEnemy() != null) {
			bitmapFont.draw(batch, action.hero.GetEnemy().GetType(), 10, 200);
		}
		
	}
	
}
