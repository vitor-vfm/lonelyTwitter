package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

/**
 * Created by romansky on 3/8/16.
 */
public interface MyObservable {
    public void registerObserver(MyObserver o);

    public void myNotifyAll();
}
