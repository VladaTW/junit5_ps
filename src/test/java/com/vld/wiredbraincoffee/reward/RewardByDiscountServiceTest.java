package com.vld.wiredbraincoffee.reward;

import com.vld.wiredbraincoffee.product.Product;
import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RewardByDiscountServiceTest {
    RewardByDiscountService reward;

    public RewardByDiscountServiceTest() {
        System.out.println("Constructor");
    }

    @BeforeAll
    static void setUpAll() {
        System.out.println("BeforeAll");
    }

    @BeforeEach
    void setUp() {
        reward = new RewardByDiscountService();

        System.out.println("BeforeEach");
    }

    @AfterEach
    void tearDown() {
        System.out.println("AfterEach");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("AfterAll");
    }

    @Test
    @Order(3)
    void setNeededPoints() {
        System.out.println("Test setNeededPoints()");
        reward.setNeededPoints(100);
        assertEquals(100, reward.getNeededPoints());
    }

    @Test
    @Order(1)
    void setPercentage() {
        System.out.println("Test setPercentage()");
        reward.setPercentage(0.1);
        assertEquals(.1, reward.getPercentage());
    }

    @Test
    @Order(2)
    void setCustomerPoints() {
        System.out.println("Test setCustomerPoints()");
        reward.setPercentage(.1);
        reward.setNeededPoints(100);
        Product smallDecaf = new Product(1, "Small Decaf", 1.99);
        List<Product> order = Collections.singletonList(smallDecaf);

        RewardInformation info = reward.applyReward(order, 0);
        assertEquals(0, info.getDiscount());
        assertEquals(0, info.getPointsRedeemed());
    }
}
