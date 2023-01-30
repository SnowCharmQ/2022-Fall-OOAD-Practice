public interface Subject<T> {
    void registerObserver(T t);

    void removeObserver(T t);

    void notifyObservers(char keyChar);

    void notifyObservers();
}
