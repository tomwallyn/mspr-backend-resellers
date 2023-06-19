package com.epsi.msprbackendresellers

import com.epsi.msprbackendresellers.repository.ProductApiClient
import com.epsi.msprbackendresellers.services.ProductService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.math.BigDecimal
import com.epsi.msprbackendresellers.models.Product
import reactor.test.StepVerifier

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private lateinit var productService: ProductService

    @MockBean
    private lateinit var productApiClient: ProductApiClient

    @Test
    fun `getProductByRef should return product`() {
        // Given
        val token = "your_token"
        val ref = "your_product_ref"
        val expectedProduct = Product(
                label = "Product Label",
                description = "Product Description",
                price = BigDecimal("10.99"),
                url = "http://example.com/product",
                date_creation = "2023-06-19",
                ref = ref,
                stock_reel = 100
        )

        `when`(productApiClient.getProduct(token, ref)).thenReturn(Mono.just(expectedProduct))

        // When
        val result = productService.getProductByRef(token, ref)

        // Then
        StepVerifier.create(result)
                .expectNext(expectedProduct)
                .verifyComplete()
    }

    @Test
    fun `getProducts should return list of products`() {
        // Given
        val token = "your_token"
        val expectedProducts = listOf(
                Product(
                        label = "Product 1",
                        description = "Product 1 Description",
                        price = BigDecimal("19.99"),
                        url = "http://example.com/product1",
                        date_creation = "2023-06-18",
                        ref = "product1",
                        stock_reel = 50
                ),
                Product(
                        label = "Product 2",
                        description = "Product 2 Description",
                        price = BigDecimal("29.99"),
                        url = "http://example.com/product2",
                        date_creation = "2023-06-17",
                        ref = "product2",
                        stock_reel = 75
                )
        )

        `when`(productApiClient.getProducts(token)).thenReturn(Flux.fromIterable(expectedProducts))

        // When
        val result = productService.getProducts(token)

        // Then
        StepVerifier.create(result)
                .expectNextSequence(expectedProducts)
                .verifyComplete()
    }
}

