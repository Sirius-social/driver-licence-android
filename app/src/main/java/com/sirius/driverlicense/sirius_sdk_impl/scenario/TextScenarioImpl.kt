package com.sirius.driverlicense.sirius_sdk_impl.scenario


import com.sirius.library.mobile.scenario.impl.TextScenario
import com.sirius.driverlicense.repository.EventRepository
import com.sirius.driverlicense.repository.MessageRepository

class TextScenarioImpl  constructor(val messageRepository: MessageRepository,
                                           val eventRepository: EventRepository): TextScenario(eventRepository) {

}