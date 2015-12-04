package com.InfoExchangeHub.Connectors;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.UUID;

import javax.xml.bind.JAXBElement;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.soap.SOAPFactory;
import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;

import PIXManager.src.org.hl7.v3.*;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.InfoExchangeHub.Exceptions.*;

import PIXManager.src.com.InfoExchangeHub.Services.Client.PIXManager_ServiceStub;

public class PIXManager
{
    /** Logger */
    public static Logger log = Logger.getLogger(PIXManager.class);

    private static final String receiverApplicationName = "2.16.840.1.113883.3.72.6.5.100.556";
	private static final String receiverTelecomValue = "https://example.org/PIXQuery";
	private static final String facilityName = "2.16.840.1.113883.3.72.6.1";
	private static final String providerOrganizationName = "HIE Portal";
	private static final String providerOrganizationContactTelecom = "555-555-5555";
	private static final String organizationOID = "2.16.840.1.113883.3.72.5.9.1";
	private static final String providerOrganizationOID = "1.2.840.114350.1.13.99998.8734";
	private static final String queryIdOID = "1.2.840.114350.1.13.28.1.18.5.999";
	private static final String dataSourceOID = "2.16.840.1.113883.3.72.5.9.3";
	private static final SOAPFactory soapFactory = OMAbstractFactory.getSOAP12Factory();
	private static PIXManager_ServiceStub pixManagerStub = null;
	private static final ObjectFactory objectFactory = new ObjectFactory();
	
	public PIXManager(String endpointURI) throws AxisFault, Exception
	{
		try
		{
			// Instantiate PIXManager client stub and enable WS-Addressing...
			pixManagerStub = new PIXManager_ServiceStub(endpointURI);
			pixManagerStub._getServiceClient().engageModule("addressing");
			
			log.info("PIXManager connector successfully initialized, endpointURI="
					+ endpointURI);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	public PRPAIN201310UV02 patientRegistryGetIdentifiers(String patientId,
			String domainOID,
			boolean populateDataSource) throws RemoteException
	{
		if ((patientId == null) ||
			(patientId.length() == 0))
		{
			throw new PatientIdParamMissingException("PatientId parameter is required");
		}
		
		PRPAIN201309UV02 pRPA_IN201309UV02 = new PRPAIN201309UV02();
		
		// ITS version...
		pRPA_IN201309UV02.setITSVersion("XML_1.0");
		
		// ID...
		II messageId = new II();
		messageId.setRoot(UUID.randomUUID().toString());
		pRPA_IN201309UV02.setId(messageId);
		
		// Creation time...
		DateTime dt = new DateTime(DateTimeZone.UTC);
		TS creationTime = new TS();
		StringBuilder creationTimeBuilder = new StringBuilder();
		creationTimeBuilder.append(dt.getYear());
		creationTimeBuilder.append((dt.getMonthOfYear() < 10) ? ("0" + dt.getMonthOfYear())
				: dt.getMonthOfYear());
		creationTimeBuilder.append((dt.getDayOfMonth() < 10) ? ("0" + dt.getDayOfMonth())
				: dt.getDayOfMonth());
		creationTimeBuilder.append((dt.getHourOfDay() < 10) ? ("0" + dt.getHourOfDay())
				: dt.getHourOfDay());
		creationTimeBuilder.append((dt.getMinuteOfHour() < 10) ? ("0" + dt.getMinuteOfHour())
				: dt.getMinuteOfHour());
		creationTimeBuilder.append((dt.getSecondOfMinute() < 10) ? ("0" + dt.getSecondOfMinute())
				: dt.getSecondOfMinute());
		creationTime.setValue(creationTimeBuilder.toString());
		pRPA_IN201309UV02.setCreationTime(creationTime);
		
		// Interaction ID...
		II interactionId = new II();
		interactionId.setRoot("2.16.840.1.113883.1.6");
		interactionId.setExtension("PRPA_IN201309UV02");
		pRPA_IN201309UV02.setInteractionId(interactionId);
		
		// Processing code...
		CS processingCode = new CS();
		processingCode.setCode("P");
		pRPA_IN201309UV02.setProcessingCode(processingCode);
		
		// Processing mode code...
		CS processingModeCode = new CS();
		processingModeCode.setCode("T");
		pRPA_IN201309UV02.setProcessingModeCode(processingModeCode);
		
		// Accept ack code...
		CS acceptAckCode = new CS();
		acceptAckCode.setCode("AL");
		pRPA_IN201309UV02.setAcceptAckCode(acceptAckCode);

		// Create receiver...
		MCCIMT000100UV01Receiver receiver = new MCCIMT000100UV01Receiver();
		receiver.setTypeCode(CommunicationFunctionType.RCV);
		MCCIMT000100UV01Device receiverDevice = new MCCIMT000100UV01Device();
		receiverDevice.setClassCode(EntityClassDevice.DEV);
		receiverDevice.setDeterminerCode("INSTANCE");
		II receiverDeviceId = new II();
		receiverDeviceId.setRoot(receiverApplicationName);
		receiverDevice.getId().add(receiverDeviceId);
		receiver.setDevice(receiverDevice);
		pRPA_IN201309UV02.getReceiver().add(receiver);
		
		// Create sender...
		MCCIMT000100UV01Sender sender = new MCCIMT000100UV01Sender();
		sender.setTypeCode(CommunicationFunctionType.SND);
		MCCIMT000100UV01Device senderDevice = new MCCIMT000100UV01Device();
		senderDevice.setClassCode(EntityClassDevice.DEV);
		senderDevice.setDeterminerCode("INSTANCE");
		II senderDeviceId = new II();
		senderDeviceId.setRoot("1.2.840.114350.1.13.99998.8734");
		senderDevice.getId().add(senderDeviceId);
		sender.setDevice(senderDevice);
		pRPA_IN201309UV02.setSender(sender);

		// Create AuthorOrPerformer...
		QUQIMT021001UV01AuthorOrPerformer authorOrPerformer = new QUQIMT021001UV01AuthorOrPerformer();
		authorOrPerformer.setTypeCode(XParticipationAuthorPerformer.fromValue("AUT"));
		COCTMT090100UV01AssignedPerson assignedPerson = new COCTMT090100UV01AssignedPerson();
		assignedPerson.setClassCode("ASSIGNED");
		II assignedPersonId = new II();
		assignedPersonId.setRoot("1.2.840.114350.1.13.99997.2.7766");
		assignedPersonId.setExtension("USR5568");
		assignedPerson.getId().add(assignedPersonId);
		authorOrPerformer.setAssignedPerson(objectFactory.createQUQIMT021001UV01AuthorOrPerformerAssignedPerson(assignedPerson));
		
		// Create QueryByParameter...
		PRPAMT201307UV02QueryByParameter queryByParam = new PRPAMT201307UV02QueryByParameter();
		II queryId = new II();
		queryId.setRoot(queryIdOID);
		queryId.setExtension(UUID.randomUUID().toString());
		queryByParam.setQueryId(queryId);
		CS responsePriorityCode = new CS();
		responsePriorityCode.setCode("I");
		queryByParam.setResponsePriorityCode(responsePriorityCode);
		CS statusCode = new CS();
		statusCode.setCode("new");
		queryByParam.setStatusCode(statusCode);
		
		// Create ParameterList...
		PRPAMT201307UV02ParameterList paramList = new PRPAMT201307UV02ParameterList();

		if (populateDataSource)
		{
			// Create DataSource...
			PRPAMT201307UV02DataSource dataSource = new PRPAMT201307UV02DataSource();
			II dataSourceId = new II();
			dataSourceId.setRoot(dataSourceOID);
			dataSource.getValue().add(dataSourceId);
			ST dataSourceSemanticsText = new ST();
			dataSourceSemanticsText.getContent().add("DataSource.id");
			dataSource.setSemanticsText(dataSourceSemanticsText);
			paramList.getDataSource().add(dataSource);
		}

		// Create PatientIdentifier...
		PRPAMT201307UV02PatientIdentifier patientIdentifier = new PRPAMT201307UV02PatientIdentifier();
		II patientIdentifierId = new II();
		patientIdentifierId.setRoot(domainOID);
		patientIdentifierId.setExtension(patientId);
		patientIdentifier.getValue().add(patientIdentifierId);
		ST patientIdentifierSemanticsText = new ST();
		patientIdentifierSemanticsText.getContent().add("Patient.Id");
		patientIdentifier.setSemanticsText(patientIdentifierSemanticsText);
		paramList.getPatientIdentifier().add(patientIdentifier);
		queryByParam.setParameterList(paramList);

		// Create ControlActProcess...
		PRPAIN201309UV02QUQIMT021001UV01ControlActProcess controlAct = new PRPAIN201309UV02QUQIMT021001UV01ControlActProcess();
		CD controlActProcessCode = new CD();
		controlActProcessCode.setCode("PRPA_TE201309UV02");
		controlActProcessCode.setCodeSystem("2.16.840.1.113883.1.6");
		controlAct.setCode(controlActProcessCode);
		controlAct.setClassCode(ActClassControlAct.CACT);
		controlAct.setMoodCode(XActMoodIntentEvent.EVN);
		controlAct.getAuthorOrPerformer().add(authorOrPerformer);
		controlAct.setQueryByParameter(objectFactory.createPRPAIN201309UV02QUQIMT021001UV01ControlActProcessQueryByParameter(queryByParam));

		pRPA_IN201309UV02.setControlActProcess(controlAct);
		
		return pixManagerStub.pIXManager_PRPA_IN201309UV02(pRPA_IN201309UV02);
	}
	
	public MCCIIN000002UV01 registerPatient(String givenName,
			String familyName,
			String middleName,
			String dateOfBirth,
			String gender) throws RemoteException
	{
		if ((familyName == null) ||
			(familyName.length() == 0))
		{
			throw new FamilyNameParamMissingException("FamilyName parameter is required");
		}
		
		PRPAIN201301UV02 pRPA_IN201301UV02 = new PRPAIN201301UV02();
		
		// ITS version...
		pRPA_IN201301UV02.setITSVersion("XML_1.0");
		
		// ID...
		II messageId = new II();
		messageId.setRoot(UUID.randomUUID().toString());
		pRPA_IN201301UV02.setId(messageId);
		
		// Creation time...
		DateTime dt = new DateTime(DateTimeZone.UTC);
		TS creationTime = new TS();
		StringBuilder creationTimeBuilder = new StringBuilder();
		creationTimeBuilder.append(dt.getYear());
		creationTimeBuilder.append((dt.getMonthOfYear() < 10) ? ("0" + dt.getMonthOfYear())
				: dt.getMonthOfYear());
		creationTimeBuilder.append((dt.getDayOfMonth() < 10) ? ("0" + dt.getDayOfMonth())
				: dt.getDayOfMonth());
		creationTimeBuilder.append((dt.getHourOfDay() < 10) ? ("0" + dt.getHourOfDay())
				: dt.getHourOfDay());
		creationTimeBuilder.append((dt.getMinuteOfHour() < 10) ? ("0" + dt.getMinuteOfHour())
				: dt.getMinuteOfHour());
		creationTimeBuilder.append((dt.getSecondOfMinute() < 10) ? ("0" + dt.getSecondOfMinute())
				: dt.getSecondOfMinute());
		creationTime.setValue(creationTimeBuilder.toString());
		pRPA_IN201301UV02.setCreationTime(creationTime);
		
		// Interaction ID...
		II interactionId = new II();
		interactionId.setRoot("2.16.840.1.113883.1.6");
		interactionId.setExtension("PRPA_IN201301UV02");
		pRPA_IN201301UV02.setInteractionId(interactionId);
		
		// Processing code...
		CS processingCode = new CS();
		processingCode.setCode("P");
		pRPA_IN201301UV02.setProcessingCode(processingCode);
		
		// Processing mode code...
		CS processingModeCode = new CS();
		processingModeCode.setCode("T");
		pRPA_IN201301UV02.setProcessingModeCode(processingModeCode);
		
		// Accept ack code...
		CS acceptAckCode = new CS();
		acceptAckCode.setCode("AL");
		pRPA_IN201301UV02.setAcceptAckCode(acceptAckCode);
		
		// Create receiver...
		MCCIMT000100UV01Receiver receiver = new MCCIMT000100UV01Receiver();
		receiver.setTypeCode(CommunicationFunctionType.RCV);
		MCCIMT000100UV01Device receiverDevice = new MCCIMT000100UV01Device();
		receiverDevice.setClassCode(EntityClassDevice.DEV);
		receiverDevice.setDeterminerCode("INSTANCE");
		II receiverDeviceId = new II();
		receiverDeviceId.setRoot(receiverApplicationName);
		receiverDevice.getId().add(receiverDeviceId);
		MCCIMT000100UV01Agent asAgent = new MCCIMT000100UV01Agent();
		asAgent.getClassCode().add("AGNT");
		MCCIMT000100UV01Organization representedOrganization = new MCCIMT000100UV01Organization();
		representedOrganization.setDeterminerCode("INSTANCE");
		representedOrganization.setClassCode("ORG");
		II representedOrganizationId = new II();
		representedOrganizationId.setRoot("2.16.840.1.113883.3.72.6.1");
		representedOrganization.getId().add(representedOrganizationId);
		asAgent.setRepresentedOrganization(objectFactory.createMCCIMT000100UV01AgentRepresentedOrganization(representedOrganization));
		receiverDevice.setAsAgent(objectFactory.createMCCIMT000100UV01DeviceAsAgent(asAgent));
		receiver.setDevice(receiverDevice);
		pRPA_IN201301UV02.getReceiver().add(receiver);
		
		// Create sender...
		MCCIMT000100UV01Sender sender = new MCCIMT000100UV01Sender();
		sender.setTypeCode(CommunicationFunctionType.SND);
		MCCIMT000100UV01Device senderDevice = new MCCIMT000100UV01Device();
		senderDevice.setClassCode(EntityClassDevice.DEV);
		senderDevice.setDeterminerCode("INSTANCE");
		II senderDeviceId = new II();
		senderDeviceId.setRoot("1.2.840.114350.1.13.99998.8734");
		senderDevice.getId().add(senderDeviceId);
		MCCIMT000100UV01Agent senderAsAgent = new MCCIMT000100UV01Agent();
		senderAsAgent.getClassCode().add("AGNT");
		MCCIMT000100UV01Organization senderRepresentedOrganization = new MCCIMT000100UV01Organization();
		senderRepresentedOrganization.setDeterminerCode("INSTANCE");
		senderRepresentedOrganization.setClassCode("ORG");
		II senderRepresentedOrganizationId = new II();
		senderRepresentedOrganizationId.setRoot("1.2.840.114350.1.13");
		senderRepresentedOrganization.getId().add(senderRepresentedOrganizationId);
		senderAsAgent.setRepresentedOrganization(objectFactory.createMCCIMT000100UV01AgentRepresentedOrganization(senderRepresentedOrganization));
		senderDevice.setAsAgent(objectFactory.createMCCIMT000100UV01DeviceAsAgent(senderAsAgent));
		sender.setDevice(senderDevice);
		pRPA_IN201301UV02.setSender(sender);
		
		PRPAIN201301UV02MFMIMT700701UV01Subject1 subject = new PRPAIN201301UV02MFMIMT700701UV01Subject1();
		
		// Generate GUID portion of patient ID...
		String guid = UUID.randomUUID().toString();
//		String patientID = guid + "^^^&" + organizationOID + "&ISO";
		
		// Create Registration Event...
		PRPAIN201301UV02MFMIMT700701UV01RegistrationEvent registrationEvent = new PRPAIN201301UV02MFMIMT700701UV01RegistrationEvent();
		registrationEvent.getClassCode().add("REG");
		registrationEvent.getMoodCode().add("EVN");
		registrationEvent.getNullFlavor().add("NA");
		CS statusCode = new CS();
		statusCode.setCode("active");
		registrationEvent.setStatusCode(statusCode);
		PRPAIN201301UV02MFMIMT700701UV01Subject2 subject1 = new PRPAIN201301UV02MFMIMT700701UV01Subject2();
		subject1.setTypeCode(ParticipationTargetSubject.SBJ);
		
		// Create Patient...
		PRPAMT201301UV02Patient patient = new PRPAMT201301UV02Patient();
		patient.getClassCode().add("PAT");
		II patientId = new II();
		patientId.setRoot(organizationOID);
		patientId.setAssigningAuthorityName("NIST2010");
		patientId.setExtension("PIX");
		patient.getId().add(patientId);
		CS patientStatusCode = new CS();
		patientStatusCode.setCode("active");
		patient.setStatusCode(patientStatusCode);
		
		// Create PatientPerson...
		PRPAMT201301UV02Person patientPerson = new PRPAMT201301UV02Person();
		
		EnFamily enFamily = null;
		if ((familyName != null) &&
			(familyName.length() > 0))
		{
			enFamily = new EnFamily();
			enFamily.getContent().add(familyName);
		}
		
		EnGiven enGiven = null;
		if ((givenName != null) &&
			(givenName.length() > 0))
		{
			enGiven = new EnGiven();
			
			if ((middleName != null) &&
				(middleName.length() > 0))
			{
				enGiven.getContent().add(givenName + " " + middleName);
			}
			else
			{
				enGiven.getContent().add(givenName);
			}
		}
		
		PN patientName = new PN();
		patientName.getContent().add(objectFactory.createENFamily(enFamily));
		
		if (enGiven != null)
		{
			patientName.getContent().add(objectFactory.createENGiven(enGiven));
		}
		
		patientPerson.getName().add(patientName);
		
		if ((gender != null) &&
			(gender.length() > 0))
		{
			CE adminGenderCode = new CE();
			adminGenderCode.setCode(gender);
			adminGenderCode.setCodeSystem("2.16.840.1.113883.5.1");
			patientPerson.setAdministrativeGenderCode(adminGenderCode);
		}

		patientPerson.getClassCode().add("PSN");
		patientPerson.setDeterminerCode("INSTANCE");
		
		JAXBElement<PRPAMT201301UV02Person> patientPersonElement = objectFactory.createPRPAMT201301UV02PatientPatientPerson(patientPerson);
		patient.setPatientPerson(patientPersonElement);
		
		// Create ProviderOrganization...
		COCTMT150003UV03Organization providerOrganization = new COCTMT150003UV03Organization();
		providerOrganization.setDeterminerCode("INSTANCE");
		providerOrganization.setClassCode("ORG");
		II providerId = new II();
		providerId.setRoot(providerOrganizationOID);
		providerOrganization.getId().add(providerId);
		ON providerName = new ON();
		providerName.getContent().add(providerOrganizationName);
		providerOrganization.getName().add(providerName);
		COCTMT150003UV03ContactParty contactParty = new COCTMT150003UV03ContactParty();
		contactParty.setClassCode(RoleClassContact.CON);
		TEL contactPartyTelecom = new TEL();
		contactPartyTelecom.setValue(providerOrganizationContactTelecom);
		contactParty.getTelecom().add(contactPartyTelecom);
		providerOrganization.getContactParty().add(contactParty);
		
		patient.setProviderOrganization(providerOrganization);
		
		subject1.setPatient(patient);

		registrationEvent.setSubject1(subject1);
		
		// Create Custodian info...
		MFMIMT700701UV01Custodian custodian = new MFMIMT700701UV01Custodian();
		custodian.getTypeCode().add("CST");
		COCTMT090003UV01AssignedEntity assignedEntity = new COCTMT090003UV01AssignedEntity();
		assignedEntity.setClassCode("ASSIGNED");
		II assignedEntityId = new II();
		assignedEntityId.setRoot(organizationOID);
		assignedEntity.getId().add(assignedEntityId);
		COCTMT090003UV01Organization assignedOrganization = new COCTMT090003UV01Organization();
		assignedOrganization.setDeterminerCode("INSTANCE");
		assignedOrganization.setClassCode("ORG");
		EN organizationName = new EN();
		organizationName.getContent().add("IHE Portal");
		assignedOrganization.getName().add(organizationName);
		assignedEntity.setAssignedOrganization(objectFactory.createCOCTMT090003UV01AssignedEntityAssignedOrganization(assignedOrganization));
		custodian.setAssignedEntity(assignedEntity);
		registrationEvent.setCustodian(custodian);
		
		// Set Subject info...
		subject.getTypeCode().add("SUBJ");
		
		subject.setRegistrationEvent(registrationEvent);
		
		PRPAIN201301UV02MFMIMT700701UV01ControlActProcess controlAct = new PRPAIN201301UV02MFMIMT700701UV01ControlActProcess();
		CD controlActProcessCode = new CD();
		controlActProcessCode.setCode("PRPA_TE201301UV02");
		controlActProcessCode.setCodeSystem("2.16.840.1.113883.1.6");
		controlAct.setCode(controlActProcessCode);
		controlAct.setClassCode(ActClassControlAct.CACT);
		controlAct.setMoodCode(XActMoodIntentEvent.EVN);
		controlAct.getSubject().add(subject);
		
		pRPA_IN201301UV02.setControlActProcess(controlAct);

		return pixManagerStub.pIXManager_PRPA_IN201301UV02(pRPA_IN201301UV02);
	}
}
