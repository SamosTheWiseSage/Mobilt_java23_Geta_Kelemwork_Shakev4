package se.gritacademy.geta.kelemwork.shakev4;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    //static int clicker = 0;
    private SensorManager sensorManager;
    private Sensor sensor;
    float x;
    float y;
    float z;
    TextView tv;
    TextView tv2;
    TextView tv3;
    ImageView imageView;
    ImageButton imageButton;
    View iv;
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this,sensor);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStart() {
        super.onStart();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this,sensor);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this,sensor);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
         x = sensorEvent.values[0];
         y = sensorEvent.values[1];
         z = sensorEvent.values[2];

        imageView = (ImageView) findViewById(R.id.rotationarrow);

        if (sensorEvent.sensor.getType()==Sensor.TYPE_GYROSCOPE) {
            if (x >0) {
                Log.d("alrik", x + "Xrotation");
                tv.setText("Gyroscope-X axis: "+x);
               // imageView.getRotation()
                imageView.getRotation();
                imageView.setRotationX(x*100);

            }
            if (y >0) {
                Log.d("alrik", y + "Yrotation");
                tv2.setText("Gyroscope-Y axis: "+y);
                imageView.getRotation();
                imageView.setRotationY(y*100);
            }  if (z >0) {
                Log.d("alrik", z + "Zrotation");
                tv3.setText("Gyroscope-Z axis: "+z);
                imageView.getRotation();
                imageView.setRotation(z*100);
            }
        }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        imageButton = (android.widget.ImageButton) findViewById(R.id.imageButton3);
        tv = findViewById(R.id.SensorView);
        tv.setText("Gyroscope-X axis: ");
        tv.setBackgroundColor(Color.WHITE);
        tv2 = findViewById(R.id.SensorView2);
        tv2.setText("Gyroscope-Y axis: ");
        tv2.setBackgroundColor(Color.WHITE);
        tv3 = findViewById(R.id.SensorView3);
        tv3.setText("Gyroscope-Z axis: ");
        tv3.setBackgroundColor(Color.WHITE);
        iv = findViewById(R.id.main);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton.animate().rotation(imageButton.getRotation()-360).start();
            }
        });
        Button b = findViewById(R.id.button);
        b.setText("Green");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv.setBackgroundColor(Color.rgb(0,150,0));
            }
        });
        Button b2 = findViewById(R.id.button2);
        b2.setText("Red");
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv.setBackgroundColor(Color.rgb(150,0,0));
            }
        });
        Button b3 = findViewById(R.id.button3);
        b3.setText("Reset");
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv.setBackgroundColor(Color.WHITE);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


}