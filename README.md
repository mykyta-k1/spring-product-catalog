# 📦 Product Management Application (Spring Data JPA)

Цей проєкт є навчальним веб-додатком, розробленим на **Spring Boot** та **Spring Data JPA**, для демонстрації ефективної роботи з даними. Додаток імітує частину функціоналу інтернет-магазину, зосереджуючись на керуванні продуктами та категоріями з розширеними можливостями пошуку, фільтрації та пагінації.

## 🚀 Основні технології

* **Backend:** Java 17, Spring Boot
* **Data Access:** **Spring Data JPA**, Hibernate
* **Database:** **H2 Database (In-Memory)** — база даних, що працює в пам'яті, ідеально підходить для розробки та тестування.
* **REST API:** Spring Web, Swagger/OpenAPI для документації API
* **Templating:** Thymeleaf
* **Build Tool:** Maven

---

## ✨ Ключовий функціонал та особливості

Додаток реалізує повний цикл роботи з даними (CRUD) та демонструє просунуті можливості фреймворку Spring Data.

1.  **Архітектура:** Проєкт побудований за принципом **трирівневої архітектури** (Контролери $\rightarrow$ Сервіси $\rightarrow$ Репозиторії), що забезпечує чистоту коду та легкість підтримки.
2.  **Моделювання даних:** Створені сутності (`Product`, `Category`, `Brand` та ін.) з коректно налаштованими **JPA-зв'язками**, включаючи реалізацію **ієрархічної структури** для категорій.
3.  **Пагінація та Сортування:** Реалізовано ефективне керування великими обсягами даних за допомогою **пагінації** (Spring Data `Page`) та **динамічного сортування** продуктів за різними критеріями.
4.  **Просунуті Пошук та Фільтрація (Ключова Особливість):**
    * Реалізовано **динамічне** фільтрування за кількома параметрами (категорія, бренд, ціновий діапазон).
    * **Специфікація:** Для реалізації гнучкого пошуку та фільтрації використовувався механізм **JPA Specifications** (через інтерфейс **`JpaSpecificationExecutor`**), що дозволяє комбінувати складні критерії запиту.
5.  **REST API та документація:**
    * Реалізовано **RESTful API** для роботи з продуктами (`ProductRestController`) з повним набором CRUD операцій (GET, POST, PUT, DELETE).
    * Підтримка фільтрації за ключовими словами та ціновим діапазоном через `@RequestParam`.
    * Використання `slug` замість ID для створення читабельних та SEO-оптимізованих URI.
    * **Swagger UI** для інтерактивного тестування та документації API endpoints (доступний за адресою `/swagger-ui/index.html`).
6.  **Централізована обробка винятків:**
    * Реалізовано глобальний обробник помилок через **`@ControllerAdvice`**, що забезпечує уніфіковане форматування відповідей при помилках.
    * Структуровані об'єкти помилок містять: timestamp, HTTP статус, повідомлення, шлях запиту та додаткові деталі для зручності налагодження.
    * Коректне повернення HTTP статус-кодів: 200 (OK), 201 (Created), 204 (No Content), 400 (Bad Request), 404 (Not Found), 451 (Unavailable For Legal Reasons).
7.  **Оптимізація коду:** Для автоматизації конвертації даних між сутностями та DTO використовувалася бібліотека **MapStruct**.
8.  **Фронтенд:** Розроблений простий користувацький інтерфейс на **Thymeleaf** для візуальної демонстрації всього функціоналу.

---

## 🛠️ Структура бази даних

Оскільки використовується H2 (In-Memory), структура таблиць автоматично генерується **Hibernate** на основі JPA-сутностей при запуску додатку:

```sql
-- Таблиці автоматично генеруються на основі JPA-сутностей.
-- Ключові зв'язки:
-- categories: самопосилання для ієрархії (parent_id)
-- products: зв'язки @ManyToOne з category_id, brand_id, product_type_id
CREATE TABLE users (
   id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
   email VARCHAR(255) UNIQUE,
   password_hash VARCHAR(128) NOT NULL,
   first_name VARCHAR(100),
   last_name VARCHAR(100),
   patronymic VARCHAR(100),
   is_active BOOLEAN DEFAULT false NOT NULL,
   role user_role DEFAULT 'USER' NOT NULL,
   last_login_at TIMESTAMP WITH TIME ZONE,
   created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,
   updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL
);

CREATE TABLE categories (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    parent_id UUID REFERENCES categories(id) ON DELETE SET NULL,
    name VARCHAR(100) UNIQUE NOT NULL,
    slug VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL
);

CREATE TABLE products (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    slug VARCHAR(150) UNIQUE NOT NULL,
    image_url VARCHAR(255),
    description TEXT,
    price NUMERIC(10,2) NOT NULL CHECK (price >= 0),
    stock INTEGER DEFAULT 0 CHECK (stock >= 0),
    brand_id UUID REFERENCES brands(id),
    category_id UUID REFERENCES categories(id),
    product_type_id UUID REFERENCES product_types(id),
    weight_grams INTEGER NOT NULL CHECK (weight_grams > 0),
    volume_ml INTEGER NOT NULL CHECK (volume_ml > 0),
    series VARCHAR(255),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL
);

CREATE TABLE brands (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) UNIQUE NOT NULL,
    slug VARCHAR(100) UNIQUE NOT NULL,
    logo_url VARCHAR(255),
    banner_url VARCHAR(255),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL
);

CREATE TABLE product_types (
   id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
   name VARCHAR(100) NOT NULL,
   slug VARCHAR(100) UNIQUE NOT NULL,
   created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,
   updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL
);
```

---

## 🔌 REST API Endpoints

Додаток надає REST API для роботи з продуктами:

* **GET** `/api/v1/products` — отримання посторінкового списку продуктів з фільтрацією
* **GET** `/api/v1/products/{slug}` — отримання конкретного продукту за slug
* **POST** `/api/v1/products` — створення нового продукту
* **PUT** `/api/v1/products?slug={slug}` — оновлення існуючого продукту
* **DELETE** `/api/v1/products/{slug}` — видалення продукту

**Документація API:** `http://localhost:8080/swagger-ui/index.html`

---

## ▶️ Запуск проєкту

**Вимоги:**
JDK 17+

**Кроки:**

**1. Клонуйте репозиторій:**

```bash
git clone git@github.com:mykyta-k1/spring-product-catalog.git
cd spring-product-catalog
```

**2. Запустіть додаток:**
Оскільки використовується H2 In-Memory, додаткового налаштування зовнішньої бази даних не потрібно.

```bash
# Використовуючи Maven
./mvnw spring-boot:run
```

**3. Доступ до додатку:**
* **Веб-інтерфейс:** `http://localhost:8080`
* **Swagger UI (API документація):** `http://localhost:8080/swagger-ui/index.html`
