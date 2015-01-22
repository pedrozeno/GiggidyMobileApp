package ncirl.project.giggidymobileapp.parse;

import ncirl.project.giggidymobileapp.lastfm.GigListActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.projectattempt.R;
import com.parse.ParseUser;

public class DashBoardActivity extends Activity implements OnClickListener {

	Button btn_gigsFeed;
	Button btn_planner;
	Button btn_profile;
	Button btn_maps;
	Button btn_logOut;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dash_board);

		btn_gigsFeed = (Button) findViewById(R.id.db_gigs_button);
		btn_planner = (Button) findViewById(R.id.db_planner_button);
		btn_profile = (Button) findViewById(R.id.db_profile_button);
		btn_maps = (Button) findViewById(R.id.db_maps_button);
		btn_logOut = (Button) findViewById(R.id.db_logout_button);

		btn_gigsFeed.setOnClickListener(this);
		btn_planner.setOnClickListener(this);
		btn_profile.setOnClickListener(this);
		btn_maps.setOnClickListener(this);
		btn_logOut.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		Intent i = null;
		switch (v.getId()) {

		case R.id.db_gigs_button:
			i = new Intent(this, GigListActivity.class);
			break;
		case R.id.db_profile_button:
			i = new Intent(this, UserProfileActivity.class);
			break;
		case R.id.db_planner_button:
			i = new Intent(this, PlannerActivity.class);
			break;
		case R.id.db_maps_button:
			i = new Intent(this, MapsActivity.class);
			break;
		case R.id.db_logout_button:
			i = new Intent(this, WelcomeActivity.class);
			ParseUser.logOut();
			finish();

		}

		startActivity(i);

	}
}
