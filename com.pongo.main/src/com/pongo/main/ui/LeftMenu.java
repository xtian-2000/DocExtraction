package com.pongo.main.ui;

import java.awt.image.BufferedImage;
import java.io.File;

import com.pongo.image.ImageUtils;
import com.pongo.main.constants.Constants;
import com.pongo.tesseract.TesseractUtils;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
		TextField filechooser_tf = new TextField();
		filechooser_tf.setEditable(false);

		Button filechooser_btn = new Button("...");
		filechooser_btn.setOnAction(e -> { selectedfile =
				showFileChooser(filechooser_tf); });

		HBox filechooser_hbx = new HBox(filechooser_btn, filechooser_tf);

		// Extract as (Text, Pdf, etc.)
		ComboBox<String> output_cb = new ComboBox<>();
		output_cb.getItems().addAll("Text File");
		output_cb.setPromptText("Text File");    

		Button extract_btn = new Button("Extract");
		extract_btn.setOnAction(e -> { 
			BufferedImage image = ImageUtils.FileToBufferedImage(selectedfile);
			String extracted_text = null;
			try {
				extracted_text = TesseractUtils.imageToText(image);
			} catch (Exception err) {
				err.printStackTrace();
			}
			textarea.setText(extracted_text);
		});

		HBox output_hbx = new HBox(output_cb, extract_btn);

		// Parent layout config
		vbox.setSpacing(10);
		vbox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(extmode_cb, filechooser_hbx, output_hbx);
	}

	private File showFileChooser(TextField filechooser_tf) {
		FileChooser fileChooser = new FileChooser();

		// Set initial directory (optional)
		File initialDirectory = new File(Constants.HOME_DIR);
		fileChooser.setInitialDirectory(initialDirectory);

		// Show the file chooser dialog
		File selectedFile = fileChooser.showOpenDialog(null);
		filechooser_tf.setText(selectedFile.getPath());

		return selectedFile;
	}
}
