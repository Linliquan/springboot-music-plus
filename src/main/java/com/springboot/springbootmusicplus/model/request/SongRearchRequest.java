package com.springboot.springbootmusicplus.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author linliquan
 * @description:
 * @create 2021/5/8 14:48
 */
@Data
@ApiModel("歌曲搜索请求")
public class SongRearchRequest implements Serializable {
    private static final long serialVersionUID = 7860689448337834421L;

    @NotNull(message = "歌曲名不能为空")
    @ApiModelProperty("歌曲名")
    private String songName;

}
