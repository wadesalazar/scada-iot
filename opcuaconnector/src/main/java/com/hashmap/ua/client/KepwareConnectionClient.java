package com.hashmap.ua.client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.DateTime;
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.builtintypes.QualifiedName;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;
import org.opcfoundation.ua.builtintypes.UnsignedShort;
import org.opcfoundation.ua.common.ServiceResultException;
import org.opcfoundation.ua.core.ApplicationDescription;
import org.opcfoundation.ua.core.ApplicationType;
import org.opcfoundation.ua.core.Attributes;
import org.opcfoundation.ua.core.BrowsePathTarget;
import org.opcfoundation.ua.core.Identifiers;
import org.opcfoundation.ua.core.ReferenceDescription;
import org.opcfoundation.ua.core.RelativePathElement;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.builtintypes.ExpandedNodeId;
import org.opcfoundation.ua.transport.security.SecurityMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosysopc.ua.ApplicationIdentity;
import com.prosysopc.ua.SecureIdentityException;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.SessionActivationException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.AddressSpaceException;
import com.prosysopc.ua.client.ConnectException;
import com.prosysopc.ua.client.InvalidServerEndpointException;
import com.prosysopc.ua.client.UaClient;


public class KepwareConnectionClient {	

	protected static UaClient client;
	protected NodeId nodeId = null;
	
	private static final Logger log = LoggerFactory.getLogger(KepwareConnectionClient.class);

	protected void initialize()
			throws Exception, SecureIdentityException, IOException, UnknownHostException, 
			URISyntaxException, InvalidServerEndpointException, SessionActivationException,ConnectException,ServiceException{

		log.info("Connecting to the Kepware Server...");
		//** Application Description is sent to the server
		ApplicationDescription appDescription = new ApplicationDescription();
		client = new UaClient("opc.tcp://192.168.1.39:49320/Kepware.KEPServerEX.V5");
		appDescription.setApplicationName(new LocalizedText("KepwareClient",
				Locale.ENGLISH));

		// host name of the computer in which the application is run
		appDescription.setApplicationUri("opc.tcp://winclienthadoop:49320/OPCUA/Kepware.KEPServerEX.V5:KepwareConnectionClient");
		appDescription.setProductUri("urn:opc.tcp://winclienthadoop:49320/OPCUA/Kepware.KEPServerEX.V5:KepwareConnectionClient");


		appDescription.setApplicationType(ApplicationType.Client);

		final ApplicationIdentity identity = new ApplicationIdentity();
		identity.setApplicationDescription(appDescription);

		client.setSecurityMode(SecurityMode.NONE);
		client.setApplicationIdentity(identity);
		client.connect();
		//	DataValue value = client.readValue(Identifiers.Server_ServerStatus_State);	

		if(nodeId == null){
			nodeId = Identifiers.RootFolder;
		}
		client.getAddressSpace().setMaxReferencesPerNode(1000);

		//	recurseNodeLookup(Identifiers.RootFolder, 0);
		//	client.disconnect();
	}

	protected long  dateTimeToLong( DateTime timestamp,
			UnsignedShort picoSeconds) {
		if ((timestamp != null) && !timestamp.equals(DateTime.MIN_VALUE)) {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			StringBuilder sb = new StringBuilder();
			sb.append(format.format(timestamp
					.getCalendar(TimeZone.getDefault()).getTime()));
			if ((picoSeconds != null)
					&& !picoSeconds.equals(UnsignedShort.valueOf(0)))
				sb.append(String.format("/%d picos", picoSeconds.getValue()));
			Timestamp ts = Timestamp.valueOf(sb.toString());
			long timestampValue = ts.getTime();

			return timestampValue;
		}
		return 0;
	}
	
	protected String getDataTypes(String tagName) {
		if(tagName.equalsIgnoreCase("Ramp1") || tagName.equalsIgnoreCase("Ramp3")  || tagName.equalsIgnoreCase("Ramp4")
				|| tagName.equalsIgnoreCase("Random1")  || tagName.equalsIgnoreCase("Random2") || tagName.equalsIgnoreCase("Random3")
				|| tagName.equalsIgnoreCase("Random4")) {
				return "Long";
		} else if(tagName.equalsIgnoreCase("Ramp2")  || tagName.equalsIgnoreCase("Sine1") || tagName.equalsIgnoreCase("Sine2") 
				|| tagName.equalsIgnoreCase("Sine3") || tagName.equalsIgnoreCase("Sine4") || tagName.equalsIgnoreCase("User2")){
				return "Float";
		} else if(tagName.equalsIgnoreCase("User1") || tagName.equalsIgnoreCase("User4")) {
			return "String";
		} else if(tagName.equalsIgnoreCase("User3")){
			return "Boolean";
		}
		return "";	
	}

	public List<IOTMessage> getIOTMessages(){
		List<IOTMessage> iotMessageList = new ArrayList<IOTMessage>();
		try{
			KepwareConnectionClient kepwareConnectionClient = new KepwareConnectionClient();
			kepwareConnectionClient.initialize();
			iotMessageList = recurseNodeLookup(Identifiers.RootFolder, 0);
			client.disconnect();
		} catch(Exception e){
			e.printStackTrace();
		}
		return iotMessageList;
	}


	public  List<IOTMessage> recurseNodeLookup(NodeId childNodeID, int level) throws ServiceException, AddressSpaceException, StatusException {

		level++;		
		List<IOTMessage> iotMessages = new ArrayList<IOTMessage>();
		try {

			BrowsePathTarget[] results = client.getAddressSpace().translateBrowsePathToNodeId(
					childNodeID,
					new RelativePathElement(
							Identifiers.HierarchicalReferences,
							false, true, new QualifiedName(0,"Objects")),
							new RelativePathElement(
									Identifiers.HierarchicalReferences,
									false, true, new QualifiedName(2, "Simulation Examples")),
									new RelativePathElement(
											Identifiers.HierarchicalReferences,
											false, true, new QualifiedName(2, "Functions")));
			// Assume a single target was returned
			ExpandedNodeId targetExpandedId = results[0].getTargetId();

			String equipmentId = results[0].getTargetId().getValue().toString();

			try{
				NodeId target = client.getAddressSpace().getNamespaceTable().toNodeId(targetExpandedId);

				List<ReferenceDescription> groupNodes = client.getAddressSpace().browse(target);
				if(groupNodes != null){
					for (int i=0; i<groupNodes.size(); i++){
						if(groupNodes.get(i).getNodeClass().name().equalsIgnoreCase("variable")) {
							ReferenceDescription groupDescription = groupNodes.get(i);

							try {
								NodeId groupNodeID = client.getAddressSpace().getNamespaceTable().toNodeId(groupDescription.getNodeId());
						
								UnsignedInteger[] attribs = {Attributes.Value};				
								DataValue[] arrayOfValues = client.readAttributes(groupNodeID, attribs);			
								String server_url = client.getEndpoint().getEndpointUrl();
								String server_name = client.getServerName().replaceAll("/", "");
								String itemId = groupDescription.getDisplayName().getText();
								String tagDataType = getDataTypes(itemId);
								String quality = "";
															
								for(int j=0; j< arrayOfValues.length;j++){
									if(arrayOfValues[j].getStatusCode().toString() != null || arrayOfValues[j].getStatusCode().toString() != ""){
										quality = arrayOfValues[j].getStatusCode().toString();
										int spacePos = quality.indexOf(" ");
										if(spacePos > 0){
											quality = quality.substring(0, spacePos);
										}																			
									}
									String tagValue = arrayOfValues[j].getValue().getValue().toString();
									long serverTimestamp = dateTimeToLong(arrayOfValues[j].getServerTimestamp(),arrayOfValues[j].getServerPicoseconds());
									long sourceTimestamp = dateTimeToLong(arrayOfValues[j].getSourceTimestamp(),arrayOfValues[j].getServerPicoseconds());
									
									
									IOTMessage iotMessage = new IOTMessage();
									//Setting the values to the object.
									iotMessage.setServerName(server_name);
									iotMessage.setServerIp(server_url);
									iotMessage.setTopic(equipmentId);
									iotMessage.setTagName(itemId);
									iotMessage.setTagDataType(tagDataType);
									iotMessage.setTagValue(tagValue);
									iotMessage.setServerReportTimestamp(serverTimestamp);
									iotMessage.setAcquistionTimestamp(sourceTimestamp);
									iotMessage.setQuality(quality);
									
									iotMessages.add(iotMessage);
								}	
							} catch (ServiceResultException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ServiceException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						}
					}
				}
			} catch(ServiceResultException e) {
				throw new ServiceException(e);
			}

		} catch (StatusException e1) {
		}	
		return iotMessages;

	}
}
