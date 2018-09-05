package main.states;

import java.awt.Font;
import java.awt.Graphics;

import main.Game;

public class MenuState extends State{

	private String[] options = {"Start", "Quit"};
	
	private int currentCursor = 0;
	
	public MenuState(Game game) {
		super(game);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setFont(new Font("Arial", Font.PLAIN, 40));
		
		//g.drawString(options, , y)
	}

}
