/*******************************************************************************
 * Copyright (c) 2015, 2016 Substance Abuse and Mental Health Services Administration (SAMHSA)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     Eversolve, LLC - initial IExHub implementation for Health Information Exchange (HIE) integration
 *     Anthony Sute, Ioana Singureanu
 *******************************************************************************/
/**
 * 
 */
package org.iexhub.connectors;

import PDQSupplier.org.hl7.v3.PRPAIN201306UV02;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.fail;

/**
 * @author A. Sute
 *
 */
public class PDQQueryManagerTest
{
	private static final String PDQManagerEndpointURI = null;
	private static final String PDQManagerTLSEndpointURI = null;

	/**
	 * Test method for {@link org.iexhub.connectors.PDQQueryManager#queryPatientDemographics(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testITI47ConsumerQueryPatientAddress_NoOtherIDsScopingOrganization()
	{
		PDQQueryManager pdqQueryManager = null;
		try
		{
			pdqQueryManager = new PDQQueryManager(PDQManagerEndpointURI);
			PRPAIN201306UV02 pdqQueryResponse = pdqQueryManager.queryPatientDemographics(null,
					null,
					null,
					null,
					null,
					null,
					"2183 Radio Park Drive",
					"ATLANTA",
					"GA",
					"30303",
					null,
					null,
					null,
					null);
		}
		catch (Exception e)
		{
			fail("Error - " + e.getMessage());
		}
	}

	/**
	 * Test method for {@link org.iexhub.connectors.PDQQueryManager#queryPatientDemographics(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testITI47ConsumerQueryPatientNameAddress_NoOtherIDsScopingOrganization_TLS()
	{
		PDQQueryManager pdqQueryManager = null;
		try
		{
			pdqQueryManager = new PDQQueryManager(null,
					true);
			PRPAIN201306UV02 pdqQueryResponse = pdqQueryManager.queryPatientDemographics(null,
					"HINOJOXS",
					null,
					null,
					null,
					null,
					"2183 Radio Park Drive",
					null,
					null,
					null,
					null,
					null,
					null,
					null);
		}
		catch (Exception e)
		{
			fail("Error - " + e.getMessage());
		}
	}

	/**
	 * Test method for {@link org.iexhub.connectors.PDQQueryManager#queryPatientDemographics(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testITI47ConsumerQueryPatientAddress_OtherIDsScopingOrganization()
	{
		PDQQueryManager pdqQueryManager = null;
		try
		{
			pdqQueryManager = new PDQQueryManager(PDQManagerEndpointURI);
			PRPAIN201306UV02 pdqQueryResponse = pdqQueryManager.queryPatientDemographics(null,
					null,
					null,
					null,
					null,
					null,
					"1905 Romrog Way",
					"ROCK SPRINGS",
					"WY",
					"82901",
					null,
					null,
					"2.16.840.1.113883.3.72.5.9.1",
					null);
		}
		catch (Exception e)
		{
			fail("Error - " + e.getMessage());
		}
	}

	/**
	 * Test method for {@link org.iexhub.connectors.PDQQueryManager#queryPatientDemographics(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testITI47ConsumerQueryPatientNameAdministrativeSex_NoOtherIDsScopingOrganization()
	{
		PDQQueryManager pdqQueryManager = null;
		try
		{
			pdqQueryManager = new PDQQueryManager(/*PDQManagerEndpointURI*/ null);
			PRPAIN201306UV02 pdqQueryResponse = pdqQueryManager.queryPatientDemographics(null,
					"HINOJOXS",
					null,
					null,
					"F",
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null);
		}
		catch (Exception e)
		{
			fail("Error - " + e.getMessage());
		}
	}

	/**
	 * Test method for {@link org.iexhub.connectors.PDQQueryManager#queryPatientDemographics(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testITI47ConsumerQueryPatientNameAdministrativeSex_NoOtherIDsScopingOrganization_TLS()
	{
		PDQQueryManager pdqQueryManager = null;
		try
		{
			pdqQueryManager = new PDQQueryManager(null,
					true);
			PRPAIN201306UV02 pdqQueryResponse = pdqQueryManager.queryPatientDemographics("SALLY",
					"SHARE",
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null);
		}
		catch (Exception e)
		{
			fail("Error - " + e.getMessage());
		}
	}

	/**
	 * Test method for {@link org.iexhub.connectors.PDQQueryManager#queryPatientDemographics(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testITI47ConsumerQueryPatientNameAdministrativeSex_OtherIDsScopingOrganization()
	{
		PDQQueryManager pdqQueryManager = null;
		try
		{
			pdqQueryManager = new PDQQueryManager(PDQManagerEndpointURI);
			PRPAIN201306UV02 pdqQueryResponse = pdqQueryManager.queryPatientDemographics(null,
					"GREGORYX",
					null,
					null,
					"F",
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					"2.16.840.1.113883.3.72.5.9.1",
					null);
		}
		catch (Exception e)
		{
			fail("Error - " + e.getMessage());
		}
	}

	/**
	 * Test method for {@link org.iexhub.connectors.PDQQueryManager#queryPatientDemographics(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testITI47ConsumerQueryPatientNameDOB_NoOtherIDsScopingOrganization()
	{
		PDQQueryManager pdqQueryManager = null;
		try
		{
			pdqQueryManager = new PDQQueryManager(PDQManagerEndpointURI);
			PRPAIN201306UV02 pdqQueryResponse = pdqQueryManager.queryPatientDemographics(null,
					"HINOJOXS",
					null,
					"12/14/1967",
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null);
		}
		catch (Exception e)
		{
			fail("Error - " + e.getMessage());
		}
	}

	/**
	 * Test method for {@link org.iexhub.connectors.PDQQueryManager#queryPatientDemographics(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testITI47ConsumerQueryPatientNameDOB_NoOtherIDsScopingOrganization_TLS()
	{
		PDQQueryManager pdqQueryManager = null;
		try
		{
			pdqQueryManager = new PDQQueryManager(null,
					true);
			PRPAIN201306UV02 pdqQueryResponse = pdqQueryManager.queryPatientDemographics(null,
					"MOORE",
					null,
					"7/6/1951",
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null);
		}
		catch (Exception e)
		{
			fail("Error - " + e.getMessage());
		}
	}

	/**
	 * Test method for {@link org.iexhub.connectors.PDQQueryManager#queryPatientDemographics(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testITI47ConsumerQueryPatientNameDOB_OtherIDsScopingOrganization()
	{
		PDQQueryManager pdqQueryManager = null;
		try
		{
			pdqQueryManager = new PDQQueryManager(PDQManagerEndpointURI);
			PRPAIN201306UV02 pdqQueryResponse = pdqQueryManager.queryPatientDemographics(null,
					"GREGORYX",
					null,
					"10/15/1929",
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					"2.16.840.1.113883.3.72.5.9.1",
					"2.16.840.1.113883.3.72.5.9.1",
					null);
		}
		catch (Exception e)
		{
			fail("Error - " + e.getMessage());
		}
	}

	/**
	 * Test method for {@link org.iexhub.connectors.PDQQueryManager#queryPatientDemographics(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testITI47ConsumerQueryPatientPatientId_NoOtherIDsScopingOrganization()
	{
		PDQQueryManager pdqQueryManager = null;
		try
		{
			pdqQueryManager = new PDQQueryManager(PDQManagerEndpointURI);
			PRPAIN201306UV02 pdqQueryResponse = pdqQueryManager.queryPatientDemographics(null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					"HJ-361",
					"2.16.840.1.113883.3.72.5.9.1",
					null,
					null);
		}
		catch (Exception e)
		{
			fail("Error - " + e.getMessage());
		}
	}

	/**
	 * Test method for {@link org.iexhub.connectors.PDQQueryManager#queryPatientDemographics(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testITI47ConsumerQueryPatientPatientId_OtherIDsScopingOrganization()
	{
		PDQQueryManager pdqQueryManager = null;
		try
		{
			pdqQueryManager = new PDQQueryManager(PDQManagerEndpointURI);
			PRPAIN201306UV02 pdqQueryResponse = pdqQueryManager.queryPatientDemographics(null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					"HJ-361",
					"2.16.840.1.113883.3.72.5.9.1",
					"2.16.840.1.113883.3.72.5.9.1",
					null);
		}
		catch (Exception e)
		{
			fail("Error - " + e.getMessage());
		}
	}

	/**
	 * Test method for {@link org.iexhub.connectors.PDQQueryManager#queryPatientDemographics(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testITI47ConsumerQueryPatientPatientId_OtherIDsScopingOrganization_TLS()
	{
		PDQQueryManager pdqQueryManager = null;
		try
		{
			pdqQueryManager = new PDQQueryManager(PDQManagerTLSEndpointURI,
					true);
			PRPAIN201306UV02 pdqQueryResponse = pdqQueryManager.queryPatientDemographics(null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					"IHERED-993^^^&1.3.6.1.4.1.21367.13.20.1000&ISO",
					null,
					"1.3.6.1.4.1.21367.13.20.1000",
					null);
		}
		catch (Exception e)
		{
			fail("Error - " + e.getMessage());
		}
	}

	/**
	 * Test method for {@link org.iexhub.connectors.PDQQueryManager#queryPatientDemographics(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testITI47ConsumerQueryContinuationOption()
	{
		PDQQueryManager pdqQueryManager = null;
		try
		{
			// Per the NIST server's instructions for this test, send a valid query message to query about all patients named WILXLIS and ask for incremental
			//   response (limited to 1 record)... 
			pdqQueryManager = new PDQQueryManager(PDQManagerEndpointURI);
			String queryId = UUID.randomUUID().toString();
			PRPAIN201306UV02 pdqQueryResponse = pdqQueryManager.queryPatientDemographics(null,
					"WILXLIS",
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					1,
					queryId);
			
			// Now send the PDQ query continuation message (QUQI_IN000003UV01), requesting one additional record...
			pdqQueryResponse = pdqQueryManager.queryContinue(pdqQueryResponse,
					1,
					queryId);
			
			// Now send a PDQ query cancel message (QUQI_IN000003UV01)
//			pdqQueryManager.queryCancel(pdqQueryResponse,
//					queryId);
		}
		catch (Exception e)
		{
			fail("Error - " + e.getMessage());
		}
	}

	/**
	 * Test method for {@link org.iexhub.connectors.PDQQueryManager#queryPatientDemographics(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testITI47ConsumerQueryContinuationOptionTLS()
	{
		PDQQueryManager pdqQueryManager = null;
		try
		{
			pdqQueryManager = new PDQQueryManager(null,
					true);
			String queryId = UUID.randomUUID().toString();
			PRPAIN201306UV02 pdqQueryResponse = pdqQueryManager.queryPatientDemographics(null,
					null,
					null,
					null,
					"M",
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					2,
					queryId);
			
			// Now send the PDQ query continuation message (QUQI_IN000003UV01), requesting five additional records...
			pdqQueryResponse = pdqQueryManager.queryContinue(pdqQueryResponse,
					2,
					queryId);

			// Now send the PDQ query continuation message (QUQI_IN000003UV01), requesting five additional records...
//			pdqQueryResponse = pdqQueryManager.queryContinue(pdqQueryResponse,
//					5);

			// Now send the PDQ query continuation message (QUQI_IN000003UV01), requesting five additional records...
//			pdqQueryResponse = pdqQueryManager.queryContinue(pdqQueryResponse,
//					5);

			// Now send a PDQ query cancel message (QUQI_IN000003UV01)
//			pdqQueryManager.queryCancel(pdqQueryResponse);
		}
		catch (Exception e)
		{
			fail("Error - " + e.getMessage());
		}
	}

}
