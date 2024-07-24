create tablespace show datafile 'E:\database\show.dbf' size 20m;
create user member identified by member1919;
Grant connect, resource to member;
alter user member default tablespace show;
Grant dba to member;