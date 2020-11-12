package cheche.controller.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Base Request
 * 
 * @author jieli
 */
public class BaseRequest {
    @ApiModelProperty(value = "第几页(默认1)")
    private Integer page = 1;
    @ApiModelProperty(value = "每页几条(默认20)")
    private Integer rows = 20;

    /** MySQL偏移量 */
    public int offset() {
        return (page - 1) * rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
