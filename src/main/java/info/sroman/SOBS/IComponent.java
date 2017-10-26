package info.sroman.SOBS;

import javafx.scene.Node;

public interface IComponent {
		
	public <E extends Node> E getPane();
}
