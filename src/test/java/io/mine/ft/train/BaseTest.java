package io.mine.ft.train;

import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//import io.mine.ft.tenantId.interceptor.biz.TenantIdThreadLocal;
import io.mine.ft.train.FtFrontApplication;

/**
 * 单元测试样例
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FtFrontApplication.class)
@WebIntegrationTest
@IntegrationTest(value = { "DEPLOY_ENV=test" })
public abstract class BaseTest {

	public final static Long tenantId = 0L;

	static {
		// TenantIdThreadLocal.setTenantId(tenantId);
	}
}
