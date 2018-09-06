package com.xyzs.springbootwxsd.converter;

import com.xyzs.springbootwxsd.dataobj.OrderMaster;
import com.xyzs.springbootwxsd.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMastr2OrderDTOConverter {
    public static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
//        List<OrderDTO> orderDTOList=new ArrayList<>();
//        orderMasterList.forEach(v->{
//            orderDTOList.add(convert(v));
//        });
//        return orderDTOList;

        return orderMasterList.stream().map(v -> convert(v)).collect(Collectors.toList());

    }
}
