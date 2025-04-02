package Model;

public class ServicoAgendamento {
	
	private String id;
	private String idServico;
	private String idAgendamento;
	private String quantidade;
	
	public ServicoAgendamento() {
		super();
	}
	public ServicoAgendamento(String id, String idServico, String idAgendamento, String quantidade) {
		super();
		this.id = id;
		this.idServico = idServico;
		this.idAgendamento = idAgendamento;
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
	public String getIdAgendamento() {
		return idAgendamento;
	}
	public void setIdAgendamento(String idAgendamento) {
		this.idAgendamento = idAgendamento;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	
}
