package de.bb42.jinawa.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import de.bb42.jinawa.R;
import de.bb42.jinawa.controller.Controller;
import de.bb42.jinawa.controller.datatypes.Page;

/**
 * Writer Activity
 * 
 * @author Johannes Becker
 * 
 */
public class Writer extends Activity {
	private static Controller controller = Controller.getInstance();
	private int positionPaper;
	private int positionStaples;
	private String noinput = "";

	private Page page;

	/**
	 * @param savedInstanceState
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_paper);
		Intent IntentStaples = getIntent();
		Bundle b = IntentStaples.getExtras();
		if (b != null) {
			positionPaper = (Integer) b.get("positionPaper");
			positionStaples = (Integer) b.get("positionStaples");
			page = controller.getStapleOfStaples().getStaples()
					.get(positionStaples).getPages().get(positionPaper);
			final EditText editText = (EditText) findViewById(R.id.editText);
			String text = page.getContent().toString();
			editText.setText(text);
			editText.addTextChangedListener(new ContentChangedListener(editText));
		}
	}

	@Override
	public void onBackPressed() {
		if (page.getContent().toString().trim().equals(noinput)) {
			Controller.getInstance().getStapleOfStaples().getStaples()
					.get(positionStaples).deletePaper(positionPaper);
			ViewDataHolder.getInstance().getSlideScreenPapers().update();

		} else {
			page.getContent().toString().trim();
			page.save();
			ViewDataHolder.getInstance().getSlideScreenPapers().update();
		}

		super.onBackPressed();
	}

	private class ContentChangedListener implements TextWatcher {
		final EditText editText;

		ContentChangedListener(EditText editText) {
			this.editText = editText;
		}

		@Override
		public void afterTextChanged(Editable s) {

			page.save(new StringBuffer(editText.getText()));
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// Does Nothing

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// Does Nothing

		}

	}
}
