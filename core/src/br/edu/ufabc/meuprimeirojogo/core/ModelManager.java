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
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
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
	}
	
	@SuppressWarnings("unchecked")
	public void loadBundle(String[] bundleNames) {
		JSONObject objects = new JSONObject(); 
		
		for(String bundle : bundleNames) {
			try (FileReader reader = new FileReader(ASSET_BUNDLES_PATH + bundle + ".json")) {
				JSONObject arr = (JSONObject) parser.parse(reader);
				for(Object key : arr.keySet())
				{
					objects.put(key, arr.get(key));
				}
			} catch (FileNotFoundException e) {
				System.err.println("File not found..." + e.getMessage());
			} catch (IOException e) {
				System.err.println("IOException..." + e.getMessage());
			} catch (ParseException e) {
				System.err.println("ParseException..." + e.getMessage());
			}
		}
		
		for(String key : assets.keySet()) {
			if (!objects.containsKey(key))
				assetManager.unload(assets.get(key));
		}
		for(Object key : objects.keySet()) {
			if (!assets.containsKey((String)key)) {
				assets.put((String) key, (String) objects.get(key));
				assetManager.load(assets.get((String) key), Model.class);
			}
		}
	}
	
	public Model getModel(String name) {
		Model go = assetManager.get(assets.get(name));
		
		BlendingAttribute bl = new BlendingAttribute(GL20.GL_SRC_ALPHA | GL20.GL_ONE_MINUS_SRC_ALPHA);
		bl.opacity = 1f;
		
		for(Material mat : go.materials) {
			mat.remove(ColorAttribute.Emissive);
			mat.set(bl);
		}
		
		return go;
	}
	
}
