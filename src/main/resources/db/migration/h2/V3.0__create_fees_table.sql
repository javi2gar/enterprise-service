create table FEES
(
    FEE_ID BIGINT           default ' '     not null,
    NAME    VARCHAR(35)        default ' '     not null,
    AMOUNT  NUMERIC(20,2)   default 0       not null,

    constraint PK_FEE
        primary key (FEE_ID)
);