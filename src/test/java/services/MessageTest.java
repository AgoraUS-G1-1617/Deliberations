package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import static org.junit.Assert.assertEquals;

import domain.Message;
import domain.User;
import forms.MessageForm;
import repositories.MessageRepository;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class MessageTest extends AbstractTest {

	
	// Service to test --------------------------------------------------------

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private MessageRepository messageRepository;
	
	// Supporting services ----------------------------------------------------
	
	@Autowired
	private UserService userService;

	
	// Test cases ------------------------------------------------------------
	


	/**
	 * @Test New messages for others users
	 * @result We persist successfully messages to the database and we check.
	 */
	@Test
	public void possitiveTestMessageCRUDTest() {
		authenticate("user1");
		Message newMessage;
		MessageForm newMessageForm;
		User recipient;
		
		recipient = userService.findByUsername("user2");
		newMessage = messageService.create();
		
		newMessageForm = messageService.messageToMessageForm(newMessage);
		newMessageForm.setSubject("subject test");
		newMessageForm.setBody("body test");
		newMessageForm.setRecipient("user2");
		
		newMessage = messageService.save(newMessageForm);
		assertEquals(true, messageRepository.exists(newMessage.getId()));
		assertEquals(true, messageService.findOne(newMessage.getId()).getSubject().equals("subject test"));
		assertEquals(true, messageService.findOne(newMessage.getId()).getRecipient().equals(recipient));
		unauthenticate();
	}
	
	/**
	 * @Test New messages for others users
	 * @result We persist successfully messages to the database and we check, later check that number of messages and pages sent and received is correct.
	 */
	@Test
	public void possitiveTestMessageOtherMethods() {
		Pageable pageable;
		Page<Message> items;
		
		authenticate("user1");
		
		Message newMessage;
		MessageForm newMessageForm;
		Integer numMessagesSent;
		Integer numMessagesReceived;
		
		
		newMessage = messageService.create();
		newMessageForm = messageService.messageToMessageForm(newMessage);
		newMessageForm.setSubject("subject test");
		newMessageForm.setBody("body test");
		newMessageForm.setRecipient("user2");
		newMessage = messageService.save(newMessageForm);
		
		numMessagesSent = messageService.countMessagesSentByUser();
		numMessagesReceived = messageService.countMessagesReceivedtByUser();
		
		Assert.isTrue(numMessagesSent == 1);
		Assert.isTrue(numMessagesReceived == 0);
		
		newMessage = messageService.create();
		newMessageForm = messageService.messageToMessageForm(newMessage);
		newMessageForm.setSubject("subject test2");
		newMessageForm.setBody("body test2");
		newMessageForm.setRecipient("user2");
		newMessage = messageService.save(newMessageForm);
		
		pageable = new PageRequest(1 - 1, 5);
		items = messageService.findAllSent(pageable);
		
		Assert.isTrue(items.getNumberOfElements() == messageService.countMessagesSentByUser());
		
		unauthenticate();
		
		authenticate("user2");
		
		pageable = new PageRequest(1 - 1, 5);
		items = messageService.findAllReceived(pageable);
		
		Assert.isTrue(items.getNumberOfElements() == messageService.countMessagesReceivedtByUser());
		
		unauthenticate();
	}
	
	
	
	
	/**
	 * @Test New messages for others users, 
	 * @result We try to persists messages with same sender and receiver so <code>IllegalArgumentException</code> is expected
	 */
	@Test(expected = IllegalArgumentException.class)
	public void negativeTestMessage() {
		authenticate("user1");
		Message newMessage;
		MessageForm newMessageForm;
		User recipient;
		
		recipient = userService.findByUsername("user1");
		newMessage = messageService.create();
		
		newMessageForm = messageService.messageToMessageForm(newMessage);
		newMessageForm.setSubject("subject test");
		newMessageForm.setBody("body test");
		newMessageForm.setRecipient("user1");
		
		newMessage = messageService.save(newMessageForm);
		assertEquals(true, messageRepository.exists(newMessage.getId()));
		assertEquals(true, messageService.findOne(newMessage.getId()).getSubject().equals("subject test"));
		assertEquals(true, messageService.findOne(newMessage.getId()).getRecipient().equals(recipient));
		unauthenticate();
	}
	
	/**
	 * @Test New messages for others users
	 * @result We try to persists messages without body, so <code>IllegalArgumentException</code> is expected
	 */
	@Test(expected = IllegalArgumentException.class)
	public void negativeTestMessage2() {
		authenticate("user1");
		Message newMessage;
		MessageForm newMessageForm;
		User recipient;
		
		recipient = userService.findByUsername("user1");
		newMessage = messageService.create();
		
		newMessageForm = messageService.messageToMessageForm(newMessage);
		newMessageForm.setSubject("subject test");
		newMessageForm.setRecipient("user1");
		
		newMessage = messageService.save(newMessageForm);
		assertEquals(true, messageRepository.exists(newMessage.getId()));
		assertEquals(true, messageService.findOne(newMessage.getId()).getSubject().equals("subject test"));
		assertEquals(true, messageService.findOne(newMessage.getId()).getRecipient().equals(recipient));
		unauthenticate();
	}

	/**
	 * @Test New messages for others users with script in body.
	 * @result We try to persists messages, so <code>IllegalArgumentException</code> is expected
	 */
	@Test(expected = IllegalArgumentException.class)
	public void negativeTestMessage3() {
		authenticate("user1");
		Message newMessage;
		MessageForm newMessageForm;
		User recipient;
		String badText = "<script>window.alert(hacked!)</script>";
		
		recipient = userService.findByUsername("user1");
		newMessage = messageService.create();
		
		newMessageForm = messageService.messageToMessageForm(newMessage);
		newMessageForm.setSubject("subject test");
		newMessageForm.setBody(badText);
		newMessageForm.setRecipient("user1");
		
		newMessage = messageService.save(newMessageForm);
		assertEquals(true, messageRepository.exists(newMessage.getId()));
		assertEquals(true, messageService.findOne(newMessage.getId()).getSubject().equals("subject test"));
		assertEquals(true, messageService.findOne(newMessage.getId()).getRecipient().equals(recipient));
		unauthenticate();
	}
	
	
	
	
	
	
	
	
	
}
