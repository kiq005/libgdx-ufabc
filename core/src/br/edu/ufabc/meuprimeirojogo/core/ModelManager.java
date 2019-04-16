package br.edu.ufabc.meuprimeirojogo.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

public class ModelManager {
	private static final String ASSET_BUNDLES_PATH = "./Configs/AssetBundles/";
	
	private Map<String, String> assets;   
	
	public AssetManager assetManager;
	public JSONParser parser;
	
	public ModelManager() {
		assetManager = new AssetManager();
		parser = new JSONParser();
		
		assets = new HashMap<String, String>();
		
		loadBundle("Level1");
	}
	
	public void loadBundle(String bundleName) {
		try (FileReader reader = new FileReader(ASSET_BUNDLES_PATH + bundleName + ".json")) {
			JSONObject arr = (JSONObject) parser.parse(reader);
			for(String key : assets.keySet()) {
				if (!arr.containsKey(key))
					assetManager.unload(assets.get(key));
			}
			for(Object key : arr.keySet()) {
				if (!assets.containsKey((String)key)) {
					assets.put((String) key, (String) arr.get(key));
					assetManager.load(assets.get((String) key), Model.class);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found...");
		} catch (IOException e) {
			System.err.println("IOException...");
		} catch (ParseException e) {
			System.err.println("ParseException..." + e.getMessage());
		}
		
	}
	
	
}
