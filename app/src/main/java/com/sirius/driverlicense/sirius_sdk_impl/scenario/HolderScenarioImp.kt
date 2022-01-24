package com.sirius.driverlicense.sirius_sdk_impl.scenario


import com.sirius.library.mobile.scenario.impl.HolderScenario
import com.sirius.driverlicense.repository.EventRepository
import com.sirius.driverlicense.repository.MessageRepository
import com.sirius.driverlicense.repository.models.LocalMessage


class HolderScenarioImp  constructor(val messageRepository: MessageRepository,
                                            val eventRepository: EventRepository) : HolderScenario(eventRepository) {




}