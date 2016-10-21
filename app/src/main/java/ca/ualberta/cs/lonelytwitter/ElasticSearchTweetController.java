package ca.ualberta.cs.lonelytwitter;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;

import ca.ualberta.cs.lonelytwitter.NormalTweet;
import ca.ualberta.cs.lonelytwitter.Tweet;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

/**
 * Created by vitor on 20/10/16.
 */
public class ElasticSearchTweetController {

    private static JestDroidClient client;

    public static class AddTweetsTask extends AsyncTask<Tweet, Void, Void> {
        
        @Override
        protected Void doInBackground(Tweet... params) {
            verifySettings();

            for (Tweet tweet : params) {
                Index index = new Index.Builder(tweet).index("testing").type("tweet").build();

                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        tweet.setId(result.getId());
                    } else {
                        Log.i("Error", "Result from elastic search did not succeed");
                    }
                } catch (Exception e) {
                    Log.i("Error", "Application failed to send tweet");
                }
            }
            return null;
        }
    }

    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig config = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080/").build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }

    // TODO: function that gets tweets from elastic search
    public static class GetTweetsTask extends AsyncTask<String, Void, ArrayList<Tweet>> {
        @Override
        protected ArrayList<Tweet> doInBackground(String... searchParams) {
            verifySettings();

            ArrayList<Tweet> tweetsToReturn = new ArrayList<Tweet>();

            // Assumption: only the first search param is used

            Search search = new Search.Builder(searchParams[0])
                    .addIndex("testing")
                    .addType("tweet")
                    .build();

            try {
                SearchResult searchResult = client.execute(search);
                if (searchResult.isSucceeded()) {
                    List<Tweet> foundTweets = searchResult.getSourceAsObjectList(Tweet.class);
                    tweetsToReturn.addAll(foundTweets);
                } else {
                    Log.i("Error", "The search in get tweets failed even though it executed");
                }
            } catch (Exception e) {
                Log.i("Error", "Executing get tweets failed");
            }

            return null;
        }
    }
}
