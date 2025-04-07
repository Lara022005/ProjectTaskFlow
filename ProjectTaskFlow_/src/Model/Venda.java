package Model;

public class Venda {
	
	private String id;
	private String idUsuario;
	private String idCliente;
	private String dataVenda;
	private String precoTotal;
	private String formaPag;
	private String desconto;
	
	
	public Venda() {
		super();
	}
	
	public Venda(String id, String idUsuario, String idCliente, String dataVenda, String precoTotal, String formaPag,
			String desconto) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.idCliente = idCliente;
		this.dataVenda = dataVenda;
		this.precoTotal = precoTotal;
		this.formaPag = formaPag;
		this.desconto = desconto;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return idUsuario;
	}
	public void setNome(String nome) {
		this.idUsuario = nome;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}
	public String getPrecoTotal() {
		return precoTotal;
	}
	public void setPrecoTotal(String precoTotal) {
		this.precoTotal = precoTotal;
	}
	public String getFormaPag() {
		return formaPag;
	}
	public void setFormaPag(String formaPag) {
		this.formaPag = formaPag;
	}
	public String getDesconto() {
		return desconto;
	}
	public void setDesconto(String desconto) {
		this.desconto = desconto;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	
	
	
	
}
