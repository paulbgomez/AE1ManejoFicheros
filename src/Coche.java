import java.io.Serializable;
import java.util.HashSet;

public class Coche implements Serializable{
    private static final long serialVersionUID = 1L;
    // Nos aseguramos que ids y matriculas son unicos    
    private static HashSet<String> matriculas = new HashSet<>();
    private static HashSet<String> ids = new HashSet<>();

    private String id, matricula, modelo, marca, color;

    public Coche(String id, String matricula, String marca, String modelo, String color) {
    	super();
    	
    	// checkeamos que no haya matriculas o Ids repetidos
    	if(matriculas.contains(matricula)){
    		System.out.println("No se puede repetir matricula");
            throw new IllegalArgumentException("La matricula ya existe");
    	};
    	
    	if(ids.contains(id)){
    		System.out.println("No se puede repetir id");
            throw new IllegalArgumentException("El id ya existe");
    	};

		this.id = id;
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;

		matriculas.add(matricula);
		ids.add(id);
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getmatricula() {
        return matricula;
    }

    public void setmatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getmarca() {
        return marca;
    }

    public void setmarca(String marca) {
        this.marca = marca;
    }

    public String getmodelo() {
        return modelo;
    }

    public void setmodelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

	@Override
	public String toString() {
		return "Coche [id=" + id + ", matricula=" + matricula + ", modelo=" + modelo + ", marca=" + marca + ", color="
				+ color + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		return true;
	}
}
