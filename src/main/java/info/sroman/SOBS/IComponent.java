package info.sroman.SOBS;

import javafx.scene.Node;

public interface IComponent {
	
	/**
	 * Returns the component's container Node.
	 * @param <E> Node type
	 * @return	  the component's container Node
	 */
	public <E extends Node> E getPane();
}
