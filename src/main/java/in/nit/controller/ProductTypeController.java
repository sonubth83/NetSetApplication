package in.nit.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import in.nit.model.Category;
import in.nit.model.Product;
import in.nit.service.ICategoryService;
import in.nit.service.IProductService;
import in.nit.util.CommonUtil;

@Controller
@RequestMapping("/product")
public class ProductTypeController {

	@Autowired
	private IProductService service;

	@Autowired
	private ICategoryService catService;

	public void commonUi(Model model) {
		List<Object[]> categoryList = catService.getCategoryIdAndName();
		Map<Integer, String> categoryMap = CommonUtil.convert(categoryList);
		model.addAttribute("categoryMap", categoryMap);
	}
	
	@RequestMapping("/register")
	public String showProduct(Model model) {
		Product product = new Product();
		product.setCategoryData(new Category());
		model.addAttribute("productType", product);
		commonUi(model);
		return "ProductRegistration";
	}


	@RequestMapping(value = "/insert", method = POST)
	public String saveProduct(@ModelAttribute Product product,
			Model model,
			@RequestParam CommonsMultipartFile[] fileUpload
			) {
		//Product p = (Product) model.getAttribute("productType");
		//Product p2 = (Product) model.getAttribute("product");
		model.addAttribute("product", new Product());
		if (fileUpload != null && fileUpload.length > 0) {
			for (CommonsMultipartFile aFile : fileUpload) {
				System.out.println("Saving file: " + aFile.getOriginalFilename());
				
				product.setFileName(aFile.getOriginalFilename());
				product.setData(aFile.getBytes());
			}
		}
		Integer id = service.saveProduct(product);
		String message = "Product '" + id + "' saved";
		model.addAttribute("message", message);
		commonUi(model);
		return "ProductRegistration";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping({ "/all", "/all/{page}" })
	public String getAllProduct(@PathVariable(required = false, name = "page") String page, 
			HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<Product> usersList = null;
		PagedListHolder<Product> userList;
		if (page == null) {
			userList = new PagedListHolder<Product>();
			usersList = service.getAllProduct();
			System.out.println("Image ->"+usersList.get(0).getData());
			userList.setSource(usersList);
			userList.setPageSize(5);
			
			request.getSession().setAttribute("userList", userList);
		} else if (page.equals("prev")) {
			userList = (PagedListHolder<Product>) request.getSession().getAttribute("userList");
			userList.previousPage();
		} else if (page.equals("next")) {
			userList = (PagedListHolder<Product>) request.getSession().getAttribute("userList");
			userList.nextPage();
		} else {
			int pageNum = Integer.parseInt(page);
			userList = (PagedListHolder<Product>) request.getSession().getAttribute("userList");

			userList.setPage(pageNum - 1);

		}
		model.addAttribute("userList", userList); 
		
		return "ProductData";
	}

	@RequestMapping("/delete")
	public String deleteProductById(@RequestParam("prdId") Integer id, Model model) {
		service.deleteProduct(id);
		String message = "Product '" + id + "' deleted";
		model.addAttribute("message", message);

		List<Product> list = service.getAllProduct();
		model.addAttribute("list", list);
		return "ProductData";
	}
}
