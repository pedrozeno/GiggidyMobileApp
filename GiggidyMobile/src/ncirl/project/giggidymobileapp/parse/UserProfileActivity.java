package ncirl.project.giggidymobileapp.parse;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.projectattempt.R;

public class UserProfileActivity extends Activity {

	private static int RESULT_LOAD_IMAGE = 1;

	ImageView profile_img;
	ImageView edit_pic;
	Button bttn_save;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);

		profile_img = (ImageView) findViewById(R.id.profile_image);
		edit_pic = (ImageView) findViewById(R.id.edit_image);
		bttn_save = (Button) findViewById(R.id.save_button);
		bttn_save.setEnabled(false);

		edit_pic.setOnClickListener(new View.OnClickListener() {

			/* When clicked the user will be able to change profile picture.
			 * The internal gallery will open and the user can choose from there.
			 */
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

				startActivityForResult(intent, RESULT_LOAD_IMAGE);

			}

		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
				&& null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();

			ImageView imageView = (ImageView) findViewById(R.id.profile_image);
			imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

		}

	}
}
