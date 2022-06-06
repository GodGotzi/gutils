package at.gotzi.api.template;

import at.gotzi.api.ano.Comment;

public abstract class IBuilder<T> {

    private boolean successful = false;

    public abstract T getResult();

    @Comment.Setter
    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    @Comment.Getter
    public boolean isSuccessful() {
        return successful;
    }
}
