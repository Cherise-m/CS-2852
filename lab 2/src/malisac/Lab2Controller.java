/*
 * Course: CS 2852
 * Term: Spring 2021
 * Assignment Name: Lab 2
 * Name: Cherise Malisa
 * Created:18/03/2021
 */

package malisac;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Controller for the lab 2 GUI
 */
public class Lab2Controller {

    FileChooser chooser = new FileChooser();
    File file;
    Picture picture;
    Picture updatedPicture;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    Canvas canvas;
    @FXML
    MenuButton draw;
    @FXML
    CheckMenuItem arrayList;
    @FXML
    CheckMenuItem linkedList;


    /**
     * opens and draws picture from specified file path
     *
     * @throws IOException if file is incorrect
     */
    @FXML
    public void open() throws IOException {
        file = new File("data");
        chooser.setInitialDirectory(file);
        try {
            file = chooser.showOpenDialog(null);
        } catch (IllegalArgumentException e) {
            alert.setTitle("Alert");
            alert.setContentText("file picked is not a .dot file");
            alert.showAndWait();
        }
        if (file != null) {
            picture = new Picture(list());
            try {
                picture.load(file.toPath());
                picture.drawDots(canvas);
                picture.drawLines(canvas);
                updatedPicture = new Picture(picture, list());
                updatedPicture = picture;
            } catch (IllegalArgumentException e) {
                alert.setTitle("Alert");
                alert.setContentText("file picked is not a .dot file");
                alert.showAndWait();
            }

        } else {
            alert.setTitle("Alert");
            alert.setContentText("no dot file was loaded");
            alert.showAndWait();
        }

    }

    /**
     * closes the GUI
     */
    @FXML
    public void close() {
        Platform.exit();
    }

    /**
     * passes picture to save method where it will be writen into a file
     * and saved
     */
    @FXML
    public void save() {

        file = new File("data");
        chooser.setInitialDirectory(file);
        file = chooser.showSaveDialog(null);

        if (file != null) {
            try {
                updatedPicture.save(file.toPath());
            } catch (FileNotFoundException e) {
                alert.setTitle("Alert");
                alert.setContentText("file you attempting to save to does not exist");
                alert.showAndWait();
            }
        } else {
            alert.setTitle("Alert");
            alert.setContentText("no dot file was loaded");
            alert.showAndWait();
        }
    }

    /**
     * draws picture in lines only
     */
    @FXML
    public void linesOnly() {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        if (file != null) {
            updatedPicture.drawLines(canvas);
        } else {
            alert.setTitle("Alert");
            alert.setContentText("no dot file was loaded");
            alert.showAndWait();
        }
    }

    /**
     * draws picture in dots only
     */
    @FXML
    public void dotsOnly() {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        if (file != null) {
            updatedPicture.drawDots(canvas);
        } else {
            alert.setTitle("Alert");
            alert.setContentText("no dot file was loaded");
            alert.showAndWait();
        }

    }

    /**
     * asks user how many dots they want to keep
     * the calls remove to remove dots
     */
    public void numberOfDots() {

        TextInputDialog dialog = new TextInputDialog();
        Alert alert2 = new Alert(Alert.AlertType.ERROR);

        dialog.setContentText("Enter number of dots you want to keep in the image");
        dialog.setContentText("Desired number of dots");
        Optional<String> response = dialog.showAndWait();
        String strategy;
        List<String> choices = new ArrayList<>();
        choices.add("a");
        choices.add("b");

        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>("a", choices);
        choiceDialog.setTitle("Choice Dialog");
        choiceDialog.setHeaderText(" select Choice a: remove dots index " +
                "\nChoice b: remove dots iterator");
        choiceDialog.setContentText("choose a letter:");

        Optional<String> result = choiceDialog.showAndWait();
        if (result.isPresent() && response.isPresent()) {
            strategy = result.get();
            try {
                updatedPicture.removeDots(Integer.parseInt(response.get()), strategy);
                GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
                graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                updatedPicture.drawDots(canvas);
                updatedPicture.drawLines(canvas);

            } catch (IllegalArgumentException e) {
                alert2.setTitle("Error!");
                alert2.setContentText("Number you wish to retain is too small");
                alert2.showAndWait();
            }
        }


    }

    /**
     * type of list chosen by user
     * @return emptylist
     */
    public List<Dot> list() {
        List<Dot> emptyList;
        if (arrayList.isSelected()) {
            arrayList.setSelected(true);
            linkedList.setSelected(false);
            emptyList = new ArrayList<>();
        } else {
            linkedList.isSelected();
            emptyList = new LinkedList<>();
            arrayList.setSelected(false);
        }
        return emptyList;
    }

}




