package systemlogic.processcentricservices.rest.client;

import java.io.File;
import java.net.URI;
import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.glassfish.jersey.client.ClientConfig;

import systemlogic.businesslogicservices.view.MeasureListHistoryImportView;
import util.JaxbUtil;

public class AdapterWS {

	static ClientConfig clientConfig = new ClientConfig();
	static Client client = ClientBuilder.newClient(clientConfig);

	static URL url = null;
	static QName qname = null;
	static Service service = null;
	
	static URI uri = null;
	static String type = "";
	static Integer idPerson = null;

	public static boolean sendMeasures (int idperson,MeasureListHistoryImportView v) {
		
		
		uri = UriBuilder.fromUri("http://rodrigo-sestari-final-rest.herokuapp.com/finalprojectrest/").build();	

		File xsdFile = new File("resource/measureImport.xsd");

		String xml = JaxbUtil.jaxbToXml("systemlogic.businesslogicservices.view", v, xsdFile);
		WebTarget service = client.target(uri.getPath() + "adapter/" + idperson);

		Response response = service.request(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML)
				.post(Entity.xml(xml));
		xml = response.readEntity(String.class);

		
		if (null != xml) {
			return true;
		}

		return false;
	}
	
	

}