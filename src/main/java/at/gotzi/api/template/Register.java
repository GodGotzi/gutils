package at.gotzi.api.template;

public interface Register<T> {
    void register(T t);

    void unregister(T t);
}
