CREATE DATABASE Hocsinh
GO

USE Hocsinh
GO

CREATE TABLE Hoc_sinh(
	ma_hoc_sinh INT IDENTITY(0,1) PRIMARY KEY,
	ho_ten VARCHAR(255),
	ngay_sinh DATE,
	gioi_tinh BIT,
	so_dien_thoai VARCHAR(255),
	dia_chi VARCHAR(255),
	tinh_trang BIT
)
GO

CREATE TABLE Khoa_hoc(
	ma_khoa_hoc INT IDENTITY(0,1) PRIMARY KEY,
	ten_khoa_hoc VARCHAR(255),
	mo_ta VARCHAR(255),
	ngay_bat_dau DATE,
	ngay_ket_thuc DATE,
	tinh_trang BIT
)
GO

CREATE TABLE Lop_hoc(
	ma_lop_hoc INT IDENTITY(0,1) PRIMARY KEY,
	ma_khoa_hoc INT,
	ma_hoc_sinh INT,
	ngay_dang_ky DATE,
	tinh_trang BIT
)
GO

