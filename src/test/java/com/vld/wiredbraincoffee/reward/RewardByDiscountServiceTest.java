package com.vld.wiredbraincoffee.reward;

import com.vld.wiredbraincoffee.product.Product;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RewardByDiscountServiceTest {
    private RewardByDiscountService reward;
    private List<Product> smallOrder;
    private List<Product> bigOrder;

    @BeforeEach
    void setUp() {
        reward = new RewardByDiscountService();
        reward.setNeededPoints(100);
        reward.setPercentage(0.1);
        Product smallDecaf = new Product(1, "Small Decaf", 1.99);

        Product bigDecaf = new Product(2, "Big Decaf", 2.49);
        Product bigLatte = new Product(3, "Big Latte", 2.99);
        Product bigTea = new Product(4, "Big Tea", 2.99);
        Product espresso = new Product(5, "Espresso", 2.99);
        bigOrder = Arrays.asList(
            bigDecaf, bigLatte, bigTea, espresso);
        smallOrder = Collections.singletonList(smallDecaf);
    }

    @Test
    @Order(3)
    void setNeededPoints() {
        assertEquals(100, reward.getNeededPoints());
    }

    @Test
    @Order(1)
    void setPercentage() {
        assertEquals(.1, reward.getPercentage());
    }

    @Test
    @Order(2)
    void setCustomerPoints() {
        RewardInformation info = reward.applyReward(smallOrder, 0);
        assertEquals(0, info.getDiscount());
        assertEquals(0, info.getPointsRedeemed());
    }

    @Test
    @Order(2)
    void enoughCustomerPointsForDisountInBigOrder() {
        RewardInformation info = reward.applyReward(bigOrder, 200);
        assertEquals(1.14, info.getDiscount(), 0.01); // the third argument, "delta", gives a range of
        // tolerance, i.e. the values within the range
        // are considered equal
        assertEquals(100, info.getPointsRedeemed());
    }
}
