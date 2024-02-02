package trabajo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Dao<T> {
	
	public Optional<T> get(UUID id);
	public Optional<T> get(String naturalIdentifier);
    
    public List<T> getAll();
    
    public void save(T t);
    
    public void update(T t, String parameterToModify, String[] params);
    
    public void delete(T t);
}
