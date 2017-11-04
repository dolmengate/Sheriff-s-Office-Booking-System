package info.sroman.SOBS;

import javafx.scene.Node;

public interface IComponent {
	
	/*
		THIS CAN BE AN ABSTRACT CLASS
	*/
		
	public <E extends Node> E getPane();
}
