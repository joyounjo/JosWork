package com.tsrs.webedi.modular.system.warpper;

import com.tsrs.webedi.common.constant.factory.ConstantFactory;
import com.tsrs.webedi.common.constant.state.IsMenu;
import com.tsrs.webedi.common.warpper.BaseControllerWarpper;

import java.util.List;
import java.util.Map;

/**
 * 菜单列表的包装类
 *
 * @author Tsrs-WebEDI
 * @date 2017年2月19日15:07:29
 */
public class MenuWarpper extends BaseControllerWarpper {

    public MenuWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        map.put("statusName", ConstantFactory.me().getMenuStatusName((Integer) map.get("status")));
        map.put("isMenuName", IsMenu.valueOf((Integer) map.get("ismenu")));
    }

}
