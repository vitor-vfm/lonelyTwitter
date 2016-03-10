package ca.ualberta.cs.lonelytwitter;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by joshua2 on 9/16/15.
 */
public class ImportantTweet extends Tweet implements MyObservable {
    public ImportantTweet(String tweet, Date date) {
        super(tweet, date);
        this.setText(tweet);
        this.date = date;
    }

    public ImportantTweet(String tweet) {
        super(tweet);
    }

    public Boolean isImportant() {
        return Boolean.TRUE;
    }

    @Override
    public String getText() {
        return "!!!" + super.getText();
    }

    public ImportantTweet(Parcel in) { super(in); }

    //http://stackoverflow.com/questions/7181526/how-can-i-make-my-custom-objects-be-parcelable codelogic 06-03-2016
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public ImportantTweet createFromParcel(Parcel in) {
            return new ImportantTweet(in);
        }

        public ImportantTweet[] newArray(int size) {
            return new ImportantTweet[0];
        }
    };

    public void registerObserver(MyObserver o) {

    }

    public void myNotifyAll() {

    }
}
