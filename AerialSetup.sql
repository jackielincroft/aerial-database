-- create schema aerial;
use aerial;

drop table if exists move_synonym;
drop table if exists move_type;
drop table if exists move;
drop table if exists apparatus_synonym;
drop table if exists user_apparatus;
drop table if exists user;
drop table if exists apparatus;

create table apparatus (
	apparatus_id int primary key auto_increment,
    name varchar(50) not null unique,
    spins tinyint default false
);

insert into apparatus values 
	(1, "silks", true),
	(2, "trapeze", false),
    (4, "hammock", true),
    (5, "cloud swing", false),
    (6, "lyra", true),
    (8, "pole", true),
    (9, "chinese pole", false),
    (10, "aerial scaffold", false),
    (11, "flying trapeze", false),
    (12, "rope", false);
    
create table apparatus_synonym (
	apparatus_synonym_id int primary key auto_increment,
    name varchar(50) not null,
    region varchar(50),
    apparatus_id int not null,
    constraint apparatus_synonym_fk_apparatus foreign key (apparatus_id) references apparatus (apparatus_id)
);

insert into apparatus_synonym values 
	(1, "fabric", null, 1),
    (2, "tissue", null, 1),
    (3, "hoop", null, 6),
    (4, "fabric", null, 1),
    (5, "cordelisse", null, 12);


create table move_type (
	move_type_id int primary key auto_increment,
    name varchar(50) not null unique
);

insert into move_type values
	(1, "split"),
    (2, "pose"),
    (3, "drop"),
    (4, "open drop"),
    (5, "beat"),
    (6, "spin"),
    (8, "climb/mount"),
    (9, "roll"),
    (10, "hang");

create table user (
	user_id int primary key auto_increment,
    email varchar(50) not null unique,
    password varchar(50) not null,
    username varchar(50) not null,
    dob date,
    level enum("beginner", "intermediate", "advanced") default "beginner"
);

insert into user values 
	(1, "user@email.com", "secret", "test_user", '1995-03-12', "intermediate"),
    (2, "user2@email.com", "supersecret", "test_user2", '2001-04-05', "beginner");


create table user_apparatus (
	user_id int not null,
    apparatus_id int not null,
    primary key (user_id, apparatus_id),
    is_primary_apparatus tinyint default 0,
    started_training date
);

insert into user_apparatus values
	(1, 2, true, null),
    (2, 1, true, '2016-08-01'),
    (3, 6, false, '2018-04-01');
    
    
create table move (
	move_id int primary key auto_increment,
    name varchar(50) not null,
    difficulty_level enum("beginner", "intermediate", "advanced") default "intermediate",
    apparatus_id int not null,
    move_type_id int not null,
    uses_inversion tinyint default false,
    is_dynamic tinyint default true,
    builds_off int,
    added_by int default null,
    constraint move_fk_apparatus foreign key (apparatus_id) references apparatus (apparatus_id),
    constraint move_fk_move_type foreign key (move_type_id) references move_type (move_type_id),
    constraint move_fk_move foreign key (builds_off) references move (move_id),
    constraint move_fk_user foreign key (added_by) references user (user_id)
);

insert into move (move_id, name, difficulty_level, apparatus_id, move_type_id, uses_inversion, is_dynamic, builds_off) values
	(1, "french climb", "beginner", 1, 8, 0, 1, null),
    (2, "russian climb", "beginner", 1, 8, 0, 1, null),
    (3, "single footlock", "beginner", 1, 2, 0, 0, null),
    (4, "double footlocks", "beginner", 1, 2, 0, 0, null),
    (5, "dancer's footlocks", "beginner", 1, 2, 0, 0, 4),
    (6, "same-side straddle climb", "intermediate", 1, 8, 1, 1, null),
    (7, "opposite-side straddle climb", "intermediate", 1, 8, 1, 1, null),
    (8, "catcher's lock", "beginner", 1, 2, 1, 0, 6),
    (9, "tourniquet", "beginner", 1, 2, 1, 0, 8),
    (10, "hip key", "beginner", 1, 2, 0, 0, 7),
    (11, "tourni-catcher's drop", "beginner", 1, 3, 1, 1, 8),
    (12, "hip key salto", "beginner", 1, 3, 0, 1, 10),
    (13, "splits roll-up", "beginner", 1, 9, 0, 1, 4),
    (14, "music box", "beginner", 1, 9, 0, 1, 3),
    (15, "hammock", "beginner", 1, 2, 0, 0, 10),
    (16, "window seat", "beginner", 1, 2, 0, 0, 15),
    (17, "archer", "beginner", 1, 2, 0, 0, 10),
    (18, "tourniquet single knee hang", "beginner", 1, 10, 0, 0, 9),
    (19, "crossback straddle", "intermediate", 1, 2, 1, 0, 4),
    (20, "flying squirrel", "intermediate", 1, 3, 1, 1, 19),
    (21, "egg-beater wraps", "beginner", 1, 2, 0, 0, null),
    (22, "crossy-back straddle", "intermediate", 1, 2, 1, 0, 21),
    (23, "break beat", "advanced", 1, 5, 1, 1, null),
    (24, "straddle beats", "intermediate", 1, 5, 1, 1, null),
    (25, "pike beats", "intermediate", 1, 5, 0, 1, null),
    (26, "tick tock beats", "beginner", 1, 5, 0, 1, null),
    (27, "single star drop", "beginner", 1, 3, 1, 1, 8),
    (28, "pencil drop", "beginner", 1, 3, 1, 1, 27),
    (29, "1.5 star drop", "intermediate", 1, 3, 1, 1, 27),
    (30, "s-wrap", "intermediate", 1, 9, 1, 1, null),
    (31, "black hole drop", "advanced", 1, 3, 1, 1, 30),
    (32, "double star drop", "intermediate", 1, 3, 1, 1, 30),
    (33, "z-wrap", "intermediate", 1, 9, 1, 1, null),
    (34, "crochet climb", "beginner", 1, 8, 1, 1, null),
    (35, "crochet x-back", "beginner", 1, 2, 1, 0, 34),
    (36, "360 drop", "beginner", 1, 3, 1, 1, 35),
    (37, "720 drop", "intermediate", 1, 3, 1, 1, 36),
    (38, "superman drop", "intermediate", 1, 3, 1, 1, 36),
    (39, "scorpion", "beginner", 1, 2, 0, 0, 21),
    (40, "full monty", "intermediate", 1, 10, 0, 0, 39),
    (41, "can opener split", "beginner", 1, 1, 1, 0, 7),
    (42, "candy cane roll-up", "beginner", 1, 9, 0, 0, 3),
    (43, "rebecca split", "beginner", 1, 1, 0, 0, 3),
    (44, "rebecca split salto", "intermediate", 1, 3, 1, 1, 43),
    (45, "front balance", "intermediate", 1, 2, 0, 0, null),
    (46, "back balance", "intermediate", 1, 2, 1, 0, null),
    (47, "seated slack drop", "beginner", 1, 3, 1, 1, 35),
    (48, "tourniquet swing seat", "beginner", 1, 2, 0, 0, 9),
    (49, "wheeldown", "intermediate", 1, 9, 0, 1, 30);

insert into move (move_id, name, difficulty_level, apparatus_id, move_type_id, uses_inversion, is_dynamic, builds_off) values
	(100, "knee hang", "beginner", 2, 10, 1, 0, null),
    (101, "single knee hang", "intermediate", 2, 10, 1, 0, 100),
    (102, "knee beats", "beginner", 2, 5, 1, 1, null),
    (103, "plank beats", "beginner", 2, 5, 0, 1, null),
    (104, "pike beats", "beginner", 2, 5, 0, 1, null),
    (105, "front balance", "beginner", 2, 2, 0, 0, null),
    (106, "catcher's lock", "beginner", 2, 2, 1, 0, 105),
    (107, "skin the cat", "beginner", 2, 2, 1, 0, null),
    (108, "split under bar", "beginner", 2, 1, 1, 0, null),
    (109, "alien split", "beginner", 2, 1, 1, 0, 108),
    (110, "sitting", "beginner", 2, 2, 0, 0, null),
    (111, "pop off", "intermediate", 2, 2, 0, 1, 110),
    (112, "ankle hang", "beginner", 2, 10, 0, 0, null),
    (113, "toe hang", "intermediate", 2, 10, 0, 0, null),
    (114, "heel hang", "intermediate", 2, 10, 0, 0, null),
    (115, "back balance", "beginner", 2, 2, 0, 0, null),
    (116, "straddleback", "beginner", 2, 2, 1, 0, null),
    (117, "gazelle", "beginner", 2, 2, 1, 0, 110);
    
insert into move (move_id, name, difficulty_level, apparatus_id, move_type_id, uses_inversion, is_dynamic, builds_off) values
	(500, "man in the moon", "beginner", 6, 2, false, false, null),
    (501, "straddleback", "beginner", 6, 2, true, false, null),
    (502, "side mount", "beginner", 6, 8, false, true, null),
    (503, "gazelle", "beginner", 6, 2, false, false, null),
    (504, "mermaid", "beginner", 6, 2, false, false, null),
    (505, "mermaid split", "beginner", 6, 1, false, false, 504),
    (506, "pullover", "beginner", 6, 8, true, true, null),
    (507, "front balance", "beginner", 6, 2, true, false, 506),
    (508, "hip circles", "advanced", 6, 9, true, true, 506),
    (509, "backwards hip circles", "advanced", 6, 9, true, true, 506),
    (510, "one-leg seat", "beginner", 6, 2, false, false, null),
    (511, "windmill", "intermediate", 6, 9, true, true, 510),
    (512, "monkey roll", "beginner", 6, 9, true, true, null);
    
    
create table move_synonym (
	move_synonym_id int primary key auto_increment,
    move_id int not null,
    name varchar(50) not null,
    region varchar(100),
    constraint alternate_name_fk_move foreign key (move_id) references move (move_id)
);

insert into move_synonym values
	(1, 1, "basic climb", "northeastern usa"),
    (2, 10, "hip lock", null),
    (3, 15, "hip key seat", "west coast usa"),
    (4, 19, "straddleback", null),
    (5, 20, "sexy kitty", "west coast usa"),
    (6, 26, "bell beats", "east coast usa"),
    (7, 31, "reverse double star drop", null),
    (8, 39, "half monty", null),
    (9, 40, "double ankle hang", null),
    (10, 48, "tourniquet hammock", "northeastern usa"),
    (11, 49, "windmill", "east coast usa"),
    (12, 511, "mill circle", "east coast usa"),
    (13, 510, "horse", "northeastern usa")
