CREATE VIEW statistic AS SELECT b.*, a.* FROM (SELECT COUNT(DISTINCT c.userNum) filledNum, COUNT(isCovid = 1 or NULL) highRiskNum, COUNT(isRecentArea = 1 or NULL) passRiskAreaNum, stateTime FROM 
(SELECT * FROM state WHERE userNum IN (SELECT userNum FROM user WHERE userType = 2)) c
GROUP BY stateTime) a,  (SELECT COUNT(*) totalNum FROM user WHERE userType = 2) b