package com.conquestrpg.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hud implements ActionListener {

    public Stage stage;
    private Viewport veiwport;

    private JButton saveButton;

//    public Hud(SpriteBatch sb, Camera cam){
//        saveButton.addActionListener(this);
//
//
//        //need to have x & y coordinates of camera for parameters below
//        veiwport = new FitViewport();
//
//        stage = new Stage(veiwport, sb);
//
//        stage.addActor(saveButton);
//
//        Table table = new Table();
//        table.top();
//        table.setFillParent(true);
//
//        table.add(saveButton);
//
//
//
//
//    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == saveButton){
            // prompt user to saveGame();
        }
    }
}
