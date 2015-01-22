package ncirl.project.giggidymobileapp.lastfm;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class GigItemPagerAdapter extends FragmentPagerAdapter {

	public GigItemPagerAdapter(FragmentManager fm) {
		super(fm);

	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			return new GigDetailFragment();

		case 1:
			return new GigAttendeeFragment();

		case 2:
			return new GigGroupFragment();

		}

		return null;
	}

	@Override
	public int getCount() {
		// Return the number of tabs
		return 3;
	}

}
