package ncirl.project.giggidymobileapp.lastfm;

import static ncirl.project.giggidymobileapp.lastfm.GigListActivity.EXTRA_ARTIST_IMAGE_INFO;
import static ncirl.project.giggidymobileapp.lastfm.GigListActivity.EXTRA_ARTIST_INFO;
import static ncirl.project.giggidymobileapp.lastfm.GigListActivity.EXTRA_DATE_INFO;
import static ncirl.project.giggidymobileapp.lastfm.GigListActivity.EXTRA_VENUE_INFO;

import ncirl.project.giggidymobileapp.utils.AppController;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.projectattempt.R;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class GigDetailActivity extends Activity {

	private ShareActionProvider mShareActionProvider;

	private ImageLoader myImageLoader;

	TextView artistsName;
	TextView gigDate;
	TextView venueName;
	Button attendButton;
	NetworkImageView artistImgUrl;
	String headliner;
	String date;
	String venue;
	String image;
	String gigId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gig_detail);

		// Get data sent from GigListActivity
		Intent intent = getIntent();
		if (null != intent) {
			headliner = intent.getStringExtra(EXTRA_ARTIST_INFO);
			venue = intent.getStringExtra(EXTRA_VENUE_INFO);
			date = intent.getStringExtra(EXTRA_DATE_INFO);
			image = intent.getStringExtra(EXTRA_ARTIST_IMAGE_INFO);
			// gigId = intent.getStringExtra(EXTRA_GIG_ID_INFO);

			AppController.getInstance().getRequestQueue();
			myImageLoader = AppController.getInstance().getImageLoader();

		}

		artistsName = (TextView) findViewById(R.id.tvHeadliner);
		artistsName.setText(headliner);

		gigDate = (TextView) findViewById(R.id.tvGigDate1);
		gigDate.setText(date);

		venueName = (TextView) findViewById(R.id.tvVenueName);
		venueName.setText(venue);

		artistImgUrl = (NetworkImageView) findViewById(R.id.ivArtistImage);
		artistImgUrl.setImageUrl(image, myImageLoader);

		attendButton = (Button) findViewById(R.id.bttnAttend);

		attendButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ParseObject gigs = new ParseObject("Gigs");
				ParseObject going = new ParseObject("Going");
				gigs.put("artist", headliner);
				gigs.put("date", date);
				gigs.put("venue", venue);
				going.put("user", ParseUser.getCurrentUser());
				going.saveInBackground();
				gigs.saveInBackground();

			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gig_detail, menu);

		mShareActionProvider = (ShareActionProvider) menu.findItem(
				R.id.action_search).getActionProvider();

		mShareActionProvider.setShareIntent(getDefaultShareIntent());

		return super.onCreateOptionsMenu(menu);

	}

	/** Returns a share intent */
	private Intent getDefaultShareIntent() {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, headliner);
		intent.putExtra(Intent.EXTRA_TEXT, date);
		return intent;
	}

}
