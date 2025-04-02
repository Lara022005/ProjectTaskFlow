package Model;

public class ProdutoVenda {
	
	private String id;
	private String idProduto;
	private String idVenda;
	private String quantidade;
	
	public ProdutoVenda() {
		super();
	}
	public ProdutoVenda(String id, String idProduto, String idVenda, String quantidade) {
		super();
		this.id = id;
		this.idProduto = idProduto;
		this.idVenda = idVenda;
		this.quantidade = quantidade;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}
	public String getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(String idVenda) {
		this.idVenda = idVenda;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

}
