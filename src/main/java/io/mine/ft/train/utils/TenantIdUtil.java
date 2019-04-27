package io.mine.ft.train.utils;

import javax.servlet.http.HttpServletRequest;

//import io.mine.ft.tenantId.interceptor.biz.TenantIdThreadLocal;
//import io.mine.sso.client.FtSsoSession;
//import io.mine.sso.client.FtSsoUser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TenantIdUtil {

	// private static String TENANT_ID = "tenantId";

	public static boolean setTenantId(HttpServletRequest request) {

		// Long tenantId = 0L;
		try {
			// FtSsoUser currentUser = FtSsoSession.getCurrentUser(request);
			// tenantId = currentUser== null?null:currentUser.getTenantId();
		} catch (Exception e) {
			log.error("TenantIdUtil setTenantId: ", e);
		}
		// if (tenantId != null) {
		// TenantIdThreadLocal.setTenantId(tenantId);
		return true;
		// }

		// String tenantIdStr =
		// StringUtils.isNotEmpty(request.getHeader(TENANT_ID))?
		// request.getHeader(TENANT_ID): request.getParameter(TENANT_ID);
		//
		// if (!StringUtils.isNumeric(tenantIdStr)) {
		// return false;
		// }
		// tenantId = Long.valueOf(tenantIdStr);
		// TenantIdThreadLocal.setTenantId(tenantId);
		// return true;
	}
}
