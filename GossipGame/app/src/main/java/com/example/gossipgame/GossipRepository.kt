package com.example.gossipgame

class GossipRepository {
    private val gossips = mutableListOf<Gossip>()

    fun addGossip(gossip: Gossip) {
        gossips.add(gossip)
    }

    fun getRandomGossip(): Gossip {
        return gossips.random()
    }

    fun size(): Int {
        return gossips.size
    }
}