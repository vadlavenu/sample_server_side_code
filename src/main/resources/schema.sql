CREATE TABLE products (
  ProductId int(11) NOT NULL AUTO_INCREMENT,
  ProductName varchar(50) DEFAULT NULL,
  ProductCode varchar(50) DEFAULT NULL,
  Tags varchar(50) DEFAULT NULL,
  ReleaseDate date DEFAULT NULL,
  Description varchar(1000) DEFAULT NULL,
  Price int(11) DEFAULT NULL,
  StarRating float DEFAULT NULL,
  ImageUrl varchar(1000) DEFAULT NULL,
  hist_flag varchar(1) DEFAULT NULL,
  PRIMARY KEY (ProductId)
);