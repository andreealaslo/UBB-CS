
-------------------------------------------------------A1------------------------------------------------------------------

CREATE TABLE Country(
	country_id INT PRIMARY KEY,
	country_name VARCHAR(15)
);
ALTER TABLE Country ALTER COLUMN country_id INT NOT NULL
---1:n one team has a single mother-country, but in a country there are multiple teams
CREATE TABLE Team(
	team_id INT PRIMARY KEY,
	team_name VARCHAR(50),
	team_country INT FOREIGN KEY REFERENCES Country(country_id) ON DELETE CASCADE ON UPDATE CASCADE,
    team_appearance_year INT,
	team_coach VARCHAR(50),
	team_staff_people INT
);
---1:n one player can play in a single team, but a team has multiple players
CREATE TABLE Player(
	player_id INT PRIMARY KEY,
	player_name VARCHAR(100),
	current_team INT FOREIGN KEY REFERENCES Team(team_id) ON DELETE CASCADE ON UPDATE CASCADE,
	player_year_of_birth INT,
	player_salary BIGINT,
	player_number SMALLINT,
	player_position VARCHAR(20)
);

CREATE TABLE Championship(
	championship_id INT PRIMARY KEY,
	championship_name VARCHAR(50),
	championship_country INT FOREIGN KEY REFERENCES Country(country_id) ON DELETE CASCADE ON UPDATE CASCADE,
	number_of_teams INT
);

CREATE TABLE Teams_in_Championship(
	team_id INT FOREIGN KEY REFERENCES Team(team_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
	championship_id INT FOREIGN KEY REFERENCES Championship(championship_id) ON DELETE CASCADE ON UPDATE CASCADE,
	UNIQUE (team_id, championship_id),
	place_in_championship TINYINT
);

CREATE TABLE Competition(
	competition_id INT PRIMARY KEY,
	competition_name VARCHAR(100),
	competition_number_of_steps INT
);
--- n:m one team in multiple competition, multiple teams in one competition
CREATE TABLE Teams_in_Competition(
	team_id INT FOREIGN KEY REFERENCES Team(team_id) ON DELETE CASCADE ON UPDATE CASCADE,
	competition_id INT FOREIGN KEY REFERENCES Competition(competition_id) ON DELETE CASCADE ON UPDATE CASCADE,
	UNIQUE (team_id, competition_id),
	year_of_competition INT
);

CREATE TABLE Winner_of_Competition(
	winner_team_id INT FOREIGN KEY REFERENCES Team(team_id) ON DELETE CASCADE ON UPDATE CASCADE,
	competition_id INT FOREIGN KEY REFERENCES Competition(competition_id) ON DELETE CASCADE ON UPDATE CASCADE,
	year_of_competition INT,
	UNIQUE (winner_team_id, competition_id, year_of_competition)
);

CREATE TABLE Goals_of_Player_in_Team(
	player_id INT FOREIGN KEY REFERENCES Player(player_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
	team_id INT FOREIGN KEY REFERENCES Team(team_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
	number_of_goals INT
);

CREATE TABLE Player_of_the_Year(
	best_player_id INT FOREIGN KEY REFERENCES Player(player_id) ON DELETE CASCADE ON UPDATE CASCADE,
	best_year INT,
	best_player_bonus INT
	UNIQUE(best_player_id , best_year)
);

CREATE TABLE Sponsor(
	sponsor_id INT PRIMARY KEY,
	sponsor_name VARCHAR(50),
	sponsor_amount_given BIGINT
);
--n:m - a team has multiple sponsors, multiple teams can be sponsored by the same one
CREATE TABLE Sponsor_on_Team(
	sponsor_id INT FOREIGN KEY REFERENCES Sponsor(sponsor_id) ON DELETE CASCADE ON UPDATE CASCADE,
	sponsored_team_id INT FOREIGN KEY REFERENCES Team(team_id) ON DELETE CASCADE ON UPDATE CASCADE,
	UNIQUE(sponsor_id, sponsored_team_id)
);

----------------------------------------------------------------------A2----------------------------------------------------------------------------

---INSERT STATEMENTS

INSERT INTO Country(country_id, country_name) VALUES (1, 'Germany')
INSERT INTO Country(country_id, country_name) VALUES (2, 'Italy'), (3, 'Romania'), (4, 'Spain'), (5, 'France'), (6, 'England'), (7, 'Poland')

INSERT INTO Team(team_id, team_name, team_country, team_appearance_year, team_coach, team_staff_people) VALUES (1,'Bayern Munchen',1,1920,'Julian Nagelsmann',350)
INSERT INTO Team(team_id, team_name, team_country, team_appearance_year, team_coach, team_staff_people) VALUES (2,'CFR',3,1907,'Dan Petrescu',120)
INSERT INTO Team(team_id, team_name, team_country, team_appearance_year, team_coach, team_staff_people) VALUES (3,'FCSB',3,1947,'Nicolae Dica',130)
INSERT INTO Team(team_id, team_name, team_country, team_appearance_year, team_coach, team_staff_people) VALUES (4,'Borussia Dortmund',1,1909,'Edic Terzic',200)
INSERT INTO Team(team_id, team_name, team_country, team_appearance_year, team_coach, team_staff_people) VALUES (5,'FC Barcelona',4,1899,'Xavi',400)
INSERT INTO Team(team_id, team_name, team_country, team_appearance_year, team_coach, team_staff_people) VALUES (6,'Paris Saint-German',5,1970,'Mauricio Pochettino',360)
INSERT INTO Team(team_id, team_name, team_country, team_appearance_year, team_coach, team_staff_people) VALUES (7,'Manchester City',6,1970,'Josep Guardiola',290)
INSERT INTO Team(team_id, team_name, team_country, team_appearance_year, team_coach, team_staff_people) VALUES (8,'Real Madrid',4,1902,'Carlo Ancelotti',300)
INSERT INTO Team(team_id, team_name, team_country, team_appearance_year, team_coach, team_staff_people) VALUES (9,'Manchester United',6,1878,'Benni McCarthy',230)
INSERT INTO Team(team_id, team_name, team_country, team_appearance_year, team_coach, team_staff_people) VALUES (10,'Juventus Torino',2,1897,'Joe Montemurro',201)
INSERT INTO Team(team_id, team_name, team_country, team_appearance_year, team_coach, team_staff_people) VALUES (11,'Inter Milano',2,1908,'Simone Inzaghi',200)
INSERT INTO Team(team_id, team_name, team_country, team_appearance_year, team_coach, team_staff_people) VALUES (12,'FC Chelsea',6,1905,'Graham Potter',240)
INSERT INTO Team(team_id, team_name, team_country, team_appearance_year, team_coach, team_staff_people) VALUES (17,'FC Porto',8,1920,'Sergio Conceicao',190) --- insert that violates integrity constraints 

INSERT INTO Player(player_id, player_name, current_team, player_year_of_birth, player_salary, player_number, player_position) VALUES (1,'Robert Lewandowski',5,1988,23000000,9,'striker')
INSERT INTO Player(player_id, player_name, current_team, player_year_of_birth, player_salary, player_number, player_position) VALUES (2,'Lionel Messi',6,1987,41000000,30,'striker')
INSERT INTO Player(player_id, player_name, current_team, player_year_of_birth, player_salary, player_number, player_position) VALUES (3,'Neymar Jumnior',6,1992,40000000,10,'striker')
INSERT INTO Player(player_id, player_name, current_team, player_year_of_birth, player_salary, player_number, player_position) VALUES (4,'Erling Haaland',7,2000,48000000,9,'striker')
INSERT INTO Player(player_id, player_name, current_team, player_year_of_birth, player_salary, player_number, player_position) VALUES (5,'Cristian Manea',2,1997,20000,4,'defender')
INSERT INTO Player(player_id, player_name, current_team, player_year_of_birth, player_salary, player_number, player_position) VALUES (6,'Leon Goretzka',1,1995,12000000,8,'midfielder')
INSERT INTO Player(player_id, player_name, current_team, player_year_of_birth, player_salary, player_number, player_position) VALUES (7,'Karim Benzema',8,1987,8000000,9,'striker')
INSERT INTO Player(player_id, player_name, current_team, player_year_of_birth, player_salary, player_number, player_position) VALUES (8,'Cristiano Ronaldo',9,1985,26000000,7,'striker')
INSERT INTO Player(player_id, player_name, current_team, player_year_of_birth, player_salary, player_number, player_position) VALUES (9,'Luka Modric',8,1985,19000000,10,'midfielder')
INSERT INTO Player(player_id, player_name, current_team, player_year_of_birth, player_number, player_position) VALUES (10,'Kylian MBappe',6,1998,7,'striker')
INSERT INTO Player(player_id, player_name, current_team, player_year_of_birth, player_number, player_position) VALUES (11,'Kay Havertz',12,1999,29,'midfielder')

INSERT INTO Goals_of_Player_in_Team(player_id,team_id,number_of_goals) VALUES (6,1,25)
INSERT INTO Goals_of_Player_in_Team(player_id,team_id,number_of_goals) VALUES (2,6,23)
INSERT INTO Goals_of_Player_in_Team(player_id,team_id,number_of_goals) VALUES (2,5,672)
INSERT INTO Goals_of_Player_in_Team(player_id,team_id,number_of_goals) VALUES (1,1,344)
INSERT INTO Goals_of_Player_in_Team(player_id,team_id,number_of_goals) VALUES (1,5,18)
INSERT INTO Goals_of_Player_in_Team(player_id,team_id,number_of_goals) VALUES (4,4,16)
INSERT INTO Goals_of_Player_in_Team(player_id,team_id,number_of_goals) VALUES (4,7,17)

INSERT INTO Championship(championship_id,championship_name,championship_country,number_of_teams) VALUES (1,'Bundesliga',1,18)
INSERT INTO Championship(championship_id,championship_name,championship_country,number_of_teams) VALUES (2,'Ligue 1',5,20)
INSERT INTO Championship(championship_id,championship_name,championship_country,number_of_teams) VALUES (3,'Serie A',2,20)
INSERT INTO Championship(championship_id,championship_name,championship_country,number_of_teams) VALUES (4,'Liga 1',3,14)
INSERT INTO Championship(championship_id,championship_name,championship_country,number_of_teams) VALUES (5,'La Liga',4,20)
INSERT INTO Championship(championship_id,championship_name,championship_country,number_of_teams) VALUES (6,'Premier League',6,20)

INSERT INTO Teams_in_Championship(team_id, championship_id,place_in_championship) VALUES (1,1,1)
INSERT INTO Teams_in_Championship(team_id, championship_id,place_in_championship) VALUES (2,4,3)
INSERT INTO Teams_in_Championship(team_id, championship_id,place_in_championship) VALUES (3,4,8)
INSERT INTO Teams_in_Championship(team_id, championship_id,place_in_championship) VALUES (4,1,3)
INSERT INTO Teams_in_Championship(team_id, championship_id,place_in_championship) VALUES (5,5,2)
INSERT INTO Teams_in_Championship(team_id, championship_id,place_in_championship) VALUES (6,2,1)
INSERT INTO Teams_in_Championship(team_id, championship_id,place_in_championship) VALUES (7,6,1)

INSERT INTO Player_of_the_Year(best_player_id,best_year, best_player_bonus) VALUES (7,2022,7000)
INSERT INTO Player_of_the_Year(best_player_id,best_year, best_player_bonus) VALUES (1,2021,10000)
INSERT INTO Player_of_the_Year(best_player_id,best_year, best_player_bonus) VALUES (2,2019,9000)
INSERT INTO Player_of_the_Year(best_player_id,best_year, best_player_bonus) VALUES (9,2018,8000)
INSERT INTO Player_of_the_Year(best_player_id,best_year, best_player_bonus) VALUES (8,2017,8000)
INSERT INTO Player_of_the_Year(best_player_id,best_year, best_player_bonus) VALUES (2,2016,7000)

INSERT INTO Competition(competition_id,competition_name, competition_number_of_steps) VALUES (1,'Champions League',4)
INSERT INTO Competition(competition_id,competition_name, competition_number_of_steps) VALUES (2,'Europa League',4)
INSERT INTO Competition(competition_id,competition_name, competition_number_of_steps) VALUES (3,'Conference League',4)
INSERT INTO Competition(competition_id,competition_name, competition_number_of_steps) VALUES (4,'Cupa Romaniei',1)
INSERT INTO Competition(competition_id,competition_name, competition_number_of_steps) VALUES (5,'FA Cup',1)---cupa Angliei
INSERT INTO Competition(competition_id,competition_name, competition_number_of_steps) VALUES (6,'CMC',7)---cupa mondiala a cublurilor FIFA

INSERT INTO Teams_in_Competition(team_id,competition_id,year_of_competition) VALUES (1,1,2022)
INSERT INTO Teams_in_Competition(team_id,competition_id,year_of_competition) VALUES (4,1,2022)
INSERT INTO Teams_in_Competition(team_id,competition_id,year_of_competition) VALUES (10,1,2022)
INSERT INTO Teams_in_Competition(team_id,competition_id,year_of_competition) VALUES (1,6,2022)
INSERT INTO Teams_in_Competition(team_id,competition_id,year_of_competition) VALUES (7,6,2022)
INSERT INTO Teams_in_Competition(team_id,competition_id,year_of_competition) VALUES (8,6,2022)
INSERT INTO Teams_in_Competition(team_id,competition_id,year_of_competition) VALUES (2,2,2021)
INSERT INTO Teams_in_Competition(team_id,competition_id,year_of_competition) VALUES (3,2,2009)

INSERT INTO Winner_of_Competition(winner_team_id,competition_id,year_of_competition) VALUES (12,1,2021)
INSERT INTO Winner_of_Competition(winner_team_id,competition_id,year_of_competition) VALUES (1,1,2020)
INSERT INTO Winner_of_Competition(winner_team_id,competition_id,year_of_competition) VALUES (12,6,2021)
INSERT INTO Winner_of_Competition(winner_team_id,competition_id,year_of_competition) VALUES (3,4,2020)

INSERT INTO Sponsor(sponsor_id, sponsor_name, sponsor_amount_given) VALUES (1, 'Adidas', 100000), (2, 'Nike', 200000), (3, 'New Balance', 100000), (4, 'Puma', 3000), (5, 'Kapa', 20000), (6, 'Under Armour', 10000)
INSERT INTO Sponsor_on_Team(sponsor_id,sponsored_team_id) VALUES (1,1), (1,6), (2,1), (2,6), (2, 4), (2,5), (3, 7), (4,1), (4, 9), (5, 4)
---UPDATE STATEMENTS

--update the number of people in the staff for team with team_id = 1 to 300;
UPDATE Team SET team_staff_people=300 WHERE team_id=1 --(used =)
SELECT *
FROM Team --modified table

--update the number of teams in all the championships which start with letter 'L' and have between 15 and 20 teams
UPDATE Championship SET number_of_teams = number_of_teams + 2 WHERE championship_name LIKE 'L_%' AND number_of_teams BETWEEN 15 AND 21 --(used LIKE, AND, BETWEEN)
SELECT *
FROM Championship --modified table

--update the salaries of the players which are strikers and midfielders, increasing it with 1000$
UPDATE Player SET player_salary = player_salary + 1000 WHERE player_position IN ('striker', 'midfielder') --(used IN)
SELECT * 
FROM Player --modified table

---DELETE STATEMENTS

--delete the players for which we do not know the salary (salary = null)
DELETE Player WHERE player_salary IS NULL --(used IS NULL)
SELECT * 
FROM Player --modified table

--delete the players which did not score more than 20 goals
DELETE Goals_of_Player_in_Team WHERE number_of_goals < 20 --(used <)
SELECT * 
FROM Goals_of_Player_in_Team --modified table

---QUERIES
--a. 2 queries with the union operation; use UNION [ALL] and OR;
--show the teams which play in a Championship or in a Competition
SELECT T.team_id, T.team_name
FROM Team T
WHERE T.team_id IN
	(SELECT Te.team_id
	FROM Teams_in_Championship Te, Championship C
	WHERE Te.championship_id = C.championship_id)
UNION												--(used UNION and WHERE with () )
SELECT T.team_id, T.team_name
FROM Team T
WHERE T.team_id IN
	(SELECT Te.team_id
	FROM Teams_in_Competition Te, Competition C
	WHERE Te.competition_id = C.competition_id)

--show the teams for which the coach's name contains letter "m" and has more than 200 people in the staff or the teams which have in their names the letter "m" 
SELECT T.team_id, T.team_name
FROM Team T
Where (T.team_coach LIKE '_%m_%' AND T.team_staff_people > 200) OR (T.team_name LIKE '_%m_%')  --(used OR and AND)

--b. 2 queries with the intersection operation; use INTERSECT and IN;
--show the players which are strikers but were also player of the year sometime
SELECT P.player_name
FROM Player P
WHERE P.player_position = 'striker' 
INTERSECT										---(used INTERSECT and DISTINCT)
SELECT DISTINCT P.player_name
FROM Player P, Player_of_the_Year PY
WHERE P.player_id = PY.best_player_id

--show the players which are strikers but were also player of the year sometime (alternative with IN)
SELECT P.player_name
FROM Player P 
WHERE P.player_position = 'striker'	 AND P.player_id IN (SELECT PY.best_player_id from Player_of_the_Year PY) --(used IN and AND)

--c. 2 queries with the difference operation; use EXCEPT and NOT IN;
--show players which are midfielders or defenders but which were never player of the year
SELECT P.player_name
FROM Player P
WHERE P.player_position = 'midfielder' OR  P.player_position = 'defender'
EXCEPT																		--(used EXCEPT, OR and DISTINCT)
SELECT DISTINCT P.player_name
FROM Player P, Player_of_the_Year PY
WHERE P.player_id = PY.best_player_id

--show players which are midfielders or defenders but which were never player of the year(alternative with NOT IN)
SELECT P.player_name
FROM Player P																--(used NOT IN, WHERE with (), AND and OR)
WHERE (P.player_position = 'midfielder' OR  P.player_position = 'defender') AND P.player_id NOT IN (SELECT PY.best_player_id from Player_of_the_Year PY)

--d. 4 queries with INNER JOIN, LEFT JOIN, RIGHT JOIN, and FULL JOIN (one query per operator)
   --one query will join at least 3 tables, while another one will join at least two many-to-many relationships;
--for each team, show the Championship in which the team is playing
SELECT T.team_name, C.championship_name
FROM Team T INNER JOIN Teams_in_Championship TC ON T.team_id = TC.team_id INNER JOIN Championship C ON TC.championship_id= C.championship_id
					--(used INNER JOIN with 3 tables)

--show the teams which won a Competition(also show which competetion), but also the ones which are not
SELECT T.team_id, T.team_name, C.competition_name
FROM Team T LEFT JOIN Winner_of_Competition WC ON T.team_id = WC.winner_team_id LEFT JOIN Competition C ON WC.competition_id = C.competition_id  
					--(used LEFT JOIN)

--show the teams which took part in a competition, but also the ones which are not
SELECT DISTINCT T.team_id, T.team_name
FROM Teams_in_Competition TC RIGHT JOIN Team T ON TC.team_id = T.team_id    --(used RIGHT JOIN and DISTINCT)

--show the teams which play in a competition and have at least one sponsor(print also the sponsors and competitions); include the teams which are not playing in a competition and the teams which are not sponsored
SELECT T.team_name, S.sponsor_name, C.competition_name
FROM Team T 
FULL JOIN Teams_in_Competition TC ON T.team_id = TC.team_id 
FULL JOIN Sponsor_on_Team ST ON T.team_id = ST.sponsored_team_id            --(used FULL JOIN on 2 tables with n:m relationship)
FULL JOIN Sponsor S ON s.sponsor_id = ST.sponsor_id
FULL JOIN Competition C ON TC.competition_id = C.competition_id

--e. 2 queries with the IN operator and a subquery in the WHERE clause; 
   --in at least one case, the subquery must include a subquery in its own WHERE clause

--show the coaches of the teams which did not played in a competition; also decrease those teams's staff
SELECT T.team_coach, T.team_staff_people-1 AS 'decreased staff'
FROM Team T
WHERE T.team_id NOT IN
	(SELECT Te.team_id								--(used IN, NOT, ORDER BY, arithmetic expression)
	FROM Teams_in_Competition Te, Competition C
	WHERE Te.competition_id = C.competition_id)
ORDER BY [decreased staff] DESC

--show the players of the teams which are in the first place in their championships and increase their salary with 9999$
SELECT DISTINCT P.player_name,P.player_salary, P.player_salary+9999 AS 'Increased salary'
FROM Player P
WHERE P.current_team IN
	(SELECT T.team_id
	FROM Team T
	WHERE T.team_id IN			--(the subquery must include a subquery in its own WHERE clause, used DISTINCT, ORDER BY and arithmetic expression)
		(SELECT TC.team_id
		FROM Teams_in_Championship TC
		WHERE TC.place_in_championship = 1))
ORDER BY [Increased salary] 

--f. 2 queries with the EXISTS operator and a subquery in the WHERE clause;
--show the teams which have sponsors
SELECT T.team_id, T.team_name
FROM Team T
WHERE EXISTS					--(used EXISTS)
		(SELECT *
		FROM Sponsor_on_Team SP 
		WHERE SP.sponsored_team_id = T.team_id)

--show the teams which are not sponsored by NIKE
SELECT T.team_name
FROM Team T
WHERE NOT EXISTS								--(used NOT EXISTS, AND)
			(SELECT ST.sponsored_team_id
			FROM Sponsor_on_Team ST
			WHERE ST.sponsor_id = 2 AND ST.sponsored_team_id = T.team_id)

--g. 2 queries with a subquery in the FROM clause;    
--show the players(name and number) which are strikers, have salary at least 12000000, and scored more than 20 goals
SELECT p.player_name, p.player_number
FROM 
	(SELECT * 
	FROM Player P																--(used WHERE with (), NOT and AND)
	WHERE (NOT P.player_salary < 12000000 AND P.player_position = 'striker')
	)p WHERE p.player_id IN 
							(SELECT G.player_id
							FROM Goals_of_Player_in_Team G
							WHERE G.number_of_goals > 20)


--show the teams which are playing in Champions League
SELECT T.team_name, T.team_coach
FROM Team T
WHERE T.team_id IN
		(SELECT c.team_id
		FROM
			(SELECT * 
			FROM Teams_in_Competition C
			WHERE C.competition_id = 1
			)c) 

--h. 4 queries with the GROUP BY clause, 3 of which also contain the HAVING clause;
--2 of the latter will also have a subquery in the HAVING clause; use the aggregation operators: COUNT, SUM, AVG, MIN, MAX;
--show country's id and the number of teams for each of them
SELECT T.team_country, COUNT(*) as 'number of teams'
FROM Team T												--(used GROUP BY and COUNT)
GROUP BY T.team_country
                       
--print the player which won the most titles of player of the year
SELECT P.player_id, P.player_name
FROM Player P INNER JOIN Player_of_the_year PY1 ON P.player_id = PY1.best_player_id
GROUP BY P.player_id, P.player_name
HAVING COUNT(*) = 
				(SELECT MAX(P2.C)								--(used GROUP BY, MAX, COUNT, and have a subquery in the HAVING clause)
				 FROM (
					SELECT COUNT(*) C
					FROM Player P1 INNER JOIN Player_of_the_year PY ON P1.player_id = PY.best_player_id
					GROUP BY P1.player_id, P1.player_name)P2)



--print the teams which won the smallest number of competitions
SELECT T2.team_id, T2.team_name
FROM Team T2 INNER JOIN Winner_of_Competition TC1 ON T2.team_id =TC1.winner_team_id
GROUP BY T2.team_id, T2.team_name
HAVING COUNT(*)=	
	(SELECT MIN(T1.C)
	FROM														--(used GROUP BY, MIN, COUNT, and have a subquery in the HAVING clause)
		(SELECT COUNT(*) C
		FROM Team T INNER JOIN Winner_of_Competition TC ON T.team_id =TC.winner_team_id
		GROUP BY T.team_id, T.team_name) T1)

--show the sponsors which have at least 2 teams sponsored
SELECT S.sponsor_id, S.sponsor_name
FROM Sponsor S
GROUP BY S.sponsor_id, S.sponsor_name							--(used GROUP BY, COUNT)
HAVING 1 < (SELECT COUNT(ST.sponsor_id)
				FROM Sponsor_on_Team ST
				WHERE S.sponsor_id = ST.sponsor_id)

--i. 4 queries using ANY and ALL to introduce a subquery in the WHERE clause (2 queries per operator); 
    --rewrite 2 of them with aggregation operators, and the other 2 with IN / [NOT] IN.

--show the top 3 players which have a bigger salary than minimum salary of the players born after 1980
SELECT TOP 3 P.player_name
FROM Player P
WHERE P.player_salary > ANY (									--(used ANY, TOP and ORDER BY)
							SELECT P2.player_salary
							FROM Player P2
							WHERE P2.player_year_of_birth > 1996)
ORDER BY P.player_salary DESC

	--rewritten with an aggregation operatior, used MIN instead of ANY
SELECT TOP 3 P.player_name
FROM Player P
WHERE P.player_salary > (									--(used MIN, TOP)
							SELECT MIN(P2.player_salary)
							FROM Player P2
							WHERE P2.player_year_of_birth > 1996)
ORDER BY P.player_salary DESC

--find all the teams that participate in a competition with 4 steps
SELECT T.team_id,T.team_name
FROM Team T													--(used ANY)
WHERE T.team_id = ANY(
					SELECT TC.team_id
					FROM Teams_in_Competition TC INNER JOIN Competition C ON TC.competition_id = C.competition_id
					WHERE C.competition_number_of_steps = 4)

     --rewritten with IN
SELECT T.team_id,T.team_name
FROM Team T													--(used IN)
WHERE T.team_id IN(
					SELECT TC.team_id
					FROM Teams_in_Competition TC INNER JOIN Competition C ON TC.competition_id = C.competition_id
					WHERE C.competition_number_of_steps = 4)

--find the player which have the salary bigger than any of the strikers which have the number on shirt bigger than 9
SELECT P.player_name
FROM Player P
WHERE P.player_salary > ALL (									--(used ALL)
							SELECT P2.player_salary
							FROM Player P2
							WHERE (P2.player_number > 9 AND P2.player_position = 'striker'))

	--rewritten with an aggregation operatior, used MAX instead of ALL
SELECT P.player_name
FROM Player P
WHERE P.player_salary > (									--(used MAX)
						SELECT MAX(P2.player_salary)
							FROM Player P2
							WHERE (P2.player_number > 9 AND P2.player_position = 'striker'))

--find the players which are playing in a team which appeared before 1900 
SELECT P.player_name
FROM Player P
WHERE P.current_team <> ALL (									--(used ALL)
							SELECT P2.current_team
							FROM Player P2 INNER JOIN Team T ON P2.current_team = T.team_id
							WHERE T.team_appearance_year > 1900)
    --rewritten with NOT IN
SELECT P.player_name
FROM Player P
WHERE P.current_team  NOT IN (									--(used NOT IN)
							SELECT P2.current_team
							FROM Player P2 INNER JOIN Team T ON P2.current_team = T.team_id
							WHERE T.team_appearance_year > 1900)



--------------------------------------------------------------------------A3------------------------------------------------------------------------
--Altering the database

--a. modify the type of a column;
GO
CREATE OR ALTER PROCEDURE make_player_number_from_Player_tinyint AS
	ALTER TABLE Player ALTER COLUMN player_number TINYINT
GO

	--revert
CREATE OR ALTER PROCEDURE make_player_number_from_Player_smallint AS
	ALTER TABLE Player ALTER COLUMN player_number SMALLINT
GO

--b. add / remove a column;
CREATE OR ALTER PROCEDURE add_championship_description_column_in_Championship AS
	ALTER TABLE Championship ADD championship_description VARCHAR(100)
GO

	--revert
CREATE OR ALTER PROCEDURE drop_championship_description_column_from_Championship AS
	ALTER TABLE Championship DROP COLUMN championship_description
GO

--c. add / remove a DEFAULT constraint;
CREATE OR ALTER PROCEDURE add_default_team_staff_in_Team AS
	ALTER TABLE Team ADD CONSTRAINT default_team_staff DEFAULT(10) FOR team_staff_people
GO

	--revert
CREATE OR ALTER PROCEDURE drop_default_team_staff_from_Team AS
	ALTER TABLE Team DROP CONSTRAINT default_team_staff 
GO

--g. create / drop a table.
CREATE OR ALTER PROCEDURE add_Stadium_table AS
	CREATE TABLE Stadium(
		stadium_id INT,
		stadium_name VARCHAR(100) NOT NULL,
		stadium_country INT NOT NULL, 
		stadium_owner_team INT NOT NULL FOREIGN KEY REFERENCES Team(team_id) ON DELETE CASCADE ON UPDATE CASCADE,
		stadium_capacity INT
	)

	--revert
GO
CREATE OR ALTER PROCEDURE drop_Stadium_table AS
	DROP TABLE Stadium 
GO

--d. add / remove a primary key;
CREATE OR ALTER PROCEDURE make_name_and_owner_primary_key_in_Stadium AS
	ALTER TABLE Stadium
		ADD CONSTRAINT Stadium_pk PRIMARY KEY (stadium_name, stadium_owner_team)
GO

	--revert
CREATE OR ALTER PROCEDURE drop_name_and_owner_as_primary_key_from_Stadium AS
	ALTER TABLE Stadium
		DROP CONSTRAINT PK__Stadium__FC0A5508008AD7C9
GO
	
--e. add / remove a candidate key;
CREATE OR ALTER PROCEDURE make_name_and_country_championship_candidate_key_in_Championship AS
	ALTER TABLE Championship
		ADD CONSTRAINT Championship_ck UNIQUE (championship_name, championship_country)
GO

	--revert
CREATE OR ALTER PROCEDURE drop_name_and_country_championship_as_candidate_key_from_Championship AS
	ALTER TABLE Championship
		DROP CONSTRAINT Championship_ck
GO

--f. add / remove a foreign key;
CREATE OR ALTER PROCEDURE make_stadium_country_foreign_key_in_Stadium AS
	ALTER TABLE Stadium
		ADD CONSTRAINT Stadium_fk FOREIGN KEY (stadium_country) REFERENCES Country(country_id)
GO

	--revert
CREATE OR ALTER PROCEDURE drop_stadium_country_as_foreign_key_from_Stadium AS
	ALTER TABLE Stadium
		DROP CONSTRAINT FK__Stadium__stadium__6477ECF3
GO


CREATE TABLE CurrentVersionTable(
	current_version INT										-- a table which contains the current version of our database
)
INSERT INTO CurrentVersionTable(current_version) VALUES (1) --this is the initial version of our database, before performing any changes


CREATE TABLE VersionAndProcedureTable(
	initial_version INT,									--a table which stores the initial version of the database
	final_version INT,										--the version in which the database will be after the procedure is executed
	procedure_name VARCHAR(100),							--the specific procedure which modify the version of the database
	PRIMARY KEY(initial_version, final_version, procedure_name)
)

INSERT INTO VersionAndProcedureTable(initial_version, final_version, procedure_name) VALUES (1, 2, 'make_player_number_from_Player_tinyint')
INSERT INTO VersionAndProcedureTable(initial_version, final_version, procedure_name) VALUES (2, 1, 'make_player_number_from_Player_smallint')
INSERT INTO VersionAndProcedureTable(initial_version, final_version, procedure_name) VALUES (2, 3, 'add_championship_description_column_in_Championship')
INSERT INTO VersionAndProcedureTable(initial_version, final_version, procedure_name) VALUES (3, 2, 'drop_championship_description_column_from_Championship')
INSERT INTO VersionAndProcedureTable(initial_version, final_version, procedure_name) VALUES (3, 4, 'add_default_team_staff_in_Team')
INSERT INTO VersionAndProcedureTable(initial_version, final_version, procedure_name) VALUES	(4, 3, 'drop_default_team_staff_from_Team')
INSERT INTO VersionAndProcedureTable(initial_version, final_version, procedure_name) VALUES (4, 5, 'add_Stadium_table')
INSERT INTO VersionAndProcedureTable(initial_version, final_version, procedure_name) VALUES (5, 4, 'drop_Stadium_table')
INSERT INTO VersionAndProcedureTable(initial_version, final_version, procedure_name) VALUES (5, 6, 'make_name_and_owner_primary_key_in_Stadium')
INSERT INTO VersionAndProcedureTable(initial_version, final_version, procedure_name) VALUES (6, 5, 'drop_name_and_owner_as_primary_key_from_Stadium')
INSERT INTO VersionAndProcedureTable(initial_version, final_version, procedure_name) VALUES (6, 7, 'make_name_and_country_championship_candidate_key_in_Championship')
INSERT INTO VersionAndProcedureTable(initial_version, final_version, procedure_name) VALUES (7, 6, 'drop_name_and_country_championship_as_candidate_key_from_Championship')
INSERT INTO VersionAndProcedureTable(initial_version, final_version, procedure_name) VALUES (7, 8, 'make_stadium_country_foreign_key_in_Stadium')
INSERT INTO VersionAndProcedureTable(initial_version, final_version, procedure_name) VALUES (8, 7, 'drop_stadium_country_as_foreign_key_from_Stadium')

GO
CREATE OR ALTER PROCEDURE go_to_version(@new_version INT) 
AS
	DECLARE @current_version INT
	DECLARE @procedure_name VARCHAR(MAX)
	SELECT @current_version = current_version FROM CurrentVersionTable

	IF (@new_version > (SELECT MAX(final_version) FROM VersionAndProcedureTable) OR @new_Version < 1)
		RAISERROR ('Invalid version, try again!', 10, 1)
	ELSE
	BEGIN
		IF @new_version = @current_version
			PRINT('You are already on this version!');
		ELSE
		BEGIN
			IF @current_version > @new_version			--if we want to go from a higher version to a smaller one
			BEGIN
				WHILE @current_version > @new_version 
					BEGIN
						SELECT @procedure_name = procedure_name FROM VersionAndProcedureTable WHERE initial_version = @current_version AND final_version = @current_version-1
						PRINT('We are executing: ||' + @procedure_name + '|| to go in version -> ' +  CAST(@current_version - 1 AS VARCHAR(10)) );
						EXEC (@procedure_name)
						SET @current_version = @current_version - 1
					END
			END
			IF @current_version < @new_version
			BEGIN
				WHILE @current_version < @new_version		--if we want to go from a smaller version to a higher one
					BEGIN
						SELECT @procedure_name = procedure_name FROM VersionAndProcedureTable WHERE initial_version = @current_version AND final_version = @current_version+1
						PRINT('We are executing ||' + @procedure_name +  '|| to go in version -> ' +  CAST(@current_version + 1 AS VARCHAR(10)) );
						EXEC (@procedure_name)
						SET @current_version = @current_version + 1
					END
			END
			UPDATE CurrentVersionTable SET current_version = @new_version
		END
	END

Exec go_to_version 6
SELECT *
FROM CurrentVersionTable

SELECT *
FROM Championship

SELECT *
FROM Stadium


SELECT OBJECT_NAME(OBJECT_ID) AS NameofConstraint,
OBJECT_NAME(parent_object_id) AS TableName,
type_desc AS ConstraintType
FROM sys.objects
WHERE type_desc LIKE '%CONSTRAINT' AND parent_object_id = OBJECT_ID('Stadium')

SELECT OBJECT_NAME(OBJECT_ID) AS NameofConstraint,
OBJECT_NAME(parent_object_id) AS TableName,
type_desc AS ConstraintType
FROM sys.objects
WHERE type_desc LIKE '%CONSTRAINT' AND parent_object_id = OBJECT_ID('Championship')



--procedura care insereaza oricate randuri intr-un tabel primit ca parametru -- trimit numele tabelului ca parametru, ca string
--in tabelele alea de test inserez date despre procedurele 