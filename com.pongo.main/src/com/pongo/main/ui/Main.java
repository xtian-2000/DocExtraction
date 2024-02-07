package com.pongo.main.ui;

import com.pongo.main.constants.Constants;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
	
    @Override
    public void start(Stage primaryStage) throws Exception{	
    	// Document Viewer
        ScrollPane docview_sc = new ScrollPane();
        docview_sc.setFitToHeight(true);
        docview_sc.setFitToWidth(true);
        
        TextArea documentText = new TextArea();
        documentText.setText(Constants.TEXTAREA_DEF_MESSAGE);
        docview_sc.setContent(documentText);
        
        // Menu section
        VBox leftmenu_vbx = new VBox();
        LeftMenu leftmenu = new LeftMenu();
        leftmenu.createContent(leftmenu_vbx, documentText);
        
        BorderPane root = new BorderPane();
        root.setLeft(leftmenu_vbx);
        root.setCenter(docview_sc);

        // Set up the primary stage
        primaryStage.setTitle("Document Extraction");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
