package services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Message;
import domain.User;
import forms.MessageForm;
import repositories.MessageRepository;

@Service
@Transactional
public class MessageService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private MessageRepository messageRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserService userService;

	// Constructors -----------------------------------------------------------

	public MessageService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Message create() {
		Message result;
		User user;
		long segundos;
		Date momento;

		result = new Message();
		user = userService.findOneByPrincipal();
		segundos = System.currentTimeMillis() - 1;
		momento = new Date(segundos);

		result.setMoment(momento);
		Assert.notNull(user);
		result.setSender(user);

		return result;
	}
	
	public Message findOne(int messageId) {
		Message result;

		result = messageRepository.findOne(messageId);

		return result;
	}

	public Message save(MessageForm messageForm) {
		Message message;
		Message res;
		Assert.notNull(messageForm);

		message = MessageFormToMessage(messageForm);

	
		res = messageRepository.save(message);
		
		return res;
	}

	// Other business methods -------------------------------------------------

	public MessageForm messageToMessageForm(Message message) {
		MessageForm res;

		res = new MessageForm();

		res.setSender(message.getSender().getUserAccount().getUsername());
		res.setMoment(message.getMoment());
		
		return res;

	}

	public Message MessageFormToMessage(MessageForm messageForm) {
		Message res;

		res = create();
			
		// Comprobamos si existe el usuario al que le queremos enviar el mensaje

		User recipient = userService.findByUsername(messageForm.getRecipient());
		Assert.notNull(recipient,"message.error.recipient");
		res.setRecipient(recipient);
		
		
		res.setMoment(messageForm.getMoment());
		res.setSubject(messageForm.getSubject());
		res.setBody(messageForm.getBody());
		
		//Comprobamos que el sender y el recipient sean diferentes
		
		Assert.isTrue(!res.getSender().equals(res.getRecipient()),"message.error.not.allow.send.yourself");
		
		return res;

	}

	public Page<Message> findAllReceived(Pageable page) {
		Page<Message> result;
		User user;

		user = userService.findOneByPrincipal();
		Assert.notNull(user);

		result = messageRepository.messagesReceived(user.getId(), page);
		Assert.notNull(result);
		return result;
	}

	public Page<Message> findAllSent(Pageable page) {
		Page<Message> result;
		User user;

		user = userService.findOneByPrincipal();
		Assert.notNull(user);

		result = messageRepository.messagesSent(user.getId(), page);
		Assert.notNull(result);
		return result;
	}
	
	public int countMessagesSentByUser(){
		int res;
		User user;
		
		user = userService.findOneByPrincipal();
		
		Assert.notNull(user);
		
		res = messageRepository.countMessagesSentByUserId(user.getId());
		
		return res;
	}
	
	public int countMessagesReceivedtByUser(){
		int res;
		User user;
		
		user = userService.findOneByPrincipal();
		
		Assert.notNull(user);
		
		res = messageRepository.countMessagesReceivedtByUserId(user.getId());
		
		return res;
	}
	
}