package com.xyzs.springbootwxsd.dataobj;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 产品分类
 */
@Data //创建GetSet方法
@Entity //声明实体类
@DynamicUpdate //动态更新
public class ProductCategory {
    /**
     * 产品分类ID
     */
    @Id //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键自增
    private Integer categoryId;
    /**
     * 产品分类名称
     */
    private String categoryName;
    /**
     * 产品分类类型
     */
    private Integer categoryType;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
