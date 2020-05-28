USE  "dmaj0919_1081496";
GO

/*** Trigger for updating Team Lead for seasonal workers ***/

CREATE TRIGGER leadBy_update_trigger
	ON SeasonalWorker
	AFTER UPDATE
AS
BEGIN
	SET NOCOUNT ON
	IF UPDATE (cpr)
	BEGIN
		DECLARE @OldCPR NVARCHAR(10)
		SELECT @OldCPR = cpr
		FROM DELETED
		DECLARE @NewCPR NVARCHAR(10)
		SELECT @NewCPR = cpr
		FROM INSERTED

		UPDATE SeasonalWorker
		SET leadBy = @NewCPR
		WHERE leadBy = @OldCPR
	END
END
GO

/*** Trigger for setting Team Lead to NULL for Seasonal Workers, if he/she is deleted ***/

CREATE TRIGGER leadBy_delete_trigger
	ON SeasonalWorker
	AFTER DELETE
AS
	SET NOCOUNT ON
	DECLARE @DeletedCPR NVARCHAR(10)
	SELECT @DeletedCPR = cpr
	FROM DELETED

	UPDATE SeasonalWorker
	SET leadBy = NULL
	WHERE leadBy = @DeletedCPR
GO

/*** Trigger for updating WorkSite Zip and CountryCode ***/

CREATE TRIGGER workSiteZip_update_trigger
	ON ZipCity
	AFTER UPDATE
AS
BEGIN
	SET NOCOUNT ON

	IF UPDATE (zip) OR UPDATE (countryCode)
	BEGIN
		DECLARE @OldZip CHAR(6)
		SELECT @OldZip = zip
		FROM DELETED
		DECLARE @NewZip Char(6)
		SELECT @NewZip = zip
		FROM INSERTED

		DECLARE @OldCC CHAR(3)
		SELECT @OldCC = countryCode
		FROM DELETED
		DECLARE @NewCC Char(3)
		SELECT @NewCC = countryCode
		FROM INSERTED

		UPDATE WorkSite
		SET zip = @NewZip, countryCode = @NewCC
		WHERE zip = @OldZip
	END
END
GO

/*** Trigger for raising error when deleting WorkSite Zip ***/

CREATE TRIGGER workSiteZip_delete_trigger
	ON ZipCity
	AFTER DELETE 
AS
	SET NOCOUNT ON
	DECLARE @OldZip CHAR(6)
	SELECT @OldZip = zip
	FROM DELETED

	IF EXISTS (SELECT * FROM WorkSite WHERE zip = @OldZip)
	BEGIN
		RAISERROR('Unable to delete - ZipCode exists in table Worksite...', 16, 1)
		ROLLBACK
	END
GO