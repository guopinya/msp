use acg;

# 创建存储过程 pro_add_column
create procedure pro_add_column()
begin
    # 判断 auction 表是否存在 bottom_price 字段，不存在则新增
    if not exists(select 1 from information_schema.columns where table_name = 'auction' and column_name = 'bottom_price') then
        alter table auction add bottom_price bigint(20) null comment '保底价';
    end if;
    # 判断 auction 表是否存在 place_of_delivery 字段，不存在则新增
    if not exists(select 1 from information_schema.columns where table_name = 'auction' and column_name = 'place_of_delivery') then
        alter table auction add place_of_delivery varchar(100) null comment '发货地';
    end if;
    # 判断 cate 表是否存在 is_all_cate 字段，不存在则新增
    if not exists(select 1 from information_schema.columns where table_name = 'cate' and column_name = 'is_all_cate') then
        alter table cate add is_all_cate tinyint(1) default 0 null comment '是否全部分类';
    end if;

end;

# 创建存储过程 pro_drop_column
create procedure pro_drop_column()
begin
    # 判断 auction表是否存在 is_allow_returns字段，存在则删除
    if exists(select 1 from information_schema.columns where table_name = 'auction' and column_name = 'is_allow_returns') then
        alter table auction drop column is_allow_returns;
    end if;
end;

# 创建存储过程 pro_modify_column
create procedure pro_modify_column()
begin
    # 判断 auction表是否存在 composite_index字段，存在则修改
    if exists(select 1 from information_schema.columns where table_name = 'auction' and column_name = 'composite_index') then
        alter table auction modify composite_index double default 0 null comment '综合指数';
    end if;
end;

# 调用并删除存储过程
call pro_add_column;
call pro_drop_column;
call pro_modify_column;
# 删除存储过程
drop procedure pro_add_column;
drop procedure pro_drop_column;
drop procedure pro_modify_column;