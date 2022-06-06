package at.gotzi.api;

public interface Action<T> {

    void run(T t);

}