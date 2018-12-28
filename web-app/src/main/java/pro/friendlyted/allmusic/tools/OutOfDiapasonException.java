package pro.friendlyted.allmusic.tools;

public class OutOfDiapasonException extends Throwable {
    public OutOfDiapasonException() {
    }

    public OutOfDiapasonException(String message) {
        super(message);
    }

    public OutOfDiapasonException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutOfDiapasonException(Throwable cause) {
        super(cause);
    }

    public OutOfDiapasonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
