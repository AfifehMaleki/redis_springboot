package ticketSell.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Ticket")
public class Product implements Serializable {
//    private static final long serialVersionUID = 1367111444570308093L;
    @Id
    private int id;

    private ClassName className;
    private long price;
    private int size;

    public enum ClassName {
        A,B,C,D
    }


}
