package org.fzb.rpcfx.api;

/**
 * RpcfxException
 *
 * @author fengzhenbing
 */
public class RpcfxException  extends RuntimeException {
    private static final long serialVersionUID = -948934144333391209L;

    /**
     * Instantiates a new Rpcfx exception.
     */
    public RpcfxException() {
    }

    /**
     * Instantiates a new Rpcfx exception.
     *
     * @param message the message
     */
    public RpcfxException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new Rpcfx exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public RpcfxException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Rpcfx exception.
     *
     * @param cause the cause
     */
    public RpcfxException(final Throwable cause) {
        super(cause);
    }
}
