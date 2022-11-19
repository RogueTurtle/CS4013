package com.cs4013;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class GUI extends Application implements EventHandler<ActionEvent> {

    private Button menuButton;
    private Button tableButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Yum"); //Window title

        Label label1 = new Label("Yum Yum best restaurant");

        menuButton = new Button("Menu");
        tableButton = new Button("Tables");

        menuButton.setOnAction(this);
        tableButton.setOnAction(this); //Makes it use handle method from this class

        VBox layout = new VBox();
        layout.getChildren().addAll(label1, menuButton, tableButton);

        Scene scene1 = new Scene(layout, 200, 200);
        primaryStage.setScene(scene1);
        primaryStage.show();

    }

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource().equals(menuButton)) {
            System.out.println("Menu opened");
        }
        if(event.getSource().equals(tableButton)) {
            System.out.println("XD");
        }
    }

    public void frontPage() {

    }

    public void menu() {

    }

    public void tables() {

    }

    public void login() {

    }
}
