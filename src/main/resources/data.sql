INSERT INTO PRODUCTS 
(
PRODUCTNAME, 
PRODUCTCODE,  
DESCRIPTION, 
STARRATING, 
PRICE, 
RELEASEDATE, 
HIST_FLAG
) 
VALUES 
(
'TestWithH2Database',
'TestWithH2DatabaseCode',
'TestWithH2DatabaseDesc',
4.6,
600,
TO_DATE('2018-12-20', 'yyyy-MM-dd'),
'N'
);