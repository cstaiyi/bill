package com.zcit.common.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zcit.common.enums.Delete;
import com.zcit.common.utils.HttpContextUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hifeng
 * @date 2018/7/27 18:19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BaseEntity<T extends Model, E extends Serializable> extends Model<T> {

    @TableId(value = "id", type = IdType.UUID)
    private E id;

    @TableField("create_time")
    private Date createTime;

    @TableField("creator_id")
    private String creatorId;

    @TableField("update_time")
    private Date updateTime;

    @TableLogic
    private Integer deleted;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    /**
     * 插入之前执行方法，需要手动调用
     */
    public void preInsert() {
        //this.id = (E) IdUtil.uuid();
        try {
            this.creatorId = HttpContextUtil.getHttpServletRequest().getHeader("regId");
        } catch (Exception e) {
            //
        }
        this.createTime = new Date();
        this.updateTime = new Date();
        this.deleted = Delete.UNDELETED.getCode();
    }

    /**
     * 更新之前执行方法，需要手动调用
     */
    public void preUpdate() {
        this.updateTime = new Date();
    }


    public static final String ID = "id";

    public static final String CREATE_TIME = "create_time";

    public static final String CREATOR_ID = "creator_id";

    public static final String UPDATE_TIME = "update_time";

    public static final String DELETED = "deleted";
}
