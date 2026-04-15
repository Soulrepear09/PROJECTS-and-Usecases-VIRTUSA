DROP DATABASE swiftship_db;
CREATE DATABASE swiftship_db;
USE swiftship_db;

CREATE DATABASE IF NOT EXISTS swiftship_db;
USE swiftship_db;

CREATE TABLE Partners (
    PartnerID INT PRIMARY KEY AUTO_INCREMENT,
    PartnerName VARCHAR(50) NOT NULL,
    State VARCHAR(50) NOT NULL,
    City VARCHAR(50) NOT NULL,
    MobileNo VARCHAR(15)
);

INSERT INTO Partners (PartnerName, State, City, MobileNo) VALUES
('Ekart', 'Maharastra', 'Mumbai', '9333100011'),
('AMZ Logistics', 'Maharastra', 'Pune', '9333100012'),
('Ecom Couriers', 'Telangana', 'Hyderabad', '9333100013'),
('DTDC', 'Tamilnadu', 'Chennai', '9333100014'),
('RedKart', 'Karnataka', 'Bengaluru', '9333100015'),
('Xpress Courier', 'Kerela', 'Kochi', '9333100016'),
('Fastx', 'West Bengal', 'Kolkata', '9333100017');

SELECT * FROM Partners;

CREATE TABLE Shipments (
	ShipmentID 	  INT PRIMARY KEY AUTO_INCREMENT,
    CustomerName  VARCHAR(100) NOT NULL,
    Destination   VARCHAR(100) NOT NULL,
    PartnerID     INT NOT NULL,
    Orderdate     DATE NOT NULL,
    DeliveryDate  Date NOT NULL,
    FOREIGN KEY(PartnerID) REFERENCES Partners(PartnerID)
);

INSERT INTO Shipments(CustomerName, Destination, PartnerID, Orderdate, DeliveryDate) VALUES
('Amith Tiwari', 'Hyderabad', 3, '2025-03-01', '2025-03-04' ),
('Prerana', 'Chennai', 4, '2025-03-02', '2025-03-06' ),
('Ram Kumar', 'Mumbai', 1, '2025-03-03', '2025-03-07' ),
('Deepak Nair', 'Kochi', 6, '2025-03-04', '2025-03-08' ),
('Anand Agarwal', 'kolkata', 7, '2025-03-05', '2025-03-09' ),
('Ranveer', 'Mumbai', 1, '2025-03-06', '2025-03-10' ),
('Swathi Reddy', 'Hyderabad', 3, '2025-03-07', '2025-03-11' ),
('kiran kumar', 'Pune', 2, '2025-03-08', '2025-03-12' ),
('Alia', 'Bengaluru', 5, '2025-03-09', '2025-03-13' ),
('Vikram', 'Chennai', 4, '2025-03-10', '2025-03-14' ),
('Meena kumari', 'Kochi', 6, '2025-03-11', '2025-03-15' ),
('Ananya', 'Bengaluru', 5, '2025-03-12', '2025-03-16' );

SELECT * FROM Shipments;

CREATE TABLE DeliveryLogs (
	logID                INT PRIMARY KEY AUTO_INCREMENT,
    ShipmentID           INT NOT NULL,
    ActualDeliveryDate   DATE NOT NULL,
    Status               VARCHAR(15) NOT NULL,
    FOREIGN KEY (ShipmentID) REFERENCES Shipments(ShipmentID)
);

INSERT INTO DeliveryLogs (ShipmentID, ActualDeliveryDate, Status) VALUES
(1, '2025-03-04', 'Successful'),
(2, '2025-03-07', 'Successful'),
(3, '2025-03-08', 'Returned'),
(4, '2025-03-10', 'Returned'),
(5, '2025-03-09', 'Successful'),
(6, '2025-03-13', 'Returned'),
(7, '2025-03-11', 'Successful'),
(8, '2025-03-12', 'Successful'),
(9, '2025-03-15', 'Returned'),
(10, '2025-03-16', 'Returned'),
(11, '2025-03-15', 'Successful'),
(12, '2025-03-16', 'Successful');

SELECT * FROM DeliveryLogs;

# Delayed Shipment query
USE swiftship_db;

SELECT
s.ShipmentID,
s.CustomerName,
s.Destination,
s.DeliveryDate,
d.ActualDeliveryDate,
DATEDIFF(d.ActualDeliveryDate, s.DeliveryDate) AS DelayDays
FROM Shipments s
JOIN DeliveryLogs d ON s.ShipmentID=d.ShipmentID
Where d.ActualDeliveryDate > s.DeliveryDate;

#Performance 
SELECT 
p.PartnerName,
p.City,
d.Status,
COUNT(*) As TotalShipments
From Partners p
JOIN Shipments s ON p.PartnerID = s.PartnerID
JOIN DeliveryLogs d ON s.ShipmentID = d.ShipmentID
GROUP BY p.PartnerName, p.City, d.status
ORDER BY p.PartnerName;

SELECT
p.PartnerName,
p.City,
p.State,
SUM(d.Status = 'Successful') AS Successful,
SUM(d.Status = 'Returned') AS Returned,
COUNT(CASE WHEN d.ActualDeliveryDate > s.DeliveryDate THEN 1 END) AS DelayCount,
ROUND(
        SUM(d.Status = 'Successful') * 100 / COUNT(*),
    1) AS Successrate
FROM Partners p
JOIN Shipments s ON p.PartnerID=s.PartnerID
JOIN DeliveryLogs d ON s.ShipmentID=d.ShipmentID
GROUP BY p.PartnerName, p.City, p.State
ORDER BY Successrate ASC;

#ZoneFilter
SELECT
Destination,
COUNT(*) AS Totalnumberoforders
FROM Shipments
GROUP BY Destination
ORDER BY Totalnumberoforders DESC;

