package com.vld.wiredbraincoffee.reward;

import com.vld.wiredbraincoffee.product.Product;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Reward by Conversion® \uD83D\uDE04")
public class RewardByDiscountServiceNestedTest {
    private RewardByDiscountService reward = null;

    @BeforeEach
    @DisplayName("Given that 100 points are needed for $10")
    void setUp() {
        reward = new RewardByDiscountService();
        reward.setPercentage(0.1);
        reward.setNeededPoints(100);
    }

    @Nested
    class ZeroPoints {
        private List<Product> smallOrder = null;
        private RewardInformation info = null;

        @BeforeEach
        void setUp() {
            Product smallDecaf = new Product(1, "Small Decaf", 1.99);
            smallOrder = Collections.singletonList(smallDecaf);
            info = reward.applyReward(smallOrder, 0);
        }

        @Test
        void checkDiscount() {
            assertEquals(0, info.getDiscount());
        }

        @Test
        void checkPointsRedeemed() {
            assertEquals(0, info.getPointsRedeemed());
        }

        @Test
        void setCustomerPoints() {
            RewardInformation info = reward.applyReward(smallOrder, 0);
            assertEquals(0, info.getDiscount());
            assertEquals(0, info.getPointsRedeemed());
        }
    }

    @Nested
    class BigOrder {
        private List<Product> bigOrder;

        @BeforeEach
        void setUp() {

            Product bigDecaf = new Product(2, "Big Decaf", 2.49);
            Product bigLatte = new Product(3, "Big Latte", 2.99);
            Product bigTea = new Product(4, "Big Tea", 2.99);
            Product espresso = new Product(5, "Espresso", 2.99);
            bigOrder = Arrays.asList(
                bigDecaf, bigLatte, bigTea, espresso);
        }

        @Test
        void enoughCustomerPointsForDisountInBigOrder() {
            RewardInformation info = reward.applyReward(bigOrder, 200);
            assertEquals(1.14, info.getDiscount(), 0.01);
            assertEquals(100, info.getPointsRedeemed());
        }
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


}
