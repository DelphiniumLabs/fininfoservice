package com.goit.fininfoservice;



import com.goit.fininfoservice.telegram.Bot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class FininfoserviceApplication {


	public static void main(String[] args) {
		SpringApplication.run(FininfoserviceApplication.class, args);

	}

}
