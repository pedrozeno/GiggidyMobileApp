package ncirl.project.giggidymobileapp.parse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.projectattempt.R;

public class WelcomeActivity extends Activity implements OnClickListener {

	Button btnSignIn;
	Button btnSignUp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		btnSignIn = (Button) findViewById(R.id.sign_in_button);
		btnSignUp = (Button) findViewById(R.id.sign_up_button);

		btnSignIn.setOnClickListener(this);
		btnSignUp.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent i = null;
		switch(v.getId()){
			case R.id.sign_in_button:
				i = new Intent(this,SignInActivity.class);
				break;
			case R.id.sign_up_button:
				i = new Intent(this,SignUpActivity.class);
				break;
		}
		startActivity(i);

	}
}
