public interface Manageable<T> {
    public void add(T item);
    public void update(T item); 
    public void delete(T item);       
    public void displayAll();
    // T findById(String id); 
}