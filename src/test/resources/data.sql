CREATE TABLE customer (
                          customer_id int NOT NULL AUTO_INCREMENT,
                          store_id int NOT NULL,
                          first_name varchar(45) NOT NULL,
                          last_name  varchar(45) NOT NULL,
                          email varchar(50),
                          address_id smallint NOT NULL,
                          activebool boolean NOT NULL,
                          create_date date,
                          last_update timestamp,
                          active int,
                          PRIMARY KEY (customer_id)
);

CREATE TABLE payment (
                         payment_id int NOT NULL AUTO_INCREMENT,
                         customer_id smallint NOT NULL,
                         staff_id smallint NOT NULL,
                         rental_id int NOT NULL,
                         amount numeric(5,2),
                         payment_date timestamp NOT NULL,
                         PRIMARY KEY (payment_id)
);

INSERT INTO customer(store_id, first_name, last_name, email, address_id, activebool, create_date, last_update, active)
VALUES(1, 'John','Doe','jd@gmail.com', 1, true, '2006-02-14', {ts '2012-09-17 18:47:52.69'}, 1);
INSERT INTO customer(store_id, first_name, last_name, email, address_id, activebool, create_date, last_update, active)
VALUES(2, 'Jack','Sparrow','js@gmail.com', 1, true, '2006-02-15', {ts '2012-09-17 18:47:52.69'}, 1);
INSERT INTO customer(store_id, first_name, last_name, email, address_id, activebool, create_date, last_update, active)
VALUES(3, 'Mary','Smith','ms@gmail.com', 1, true, '2006-02-16', {ts '2012-09-17 18:47:52.69'}, 1);

INSERT INTO payment(payment_id, customer_id, staff_id, rental_id, amount,payment_date)
VALUES(1,1,1,1,5.99,{ts '2012-09-17 18:47:52.69'});
INSERT INTO payment(payment_id, customer_id, staff_id, rental_id, amount,payment_date)
VALUES(2,1,1,1,5.99,{ts '2012-09-17 18:47:52.69'});
INSERT INTO payment(payment_id, customer_id, staff_id, rental_id, amount,payment_date)
VALUES(3,2,1,1,5.99,{ts '2012-09-17 18:47:52.69'});
INSERT INTO payment(payment_id, customer_id, staff_id, rental_id, amount,payment_date)
VALUES(4,2,1,1,5.99,{ts '2012-09-17 18:47:52.69'});
INSERT INTO payment(payment_id, customer_id, staff_id, rental_id, amount,payment_date)
VALUES(5,3,1,1,5.99,{ts '2012-09-17 18:47:52.69'});