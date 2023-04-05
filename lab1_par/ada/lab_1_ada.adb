with Ada.Text_IO; use Ada.Text_IO;

procedure Main is

   Step : Long_Long_Integer := 2; -- Step variable

   can_stop : boolean := false;
   pragma Atomic(can_stop);

   task type break_thread;
   task type main_thread(id : Integer);

   task body break_thread is
   begin
      delay 5.0;
      can_stop := true;
   end break_thread;

   task body main_thread is
      sum : Long_Long_Integer := 0;
      count : Long_Long_Integer := 0;
   begin
      loop
         sum := sum + Step;
         count := count + 1;
         exit when can_stop;
      end loop;
      delay 1.0;
      Ada.Text_IO.Put_Line(id'Img  & " - " & sum'Img & " Loop Count = " & count'Img);
   end main_thread;

   b1 : break_thread;
   t1 : main_thread(0);
   t2 : main_thread(1);
   t3 : main_thread(2);
   t4 : main_thread(3);
   t5 : main_thread(4);
   t6 : main_thread(5);
   t7 : main_thread(6);
   t8 : main_thread(7);
begin
   null;
end Main;
