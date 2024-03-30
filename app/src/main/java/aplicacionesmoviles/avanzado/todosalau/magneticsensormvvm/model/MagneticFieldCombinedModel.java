package aplicacionesmoviles.avanzado.todosalau.magneticsensormvvm.model;

public class MagneticFieldCombinedModel {
    // Atributos para almacenar los valores del campo magnético
    private float totalMagneticFieldStrength; // Intensidad total del campo magnético
    private float x; // Componente x del campo magnético
    private float y; // Componente y del campo magnético
    private float z; // Componente z del campo magnético

    // Constructor de la clase
    public MagneticFieldCombinedModel(float totalMagneticFieldStrength, float x, float y, float z) {
        // Inicializamos los atributos con los valores proporcionados
        this.totalMagneticFieldStrength = totalMagneticFieldStrength;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Método para obtener la intensidad total del campo magnético
    public float getTotalMagneticFieldStrength() {
        return totalMagneticFieldStrength;
    }

    // Método para obtener la componente x del campo magnético
    public float getX() {
        return x;
    }

    // Método para obtener la componente y del campo magnético
    public float getY() {
        return y;
    }

    // Método para obtener la componente z del campo magnético
    public float getZ() {
        return z;
    }}
