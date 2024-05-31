create table PRODUCTS
(
    PRODUCT_ID      BIGINT                          not null,
    BRAND_ID        SMALLINT                        not null,
    DESCRIPTION     VARCHAR(30)        default ' '     not null,
    PURCHASE_VALUE  NUMERIC(20, 2)  ,
    constraint PK_PRODUCTS
        primary key (PRODUCT_ID)
);

alter table PRODUCTS
add foreign key (BRAND_ID)
references BRANDS(BRAND_ID);


