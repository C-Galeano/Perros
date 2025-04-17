import java.util.*;

public class Persona {
    private String nombre;
    private String apellido;
    private int edad;
    private String documento;
    private List<Perro> perrosAdoptados;

    public static final int MAX_PERROS_ADOPTADOS = 3;


    public Persona(String nombre, String apellido, int edad, String documento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.documento = documento;
        this.perrosAdoptados = new ArrayList<>();
    }


    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public String getDocumento() {
        return documento;
    }

    public List<Perro> getPerrosAdoptados() {

        return new ArrayList<>(perrosAdoptados);
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }


    public boolean adoptarPerro(Perro perro) {
        if (perrosAdoptados.size() < MAX_PERROS_ADOPTADOS) {
            perrosAdoptados.add(perro);
            return true;
        } else {
            System.out.println("Error: " + this.nombre + " ya ha alcanzado el límite de " + MAX_PERROS_ADOPTADOS + " perros adoptados.");
            return false;
        }
    }


    public Perro perroMasGrande() {
        if (perrosAdoptados.isEmpty()) {
            return null;
        }

        Perro perroMayor = perrosAdoptados.get(0);
        for (int i = 1; i < perrosAdoptados.size(); i++) {
            if (perrosAdoptados.get(i).getEdad() > perroMayor.getEdad()) {
                perroMayor = perrosAdoptados.get(i);
            }
        }
        return perroMayor;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Persona{")
                .append("nombre='").append(nombre).append('\'')
                .append(", apellido='").append(apellido).append('\'')
                .append(", edad=").append(edad)
                .append(", documento='").append(documento).append('\'')
                .append(", Perros Adoptados=");

        if (perrosAdoptados.isEmpty()) {
            sb.append("[Ninguno]");
        } else {
            sb.append("[\n");
            for (Perro perro : perrosAdoptados) {
                sb.append("\t").append(perro.toString()).append("\n");
            }
            sb.append("    ]");
        }
        sb.append('}');
        return sb.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(documento, persona.documento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documento);
    }
}