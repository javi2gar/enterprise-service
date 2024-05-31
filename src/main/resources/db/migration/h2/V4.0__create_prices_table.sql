create table PRICES
(
    BRAND_ID    SMALLINT        default 0 not null                  ,
    START_DATE  TIMESTAMP       default CURRENT_TIMESTAMP   not null,
    END_DATE    TIMESTAMP                                           ,
    PRICE_LIST  BIGINT          default 0 not null                  ,
    PRODUCT_ID  BIGINT          default 0 not null                  ,
    PRIORITY    NUMERIC(1)      default 1 Not null                  ,
    PRICE       NUMERIC(20,2)   default 0 not null                  ,
    CURR        VARCHAR(3)         default 'EUR' not null              ,
    constraint PK_PRICES
        primary key (BRAND_ID, PRICE_LIST, PRODUCT_ID)
);

alter table PRICES
add foreign key (BRAND_ID)
references BRANDS(BRAND_ID);

alter table PRICES
add foreign key (PRICE_LIST)
references FEES(FEE_ID);

alter table PRICES
add foreign key (PRODUCT_ID)
references PRODUCTS(PRODUCT_ID);
