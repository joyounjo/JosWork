package com.tsrs.webedi.modular.system.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 通知dao
 *
 * @author Tsrs-WebEDI
 * @date 2017-05-09 23:03
 */
public interface NoticeDao {

    List<Map<String, Object>> list(@Param("condition") String condition);
}
