package com.msagres.inventoryapp.product;

import com.msagres.inventoryapp.category.Category;
import com.msagres.inventoryapp.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/products/new")
    public String showNewProductForm(Model model) {
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("product", new Product());
        model.addAttribute("categoryList", categoryList);

        return "product_form";
    }

    @PostMapping("/products/save")
    public String saveProduct(Product product) {
        productRepository.save(product);

        return "redirect:/products";
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        List<Product> productList = productRepository.findAll();
        model.addAttribute("productList", productList);

        return "products";
    }

    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable("id") UUID id, Model model) {
        Product product = productRepository.findById(id).get();
        model.addAttribute("product", product);

        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("categoryList", categoryList);

        return "product_form";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") UUID id, Model model){
        productRepository.deleteById(id);

        return "redirect:/products";
    }
}
