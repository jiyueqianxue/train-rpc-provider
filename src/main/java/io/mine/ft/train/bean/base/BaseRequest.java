package io.mine.ft.train.bean.base;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**基础请求
 * @author machao
 *
 */
public class BaseRequest implements Serializable {
	
	private static final long serialVersionUID = -4496867430298036989L;
	/**
	 * 租户ID
	 */
	@NotNull(message = "租户ID不能为空")
	private Long tenantId;
	
	public Long getTenantId() {
		return tenantId;
	}
	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}
}
