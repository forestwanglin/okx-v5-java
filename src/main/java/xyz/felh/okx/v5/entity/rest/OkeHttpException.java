package xyz.felh.okx.v5.entity.rest;

public class OkeHttpException extends RuntimeException {

    private final OkxRestError okxRestError;

    public OkeHttpException(OkxRestError okxRestError, String message, Throwable cause) {
        super(message, cause);
        this.okxRestError = okxRestError;
    }

}
