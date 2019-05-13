package ikigai.example.dispositivosmoveis.ikigai;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btTip;
    Button btStart;
    private PaintView paintView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btTip = findViewById(R.id.btTip);
        btStart = findViewById(R.id.btStart);
//        paintView = (PaintView) findViewById(R.id.paintView);
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        paintView.init(metrics);

        btTip.setOnClickListener((v) -> {
            Intent it = new Intent( this, PaintMainView.class);
            startActivity(it);
        });
        btStart.setOnClickListener((v) -> {
            Intent it = new Intent(this, DoodleMainView.class);
            startActivity(it);
        });
        }

//        @Override
//        public boolean onCreateOptionsMenu(Menu menu) {
//            MenuInflater menuInflater = getMenuInflater();
//            menuInflater.inflate(R.menu.main, menu);
//            return super.onCreateOptionsMenu(menu);
//        }

//        @Override
//        public boolean onOptionsItemSelected(MenuItem item) {
//            switch(item.getItemId()) {
//                case R.id.normal:
//                    paintView.normal();
//                    return true;
//                case R.id.emboss:
//                    paintView.emboss();
//                    return true;
//                case R.id.blur:
//                    paintView.blur();
//                    return true;
//                case R.id.clear:
//                    paintView.clear();
//                    return true;
//            }
//
//            return super.onOptionsItemSelected(item);
//        }
//        btTip = (Button) findViewById(R.id.btTip);
//        btStart = (Button) findViewById(R.id.btStart);
//
//
//
//
// }
}
