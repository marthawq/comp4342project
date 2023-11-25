CREATE DATABASE IF NOT EXISTS `community` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `community`;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `userId`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'primary key',
    `username`        varchar(128)        NOT NULL DEFAULT '' COMMENT 'username',
    `email`           varchar(128) UNIQUE NOT NULL DEFAULT '' COMMENT 'user email',
    `password`        varchar(128)        NOT NULL DEFAULT '' COMMENT 'user password',
    `score`           bigint(128)        NOT NULL DEFAULT 0   COMMENT 'user score',
    PRIMARY KEY (`userId`, `email`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='user table';

INSERT INTO `user`
(username, email, password, score) values ('Dane', 'dhitzmann0@chronoengine.com', '1941IMX', 5);
insert into user (username, email, password, score) values ('Marv', 'mgillespey1@so-net.ne.jp', '7440NnBM', 4);
insert into user (username, email, password, score) values ('Jude', 'jlongstreet2@bbb.org', '3081+)', 8);
insert into user (username, email, password, score) values ('Merralee', 'maldington3@fc2.com', '5356|', 5);
insert into user (username, email, password, score) values ('Wood', 'wmeggison4@psu.edu', '6161G', 8);
insert into user (username, email, password, score) values ('Jaime', 'jgayden5@dedecms.com', '4241r', 9);
insert into user (username, email, password, score) values ('Gustave', 'ggreguol6@rakuten.co.jp', '8899', 5);
insert into user (username, email, password, score) values ('Hana', 'hpullinger7@vinaora.com', '9778K?7F', 4);
insert into user (username, email, password, score) values ('Cart', 'cganforthe8@washingtonpost.com', '9715ov', 5);
insert into user (username, email, password, score) values ('Margo', 'mpatters9@sphinn.com', '4933GB', 5);
insert into user (username, email, password, score) values ('Nelli', 'nrosenbuscha@nifty.com', '2845A', 9);
insert into user (username, email, password, score) values ('Cirilo', 'cmacavaddyb@wiley.com', '1296at', 9);
insert into user (username, email, password, score) values ('Rebeka', 'rbrisbanec@gnu.org', '7118?(', 4);
insert into user (username, email, password, score) values ('Marcie', 'mtrynord@pcworld.com', '4171q`MU', 5);
insert into user (username, email, password, score) values ('Mureil', 'mokuddyhye@live.com', '9480J', 6);
insert into user (username, email, password, score) values ('Woodie', 'wbrunettif@ocn.ne.jp', '0531N', 7);
insert into user (username, email, password, score) values ('Janaya', 'jkidstong@tamu.edu', '7089zFJ', 3);
insert into user (username, email, password, score) values ('Cointon', 'cnaishh@360.cn', '4336wCa', 1);
insert into user (username, email, password, score) values ('Kelsey', 'kforresti@weather.com', '5985n', 5);
insert into user (username, email, password, score) values ('Collette', 'cmalloyj@webs.com', '9276ZH((', 4);
insert into user (username, email, password, score) values ('Eddie', 'ebendelk@ycombinator.com', '3493', 5);
insert into user (username, email, password, score) values ('Emmey', 'edodsleyl@mlb.com', '4306vS,h', 6);
insert into user (username, email, password, score) values ('Ebenezer', 'erowthornm@unc.edu', '2272a1U', 6);
insert into user (username, email, password, score) values ('Ronni', 'rdarnodyn@people.com.cn', '2934.', 5);
insert into user (username, email, password, score) values ('Joe', 'jcuardallo@drupal.org', '84750', 4);
insert into user (username, email, password, score) values ('Lucita', 'lfolinip@networksolutions.com', '1386.', 6);
insert into user (username, email, password, score) values ('Farrah', 'fbeltonq@mit.edu', '75922>O5', 4);
insert into user (username, email, password, score) values ('Joli', 'jchesshirer@slideshare.net', '7916{uV', 5);
insert into user (username, email, password, score) values ('Myca', 'memminss@google.co.jp', '6606_', 6);
insert into user (username, email, password, score) values ('Royce', 'rvedikhovt@umn.edu', '0828''', 10);
insert into user (username, email, password, score) values ('Kirbee', 'kjenkinsonu@google.ru', '3612IUr', 1);
insert into user (username, email, password, score) values ('Gage', 'gyoungerv@discovery.com', '3592', 4);
insert into user (username, email, password, score) values ('Sebastiano', 'snieselw@digg.com', '6915g', 2);
insert into user (username, email, password, score) values ('Darrin', 'dfishlyx@slate.com', '9822cx.Q', 5);
insert into user (username, email, password, score) values ('Orlan', 'ocapseyy@imdb.com', '20188T1', 1);
insert into user (username, email, password, score) values ('Tedda', 'tillemz@issuu.com', '4878', 6);
insert into user (username, email, password, score) values ('Aubert', 'agosker10@spotify.com', '8814', 6);
insert into user (username, email, password, score) values ('Janean', 'jgiraudat11@ted.com', '4926<aEW', 2);
insert into user (username, email, password, score) values ('Kanya', 'ksarchwell12@pinterest.com', '3217>pR', 4);
insert into user (username, email, password, score) values ('Cyril', 'cjudkin13@cam.ac.uk', '4766', 9);
insert into user (username, email, password, score) values ('Elnora', 'eaxby14@webs.com', '0727ri', 3);
insert into user (username, email, password, score) values ('Brucie', 'bivel15@sciencedirect.com', '4087wb0w', 1);
insert into user (username, email, password, score) values ('Sherman', 'sstoak16@about.com', '1631MY!+', 8);
insert into user (username, email, password, score) values ('Siana', 'sfossett17@stumbleupon.com', '88776O}', 1);
insert into user (username, email, password, score) values ('Reggy', 'rprecious18@unblog.fr', '5734MQu', 1);
insert into user (username, email, password, score) values ('Nevin', 'npynner19@umn.edu', '0093psz', 8);
insert into user (username, email, password, score) values ('Keeley', 'kstair1a@ebay.co.uk', '3510', 9);
insert into user (username, email, password, score) values ('Halley', 'hbrydson1b@example.com', '68568', 6);
insert into user (username, email, password, score) values ('Gloriana', 'gbarlow1c@g.co', '58697hKV', 4);
insert into user (username, email, password, score) values ('Thea', 'tbellenger1d@odnoklassniki.ru', '6048}', 2);
insert into user (username, email, password, score) values ('Glennie', 'gwhittock1e@rediff.com', '2806C', 5);
insert into user (username, email, password, score) values ('Nanete', 'nsharpus1f@opera.com', '0298&(t', 4);
insert into user (username, email, password, score) values ('Farica', 'fhazelhurst1g@google.cn', '4491"4,G', 4);
insert into user (username, email, password, score) values ('Shelli', 'smaylard1h@hc360.com', '0355d', 6);
insert into user (username, email, password, score) values ('Marris', 'mgreger1i@desdev.cn', '1778}', 2);
insert into user (username, email, password, score) values ('Abeu', 'ameriel1j@canalblog.com', '43033jda', 9);
insert into user (username, email, password, score) values ('Lucia', 'lmosdell1k@desdev.cn', '4079I"', 9);
insert into user (username, email, password, score) values ('Glenn', 'gscryne1l@privacy.gov.au', '1892Tj', 8);
insert into user (username, email, password, score) values ('Christian', 'cblazy1m@hubpages.com', '2478', 2);
insert into user (username, email, password, score) values ('Malchy', 'mantliff1n@live.com', '5253G', 3);
insert into user (username, email, password, score) values ('Garret', 'gbriskey1o@behance.net', '2691zMo', 3);
insert into user (username, email, password, score) values ('Darren', 'dbattison1p@artisteer.com', '3954', 5);
insert into user (username, email, password, score) values ('Shurlocke', 'spundy1q@php.net', '3260', 8);
insert into user (username, email, password, score) values ('Dottie', 'dchevolleau1r@economist.com', '1561#oq''', 8);
insert into user (username, email, password, score) values ('Lilllie', 'lsloyan1s@discuz.net', '4993"', 8);
insert into user (username, email, password, score) values ('Sandy', 'scotilard1t@dailymotion.com', '9662p', 5);
insert into user (username, email, password, score) values ('Clark', 'cshorten1u@gravatar.com', '55872I)', 4);
insert into user (username, email, password, score) values ('Jo', 'jfatkin1v@mit.edu', '1001', 7);
insert into user (username, email, password, score) values ('Zelda', 'zfist1w@tamu.edu', '5634ys', 3);
insert into user (username, email, password, score) values ('Elysha', 'eeggleston1x@usnews.com', '4440''%', 1);
insert into user (username, email, password, score) values ('Mycah', 'mbineham1y@washington.edu', '3183(Xyw', 9);
insert into user (username, email, password, score) values ('Dionisio', 'dbottrill1z@squidoo.com', '9789?G', 2);
insert into user (username, email, password, score) values ('Oren', 'oelsbury20@businesswire.com', '1310k,tL', 4);
insert into user (username, email, password, score) values ('Malchy', 'mpunter21@nydailynews.com', '0827', 1);
insert into user (username, email, password, score) values ('Harry', 'hcovelle22@vinaora.com', '1869', 8);
insert into user (username, email, password, score) values ('Eirena', 'ehazlegrove23@amazon.co.uk', '0169Y|p', 7);
insert into user (username, email, password, score) values ('Sonnnie', 'sdudeney24@parallels.com', '2546l', 7);
insert into user (username, email, password, score) values ('Blythe', 'bbussens25@pinterest.com', '1729', 1);
insert into user (username, email, password, score) values ('Kial', 'kmoakes26@skype.com', '5813G*+A', 8);
insert into user (username, email, password, score) values ('Ilario', 'icruickshanks27@1und1.de', '6242q''2', 2);
insert into user (username, email, password, score) values ('Rozalie', 'rmerrick28@sbwire.com', '5942NQ0', 5);
insert into user (username, email, password, score) values ('Rozina', 'rshilleto29@arstechnica.com', '7785|4', 3);
insert into user (username, email, password, score) values ('Thibaut', 'tbakewell2a@fda.gov', '7595)s`<', 5);
insert into user (username, email, password, score) values ('Rowen', 'rkochl2b@omniture.com', '4683d7', 4);
insert into user (username, email, password, score) values ('Kris', 'klacoste2c@usgs.gov', '8458jP0', 9);
insert into user (username, email, password, score) values ('Gerhard', 'gcaselick2d@reference.com', '1328', 6);
insert into user (username, email, password, score) values ('Rafaela', 'rbreakwell2e@diigo.com', '8342X', 9);
insert into user (username, email, password, score) values ('Mignon', 'mpods2f@geocities.jp', '3842S?A', 8);
insert into user (username, email, password, score) values ('Janaya', 'jstranger2g@zdnet.com', '1916f&', 8);
insert into user (username, email, password, score) values ('Leila', 'lgatland2h@spiegel.de', '8072L', 5);
insert into user (username, email, password, score) values ('Carlie', 'copdenorth2i@slate.com', '4997', 3);
insert into user (username, email, password, score) values ('Victoir', 'vriccioppo2j@topsy.com', '4782y''', 5);
insert into user (username, email, password, score) values ('Hilliary', 'hgomar2k@cbc.ca', '7486"rt.', 2);
insert into user (username, email, password, score) values ('Dorita', 'dmackenzie2l@facebook.com', '92146', 1);
insert into user (username, email, password, score) values ('Giralda', 'ghaker2m@wisc.edu', '46651Obq', 7);
insert into user (username, email, password, score) values ('Dukey', 'dlackey2n@e-recht24.de', '6647w"', 4);
insert into user (username, email, password, score) values ('Sandi', 'sbosden2o@unicef.org', '9916y3B+', 1);
insert into user (username, email, password, score) values ('Bruce', 'baronow2p@issuu.com', '9468L5}', 3);
insert into user (username, email, password, score) values ('Winonah', 'wsymon2q@loc.gov', '9875`2*', 3);
insert into user (username, email, password, score) values ('Tedi', 'tmcgrann2r@acquirethisname.com', '8957Z"', 8);
insert into user (username, email, password, score) values ('Kalila', 'kmccarroll2s@mayoclinic.com', '2810i?v', 9);
insert into user (username, email, password, score) values ('Prudy', 'pkassman2t@cargocollective.com', '4114Tk<', 6);
insert into user (username, email, password, score) values ('Cicely', 'cwethey2u@histats.com', '21204D37', 7);
insert into user (username, email, password, score) values ('Pauline', 'pbarstow2v@usatoday.com', '6314.M', 1);
insert into user (username, email, password, score) values ('Karita', 'ktimeby2w@wikispaces.com', '4902r', 2);
insert into user (username, email, password, score) values ('Ilyse', 'ipeters2x@elpais.com', '0676b6', 7);
insert into user (username, email, password, score) values ('Porter', 'pspeakman2y@weather.com', '7118', 9);
insert into user (username, email, password, score) values ('Wallie', 'wlipscomb2z@cloudflare.com', '8924NnC', 2);
insert into user (username, email, password, score) values ('Donal', 'dprayer30@samsung.com', '3617Qi', 4);
insert into user (username, email, password, score) values ('Myer', 'mkernoghan31@whitehouse.gov', '1210n', 6);
insert into user (username, email, password, score) values ('Ursala', 'uogglebie32@bizjournals.com', '6009np', 2);
insert into user (username, email, password, score) values ('Teador', 'texposito33@elegantthemes.com', '2661QP"', 7);
insert into user (username, email, password, score) values ('Cyrill', 'cfeak34@meetup.com', '5149C', 1);
insert into user (username, email, password, score) values ('Anallese', 'abuswell35@google.nl', '3031toI', 8);
insert into user (username, email, password, score) values ('Lida', 'lrogerson36@typepad.com', '3357"', 3);
insert into user (username, email, password, score) values ('Ginnie', 'gtunmore37@joomla.org', '61225N`2', 6);
insert into user (username, email, password, score) values ('Jay', 'jelderfield38@miibeian.gov.cn', '6369V', 5);
insert into user (username, email, password, score) values ('Claudine', 'cvagges39@hibu.com', '7846Ff', 9);
insert into user (username, email, password, score) values ('Kary', 'kconyer3a@flickr.com', '8054', 3);
insert into user (username, email, password, score) values ('Ursulina', 'ualwood3b@wiley.com', '5735', 7);
insert into user (username, email, password, score) values ('Randie', 'rlorentz3c@goo.ne.jp', '8047g', 2);
insert into user (username, email, password, score) values ('Devon', 'dcalbaithe3d@rakuten.co.jp', '5883m', 6);
insert into user (username, email, password, score) values ('Maye', 'mcases3e@sphinn.com', '1762=A(A', 6);
insert into user (username, email, password, score) values ('Nana', 'ngilcrist3f@wp.com', '4093o~b', 8);
insert into user (username, email, password, score) values ('Heddi', 'hsacks3g@illinois.edu', '0449xrM', 6);
insert into user (username, email, password, score) values ('Odey', 'omasi3h@simplemachines.org', '2282K', 8);
insert into user (username, email, password, score) values ('Justen', 'jwakenshaw3i@prweb.com', '0572K', 2);
insert into user (username, email, password, score) values ('Carolyne', 'chunday3j@com.com', '7069', 6);
insert into user (username, email, password, score) values ('Eimile', 'ewormell3k@house.gov', '0914!Jq`', 4);
insert into user (username, email, password, score) values ('Bradford', 'bleathe3l@indiegogo.com', '6904Lua', 9);
insert into user (username, email, password, score) values ('Andrej', 'arutigliano3m@sphinn.com', '3113z', 4);
insert into user (username, email, password, score) values ('Annette', 'apearcey3n@wix.com', '3679>', 8);
insert into user (username, email, password, score) values ('Valentina', 'vgrewar3o@blogs.com', '9200', 10);
insert into user (username, email, password, score) values ('Austin', 'astrangeways3p@whitehouse.gov', '8014S*w', 10);
insert into user (username, email, password, score) values ('Celesta', 'ccurgenuer3q@engadget.com', '6977', 9);
insert into user (username, email, password, score) values ('Korey', 'korbine3r@hugedomains.com', '9256', 8);
insert into user (username, email, password, score) values ('Thaxter', 'tschuricht3s@census.gov', '9873Bi', 5);
insert into user (username, email, password, score) values ('Retha', 'rcoda3t@ihg.com', '5681', 9);
insert into user (username, email, password, score) values ('Natalie', 'ncarnew3u@netlog.com', '9612V@fD', 1);
insert into user (username, email, password, score) values ('Kevyn', 'kcorona3v@desdev.cn', '5648z', 10);
insert into user (username, email, password, score) values ('Simon', 'sraspison3w@usatoday.com', '0146n#.', 6);
insert into user (username, email, password, score) values ('Luise', 'lwalklot3x@tmall.com', '5897Odak', 9);
insert into user (username, email, password, score) values ('Sig', 'strevarthen3y@zdnet.com', '2645R', 4);
insert into user (username, email, password, score) values ('Konstantine', 'klarrett3z@google.com', '6300', 8);
insert into user (username, email, password, score) values ('Clem', 'culyatt40@php.net', '6995', 5);
insert into user (username, email, password, score) values ('Alaster', 'adenisyev41@oakley.com', '4407JPeO', 8);
insert into user (username, email, password, score) values ('Harwilll', 'hfarquharson42@oracle.com', '4217oxYD', 3);
insert into user (username, email, password, score) values ('Virge', 'vneill43@rediff.com', '8707', 9);
insert into user (username, email, password, score) values ('Candace', 'clochran44@exblog.jp', '5984On$', 9);
insert into user (username, email, password, score) values ('Sterne', 'sburnyate45@desdev.cn', '1027Dg.z', 1);
insert into user (username, email, password, score) values ('Kahlil', 'kdunkerley46@craigslist.org', '9095E?', 8);
insert into user (username, email, password, score) values ('Filippa', 'ftorrent47@time.com', '1072@aSX', 3);
insert into user (username, email, password, score) values ('Karlik', 'kioselev48@weather.com', '13126D6B', 8);
insert into user (username, email, password, score) values ('Gradey', 'gsemrad49@sfgate.com', '8274U8', 8);
insert into user (username, email, password, score) values ('Matti', 'measman4a@salon.com', '4109L''P', 5);
insert into user (username, email, password, score) values ('Moria', 'mdodd4b@amazonaws.com', '5742Uc', 1);
insert into user (username, email, password, score) values ('Jesselyn', 'jwoolford4c@harvard.edu', '7186', 1);
insert into user (username, email, password, score) values ('Garvin', 'gsparshutt4d@wsj.com', '2702', 5);
insert into user (username, email, password, score) values ('Lane', 'lfusedale4e@ameblo.jp', '0981I*k', 7);
insert into user (username, email, password, score) values ('Missy', 'mrouge4f@hc360.com', '3614.OyP', 8);
insert into user (username, email, password, score) values ('Rosalinda', 'rsacco4g@bluehost.com', '6870o|Vy', 5);
insert into user (username, email, password, score) values ('Zandra', 'zjoskovitch4h@hp.com', '5979,X', 3);
insert into user (username, email, password, score) values ('Chantal', 'ckitchin4i@harvard.edu', '5711D', 2);
insert into user (username, email, password, score) values ('Halley', 'hstronach4j@gov.uk', '9042', 6);
insert into user (username, email, password, score) values ('Hoebart', 'halbertson4k@noaa.gov', '7017', 9);
insert into user (username, email, password, score) values ('Matthiew', 'msoutherton4l@phpbb.com', '15881N~', 9);
insert into user (username, email, password, score) values ('Starr', 'sskatcher4m@google.pl', '5086kV>', 4);
insert into user (username, email, password, score) values ('Augustine', 'arounsefull4n@google.ru', '9733', 1);
insert into user (username, email, password, score) values ('Richie', 'rcapineer4o@irs.gov', '9072|9', 4);
insert into user (username, email, password, score) values ('Cori', 'ctribble4p@google.nl', '4502(', 9);
insert into user (username, email, password, score) values ('Loralee', 'llissandre4q@wikimedia.org', '3230XOp', 6);
insert into user (username, email, password, score) values ('Barbra', 'bmcgrowther4r@tuttocitta.it', '8384l', 1);
insert into user (username, email, password, score) values ('Darci', 'dclemerson4s@netvibes.com', '1694~', 6);
insert into user (username, email, password, score) values ('Ferris', 'fsharphouse4t@1und1.de', '4108$U2', 2);
insert into user (username, email, password, score) values ('Adore', 'alidyard4u@macromedia.com', '8237r3q', 8);
insert into user (username, email, password, score) values ('Zack', 'zwills4v@senate.gov', '6256W', 7);
insert into user (username, email, password, score) values ('Chaunce', 'cdagnall4w@answers.com', '9882', 5);
insert into user (username, email, password, score) values ('Sawyere', 'spitsall4x@hatena.ne.jp', '0260', 2);
insert into user (username, email, password, score) values ('Teriann', 'twastell4y@thetimes.co.uk', '7479$', 8);
insert into user (username, email, password, score) values ('Eirena', 'emcsparran4z@hugedomains.com', '8511K', 9);
insert into user (username, email, password, score) values ('Nancee', 'npole50@icio.us', '8365?0UA', 6);
insert into user (username, email, password, score) values ('Arron', 'ashillitoe51@clickbank.net', '9505,1?D', 3);
insert into user (username, email, password, score) values ('Roseline', 'rfrostdick52@patch.com', '7502R', 9);
insert into user (username, email, password, score) values ('Garrot', 'gkilbee53@phpbb.com', '9721#1O`', 3);
insert into user (username, email, password, score) values ('Marge', 'mgregoli54@eventbrite.com', '5012T.hS', 8);
insert into user (username, email, password, score) values ('Reagan', 'rsneddon55@dot.gov', '9407IyEy', 6);
insert into user (username, email, password, score) values ('Evonne', 'ealdington56@wiley.com', '2169$', 2);
insert into user (username, email, password, score) values ('Cathrin', 'cadnams57@cocolog-nifty.com', '7289WVl', 4);
insert into user (username, email, password, score) values ('Abramo', 'abyrd58@google.pl', '3665$CBX', 4);
insert into user (username, email, password, score) values ('Daisi', 'ddannehl59@bloomberg.com', '5666ad=', 7);
insert into user (username, email, password, score) values ('Scarlett', 'sfancy5a@google.fr', '1135z58', 5);
insert into user (username, email, password, score) values ('Amara', 'abristowe5b@icq.com', '88732{}', 9);
insert into user (username, email, password, score) values ('Valentino', 'vortells5c@so-net.ne.jp', '4165<', 5);
insert into user (username, email, password, score) values ('Nissa', 'nkett5d@amazon.de', '287828', 9);
insert into user (username, email, password, score) values ('Dorrie', 'dshufflebotham5e@wikipedia.org', '8730', 6);
insert into user (username, email, password, score) values ('Ynes', 'yfaustin5f@webnode.com', '5763y', 2);
insert into user (username, email, password, score) values ('Pearle', 'pdillow5g@nps.gov', '1401', 1);
insert into user (username, email, password, score) values ('Binnie', 'bkees5h@1und1.de', '4489H', 8);
insert into user (username, email, password, score) values ('Sarah', 'sjurkowski5i@unicef.org', '5552CP(', 3);
insert into user (username, email, password, score) values ('Mandy', 'mspick5j@csmonitor.com', '3994g', 6);


DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic`
(
    `topicId`    bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'primary key',
    `userId`     bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT 'user key',
    `title`       varchar(128)        NOT NULL default '' COMMENT 'title',
    `content`     text                NOT NULL COMMENT 'content',
    `create_time` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
    PRIMARY KEY (`topicId`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='topic table';

INSERT INTO `topic`
VALUES (1, 1, 'About question 2', 'Hey guys, do you have any idea for this?！', '2023-11-21 14:32:00');

DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`
(
    `postId`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'primary key',
    `topicId`   bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT 'topic id',
    `userId`     bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT 'user id',
    `content`     text                NOT NULL COMMENT 'content',
    `create_time` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'creat time',
    PRIMARY KEY (`postId`),
    INDEX parent_id (`topicId`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='reply table';
INSERT INTO `post`
VALUES (1, 1, 1, 'No idea.', '2023-11-21 14:50:19'),
       (2, 1, 2, 'I think it should be this.', '2023-11-21 15:51:19');