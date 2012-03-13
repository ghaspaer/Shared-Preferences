package pgi.ghaspaer.EjemploSharedPreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class SharedPreferencesActivity extends Activity {
	/** Called when the activity is first created. */
	private SharedPreferences prefs;
	private String prefName = "MyPref";
	private EditText editText;
	private SeekBar seekBar;
	private Button btn;
	private static final String FONT_SIZE_KEY = "fontsize";
	private static final String TEXT_VALUE_KEY = "textvalue";

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        editText = (EditText) findViewById(R.id.EditText01);
        seekBar = (SeekBar) findViewById(R.id.SeekBar01);
        btn = (Button) findViewById(R.id.btnSave);
        btn.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        //---get the SharedPreferences object---
        prefs = getSharedPreferences(prefName, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        //---save the values in the EditText view to preferences---
        editor.putFloat(FONT_SIZE_KEY, editText.getTextSize());
        editor.putString(TEXT_VALUE_KEY, editText.getText().toString());
        //---saves the values---
        editor.commit();
        //---display file saved message---
        Toast.makeText(getBaseContext(),
        "Font size saved successfully!",
        Toast.LENGTH_SHORT).show();
        }
        });
        /////////////////////////////////////////////////////////////////////
      //---load the SharedPreferences object---
        SharedPreferences prefs = getSharedPreferences(prefName, MODE_PRIVATE);
        //---set the TextView font size to the previously saved values---
        float fontSize = prefs.getFloat(FONT_SIZE_KEY, 12);
        //---init the SeekBar and EditText---
        seekBar.setProgress((int) fontSize);
        editText.setText(prefs.getString(TEXT_VALUE_KEY,""));
        editText.setTextSize(seekBar.getProgress());
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
        //@Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
        //@Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }
        //@Override
        public void onProgressChanged(SeekBar seekBar, int progress,
        boolean fromUser) {
        //---change the font size of the EditText---
        editText.setTextSize(progress);
        }
        });
        
    }
}