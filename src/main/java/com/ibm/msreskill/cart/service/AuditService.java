package com.ibm.msreskill.cart.service;

import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.msreskill.cart.audit.stream.AuditStream;

@Service
public class AuditService {

	private final AuditStream auditStream;
	private static Logger log = LoggerFactory.getLogger(AuditService.class);
	
	public AuditService(AuditStream auditStream)
	{
		this.auditStream = auditStream;
	}
	
	public void publishAuditEvent(final ResponseEntity<?> response)
	{
		
		
		Executors.newFixedThreadPool(1).execute(() -> {

			String responseJSON = "";
			try {
				
				ObjectMapper obj = new ObjectMapper();
				responseJSON = obj.writeValueAsString(response);
				MessageChannel messageChannel = auditStream.outboundAudit();
				messageChannel.send(MessageBuilder.withPayload(responseJSON)
						.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.TEXT_PLAIN_VALUE).build());

			} catch (Throwable e) {
				log.error("Error :" + e.getMessage());
				log.info("Discount Respnsek : "+responseJSON);
			}

		});
	}
}
