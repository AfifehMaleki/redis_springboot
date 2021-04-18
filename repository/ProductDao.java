package ticketSell.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ticketSell.entity.Product;

import java.util.List;

import static ticketSell.entity.Product.ClassName.*;

@Repository

public class ProductDao {

    public static final String HASH_KEY ="Ticket";

    @Autowired
    private RedisTemplate template;

//    public Product save(Product product){
//        template.opsForHash().put(HASH_KEY,product.getId(),product);
//        return product;
//    }

    public List<Product> findAll(){
        return template.opsForHash().values(HASH_KEY);
    }

    public Product findProductById(int id){
        return (Product) template.opsForHash().get(HASH_KEY,id);
    }

    public String deleteProduct(int id){
        template.opsForHash().delete(HASH_KEY,id);
        return "Product removed";
    }

    public void initializing(){
        Product pa = new Product( 1,A,100,2);
        Product pb = new Product( 2,B,50,5_001);
        Product pc = new Product( 3,C,25,5_001);
        Product pd = new Product( 4,D,10,5_001);

        template.opsForHash().put(HASH_KEY,pa.getId(),pa);
        template.opsForHash().put(HASH_KEY,pb.getId(),pb);
        template.opsForHash().put(HASH_KEY,pc.getId(),pc);
        template.opsForHash().put(HASH_KEY,pd.getId(),pd);
        return ;
    }

    public void updateProduct(Product product, int size) {
        product.setSize(size);
        template.opsForHash().put(HASH_KEY,product.getId(),product);
    }
}
