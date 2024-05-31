create table BRANDS
(
    BRAND_ID SMALLINT       default 0       not null,
    NAME     CHAR(35)       default ' '     not null,

    constraint PK_BRANDS
        primary key (BRAND_ID)
);