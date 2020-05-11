package in.nit.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.nit.model.Product;
import in.nit.service.IProductService;

@RestController
@RequestMapping("/rest/product")
public class ProductTypeControllerRest {

	@Autowired
	private IProductService service;

	@GetMapping("/all")
	public ResponseEntity<?> fetchAllProduct() {
		ResponseEntity<?> resp = null;
		try {
			List<Product> product = service.getAllProduct();
			if (product != null && !product.isEmpty()) {
				resp = new ResponseEntity<List<Product>>(product, HttpStatus.OK);
			} else {
				resp = new ResponseEntity<String>(" No PRODUCTS Found ", HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to Fetch Data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@DeleteMapping("/remove")
	public ResponseEntity<String> removeProduct(@RequestParam("id") Integer id) {
		ResponseEntity<String> resp = null;
		try {
			service.deleteProduct(id);
			resp = new ResponseEntity<String>("Product '" + id + "' Deleted", HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Product '" + id + "' Not Exist", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}

	@PostMapping("/save")
	public ResponseEntity<String> saveProductData(@RequestBody Product product) {
		ResponseEntity<String> resp = null;
		try {
			Integer id = service.saveProduct(product);
			resp = new ResponseEntity<String>("Product '" + id + "' saved", HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to save Product", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}

}