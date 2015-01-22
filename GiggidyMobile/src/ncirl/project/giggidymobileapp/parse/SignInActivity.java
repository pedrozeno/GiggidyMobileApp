package ncirl.project.giggidymobileapp.parse;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectattempt.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * @author paddybyrne 
 * 
 * 		   This activity allows the user to sign into the
 *         application. 
 * 
 */
public class SignInActivity extends Activity {

	EditText eText_username;
	EditText eText_password;

	Button btn_signIn;

	ProgressDialog pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);

		pDialog = new ProgressDialog(SignInActivity.this);

		eText_username = (EditText) findViewById(R.id.etUserName);
		eText_password = (EditText) findViewById(R.id.etPass);

		btn_signIn = (Button) findViewById(R.id.sign_in_button);

		btn_signIn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				signMeIn();

			}

		});

	}

	private void signMeIn() {
		String username = eText_username.getText().toString().trim();
		String password = eText_password.getText().toString().trim();

		// Validate all sign up data
		boolean validationError = false;
		StringBuilder validationErrorMessage = new StringBuilder(
				getString(R.string.error_intro));

		if (username.length() == 0) {
			validationError = true;
			validationErrorMessage
					.append(getString(R.string.error_blank_username));
		}
		if (password.length() == 0) {
			if (validationError) {
				validationErrorMessage.append(getString(R.string.error_join));
			}
			validationError = true;
			validationErrorMessage
					.append(getString(R.string.error_blank_password));
		}

		validationErrorMessage.append(getString(R.string.error_end));

		// If there is a validation error, display the error
		if (validationError) {
			Toast.makeText(SignInActivity.this,
					validationErrorMessage.toString(), Toast.LENGTH_LONG)
					.show();
			return;
		}

		pDialog.setMessage("Logging In");
		pDialog.show();

		ParseUser.logInInBackground(username, password, new LogInCallback() {

			@Override
			public void done(ParseUser user, ParseException e) {

				pDialog.dismiss();
				if (e != null) {
			          // Show the error message
			          Toast.makeText(SignInActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
			        } else {
			        	Intent i = new Intent(SignInActivity.this, DashBoardActivity.class);
			        	startActivity(i);
			        }
			}

		});

	}
}
