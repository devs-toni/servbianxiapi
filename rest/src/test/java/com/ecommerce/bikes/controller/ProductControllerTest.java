package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.domain.Like;
import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.entities.LikeEntity;
import com.ecommerce.bikes.entities.ProductEntity;
import com.ecommerce.bikes.entities.UserEntity;
import com.ecommerce.bikes.http.ProductResponse;
import com.ecommerce.bikes.useCases.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductControllerTest {
    @InjectMocks
    private ProductController productController;
    private final FindProductByIdUseCase findProductByIdUseCase = mock(FindProductByIdUseCase.class);
    private final FindAllProductsUseCase findAllProductsUseCase = mock(FindAllProductsUseCase.class);
    private final FindAllProductsByTypeUseCase findAllProductsByTypeUseCase = mock(FindAllProductsByTypeUseCase.class);
    private final FindFavouritesUseCase findFavouritesUseCase = mock(FindFavouritesUseCase.class);
    private final FindProductsByNameUseCase findProductsByNameUseCase = mock(FindProductsByNameUseCase.class);
    private final InsertLikeUseCase insertLikeUseCase = mock(InsertLikeUseCase.class);
    private final GetLikeUseCase getLikeUseCase = mock(GetLikeUseCase.class);
    private final DeleteLikeUseCase deleteLikeUseCase = mock(DeleteLikeUseCase.class);

    @Test
    public void should_return_product_by_id() {
        when(findProductByIdUseCase.find(1L)).thenReturn(product);

        ResponseEntity<ProductResponse> response = productController.findProductById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productResponse, response.getBody());
    }

    @Test
    public void should_return_all_products() {
        when(findAllProductsUseCase.find()).thenReturn(products);

        ResponseEntity<List<ProductResponse>> response = productController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productsResponses, response.getBody());
    }

    @Test
    public void should_return_all_products_by_type() {
        String type = "road";

        when(findAllProductsByTypeUseCase.find(type)).thenReturn(products);

        ResponseEntity<List<ProductResponse>> response = productController.findAllByType(type);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productsResponses, response.getBody());
    }

    @Test
    public void should_return_all_favourite_products() {

        when(findFavouritesUseCase.find(1L)).thenReturn(products);

        ResponseEntity<List<ProductResponse>> response = productController.findFavourites(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productsResponses, response.getBody());
    }

    @Test
    public void should_return_all_products_by_name() {
        String name = "Meth";

        when(findProductsByNameUseCase.find(name)).thenReturn(products);

        ResponseEntity<List<ProductResponse>> response = productController.findByName(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productsResponses, response.getBody());
    }

    @Test
    public void should_add_like() {
        when(insertLikeUseCase.add(1L, 1L)).thenReturn(10);

        ResponseEntity<Integer> response = productController.addLike(1L, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void should_get_like() {
        when(getLikeUseCase.get(1L, 1L)).thenReturn(10);

        ResponseEntity<Integer> response = productController.getLike(1L, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void should_delete_like() {
        when(deleteLikeUseCase.delete(1L, 1L)).thenReturn(10);

        ResponseEntity<Integer> response = productController.deleteLike(1L, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    protected static final ProductEntity productEntity =
            new ProductEntity(
                    1L,
                    "Methanol XH",
                    "road",
                    34f,
                    34,
                    "dsf",
                    "fds",
                    emptySet(),
                    emptyList(),
                    emptyList());
    protected static final Product product = ProductEntity.toDomain(productEntity);
    protected static final List<Product> products = List.of(product);
    protected static final ProductResponse productResponse = ProductResponse.toProductResponse(product);
    protected static final List<ProductResponse> productsResponses = List.of(productResponse);
}