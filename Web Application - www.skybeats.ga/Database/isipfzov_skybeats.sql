-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Apr 21, 2021 at 01:17 AM
-- Server version: 10.3.28-MariaDB-cll-lve
-- PHP Version: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `isipfzov_skybeats`
--

-- --------------------------------------------------------

--
-- Table structure for table `genres`
--

CREATE TABLE `genres` (
  `id` int(30) NOT NULL,
  `genre` varchar(200) NOT NULL,
  `description` text NOT NULL,
  `cover_photo` text NOT NULL,
  `date_created` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `genres`
--

INSERT INTO `genres` (`id`, `genre`, `description`, `cover_photo`, `date_created`) VALUES
(1, 'Pop', 'Popular music', '1605745560_play.jpg', '2020-11-19 08:26:53'),
(2, 'Rock', ' Its loud and strong beats make it popular among the youths.', 'default_cover.jpg', '2020-11-19 08:29:13'),
(3, 'Country Music', 'Country Music', 'default_cover.jpg', '2020-11-19 08:59:17');

-- --------------------------------------------------------

--
-- Table structure for table `playlist`
--

CREATE TABLE `playlist` (
  `id` int(30) NOT NULL,
  `user_id` int(30) NOT NULL,
  `title` text NOT NULL,
  `description` text NOT NULL,
  `cover_image` text NOT NULL,
  `date_created` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `playlist`
--

INSERT INTO `playlist` (`id`, `user_id`, `title`, `description`, `cover_image`, `date_created`) VALUES
(1, 1, 'Playlist 1', 'Nunc pellentesque at erat eu vulputate. Integer ornare nec mauris ac sodales. Maecenas venenatis rutrum urna at faucibus. Aenean feugiat, purus ac venenatis mollis, lectus nunc dapibus mauris, sed imperdiet erat augue eu mauris. In eu diam eleifend, accumsan massa vitae, tempor velit. Interdum et malesuada fames ac ante ipsum primis in faucibus. Duis imperdiet tortor lectus, et scelerisque massa efficitur a. In hendrerit felis nec gravida cursus. Suspendisse aliquet est vel lacus venenatis interdum. Vestibulum quis risus dolor. Aliquam feugiat sagittis nibh, id dignissim ipsum mollis et. Nunc nec sapien ligula. Donec laoreet leo eget velit tristique, vitae pulvinar velit hendrerit.', '1605833520_m2.jpg', '2020-11-20 08:52:36'),
(2, 2, 'Playlist 2', 'Nulla sollicitudin laoreet elit quis interdum. Nam dictum convallis suscipit. Etiam in sapien mauris. Nunc varius metus tortor, at porta tortor aliquet vel. Praesent a augue laoreet, mattis justo a, posuere nulla. Donec dictum tortor vel metus interdum dignissim. Vestibulum commodo aliquam gravida. Nulla facilisi.', 'play.jpg', '2020-11-20 08:58:35'),
(3, 2, 'My Playlist', 'Sample', '1605833940_h1.jpg', '2020-11-20 08:59:23');

-- --------------------------------------------------------

--
-- Table structure for table `playlist_items`
--

CREATE TABLE `playlist_items` (
  `id` int(30) NOT NULL,
  `playlist_id` int(30) NOT NULL,
  `music_id` int(30) NOT NULL,
  `date_created` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `playlist_items`
--

INSERT INTO `playlist_items` (`id`, `playlist_id`, `music_id`, `date_created`) VALUES
(1, 6, 1, '2020-11-20 08:52:51'),
(2, 2, 2, '2020-11-20 08:58:44'),
(3, 3, 2, '2020-11-20 08:59:46'),
(4, 3, 1, '2020-11-20 08:59:46');

-- --------------------------------------------------------

--
-- Table structure for table `uploads`
--

CREATE TABLE `uploads` (
  `id` int(30) NOT NULL,
  `user_id` int(30) NOT NULL,
  `genre_id` int(30) NOT NULL,
  `title` text NOT NULL,
  `artist` text NOT NULL,
  `description` text NOT NULL,
  `upath` text NOT NULL,
  `cover_image` text NOT NULL,
  `date_created` int(11) NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `uploads`
--

INSERT INTO `uploads` (`id`, `user_id`, `genre_id`, `title`, `artist`, `description`, `upath`, `cover_image`, `date_created`) VALUES
(1, 1, 2, 'Song 101', 'BenSound', '&lt;p&gt;&lt;span style=&quot;color: rgb(0, 0, 0); font-family: &amp;quot;Open Sans&amp;quot;, Arial, sans-serif; font-size: 14px; text-align: justify;&quot;&gt;Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed egestas est massa, non ullamcorper augue sodales non. Morbi mollis venenatis augue sit amet lacinia. Cras tempor tempor urna, nec consectetur tellus ullamcorper quis. Mauris vitae blandit tellus. Sed feugiat tincidunt malesuada. Cras egestas consequat molestie. Ut non ex nec tellus vestibulum tincidunt. Suspendisse facilisis lorem id sapien euismod, id gravida felis blandit. Nam quis diam tempor, luctus nisl at, auctor velit. Nunc rhoncus, turpis et ornare sagittis, metus diam dignissim dolor, non faucibus quam leo ut lectus. Etiam accumsan tellus eu hendrerit posuere. Aliquam erat volutpat. Donec fermentum purus odio, vel sodales sapien lobortis eu. Sed neque tellus, sagittis id scelerisque at, luctus ac felis.&lt;/span&gt;&lt;br&gt;&lt;/p&gt;', '1605833220_bensound-creativeminds.mp3', '1605833220_m1.jpg', 2147483647),
(3, 1, 1, 'She Loves Control', 'Camila Cabello', '', '1618665240_Camila Cabello - She Loves Control (Audio).mp3', 'default_cover.jpg', 2147483647),
(4, 1, 1, 'DJ Snake - Taki Taki', 'Selena Gomez, Ozuna, Cardi', '&lt;p&gt;DJ Snake - Taki Taki (8D AUDIO) ft. Selena Gomez, Ozuna, Cardi B&lt;br&gt;&lt;/p&gt;', '1618737060_DJ Snake - Taki Taki (8D AUDIO) ft. Selena Gomez, Ozuna, Cardi B.mp3', '1618737060_taki taki photo.jpg', 2147483647),
(5, 1, 1, 'Cartoon - On & On', 'Daniel feat. Levi', '&lt;p&gt;Cartoon - On &amp;amp; On (feat. Daniel Levi) NCS Release&lt;br&gt;&lt;/p&gt;', '1618737540_Cartoon - On & On (feat. Daniel Levi) NCS Release.mp3', '1618737540_cartoon song photo.jpg', 2147483647),
(6, 1, 1, 'Boyfriend', 'Justin Bieber', '&lt;p&gt;Justin Bieber - Boyfriend&lt;br&gt;&lt;/p&gt;', '1618737840_27 Justin Bieber - Boyfriend (www.SongsLover.pk).mp3', '1618737840_Justin-Bieber-Boyfriend.jpg', 2147483647),
(7, 1, 1, 'No Promises ', 'Shayne Ward', '&lt;p&gt;Shayne Ward - No Promises&amp;nbsp;&lt;br&gt;&lt;/p&gt;', '1618738380_Shayne Ward - No Promises (Video)[via torchbrowser.com].mp3', '1618738380_maxresdefault.jpg', 2147483647),
(8, 1, 1, 'Worth It ft. Kid Ink', 'Fifth Harmony', '&lt;p&gt;Fifth Harmony - Worth It ft. Kid Ink&lt;br&gt;&lt;/p&gt;', '1618738680_Fifth Harmony - Worth It ft. Kid Ink[via torchbrowser.com].mp3', '1618738680_6a358e3741771ca4c8a341e1830ee0a7.png', 2147483647),
(9, 1, 2, 'Heenaya', 'Suwahas Ft Smokio', '&lt;p&gt;Heenaya - Suwahas Ft Smokio&lt;br&gt;&lt;/p&gt;', '1618739100_Heenaya - Suwahas Ft Smokio.mp3', '1618739100_Heenaya-Suwahas-Ft-Smokio-.jpg', 2147483647),
(10, 1, 2, 'Sia - Big Girls Cry', 'Vidya Vox', '&lt;p&gt;Sia - Big Girls Cry _ Kabhi Jo Badal (Vidya Vox Mashup Cover)&lt;br&gt;&lt;/p&gt;', '1618739400_shape_of_you_and_tu_cheez_badi_hai_by_vidya_vox_cover_song.mp3', '1618739400_x1080.jfif', 2147483647),
(11, 1, 1, 'positions', 'Ariana Grande', '', '1618845660_Ariana Grande - Positions (Pendona.com).mp3', '1618845660_ariana.jpg', 2147483647);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(30) NOT NULL,
  `firstname` varchar(200) NOT NULL,
  `lastname` varchar(200) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `contact` varchar(50) NOT NULL,
  `address` text NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` text NOT NULL,
  `type` int(1) NOT NULL DEFAULT 2,
  `profile_pic` text NOT NULL,
  `date_created` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `firstname`, `lastname`, `gender`, `contact`, `address`, `email`, `password`, `type`, `profile_pic`, `date_created`) VALUES
(1, 'Administrator', '', 'Male', '+123546879', '', 'admin@admin.com', '0192023a7bbd73250516f069df18b500', 1, '', '2020-11-18 16:50:06'),
(3, 'Janith', 'Pasindu', 'Male', '0775009000', 'No 439/3,Highlevelrd,Nawinna,Maharagama', 'k2pasindu@gmail.com', '33e4be6079d166d33d1f58ce3211d1d4', 2, '1618593180_Cricket2 (1).jpg', '2021-04-16 13:13:20'),
(4, 'test1', 'test2', 'Male', '07145652899', 'navinna', 'test@gmail.com', 'cc03e747a6afbbcbf8be7668acfebee5', 2, '1618593420_6768f1517065ef34ae8ef366ba92677c.jpg', '2021-04-16 13:17:57'),
(5, 'Jayani', 'Dharmasena', 'Female', '0766506625', '44/1/32,  Kompayahena Road,  Tharanga place, Panagoda, Godagama', 'jayaninirupama.98@gmail.com', '7ef97a56d3c6bf04faa4e3e8baa71b7d', 2, '1618631460_wood texture.jpg', '2021-04-16 23:51:36'),
(6, 'Suresh', 'Dilhan', 'Male', '0716625326', 'NO-573, Sisil Uyana, Udagama', 'dilhan9g@gmail.com', '25d55ad283aa400af464c76d713c07ad', 3, '1618636860_1600942755544.jpg', '2021-04-17 01:21:28'),
(7, 'Aashenrashmina', 'paranamana', 'Male', '07662213134', '744/33 ARALIYA MAWATHA\r\nwalgama', 'aashenrashminaparanamana@gmail.com', '523958b27dedf56c57f1669520574022', 2, '1618638480_IMG_20210323_103722.jpg', '2021-04-17 01:48:25'),
(8, 'Yuwantha', 'Jayasinghe', 'Male', '0763610220', 'Kottawa', 'yuwantharavindu@outlook.com', 'e10adc3949ba59abbe56e057f20f883e', 2, '', '2021-04-17 01:53:08'),
(10, 'manusha', 'umayanga', 'Male', '0872626227', 'jahajajjj@gmail.com', 'bssjsjsjss', 'b002a688af5c97e632a5adce23988189', 2, '', '2021-04-18 07:36:39'),
(12, 'mmm', 'mmmm', 'Male', '627282282', 'mn@gmail.com', 'hshsjssjs', 'b26b5e32618f5635072972a088e53516', 2, '', '2021-04-18 07:39:29'),
(13, 'bajjaja', 'hssokss', 'Male', '346788', 'ad@gmail.com', 'nshsjjsjs', '0192023a7bbd73250516f069df18b500', 2, '', '2021-04-18 07:41:26'),
(14, 'mamaman', 'jjhwhs', 'Male', '6272828', 'bsbsjnjs', 'kk@gmil.com', '88cdb337f8c62dc69c1aee4066f80bf5', 2, '', '2021-04-18 07:45:59'),
(15, 'umayanga', 'hsjajaisisjjw', 'Male', '627282882', 'jsssjsjs', 'uma@gmail.com', '94964d16964622c8a5f605bf7f82bc73', 2, '', '2021-04-18 07:47:09'),
(16, 'Janith', 'Pasindu', 'Female', '0775009000', 'No 439/3,Highlevelrd,Nawinna,Maharagama', 'k2pasindu1@gmail.com', '25f9e794323b453885f5181f1b624d0b', 2, '', '2021-04-19 09:43:08'),
(17, 'jayani', 'nirupama', 'Female', '0766506625', 'godagama', 'jayaninirupama.98@gmail.com', '7ef97a56d3c6bf04faa4e3e8baa71b7d', 2, '', '2021-04-19 14:58:16'),
(19, 'gghgkjkjg', 'dharmasena', 'Female', '0785674432', '44/1/32,  Kompayahena Road,  Tharanga place, Panagoda, Godagama', 'jayani@gmail.com', '6c2e124fd664c2bed4b5e40a88fbe1ef', 2, '', '2021-04-19 11:55:14'),
(20, 'yeshani', 'dulakshika', 'female', '0783452213', '', 'yeshani@gmail.com', '5fc74a538709a0c40283d7bf91d4e631', 2, '', '2021-04-19 16:49:41'),
(21, 'jsjsjjs', 'hsjsjja', 'Male', 'bsjssa', '', 'sdd@gmail.com', 'e256ee7e3632912a9c92d7b6a0e5da1c', 2, '', '2021-04-20 18:08:47');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `genres`
--
ALTER TABLE `genres`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `playlist`
--
ALTER TABLE `playlist`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `playlist_items`
--
ALTER TABLE `playlist_items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `uploads`
--
ALTER TABLE `uploads`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `genres`
--
ALTER TABLE `genres`
  MODIFY `id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `playlist`
--
ALTER TABLE `playlist`
  MODIFY `id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `playlist_items`
--
ALTER TABLE `playlist_items`
  MODIFY `id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `uploads`
--
ALTER TABLE `uploads`
  MODIFY `id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
