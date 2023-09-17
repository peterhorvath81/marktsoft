INSERT INTO p_owner (EMAIL, NAME, PHONE_NUMBER) VALUES ('john@email.com', 'John', '1234');
INSERT INTO p_owner (EMAIL, NAME, PHONE_NUMBER) VALUES ('mary@email.com', 'Mary', '4321');
INSERT INTO p_owner (EMAIL, NAME, PHONE_NUMBER) VALUES ('charles@email.com', 'Charles', '2345');
INSERT INTO p_owner (EMAIL, NAME, PHONE_NUMBER) VALUES ('otto@email.com', 'Otto', '3456');
INSERT INTO p_owner (EMAIL, NAME, PHONE_NUMBER) VALUES ('frank@email.com', 'Frank', '9876');
INSERT INTO p_owner (EMAIL, NAME, PHONE_NUMBER) VALUES ('timmy@email.com', 'Timmy', '8765');
INSERT INTO p_owner (EMAIL, NAME, PHONE_NUMBER) VALUES ('cartman@email.com', 'Cartman', '7654');
INSERT INTO p_owner (EMAIL, NAME, PHONE_NUMBER) VALUES ('kenny@email.com', 'Kenny', '6543');

INSERT INTO p_pet (NAME, SPECIES) VALUES ('Tom', 'Cat');
INSERT INTO p_pet (NAME, SPECIES) VALUES ('Jerry', 'Mouse');
INSERT INTO p_pet (NAME, SPECIES) VALUES ('Butch', 'Dog');

INSERT INTO owner_pet(P_OWNER_ID, P_PET_ID) VALUES (1,1);
INSERT INTO owner_pet(P_OWNER_ID, P_PET_ID) VALUES (2,1);
INSERT INTO owner_pet(P_OWNER_ID, P_PET_ID) VALUES (3,2);
INSERT INTO owner_pet(P_OWNER_ID, P_PET_ID) VALUES (5,2);
INSERT INTO owner_pet(P_OWNER_ID, P_PET_ID) VALUES (8,3);