package Views;

import Models.PrisonerSearchModel;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RowContextMenu {

	ContextMenu contextMenu;
	Stage editModalStage;
	Stage deleteModalStage;
	Pane editContent;
	Pane deleteContent;
	
	PrisonerSearchModel model;
	
	public RowContextMenu(Pane editContent, Pane deleteContent) {
		
		contextMenu = new ContextMenu();
		MenuItem editMenuItem = new MenuItem("Edit Record");

		this.editContent = editContent;
		editContent.setPadding(new Insets(20));
		editModalStage = new Stage();
		editModalStage.initModality(Modality.APPLICATION_MODAL);
		editModalStage.setScene(new Scene(editContent));

		editMenuItem.setOnAction(e -> {
			editModalStage.show();
			contextMenu.hide();
		});

		MenuItem deleteMenuItem = new MenuItem("Delete Record");

		this.deleteContent = deleteContent;
		deleteContent.setPadding(new Insets(20));
		deleteModalStage = new Stage();
		deleteModalStage.initModality(Modality.APPLICATION_MODAL);
		deleteModalStage.setScene(new Scene(deleteContent));

		deleteMenuItem.setOnAction(e -> {
			deleteModalStage.show();
			contextMenu.hide();
		});

		contextMenu.getItems().addAll(FXCollections.observableArrayList(editMenuItem, deleteMenuItem));
	}

	public Stage getEditModalStage() {
		return editModalStage;
	}

	public Stage getDeletModalStage() {
		return deleteModalStage;
	}

	public void show(Parent anchor, double x, double y) {
		contextMenu.show(anchor, Side.TOP, x, y);
	}
	
	public void hide() {
		contextMenu.hide();
	}

}
