package Model;

public class Produto {

	private String id;
	private String nome;
	private String codBarra;
	private String dataFab;
	private String dataVal;
	private String precoUni;
	private String estoque;
		
	public Produto() {
		super();
	}
	public Produto(String id, String nome, String codBarra, String dataFab, String dataVal, String precoUni,
			String estoque) {
		super();
		this.id = id;
		this.nome = nome;
		this.codBarra = codBarra;
		this.dataFab = dataFab;
		this.dataVal = dataVal;
		this.precoUni = precoUni;
		this.estoque = estoque;
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
	public String getCodBarra() {
		return codBarra;
	}
	public void setCodBarra(String codBarra) {
		this.codBarra = codBarra;
	}
	public String getDataFab() {
		return dataFab;
	}
	public void setDataFab(String dataFab) {
		this.dataFab = dataFab;
	}
	public String getDataVal() {
		return dataVal;
	}
	public void setDataVal(String dataVal) {
		this.dataVal = dataVal;
	}
	public String getPrecoUni() {
		return precoUni;
	}
	public void setPrecoUni(String precoUni) {
		this.precoUni = precoUni;
	}
	public String getEstoque() {
		return estoque;
	}
	public void setEstoque(String estoque) {
		this.estoque = estoque;
	}
	
}
