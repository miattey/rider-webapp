CREATE TABLE Booking (
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
  customer_id INT NOT NULL,
  start VARCHAR(60) NOT NULL,
  destination VARCHAR(60) NOT NULL,
  distance DOUBLE NOT NULL DEFAULT 1,
  date DATE NOT NULL,
  time TIME NOT NULL,
  fee INT NOT NULL,
  driver_id INT DEFAULT NULL,
  status INT NOT NULL,
  PRIMARY KEY (id),
    FOREIGN KEY (customer_id)
    REFERENCES customer (id),
  );


CREATE TABLE users(
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  usertype VARCHAR(255) NOT NULL,
  first_name VARCHAR(45) NOT NULL,
  Last_name VARCHAR(45) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE customer (
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
  address VARCHAR(60) NOT NULL,
  user_id INT NOT NULL,
  PRIMARY KEY (id),
    FOREIGN KEY (user_id)
    REFERENCES users(id)
  );


CREATE TABLE driver (
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
  registration VARCHAR(10) NOT NULL,
  user_id INT NOT NULL,
  PRIMARY KEY (id),
    FOREIGN KEY (user_id)
    REFERENCES users(id)
    );


CREATE TABLE admin (
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
  user_id INT NOT NULL,
  PRIMARY KEY (id),
    FOREIGN KEY (user_id)
    REFERENCES users(id)
  );


CREATE TABLE Fare (
    id INT NOT NULL GENERATED ALWAYS AS IDENTITY
          (START WITH 1, INCREMENT BY 1),
    gst DOUBLE NOT NULL,
    price_per_kilometer DOUBLE NOT NULL,
    base_price DOUBLE NOT NULL,
    PRIMARY KEY (id)
    );

