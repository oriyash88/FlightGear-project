package com.example.tirgul_2;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import view_model.ViewModel;

import java.util.Observable;
import java.util.Observer;

public class HelloController  {

    @FXML
    Canvas joystick;
    @FXML
    Slider throttle;
    @FXML
    Slider rudder;
    ViewModel vm;
    DoubleProperty aileron,elevator;

    boolean mousePushed;
    double jx,jy;
    double mx;
    double my;

    public HelloController()
    {
        mousePushed = false;
        this.jx =0;
        this.jy =0;
        aileron = new SimpleDoubleProperty();
        elevator = new SimpleDoubleProperty();
    }

    @FXML
     void paint(){
        GraphicsContext gc = joystick.getGraphicsContext2D();
        mx = joystick.getWidth()/2;
        my = joystick.getHeight()/2;
        gc.clearRect(0,0, joystick.getWidth(), joystick.getHeight());
        gc.strokeOval(jx-50, jy-50, 100,100);
        aileron.set((jx-mx)/mx);
        elevator.set((jy-my)/my);
    }

    void init(ViewModel vm){
        this.vm = vm;
        vm.throttle.bind(throttle.valueProperty());
        vm.rudder.bind(rudder.valueProperty());
        vm.aileron.bind(aileron);
        vm.elevator.bind(elevator);
    }

    @FXML
    public void mouseDown(MouseEvent me){
         if(!mousePushed){
             mousePushed = true;
         }
    }
    @FXML
    public void mouseUp(MouseEvent me){
        if(mousePushed){
            mousePushed = false;
            jx=mx;
            jy=my;
            paint();
        }
    }

    @FXML
    public void mouseMove(MouseEvent me){
        if(mousePushed){
            jx = me.getX();
            jy = me.getY();
            paint();
        }
    }




}