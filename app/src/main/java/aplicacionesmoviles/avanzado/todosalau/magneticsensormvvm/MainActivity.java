package aplicacionesmoviles.avanzado.todosalau.magneticsensormvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import aplicacionesmoviles.avanzado.todosalau.magneticsensormvvm.model.MagneticFieldCombinedModel;
import aplicacionesmoviles.avanzado.todosalau.magneticsensormvvm.viewmodel.MagneticFieldCombinedViewModel;

public class MainActivity extends AppCompatActivity  implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor magnetometer;
    private TextView magneticFieldTotalTextView;
    private TextView magneticFieldTextViewX;
    private TextView magneticFieldTextViewY;
    private TextView magneticFieldTextViewZ;
    private Button startButton;
    private boolean isListening = false;
    private MagneticFieldCombinedViewModel magneticFieldCombinedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a las vistas en el diseño de la actividad
        magneticFieldTotalTextView = findViewById(R.id.magneticFieldTotalTextView);
        magneticFieldTextViewX = findViewById(R.id.magneticFieldTextViewX);
        magneticFieldTextViewY = findViewById(R.id.magneticFieldTextViewY);
        magneticFieldTextViewZ = findViewById(R.id.magneticFieldTextViewZ);
        startButton = findViewById(R.id.startButton);

        // Inicialización del SensorManager y obtención del magnetómetro
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        }

        // Inicialización del ViewModel para manejar los datos del campo magnético
        magneticFieldCombinedViewModel = new MagneticFieldCombinedViewModel();

        // Configuración del botón de inicio para comenzar o detener la lectura del sensor
        startButton.setOnClickListener(v -> {
            if (!isListening) {
                // Comienza a escuchar los datos del sensor cuando se presiona el botón
                sensorManager.registerListener(MainActivity.this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
                startButton.setText("Stop");
                isListening = true;
            } else {
                // Detiene la escucha del sensor cuando se presiona el botón nuevamente
                sensorManager.unregisterListener(MainActivity.this);
                startButton.setText("Start");
                isListening = false;
                onPause(); // Pausa la actividad
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Método llamado cuando hay un cambio en los datos del sensor
        // Actualiza los datos del campo magnético y actualiza las vistas correspondientes
        magneticFieldCombinedViewModel.updateMagneticField(event);
        MagneticFieldCombinedModel magneticFieldCombinedModel = magneticFieldCombinedViewModel.getMagneticField();
        magneticFieldTotalTextView.setText("Total Magnetic Field: " + magneticFieldCombinedModel.getTotalMagneticFieldStrength() + " μT");
        magneticFieldTextViewX.setText("Magnetic Field X: " + magneticFieldCombinedModel.getX() + " μT");
        magneticFieldTextViewY.setText("Magnetic Field Y: " + magneticFieldCombinedModel.getY() + " μT");
        magneticFieldTextViewZ.setText("Magnetic Field Z: " + magneticFieldCombinedModel.getZ() + " μT");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Método llamado cuando cambia la precisión del sensor
        // No se implementa nada aquí para este ejemplo
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Método llamado cuando la actividad está en pausa
        // Detiene la escucha del sensor para conservar la energía
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Método llamado cuando la actividad se reanuda
        // Reanuda la escucha del sensor si estaba activo antes de pausar la actividad
        if (isListening) {
            sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
}