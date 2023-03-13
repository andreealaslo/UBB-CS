
IF OBJECT_ID('Ta', 'U') IS NOT NULL
	DROP TABLE Ta

IF OBJECT_ID('Tb', 'U') IS NOT NULL
	DROP TABLE Tb

IF OBJECT_ID('Tc', 'U') IS NOT NULL
	DROP TABLE Tc


CREATE TABLE Ta(
	aid INT PRIMARY KEY,
	a2 INT UNIQUE,
	aux_a INT
);

CREATE TABLE Tb(
	bid INT PRIMARY KEY,
	b2 INT,
	aux_b INT
);

CREATE TABLE Tc(
	cid INT PRIMARY KEY,
	aid INT FOREIGN KEY references Ta(aid),
	bid INT FOREIGN KEY references Tb(bid),
	aux_c INT
);



---we need to generate and insert some random data in the tables, as for tables with small amount of data the indexes are useless

---insert into Ta
GO
CREATE OR ALTER PROCEDURE insertIntoTa(@nr_rows INT) AS
BEGIN
	DECLARE @aid INT
	SET @aid = 1
	DECLARE @another_value INT
	SET @another_value = 10
	WHILE @nr_rows>0
	BEGIN
		INSERT into Ta values (@aid, @nr_rows, @another_value)
		SET @aid = @aid + 1
		SET @nr_rows =@nr_rows - 1
		SET @another_value = @another_value+100
	END
END

---insert into Tb
GO
CREATE OR ALTER PROCEDURE insertIntoTb(@nr_rows INT) AS
BEGIN
	DECLARE @bid INT
	SET @bid = 10
	DECLARE @another_value INT
	SET @another_value = 10
	WHILE @nr_rows>0
	BEGIN
		INSERT into Tb values (@bid, @nr_rows%10, @another_value)
		SET @bid = @bid + 1
		SET @nr_rows =@nr_rows - 1
		SET @another_value = @another_value+15
	END
END

---insert into Tc
GO
CREATE OR ALTER PROCEDURE insertIntoTc(@nr_rows INT) AS
BEGIN
	DECLARE @cid INT
	DECLARE @aid INT
	DECLARE @bid INT
	DECLARE @another_value INT
	SET @another_value = 12
	SET @cid = 2
	WHILE @nr_rows>0
	BEGIN
		SET @aid = (SELECT TOP 1 aid FROM Ta ORDER BY NEWID())
		SET @bid = (SELECT TOP 1 bid FROM Tb ORDER BY NEWID())
		INSERT into Tc values (@cid, @aid, @bid, @another_value)
		SET @cid = @cid + 1
		SET @nr_rows =@nr_rows - 1
		SET @another_value = @another_value+13
	END
END

EXEC insertIntoTa 1000
EXEC insertIntoTb 1000
EXEC insertIntoTc 1000

SELECT * FROM Tb
SELECT * FROM Tc


---a. Write queries on Ta such that their execution plans contain the following operators:
---clustered index scan =>scan the entire table
SELECT * FROM Ta   ---cost: 0.0058635

---clustered index seek =>returns a specific subset of rows from a clustered index 
SELECT * FROM Ta
WHERE aid < 300   ---cost: 0.0036109  

---nonclustered index scan
SELECT a2 FROM Ta
ORDER  BY a2 DESC  ---cost: 0.0051227   ---scan the entire nonclustered index

---nonclustered index seek
SELECT a2 FROM Ta
WHERE a2 > 300     ---cost: 0.0047927    

---key lookup
SELECT a2, aux_a FROM Ta
WHERE a2 = 300	   ---cost: 0.0032831(index seek) + 0.0032831 (key lookup) = 0.0065662


---b. Write a query on table Tb with a WHERE clause of the form WHERE b2 = value and analyze its execution plan. 
---Create a nonclustered index that can speed up the query. Examine the execution plan again.
SELECT * FROM Tb
WHERE b2 = 8    ---cost: 0.0058635  cost for clustered index scan
GO
DROP Index Tb_b2_indx ON Tb
GO
CREATE INDEX Tb_b2_indx ON Tb(b2)   ---cost after creating the nonclustered index: 0.00493866

---c. Create a view that joins at least 2 tables. Check whether existing indexes are helpful; 
---if not, reassess existing indexes / examine the cardinality of the tables.


GO
CREATE OR ALTER VIEW View1 AS
	SELECT A.aid, B.bid, C.cid
	FROM Tc C
	LEFT JOIN Ta A ON A.aid = C.aid
	left JOIN Tb B ON B.bid = C.bid
	WHERE B.b2 = 1 AND A.aux_a > 1

GO
SELECT *
FROM View1

---with existing indexes, cost: 0.0222987
DROP INDEX Tc_index ON Tc
CREATE NONCLUSTERED INDEX Tc_index ON Tc(aid, bid)  ---with this additional nonclustered index : 0.0252816
