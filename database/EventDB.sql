create database EventDB;
use EventDB;
CREATE TABLE Admin (
	admin_id INT, -- new added
    username VARCHAR(100),
    name VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    mobile long NOT NULL,
    PRIMARY KEY (admin_id) -- altered
	-- PRIMARY KEY (username)
);
CREATE TABLE Vendor (
    vendor_id INT,
    username VARCHAR(100), -- new added 
    password VARCHAR(100) NOT NULL, -- new added
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    contact_no INT NOT NULL,
    PRIMARY KEY (vendor_id)
);
CREATE TABLE User (
    user_id INT PRIMARY KEY,
    name varchar(100) not null,
    username varchar(100) not null,
    password varchar(100) not null,
    date_joining DATE not null,
    birth_date DATE not null,
    department varchar(100) not null,
    mobile long not null,
    email varchar(100) not null,
    location varchar(255) not null,
    status boolean not null DEFAULT false
);
create table ServiceList(
	service_id int primary key,
    service_name varchar(100) not null,
    service_cost float not null,
    service_type varchar(15) not null
);
CREATE TABLE PlanRequest (
    plan_request_id INT PRIMARY KEY,
    from_date DATE not null,
    to_date DATE not null,
    no_of_persons INT not null,
    service_id INT not null,
    FOREIGN KEY (service_id) REFERENCES ServiceList(service_id),
    CONSTRAINT check_no_of_persons CHECK (no_of_persons>30)
);
CREATE TABLE Quotation (
    quotation_id INT, -- altered
    -- id INT,
    package_type VARCHAR(10) not null,
    estimated_amount DOUBLE not null,
    vendor_id INT not null,
    user_id INT not null,
    plan_request_id INT not null,
    status BOOLEAN not null,
    PRIMARY KEY (id),
    FOREIGN KEY (vendor_id)
		REFERENCES Vendor (vendor_id),
    FOREIGN KEY (user_id)
        REFERENCES User (user_id),
    FOREIGN KEY (plan_request_id)
        REFERENCES PlanRequest(plan_request_id)
);
create table Package(
	package_id int primary key,
    package_name varchar(100) not null,
    amount float not null,
    vendor_id int not null,
    foreign key (vendor_id) references Vendor(vendor_id)
);


create table ServicePackageRef(
	package_id int not null,
    service_id int not null,
     FOREIGN KEY (package_id) REFERENCES Package(package_id),
    FOREIGN KEY (service_id) REFERENCES ServiceList(service_id)
);

CREATE TABLE RequestServiceRef (
    plan_request_id INT not null,
    service_id INT not null,
    FOREIGN KEY (plan_request_id) REFERENCES PlanRequest(plan_request_id),
    FOREIGN KEY (service_id) REFERENCES ServiceList(service_id)
);