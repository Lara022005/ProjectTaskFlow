package Model;

public class Usuario {
	
	private String id;
	private String idFuncionario;
	private String cpf;
	private String nome;
	private String nivelUsuario;
	private String senha;
	
	public Usuario() {
		super();
	}

	public Usuario(String id, String idFuncionario, String nome, String nivelUsuario, String senha) {
		super();
		this.id = id;
		this.idFuncionario = idFuncionario;
		this.nome = nome;
		this.nivelUsuario = nivelUsuario;
		this.senha = senha;
	
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNivelUsuario() {
		return nivelUsuario;
	}
	public void setNivelUsuario(String nivelUsuario) {
		this.nivelUsuario = nivelUsuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
		
}
