package com.pongo.main.ui;

import java.awt.image.BufferedImage;
import java.io.File;

import com.pongo.image.ImageUtils;
import com.pongo.main.constants.Constants;
import com.pongo.tesseract.TesseractUtils;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;


public class LeftMenu {
	File selectedfile = null;

	public void createContent(VBox vbox, TextArea textarea) {
		// Mode of extraction ComboBox
		ComboBox<String> extmode_cb = new ComboBox<>();
		extmode_cb.getItems().addAll("Tesseract", "Google OCR");
		extmode_cb.setPromptText("Tesseract");        

		// File Chooser     
		
		Label filename_l = new Label();
		filename_l.setText(Constants.FILENAME_LABEL_DEF_MESSAGE);
		filename_l.setPadding(new javafx.geometry.Insets(5, 0, 0, 10));

		Button filechooser_btn = new Button("...");
		filechooser_btn.setOnAction(e -> { 
			selectedfile = showFileChooser(filename_l); 
			if (selectedfile != null) {
				BufferedImage image = ImageUtils.FileToBufferedImage(selectedfile);
				String extracted_text = null;
				try {
					extracted_text = TesseractUtils.imageToText(image);
				} catch (Exception err) {
					err.printStackTrace();
				}
				textarea.setText(extracted_text);
			}
		});

		HBox filechooser_hbx = new HBox(filechooser_btn, filename_l);

		// Extract as (Text, Pdf, etc.)
		ComboBox<String> output_cb = new ComboBox<>();
		output_cb.getItems().addAll("Text File");
		output_cb.setPromptText("Text File");    

		Button extract_btn = new Button("Save");
		extract_btn.setOnAction(e -> { 
			System.out.println("Save Button");
			resetComponentState(textarea, filename_l);
		});

		HBox output_hbx = new HBox(output_cb, extract_btn);

		// Parent layout config
		vbox.setSpacing(10);
		vbox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(extmode_cb, filechooser_hbx, output_hbx);
	}
	
	private void resetComponentState(TextArea textarea, Label filename_l) {
		textarea.setText(Constants.TEXTAREA_DEF_MESSAGE);
		filename_l.setText(Constants.FILENAME_LABEL_DEF_MESSAGE);
	}
	
	private File showFileChooser(Label label) {
		FileChooser fileChooser = new FileChooser();

		// Set initial directory (optional)
		File initialDirectory = new File(Constants.HOME_DIR);
		fileChooser.setInitialDirectory(initialDirectory);

		// Show the file chooser dialog
		File selectedFile = fileChooser.showOpenDialog(null);
		label.setText(selectedFile.getName());

		return selectedFile;
	}
}
