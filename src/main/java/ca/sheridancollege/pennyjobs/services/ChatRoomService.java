package ca.sheridancollege.pennyjobs.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.sheridancollege.pennyjobs.beans.ChatRoom;
import ca.sheridancollege.pennyjobs.repositories.ChatRoomRepository;

@Service
public class ChatRoomService {
	
	@Autowired private ChatRoomRepository crRepo;
	
	public Optional<String> getChatId(String senderId, String recipientId, boolean createIfNotExist) {
		
		return crRepo
				.findBySenderIdAndRecipientId(senderId, recipientId)
				.map(ChatRoom::getChatId)
				.or(() -> {
					if(!createIfNotExist) {
						return Optional.empty();
					}
					
					var chatId = String.format("%s_%s", senderId, recipientId);
					
					ChatRoom senderRecipient = ChatRoom
							.builder()
							.chatId(chatId)
							.senderId(senderId)
							.recipientId(recipientId)
							.build();
					
					ChatRoom recipientSender = ChatRoom
							.builder()
							.chatId(chatId)
							.senderId(recipientId)
							.recipientId(senderId)
							.build();
					
					crRepo.save(senderRecipient);
					crRepo.save(recipientSender);
					
					return Optional.of(chatId);
				});
		
	}

}
