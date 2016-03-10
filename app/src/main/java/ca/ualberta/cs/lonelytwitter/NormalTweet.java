package ca.ualberta.cs.lonelytwitter;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by joshua2 on 9/16/15.
 */
public class NormalTweet extends Tweet {


    public NormalTweet(String tweet, Date date) {
        super(tweet, date);
    }

    public NormalTweet(String tweet) {
        super(tweet);
    }

    public Boolean isImportant() {
        return Boolean.FALSE;
    }

    public NormalTweet(Parcel in) { super(in); }


    //http://stackoverflow.com/questions/7181526/how-can-i-make-my-custom-objects-be-parcelable codelogic 06-03-2016
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public NormalTweet createFromParcel(Parcel in) {
            return new NormalTweet(in);
        }

        public NormalTweet[] newArray(int size) {
            return new NormalTweet[0];
        }
    };


}
