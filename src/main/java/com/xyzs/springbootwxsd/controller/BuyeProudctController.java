package com.xyzs.springbootwxsd.controller;

import com.xyzs.springbootwxsd.VO.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buye/proudct")
public class BuyeProudctController {
    public ResultVO list() {
        ResultVO resultVO = new ResultVO();
        return resultVO;
    }
}
