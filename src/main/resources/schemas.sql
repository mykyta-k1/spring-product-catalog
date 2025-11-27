CREATE TABLE users
(
  id            UUID PRIMARY KEY         DEFAULT gen_random_uuid(),
  email         VARCHAR(255) UNIQUE,
  password_hash VARCHAR(128)                            NOT NULL,
  first_name    VARCHAR(100),
  last_name     VARCHAR(100),
  patronymic    VARCHAR(100),
  is_active     BOOLEAN                  DEFAULT false  NOT NULL,
  role          user_role                DEFAULT 'USER' NOT NULL,
  last_login_at TIMESTAMP WITH TIME ZONE,
  created_at    TIMESTAMP WITH TIME ZONE DEFAULT NOW()  NOT NULL,
  updated_at    TIMESTAMP WITH TIME ZONE DEFAULT NOW()  NOT NULL
);

CREATE TABLE categories
(
  id         UUID PRIMARY KEY         DEFAULT gen_random_uuid(),
  parent_id  UUID                                   REFERENCES categories (id) ON DELETE SET NULL,
  name       VARCHAR(100) UNIQUE                    NOT NULL,
  slug       VARCHAR(100) UNIQUE                    NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,
  updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL
);

CREATE TABLE products
(
  id              UUID PRIMARY KEY         DEFAULT gen_random_uuid(),
  name            VARCHAR(255)                           NOT NULL,
  slug            VARCHAR(150) UNIQUE                    NOT NULL,
  image_url       VARCHAR(255),
  description     TEXT,
  price           NUMERIC(10, 2)                         NOT NULL CHECK (price >= 0),
  stock           INTEGER                  DEFAULT 0 CHECK (stock >= 0),
  brand_id        UUID REFERENCES brands (id),
  category_id     UUID REFERENCES categories (id),
  product_type_id UUID REFERENCES product_types (id),
  weight_grams    INTEGER                                NOT NULL CHECK (weight_grams > 0),
  volume_ml       INTEGER                                NOT NULL CHECK (volume_ml > 0),
  series          VARCHAR(255),
  created_at      TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,
  updated_at      TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL
);

CREATE TABLE brands
(
  id         UUID PRIMARY KEY         DEFAULT gen_random_uuid(),
  name       VARCHAR(255) UNIQUE                    NOT NULL,
  slug       VARCHAR(100) UNIQUE                    NOT NULL,
  logo_url   VARCHAR(255),
  banner_url VARCHAR(255),
  created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,
  updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL
);

CREATE TABLE product_types
(
  id         UUID PRIMARY KEY         DEFAULT gen_random_uuid(),
  name       VARCHAR(100)                           NOT NULL,
  slug       VARCHAR(100) UNIQUE                    NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,
  updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL
);
