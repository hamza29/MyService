package hamza.s4smartphones.myservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    private TextView txt, txt1;
    private Button btn2,btn;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Event event) {
        Integer str = event.getMessage();

        txt.setText(str.toString().trim());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        btn = (Button) findViewById(R.id.service);
        txt = (TextView) findViewById(R.id.prcnt);
        txt1 = (TextView) findViewById(R.id.limit);
        btn2 = (Button) findViewById(R.id.stop);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyIntentService.class);
                stopService(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lmt1 = txt1.getText().toString();
                //   int l1 = Integer.parseInt(lmt1);
                //Intent intent = new Intent(MainActivity.this,MyServiceTest.class);
                Intent intent = new Intent(MainActivity.this, MyIntentService.class);
                intent.putExtra("limit", lmt1);
                startService(intent);
            }
        });

    }
}
