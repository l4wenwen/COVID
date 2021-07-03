CREATE VIEW statistic_divide AS
SELECT 
c.collegeNum, 
totalNum, 
COUNT(DISTINCT c.userNum) filledNum, 
COUNT(isCovid = 1 or NULL) highRiskNum, 
COUNT(isRecentArea = 1 or NULL) passRiskAreaNum, 
stateTime
FROM 
(SELECT state.*,collegeNum FROM state, user WHERE state.userNum = user.userNum AND userType=2) c, 
(SELECT collegeNum, COUNT(*) totalNum FROM user WHERE userType = 2  GROUP BY collegeNum) b
WHERE c.collegeNum = b.collegeNum 
GROUP BY stateTime, c.collegeNum, totalNum