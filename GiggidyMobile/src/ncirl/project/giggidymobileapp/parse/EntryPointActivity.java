package ncirl.project.giggidymobileapp.parse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseUser;


/**
 * @author paddybyrne
 * Entry point for application. Checks if a current user is cached. 
 * If so, send the user directly to the dash board, otherwise send to
 * Welcome screen.
 *
 */
public class EntryPointActivity extends Activity {

	public EntryPointActivity() {

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Check if there is a current user
		if (ParseUser.getCurrentUser() != null) {

			startActivity(new Intent(this, DashBoardActivity.class));
		} else {
			// Direct to sign up\sign in page
			startActivity(new Intent(this, WelcomeActivity.class));

		}

	}
}
