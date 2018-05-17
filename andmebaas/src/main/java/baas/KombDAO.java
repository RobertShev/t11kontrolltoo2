package baas;
import javax.transaction.*;
import org.springframework.data.repository.CrudRepository;
@Transactional
public interface KombDAO extends CrudRepository<Komb, Integer>{}