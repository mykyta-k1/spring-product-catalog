-- ============================
-- BRANDS
-- ============================
INSERT INTO brands (id, name, slug, logo_url, created_at, updated_at) VALUES
('550e8400-e29b-41d4-a716-446655440001', 'Apple', 'apple', 'https://logo.clearbit.com/apple.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('550e8400-e29b-41d4-a716-446655440002', 'Samsung', 'samsung', 'https://logo.clearbit.com/samsung.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('550e8400-e29b-41d4-a716-446655440003', 'Nike', 'nike', 'https://logo.clearbit.com/nike.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('550e8400-e29b-41d4-a716-446655440004', 'Sony', 'sony', 'https://logo.clearbit.com/sony.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('550e8400-e29b-41d4-a716-446655440005', 'Xiaomi', 'xiaomi', 'https://logo.clearbit.com/mi.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('550e8400-e29b-41d4-a716-446655440006', 'Adidas', 'adidas', 'https://logo.clearbit.com/adidas.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ============================
-- PRODUCT TYPES
-- ============================
INSERT INTO product_types (id, name, slug, created_at, updated_at) VALUES
('650e8400-e29b-41d4-a716-446655440001', 'Smartphone', 'smartphone', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('650e8400-e29b-41d4-a716-446655440002', 'Laptop', 'laptop', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('650e8400-e29b-41d4-a716-446655440003', 'Shoes', 'shoes', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('650e8400-e29b-41d4-a716-446655440004', 'Headphones', 'headphones', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('650e8400-e29b-41d4-a716-446655440005', 'TV', 'tv', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('650e8400-e29b-41d4-a716-446655440006', 'Tablet', 'tablet', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ============================
-- CATEGORIES
-- ============================
INSERT INTO categories (id, parent_id, name, slug, created_at, updated_at) VALUES
('750e8400-e29b-41d4-a716-446655440001', NULL, 'Electronics', 'electronics', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440002', NULL, 'Fashion', 'fashion', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440003', NULL, 'Sports', 'sports', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO categories (id, parent_id, name, slug, created_at, updated_at) VALUES
('750e8400-e29b-41d4-a716-446655440011', '750e8400-e29b-41d4-a716-446655440001', 'Smartphones', 'smartphones', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440012', '750e8400-e29b-41d4-a716-446655440001', 'Laptops', 'laptops', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440013', '750e8400-e29b-41d4-a716-446655440001', 'TV & Audio', 'tv-audio', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440014', '750e8400-e29b-41d4-a716-446655440001', 'Tablets', 'tablets', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440021', '750e8400-e29b-41d4-a716-446655440002', 'Shoes', 'shoes', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('750e8400-e29b-41d4-a716-446655440031', '750e8400-e29b-41d4-a716-446655440003', 'Running', 'running', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ============================
-- PRODUCTS - SMARTPHONES
-- ============================
INSERT INTO products (id, name, slug, image_url, description, price, stock, brand_id, category_id, product_type_id, weight_grams, volume_ml, series, created_at, updated_at) VALUES
('850e8400-e29b-41d4-a716-446655440001', 'iPhone 15 Pro Max', 'iphone-15-pro-max',
'https://images.unsplash.com/photo-1695048133142-1a20484d2569?w=800',
'Latest flagship with titanium design and A17 Pro chip', 1199.99, 25,
'550e8400-e29b-41d4-a716-446655440001', '750e8400-e29b-41d4-a716-446655440011', '650e8400-e29b-41d4-a716-446655440001',
221, 0, 'iPhone 15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('850e8400-e29b-41d4-a716-446655440002', 'iPhone 14 Pro', 'iphone-14-pro',
'https://images.unsplash.com/photo-1678652197831-2d180705cd2c?w=800',
'Powerful iPhone with Dynamic Island', 899.99, 40,
'550e8400-e29b-41d4-a716-446655440001', '750e8400-e29b-41d4-a716-446655440011', '650e8400-e29b-41d4-a716-446655440001',
206, 0, 'iPhone 14', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('850e8400-e29b-41d4-a716-446655440003', 'Samsung Galaxy S24 Ultra', 'samsung-galaxy-s24-ultra',
'https://images.unsplash.com/photo-1610945415295-d9bbf067e59c?w=800',
'Premium Android flagship with S Pen', 1299.99, 30,
'550e8400-e29b-41d4-a716-446655440002', '750e8400-e29b-41d4-a716-446655440011', '650e8400-e29b-41d4-a716-446655440001',
232, 0, 'Galaxy S', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('850e8400-e29b-41d4-a716-446655440004', 'Samsung Galaxy A54 5G', 'samsung-galaxy-a54',
'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=800',
'Mid-range phone with great value', 449.99, 65,
'550e8400-e29b-41d4-a716-446655440002', '750e8400-e29b-41d4-a716-446655440011', '650e8400-e29b-41d4-a716-446655440001',
202, 0, 'Galaxy A', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('850e8400-e29b-41d4-a716-446655440005', 'Xiaomi 14 Pro', 'xiaomi-14-pro',
'https://images.unsplash.com/photo-1598327105666-5b89351aff97?w=800',
'Flagship killer with Leica camera', 899.99, 35,
'550e8400-e29b-41d4-a716-446655440005', '750e8400-e29b-41d4-a716-446655440011', '650e8400-e29b-41d4-a716-446655440001',
219, 0, 'Xiaomi 14', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ============================
-- PRODUCTS - LAPTOPS
-- ============================
INSERT INTO products (id, name, slug, image_url, description, price, stock, brand_id, category_id, product_type_id, weight_grams, volume_ml, series, created_at, updated_at) VALUES
('850e8400-e29b-41d4-a716-446655440011', 'MacBook Pro 16"', 'macbook-pro-16',
'https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=800',
'Professional laptop with M3 Max chip', 2499.99, 15,
'550e8400-e29b-41d4-a716-446655440001', '750e8400-e29b-41d4-a716-446655440012', '650e8400-e29b-41d4-a716-446655440002',
2150, 0, 'M3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('850e8400-e29b-41d4-a716-446655440012', 'MacBook Air 15"', 'macbook-air-15',
'https://images.unsplash.com/photo-1611186871348-b1ce696e52c9?w=800',
'Thin and light powerhouse', 1299.99, 30,
'550e8400-e29b-41d4-a716-446655440001', '750e8400-e29b-41d4-a716-446655440012', '650e8400-e29b-41d4-a716-446655440002',
1510, 0, 'M2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('850e8400-e29b-41d4-a716-446655440013', 'Samsung Galaxy Book4 Pro', 'samsung-galaxy-book4-pro',
'https://images.unsplash.com/photo-1588872657578-7efd1f1555ed?w=800',
'Premium Windows laptop with AMOLED display', 1599.99, 18,
'550e8400-e29b-41d4-a716-446655440002', '750e8400-e29b-41d4-a716-446655440012', '650e8400-e29b-41d4-a716-446655440002',
1560, 0, 'Book4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ============================
-- PRODUCTS - HEADPHONES
-- ============================
INSERT INTO products (id, name, slug, image_url, description, price, stock, brand_id, category_id, product_type_id, weight_grams, volume_ml, series, created_at, updated_at) VALUES
('850e8400-e29b-41d4-a716-446655440021', 'Sony WH-1000XM5', 'sony-wh-1000xm5',
'https://images.unsplash.com/photo-1546435770-a3e426bf472b?w=800',
'Industry-leading noise cancellation', 399.99, 42,
'550e8400-e29b-41d4-a716-446655440004', '750e8400-e29b-41d4-a716-446655440013', '650e8400-e29b-41d4-a716-446655440004',
250, 0, 'XM5', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('850e8400-e29b-41d4-a716-446655440022', 'Sony WH-1000XM4', 'sony-wh-1000xm4',
'https://images.unsplash.com/photo-1484704849700-f032a568e944?w=800',
'Previous generation still excellent', 279.99, 55,
'550e8400-e29b-41d4-a716-446655440004', '750e8400-e29b-41d4-a716-446655440013', '650e8400-e29b-41d4-a716-446655440004',
254, 0, 'XM4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ============================
-- PRODUCTS - TVs
-- ============================
INSERT INTO products (id, name, slug, image_url, description, price, stock, brand_id, category_id, product_type_id, weight_grams, volume_ml, series, created_at, updated_at) VALUES
('850e8400-e29b-41d4-a716-446655440031', 'Samsung 65" OLED 4K', 'samsung-65-oled',
'https://images.samsung.com/is/image/samsung/p6pim/ua/qe65s85faexua/gallery/ua-oled-s85f-qe65s85faexua-546527403?$684_547_PNG$',
'Premium OLED TV with quantum processor', 2299.99, 8,
'550e8400-e29b-41d4-a716-446655440002', '750e8400-e29b-41d4-a716-446655440013', '650e8400-e29b-41d4-a716-446655440005',
21000, 0, 'S95C', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('850e8400-e29b-41d4-a716-446655440032', 'Sony Bravia XR 55"', 'sony-bravia-xr-55',
'https://images.unsplash.com/photo-1461151304267-38535e780c79?w=800',
'OLED TV with cognitive intelligence', 1799.99, 10,
'550e8400-e29b-41d4-a716-446655440004', '750e8400-e29b-41d4-a716-446655440013', '650e8400-e29b-41d4-a716-446655440005',
18500, 0, 'XR', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ============================
-- PRODUCTS - TABLETS
-- ============================
INSERT INTO products (id, name, slug, image_url, description, price, stock, brand_id, category_id, product_type_id, weight_grams, volume_ml, series, created_at, updated_at) VALUES
('850e8400-e29b-41d4-a716-446655440041', 'iPad Pro 12.9"', 'ipad-pro-129',
'https://images.unsplash.com/photo-1544244015-0df4b3ffc6b0?w=800',
'Most powerful iPad with M2 chip', 1099.99, 22,
'550e8400-e29b-41d4-a716-446655440001', '750e8400-e29b-41d4-a716-446655440014', '650e8400-e29b-41d4-a716-446655440006',
682, 0, 'M2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('850e8400-e29b-41d4-a716-446655440042', 'iPad Air 10.9"', 'ipad-air',
'https://images.unsplash.com/photo-1585790050230-5dd28404ccb9?w=800',
'Perfect balance of power and portability', 599.99, 38,
'550e8400-e29b-41d4-a716-446655440001', '750e8400-e29b-41d4-a716-446655440014', '650e8400-e29b-41d4-a716-446655440006',
461, 0, 'M1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ============================
-- PRODUCTS - SHOES
-- ============================
INSERT INTO products (id, name, slug, image_url, description, price, stock, brand_id, category_id, product_type_id, weight_grams, volume_ml, series, created_at, updated_at) VALUES
('850e8400-e29b-41d4-a716-446655440051', 'Nike Air Max 270', 'nike-air-max-270',
'https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=800',
'Iconic comfort with Max Air cushioning', 149.99, 75,
'550e8400-e29b-41d4-a716-446655440003', '750e8400-e29b-41d4-a716-446655440021', '650e8400-e29b-41d4-a716-446655440003',
350, 0, 'Air Max', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('850e8400-e29b-41d4-a716-446655440052', 'Nike Air Force 1', 'nike-air-force-1',
'https://images.unsplash.com/photo-1549298916-b41d501d3772?w=800',
'Classic street style basketball shoe', 109.99, 95,
'550e8400-e29b-41d4-a716-446655440003', '750e8400-e29b-41d4-a716-446655440021', '650e8400-e29b-41d4-a716-446655440003',
380, 0, 'Air Force', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('850e8400-e29b-41d4-a716-446655440053', 'Nike Pegasus 40', 'nike-pegasus-40',
'https://images.unsplash.com/photo-1606107557195-0e29a4b5b4aa?w=800',
'Responsive cushioning for daily runs', 139.99, 60,
'550e8400-e29b-41d4-a716-446655440003', '750e8400-e29b-41d4-a716-446655440031', '650e8400-e29b-41d4-a716-446655440003',
295, 0, 'Pegasus', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('850e8400-e29b-41d4-a716-446655440054', 'Adidas Ultraboost 23', 'adidas-ultraboost-23',
'https://images.unsplash.com/photo-1608231387042-66d1773070a5?w=800',
'Energy-returning running shoes', 189.99, 45,
'550e8400-e29b-41d4-a716-446655440006', '750e8400-e29b-41d4-a716-446655440031', '650e8400-e29b-41d4-a716-446655440003',
310, 0, 'Ultraboost', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);