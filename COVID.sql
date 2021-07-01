DROP TABLE IF EXISTS `Class`;
DROP TABLE IF EXISTS `College`;
DROP TABLE IF EXISTS `Major`;
DROP TABLE IF EXISTS `User`;
DROP TABLE IF EXISTS `State`;
DROP TABLE IF EXISTS `Vacation`;

CREATE TABLE IF NOT EXISTS `Class` (
    `classNum` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `className` VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS `College` (
    `collegeNum` INT PRIMARY KEY AUTO_INCREMENT,
    `collegeName` VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS `Major` (
    `majorNum` INT PRIMARY KEY AUTO_INCREMENT,
    `collegeNum` INT NOT NULL,
    `classNum` INT NOT NULL,
    FOREIGN KEY(collegeNum) REFERENCES College(collegeNum),
    FOREIGN KEY(classNum) REFERENCES Class(classNum)
);

CREATE TABLE IF NOT EXISTS `User` (
    `userNum` CHAR(9) PRIMARY KEY,
    `collegeNum` INT NOT NULL,
    `majorNum` INT NOT NULL,
    `classNum` INT NOT NULL,
    `userType` INT NOT NULL,
    `userName` VARCHAR(10) NOT NULL,
    `password` VARCHAR(10) NOT NULL,
    `sex` BOOLEAN NOT NULL,
    FOREIGN KEY(collegeNum) REFERENCES College(collegeNum),
    FOREIGN KEY(majorNum) REFERENCES Major(majorNum),
    FOREIGN KEY(classNum) REFERENCES Class(classNum)
);

CREATE TABLE IF NOT EXISTS `State` (
    `stateNum` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `userNum` CHAR(9) NOT NULL,
    `stateTime` DATE NOT NULL,
    `isTemperature` BOOLEAN NOT NULL,
    `isCovid` BOOLEAN NOT NULL,
    `isLikeCovid` BOOLEAN NOT NULL,
    `quarantine` INT NOT NULL,
    `isRecentArea` BOOLEAN NOT NULL,
    `isRecentCountry` BOOLEAN NOT NULL,
    `isRecentPeople` BOOLEAN NOT NULL,
    `isSymptom` BOOLEAN NOT NULL,
    `isAbnormal` BOOLEAN NOT NULL,
    `healthCodeType` INT NOT NULL,
    `isOutSchool` BOOLEAN NOT NULL,
    `isOutCity` BOOLEAN NOT NULL,
    FOREIGN KEY(userNum) REFERENCES `User`(userNum)
);

CREATE TABLE IF NOT EXISTS `Vacation` (
    `vacationNum` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    `userNum` CHAR(9) NOT NULL,
    `reason` VARCHAR(255) NOT NULL,
    `startTime` DATE NOT NULL,
    `endTime` DATE NOT NULL,
    `requestTime` DATE NOT NULL,
    `way` VARCHAR(255) NOT NULL,
    `state` INT NOT NULL,
    FOREIGN KEY(userNum) REFERENCES `User`(userNum)
);