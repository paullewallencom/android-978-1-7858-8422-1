package com.packtpub.masteringandroidapp.samples;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antonio on 24/05/2015.
 */
public class MyObserved {
    public interface ObserverInterface{
        public void notifyListener();
    }

    List<ObserverInterface> observersList;

    public MyObserved(){
        observersList = new ArrayList<>();
    }

    public void addObserver(ObserverInterface observer){
        observersList.add(observer);
    }

    public void removeObserver(ObserverInterface observer){
        observersList.remove(observer);
    }

    public void notifyAllObservers(){
        for (ObserverInterface observer : observersList){
            observer.notify();
        }
    }
}