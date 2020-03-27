/**
 * File: InputManager.java
 * 
 * This file is free to use and modify as it is for educational use.
 * brought to you by Game Programming Snippets (http://gpsnippets.blogspot.com/)
 * 
 * Revisions:
 * 1.1 Initial Revision 
 * 
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class was created to show how implement a keyboard polling system
 * using java.awt.event.KeyListener this allows the ability to capture multiple
 * keys being pressed simulatneously. To use this class just simply add it as a
 * key listener to your JFrame and it will be populated with input. 
 */
public final class InputManager implements KeyListener {
	private int[] keys = new int[256];
	private boolean[] key_state_up = new boolean[256];  //true if not pressed
	private boolean[] key_state_down = new boolean[256]; //true if pressed
	private boolean keyPressed = false;
	
	//variable that indicates that some key was released this frame.
	private boolean keyReleased = false; //cleared every frame.
	
	//a string used as a buffer by widgets or other text input controls
	private String keyCache = "";
	
	//the only instantiated object
	private static InputManager instance = new InputManager();
	
	protected InputManager() {
	}
	public static InputManager getInstance() {
		return instance;
	}
	public void keyPressed(KeyEvent e) {
		if( e.getKeyCode() >= 0 && e.getKeyCode() < 256 ) {
			//keys[e.getKeyCode()] = (int) System.currentTimeMillis();
			keys[e.getKeyCode()] = 1;
			key_state_down[e.getKeyCode()] = true;
			key_state_up[e.getKeyCode()] = false;
			keyPressed = true;
			keyReleased = false;
		}
	}
	public void keyReleased(KeyEvent e) {
		if( e.getKeyCode() >= 0 && e.getKeyCode() < 256 ) {
			keys[e.getKeyCode()] = 0;
			key_state_up[e.getKeyCode()] = true;
			key_state_down[e.getKeyCode()] = false;
			keyPressed = false;
			keyReleased = true;
		}
	}
	public boolean[] returnPressed() {
		return key_state_down;
	}
	public void keyTyped(KeyEvent e) {	
		keyCache += e.getKeyChar();
		
	}
	public boolean isKeyDown( int key ) {
		return key_state_down[key];
	}
	public boolean isKeyUp( int key ) {
		return key_state_up[key];
	}
	public boolean isAnyKeyDown() {
		return keyPressed;
	}
	public boolean isAnyKeyUp() {
		return keyReleased;
	}
	public void update() {
		//clear out the key up states
		key_state_up = new boolean[256];
		keyReleased = false;
		if( keyCache.length() > 1024 ) {
			keyCache = "";
		}
	}
	
} // InputManager
