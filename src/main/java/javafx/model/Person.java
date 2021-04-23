package javafx.model;

public class Person {

	protected String name;
	protected String NIF;
	
	public Person(String name, String nIF) {
		super();
		this.name = name;
		NIF = nIF;
	}
	
	public Person() {
		super();
		this.name = "";
		NIF = "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNIF() {
		return NIF;
	}

	public void setNIF(String nIF) {
		NIF = nIF;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((NIF == null) ? 0 : NIF.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (NIF == null) {
			if (other.NIF != null)
				return false;
		} else if (!NIF.equals(other.NIF))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", NIF=" + NIF + "]";
	}
	
}
