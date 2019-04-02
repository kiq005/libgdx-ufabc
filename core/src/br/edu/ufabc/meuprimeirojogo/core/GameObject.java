package br.edu.ufabc.meuprimeirojogo.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController.AnimationDesc;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController.AnimationListener;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

import br.edu.ufabc.meuprimeirojogo.MeuJogo;

public class GameObject extends ModelInstance{
	private boolean visible;
	private boolean animated;
	private boolean animationFinished;
	private AnimationController controller;
	private BoundingBox         boundingBox;
	private Vector3				minOriginal;
	private Vector3				maxOriginal;
	private Vector3             ctrOriginal;
	private float               angle;
	
	/* for debug */
	private ModelInstance       boxInstance;
	private Vector3             position;
	
	public GameObject(Model model) {
		super(model);
		visible = true;  
		animationFinished = false;
		boundingBox = new BoundingBox();
		calculateBoundingBox(boundingBox);
		//System.out.println("Bounding Box = "+boundingBox);
		// for debug reasons
		minOriginal = new Vector3();
		maxOriginal = new Vector3();
		ctrOriginal = new Vector3();
		boundingBox.getMax(minOriginal);
		boundingBox.getMin(maxOriginal);
		boundingBox.getCenter(ctrOriginal);
		boxInstance = new ModelInstance(MeuJogo.modelBuider.createBox(
				(Math.abs(maxOriginal.x)+Math.abs(minOriginal.x)), 
				(Math.abs(maxOriginal.y)+Math.abs(minOriginal.y)), 
				(Math.abs(maxOriginal.z)+Math.abs(minOriginal.z)), 
                new Material(ColorAttribute.createDiffuse(Color.LIGHT_GRAY)),
                Usage.Position | Usage.Normal));
		BlendingAttribute bl = new BlendingAttribute(GL20.GL_SRC_ALPHA 
				                                   | GL20.GL_ONE_MINUS_SRC_ALPHA);
		bl.opacity = 0.6f;
		boxInstance.materials.get(0).set(bl);
		position = new Vector3();
				
		
	}
	public GameObject(Model model, boolean visible) {
		this(model);
		this.visible = visible;
	}
	public GameObject(Model model, boolean visible, boolean animated, boolean looped, float loopSpeed) {
		this(model, visible);
		this.animated = animated;
		controller = new AnimationController(this);
		if (animated) {
			controller.setAnimation(this.animations.first().id, (looped)? -1: 1, loopSpeed, 
					new AnimationListener() {

						@Override
						public void onEnd(AnimationDesc animation) {
							// TODO Auto-generated method stub
							animationFinished = true;
						}

						@Override
						public void onLoop(AnimationDesc animation) {
							// TODO Auto-generated method stub
							animationFinished = true;
						}
				
			});
		}
	}
	
	public void updateBoundingBox() {
		this.transform.getTranslation(position);
		boundingBox.set(position.cpy().add(minOriginal), 
				        position.cpy().add(maxOriginal));
		ctrOriginal.add(position.cpy());
		
		position.y = (boundingBox.getHeight()/2);  	    
		boxInstance.transform.setToTranslation(position);
	}
	
	public void update(float delta) {
		if (animated) {
		   controller.update(delta);
		}
		// for debug reasons
		
		
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public boolean isAnimationFinished() {
		return this.animationFinished;
	}
	public void resetAnimation() {
		animationFinished = false;		
	}
	
	
	public float getAngle() {
		return angle;
	}
	public void setAngle(float angle) {
		this.angle += angle;
		if (this.angle >= 360) this.angle -= 360;
	}
	public BoundingBox getBoundingBox() {
		return boundingBox;
	}
	
	public ModelInstance getBoxInstance() {
		return boxInstance;
	}
	
	public void updateBoxScale(float scale) {
		minOriginal.scl(scale);
		maxOriginal.scl(scale);
	}
	
	public void updateBoxScale(float scaleX, float scaleY, float scaleZ) {
	    minOriginal.scl(scaleX, scaleY, scaleZ);
	    maxOriginal.scl(scaleX, scaleY, scaleZ);
	}
	
	

}
