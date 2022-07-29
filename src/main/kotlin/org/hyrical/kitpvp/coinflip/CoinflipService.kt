package org.hyrical.kitpvp.coinflip

import java.util.UUID

object CoinflipService {
    val coinflips = hashMapOf<UUID, Coinflip>()
    val activeCoinFlips: ArrayList<Coinflip> = arrayListOf()

    data class Coinflip(val player: UUID, val otherPlayer: UUID?, val amount: Double)
}