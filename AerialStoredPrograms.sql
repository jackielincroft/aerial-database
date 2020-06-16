use aerial;

-- Get the moves that build off of the given move.
delimiter //
create procedure get_next_moves(apparatusName varchar(50), moveName varChar(50)) 
begin 
select 
	m1.name as 'next_move', 
	m1.difficulty_level as 'difficulty',
    m1.is_dynamic as 'is_dynamic',
    m1.uses_inversion as 'uses_inversion',
    mt.name as 'move_type'
from move m1 join move m2 on (m1.builds_off = m2.move_id)
	join apparatus a on (m1.apparatus_id = a.apparatus_id)
    join move_type mt on (m1.move_type_id = mt.move_type_id)
where a.name = apparatusName and m2.name like moveName;
end //
delimiter ;

-- Find the moves that this move builds off of.
delimiter //
create procedure get_prerequisites(apparatusName varchar(50), startingMove varchar(50))
begin
declare prereq_id int;
declare prereq_name varchar(50);

select m1.builds_off, m2.name
into prereq_id, prereq_name
from move m1 join apparatus a using (apparatus_id)
	join move m2 on (m1.builds_off = m2.move_id)
where a.name = apparatusName and m.name like startingMove;

if first_prereq_id is not null then
	select prereq_name;
end if;
end //
delimiter ;

-- 