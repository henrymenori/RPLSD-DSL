-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 30, 2015 at 04:06 PM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `dsl`
--

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE IF NOT EXISTS `mahasiswa` (
  `nim` varchar(8) NOT NULL,
  `no_reg` varchar(8) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `kode_pos` varchar(5) NOT NULL,
  `semester` int(11) NOT NULL,
  `nip_wali` varchar(18) NOT NULL,
  `dosen_wali` varchar(50) NOT NULL,
  `fakultas` varchar(50) NOT NULL,
  `program_studi` varchar(50) NOT NULL,
  `total_SKS` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`nim`, `no_reg`, `nama`, `alamat`, `kode_pos`, `semester`, `nip_wali`, `dosen_wali`, `fakultas`, `program_studi`, `total_SKS`) VALUES
('13512002', '78945612', 'Eldwin Christian', 'Kebon Bibit Barat I 52', '32145', 7, '1709195610123477', 'Bayu Hendrojoyo', 'STEI', 'Teknik Informatika', 120),
('13512009', '12103350', 'Rita Sarah', 'Tubagus Ismail 7 no 15', '40135', 7, '197701272008012011', 'Ayu Purwarianti', 'STEI', 'Teknik Informatika', 115),
('13512024', '13141522', 'Riady Sastra Kusuma', 'Kebon Bunga Indah No. 7', '33123', 6, '140519753716', 'Gilang Husain Sukajadi', 'STEI', 'Teknik Informatika', 102),
('13512082', '55661456', 'Marcelinus Henry Menori', 'Cimahi Barat VI No. 44', '33456', 8, '2212194588225566', 'Dodi Adi Cahyadi', 'STEI', 'Teknik Informatika', 140),
('13512098', '12345678', 'William Stefan Hartono', 'Jl. Ciumbuleuit no. 47', '40141', 7, '195210011978031002', 'Munawar Ahmad Z.A.', 'STEI', 'Teknik Informatika', 124);

-- --------------------------------------------------------

--
-- Table structure for table `mata_kuliah`
--

CREATE TABLE IF NOT EXISTS `mata_kuliah` (
  `kode_matkul` varchar(6) NOT NULL,
  `nama_matkul` varchar(50) NOT NULL,
  `fakultas` varchar(50) NOT NULL,
  `program_studi` varchar(50) NOT NULL,
  `SKS` int(11) NOT NULL,
  `semester` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mata_kuliah`
--

INSERT INTO `mata_kuliah` (`kode_matkul`, `nama_matkul`, `fakultas`, `program_studi`, `SKS`, `semester`) VALUES
('EL1092', 'Dasar Rangkaian Elektrik', 'STEI', 'Teknik Elektro', 2, 2),
('FI1101', 'Fisika Dasar IA', 'FMIPA', 'Fisika', 4, 1),
('FI1201', 'Fisika Dasar IIA', 'FMIPA', 'Fisika', 4, 2),
('IF2110', 'Algoritma & Struktur Data', 'STEI', 'Teknik Informatika', 4, 3),
('IF2112', 'Pemrograman Fungsional', 'STEI', 'Teknik Informatika', 1, 3),
('IF2120', 'Matematika Diskrit', 'STEI', 'Teknik Informatika', 3, 3),
('IF2121', 'Logika Informatika', 'STEI', 'Teknik Informatika', 3, 3),
('IF2122', 'Probabilitas & Statistika', 'STEI', 'Teknik Informatika', 3, 3),
('IF2130', 'Organisasi & Arsitektur Komputer', 'STEI', 'Teknik Informatika', 3, 3),
('IF2210', 'Pemrograman Berorientasi Objek', 'STEI', 'Teknik Informatika', 3, 4),
('IF2211', 'Strategi Algoritma', 'STEI', 'Teknik Informatika', 3, 4),
('IF2220', 'Teori Bahasa Formal dan Otomata', 'STEI', 'Teknik Informatika', 3, 4),
('IF2230', 'Sistem Operasi', 'STEI', 'Teknik Informatika', 3, 4),
('IF2240', 'Basis Data', 'STEI', 'Teknik Informatika', 3, 4),
('IF2250', 'Dasar Rekayasa Perangkat Lunak', 'STEI', 'Teknik Informatika', 3, 4),
('IF3110', 'Pengembangan Aplikasi Berbasis Web', 'STEI', 'Teknik Informatika', 2, 5),
('IF3111', 'Pengembangan Aplikasi pada Platform Khusus', 'STEI', 'Teknik Informatika', 2, 6),
('IF3130', 'Jaringan Komputer', 'STEI', 'Teknik Informatika', 3, 5),
('IF3140', 'Manajemen Basis Data', 'STEI', 'Teknik Informatika', 3, 5),
('IF3150', 'Manajemen Proyek Perangkat Lunak', 'STEI', 'Teknik Informatika', 3, 5),
('IF3151', 'Interaksi Manusia & Komputer', 'STEI', 'Teknik Informatika', 3, 5),
('IF3170', 'Inteligensi Buatan', 'STEI', 'Teknik Informatika', 4, 5),
('IF3230', 'Sistem Paralel dan Terdistribusi', 'STEI', 'Teknik Informatika', 3, 6),
('IF3240', 'Sistem Informasi', 'STEI', 'Teknik Informatika', 3, 6),
('IF3250', 'Proyek Perangkat Lunak', 'STEI', 'Teknik Informatika', 3, 6),
('IF3260', 'Grafika Komputer', 'STEI', 'Teknik Informatika', 3, 6),
('IF3280', 'Socio-informatika dan Profesionalisme', 'STEI', 'Teknik Informatika', 3, 6),
('IF4090', 'Kerja Praktek', 'STEI', 'Teknik Informatika', 2, 7),
('IF4091', 'Tugas Akhir I & Seminar', 'STEI', 'Teknik Informatika', 2, 7),
('IF4150', 'Rekayasa Perangkat Lunak Spesifik Domain', 'STEI', 'Teknik Informatika', 2, 7),
('KI1101', 'Kimia Dasar IA', 'FMIPA', 'Kimia', 3, 1),
('KI1201', 'Kimia Dasar IIA', 'FMIPA', 'Kimia', 3, 2),
('KU1001', 'Olah Raga', 'FSRD', 'MKDU', 2, 2),
('KU1011', 'Tata Tulis Karya Ilmiah', 'FSRD', 'MKDU', 2, 2),
('KU1021', 'Pemahaman Teks Akademik', 'FSRD', 'MKDU', 2, 1),
('KU1071', 'Pengenalan Teknologi Informasi A', 'FSRD', 'MKDU', 2, 1),
('KU1101', 'Konsep Pengembangan Ilmu Pengetahuan', 'FSRD', 'MKDU', 2, 1),
('KU1201', 'Sistem Alam & Semesta', 'FSRD', 'MKDU', 2, 2),
('KU2071', 'Pancasila dan Kewarganegaraan', 'FSRD', 'MKDU', 2, 8),
('MA1101', 'Kalkulus IA', 'FMIPA', 'Matematika', 4, 1),
('MA1201', 'Kalkulus IIA', 'FMIPA', 'Matematika', 4, 2);

-- --------------------------------------------------------

--
-- Table structure for table `mengambil`
--

CREATE TABLE IF NOT EXISTS `mengambil` (
  `nim` varchar(8) NOT NULL,
  `kode_matkul` varchar(6) NOT NULL,
  `nilai` varchar(2) NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mengambil`
--

INSERT INTO `mengambil` (`nim`, `kode_matkul`, `nilai`, `status`) VALUES
('13512002', 'EL1092', 'C', 'Lulus'),
('13512002', 'IF2112', 'AB', 'Lulus'),
('13512002', 'IF2220', 'AB', 'Lulus'),
('13512002', 'IF3110', 'A', 'Lulus'),
('13512002', 'IF3230', 'B', 'Lulus'),
('13512002', 'IF4090', 'E', 'Tidak Lulus'),
('13512009', 'EL1092', 'C', 'Lulus'),
('13512009', 'FI1101', 'A', 'Lulus'),
('13512009', 'FI1201', 'A', 'Lulus'),
('13512009', 'IF2110', 'D', 'Tidak Lulus'),
('13512009', 'IF2112', 'BC', 'Lulus'),
('13512009', 'IF2120', 'C', 'Lulus'),
('13512009', 'IF2121', 'B', 'Lulus'),
('13512024', 'FI1101', 'AB', 'Lulus'),
('13512024', 'IF2110', 'A', 'Lulus'),
('13512024', 'IF2211', 'B', 'Lulus'),
('13512024', 'IF3240', 'AB', 'Lulus'),
('13512024', 'IF3280', 'A', 'Lulus'),
('13512082', 'FI1201', 'B', 'Lulus'),
('13512082', 'IF2122', 'C', 'Lulus'),
('13512082', 'IF3111', 'C', 'Lulus'),
('13512082', 'IF4090', 'B', 'Lulus'),
('13512082', 'IF4150', 'D', 'Tidak Lulus'),
('13512082', 'KU2071', 'AB', 'Lulus'),
('13512082', 'MA1101', 'A', 'Lulus'),
('13512082', 'MA1201', 'A', 'Lulus'),
('13512098', 'EL1092', 'BC', 'Lulus'),
('13512098', 'FI1101', 'AB', 'Lulus'),
('13512098', 'FI1201', 'A', 'Lulus'),
('13512098', 'IF2110', 'C', 'Lulus'),
('13512098', 'IF2112', 'AB', 'Lulus'),
('13512098', 'IF2120', 'B', 'Lulus'),
('13512098', 'IF2121', 'BC', 'Lulus'),
('13512098', 'IF2122', 'B', 'Lulus'),
('13512098', 'IF2130', 'A', 'Lulus'),
('13512098', 'IF2250', 'B', 'Lulus'),
('13512098', 'IF3110', 'AB', 'Lulus'),
('13512098', 'IF3111', 'C', 'Lulus'),
('13512098', 'IF3130', 'B', 'Lulus'),
('13512098', 'IF3140', 'B', 'Lulus'),
('13512098', 'IF4090', 'B', 'Lulus'),
('13512098', 'IF4091', 'A', 'Lulus'),
('13512098', 'IF4150', 'A', 'Lulus'),
('13512098', 'KU1001', 'AB', 'Lulus'),
('13512098', 'MA1101', 'A', 'Lulus'),
('13512098', 'MA1201', 'AB', 'Lulus');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`nim`), ADD KEY `nip_wali` (`nip_wali`), ADD KEY `dosen_wali` (`dosen_wali`), ADD KEY `fakultas` (`fakultas`), ADD KEY `program_studi` (`program_studi`);

--
-- Indexes for table `mata_kuliah`
--
ALTER TABLE `mata_kuliah`
  ADD PRIMARY KEY (`kode_matkul`), ADD KEY `fakultas` (`fakultas`,`program_studi`), ADD KEY `program_studi` (`program_studi`);

--
-- Indexes for table `mengambil`
--
ALTER TABLE `mengambil`
  ADD UNIQUE KEY `nim_2` (`nim`,`kode_matkul`), ADD KEY `nim` (`nim`,`kode_matkul`), ADD KEY `kode_matkul` (`kode_matkul`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `mengambil`
--
ALTER TABLE `mengambil`
ADD CONSTRAINT `mengambil_ibfk_1` FOREIGN KEY (`nim`) REFERENCES `mahasiswa` (`nim`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `mengambil_ibfk_2` FOREIGN KEY (`kode_matkul`) REFERENCES `mata_kuliah` (`kode_matkul`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
