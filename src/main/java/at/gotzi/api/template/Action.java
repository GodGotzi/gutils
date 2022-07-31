package at.gotzi.api.template;

public interface Action<T> {

    void run(T t);

}