package jw.org.asignaciones.notification

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException


@Component
@ConditionalOnProperty(prefix = "telegrambots", name = ["enabled"], havingValue = "true", matchIfMissing = true)
class TelegramNotifier (
    @Value("\$telegrambots.apikey")
    private val apiKey: String,
    @Value("\$telegrambots.username")
    private val userName: String,
    private val telegramBotsApi: TelegramBotsApi
): TelegramLongPollingBot(apiKey) {

    override fun getBotUsername(): String = userName

    override fun getBotToken(): String = apiKey

    override fun onRegister() {}
    override fun onUpdateReceived(update: Update) {
        val command: String = update.getMessage().getText()
        if (command == "/hello") {
            val message = "Hello, dear friend!"
            val response = SendMessage()
            response.setChatId(update.getMessage().getChatId().toString())
            response.text = message
            try {
                execute(response)
            } catch (e: TelegramApiException) {
                e.printStackTrace()
            }
        }
    }

    override fun onUpdatesReceived(updates: List<Update?>?) {}

    @Throws(TelegramApiException::class)
    private fun sendNotification(chatId: String, msg: String) {
        val response = SendMessage(chatId, msg)
        execute(response)
    }
}

/*
referencia:

https://monsterdeveloper.gitbook.io/java-telegram-bot-tutorial/
and the corresponfing repo
https://github.com/MonsterDeveloper/java-telegram-bot-tutorial/



*/