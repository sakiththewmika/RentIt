package com.rentit.controller;

import com.rentit.model.Product;
import com.rentit.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllProducts() throws Exception {
        Product product1 = new Product();
        product1.setId("1");
        product1.setName("Laptop");
        product1.setPrice(999.99);
        product1.setCategory("Electronics");

        Product product2 = new Product();
        product2.setId("2");
        product2.setName("Camera");
        product2.setPrice(499.99);
        product2.setCategory("Photography");

        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(product1, product2));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Laptop")))
                .andExpect(jsonPath("$[1].name", is("Camera")));
    }

    @Test
    public void testCreateProduct() throws Exception {
        Product product = new Product();
        product.setName("Chair");
        product.setPrice(49.99);
        product.setCategory("Furniture");

        Product savedProduct = new Product();
        savedProduct.setId("123");
        savedProduct.setName("Chair");
        savedProduct.setPrice(49.99);
        savedProduct.setCategory("Furniture");

        Mockito.when(repository.save(Mockito.any(Product.class))).thenReturn(savedProduct);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("123")))
                .andExpect(jsonPath("$.name", is("Chair")))
                .andExpect(jsonPath("$.price", is(49.99)))
                .andExpect(jsonPath("$.category", is("Furniture")));
    }
}
