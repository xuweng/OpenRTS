package controller.game;

import java.util.logging.Logger;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;

import controller.InputInterpreter;
import event.EventManager;
import event.InputEvent;
import geometry.geom2d.Point2D;

public class GameInputInterpreter extends InputInterpreter {

	private static final Logger logger = Logger.getLogger(GameInputInterpreter.class.getName());

	protected final static String SELECT = "select";
	protected final static String ACTION = "action";
	protected final static String MOVE_ATTACK = "moveattack";
	protected final static String MULTIPLE_SELECTION = "multipleselection";
	protected final static String HOLD = "hold";
	protected final static String PAUSE = "pause";

	GameInputInterpreter(GameController ctl) {
		super(ctl);
		mappings = new String[] { SELECT, ACTION, MOVE_ATTACK, MULTIPLE_SELECTION, HOLD, PAUSE };
	}


	@Override
	protected void registerInputs(InputManager inputManager) {

		inputManager.addMapping(SELECT, new MouseButtonTrigger(0));
		inputManager.addMapping(ACTION, new MouseButtonTrigger(1));
		inputManager.addMapping(MOVE_ATTACK, new KeyTrigger(KeyInput.KEY_A));
		inputManager.addMapping(MULTIPLE_SELECTION, new KeyTrigger(KeyInput.KEY_LCONTROL), new KeyTrigger(KeyInput.KEY_RCONTROL));
		inputManager.addMapping(HOLD, new KeyTrigger(KeyInput.KEY_H));
		inputManager.addMapping(PAUSE, new KeyTrigger(KeyInput.KEY_SPACE));

		inputManager.addListener(this, mappings);
	}

	@Override
	public void onAnalog(String name, float value, float tpf) {
	}

	@Override
	public void onAction(String name, boolean isPressed, float tpf) {
		logger.info("User create Event on Client:" + name);
		InputEvent event = new InputEvent(name, getSpatialCoord(), isPressed);
		EventManager.post(event);
	}

	private Point2D getSpatialCoord() {
		return ctrl.spatialSelector.getCoord((((GameController) ctrl).view.getRootNode()));
	}
}
