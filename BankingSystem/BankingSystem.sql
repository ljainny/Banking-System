 create table account_master
 (account_id number(10) Primary Key,
 account_type varchar2(25),
 account_balance number(15),
 open_date date);


create table customer
(account_id number(10) references account_master(account_id),
customer_name varchar2(50),
email varchar2(30),
address varchar2(100),
pancard varchar2(15));


 create table transactions
 (transaction_id number,
 tran_description varchar2(100),
 dateoftransaction date,
 transactiontype varchar2(20),
 tranamount number(15),
 account_id number(10) references account_master(account_id),
 payee_account_id number(10),
 balance number(15));


create table servicetracker
(service_id number primary key,
service_description varchar2(100),
account_id number references account_master(account_id),
service_raised_date date,
service_status varchar2(20));


 create table user_table
 (account_id number references account_master(account_id),
 login_password varchar2(15),
 secret_question varchar2(50),
 lock_status varchar2(7));


create table fund_transfer
(fund_transfer_id number primary key,
account_id number(10) references account_master(account_id),
payee_account_id number(10),
date_of_transfer date,
transfer_amount number(15));

create sequence service_seq
start with 1000
increment by 1;


create sequence accNo_seq
start with 1000;


 create sequence trans_seq
 start with 1000;

 create sequence fund_seq
 start with 1000;
 
 