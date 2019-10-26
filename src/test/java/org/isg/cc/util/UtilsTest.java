package org.isg.cc.util;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.isg.cc.model.Organization;
import org.isg.cc.model.Ticket;
import org.isg.cc.model.User;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class UtilsTest {

	@Rule
	public EasyMockRule mocks = new EasyMockRule(this);

	@TestSubject
	Utils utils = Utils.getInstance();

	@Mock
	private Utils utilsMock;

	private User user;
	private Ticket ticket;
	private Organization organization;
	JSONParser jsonParser;

	static String userJson = "[\r\n" + "  {\r\n" + "    \"_id\": 1,\r\n"
			+ "    \"url\": \"http://initech.tokoin.io.com/api/v2/users/1.json\",\r\n"
			+ "    \"external_id\": \"74341f74-9c79-49d5-9611-87ef9b6eb75f\",\r\n"
			+ "    \"name\": \"Francisca Rasmussen\",\r\n" + "    \"alias\": \"Miss Coffey\",\r\n"
			+ "    \"created_at\": \"2016-04-15T05:19:46 -10:00\",\r\n" + "    \"active\": true,\r\n"
			+ "    \"verified\": true,\r\n" + "    \"shared\": false,\r\n" + "    \"locale\": \"en-AU\",\r\n"
			+ "    \"timezone\": \"Sri Lanka\",\r\n" + "    \"last_login_at\": \"2013-08-04T01:03:27 -10:00\",\r\n"
			+ "    \"email\": \"coffeyrasmussen@flotonic.com\",\r\n" + "    \"phone\": \"8335-422-718\",\r\n"
			+ "    \"signature\": \"Don't Worry Be Happy!\",\r\n" + "    \"organization_id\": 101,\r\n"
			+ "    \"tags\": [\r\n" + "      \"Springville\",\r\n" + "      \"Sutton\",\r\n"
			+ "      \"Hartsville/Hartley\",\r\n" + "      \"Diaperville\"\r\n" + "    ],\r\n"
			+ "    \"suspended\": true,\r\n" + "    \"role\": \"admin\"\r\n" + "  },\r\n" + "  {\r\n"
			+ "    \"_id\": 2,\r\n" + "    \"url\": \"http://initech.tokoin.io.com/api/v2/users/2.json\",\r\n"
			+ "    \"external_id\": \"c9995ea4-ff72-46e0-ab77-dfe0ae1ef6c2\",\r\n"
			+ "    \"name\": \"Cross Barlow\",\r\n" + "    \"alias\": \"Miss Joni\",\r\n"
			+ "    \"created_at\": \"2016-06-23T10:31:39 -10:00\",\r\n" + "    \"active\": true,\r\n"
			+ "    \"verified\": true,\r\n" + "    \"shared\": false,\r\n" + "    \"locale\": \"zh-CN\",\r\n"
			+ "    \"timezone\": \"Armenia\",\r\n" + "    \"last_login_at\": \"2012-04-12T04:03:28 -10:00\",\r\n"
			+ "    \"email\": \"jonibarlow@flotonic.com\",\r\n" + "    \"phone\": \"9575-552-585\",\r\n"
			+ "    \"signature\": \"Don't Worry Be Happy!\",\r\n" + "    \"organization_id\": 102,\r\n"
			+ "    \"tags\": [\r\n" + "      \"Foxworth\",\r\n" + "      \"Woodlands\",\r\n" + "      \"Herlong\",\r\n"
			+ "      \"Henrietta\"\r\n" + "    ],\r\n" + "    \"suspended\": false,\r\n" + "    \"role\": \"admin\"\r\n"
			+ "  }\r\n" + " ]";

	static String orgJson = "[\r\n" + "  {\r\n" + "    \"_id\": 101,\r\n"
			+ "    \"url\": \"http://initech.tokoin.io.com/api/v2/organizations/101.json\",\r\n"
			+ "    \"external_id\": \"9270ed79-35eb-4a38-a46f-35725197ea8d\",\r\n" + "    \"name\": \"Enthaze\",\r\n"
			+ "    \"domain_names\": [\r\n" + "      \"kage.com\",\r\n" + "      \"ecratic.com\",\r\n"
			+ "      \"endipin.com\",\r\n" + "      \"zentix.com\"\r\n" + "    ],\r\n"
			+ "    \"created_at\": \"2016-05-21T11:10:28 -10:00\",\r\n" + "    \"details\": \"MegaCorp\",\r\n"
			+ "    \"shared_tickets\": false,\r\n" + "    \"tags\": [\r\n" + "      \"Fulton\",\r\n"
			+ "      \"West\",\r\n" + "      \"Rodriguez\",\r\n" + "      \"Farley\"\r\n" + "    ]\r\n" + "  },\r\n"
			+ "  {\r\n" + "    \"_id\": 102,\r\n"
			+ "    \"url\": \"http://initech.tokoin.io.com/api/v2/organizations/102.json\",\r\n"
			+ "    \"external_id\": \"7cd6b8d4-2999-4ff2-8cfd-44d05b449226\",\r\n" + "    \"name\": \"Nutralab\",\r\n"
			+ "    \"domain_names\": [\r\n" + "      \"trollery.com\",\r\n" + "      \"datagen.com\",\r\n"
			+ "      \"bluegrain.com\",\r\n" + "      \"dadabase.com\"\r\n" + "    ],\r\n"
			+ "    \"created_at\": \"2016-04-07T08:21:44 -10:00\",\r\n" + "    \"details\": \"Non profit\",\r\n"
			+ "    \"shared_tickets\": false,\r\n" + "    \"tags\": [\r\n" + "      \"Cherry\",\r\n"
			+ "      \"Collier\",\r\n" + "      \"Fuentes\",\r\n" + "      \"Trevino\"\r\n" + "    ]\r\n" + "  }\r\n"
			+ "]";

	static String ticketJson = "[\r\n" + "  {\r\n" + "    \"_id\": \"436bf9b0-1147-4c0a-8439-6f79833bff5b\",\r\n"
			+ "    \"url\": \"http://initech.tokoin.io.com/api/v2/tickets/436bf9b0-1147-4c0a-8439-6f79833bff5b.json\",\r\n"
			+ "    \"external_id\": \"9210cdc9-4bee-485f-a078-35396cd74063\",\r\n"
			+ "    \"created_at\": \"2016-04-28T11:19:34 -10:00\",\r\n" + "    \"type\": \"incident\",\r\n"
			+ "    \"subject\": \"A Catastrophe in Korea (North)\",\r\n"
			+ "    \"description\": \"Nostrud ad sit velit cupidatat laboris ipsum nisi amet laboris ex exercitation amet et proident. Ipsum fugiat aute dolore tempor nostrud velit ipsum.\",\r\n"
			+ "    \"priority\": \"high\",\r\n" + "    \"status\": \"pending\",\r\n" + "    \"submitter_id\": 2,\r\n"
			+ "    \"assignee_id\": 1,\r\n" + "    \"organization_id\": 101,\r\n" + "    \"tags\": [\r\n"
			+ "      \"Ohio\",\r\n" + "      \"Pennsylvania\",\r\n" + "      \"American Samoa\",\r\n"
			+ "      \"Northern Mariana Islands\"\r\n" + "    ],\r\n" + "    \"has_incidents\": false,\r\n"
			+ "    \"due_at\": \"2016-07-31T02:37:50 -10:00\",\r\n" + "    \"via\": \"web\"\r\n" + "  },\r\n"
			+ "  {\r\n" + "    \"_id\": \"1a227508-9f39-427c-8f57-1b72f3fab87c\",\r\n"
			+ "    \"url\": \"http://initech.tokoin.io.com/api/v2/tickets/1a227508-9f39-427c-8f57-1b72f3fab87c.json\",\r\n"
			+ "    \"external_id\": \"3e5ca820-cd1f-4a02-a18f-11b18e7bb49a\",\r\n"
			+ "    \"created_at\": \"2016-04-14T08:32:31 -10:00\",\r\n" + "    \"type\": \"incident\",\r\n"
			+ "    \"subject\": \"A Catastrophe in Micronesia\",\r\n"
			+ "    \"description\": \"Aliquip excepteur fugiat ex minim ea aute eu labore. Sunt eiusmod esse eu non commodo est veniam consequat.\",\r\n"
			+ "    \"priority\": \"low\",\r\n" + "    \"status\": \"hold\",\r\n" + "    \"submitter_id\": 1,\r\n"
			+ "    \"assignee_id\": 2,\r\n" + "    \"organization_id\": 102,\r\n" + "    \"tags\": [\r\n"
			+ "      \"Puerto Rico\",\r\n" + "      \"Idaho\",\r\n" + "      \"Oklahoma\",\r\n"
			+ "      \"Louisiana\"\r\n" + "    ],\r\n" + "    \"has_incidents\": false,\r\n"
			+ "    \"due_at\": \"2016-08-15T05:37:32 -10:00\",\r\n" + "    \"via\": \"chat\"\r\n" + "  }\r\n" + "]  ";

	@Before
	public void setUp() {
		user = new User();
		ticket = new Ticket();
		organization = new Organization();
	}

	@Test
	public void testSetUserOrgName() throws Exception {

		jsonParser = new JSONParser();
		user.setOrganization_id(101);
		EasyMock.expect(utilsMock.readJsonFile("org")).andReturn(jsonParser.parse(orgJson));
		EasyMock.replay(utilsMock);

		user = utils.setUserOrgName(user);

		assertEquals("Enthaze", user.getOrganization_name());
	}

	@Test
	public void testSetTicketOrgName() throws Exception {
		
		jsonParser = new JSONParser();
		user.setOrganization_id(102);
		
		EasyMock.expect(utilsMock.readJsonFile("org")).andReturn(jsonParser.parse(orgJson));
		EasyMock.replay(utilsMock);

		user = utils.setUserOrgName(user);

		assertEquals("Nutralab", user.getOrganization_name());
	}

	@Test
	public void testSetTicketAssignee() throws Exception {
		jsonParser = new JSONParser();
		ticket.setAssignee_id(1);
		
		EasyMock.expect(utilsMock.readJsonFile("user")).andReturn(jsonParser.parse(userJson));
		EasyMock.replay(utilsMock);

		ticket = utils.setTicketAssignee(ticket);

		assertEquals("Francisca Rasmussen", ticket.getAssignee_name());
	}

	@Test
	public void testSetTicketSubmitter() throws Exception {
		jsonParser = new JSONParser();
		ticket.setSubmitter_id(2);
		
		EasyMock.expect(utilsMock.readJsonFile("user")).andReturn(jsonParser.parse(userJson));
		EasyMock.replay(utilsMock);

		ticket = utils.setTicketSubmitter(ticket);

		assertEquals("Cross Barlow", ticket.getSubmitter_name());
	}

}
