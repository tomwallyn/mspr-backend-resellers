package com.epsi.msprbackendresellers

import com.epsi.msprbackendresellers.models.Line
import com.epsi.msprbackendresellers.models.Order
import com.epsi.msprbackendresellers.repository.OrderApiClient
import com.epsi.msprbackendresellers.services.OrderService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private lateinit var orderService: OrderService

    @MockBean
    private lateinit var orderApiClient: OrderApiClient

    @Test
    fun `getOrderByRef should return order`() {
        // Given
        val token = "your_token"
        val ref = "your_order_ref"
        val expectedOrder = Order(
                socid = "socid",
                statut = "statut",
                billed = "billed",
                lines = listOf(Line("fk_commande", "commande_id", "ref", "libelle")),
                ref = ref
        )

        `when`(orderApiClient.getOrder(token, ref)).thenReturn(Mono.just(expectedOrder))

        // When
        val result = orderService.getOrderByRef(token, ref)

        // Then
        StepVerifier.create(result)
                .expectNext(expectedOrder)
                .verifyComplete()
    }

    @Test
    fun `getOrders should return list of orders`() {
        // Given
        val token = "your_token"
        val expectedOrders = listOf(
                Order(
                        socid = "socid1",
                        statut = "statut1",
                        billed = "billed1",
                        lines = listOf(Line("fk_commande1", "commande_id1", "ref1", "libelle1")),
                        ref = "order1"
                ),
                Order(
                        socid = "socid2",
                        statut = "statut2",
                        billed = "billed2",
                        lines = listOf(Line("fk_commande2", "commande_id2", "ref2", "libelle2")),
                        ref = "order2"
                )
        )

        `when`(orderApiClient.getOrders(token)).thenReturn(Flux.fromIterable(expectedOrders))

        // When
        val result = orderService.getOrders(token)

        // Then
        StepVerifier.create(result)
                .expectNextSequence(expectedOrders)
                .verifyComplete()
    }
}


