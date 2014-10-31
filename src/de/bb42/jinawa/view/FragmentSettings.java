package de.bb42.jinawa.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import de.bb42.jinawa.R;

public class FragmentSettings extends Fragment implements View.OnClickListener {
	private ViewGroup view;
	private int position;
	private Intent intentPaper = new Intent(SlideScreenStaples.getContext(),
			Settings.class);

	public FragmentSettings(int position) {
		this.position = position;

	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = (ViewGroup) inflater.inflate(
				R.layout.fragment_screen_slide_page, container, false);
		view.setId(position);
		Button mButton = (Button) view.findViewById(R.id.button1);
		mButton.setId(position);
		mButton.setText("Settings");
		mButton.setBackgroundResource(R.drawable.staple2);
		mButton.setOnClickListener(this);

		return view;
	}

	/**
	 * Starts new Activity
	 * 
	 * @param v
	 *            ?
	 */
	@Override
	public void onClick(View v) {
		startActivity(intentPaper);

	}

}