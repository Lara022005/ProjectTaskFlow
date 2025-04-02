package Model;

public class ServicoVenda {
	
	private String id;
	private String idServico;
	private String idVenda;
	private String quantidade;
	
	public ServicoVenda() {
		super();
	}
	public ServicoVenda(String id, String idServico, String idVenda, String quantidade) {
		super();
		this.id = id;
		this.idServico = idServico;
		this.idVenda = idVenda;
		this.quantidade = quantidade;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdServico() {
		return idServico;
	}
	public void setIdServico(String idServico) {
		this.idServico = idServico;
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
