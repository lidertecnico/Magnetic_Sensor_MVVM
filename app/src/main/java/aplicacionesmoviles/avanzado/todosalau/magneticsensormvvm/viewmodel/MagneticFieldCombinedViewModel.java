package aplicacionesmoviles.avanzado.todosalau.magneticsensormvvm.viewmodel;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

import aplicacionesmoviles.avanzado.todosalau.magneticsensormvvm.model.MagneticFieldCombinedModel;

public class MagneticFieldCombinedViewModel {
    private MagneticFieldCombinedModel magneticFieldCombinedModel;

    // Constructor de la clase
    public MagneticFieldCombinedViewModel() {
        // Inicializamos el modelo combinado de campo magnético con valores predeterminados
        magneticFieldCombinedModel = new MagneticFieldCombinedModel(0, 0, 0, 0);
    }

    // Método para actualizar el campo magnético
    public void updateMagneticField(SensorEvent event) {
        // Verificamos si el evento corresponde al sensor de campo magnético
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            // Obtenemos los valores del evento para las tres coordenadas (x, y, z)
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // Calculamos la intensidad total del campo magnético
            float totalMagneticFieldStrength = (float) Math.sqrt(x * x + y * y + z * z);

            // Creamos un nuevo modelo combinado de campo magnético con los valores obtenidos
            magneticFieldCombinedModel = new MagneticFieldCombinedModel(totalMagneticFieldStrength, x, y, z);
        }
    }

    // Método para obtener el modelo combinado de campo magnético
    public MagneticFieldCombinedModel getMagneticField() {
        return magneticFieldCombinedModel;
    }
}