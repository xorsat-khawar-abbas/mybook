package net.xorsat.mybook;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ThanksActivity extends Activity {
    public static String MESSAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);
        TextView mTextView = (TextView) findViewById(R.id.textView);
        mTextView.setText(MESSAGE);

    }
}
