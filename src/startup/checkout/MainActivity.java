package startup.checkout;
import com.google.zxing.integration.android.IntentIntegrator;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentResult;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
/**
 * 
 * @author Abhishek Deo, Sagar Laud, Karan Pahawa, (And anyone else)
 *
 */
public class MainActivity extends Activity implements OnClickListener{

	private Button scanButton;
	private TextView formatField, contentField;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		scanButton = (Button)findViewById(R.id.scan_button);
		formatField = (TextView)findViewById(R.id.scan_format);
		contentField = (TextView)findViewById(R.id.scan_content);
		scanButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0.getId()==R.id.scan_button) {
			IntentIntegrator integrator = new IntentIntegrator(this);
			integrator.initiateScan();
		}
		
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if(result!=null){
			formatField.setText("Format: "+result.getFormatName());
			contentField.setText("Content: "+result.getContents());
		}
		
		else {
			Toast.makeText(getApplicationContext(), "No Data Received", Toast.LENGTH_SHORT).show();
		}
	}
}
