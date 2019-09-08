package com.thoughtmechanix.licenses.controllers;

import com.thoughtmechanix.licenses.model.License;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.ServletContext;

/**
 * This class extends the AbstracTest class to implements
 * the unit test for each method such GET, POST, PUT and DELETE
 * of the respective RestController.
 *
 * @author Ernesto Antonio Rodriguez Acosta
 */
public class LicenseServiceControllerTest extends AbstractTest {

    private static final String ORGANIZATION_ID = "e254f8c-c442-4ebe-a82a-e2fc1d1ff78a";
    private static final String LICENSE_ID = "f38318c-c338-2ebe-a82a-e2fc1d1ff78a";
    private License license;
    LicenseServiceController licenseServiceControllerMock;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        license = License.builder().id(LICENSE_ID)
                    .productName("Teleco")
                    .licenseType("Seat")
                    .organizationId("TestOrg")
                    .build();

        licenseServiceControllerMock = Mockito.mock(LicenseServiceController.class);
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesClientRestController() throws Exception {
        ServletContext servletContext = webApplicationContext.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(webApplicationContext.getBean("licenseServiceController"));
    }

    @Test
    public void findByIdTest()  throws Exception {
        String uri = "/v1/organizations/" + ORGANIZATION_ID + "/licenses/" + LICENSE_ID;

        Mockito.when(licenseServiceControllerMock.findById(ORGANIZATION_ID, LICENSE_ID))
                .thenReturn(license);

        License result = licenseServiceControllerMock.findById(ORGANIZATION_ID, LICENSE_ID);

        Assert.assertEquals(LICENSE_ID, result.getId());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                                    .accept(MediaType.APPLICATION_JSON_VALUE))
                                    .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(302, status);
    }
}