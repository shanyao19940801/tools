package liac.bean;

import java.util.List;

/**
 * @author 单耀
 * @version 1.0
 * @description
 * @date 2020/8/19 12:41
 */
public class IpResponse {
    private Integer status;
    private List<ResDetail> data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ResDetail> getData() {
        return data;
    }

    public void setData(List<ResDetail> data) {
        this.data = data;
    }
}
