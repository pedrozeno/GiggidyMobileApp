package ncirl.project.giggidymobileapp.parse;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectattempt.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * @author paddybyrne
 * This activity allows the user to sign up or sign in. Input validation is performed.
 *
 */
public class SignUpActivity extends Activity {

	Button signUpButton;
	EditText usernameText;
	EditText passwordText;
	EditText emailText;

	ProgressDialog pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		pDialog = new ProgressDialog(SignUpActivity.this);

		signUpButton = (Button) findViewById(R.id.sign_in_button);
		usernameText = (EditText) findViewById(R.id.etUserName);
		passwordText = (EditText) findViewById(R.id.etPass);
		emailText = (EditText) findViewById(R.id.etEmail);

		signUpButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				signMeUp();

			}

		});

	}

	private void signMeUp() {

		String username = usernameText.getText().toString().trim();
		String password = passwordText.getText().toString().trim();
		String email = emailText.getText().toString().trim();

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
		if (email.length() == 0) {
			if (validationError) {
				validationErrorMessage.append(getString(R.string.error_join));
			}
			validationError = true;
			validationErrorMessage
					.append(getString(R.string.error_blank_email));
		}

		validationErrorMessage.append(getString(R.string.error_end));

		// If there is a validation error, display the error
		if (validationError) {
			Toast.makeText(SignUpActivity.this,
					validationErrorMessage.toString(), Toast.LENGTH_LONG)
					.show();
			return;
		}

		pDialog.setMessage("Thanks " + username + ". Welcome to Giggidy!");
		pDialog.show();

		// Create Parse Object and add data
		ParseUser users = new ParseUser();
		users.setUsername(username);
		users.setPassword(password);
		users.setEmail(email);

		users.signUpInBackground(new SignUpCallback() {

			@Override
			public void done(ParseException e) {
				pDialog.dismiss();
				if (e != null) {
					// Show the error message
					Toast.makeText(SignUpActivity.this, e.getMessage(),
							Toast.LENGTH_LONG).show();
				} else {
					Intent i = new Intent(SignUpActivity.this,
							DashBoardActivity.class);
					startActivity(i);

				}
			}

		});

	}

}
