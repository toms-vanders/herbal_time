USE master;

alter database "dmaj0919_1081496" set single_user with rollback immediate
GO

alter database "dmaj0919_1081496" set MULTI_USER
GO


DROP DATABASE IF EXISTS "dmaj0919_1081496";
GO

CREATE DATABASE "dmaj0919_1081496";
GO

SET DATEFORMAT mdy;
GO

USE  "dmaj0919_1081496";
GO

-- SET DATEFORMAT mdy;

-- TODO: write delete and update rules
-- POSSIBLE RULES: NO ACTION, CASCADE, SET NULL, SET DEFAULT
-- COMMENTS: 
-- had to add country to every table that references "zip" from ZipCity, 
-- because zip and city form a composite key, and you can't reference to only a part of
-- a primary key on another table.
-- the same goes for referencing clientID, you also need to add CVR to the table from which you're referencing
-- to match the composite primary key


-- 08/05/2020
-- what about the commented RULES? and where it says no action

--09/05/2020
-- worked on Toms' new relational model. Addressed all 4 comments.

--20/05/2020
-- Using triggers instead of foreign key for SeasonalWorkers.leadBy and WorkSite.zip & countryCode.
-- Zip Codes Work as follows: Update Cascades, Delete no action - meaning that you can't delete those zip codes that already are referenced in other tables.
-- Deleting client now deletes all their WorkSites, which deltes all WorkSite WorkTypes and WorkTasks that have deleted WorkTypes are set to NULL.
-- All foreign keys that reference WorkSiteID and WorkTypeID have ON UPDATE NO ACTION, so updating those columns would throw an error. They are surogate primary keys and auto incremented so should not be updated. 
-- For SeasonalWorkers CONSTRAINT "FK_WorkSite" FOREIGN KEY ON DELTE SET TO NULL. So when WorkSite is deleted, this get's set to null and worker need to assign a new worksite.
-- WorkTask now have foreign key to Person table instead of SeasonalWorker Table.
-- For WorkTask, now if WorkType is delted it is set to NULL in WorkTask and if SeasonalWorker is deleted, all their WorkTasks are deleted. (This maybe need to be changed).

CREATE TABLE ZipCity (
    zip CHAR(6) NOT NULL,
    countryCode CHAR(3) NOT NULL,
    city NVARCHAR(30) NOT NULL,
    
    CONSTRAINT "PK_Zip" PRIMARY KEY CLUSTERED (
        zip, countryCode
    )
);
GO

CREATE TABLE Person (
    cpr NVARCHAR(10) NOT NULL,
    fname NVARCHAR(30) NOT NULL,
    lname NVARCHAR(30) NOT NULL,
    dateOfBirth DATETIME NOT NULL,
    sex CHAR NOT NULL,
    email NVARCHAR(30),
    phoneNum NVARCHAR(50) NOT NULL,
    streetName NVARCHAR(100) NOT NULL,
    streetNum NVARCHAR(10) NOT NULL,
    zip CHAR(6) NOT NULL,
    countryCode CHAR(3) NOT NULL,
    country NVARCHAR(50) NOT NULL,

    CONSTRAINT "PK_Person" PRIMARY KEY CLUSTERED (
        cpr
    ),

    CONSTRAINT "FK_PersonZip" FOREIGN KEY (
        zip, countryCode
    ) REFERENCES ZipCity(
        zip, countryCode
    )
    ON UPDATE CASCADE
    ON DELETE NO ACTION,
);
GO

CREATE TABLE Employee (
    cpr NVARCHAR(10) NOT NULL,
    regNum NVARCHAR(30) NOT NULL,
    kontoNum NVARCHAR(50) NOT NULL,
    salary REAL NOT NULL,

    CONSTRAINT "PK_Employee" PRIMARY KEY CLUSTERED (
        cpr
    ),

    CONSTRAINT "FK_EmployeeCPR" FOREIGN KEY (
        cpr
    ) REFERENCES Person (
        cpr
    )
    ON UPDATE CASCADE
    ON DELETE CASCADE,
);
GO

CREATE TABLE Client (
    cvr NVARCHAR(8) NOT NULL,
    clientName NVARCHAR(100) NOT NULL,
    email NVARCHAR(30) NOT NULL,
    phoneNum NVARCHAR(50) NOT NULL,
    streetName NVARCHAR(100) NOT NULL,
    streetNum NVARCHAR(10) NOT NULL,
    zip CHAR(6) NOT NULL,
    countryCode CHAR(3) DEFAULT 'DK',
    country NVARCHAR(50) DEFAULT 'Denmark',
    dateStart DATETIME NOT NULL,
    dateEnd DATETIME NOT NULL,

    CONSTRAINT "PK_Client" PRIMARY KEY CLUSTERED (
        cvr
    ),
    CONSTRAINT "FK_ZipClient" FOREIGN KEY (
        zip, countryCode
    ) REFERENCES ZipCity (
        zip, countryCode
    )
    ON UPDATE CASCADE
    ON DELETE NO ACTION,
);
GO

CREATE TABLE WorkSite (
    workSiteID INT IDENTITY(1,1) NOT NULL,
    siteName NVARCHAR(100) NOT NULL,
    siteDescription NVARCHAR(150) NOT NULL,
	streetName NVARCHAR(25) NOT NULL,
    streetNum NVARCHAR(10) NOT NULL,
    zip CHAR(6) NOT NULL,
    countryCode CHAR(3) DEFAULT 'DK',
    country NVARCHAR(50) DEFAULT 'Denmark',
    typeOfJob NVARCHAR(50) NOT NULL,
    pricePerWorker REAL, -- can be NULL?
    cvr NVARCHAR(8) NOT NULL,

    CONSTRAINT "PK_WorkSite" PRIMARY KEY CLUSTERED (
        workSiteID
    ),
    /*** Replaced With a Trigger
	CONSTRAINT "FK_ZipWorkSite" FOREIGN KEY (
        zip, countryCode
    ) REFERENCES ZipCity (
        zip, countryCode
    )
    ON UPDATE NO ACTION
    ON DELETE NO ACTION, ***/

    CONSTRAINT "FK_WorkSiteClient" FOREIGN KEY (
        cvr
    ) REFERENCES Client (
        cvr
    )

	ON UPDATE CASCADE
	ON DELETE CASCADE

);
GO

CREATE TABLE Produce (
    produceName NVARCHAR(50) NOT NULL,

    CONSTRAINT "PK_Produce" PRIMARY KEY CLUSTERED (
        produceName
    ),
);
GO

CREATE TABLE WorkSiteProduce (
    workSiteID INT NOT NULL,
	produceName NVARCHAR(50) NOT NULL,

    CONSTRAINT "PK_WorkSiteID" PRIMARY KEY CLUSTERED (
        workSiteID, produceName
    ),

	CONSTRAINT "FK_WorkSiteProduce" FOREIGN KEY (
        workSiteID
    ) REFERENCES WorkSite (
        workSiteID
    )
    ON UPDATE NO ACTION
    ON DELETE CASCADE,

	CONSTRAINT "FK_Produce" FOREIGN KEY (
        produceName
    ) REFERENCES Produce (
        produceName
    )
    ON UPDATE CASCADE
    ON DELETE CASCADE,

);
GO

CREATE TABLE SeasonalWorker (
    cpr NVARCHAR(10) NOT NULL,
    passportNum NVARCHAR(9) NOT NULL,
    swift NVARCHAR(11) NOT NULL,
    iban NVARCHAR(34) NOT NULL,
    ssn NVARCHAR(50) NOT NULL,
    workedBefore BIT,
    leadBy NVARCHAR(10), 
    wSiteID INT,

    CONSTRAINT "PK_SeasonalWorker" PRIMARY KEY(
        cpr
    ),
    
    /*** Replaced with a trigger 
	CONSTRAINT "FK_WorkerLead" FOREIGN KEY (
        leadBy
    ) REFERENCES SeasonalWorker (
        cpr
    )
   ON UPDATE CASCADE
   ON DELETE SET NULL, ***/

    CONSTRAINT "FK_PersonCPR" FOREIGN KEY (
        cpr
    ) REFERENCES Person (
        cpr
    )
    ON UPDATE CASCADE
    ON DELETE CASCADE,

    CONSTRAINT "FK_WorkSite" FOREIGN KEY (
        wSiteID
    ) REFERENCES WorkSite (
        workSiteID
    )
   ON UPDATE NO ACTION
   ON DELETE SET NULL
);
GO

CREATE TABLE WorkType (
    workTypeID INT IDENTITY(1,1) NOT NULL,
    descOfJob NVARCHAR(50) NOT NULL,
    typeOfProduce NVARCHAR(30) NOT NULL,
    salaryType NVARCHAR(10) NOT NULL,
    pay REAL NOT NULL,
    workSiteID INT NOT NULL,

    CONSTRAINT "PK_WorkType" PRIMARY KEY CLUSTERED (
        workTypeID
    ),

    CONSTRAINT "FK_WorkSiteWorkType" FOREIGN KEY (
        workSiteID
    ) REFERENCES WorkSite (
        workSiteID
    )
    ON UPDATE NO ACTION
    ON DELETE CASCADE,
);
GO

CREATE TABLE WorkTask (
    workTaskID INT IDENTITY(1,1) NOT NULL,
    hoursWorked REAL,
    quantity REAL,
    dateStart DATETIME NOT NULL,
    dateEnd DATETIME NOT NULL,
    taskStatus NVARCHAR(50) NOT NULL,
    workTypeID INT,
    workerCpr NVARCHAR(10) NOT NULL,

    CONSTRAINT "PK_WorkTask" PRIMARY KEY CLUSTERED (
        workTaskID
    ),

    CONSTRAINT "FK_WorkType" FOREIGN KEY (
        workTypeID
    ) REFERENCES WorkType (
        workTypeID
    )
    ON UPDATE NO ACTION
    ON DELETE SET NULL,

    CONSTRAINT "FK_WorkerWorkTask" FOREIGN KEY ( --Chnaged foreign key to Person table
        workerCpr
    ) REFERENCES Person (
        cpr
    )
    ON UPDATE CASCADE
    ON DELETE NO ACTION,
);
GO