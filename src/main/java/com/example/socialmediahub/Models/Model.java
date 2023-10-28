package com.example.socialmediahub.Models;

import com.example.socialmediahub.Views.ViewFactory;

/*
    This class was developed using code from https://www.youtube.com/watch?v=lkov5shwRQs
 */
public class Model {
    private final ViewFactory viewFactory;
    private static Model model;

    private Model(){
        this.viewFactory = new ViewFactory();
    }

    public static synchronized Model getInstance(){
        if(model == null){
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory(){
        return viewFactory;
    }
}
