package com.example.a1;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class SortingHubController {

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Slider slider;

    @FXML
    private Label value;

    @FXML
    private Button btn;

    @FXML
    private Button btnreset;

    @FXML
    private AnchorPane anchorPane;

    private int[] intArray;

    private SortingStrategy sortingMethod;

    public int[] createRandomArrayList(int value) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= value; i++) {
            list.add(i);
        }
        Collections.shuffle(list, new Random());
        intArray = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            intArray[i] = list.get(i);
        }
        return intArray;
    }

    public void updateGraph() {
        anchorPane.getChildren().clear();
        int gapWidth = 1;
        value.setText(String.format("%.0f", slider.getValue()));
        double width = (anchorPane.getPrefWidth() - intArray.length * gapWidth) / intArray.length;
        double height = anchorPane.getPrefHeight();
        int max = Arrays.stream(intArray).max().getAsInt();
        for (int i = 0; i < intArray.length; i++) {
            Rectangle rect = new Rectangle(width, (intArray[i] / (double) max * height) - 1);
            rect.setX(i * (width + gapWidth) + gapWidth);
            rect.setY(height - rect.getHeight());
            rect.setFill(Color.RED);
            anchorPane.getChildren().add(rect);
        }
    }

    @FXML
    public void initialize() {
        slider.setValue(64);
        comboBox.setItems(FXCollections.observableArrayList("Merge Sort", "Selection Sort"));
        comboBox.setValue("Merge Sort");
        value.setText(String.format("%.0f", slider.getValue()));
        intArray = createRandomArrayList((int) slider.getValue());
        updateGraph();
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            intArray = createRandomArrayList((int) slider.getValue());
            updateGraph();
        });

    }

    @FXML
    public void setSortStrategy() {
        intArray=createRandomArrayList((int)slider.getValue());
        if (comboBox.getValue().equals("Merge Sort")) {
            sortingMethod = new MergeSort(intArray,this);
        } else if (comboBox.getValue().equals("Selection Sort")) {
            sortingMethod = new SelectionSort(intArray,this);
        }
        Thread thread = new Thread(sortingMethod);
        thread.start();
    }

    @FXML
    public void reset(){
        slider.setValue(64);
        comboBox.setValue("Merge Sort");
        intArray = createRandomArrayList(64);
        updateGraph();
    }
}