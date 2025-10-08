package com.product.product.infrastructure.dao;

import com.product.product.domain.model.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {

    // Пошук по кількох категоріях одразу
    public static Specification<Product> categoryIn(List<UUID> categoryIds) {
        if (categoryIds == null || categoryIds.isEmpty()) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            root.get("category").get("id").in(categoryIds);
    }

    // Пошук продукців по категорії
    public static Specification<Product> hasCategory(UUID categoryId) {
        if (categoryId == null) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("category").get("id"), categoryId);
    }

    // Пошук по кількох брендах
    public static Specification<Product> brandIn(List<UUID> brandIds) {
        if (brandIds == null || brandIds.isEmpty()) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            root.get("brand").get("id").in(brandIds);
    }

    // Пошук продуктів за брендом
    public static Specification<Product> hasBrand(UUID brandId) {
        if (brandId == null) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("brand").get("id"), brandId);
    }

    // Пошук по кількох типах продуктів
    public static Specification<Product> productTypeIn(List<UUID> productTypeIds) {
        if (productTypeIds == null || productTypeIds.isEmpty()) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            root.get("productType").get("id").in(productTypeIds);
    }

    // Пошук продуктів певного типу
    public static Specification<Product> hasProductType(UUID productTypeId) {
        if (productTypeId == null) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("productType").get("id"), productTypeId);
    }

    /**
     * Пошук по назві товару без врахування регістру та точності SQL еквівалент: WHERE LOWER(name)
     * LIKE '%keyword%'
     * @param keyword пошуковий запит по назві продукту
     * @return продукти що можуть відповідати повністю або частково, по назві
     */
    public static Specification<Product> nameContains(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                "%".concat(keyword.toLowerCase()).concat("%"));
    }

    /**
     * Якщо min не вказано — виконати пошук за max < Якщо max не вказано — виконати пошук за min >
     * Якщо обидва значення присутні, тоді шукати в діапазоні
     *
     * @param minPrice мінімальне значення ціни
     * @param maxPrice максимальне значення ціни
     * @return повернути продукти в діапазоні ціни або ті що більші за мінімальну або менші за
     * максимальну ціну
     */
    public static Specification<Product> priceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        if (minPrice == null && maxPrice == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> {
            if (minPrice == null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
            }
            if (maxPrice == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
            }
            return criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
        };
    }

    /**
     * Вибірка тих продуктів що є в наявності на складі SQL еквівалент: WHERE stock > 0
     *
     * @return продукти в наявності
     */
    public static Specification<Product> inStock() {
        return (root, query, criteriaBuilder) ->
            // Підказка: criteriaBuilder.greaterThan(поле, значення)
            // Поле: "stock", значення: 0
            criteriaBuilder.greaterThan(root.get("stock"), 0);
    }

    /**
     * Пошук продуктів що містять масу в діапазоні
     * @param minWeight мінімальна кількість грам
     * @param maxWeight максимальна кількість грам
     * @return продукти що важать у вказаному діапазоні
     */
    public static Specification<Product> weightBetween(Integer minWeight, Integer maxWeight) {
        if (minWeight == null && maxWeight == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> {
            if (minWeight == null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("weightGrams"), maxWeight);
            }
            if (maxWeight == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("weightGrams"), minWeight);
            }
            return criteriaBuilder.between(root.get("weightGrams"), minWeight, maxWeight);
        };
    }

    public static Specification<Product> volumeBetween(Integer minVolume, Integer maxVolume) {
        if (minVolume == null && maxVolume == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> {
            if (minVolume == null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("volumeMl"), maxVolume);
            }
            if (maxVolume == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("volumeMl"), minVolume);
            }
            return criteriaBuilder.between(root.get("volumeMl"), minVolume, maxVolume);
        };
    }

    // Інші фільтри

    // Пошук продуктів більших за мінімальне значення
    public static Specification<Product> priceGreaterThanOrEqual(BigDecimal minPrice) {
        if (minPrice == null) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            // Підказка: criteriaBuilder має метод greaterThanOrEqualTo(поле, значення)
            criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    /**
     * Метод пошуку продуктів що належать певній категорії, ціна яких менша за вказану та більше за
     * 0 на складі SQL: WHERE category_id = ? AND price <= ? AND stock > 0
     *
     * @param categoryId категорія
     * @param maxPrice   максимальна ціна продукту
     * @return продукти певної категорії що є в наявності та вартують менше вказаної ціни
     */
    public static Specification<Product> affordableInCategory(
        UUID categoryId, BigDecimal maxPrice) {
        if (categoryId == null || maxPrice == null) {
            return null;
        }

        return (root, query, criteriaBuilder) -> {
            // Потрібно створити 3 умови і об'єднати їх через AND

            // Умова 1: категорія
            var categoryCondition = criteriaBuilder.equal(
                root.get("category").get("id"), categoryId);

            // Умова 2: ціна
            var priceCondition = criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);

            // Умова 3: в наявності
            var stockCondition = criteriaBuilder.greaterThan(root.get("stock"), 0);

            // Об'єднати через AND
            // Підказка: criteriaBuilder.and(умова1, умова2, умова3...)
            return criteriaBuilder.and(categoryCondition, priceCondition, stockCondition);
        };
    }

    /**
     * Метод робить вибірку продуктів що належать одному із вказаних брендів SQL: WHERE brand_id = ?
     * OR brand_id = ?
     *
     * @param brandId1 бренд який шукаємо
     * @param brandId2 другий бренд який шукаємо
     * @return продукти що належать або першому або другому бренду
     */
    public static Specification<Product> brandIsOneOf(UUID brandId1, UUID brandId2) {
        if (brandId1 == null && brandId2 == null) {
            return null;
        }

        return (root, query, criteriaBuilder) -> {
            // Що робити якщо тільки один з ID заданий?
            // Підказка: criteriaBuilder.or(умова1, умова2)
            var brand1Condition = criteriaBuilder.equal(root.get("brand").get("id"), brandId1);
            var brand2Condition = criteriaBuilder.equal(root.get("brand").get("id"), brandId2);

            if (brandId1 != null && brandId2 == null) {
                return brand1Condition;
            }
            if (brandId1 == null && brandId2 != null) {
                return brand2Condition;
            }
            return criteriaBuilder.or(brand1Condition, brand2Condition);
        };
    }

    /**
     * Вибірка усіх категорій окрім вказаної SQL: WHERE category_id != ? (або WHERE NOT category_id
     * = ?)
     *
     * @param categoryId ID категорії, яку уникаємо
     * @return список категорій окрім вказаної в ID
     */
    public static Specification<Product> categoryIsNot(UUID categoryId) {
        if (categoryId == null) {
            return null;
        }

        return (root, query, criteriaBuilder) ->
            // Підказка:
            // Варіант 1: criteriaBuilder.notEqual(поле, значення)
            // Варіант 2: criteriaBuilder.not(умова)
            criteriaBuilder.notEqual(root.get("category").get("id"), categoryId);
    }
}
