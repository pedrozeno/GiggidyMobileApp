package ncirl.project.giggidymobileapp.lastfm;

import com.android.volley.toolbox.NetworkImageView;
import com.example.projectattempt.R;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import static ncirl.project.giggidymobileapp.lastfm.GigListActivity.EXTRA_ARTIST_IMAGE_INFO;
import static ncirl.project.giggidymobileapp.lastfm.GigListActivity.EXTRA_ARTIST_INFO;
import static ncirl.project.giggidymobileapp.lastfm.GigListActivity.EXTRA_DATE_INFO;
import static ncirl.project.giggidymobileapp.lastfm.GigListActivity.EXTRA_GIG_ID;
import static ncirl.project.giggidymobileapp.lastfm.GigListActivity.EXTRA_VENUE_INFO;

public class GigItemActivity extends FragmentActivity implements
		ActionBar.TabListener {

	private ViewPager viewPager;
	private GigItemPagerAdapter myAdapter;
	private ActionBar actionBar;
	// Tab titles
	private String[] myTabs = { "Artist", "Attending", "Groupies" };

	NetworkImageView artistImgUrl;
	String headliner;
	String date;
	String venue;
	String image;
	String gigId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gig_item);

		viewPager = (ViewPager) findViewById(R.id.my_pager);
		myAdapter = new GigItemPagerAdapter(getSupportFragmentManager());
		actionBar = getActionBar();

		viewPager.setAdapter(myAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		for (String tab_title : myTabs) {

			actionBar.addTab(actionBar.newTab().setText(tab_title)
					.setTabListener(this));

		}

		// Creates swipable views
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

		/*
		 * Info passed from GigList View, to be passed to Gig Detail Fragment
		 */
		// Get data sent from GigListActivity
		Intent intent = getIntent();
		if (null != intent) {
			headliner = intent.getStringExtra(EXTRA_ARTIST_INFO);
			venue = intent.getStringExtra(EXTRA_VENUE_INFO);
			date = intent.getStringExtra(EXTRA_DATE_INFO);
			image = intent.getStringExtra(EXTRA_ARTIST_IMAGE_INFO);
			gigId = intent.getStringExtra(EXTRA_GIG_ID);

		}
		Bundle args = new Bundle();
		args.putString(EXTRA_ARTIST_INFO, getIntent().getExtras().getString(headliner));
		args.putString(EXTRA_VENUE_INFO, getIntent().getExtras().getString(venue));
		args.putString(EXTRA_DATE_INFO, getIntent().getExtras().getString(date));
		args.putString(EXTRA_ARTIST_IMAGE_INFO, getIntent().getExtras().getString(image));
		args.putString(EXTRA_GIG_ID, getIntent().getExtras().getString(gigId));
		GigDetailFragment gigFrag = new GigDetailFragment();
		gigFrag.setArguments(args);
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

}
