CREATE SCHEMA %1$s;

CREATE TABLE %1$s.STAFF (
  ID               BIGINT      PRIMARY KEY AUTO_INCREMENT,
  NAME             VARCHAR(30) NOT NULL,
  AGE              INT         NOT NULL
);

CREATE INDEX staff_age
  ON %1$s.STAFF (AGE);

CREATE TABLE %1$s.DEPARTMENTS (
  ID               BIGINT      PRIMARY KEY AUTO_INCREMENT,
  DEPARTMENT_NAME  VARCHAR(50) NOT NULL,
  DISTRICT_NAME    VARCHAR(50) NOT NULL
);

CREATE INDEX depart_distict
  ON %1$s.DEPARTMENTS (DISTRICT_NAME);

CREATE TABLE %1$s.EMPLOYEES (
  FK_STAFF_ID      BIGINT      NOT NULL,
  FK_DEPARTMENT_ID BIGINT      NOT NULL,
    PRIMARY KEY(FK_STAFF_ID, FK_DEPARTMENT_ID),
    CONSTRAINT FK_STAFF_EMPL FOREIGN KEY (FK_STAFF_ID)
      REFERENCES STAFF(ID),
    CONSTRAINT FK_DEPART_EMPL FOREIGN KEY (FK_DEPARTMENT_ID)
      REFERENCES DEPARTMENTS(ID)
);
