package net.xorsat.mybook;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.xorsat.model.Branch;

public class BranchDetailActivity extends Activity {
	public static Branch BRANCH;
	TextView tvBranchName;
	TextView tvBranchAddress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_branch_detail);
		tvBranchName = (TextView) findViewById(R.id.branchDetail_textView_name);
		tvBranchAddress = (TextView) findViewById(R.id.branchDetail_textView_address);

		tvBranchName.setText(BRANCH.getBranch_name());
		tvBranchAddress.setText(BRANCH.getBranch_address());
	}

	public void onClick_call(View view) {
		dialPhoneNumber(BRANCH.getBranch_phone());
	}

	public void dialPhoneNumber(String phoneNumber) {
		Intent intent = new Intent(Intent.ACTION_DIAL);
		intent.setData(Uri.parse("tel:" + phoneNumber));
		if (intent.resolveActivity(getPackageManager()) != null) {
			startActivity(intent);
		}
	}
}
