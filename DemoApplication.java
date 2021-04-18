package ticketSell;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ticketSell.entity.Product;
import ticketSell.repository.ProductDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {

	@Autowired
	private ProductDao dao;

//	@PostMapping
//	public Product save(@RequestBody Product product){
//		return dao.save(product);
//	}

	@GetMapping
	public List<Product> getAllProducts(){
		return dao.findAll();
	}

//	@GetMapping("/{id}")
//	public Product findProduct(@PathVariable int id){
//		return dao.findProductById(id);
//	}

//	@DeleteMapping("/{id}")
//	public String remove(@PathVariable int id){
//		return dao.deleteProduct(id);
//	}
//***************
//	@GetMapping("/{className}")
//	public Serializable findProduct(@PathVariable String className, Model model) {
//		Product product;
//		int id = 0;
//
//		switch(className){
//			case "A": id = 1; break;
//			case "B": id = 2; break;
//			case "C": id = 3; break;
//			case "D": id = 4; break;
//		}
//
//		if(dao.findProductById(id) == null)
//			dao.initializing();
//
//		product = dao.findProductById(id);
//
//		int size = product.getSize();
//		if (size > 0) {
//			size--;
//			dao.updateProduct(product, size);
//			return product;
//		}
//		return "Error";
//	}

	//*******************************

	@GetMapping("/product/{className}/cash")
	public ModelAndView showTicket(@PathVariable String className, Model model) throws InterruptedException {
		Product product;
		int id = 0;

		switch(className){
			case "A": id = 1; break;
			case "B": id = 2; break;
			case "C": id = 3; break;
			case "D": id = 4; break;
		}

		if(dao.findProductById(id) == null)
			dao.initializing();

		product = dao.findProductById(id);

		int size = product.getSize();
		if (size > 0) {
			size--;
			dao.updateProduct(product, size);
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("suc");
		return modelAndView;
	}


	@GetMapping("/product/{className}/no")
	public ModelAndView redirectTicket(@PathVariable String className, Model model) throws InterruptedException {
		Product product;
		int id = 0;

		switch(className){
			case "A": id = 1; break;
			case "B": id = 2; break;
			case "C": id = 3; break;
			case "D": id = 4; break;
		}

		product = dao.findProductById(id);
		int size = product.getSize();
		dao.updateProduct(product, ++size);

		return new ModelAndView("redirect:" + "/product");
	}

//	@GetMapping("/prod/cash")
//	public ModelAndView cash() {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("suc");
//		return modelAndView;
//	}


	@RequestMapping("/product")
	ModelAndView start(Model model){
		Product product;
		List cName = new ArrayList(),price = new ArrayList(),size = new ArrayList();
//		for (int i = 1; i <= 4; i++) {
//			product = dao.findProductById(i);
//
//		}
		model.addAttribute("products", dao.findAll());

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;

	}

	@RequestMapping("/")
	String init(){
		dao.initializing();
		return "initial";
	}



	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

