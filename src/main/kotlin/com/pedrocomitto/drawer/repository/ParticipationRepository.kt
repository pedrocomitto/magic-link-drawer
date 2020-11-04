package com.pedrocomitto.drawer.repository

import com.pedrocomitto.drawer.document.Participation
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ParticipationRepository : MongoRepository<Participation, String>