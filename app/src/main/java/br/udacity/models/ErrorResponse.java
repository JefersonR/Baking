package br.udacity.models;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import okhttp3.ResponseBody;

public class ErrorResponse {
    public static final String UPDATE_VERSION = "111";
    public static final String NETWORK_DISABLE = "222";
    public static final String UNEXPECTED = "333";
    public static final String SESSION = "444";
    public static final String FAIL = "555";

    @Expose
    private String code;
    @Expose
    private int codeServer = 0;
    @Expose
    private String message;
    @Expose
    private String messageServer = "";




    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessageServer() {
        return messageServer;
    }

    public void setMessageServer(String messageServer) {
        this.messageServer = messageServer;
    }


    public int getCodeServer() {
        return codeServer;
    }

    public void setCodeServer(int codeServer) {
        this.codeServer = codeServer;
    }

    public static  ErrorResponse NETWORK_ERROR() {
        final ErrorResponse error = new ErrorResponse();
        error.code = NETWORK_DISABLE;
        error.message = "Verifique sua conexão e tente novamente.";
        return error;
    }

    public static  ErrorResponse getResponseError(final ResponseBody responseBody, int serverCode) {
        ErrorResponse error;
        try {
            error = new Gson().fromJson(responseBody.string(), ErrorResponse.class);
        } catch (Exception e) {
            error = getGsonParseError();
        }
        if (error == null) {
            error = new ErrorResponse();
            error.setCode(ErrorResponse.UNEXPECTED);
            error.setMessageServer("Ocorreu um erro inesperado. Por favor, tente novamente.");
            error.setMessage("Ocorreu um erro inesperado. Por favor, tente novamente.");
        }
        error.setCodeServer(serverCode);
        return error;
    }



    public static  ErrorResponse getGsonParseError() {
        final ErrorResponse error = new ErrorResponse();
        error.setCode("Error to parse Gson");
        error.setMessage("Desculpe-nos, não foi possível processar a sua solicitação no momento.");
        error.setMessageServer("Desculpe-nos, não foi possível processar a sua solicitação no momento.");
        return error;
    }

    public static  ErrorResponse getConnectionError() {
        final ErrorResponse error = new ErrorResponse();
        error.setCode(UNEXPECTED);
        error.setMessage("Você não está conectado, por favor tente novamente mais tarde.");
        error.setMessageServer("Você não está conectado, por favor tente novamente mais tarde.");
        return error;
    }
    public static ErrorResponse getExceptionError(final ResponseBody responseBody) {
        final ErrorResponse error = new ErrorResponse();
        error.setCode(UNEXPECTED);
        error.setMessage("Desculpe-nos, não foi possível processar a sua solicitação no momento.");
        ErrorResponse errorServer;
        try {
            if(responseBody != null) {
                errorServer = new Gson().fromJson(responseBody.string(), ErrorResponse.class);
                error.setMessageServer(errorServer.message);
            }else{
                error.setMessageServer("Desculpe-nos, não foi possível processar a sua solicitação no momento.");
            }
        } catch (Exception e) {
            error.setMessageServer("Desculpe-nos, não foi possível processar a sua solicitação no momento.");
        }
        return error;
    }

    public static ErrorResponse getExceptionFail(final ResponseBody responseBody, int serverCode) {
        final ErrorResponse error = new ErrorResponse();
        error.setCode(UNEXPECTED);
        error.setMessage("Desculpe-nos, não foi possível processar a sua solicitação no momento.");
        ErrorResponse errorServer;
        try {
            if(responseBody != null) {
                errorServer = new Gson().fromJson(responseBody.string(), ErrorResponse.class);
                error.setMessageServer(errorServer.message);

            }else{
                error.setMessageServer("Desculpe-nos, não foi possível processar a sua solicitação no momento.");
            }
        } catch (Exception e) {
            error.setMessageServer("Desculpe-nos, não foi possível processar a sua solicitação no momento.");
        }
        error.setCodeServer(serverCode);
        return error;
    }

    public static ErrorResponse getUpdateApplicationError() {
        final ErrorResponse error = new ErrorResponse();
        error.setCode(UPDATE_VERSION);
        error.setMessage("A versão do seu aplicativo está desatualizada. Clique no botão abaixo para atualizar.");
        error.setMessageServer("A versão do seu aplicativo está desatualizada. Clique no botão abaixo para atualizar.");
        return error;
    }

    public static ErrorResponse setSessionError(final ResponseBody responseBody, int serverCode) {
        final ErrorResponse error = new ErrorResponse();
        ErrorResponse errorServer;
        try {
            if(responseBody != null) {
                errorServer = new Gson().fromJson(responseBody.string(), ErrorResponse.class);
                error.setMessageServer(errorServer.message);
            }else{
                error.setMessageServer("Sua sessão expirou. Por favor, faça o login novamente.");
            }
        } catch (Exception e) {
            error.setMessageServer("Sua sessão expirou. Por favor, faça o login novamente.");
        }
        error.setCode(SESSION);
        error.setMessage("Sua sessão expirou. Por favor, faça o login novamente.");
        error.setCodeServer(serverCode);
        return error;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "code='" + code + '\'' +
                ", codeServer='" + codeServer + '\'' +
                ", message='" + message + '\'' +
                ", messageServer='" + messageServer + '\'' +
                '}';
    }
}

