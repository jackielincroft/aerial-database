
-- I'm teaching a beginner silks lesson, and I want some easy poses that don't require inverting.
select m.name, mt.name, is_dynamic
from move m join apparatus a using (apparatus_id)
	join move_type mt using (move_type_id)
where a.name = "silks" and m.difficulty_level = "beginner";


-- I finally learned how to do a pullover on lyra!  What other moves can I work on that build off of it?
select 
	m1.name as 'new_move', 
	m1.difficulty_level as "new_move_difficulty", 
	m2.name as 'builds_off'
from move m1 join move m2 on (m1.builds_off = m2.move_id)
	join apparatus a on (m1.apparatus_id = a.apparatus_id)
where a.name = "lyra" and m2.name = "pullover";


-- I just moved to the east coast – what new regional vocabulary do aerialists use here?
select a.name as 'apparatus', m.name as "move", ms.name as "move_synonym", ms.region
from move m join move_synonym ms using (move_id)
	join apparatus a using (apparatus_id)
where ms.region like "%east%usa";


-- I was totally inspired during open gym the other day, and created a new move to add to the database!



-- I'm trying to build a silks routine.  I'd like to start with same-side straddle climb, and pick a sequence of 3 or 4 moves.
-- Show me moves that build off of a same-side straddle climb.
call get_next_moves("silks", "same-side straddle climb");
-- Okay, now show me moves that build off of catcher's lock.
call get_next_moves("silks", "catcher's lock");
-- Okay, I'll go into tourniquet next.  What moves can I do after that?
call get_next_moves("silks", "tourniquet");
