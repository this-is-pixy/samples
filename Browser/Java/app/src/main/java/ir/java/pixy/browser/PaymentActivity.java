package ir.java.pixy.browser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {

    Button openBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        openBrowser = findViewById(R.id.open_browser);
        openBrowser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == openBrowser.getId()) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW);
            browserIntent.setData(Uri.parse("https://www.zarinpal.com/"));
            startActivity(browserIntent);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(intent.getAction() != null && intent.getAction().equals(Intent.ACTION_VIEW)) {

            Uri uri = intent.getData();

            try {
                String q = uri.getQueryParameter("q");
                String s = uri.getQueryParameter("s");

                //do something with data

            }catch (NullPointerException exception) { }
        }
    }
}
