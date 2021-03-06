package ncirl.project.giggidymobileapp.lastfm;

import static ncirl.project.giggidymobileapp.lastfm.GigListActivity.EXTRA_ARTIST_IMAGE_INFO;
import static ncirl.project.giggidymobileapp.lastfm.GigListActivity.EXTRA_ARTIST_INFO;
import static ncirl.project.giggidymobileapp.lastfm.GigListActivity.EXTRA_DATE_INFO;
import static ncirl.project.giggidymobileapp.lastfm.GigListActivity.EXTRA_GIG_ID;
import static ncirl.project.giggidymobileapp.lastfm.GigListActivity.EXTRA_VENUE_INFO;
import ncirl.project.giggidymobileapp.utils.AppController;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.projectattempt.R;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class GigDetailFragment extends Fragment {

	Context context;

	private ImageLoader myImageLoader;

	View rootView;

	TextView headlinerTv;
	TextView gigDateTv;
	TextView venueNameTv;
	Button attendButton;
	NetworkImageView artistImgUrl;

	String headliner;
	String date;
	String venue;
	String image;
	String gigId;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_gig_detail, container,
				false);

		context = container.getContext();

		// Initialise ImageLoader
		myImageLoader = AppController.getInstance().getImageLoader();

		headliner = getActivity().getIntent().getStringExtra(EXTRA_ARTIST_INFO);
		date = getActivity().getIntent().getStringExtra(EXTRA_DATE_INFO);
		venue = getActivity().getIntent().getStringExtra(EXTRA_VENUE_INFO);
		image = getActivity().getIntent().getStringExtra(
				EXTRA_ARTIST_IMAGE_INFO);
		gigId = getActivity().getIntent().getStringExtra(EXTRA_GIG_ID);

		headlinerTv = (TextView) rootView.findViewById(R.id.tvHeadliner);
		headlinerTv.setText(headliner);

		gigDateTv = (TextView) rootView.findViewById(R.id.tvGigDate1);
		gigDateTv.setText(date);

		venueNameTv = (TextView) rootView.findViewById(R.id.tvVenueName);
		venueNameTv.setText(venue);

		artistImgUrl = (NetworkImageView) rootView
				.findViewById(R.id.ivArtistImage);
		artistImgUrl.setImageUrl(image, myImageLoader);

		attendButton = (Button) rootView.findViewById(R.id.bttnAttend);
		attendButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/*
				 * This will check against a cloud function that checks if a
				 * Gig_ID already exists and ignores the command if it does
				 * exist. It will add the value if it doesn't.
				 */

				startDialog();

			}

		});

		return rootView;
	}

	private void checkIfAlreadyAttending() {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("GoingTo");
		query.whereEqualTo("Gig_ID", gigId);
		query.whereEqualTo("User_ID", ParseUser.getCurrentUser());
		query.getFirstInBackground(new GetCallback<ParseObject>() {

			@Override
			public void done(ParseObject object, ParseException e) {
				if (e != null) {
					attendButton.setBackgroundColor(R.drawable.going_buttn_bg);
					attendButton.setText("Going");
					attendButton.setEnabled(false);
				} else {
					attendButton = (Button) rootView
							.findViewById(R.id.bttnAttend);
					attendButton.setEnabled(true);
				}

			}

		});

	}

	private void addGigToParse() {
		ParseObject gig = new ParseObject("Gigs");
		gig.put("headliner", headliner);
		gig.put("date", date);
		gig.put("venue", venue);
		gig.put("Gig_ID", gigId);
		gig.saveInBackground();

	}

	private void addUserToAttendeesTable() {
		// create an entry in the attendee table
		ParseObject attending = new ParseObject("Attending");
		attending.put("User_ID", ParseUser.getCurrentUser());
		attending.put("Gig_ID", gigId);
		attending.saveInBackground();
		addUserToAttendeesTable();

	}

	private void startDialog() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		// set title
		alertDialogBuilder.setTitle("Please Confirm");

		// set dialog message
		alertDialogBuilder
				.setMessage("Are you sure you want to see " + headliner)
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

								addGigToParse();
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// display dialog
		alertDialog.show();
	}

}
