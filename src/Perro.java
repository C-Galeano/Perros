import java.util.* ;

public class Perro {
    private String placa;
    private String nombre;
    private String raza;
    private int edad;
    private String tamaño;


    public Perro(String placa, String nombre, String raza, int edad, String tamaño) {
        this.placa = placa;
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.tamaño = tamaño;
    }


    public String getPlaca() {
        return placa;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRaza() {
        return raza;
    }

    public int getEdad() {
        return edad;
    }

    public String getTamaño() {
        return tamaño;
    }


    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }


    @Override
    public String toString() {
        return "Perro{" +
                "placa='" + placa + '\'' +
                ", nombre='" + nombre + '\'' +
                ", raza='" + raza + '\'' +
                ", edad=" + edad +
                ", tamaño='" + tamaño + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perro perro = (Perro) o;
        return Objects.equals(placa, perro.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placa);
    }
}
