package org.supernani.domain.exceptions;

public class ErroResponse {

    private String erro;
    private String mensagem;

    public ErroResponse() {
    }

    public ErroResponse(String erro, String mensagem) {
        this.erro = erro;
        this.mensagem = mensagem;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}