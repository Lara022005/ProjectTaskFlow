package Model;

public class Servico {
	
	private String id;
	private String nome;
	private String precoUni;
	private String descricao;
	private String estoque;
	private String totalServico;
	
		
	public Servico() {
		super();
	}
	public Servico(String id, String nome, String precoUni, String descricao, String estoque, String totalServico) {
		super();
		this.id = id;
		this.nome = nome;
		this.precoUni = precoUni;
		this.descricao = descricao;
		this.estoque = estoque;
		this.totalServico = totalServico;
	}
	
	public String getEstoque() {
		return estoque;
	}
	public void setEstoque(String estoque) {
		this.estoque = estoque;
	}
	public String getTotalServico() {
		return totalServico;
	}
	public void setTotalServico(String totalServico) {
		this.totalServico = totalServico;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPrecoUni() {
		return precoUni;
	}
	public void setPrecoUni(String preco) {
		this.precoUni = preco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	

}
