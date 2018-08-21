package com.xyzs.springbootwxsd.repository;

import com.xyzs.springbootwxsd.dataobj.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
    Page<OrderMaster> findByBuyerOpenid(String buyerId, Pageable pageable);
}
