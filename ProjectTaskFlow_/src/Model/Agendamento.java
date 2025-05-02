package Model;

public class Agendamento {

	private String id;
	private String idServico;
	private String idCliente;
	private String dataAgendamento;
	private String descricao;
	private String horario;
	private String statusAgendamento;


	public Agendamento() {
		super();
	}

	public Agendamento(String id, String idServico, String idCliente, String dataAgendamento, String descricao,
			String horario, String statusAgendamento) {
		super();
		this.id = id;
		this.idServico = idServico;
		this.idCliente = idCliente;
		this.dataAgendamento = dataAgendamento;
		this.descricao = descricao;
		this.horario = horario;
		this.statusAgendamento = statusAgendamento;
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
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getDataAgendamento() {
		return dataAgendamento;
	}
	public void setDataAgendamento(String dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getStatusAgendamento() {
		return statusAgendamento;
	}
	public void setStatusAgendamento(String statusAgendamento) {
		this.statusAgendamento = statusAgendamento;
	}
	

}
