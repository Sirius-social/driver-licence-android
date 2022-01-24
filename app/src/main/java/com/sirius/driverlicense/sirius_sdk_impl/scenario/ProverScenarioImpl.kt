package com.sirius.driverlicense.sirius_sdk_impl.scenario


import com.sirius.library.mobile.scenario.impl.ProverScenario
import com.sirius.driverlicense.repository.EventRepository
import com.sirius.driverlicense.repository.MessageRepository

class ProverScenarioImpl  constructor(val messageRepository: MessageRepository,
                                             val eventRepository: EventRepository): ProverScenario(eventRepository) {


}