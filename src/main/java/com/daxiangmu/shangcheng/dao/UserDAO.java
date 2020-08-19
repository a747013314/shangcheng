package com.daxiangmu.shangcheng.dao;

import org.apache.ibatis.annotations.Param;

public interface UserDAO {

    void insertT_user_sort(String us_name);
    
    void insertUser();
}
