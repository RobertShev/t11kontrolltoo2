package baas;
import javax.persistence.*;

@Entity
@Table(name="komb")
public class Komb{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int id;
    public int n;
    public int m;
    public float vastus;    
}