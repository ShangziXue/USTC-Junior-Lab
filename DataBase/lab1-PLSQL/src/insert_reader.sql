begin
  insert into Reader values('RMA001', 'Navas', 32, '��˹�����');
  insert into Reader values('RMA020', 'Asensio', 22, '������');
  insert into Reader values('RMA004', 'Ramos', 32, '������');
  insert into Reader values('RMA007', 'Ronaldo', 33, '������');
  insert into Reader values('RMA009', 'Benzema', 31, '����');
  insert into Reader values('RMA011', 'Bale', 32, '����ʿ');
  insert into Reader values('RMA008', 'Kroos', 28, '�¹�');
  insert into Reader values('RMA010', 'Modric', 33, '���޵���');
  insert into Reader values('RMA098', 'Rose', 24, 'Ӣ��');
  insert into Reader values('RMA099', '����', 21, '�й�');
  commit;
  dbms_output.put_line('����ɹ���');
exception
  when Dup_val_on_index Then
    dbms_output.put_line('����ʧ��,�����Ѵ���');
  when others Then
    dbms_output.put_line('����ʧ��,δ֪ԭ��');
end;