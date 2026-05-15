package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class demanda {

    public static final String STATUS_EM_ANDAMENTO = "Em andamento";
    public static final String STATUS_FINALIZADO = "Finalizado";
    public static final String STATUS_REAGENDADO = "Reagendado";
    public static final String STATUS_EXCLUIDO = "Excluido";

    // Atributos
    private String nome;
    private String descricao;
    private String local;
    private String objetivo;
    private String status;
    private Date prazoFinal;
    private Date prazoTemp;
    private String usuarioResponsavel;
    private Date ultimaEdicao;
    private Date dataCriacao;
    private String prioridade;

    // Construtor
    public demanda(
            String nome,
            String descricao,
            String local,
            String objetivo,
            String status,
            Date prazoFinal,
            Date prazoTemp,
            String usuarioResponsavel,
            Date ultimaEdicao,
            Date dataCriacao,
            String prioridade) {
        this.nome = nome;
        this.descricao = descricao;
        this.local = local;
        this.objetivo = objetivo;
        this.status = validarStatus(status);
        this.prazoFinal = prazoFinal;
        this.prazoTemp = prazoTemp;
        this.usuarioResponsavel = usuarioResponsavel;
        this.ultimaEdicao = ultimaEdicao;
        this.dataCriacao = dataCriacao;
        this.prioridade = prioridade;
    }

    public void CriarDemanda() {
        this.dataCriacao = new Date();
        this.ultimaEdicao = new Date();
        this.status = STATUS_EM_ANDAMENTO;
    }

    public void EditarDemanda() {
        this.ultimaEdicao = new Date();
    }

    public void ExcluirDemanda() {
        this.status = STATUS_EXCLUIDO;
        this.ultimaEdicao = new Date();
    }

    public void ReagendarDemanda(Date dataAgendada) {
        this.prazoTemp = dataAgendada;
        this.status = STATUS_REAGENDADO;
        this.ultimaEdicao = new Date();
    }

    public void FinalizarDemanda() {
        this.status = STATUS_FINALIZADO;
        this.ultimaEdicao = new Date();
    }

    public void DefinirPrazoFinalDemanda(Date prazoFinal) {
        this.prazoFinal = prazoFinal;
        this.ultimaEdicao = new Date();
    }

    public void DefinirStatus(String status) {
        this.status = validarStatus(status);
        this.ultimaEdicao = new Date();
    }

    public void DefinirPrioridade(String prioridade) {
        this.prioridade = prioridade;
        this.ultimaEdicao = new Date();
    }

    public List<usuario> DefinirResponsavel(usuario usuario) {
        List<usuario> usuarios = new ArrayList<>();

        if (usuario != null) {
            this.usuarioResponsavel = usuario.getNome();
            usuarios.add(usuario);
        }

        this.ultimaEdicao = new Date();
        return usuarios;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getLocal() {
        return local;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public String getStatus() {
        AtualizarStatusReagendado();
        return status;
    }

    public Date getPrazoFinal() {
        return prazoFinal;
    }

    public Date getPrazoTemp() {
        return prazoTemp;
    }

    public String getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public Date getUltimaEdicao() {
        return ultimaEdicao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public void setStatus(String status) {
        this.status = validarStatus(status);
    }

    public void setPrazoFinal(Date prazoFinal) {
        this.prazoFinal = prazoFinal;
    }

    public void setPrazoTemp(Date prazoTemp) {
        this.prazoTemp = prazoTemp;
    }

    public void setUsuarioResponsavel(String usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }

    public void setUltimaEdicao(Date ultimaEdicao) {
        this.ultimaEdicao = ultimaEdicao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public void AtualizarStatusReagendado() {
        Date hoje = new Date();

        if (STATUS_REAGENDADO.equals(status) && prazoTemp != null && !prazoTemp.after(hoje)) {
            this.status = STATUS_EM_ANDAMENTO;
            this.ultimaEdicao = hoje;
        }
    }

    private String validarStatus(String status) {
        if (status == null || status.isBlank()) {
            return STATUS_EM_ANDAMENTO;
        }

        if (STATUS_EM_ANDAMENTO.equals(status)
                || STATUS_FINALIZADO.equals(status)
                || STATUS_REAGENDADO.equals(status)
                || STATUS_EXCLUIDO.equals(status)) {
            return status;
        }

        throw new IllegalArgumentException("Status invalido: " + status);
    }
}
