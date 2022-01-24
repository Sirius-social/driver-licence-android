package com.sirius.driverlicense.sirius_sdk_impl.scenario


import com.sirius.library.mobile.scenario.impl.QuestionAnswerScenario
import com.sirius.driverlicense.repository.EventRepository
import com.sirius.driverlicense.repository.MessageRepository

class QuestionAnswerScenarioImp  constructor(val messageRepository: MessageRepository,
                                                    val eventRepository: EventRepository) : QuestionAnswerScenario(eventRepository) {


}